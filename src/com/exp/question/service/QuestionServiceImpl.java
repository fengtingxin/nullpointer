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
	private QuestionDaoImpl questionDaoImpl;// 将questiondaoimpl改成questionDaoImpl

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
		return this.questionDaoImpl.findQuestionByTime(pageNum, pageSize, params);
	}

	public List<Question> findQuestionRecommend() {
		return questionDaoImpl.findQuestionRecommend();
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
		return questionDaoImpl.findQuestion_theNew(currentPageNum, pageSize);
	}

	/**
	 * @author zhang zhao lin
	 * @param currentPageNum
	 *            当前页数 默认为1
	 * @param pageSize
	 *            每一页有多少条数据
	 * @return 查询问题页中数据 按照最多人回答排序
	 */
	public Page<Question> findQuestion_theMostAnswerCount(Integer currentPageNum, Integer pageSize) {
		return questionDaoImpl.findQuestion_theMostAnswerCount(currentPageNum, pageSize);
	}

	public Page<Question> findQuestion_noOne(Integer currentPageNum, Integer pageSize) {
		return questionDaoImpl.findQuestion_noOne(currentPageNum, pageSize);
	}

	/**
	 * @function 通过qustion的id获取单个Question
	 * @author tangwenru
	 * @param questionId
	 * @return
	 */
	public Question getQuestion(Integer questionId) {
		return this.questionDaoImpl.getQuestion(questionId);

	}

	/**
	 * @function 更新问题
	 * @author tangwenru
	 * @param q
	 */
	@Transactional(readOnly = false)
	public void updateQuestion(Question q) {
		this.questionDaoImpl.updateQuestion(q);

	}

	/**
	 * 
	 * 
	 */
	@Transactional(readOnly = false)
	public void saveQuestion(Question q) {
		this.questionDaoImpl.saveQuestion(q);
	}
}
