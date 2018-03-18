package com.gamehub.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gamehub.member.MemberDAO;
import com.gamehub.member.MemberService;
import com.gamehub.domain.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	
	@Inject
	private MemberService service;
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Test
	public void testregisterMem() throws Exception{
		MemberVO mv = new MemberVO();
		mv.setMem_id("user01@gmail.com");
		mv.setMem_name("유종현");
		mv.setMem_passwd("1111");
		mv.setMem_addr_num("12345");
		mv.setMem_addr_basic("경기도 안양시 동안구 동편로 120");
		mv.setMem_addr_detail("동편마을아파트 106동 1606호");
		mv.setMem_tel("010-4590-5395");
		mv.setMem_nick("네파");
		mv.setMem_receive("Y");
		dao.registerMem(mv);
	}
	
	@Test//DB에서 넣었던 암호를 암호화 해주기 즉 더미데이터 암호 초기화 시켜주기
	public void passwdchange() throws Exception{
		service.adminpw();
	}
	
}
