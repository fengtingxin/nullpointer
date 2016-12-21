package com.exp.entity;
/**
 * @author tangwenru
 * 问题被赞记录表
 */
import java.util.Date;

import javax.persistence.CascadeType;
/**
 * @author tangwenru
 * question点赞记录表
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="question_like_record")
public class QuestionLikeRecord {
	private Integer questionLikeRecordId;
	private Integer questionLikeStatus;
	private Date questionLikeTime;
	private UserInfo userInfo;
	private Question question;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getQuestionLikeRecordId() {
		return questionLikeRecordId;
	}
	public void setQuestionLikeRecordId(Integer questionLikeRecordId) {
		this.questionLikeRecordId = questionLikeRecordId;
	}
	public Integer getQuestionLikeStatus() {
		return questionLikeStatus;
	}
	public void setQuestionLikeStatus(Integer questionLikeStatus) {
		this.questionLikeStatus = questionLikeStatus;
	}
	public Date getQuestionLikeTime() {
		return questionLikeTime;
	}
	public void setQuestionLikeTime(Date questionLikeTime) {
		this.questionLikeTime = questionLikeTime;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "userInfoId")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "questionId")
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	

}
