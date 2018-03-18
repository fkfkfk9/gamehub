package com.gamehub.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gamehub.domain.LoginDTO;
import com.gamehub.domain.MemberVO;
import com.gamehub.member.MemberService;
import com.gamehub.util.MailHandler;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;
	@Inject
	private JavaMailSender mailSender;
	
	//약관동의 페이지
	@RequestMapping(value = "/agreePage", method = RequestMethod.GET)
	public void agreePage() throws Exception {
		
	}
	//회원가입 폼
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGet() throws Exception {
		
	}
	//회원가입 정보 받아와 DB에 저장
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("member")MemberVO member, RedirectAttributes rttr) throws Exception {
		logger.info("register post 진입");
		logger.info(member.toString());
		service.registerMem(member);
		MailHandler sendMail = new MailHandler(mailSender);
		sendMail.setSubject("[GameHub 회원가입 이메일 인증]");
		sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
				.append("<a href='http://localhost:8088/member/verify?mem_id=" + member.getMem_id())
				.append("' target='_blenk'>이메일 인증 확인</a>").toString());
		sendMail.setFrom("fkfkfk999@gmail.com", "GameHub");
		sendMail.setTo(member.getMem_id());
		sendMail.send();
		rttr.addFlashAttribute("msg", "register");
		return "redirect:/";
	}
	
	//로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {		
		logger.info("loginGET 진입");
	}
	
	//로그인 작업
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(LoginDTO dto, HttpServletRequest request, 
			RedirectAttributes rttr) throws Exception {	
		logger.info("loginPOST 진입");
		logger.info(dto.toString());
		String result = service.login(dto, request.getSession());
		if(result.equals("login")) {
			rttr.addFlashAttribute("msg", result);
			return "redirect:/";
		}else if(result.equals("admin")) {
			rttr.addFlashAttribute("msg", result);
			return "redirect:/";
		}else {
			rttr.addFlashAttribute("msg", result);
			return "redirect:/member/login";
		}
		
	}
	//로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {	
		request.getSession().invalidate();
		return "redirect:/";
	}
		
	//이메일 인증 처리
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String verify(@RequestParam String mem_id, RedirectAttributes rttr)throws Exception {
		logger.info("이메일 인증기능 처리");
		service.verifyMember(mem_id);
		rttr.addFlashAttribute("msg", "verify");
		return "redirect:/";
	}
	
	//마이페이지
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public void mypage()throws Exception {
	}
	
	//업데이트 페이지
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(HttpServletRequest request, Model model)throws Exception {
		model.addAttribute("member", service.updateInfo(request.getSession()));
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(MemberVO mv, RedirectAttributes rttr)throws Exception {
		logger.info("업데이트 : " + mv.toString());
		service.update(mv);
		rttr.addFlashAttribute("msg", "modify");
		return "redirect:/";
	}
	
	//회원탈퇴 기능
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void deleteGET()throws Exception {
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(LoginDTO dto, RedirectAttributes rttr, HttpServletRequest request)throws Exception {
		String result = service.delete(dto, request.getSession());
		logger.info(result);
		if(result.equals("delete")) {
			rttr.addFlashAttribute("msg", result);
			return "redirect:/";
		}else {
			rttr.addFlashAttribute("msg", result);
			return "redirect:/member/delete";
		}
	}

	//가입완료 페이지
	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public void complete() throws Exception {
		
	}
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheck(HttpServletRequest request)throws Exception{
		logger.info("idCheck start!!");
		String result = "N";
		String mem_id = service.idCheck(request.getParameter("mem_id"));
		if(mem_id == null || mem_id.equals(null)) {
			result = "Y";
		}
		logger.info("result :" + result);
		return result; 
	}
	//닉네임 중복체크
	@ResponseBody
	@RequestMapping(value = "/nickCheck", method = RequestMethod.POST)
	public String nickCheck(HttpServletRequest request)throws Exception{
		logger.info("nickCheck start!! : " + request.getParameter("mem_nick"));
		String result = "N";
		String mem_nick = service.nickCheck(request.getParameter("mem_nick"));
		if(mem_nick == null || mem_nick.equals(null)) {
			result = "Y";
		}
		logger.info("result :" + result);
		return result; 
	}
	//아이디 찾기
	@ResponseBody
	@RequestMapping(value = "/searchId", method = RequestMethod.POST)
	public String searchIdPOST(LoginDTO dto) throws Exception {	
		logger.info("searchId start!! : " + dto.getMem_name());
		String result = service.searchId(dto);		
		logger.info("결과값 : " + result);
		return result;
	}
	//암호 찾기
	@ResponseBody
	@RequestMapping(value = "/searchPw", method = RequestMethod.POST)
	public String searchPwPOST(LoginDTO dto) throws Exception {	
		logger.info("searchPw start!! : " + dto.toString());
		String result = "";
		result = service.searchPw(dto);
		logger.info("result : " + result);
		if(result.equals("failid")) {
			result = "failid";
		}else if(result.equals("nosearch")) {
			result = "nosearch";
		}else {
			MailHandler sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[GameHub 임시 비밀번호 발급]");
			sendMail.setText(new StringBuffer().append("<h1>임시 비밀번호</h1>")
					.append("<p>회원님의 비밀번호는 임시비밀번호로 대체되었습니다.</p>")
					.append("<p>임시비밀번호로 로그인 한 후 회원정보수정에서 원하는 비밀번호로 변경하십시오.</p>")
					.append("<p>임시 비밀번호 : "+ result +"</p>").toString());
			sendMail.setFrom("fkfkfk999@gmail.com", "GameHub");
			sendMail.setTo(dto.getMem_id());
			sendMail.send();
			result = "searchPw";
		}		
		return result;
	}
}
