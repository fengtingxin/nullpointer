package com.exp.comment.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.Comment;
import com.framework.BaseDao;
import com.framework.Page;

@Repository
public class CommentDaoImpl extends BaseDao<Comment, String> {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * @author Ray_1 功能 按时间将用户的所有评论查询出来
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            一页几条
	 * @param params
	 *            hql参数
	 * @return 用户的问题列表，放到page对象中
	 */
	// 按页，搜索条件查询
	public Page<Comment> findCommentByTime(int pageNum, int pageSize, Object[] params) {
		String hql;

		hql = "from Comment c where c.userInfo.userInfoId=? order by c.commentPublishTime";
		params[0] = params[0];
		try {
			Page<Comment> page = new Page<Comment>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page = this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Comment findCommentById(Integer commentId) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Comment where commentId=" + commentId);
		return (Comment) query.uniqueResult();
	}

	/**
	 * @function 删除评论
	 * @author tangwenru
	 * @param commentId
	 *            评论的id
	 */
	public void deleteComment(int commentId) {
		try {
			Query query = this.sessionFactory.getCurrentSession()
					.createQuery("delete from Comment c where c.commentId=" + commentId);
			int ret = query.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @fucntion 根据commentId查询单个评论
	 * @author tangwenru
	 * @param commentId
	 * @return
	 */
	public Comment getComment(Integer commentId){
		try {
			return this.get(commentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
