package com.exp.entity;
/**
 * @author tangwenru
 */
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
@Entity
@Table(name="bug_like_record")
public class BugLikeRecord {
	private Integer bugLikeRecordId;//Bug点赞记录表id
	private Date bugLikeTime;//点赞时间
	private Integer  bugLikeStatus;//该用户是否点赞
	private UserInfo userInfo;
	private Bug bug;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBugLikeRecordId() {
		return bugLikeRecordId;
	}
	public void setBugLikeRecordId(Integer bugLikeRecordId) {
		this.bugLikeRecordId = bugLikeRecordId;
	}
	
	
	
	public Date getBugLikeTime() {
		return bugLikeTime;
	}
	public void setBugLikeTime(Date bugLikeTime) {
		this.bugLikeTime = bugLikeTime;
	}
	public Integer getBugLikeStatus() {
		return bugLikeStatus;
	}
	public void setBugLikeStatus(Integer bugLikeStatus) {
		this.bugLikeStatus = bugLikeStatus;
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
	@JoinColumn(name = "bugId")
	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	
	

}
