package com.gamehub.product;

import java.util.List;
import java.util.Map;

import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;

public interface ProductService {
	//카테고리 가져오기
	public List<CategoryVO> getCategory()throws Exception;
	//상품목록 가져오기
	public List<ProductVO> getlistSearch(SearchPaging sp) throws Exception;
	//검색한 상품 숫자 가져오기
	public Integer getSearchCount(SearchPaging sp) throws Exception;
	//들어간 페이지의 자식 카테고리 가져오기
	public List<CategoryVO> childCategory(String cateCode)throws Exception;
	//상품 정보 받아오기
	public Map<String, Object> productRead(int pd_num)throws Exception;
}
