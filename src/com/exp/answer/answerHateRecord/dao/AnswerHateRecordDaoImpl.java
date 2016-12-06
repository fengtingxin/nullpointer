package com.exp.answer.answerHateRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.AnswerHateRecord;
import com.framework.BaseDao;

@Repository
public class AnswerHateRecordDaoImpl extends BaseDao<AnswerHateRecord,String>{

	/**
	 * @function 更新回答被踩记录
	 * @author tangwenru
	 * @param a
	 */
	public void updateAnswerHateRecord(AnswerHateRecord a){
		try {
			this.update(a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 保存回答被踩记录
	 * @author tangwenru
	 * @param a
	 */
	public void saveAnswerHateRecord(AnswerHateRecord a){
		try {
			this.save(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据answerId,userInfoId查询单个回答被踩记录
	 * @author tangwenru
	 * @param answerId
	 * @param userInfoId
	 * @return
	 */
	public AnswerHateRecord findAnswerHaterecord(Integer answerId,Integer userInfoId){
		try {
			return super.findOne("from AnswerHateRecord a where a.answer.answerId=? and a.userInfo.userInfoId=?",new Object[] { answerId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * @function 根据AnswerHateRecordId查询单个回答被踩记录
	 * @author tangwenru
	 * @param answerHateRecordId
	 * @return
	 */
	public AnswerHateRecord getAnswerHateRecord(int answerHateRecordId){
		try {
			AnswerHateRecord a=this.get(answerHateRecordId);
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
