package com.exp.answer.dao;


import org.springframework.stereotype.Repository;

import com.exp.entity.Answer;
import com.framework.BaseDao;
import com.framework.Page;
//汤文茹删除了不必要引入的包
@Repository
public class AnswerDaoImpl extends BaseDao<Answer, Integer> {

	/**
	 * @author Ray_1 功能 按时间将用户的所有回答问题查询出来
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            一页几条
	 * @param params
	 *            hql参数
	 * @return 用户的问题列表，放到page对象中
	 */
	// 按页，搜索条件查询
	public Page<Answer> findAnswerByTime(int pageNum, int pageSize, Object[] params) {
		String hql;

		hql = "from Answer where answerAuthorId=? order by answerPublishTime";
		params[0] = params[0];
		try {
			Page<Answer> page = new Page<Answer>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page = this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
