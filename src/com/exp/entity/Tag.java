package com.exp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * 实体类：标签类
 * 
 * @author tangwenru
 *
 */
@Entity
@Table(name = "tag")
public class Tag implements java.io.Serializable {

	// 属性
	private Integer tagId; // id //主键
	private String tagName; // 标签名
	private Set<Question> questions = new HashSet<Question>(0);
	private Set<R_Tag_UserInfo> r_tag_userInfo = new HashSet<R_Tag_UserInfo>(0);
	private Set<Bug> bugs = new HashSet<Bug>(0);

	// get/set方法
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@ManyToMany(mappedBy = "tags")
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(mappedBy = "tag", fetch = FetchType.EAGER)
	@OrderBy(value = "tagNumber ASC")
	public Set<R_Tag_UserInfo> getR_tag_userInfo() {
		return r_tag_userInfo;
	}

	public void setR_tag_userInfo(Set<R_Tag_UserInfo> r_tag_userInfo) {
		this.r_tag_userInfo = r_tag_userInfo;
	}

	@ManyToMany(mappedBy = "tags")
	public Set<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(Set<Bug> bugs) {
		this.bugs = bugs;
	}

}
