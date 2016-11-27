package com.exp.comment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.comment.dao.CommentDaoImpl;
import com.exp.entity.Comment;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl {
	
	@Resource
	private CommentDaoImpl commentDaoImpl;
	
	@Transactional(readOnly = false)
	public void saveComment(Comment comment){
		try {
			this.commentDaoImpl.save(comment);
			System.out.println(comment.getParentComment().getCommentId());
		} catch (Exception e) {
			// TODO Auto-generated catch block1
			System.out.println("comment save question!");
		}
	}
	
	public Comment getCommentById(Integer commentId){
		try {
			return this.commentDaoImpl.get(commentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return this.commentDaoImpl.findCommentById(commentId);
			
		}
	}

}
