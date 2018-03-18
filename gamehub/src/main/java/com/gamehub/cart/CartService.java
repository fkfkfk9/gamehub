package com.gamehub.cart;

import java.util.List;
import java.util.Map;

import com.gamehub.domain.CartVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;

public interface CartService {
	//장바구니 추가
	public String cartAdd(CartVO cv)throws Exception;
	//장바구니 추가시 수량 추가
	public void cartUpdate(CartVO cv)throws Exception;
	//장바구니에서 수량 변경시 업데이트
	public void amountModify(CartVO cv)throws Exception;
	//장바구니 리스트
	public List<CartVO> getCartList(String mem_id)throws Exception;
	//장바구니 삭제
	public void cartDelete(List<String> cclist)throws Exception;
}
