package com.exp.entity;
/**
 * @author tangwenru
 * 签到记录表
 */
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sign_in_record")
public class SignInRecord {
	private Integer signId;
	private UserInfo userInfo;
	private Date lastTime;
	private Integer signNumber;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSignId() {
		return signId;
	}
	public void setSignId(Integer signId) {
		this.signId = signId;
	}
	@OneToOne  
	@JoinColumn(name="userInfoId",insertable=true,unique=true)  
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
