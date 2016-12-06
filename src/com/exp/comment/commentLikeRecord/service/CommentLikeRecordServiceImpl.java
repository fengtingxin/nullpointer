package com.exp.comment.commentLikeRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.comment.commentLikeRecord.dao.CommentLikeRecordDaoImpl;
import com.exp.entity.CommentLikeRecord;

@Service
@Transactional(readOnly=true)
public class CommentLikeRecordServiceImpl {
	@Resource
	private CommentLikeRecordDaoImpl commentLikeRecordImpl;
	/**
	 * @function 更新评论被赞记录
	 * @author tangwenru
	 * @param c
	 */
	@Transactional(readOnly=false)
	public void updateCommentLikeRecord(CommentLikeRecord c){
		this.commentLikeRecordImpl.updateCommentLikeRecord(c);
	}
	/**
	 * @function 保存评论被赞记录
	 * @author tangwenru
	 * @param c
	 */
	@Transactional(readOnly=false)
	public void saveCommentLikeRecord(CommentLikeRecord c){
		this.commentLikeRecordImpl.saveCommentLikeRecord(c);
	}
	/**
	 * @function 根据commenId,userInfoId查询单个评论被赞记录
	 * @author tangwenru
	 * @param commentId
	 * @param userInfoId
	 * @return
	 */
	public CommentLikeRecord findCommentLikeRecord(Integer commentId,Integer userInfoId){
		return this.commentLikeRecordImpl.findCommentLikerecord(commentId, userInfoId);
	}
	/**
	 * @function 根据commentLikeRecordId查询单个评论被赞记录
	 * @author tangwenru
	 * @param commentLikeRecordId
	 * @return
	 */
	public CommentLikeRecord getCommentLikeRecord(Integer commentLikeRecordId){
		return this.commentLikeRecordImpl.getCommentLikeRecord(commentLikeRecordId);
	}
	

}
