package com.exp.hibernateSearch.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.entity.Question;
import com.exp.hibernateSearch.dao.HibernateSearchDaoImpl;

@Service
@Transactional(readOnly = true)
public class HibernateSearchServiceImpl {

	@Resource
	private HibernateSearchDaoImpl hibernateSearchDaoImpl;

	/**
	 * @author Ray_1
	 * @功能：hibernateSearch查询不分页的bug
	 */
	public List<Bug> searchBug(String search) {
		return hibernateSearchDaoImpl.searchBug(search);
	}

	/**
	 * @author Ray_1 @功能： 查询question
	 * @param search
	 * @return
	 */
	public List<Question> searchQuestion(String search) {
		return hibernateSearchDaoImpl.searchQuestion(search);
	}

}
