package com.exp.question.questionLikeRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.QuestionLikeRecord;
import com.exp.question.questionLikeRecord.dao.QuestionLikeRecordDaoImpl;

@Service
@Transactional(readOnly=true)
public class QuestionLikeRecordServiceImpl {

	@Resource
	private  QuestionLikeRecordDaoImpl  questionLikeRecordDaoImpl;
	/**
	 * @function 更新问题被赞记录
	 * @author tangwenru
	 * @param q
	 */
	@Transactional(readOnly=false)
	public void updateQuestionLikeRecord(QuestionLikeRecord q){
		this.questionLikeRecordDaoImpl.updateLikeQuestioneRecord(q);
	}
	/**
	 * @function 保存问题被赞记录
	 * @author tangwenru
	 * @param q
	 */
	@Transactional(readOnly=false)
	public void saveQuestionLikeRecord(QuestionLikeRecord q){
		this.questionLikeRecordDaoImpl.saveQuestionLikeRecord(q);
		
	}
	/**
	 * @function 查询单个问题被赞记录
	 * @author tangwenru
	 * @param questionId
	 * @param userInfoId
	 * @return
	 */
	public QuestionLikeRecord findQuestionLikeRecord(Integer questionId,Integer userInfoId){
		return this.questionLikeRecordDaoImpl.findQuestionLikeRecord(questionId, userInfoId);
	}
	}
