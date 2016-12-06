package com.exp.answer.answerLikeRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.AnswerLikeRecord;
import com.framework.BaseDao;
@Repository
public class AnswerLikeRecordDaoImpl  extends BaseDao<AnswerLikeRecord, String>{

	/**
	 * @function 更新回答被赞记录
	 * @author tangwenru
	 * @param a
	 */
	public void updateAnswerLikeRecord(AnswerLikeRecord a){
		try {
			this.update(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 保存回答被赞记录
	 * @author tangwenru
	 * @param a
	 */
	public void saveAnswerLikeRecord(AnswerLikeRecord a){
		try {
			this.save(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据answerId,userInfoId查询单个回答被赞记录
	 * @author tangwenru
	 * @param answerId
	 * @param userInfoId
	 * @return
	 */
	public AnswerLikeRecord findAnswerLikerecord(Integer answerId,Integer userInfoId){
		try {
			return super.findOne("from AnswerLikeRecord a where a.answer.answerId=? and a.userInfo.userInfoId=?",new Object[] { answerId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * @function 根据AnswerLikeRecordId查询单个回答被赞记录
	 * @author tangwenru
	 * @param answerLikeRecordId
	 * @return
	 */
	public AnswerLikeRecord getAnswerLikeRecord(int answerLikeRecordId){
		try {
			AnswerLikeRecord a=this.get(answerLikeRecordId);
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
