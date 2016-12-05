package com.exp.advice.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.Advice;
import com.framework.BaseDao;
@Repository
public class AdviceDaoImpl extends BaseDao<Advice, String>  {

	@Resource
	private SessionFactory sessionFactory;
	
	public List<Advice> fingAllAdvice(){
		Query query =this.sessionFactory.getCurrentSession().createQuery("from Advice");
		return query.list();
	}
}
