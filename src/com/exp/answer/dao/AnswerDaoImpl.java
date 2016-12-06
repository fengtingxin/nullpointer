package com.exp.answer.dao;


import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.Answer;
import com.framework.BaseDao;
import com.framework.Page;
//汤文茹删除了不必要引入的包
@Repository
public class AnswerDaoImpl extends BaseDao<Answer, Integer> {
	@Resource
	private SessionFactory sessionFactory;

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
	
	/**
	 * @function 删除回答
	 * @author tangwenru
	 * @param answerId 评论的id
	 */
	public void deleteAnswer(int answerId) {
		try {
			Query query=this.sessionFactory.getCurrentSession().createQuery("delete from Answer a where a.answerId="+answerId);
			int ret=query.executeUpdate(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据answerId查询单个Answer
	 * @author tangwenru
	 * @param answerId
	 * @return
	 */
	public Answer getAnser(int answerId){
		try {
			Answer answer=this.get(answerId);
			return answer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @function 更新回答
	 * @author tangwenru
	 * @param answer
	 */
	public void updateAnswer(Answer answer){
		try {
			this.update(answer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据userInfoId,questionId查询回答
	 * @author tangwenru
	 * @param userInfoId
	 * @param questionId
	 * @return
	 */
	public List<Answer> findAnswer(Integer userInfoId,Integer questionId) {
		Session session = super.getSession();
		Query query = session.createQuery("from Answer a where a.userInfo.userInfoId=? and a.question.questionId=?");
		query.setInteger(0, userInfoId);
		query.setInteger(1, questionId);
		return query.list();
	}
	
}
