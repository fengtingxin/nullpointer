package com.exp.comment.commentHateRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.comment.commentHateRecord.dao.CommentHateRecordDaoImpl;
import com.exp.entity.CommentHateRecord;

@Service
@Transactional(readOnly=true)
public class CommentHateRecordServiceImpl {
	@Resource
	private CommentHateRecordDaoImpl commentHateRecordDaoImpl;
	/**
	 * @function 更新评论被踩记录
	 * @author tangwenru
	 * @param c
	 */
	@Transactional(readOnly=false)
	public void updateCommentHateRecord(CommentHateRecord c){
		this.commentHateRecordDaoImpl.updateCommentHateRecord(c);
		
	}
	/**
	 * @function 保存评论被踩记录
	 * @author tangwenru
	 * @param c
	 */
	@Transactional(readOnly=false)
	public void saveCommentHateRecord(CommentHateRecord c){
		this.commentHateRecordDaoImpl.saveCommentHateRecord(c);
	}
	/**
	 * @function 根据commentId,userInfoId查询单个评论被踩记录
	 * @author tangwenru
	 * @param commentId
	 * @param userInfoId
	 * @return
	 */
	public CommentHateRecord findCommentHateRecord(Integer commentId,Integer userInfoId){
		return this.commentHateRecordDaoImpl.findCommentHaterecord(commentId, userInfoId);
	}
	/**
	 * @function 根据commentHateRecordId查询单个评论被踩记录
	 * @author tangwenru
	 * @param commentHateRecordId
	 * @return
	 */
	public CommentHateRecord getCommentHateRecord(Integer commentHateRecordId){
		return this.getCommentHateRecord(commentHateRecordId);
	}

}
