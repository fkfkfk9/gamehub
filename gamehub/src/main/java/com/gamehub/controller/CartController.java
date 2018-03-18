package com.gamehub.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamehub.cart.CartService;
import com.gamehub.domain.CartVO;
import com.gamehub.domain.LoginDTO;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	@Inject
	private CartService service;
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	//장바구니 추가
	@ResponseBody
	@RequestMapping(value ="/add", method=RequestMethod.POST)
	public String cartadd(CartVO cv, HttpServletRequest request)throws Exception{
		logger.info("cart Add start!!!!!!!!!");
		
		String result = "";
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO) session.getAttribute("loginDto");
		if(dto != null) {
			cv.setMem_id(dto.getMem_id());
		}else {
			logger.info("로그인을 하지 않음");
			result = "login";
			return result;
		}
		logger.info(cv.toString());	   
		result = service.cartAdd(cv);
		return result;
	}	  
	
	//장바구니 수정
	@ResponseBody
	@RequestMapping(value ="/update", method=RequestMethod.POST)
	public String cartupdate(CartVO cv)throws Exception{
		logger.info("cart update start!!!!!!!!!");
		logger.info(cv.toString());	
		service.cartUpdate(cv);
		return "ok";
	}
	//장바구니 수정
	@ResponseBody
	@RequestMapping(value ="/modify", method=RequestMethod.POST)
	public String cartmodify(CartVO cv)throws Exception{
		logger.info("cart modify start!!!!!!!!!");
		logger.info(cv.toString());	
		service.amountModify(cv);
		return "ok";
	}
	//장바구니 수정
	@ResponseBody
	@RequestMapping(value ="/delete", method=RequestMethod.POST)
	public String cartdelete(@RequestParam(value="cclist", required=true) List<String> cclist)throws Exception{
		logger.info("cart delete start!!!!!!!!!");
		logger.info(cclist.size() + "");	
		String result = "";
		
		if(cclist.size() == 0) {
			result = "nodata";
		}else {
			service.cartDelete(cclist);
			result = "ok";
		}
		return result;
	}
	//장바구니 리스트
	@RequestMapping(value ="/list", method=RequestMethod.GET)
	public void cartList(HttpServletRequest request, Model model)throws Exception{
		logger.info("cart list start!!!!!!!!!");
		
		HttpSession session = request.getSession();
		LoginDTO dto = (LoginDTO) session.getAttribute("loginDto");
		if(dto != null) {
			model.addAttribute("clist", service.getCartList(dto.getMem_id()));
		}else {
			logger.info("로그인을 하지 않음");
		}
	}	  
}
