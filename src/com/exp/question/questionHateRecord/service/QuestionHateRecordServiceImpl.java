package com.exp.question.questionHateRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.QuestionHateRecord;
import com.exp.question.questionHateRecord.dao.QuestionHateRecordDaoImpl;

@Service
@Transactional(readOnly=true)
public class QuestionHateRecordServiceImpl {
	@Resource
	private QuestionHateRecordDaoImpl questionHateRecordDaoImpl;
	/**
	 * @function 更新问题被踩记录
	 * @author tangwenru
	 * @param q
	 */
	@Transactional(readOnly=false)
	public void updateQuestionHateRecord(QuestionHateRecord q){
		this.questionHateRecordDaoImpl.updateHateQuestioneRecord(q);
		
	}
	/**
	 * @function 保持问题被踩记录 
	 * @author tangwenru
	 * @param q
	 */
	@Transactional(readOnly=false)
	public void saveQuestionHateRecord(QuestionHateRecord q){
		this.questionHateRecordDaoImpl.saveQuestionHateRecord(q);
	}
	/**
	 * @function 根据questionId,userInfoId查询单个问题被踩记录
	 * @author tangwenru
	 * @param questionId
	 * @param userInfoId
	 * @return
	 */
	public QuestionHateRecord findQuestionHateRecord(Integer questionId,Integer userInfoId){
		return this.questionHateRecordDaoImpl.findQuestionHateRecord(questionId, userInfoId);
		
	}
	

}
