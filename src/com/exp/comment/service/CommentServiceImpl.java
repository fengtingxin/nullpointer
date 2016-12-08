package com.exp.comment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.comment.dao.CommentDaoImpl;
import com.exp.entity.Comment;
import com.framework.Page;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl {

	@Resource
	private CommentDaoImpl commentDaoImpl;

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
	@Transactional(readOnly = true)
	public Page<Comment> findCommentByTime(int pageNum, int pageSize, Object[] params) {
		return this.commentDaoImpl.findCommentByTime(pageNum, pageSize, params);
	}

	/**
	 * @fuction 保存评论
	 * @author fengtingxin
	 * @param comment
	 *            Comment对象
	 */
	@Transactional(readOnly = false)
	public void saveComment(Comment comment) {
		try {
			this.commentDaoImpl.save(comment);
			System.out.println(comment.getParentComment().getCommentId());
		} catch (Exception e) {
			// TODO Auto-generated catch block1
			System.out.println("comment save question!");
		}
	}

	public Comment getCommentById(Integer commentId) {
		try {
			return this.commentDaoImpl.get(commentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return this.commentDaoImpl.findCommentById(commentId);

		}
	}

	/**
	 * @function 根据评论的id删除评论
	 * @author tangwenru
	 * @param commentId
	 */
	@Transactional(readOnly = false)
	public void deleteComment(int commentId) {
		this.commentDaoImpl.deleteComment(commentId);
	}
	/**
	 * @function 更新评论
	 * @author tangwenru
	 * @param comment
	 */
	@Transactional(readOnly=false)
	public void updateComment(Comment comment){
		try {
			this.commentDaoImpl.update(comment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				this.commentDaoImpl.update(comment);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("update comment error ");
				e1.printStackTrace();
			}
		}
	}
	/**
	 * @function 根据commentId查询单个评论
	 * @author tangwenru
	 * @param commentId
	 * @return
	 */
	public Comment getComment(Integer commentId){
		return this.commentDaoImpl.getComment(commentId);
	}

}
