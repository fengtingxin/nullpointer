package com.exp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "advice")
public class Advice {

	private Integer adviceId;   //ID
	private String adviceUserName; //建议人用户名
	private String adviceTheme;    //建议主题
	private String adviceContent;  //建议内容
	private String adviceUserEmail;//建议使用邮箱
	private Date adviceTime;       //建议的时间
	private boolean visable;       //是否被查看
	
	
	//set/get
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getAdviceId() {
		return adviceId;
	}
	public void setAdviceId(Integer adviceId) {
		this.adviceId = adviceId;
	}
	public String getAdviceUserName() {
		return adviceUserName;
	}
	public void setAdviceUserName(String adviceUserName) {
		this.adviceUserName = adviceUserName;
	}
	public String getAdviceTheme() {
		return adviceTheme;
	}
	public void setAdviceTheme(String adviceTheme) {
		this.adviceTheme = adviceTheme;
	}
	public String getAdviceContent() {
		return adviceContent;
	}
	public void setAdviceContent(String adviceContent) {
		this.adviceContent = adviceContent;
	}
	public String getAdviceUserEmail() {
		return adviceUserEmail;
	}
	public void setAdviceUserEmail(String adviceUserEmail) {
		this.adviceUserEmail = adviceUserEmail;
	}
	public Date getAdviceTime() {
		return adviceTime;
	}
	public void setAdviceTime(Date adviceTime) {
		this.adviceTime = adviceTime;
	}
	public boolean isVisable() {
		return visable;
	}
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	
	
}
