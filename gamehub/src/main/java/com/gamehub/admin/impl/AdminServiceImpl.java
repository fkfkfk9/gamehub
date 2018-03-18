package com.gamehub.admin.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gamehub.admin.AdminDAO;
import com.gamehub.admin.AdminService;
import com.gamehub.domain.CategoryVO;
import com.gamehub.product.ProductDAO;
import com.gamehub.product.ProductService;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	private AdminDAO dao;
	
	
}
