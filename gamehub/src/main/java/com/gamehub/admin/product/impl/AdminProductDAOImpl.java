package com.gamehub.admin.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gamehub.admin.product.AdminProductDAO;
import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;

@Repository
public class AdminProductDAOImpl implements AdminProductDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.gamehub.mapper.AdminProductMapper";

	@Override
	public void register(ProductVO pv) throws Exception {
		session.insert(namespace+".register", pv);		
	}

	@Override
	public List<CategoryVO> getCategory() throws Exception {
		return session.selectList(namespace+".getCategory");
	}

	@Override
	public void addAttach(AttachVO avo) throws Exception {
		session.insert(namespace+".addAttach", avo);		
	}

	@Override
	public List<ProductVO> getlistAll() throws Exception {
		return session.selectList(namespace+".getlistAll");
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
	public void approval(int pd_num, String pd_buy) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("pd_num", new Integer(pd_num));
		paramMap.put("pd_buy", pd_buy);
		session.update(namespace+".approval", paramMap);		
	}

	@Override
	public ProductVO getupdateInfo(int pd_num) throws Exception {
		return session.selectOne(namespace+".updateInfo", pd_num);
	}

	@Override
	public List<AttachVO> getAttachInfo(int pd_num) throws Exception {
		return session.selectList(namespace+".getAttach", pd_num);
	}

	@Override
	public void productUpdate(ProductVO pv) throws Exception {
		session.update(namespace+".productUpdate",pv);
	}

	@Override
	public void productDelete(int pd_num) throws Exception {
		session.delete(namespace+".productDelete",pd_num);
	}

	@Override
	public void attachDelete(int pd_num) throws Exception {
		session.delete(namespace+".attachDelete",pd_num);
	}

	@Override
	public void attachUpdate(AttachVO avo) throws Exception {
		session.insert(namespace+".attachUpdate",avo);
	}

	@Override
	public ProductVO getSearchCategory(String cateCode) throws Exception {
		return session.selectOne(namespace+".getSearchCategory", cateCode);
	}

	
}