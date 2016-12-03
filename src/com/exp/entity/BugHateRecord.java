package com.exp.entity;
/**
 * @author tangwenru
 * bug获踩记录表
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
@Table(name="bug_hate_record")
public class BugHateRecord {
	private Integer bugHateRecordId;
	private Integer bugHateStatus;
	private Date bugHateTime;
	private UserInfo userInfo;
	private Bug bug;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBugHateRecordId() {
		return bugHateRecordId;
	}
	public void setBugHateRecordId(Integer bugHateRecordId) {
		this.bugHateRecordId = bugHateRecordId;
	}
	public Integer getBugHateStatus() {
		return bugHateStatus;
	}
	public void setBugHateStatus(Integer bugHateStatus) {
		this.bugHateStatus = bugHateStatus;
	}
	public Date getBugHateTime() {
		return bugHateTime;
	}
	public void setBugHateTime(Date bugHateTime) {
		this.bugHateTime = bugHateTime;
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
