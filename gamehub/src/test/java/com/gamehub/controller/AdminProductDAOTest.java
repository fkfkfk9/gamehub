package com.gamehub.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gamehub.admin.product.AdminProductDAO;
import com.gamehub.admin.product.AdminProductService;
import com.gamehub.domain.ProductVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class AdminProductDAOTest {

	@Inject
	private AdminProductDAO dao;
	
	@Inject
	private AdminProductService service;
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(AdminProductDAOTest.class);
	
	@Test
	public void testregisterProduct() throws Exception{
		ProductVO pv = new ProductVO();
		pv.setCg_code("PcStSports");
		pv.setPd_name("Football Manager 2018");
		pv.setPd_price(54000);
		pv.setPd_discount(5);
		pv.setPd_develop("Sports Interactive");
		pv.setPd_content("Football Manager 2018은 공전의"
				+ " 히트를 기록한 베스트셀러 시리즈 최신 버전입니다. ");
	    pv.setPd_requirement("최소:\r\n" + "운영체제: Windows10- 64-bit or 32-bit");
	    pv.setPd_publisher("SEGA");
	    pv.setPd_img("/s_default_img.jpg");
	    pv.setPd_amount(50);
	    pv.setPd_buy("N");
	    pv.setPd_korea("Y");
	    pv.setPd_release("2017년 11월 10일");
	    pv.setPd_bonus("특전은 없습니다.");
		dao.register(pv);
	}
	
	
}
