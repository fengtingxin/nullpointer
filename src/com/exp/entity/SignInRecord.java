package com.exp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="sign_up_record")
public class SignInRecord {
	private UserInfo userInfo;
	private Date lastTime;
	private Integer signNumber;
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Integer getSignNumber() {
		return signNumber;
	}
	public void setSignNumber(Integer signNumber) {
		this.signNumber = signNumber;
	}
	

}
