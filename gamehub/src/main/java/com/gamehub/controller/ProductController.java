package com.gamehub.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.PageBtn;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.product.ProductService;
import com.gamehub.util.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	@Inject
	private ProductService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	//상품리스트 페이지
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void productList(SearchPaging sp, Model model) throws Exception {		
		logger.info("show list......................");
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
	    //현재 페이지에 만들 페이지버튼에 대한 정보를 보낸다.
	    model.addAttribute("pb", pb);
	    //총 숫자도 페이지에 보낸다.
	    model.addAttribute("cnt", aticleCnt);
	    //현재페이지의 자식 카테고리리스트도 보낸다.
	    model.addAttribute("catelist", service.childCategory(sp.getCateCode()));
		model.addAttribute("sp", sp);
		model.addAttribute("pvlist", pvlist);
	}
	//상품리스트 페이지 바둑판 형식으로
	@RequestMapping(value = "/cube", method = RequestMethod.GET)
	public void productCube(SearchPaging sp, Model model) throws Exception {		
		logger.info("show list......................");
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
	    //현재 페이지에 만들 페이지버튼에 대한 정보를 보낸다.
	    model.addAttribute("pb", pb);
	    //총 숫자도 페이지에 보낸다.
	    model.addAttribute("cnt", aticleCnt);
	    //현재페이지의 자식 카테고리리스트도 보낸다.
	    model.addAttribute("catelist", service.childCategory(sp.getCateCode()));
		model.addAttribute("sp", sp);
		model.addAttribute("pvlist", pvlist);
	}
	
	//상품리스트 페이지 바둑판 형식으로
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void productRead(int pd_num, Model model) throws Exception {
		Map<String, Object> readMap = service.productRead(pd_num);
	
		model.addAttribute("pv", readMap.get("pv"));
		model.addAttribute("alist", readMap.get("avo"));
	}
}
