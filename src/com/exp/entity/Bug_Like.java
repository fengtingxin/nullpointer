package com.exp.entity;

import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bug_like")
public class Bug_Like {
	private Integer id;
	private Bug bug;
	private HashSet<UserInfo> userInfos = new HashSet<UserInfo>(0);

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(targetEntity = Bug.class)
	@PrimaryKeyJoinColumn(name = "bug_id", referencedColumnName = "bugId")
	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	@ManyToOne(targetEntity = UserInfo.class, cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "user_id")
	public HashSet<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(HashSet<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

}
