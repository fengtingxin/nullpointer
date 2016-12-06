package com.exp.question.questionLikeRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.QuestionHateRecord;
import com.exp.entity.QuestionLikeRecord;
import com.framework.BaseDao;

@Repository
public class QuestionLikeRecordDaoImpl extends BaseDao<QuestionLikeRecord, String>{
	/**
	 * @function 保存question被赞记录
	 * @author tangwenru
	 * @param q
	 */
	public void saveQuestionLikeRecord(QuestionLikeRecord q){
		try {
			this.save(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据questionId,userInfoId查询单个question被赞记录
	 * @author tangwenru
	 * @param questionId
	 * @param userInfoId
	 * @return
	 */
	public QuestionLikeRecord findQuestionLikeRecord(Integer questionId,Integer userInfoId){
		try {
			return super.findOne("from QuestionLikeRecord q where q.question.questionId=? and q.userInfo.userInfoId=?",new Object[] { questionId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
	/**
	 * @function 更新question被赞记录
	 * @author tangwenru
	 * @param q
	 */
	public void updateLikeQuestioneRecord(QuestionLikeRecord q){
		try {
			this.update(q);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
