package com.exp.entity;
/**
 * @author tangwenru
 * 登录记录表
 */
import javax.persistence.CascadeType;
/**
 * 
 */
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sign_up_record")
public class SignUpRecord {
	private Integer signUpId;
	private UserInfo userInfo;
	private Integer years;//年份
	private Integer months;// 月份
	private Integer signUpNumber;// 登录次数
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSignUpId() {
		return signUpId;
	}

	public void setSignUpId(Integer signUpId) {
		this.signUpId = signUpId;
	}
	@ManyToOne(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "userInfoId")
	public UserInfo getUserInfo() {
		return userInfo;
	}

	

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	

	public Integer getSignUpNumber() {
		return signUpNumber;
	}

	public void setSignUpNumber(Integer signUpNumber) {
		this.signUpNumber = signUpNumber;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}
    
	
    
	

}
