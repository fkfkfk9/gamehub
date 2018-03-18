package com.gamehub.product.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.product.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.gamehub.mapper.ProductMapper";
	
	@Override
	public List<CategoryVO> getCategory() throws Exception {
		return session.selectList(namespace+".getCategory");
	}

	@Override
	public List<ProductVO> getlistSearch(SearchPaging sp) throws Exception {
		return session.selectList(namespace+".searchListPage", sp);
	}

	@Override
	public Integer getSearchCount(SearchPaging sp) throws Exception {
		return session.selectOne(namespace+".searchCount", sp);
	}

	@Override
	public ProductVO getProductInfo(int pd_num) throws Exception {
		return session.selectOne(namespace+".getProductInfo", pd_num);
	}

	@Override
	public List<AttachVO> getAttachInfo(int pd_num) throws Exception {
		return session.selectList(namespace+".getAttach", pd_num);
	}

	@Override
	public ProductVO getSearchCategory(String cateCode) throws Exception {
		return session.selectOne(namespace+".getSearchCategory", cateCode);
	}

	@Override
	public List<CategoryVO> childCategory(String cateCode) throws Exception {
		return session.selectList(namespace+".childCategory", cateCode);
	}	
}