package com.gamehub.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gamehub.admin.product.AdminProductService;
import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.PageBtn;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.util.MediaUtils;
import com.gamehub.util.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminProductController {

	@Inject
	private AdminProductService service;

	@Resource(name = "uploadPath")
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

	//상품추가 페이지
	@RequestMapping(value = "/product/register", method = RequestMethod.GET)
	public void productAdd(@ModelAttribute("sp") SearchPaging sp, Model model) throws Exception {		
		List<CategoryVO> ctglist = service.getCategory();		

		model.addAttribute("cgList", JSONArray.fromObject(ctglist));	
	}
	//상품추가 페이지
	@RequestMapping(value = "/product/register", method = RequestMethod.POST)
	public String productAddPOST(ProductVO pv, RedirectAttributes rttr) throws Exception {		
		logger.info("물품등록 시작");
		logger.info(pv.toString());
		
		service.register(pv);
		
		rttr.addFlashAttribute("msg", "productAdd");
		return "redirect:/admin/product/list";
	}

	//상품리스트 페이지
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public void productList(SearchPaging sp, Model model) throws Exception {		
		logger.info("show list......................");
		List<CategoryVO> ctglist = service.getCategory();
		if(sp.getKeyword() != null) {
			sp.setKeyword(URLDecoder.decode(sp.getKeyword(), "utf-8"));
		}
		logger.info(sp.toString());
		List<ProductVO> pvlist = service.getlistSearch(sp);
		for (ProductVO pv : pvlist) {
			pv.setPd_img(URLEncoder.encode(UploadFileUtils.addScalr(pv.getPd_img()), "utf-8"));			
			logger.info(pv.toString());
		}	    
	    //모든 컬럼의 숫자를 가져온다.
	    int aticleCnt = service.getSearchCount(sp);
	    //페이지 버튼 클래스 생성
	    PageBtn pb = new PageBtn();
	    //현재 페이지 정보를 페이지버튼에 넣어준다.
	    pb.setPaging(sp);
	    //총 컬럼의 숫자를 페이지 버튼에 넣어준다.
	    pb.setTotalcol(aticleCnt);
	    if(sp.getCateCode() == null || sp.getCateCode().equals(null)){
	    	Map<String, Object> cateMap = new HashMap<>();
			cateMap.put("cg_code", "0");
			cateMap.put("cg_parent", "0");
			cateMap.put("cg_ancestor", "0");
			logger.info("list map:" + cateMap);
			model.addAttribute("cateMap", cateMap);
	    }else {
	    	Map<String,Object> cateMap = service.getSearchCategory(sp);
	    	logger.info("list map:" + cateMap);
	    	model.addAttribute("cateMap", cateMap);
	    }
	    //현재 페이지에 만들 페이지버튼에 대한 정보를 보낸다.
	    model.addAttribute("pb", pb);
	    //총 숫자도 페이지에 보낸다.
	    model.addAttribute("cnt", aticleCnt);
		model.addAttribute("cgList", JSONArray.fromObject(ctglist));
		model.addAttribute("spjson", JSONArray.fromObject(sp));
		model.addAttribute("sp", sp);
		model.addAttribute("pvlist", pvlist);
	}
	
	//상품수정 페이지
	@RequestMapping(value = "/product/modify", method = RequestMethod.GET)
	public void productModifyGet(@ModelAttribute("sp") SearchPaging sp, int pd_num, Model model) throws Exception {		
		logger.info("show modify......................pd_num : "+pd_num);
		//카테고리 정보 얻어오기
		List<CategoryVO> ctglist = service.getCategory();
		//업데이트에 사용할 정보 얻어오기
		Map<String, Object> modifyMap = service.getModifyInfo(pd_num);
		
		model.addAttribute("cgList", JSONArray.fromObject(ctglist));
		model.addAttribute("pv", modifyMap.get("pv"));
		model.addAttribute("avo", JSONArray.fromObject(modifyMap.get("avo")));
	}
	
	//상품수정 작업
	@RequestMapping(value = "/product/modify", method = RequestMethod.POST)
	public String productModifyPost(@ModelAttribute("sp") SearchPaging sp, 
			ProductVO pv, RedirectAttributes rttr) throws Exception {		
		logger.info("show modifypost..............pv : "+pv.toString());
		
		service.update(pv);
		
		rttr.addAttribute("pageNum", sp.getPageNum());
		rttr.addAttribute("pageSize", sp.getPageSize());
		rttr.addAttribute("searchType", sp.getSearchType());
		rttr.addAttribute("keyword", sp.getKeyword());
		rttr.addAttribute("cateCode", sp.getCateCode());
		rttr.addAttribute("minPrice", sp.getMinPrice());
		rttr.addAttribute("maxPrice", sp.getMaxPrice());
		rttr.addAttribute("date", sp.getDate());
		rttr.addAttribute("display", sp.getDisplay());
		rttr.addFlashAttribute("msg", "productModify");
		return "redirect:/admin/product/list";		
	}
	
	//상품삭제 페이지
	@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	public String productDelete(@ModelAttribute("sp") SearchPaging sp, int pd_num, RedirectAttributes rttr) throws Exception {		
		logger.info("show delete......................pd_num : "+pd_num);
		//삭제하기 전 파일을 지울 정보 얻어오기
		Map<String, Object> modifyMap = service.getModifyInfo(pd_num);
		//상품정보 받아오기
		ProductVO pv = (ProductVO)modifyMap.get("pv");
		//상품관련 업로드 파일정보 받아오기
		List<AttachVO> avoList = (List<AttachVO>) modifyMap.get("avo");
		//upload폴더에 저장된 메인이미지와 추가 이미지 파일들을 삭제한다.
		deleteFile(pv.getPd_img());
		for (AttachVO avo : avoList) deleteFile(avo.getA_fullname());
		service.delete(pd_num);
		
		rttr.addAttribute("pageNum", sp.getPageNum());
		rttr.addAttribute("pageSize", sp.getPageSize());
		rttr.addAttribute("searchType", sp.getSearchType());
		rttr.addAttribute("keyword", sp.getKeyword());
		rttr.addAttribute("cateCode", sp.getCateCode());
		rttr.addAttribute("minPrice", sp.getMinPrice());
		rttr.addAttribute("maxPrice", sp.getMaxPrice());
		rttr.addAttribute("date", sp.getDate());
		rttr.addAttribute("display", sp.getDisplay());
		rttr.addFlashAttribute("msg", "productDelete");
		return "redirect:/admin/product/list";		
	}
	
	//상품구매여부 업데이트
	@ResponseBody
	@RequestMapping(value = "/product/approval", method = RequestMethod.POST)
	public ResponseEntity<String> approvalPost(String pd_num, String pd_buy) throws Exception {		
		logger.info("승인여부 업데이트 시작");
		logger.info(pd_num.toString() + pd_buy.toString());
		service.approval(pd_num, pd_buy);
						
		return new ResponseEntity<String>("updated", HttpStatus.OK);
	}
	
	//상품추가 ck에디터의 이미지 업로드 기능
	@RequestMapping("/product/imageUpload")
	public void imageUpload(HttpServletRequest request, 
			HttpServletResponse response, @RequestParam MultipartFile upload)throws Exception {
		System.out.println("서버로옴");
		OutputStream out = null;
		PrintWriter pw = null;

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			HttpSession session = request.getSession(); 
			String root_path = session.getServletContext().getRealPath("/");
			//String uploadPath =root_path + "resources\\upload\\ckeditor\\" + fileName;
			String uploadPath =root_path + "resources/upload/ckeditor/" + fileName;
			System.out.println("ck에디터 업로드 경로"+uploadPath);
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			out.flush();

			String callback = request.getParameter("CKEditorFuncNum");
			System.out.println(callback);
			pw = response.getWriter();
			//CKEDITOR에 업로드 된 서버측의 파일경로를 반환하는 목적
			String fileUrl = "/resources/upload/ckeditor/" + fileName;

			pw.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
					+ callback + ",'" + fileUrl + "','이미지를 업로드 하였습니다.')</script>");
			pw.flush();
		} catch (Exception e) {}
		finally {
			try {
				if(out != null) {out.close();}
				if(pw != null) {pw.close();}
			} catch (IOException e2) {}
		}
		return;
	}

	//파일 업로드
	@ResponseBody
	@RequestMapping(value ="/product/uploadAjax", method=RequestMethod.POST, 
	produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file)throws Exception{
		//파일을 매개변수로 받았고 리턴값은 파일의 이름이다. ResponseEntity는 리턴값의 상태를 같이 보내는 것이다.
		logger.info("originalName: " + file.getOriginalFilename());	   
		return //서블릿 컨텍스트에 정의해놓은 업로드 경로, 업로드한 파일이름, 파일을 바이터화해서 업로드 메소드로 전송
				new ResponseEntity<String>(//리턴된 파일경로를 ajax로 리턴해준다.
						UploadFileUtils.uploadFile(uploadPath, 
								file.getOriginalFilename(), 
								file.getBytes()), 
						HttpStatus.CREATED);
	}	  
	
	//파일을 보여준다.
	@ResponseBody
	@RequestMapping("/product/displayFile")
	public ResponseEntity<byte[]>  displayFile(String fileName)throws Exception{//파일이름을 매개변수로 받는다.    
		InputStream in = null; //인풋 스트림을 생성
		fileName = URLDecoder.decode(fileName, "utf-8");
		ResponseEntity<byte[]> entity = null;//리턴값을 생성    
		logger.info("FILE NAME: " + fileName);	    
		try{
			//파일 포멧을 가져온다.
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);	      
			//파일포멧이 이미지 타입인지 확인한다.
			MediaType mType = MediaUtils.getMediaType(formatName);	      
			//패킷에 정보를 담아줄 헤더를 만들어준다.
			HttpHeaders headers = new HttpHeaders();	      
			//만들어 놓은 인풋스트림에 업로드 경로와 매개변수로 넘어온 파일이름을 넣어준다.
			in = new FileInputStream(uploadPath+fileName);	      
			//파일이 이미지 타입일 경우
			if(mType != null){
				//헤더에 타입을 이미지 타입으로 셋팅한다.
				headers.setContentType(mType);
			}else{	        
				//파일이름을 가져온다.
				fileName = fileName.substring(fileName.indexOf("_")+1);
				//클릭 시 다운로드 받을 수 있게끔 헤더에 설정해줌
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+ 
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			//리턴할 데이터에 생성시킨 인풋스트림을 byte배열로 변환하여 헤더정보와 같이 보낸다.
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), 
					headers, 
					HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;    
	}

	//파일을 삭제한다.
	@ResponseBody
	@RequestMapping(value="/product/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) throws UnsupportedEncodingException{//스케일된 파일경로를 받아옴
		fileName = URLDecoder.decode(fileName, "utf-8");
		logger.info("delete file: "+ fileName);
		//포멧을 받아온다.
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);	    
		MediaType mType = MediaUtils.getMediaType(formatName);	    
		if(mType != null){//이미지 타입일 경우
			//s_를 빼는 부분
			String front = fileName.substring(0,13);
			String end = fileName.substring(15);
			//기본 경로에 파일경로를 더하고 /를 \로 변환시켜 실제 경로에 있는 파일을 삭제
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}	    
		//기본적으로 넘어온 파일을 삭제 이미지 타입은 s_붙은걸 삭제하고 일반 파일은 스케일을 안하기 때문에 기본만 삭제해도 된다.
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();	    
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
