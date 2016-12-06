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
 * comment获踩记录表
 * @author tangwenru
 *
 */
@Entity
@Table(name="comment_hate_record")
public class CommentHateRecord {
	private Integer commentHateRecordId;
	private Integer commentHateStatus;
	private Date commentHateTime;
	private UserInfo userInfo;
	private Comment comment;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCommentHateRecordId() {
		return commentHateRecordId;
	}
	public void setCommentHateRecordId(Integer commentHateRecordId) {
		this.commentHateRecordId = commentHateRecordId;
	}
	public Integer getCommentHateStatus() {
		return commentHateStatus;
	}
	public void setCommentHateStatus(Integer commentHateStatus) {
		this.commentHateStatus = commentHateStatus;
	}
	public Date getCommentHateTime() {
		return commentHateTime;
	}
	public void setCommentHateTime(Date commentHateTime) {
		this.commentHateTime = commentHateTime;
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
	@JoinColumn(name = "commentId")
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	

}
