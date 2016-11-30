package com.exp.text.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.text.dao.TextDaoImpl;
import com.framework.Page;
@Service
@Transactional(readOnly = true)
public class TextServiceImpl {
	@Resource
	private TextDaoImpl textDaoImpl;
	
	@Transactional(readOnly = true)
	public Page<Bug> listTest(int pageNum, int pageSize, Object[] params) {
		return this.textDaoImpl.findText(pageNum, pageSize, params);
	}
}
