package com.gamehub.cart.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gamehub.cart.CartDAO;
import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CartVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.product.ProductDAO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.gamehub.mapper.CartMapper";

	@Override
	public void cartAdd(CartVO cv) throws Exception {
		session.insert(namespace+".cartAdd", cv);		
	}

	@Override
	public String productCheck(int pd_num) throws Exception {
		return session.selectOne(namespace+".productCheck", pd_num);
	}

	@Override
	public void amountUpdate(CartVO cv) throws Exception {
		session.update(namespace+".amountUpdate", cv);
	}

	@Override
	public void cartDelete(List<Integer> list) throws Exception {
		session.delete(namespace+".cartDelete", list);
	}

	@Override
	public List<CartVO> getCartList(String mem_id) throws Exception {
		return session.selectList(namespace+".getCartList", mem_id);
	}

	@Override
	public void amountModify(CartVO cv) throws Exception {
		session.update(namespace+".amountModify", cv);
	}
	
}