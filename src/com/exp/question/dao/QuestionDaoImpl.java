package com.exp.question.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.exp.entity.Question;
import com.framework.BaseDao;
import com.framework.Page;

@Repository
public class QuestionDaoImpl extends BaseDao<Question, Integer> {

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
	// 按页，搜索条件查询
	public Page<Question> findQuestionByTime(int pageNum, int pageSize, Object[] params) {
		String hql;

		hql = "from Question where questionAuthorId=? order by questionPublishTime";
		params[0] = params[0];
		try {
			Page<Question> page = new Page<Question>();
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
	 * @author zhang zhao lin
	 * @return 查询首页中的问答推荐信息 按照点赞人数和时间排
	 */
	public List<Question> findQuestionRecommend() {
		Session session = super.getSession();
		Query query = session.createQuery("from Question order by questionLikeNum DESC,questionPublishTime DESC");
		return query.list();
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
		String hql = "from Question order by questionPublishTime DESC";

		Page<Question> page = new Page<Question>();
		try {
			page = super.findByPage(currentPageNum, pageSize, hql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
		// return "";
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
		String hql = "from Question order by questionAnswerCount DESC,questionPublishTime DESC";
		Page<Question> page = new Page<Question>();
		try {
			page = super.findByPage(currentPageNum, pageSize, hql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @author zhang zhao lin
	 * @param currentPageNum
	 *            当前页
	 * @param pageSize
	 * @return 查询尚未解决的问题
	 */
	public Page<Question> findQuestion_noOne(Integer currentPageNum, Integer pageSize) {
		String sql = "from Question where questionAnswerCount = 0";
		Page<Question> page = new Page<Question>();
		try {
			page = super.findByPage(currentPageNum, pageSize, sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * @function 根据question的id查询单个Question
	 * @author tangwenru
	 * @param questionId
	 * @return
	 */
	public Question getQuestion(int questionId) {
		try {
			Question question = this.get(questionId);
			return question;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 测试搜索框下面内容
	 * 
	 * @author Ray_1
	 */
	// public TestSearch(){
	//
	// }
	/**
	 * @function 更新问题
	 * @author tangwenru
	 * @param q
	 */
	public void updateQuestion(Question q) {
		try {
			this.update(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public void saveQuestion(Question q) {
		try {
			this.save(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
