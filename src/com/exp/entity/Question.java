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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed
@Table(name = "question")
public class Question {

	private Integer questionId;
	private String questionTitle;// 问题的标题
	private String questionDescribe;// 问题的描述
	private String questionDetailed;// 问题详细信息
	private Date questionPublishTime;// 问题的发表时间
	private Integer questionLikeNum = 0;// 问题获赞数量
	private Integer questionHateNum = 0;// 问题被踩数量
	private Integer questionAnswerCount = 0;// 回答的数量
	private UserInfo userInfo;// 问题对应的用户
	private Set<Answer> answers = new HashSet<Answer>(0);// 问题的回答
	private Set<Tag> tags = new HashSet<Tag>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getQuestionDescribe() {
		return questionDescribe;
	}

	public void setQuestionDescribe(String questionDescribe) {
		this.questionDescribe = questionDescribe;
	}

	public Date getQuestionPublishTime() {
		return questionPublishTime;
	}

	public void setQuestionPublishTime(Date questionPublishTime) {
		this.questionPublishTime = questionPublishTime;
	}

	public Integer getQuestionLikeNum() {
		return questionLikeNum;
	}

	public void setQuestionLikeNum(Integer questionLikeNum) {
		this.questionLikeNum = questionLikeNum;
	}

	public Integer getQuestionHateNum() {
		return questionHateNum;
	}

	public String getQuestionDetailed() {
		return questionDetailed;
	}

	public void setQuestionDetailed(String questionDetailed) {
		this.questionDetailed = questionDetailed;
	}

	public void setQuestionHateNum(Integer questionHateNum) {
		this.questionHateNum = questionHateNum;
	}

	public Integer getQuestionAnswerCount() {
		return questionAnswerCount;
	}

	public void setQuestionAnswerCount(Integer questionAnswerCount) {
		this.questionAnswerCount = questionAnswerCount;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionAuthorID")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@OneToMany(mappedBy = "question")
	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "r_tag_question", joinColumns = {
			@JoinColumn(name = "questionId", referencedColumnName = "questionId") }, inverseJoinColumns = {
					@JoinColumn(name = "tagId", referencedColumnName = "tagId") })
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

}
