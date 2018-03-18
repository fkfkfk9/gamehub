package com.gamehub.cart.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gamehub.cart.CartDAO;
import com.gamehub.cart.CartService;
import com.gamehub.domain.CartVO;
import com.gamehub.util.UploadFileUtils;

@Service
public class CartServiceImpl implements CartService {

	@Inject
	private CartDAO dao;

	@Override
	public String cartAdd(CartVO cv) throws Exception {
		String result = "";
		result = dao.productCheck(cv.getPd_num());
		if(result == null){
			dao.cartAdd(cv);
			result = "ok";
		}
		return result;
	}

	@Override
	public void cartUpdate(CartVO cv) throws Exception {
		dao.amountUpdate(cv);		
	}

	@Override
	public List<CartVO> getCartList(String mem_id)throws Exception {
		List<CartVO> list = dao.getCartList(mem_id);
		for (CartVO cv : list) {
			cv.setPd_img(UploadFileUtils.addScalr(cv.getPd_img()));
		}
		return list;
	}

	@Override
	public void amountModify(CartVO cv) throws Exception {
		dao.amountModify(cv);		
	}

	@Override
	public void cartDelete(List<String> cclist) throws Exception {
		List<Integer> list = new ArrayList<>(cclist.size());
		for (String str : cclist) {
			list.add(Integer.parseInt(str));
		}
		dao.cartDelete(list);
	}
	
}
