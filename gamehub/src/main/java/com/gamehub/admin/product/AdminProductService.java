package com.gamehub.admin.product;

import java.util.List;
import java.util.Map;

import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;

public interface AdminProductService {
	//상푸추가
	public void register(ProductVO pv)throws Exception;
	//카테고리 받아오기
	public List<CategoryVO> getCategory()throws Exception;
	//모든 물품 리스트 받아오기
	public List<ProductVO> getlistAll()throws Exception;
	//서치 정보가 들어간 리스트 받아오기
	public List<ProductVO> getlistSearch(SearchPaging sp)throws Exception;
	//서치된 모든 vo갯수 받아오기
	public Integer getSearchCount(SearchPaging sp)throws Exception;
	//판매 승인정보 업데이트
	public void approval(String pd_num, String pd_buy)throws Exception;
	//업데이트 정보 얻어오기
	public Map<String, Object> getModifyInfo(int pd_num)throws Exception;
	//업데이트
	public void update(ProductVO pv)throws Exception;
	//검색 후 사용된 카테고리 정보
	public Map<String, Object> getSearchCategory(SearchPaging sp)throws Exception;
	//상품과 상품관련 업로드 파일 삭제하기
	public void delete(int pd_num)throws Exception;
}
