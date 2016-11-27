package com.exp.comment.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.Comment;
import com.framework.BaseDao;
@Repository
public class CommentDaoImpl extends BaseDao<Comment, String> {

	@Resource
	private SessionFactory sessionFactory;
	
	public Comment findCommentById(Integer commentId){
		Query query=this.sessionFactory.getCurrentSession().createQuery("from Comment where commentId="+commentId);
		return (Comment) query.uniqueResult();
	}
}
