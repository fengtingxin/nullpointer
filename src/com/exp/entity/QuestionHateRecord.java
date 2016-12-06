package com.exp.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * question获踩记录表
 * @author tangwenru
 *
 */
@Entity
@Table(name="question_hate_record")
public class QuestionHateRecord {
	private Integer questionHateRecordId;
	private Integer questionHateStatus;
	private Date questionHateTime;
	private UserInfo userInfo;
	private Question question;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getQuestionHateRecordId() {
		return questionHateRecordId;
	}
	public void setQuestionHateRecordId(Integer questionHateRecordId) {
		this.questionHateRecordId = questionHateRecordId;
	}
	public Integer getQuestionHateStatus() {
		return questionHateStatus;
	}
	public void setQuestionHateStatus(Integer questionHateStatus) {
		this.questionHateStatus = questionHateStatus;
	}
	public Date getQuestionHateTime() {
		return questionHateTime;
	}
	public void setQuestionHateTime(Date questionHateTime) {
		this.questionHateTime = questionHateTime;
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
