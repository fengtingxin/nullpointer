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
@Table(name = "bug")
public class Bug {
	// 属性
	private Integer bugId;
	private String bugTitle;// bug标题

	private String bugDescribe;// bug描述
	private String bugReason;// bug原因
	private String bugMethod;// bug解决方法
	private Date bugPublishTime;// bug发表时间
	private Integer bugLikeNum;// bug获赞数量
	private Integer bugHateNum;// bug被踩数量
	private Integer bugPageviews;// bug浏览量
	private UserInfo userInfo;// bug对应的用户
	private boolean bugAudited; //bug是否被审核
	private boolean bugAuditPass; //bug审核是否通过      
	//逻辑为：bugAudited为false,bugAuditPass为false，未被审核
	//bugAudited为true,bugAuditPass为false，审核未通过
	//bugAudited为true,bugAuditPass为true，审核通过
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Tag> tags = new HashSet<Tag>(0);
	private Set<BugLikeRecord> bugLikeRecords = new HashSet<BugLikeRecord>(0);

	// set/get方法
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getBugDescribe() {
		return bugDescribe;
	}

	public void setBugDescribe(String bugDescribe) {
		this.bugDescribe = bugDescribe;
	}

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getBugReason() {
		return bugReason;
	}

	public void setBugReason(String bugReason) {
		this.bugReason = bugReason;
	}
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(impl=StandardAnalyzer.class)
	public String getBugMethod() {
		return bugMethod;
	}

	public void setBugMethod(String bugMethod) {
		this.bugMethod = bugMethod;
	}

	public Date getBugPublishTime() {
		return bugPublishTime;
	}

	public void setBugPublishTime(Date bugPublishTime) {
		this.bugPublishTime = bugPublishTime;
	}

	public Integer getBugLikeNum() {
		return bugLikeNum;
	}

	public void setBugLikeNum(Integer bugLikeNum) {
		this.bugLikeNum = bugLikeNum;
	}

	public Integer getBugHateNum() {
		return bugHateNum;
	}

	public void setBugHateNum(Integer bugHateNum) {
		this.bugHateNum = bugHateNum;
	}

	public Integer getBugPageviews() {
		return bugPageviews;
	}

	public void setBugPageviews(Integer bugPageviews) {
		this.bugPageviews = bugPageviews;
	}

	@OneToMany(mappedBy = "bug", cascade = CascadeType.ALL)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "bugAuthorID")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "r_tag_bug", joinColumns = {
			@JoinColumn(name = "bugId", referencedColumnName = "bugId") }, inverseJoinColumns = {
					@JoinColumn(name = "tagId", referencedColumnName = "tagId") })
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	/**
	 * @author tangwenru
	 * @return
	 */
	@OneToMany(mappedBy = "bug", cascade = CascadeType.ALL)
	public Set<BugLikeRecord> getBugLikeRecords() {
		return bugLikeRecords;
	}

	public void setBugLikeRecords(Set<BugLikeRecord> bugLikeRecords) {
		this.bugLikeRecords = bugLikeRecords;
	}

	public boolean isBugAudited() {
		return bugAudited;
	}

	public void setBugAudited(boolean bugAudited) {
		this.bugAudited = bugAudited;
	}

	public boolean isBugAuditPass() {
		return bugAuditPass;
	}

	public void setBugAuditPass(boolean bugAuditPass) {
		this.bugAuditPass = bugAuditPass;
	}
	
	
}
