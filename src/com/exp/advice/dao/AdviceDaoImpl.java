package com.exp.advice.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.Advice;
import com.framework.BaseDao;
@Repository
public class AdviceDaoImpl extends BaseDao<Advice, String>  {

	@Resource
	private SessionFactory sessionFactory;
	
	
}
