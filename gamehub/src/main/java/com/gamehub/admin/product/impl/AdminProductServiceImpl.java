package com.gamehub.admin.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamehub.admin.product.AdminProductDAO;
import com.gamehub.admin.product.AdminProductService;
import com.gamehub.domain.AttachVO;
import com.gamehub.domain.CategoryVO;
import com.gamehub.domain.ProductVO;
import com.gamehub.domain.SearchPaging;
import com.gamehub.product.ProductDAO;
import com.gamehub.util.UploadFileUtils;

@Service
public class AdminProductServiceImpl implements AdminProductService {

	@Inject
	private AdminProductDAO dao;
	
	@Transactional
	@Override
	public void register(ProductVO pv) throws Exception {
		dao.register(pv);
		if(pv.getFiles() != null) {
			for (String file : pv.getFiles()) {
				AttachVO avo = new AttachVO();
				avo.setA_filename(file.substring(file.lastIndexOf("_")+1));
				avo.setA_format(file.substring(file.lastIndexOf(".")+1));
				avo.setA_scalname(UploadFileUtils.addScalr(file));
				avo.setA_fullname(file);
				dao.addAttach(avo);
			}
		}		
	}

	@Override
	public List<CategoryVO> getCategory() throws Exception {
		return dao.getCategory();
	}

	@Override
	public List<ProductVO> getlistAll() throws Exception {
		return dao.getlistAll();
	}

	@Override
	public List<ProductVO> getlistSearch(SearchPaging sp) throws Exception {
		return dao.getlistSearch(sp);
	}

	@Override
	public Integer getSearchCount(SearchPaging sp) throws Exception {
		return dao.getSearchCount(sp);
	}

	@Transactional
	@Override
	public void approval(String pd_num, String pd_buy) throws Exception {
		String[] pd_numarr = pd_num.split(",");
		for (int i = 0; i < pd_numarr.length; i++) {
			dao.approval(Integer.parseInt(pd_numarr[i]), pd_buy);
		}		
	}

	@Override
	public Map<String, Object> getModifyInfo(int pd_num) throws Exception {
		Map<String, Object> modifyMap = new HashMap<>();
		ProductVO pv = dao.getupdateInfo(pd_num);
		pv.setPd_img(UploadFileUtils.addScalr(pv.getPd_img()));
		modifyMap.put("pv", pv);
		modifyMap.put("avo", dao.getAttachInfo(pd_num));
		return modifyMap;
	}

	@Transactional
	@Override
	public void update(ProductVO pv) throws Exception {
		dao.productUpdate(pv);
		dao.attachDelete(pv.getPd_num());
		if(pv.getFiles() != null) {
			for (String file : pv.getFiles()) {
				AttachVO avo = new AttachVO();
				avo.setA_filename(file.substring(file.lastIndexOf("_")+1));
				avo.setA_format(file.substring(file.lastIndexOf(".")+1));
				avo.setA_scalname(UploadFileUtils.addScalr(file));
				avo.setA_fullname(file);
				avo.setPd_num(pv.getPd_num());
				dao.attachUpdate(avo);
			}
		}		
	}

	@Override
	public Map<String, Object> getSearchCategory(SearchPaging sp) throws Exception {
		Map<String, Object> cateMap = new HashMap<>();
		ProductVO pv = dao.getSearchCategory(sp.getCateCode());
		if(pv == null) {
			cateMap.put("cg_code", "0");
			cateMap.put("cg_parent", "0");
			cateMap.put("cg_ancestor", "0");
		}else {
			if(pv.getCg_ancestor() == null) pv.setCg_ancestor("0");
			if(pv.getCg_parent() == null) pv.setCg_parent("0");
			if(pv.getCg_code() == null) pv.setCg_code("0");
			cateMap.put("cg_code", pv.getCg_code());
			cateMap.put("cg_parent", pv.getCg_parent());
			cateMap.put("cg_ancestor", pv.getCg_ancestor());
		}
		return cateMap;
	}

	@Transactional
	@Override
	public void delete(int pd_num) throws Exception {
		dao.attachDelete(pd_num);
		dao.productDelete(pd_num);
	}

	
}
