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
 * comment点赞记录表
 * @author tangwenru
 *
 */
@Entity
@Table(name="comment_like_record")
public class CommentLikeRecord {
	private Integer commentLikeRecordId;
	private Integer commentLikeStatus;
	private Date commentLikeTime;
	private UserInfo userInfo;
	private Comment comment;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCommentLikeRecordId() {
		return commentLikeRecordId;
	}
	public void setCommentLikeRecordId(Integer commentLikeRecordId) {
		this.commentLikeRecordId = commentLikeRecordId;
	}
	public Integer getCommentLikeStatus() {
		return commentLikeStatus;
	}
	public void setCommentLikeStatus(Integer commentLikeStatus) {
		this.commentLikeStatus = commentLikeStatus;
	}
	public Date getCommentLikeTime() {
		return commentLikeTime;
	}
	public void setCommentLikeTime(Date commentLikeTime) {
		this.commentLikeTime = commentLikeTime;
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
