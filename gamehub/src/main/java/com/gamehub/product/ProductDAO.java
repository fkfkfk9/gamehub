package com.gamehub.product;

import java.util.List;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;


public interface ProductDAO {
	//카테고리 가져오기
	public List<CategoryVO> getCategory()throws Exception;
	//서치 정보가 들어간 리스트 받아오기
	public List<ProductVO> getlistSearch(SearchPaging sp)throws Exception;
	//서치된 모든 vo갯수 받아오기
	public Integer getSearchCount(SearchPaging sp)throws Exception;
	//업데이트 정보 얻어오기
	public ProductVO getProductInfo(int pd_num)throws Exception;
	//업데이트시 추가 이미지 정보 받아오기
	public List<AttachVO> getAttachInfo(int pd_num)throws Exception;
	//검색 후 사용된 카테고리 정보
	public ProductVO getSearchCategory(String cateCode)throws Exception;
	//들어간 페이지의 자식 카테고리 가져오기
	public List<CategoryVO> childCategory(String cateCode)throws Exception;
}
