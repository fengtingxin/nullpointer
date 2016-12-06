package com.exp.comment.commentHateRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.CommentHateRecord;
import com.framework.BaseDao;

@Repository
public class CommentHateRecordDaoImpl extends BaseDao<CommentHateRecord, String>{
	/**
	 * @function 更新评论被踩记录
	 * @author tangwenru
	 * @param c
	 */
	public void updateCommentHateRecord(CommentHateRecord c){
		try {
			this.update(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 保存评论被踩记录
	 * @author tangwenru
	 * @param c
	 */
	public void saveCommentHateRecord(CommentHateRecord c){
		try {
			this.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据commentId,userInfoId查询单个评论被踩记录
	 * @author tangwenru
	 * @param commentId
	 * @param userInfoId
	 * @return
	 */
	public CommentHateRecord findCommentHaterecord(Integer commentId,Integer userInfoId){
		try {
			return super.findOne("from CommentHateRecord c where c.comment.commentId=? and c.userInfo.userInfoId=?",new Object[] { commentId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * @function 根据commentHateRecordId查询单个评论被踩记录
	 * @author tangwenru
	 * @param commentHateRecordId
	 * @return
	 */
	public CommentHateRecord getCommentHateRecord(int commentHateRecordId){
		try {
			CommentHateRecord c=this.get(commentHateRecordId);
			return c;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
