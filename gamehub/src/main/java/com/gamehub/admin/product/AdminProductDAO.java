package com.gamehub.admin.product;

import java.util.List;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;


public interface AdminProductDAO {
	//상품추가
	public void register(ProductVO pv)throws Exception;
	//상품추가시 부가 파일 업로드
	public void addAttach(AttachVO avo)throws Exception;
	//카테고리 받아오기
	public List<CategoryVO> getCategory()throws Exception;
	//모든 물품 리스트 받아오기
	public List<ProductVO> getlistAll()throws Exception;
	//서치 정보가 들어간 리스트 받아오기
	public List<ProductVO> getlistSearch(SearchPaging sp)throws Exception;
	//서치된 모든 vo갯수 받아오기
	public Integer getSearchCount(SearchPaging sp)throws Exception;
	//판매 승인정보 업데이트
	public void approval(int pd_num, String pd_buy)throws Exception;
	//업데이트 정보 얻어오기
	public ProductVO getupdateInfo(int pd_num)throws Exception;
	//업데이트시 추가 이미지 정보 받아오기
	public List<AttachVO> getAttachInfo(int pd_num)throws Exception;
	//물품 업데이트
	public void productUpdate(ProductVO pv)throws Exception;
	//attach 업데이트용 삽입
	public void attachUpdate(AttachVO avo)throws Exception;
	//물품 삭제
	public void productDelete(int pd_num)throws Exception;
	//attach 삭제
	public void attachDelete(int pd_num)throws Exception;
	//검색 후 사용된 카테고리 정보
	public ProductVO getSearchCategory(String cateCode)throws Exception;
}
