package com.gamehub.product.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.product.ProductDAO;
import com.gamehub.product.ProductService;
import com.gamehub.util.UploadFileUtils;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	
	@Override
	public List<CategoryVO> getCategory() throws Exception {
		return dao.getCategory();
	}

	@Override
	public List<ProductVO> getlistSearch(SearchPaging sp) throws Exception {
		return dao.getlistSearch(sp);
	}

	@Override
	public Integer getSearchCount(SearchPaging sp) throws Exception {
		return dao.getSearchCount(sp);
	}

	@Override
	public List<CategoryVO> childCategory(String cateCode) throws Exception {
		return dao.childCategory(cateCode);
	}

	@Override
	public Map<String, Object> productRead(int pd_num) throws Exception {
		Map<String, Object> readMap = new HashMap<>();
		ProductVO pv = dao.getProductInfo(pd_num);
				
		List<AttachVO> list = dao.getAttachInfo(pd_num);
		//익스플로러 한글인식 불가 문제로 이미지 경로 인코딩
		if(list != null) {
			for (AttachVO attachVO : list) {
				attachVO.setA_scalname(URLEncoder.encode(attachVO.getA_scalname(), "utf-8"));
				attachVO.setA_fullname(URLEncoder.encode(attachVO.getA_fullname(), "utf-8"));
			}
		}		
		
		if(pv != null) 
			pv.setPd_img(URLEncoder.encode(UploadFileUtils.addScalr(pv.getPd_img()), "utf-8"));		
		
		readMap.put("pv", pv);
		readMap.put("avo", list);
		return readMap;
	}
	
	
}
