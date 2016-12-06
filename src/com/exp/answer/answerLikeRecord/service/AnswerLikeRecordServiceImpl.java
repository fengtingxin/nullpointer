package com.exp.answer.answerLikeRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.answer.answerLikeRecord.dao.AnswerLikeRecordDaoImpl;
import com.exp.entity.AnswerLikeRecord;

@Service
@Transactional(readOnly=true)
public class AnswerLikeRecordServiceImpl {
	@Resource
	private AnswerLikeRecordDaoImpl answerLikeRecordDaoImpl;
	/**
	 * @function 更新回答被赞记录
	 * @author tangwenru
	 * @param a
	 */
	@Transactional(readOnly=false)
	public void updateAnswerLikeRecord(AnswerLikeRecord a){
		this.answerLikeRecordDaoImpl.updateAnswerLikeRecord(a);
	}
	/**
	 * @function 保存回答被赞记录
	 * @author tangwenru
	 * @param a
	 */
	@Transactional(readOnly=false)
	public void saveAnswerLikeRecord(AnswerLikeRecord a){
		this.answerLikeRecordDaoImpl.saveAnswerLikeRecord(a);
	}
	/**
	 * @function 根据answerId,userInfoId查询单个问题被赞记录
	 * @author tangwenru
	 * @param answerId
	 * @param userInfoId
	 * @return
	 */
	public AnswerLikeRecord findAnswerLikeRecord(Integer answerId,Integer userInfoId){
		return this.answerLikeRecordDaoImpl.findAnswerLikerecord(answerId, userInfoId);
	}
	/**
	 * @function 根据answerLikeRecord查询单个问题被赞记录
	 * @author tangwenru
	 * @param answerLikeRecordId
	 * @return
	 */
	public AnswerLikeRecord getAnswerLikeRecord(Integer answerLikeRecordId){
		return this.answerLikeRecordDaoImpl.getAnswerLikeRecord(answerLikeRecordId);
	}

}
