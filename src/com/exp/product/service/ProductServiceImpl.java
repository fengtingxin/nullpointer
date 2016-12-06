package com.exp.product.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.product.dao.ProductDaoImpl;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl {
	@Resource
	private ProductDaoImpl productDaoImpl;

	public List<Bug> search(String search) {
		return productDaoImpl.search(search);
	}
}
