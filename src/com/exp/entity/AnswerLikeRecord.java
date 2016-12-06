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
 * 回答点赞记录表
 * @author tangwenru
 *
 */
@Entity
@Table(name="answer_like_record")
public class AnswerLikeRecord {
	private Integer answerLikeRecordId;
	private Integer answerLikeStatus;
	private Date answerLikeTime;
	private UserInfo userInfo;
	private Answer answer;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAnswerLikeRecordId() {
		return answerLikeRecordId;
	}
	public void setAnswerLikeRecordId(Integer answerLikeRecordId) {
		this.answerLikeRecordId = answerLikeRecordId;
	}
	public Integer getAnswerLikeStatus() {
		return answerLikeStatus;
	}
	public void setAnswerLikeStatus(Integer answerLikeStatus) {
		this.answerLikeStatus = answerLikeStatus;
	}
	public Date getAnswerLikeTime() {
		return answerLikeTime;
	}
	public void setAnswerLikeTime(Date answerLikeTime) {
		this.answerLikeTime = answerLikeTime;
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
