package com.exp.question.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Question;
import com.exp.question.dao.QuestionDaoImpl;
import com.framework.Page;

/**
 * @zhangzhaolin 问题查询
 *
 */
@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl {

	@Resource
	private QuestionDaoImpl questiondaoimpl;

	/**
	 * @author Ray_1 功能 按时间将用户的所有问题查询出来
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            一页几条
	 * @param params
	 *            hql参数
	 * @return 用户的问题列表，放到page对象中
	 */
	@Transactional(readOnly = true)
	public Page<Question> findQuestionByTime(int pageNum, int pageSize, Object[] params) {
		return this.questiondaoimpl.findQuestionByTime(pageNum, pageSize, params);
	}

	public List<Question> findQuestionRecommend() {
		return questiondaoimpl.findQuestionRecommend();
	}

	/**
	 * @author zhang zhao lin
	 * @param currentPageNum
	 *            当前是第几页
	 * @param pageSize
	 *            每一页有多少条数据
	 * @return 简单的罗列出技术问答中的数据---最新发布
	 */
	public Page<Question> findQuestion_theNew(Integer currentPageNum, Integer pageSize) {
		return questiondaoimpl.findQuestion_theNew(currentPageNum, pageSize);
	}
}
