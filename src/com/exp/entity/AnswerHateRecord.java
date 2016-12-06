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
 * answer获踩记录表
 * @author tangwenru
 *
 */
@Entity
@Table(name="answer_hate_record")
public class AnswerHateRecord {
	private Integer answerHateRecordId;
	private Integer answerHateStatus;
	private Date answerHateTime;
	private UserInfo userInfo;
	private Answer answer;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAnswerHateRecordId() {
		return answerHateRecordId;
	}
	public void setAnswerHateRecordId(Integer answerHateRecordId) {
		this.answerHateRecordId = answerHateRecordId;
	}
	public Integer getAnswerHateStatus() {
		return answerHateStatus;
	}
	public void setAnswerHateStatus(Integer answerHateStatus) {
		this.answerHateStatus = answerHateStatus;
	}
	public Date getAnswerHateTime() {
		return answerHateTime;
	}
	public void setAnswerHateTime(Date answerHateTime) {
		this.answerHateTime = answerHateTime;
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
	@JoinColumn(name = "answerId")
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	

}
