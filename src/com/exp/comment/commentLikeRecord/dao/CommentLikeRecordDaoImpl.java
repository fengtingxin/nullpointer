package com.exp.comment.commentLikeRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.CommentLikeRecord;
import com.framework.BaseDao;

@Repository
public class CommentLikeRecordDaoImpl  extends BaseDao<CommentLikeRecord, String>{

	/**
	 * @function 更新评论被赞记录
	 * @author tangwenru
	 * @param c
	 */
	public void updateCommentLikeRecord(CommentLikeRecord c){
		try {
			this.update(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 保存评论被赞记录
	 * @author tangwenru
	 * @param c
	 */
	public void saveCommentLikeRecord(CommentLikeRecord c){
		try {
			this.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据commentId,userInfoId查询单个评论被赞记录
	 * @author tangwenru
	 * @param commentId
	 * @param userInfoId
	 * @return
	 */
	public CommentLikeRecord findCommentLikerecord(Integer commentId,Integer userInfoId){
		try {
			return super.findOne("from CommentLikeRecord c where c.comment.commentId=? and c.userInfo.userInfoId=?",new Object[] { commentId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * @function 根据commentLikeRecordId查询单个评论被赞记录
	 * @author tangwenru
	 * @param commentLikeRecordId
	 * @return
	 */
	public CommentLikeRecord getCommentLikeRecord(int commentLikeRecordId){
		try {
			CommentLikeRecord c=this.get(commentLikeRecordId);
			return c;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
