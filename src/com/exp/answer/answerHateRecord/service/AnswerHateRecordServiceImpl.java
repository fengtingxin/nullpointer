package com.exp.answer.answerHateRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.answer.answerHateRecord.dao.AnswerHateRecordDaoImpl;
import com.exp.entity.AnswerHateRecord;

@Service
@Transactional(readOnly=true)
public class AnswerHateRecordServiceImpl {
	@Resource
	private AnswerHateRecordDaoImpl answerHateRecordDaoImpl;
	/**
	 * @function 更新回答被踩记录
	 * @author tangwenru
	 * @param a
	 */
	@Transactional(readOnly=false)
	public void updateAnswerHateRecord(AnswerHateRecord a){
		this.answerHateRecordDaoImpl.updateAnswerHateRecord(a);
	}
	/**
	 * @function 保存回答被踩记录
	 * @author tangwenru
	 * @param a
	 */
	@Transactional(readOnly=false)
	public void saveAnswerHateRecord(AnswerHateRecord a){
		this.answerHateRecordDaoImpl.saveAnswerHateRecord(a);
	}
	/**
	 * @function 根据answerId,userInfoId查询单个回答被踩记录
	 * @author tangwenru
	 * @param answerId
	 * @param userInfoId
	 * @return
	 */
	public AnswerHateRecord findAnswerHateRecord(Integer answerId,Integer userInfoId){
		return this.answerHateRecordDaoImpl.findAnswerHaterecord(answerId, userInfoId);
	}
	/**
	 * @function 根据answerHateRecordId查询单个回答被踩记录
	 * @author tangwenru
	 * @param answerHateRecordId
	 * @return
	 */
	public AnswerHateRecord getAnswerHateRecord(Integer answerHateRecordId){
		return this.answerHateRecordDaoImpl.getAnswerHateRecord(answerHateRecordId);
	}

}
