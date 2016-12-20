package com.exp.hibernateSearch.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.entity.Question;
import com.exp.hibernateSearch.dao.HibernateSearchDaoImpl;
import com.framework.Page;

@Service
@Transactional(readOnly = true)
public class HibernateSearchServiceImpl {

	@Resource
	private HibernateSearchDaoImpl hibernateSearchDaoImpl;

	/**
	 * @author Ray_1
	 * @功能：用hibernateSearch 获取4条bug，并用高亮显示。
	 * @param search
	 * @return
	 */
	public List<Object> searchBug(String search) {
		return hibernateSearchDaoImpl.searchBug(search);
	}

	/**
	 * @author Ray_1
	 * @功能：用hibernateSearch查询4条 question，并用高亮显示。
	 * @param search
	 * @return
	 */
	public List<Object> searchQuestion(String search) {
		return hibernateSearchDaoImpl.searchQuestion(search);
	}

	/**
	 * @author Ray_1
	 * @desc hibernate Search 分页查询bug并实现高亮
	 * @param pageNum
	 * @param pageSize
	 * @param search
	 * @return
	 */
	public Page<Bug> findBugByPage(int pageNum, int pageSize, String search) {
		try {
			return hibernateSearchDaoImpl.findBugByPage(pageNum, pageSize, search);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @author Ray_1
	 * @desc hibernate Search 分页查询question并实现高亮
	 * @param pageNum
	 * @param pageSize
	 * @param search
	 * @return
	 */
	public Page<Question> findQuestionByPage(int pageNum, int pageSize, String search) {
		try {
			return hibernateSearchDaoImpl.findQuestionByPage(pageNum, pageSize, search);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
