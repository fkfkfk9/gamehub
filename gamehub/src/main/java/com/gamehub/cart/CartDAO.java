package com.gamehub.cart;

import java.util.List;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CartVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;


public interface CartDAO {
	//장바구니 추가
	public void cartAdd(CartVO cv)throws Exception;
	//장바구니에 해당 물건이 있는지 확인
	public String productCheck(int pd_num)throws Exception;
	//장바구니 추가시 수량 변경시 업데이트
	public void amountUpdate(CartVO cv)throws Exception;
	//장바구니에서 수량 변경시 업데이트
	public void amountModify(CartVO cv)throws Exception;
	//장바구니 삭제
	public void cartDelete(List<Integer> list)throws Exception;
	//장바구니 목록
	public List<CartVO> getCartList(String mem_id)throws Exception;
}
