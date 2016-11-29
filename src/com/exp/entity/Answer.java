package com.exp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * @tangwenru 
 * 实体类之间的映射关系
 *
 */
@Entity
@Table(name = "answer")
public class Answer {

	//属性
	private Integer answerId;
	private String answerContent;//回答的内容
	private Date answerPublishTime;//回答的时间
	private Integer answerLikeNum;//回答的获赞数量
	private Integer answerHateNum;//回答的被踩数量
	private Question question;//回答对应的问题
	private UserInfo userInfo;//回答对应的用户
	private Answer parentAnswer;//回答的父回答
	private Set<Answer> answers = new HashSet<Answer>(0);
	
	//set/get方法
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getAnswerId() {
		return answerId;
	}
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}
	
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	
	public Date getAnswerPublishTime() {
		return answerPublishTime;
	}
	public void setAnswerPublishTime(Date answerPublishTime) {
		this.answerPublishTime = answerPublishTime;
	}
	
	public Integer getAnswerLikeNum() {
		return answerLikeNum;
	}
	public void setAnswerLikeNum(Integer answerLikeNum) {
		this.answerLikeNum = answerLikeNum;
	}
	
	public Integer getAnswerHateNum() {
		return answerHateNum;
	}
	public void setAnswerHateNum(Integer answerHateNum) {
		this.answerHateNum = answerHateNum;
	}
	/**
	 * 
	 * @return
	 */
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "questionID")
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "answerAuthorID")
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "answerParentId",referencedColumnName="answerId",updatable=true)
	public Answer getParentAnswer() {
		return parentAnswer;
	}
	public void setParentAnswer(Answer parentAnswer) {
		this.parentAnswer = parentAnswer;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "answerId")
	public Set<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}
	
	
	
	
}
