package com.exp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "userinfo")
public class UserInfo {

	// 属性
	private Integer userInfoId;
	private Date userInfoBirthday;// 用户生日
	private String userInfoHeadPortrait;// 用户头像
	private String userInfoSex;// 用户性别
	private Date userInfoRegistTime;// 用户注册时间
	private String userInfoDescribe;// 用户描述
	private Integer userInfoHonorCount;// 荣誉值
	private LoginUser loginUser;
	private Set<Bug> bugs = new HashSet<Bug>(0);
	private Set<Question> questions = new HashSet<Question>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);
	private Set<Answer> answers = new HashSet<Answer>(0);
	private Set<R_Tag_UserInfo> r_tag_userInfo = new HashSet<R_Tag_UserInfo>(0);
	private Set<BugLikeRecord> bugLikeRecords = new HashSet<BugLikeRecord>(0);
    private SignInRecord signInRecord;
    private Set<SignUpRecord> signUpRecords=new HashSet<SignUpRecord>(0);
  
	@Id
	@GenericGenerator(name = "foreignkey", strategy = "foreign", parameters = @Parameter(value = "loginUser", name = "property"))
	@GeneratedValue(generator = "foreignkey")
	public Integer getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Date getUserInfoBirthday() {
		return userInfoBirthday;
	}

	public void setUserInfoBirthday(Date userInfoBirthday) {
		this.userInfoBirthday = userInfoBirthday;
	}

	public String getUserInfoHeadPortrait() {
		return userInfoHeadPortrait;
	}

	public void setUserInfoHeadPortrait(String userInfoHeadPortrait) {
		this.userInfoHeadPortrait = userInfoHeadPortrait;
	}

	public String getUserInfoSex() {
		return userInfoSex;
	}

	public void setUserInfoSex(String userInfoSex) {
		this.userInfoSex = userInfoSex;
	}

	public Date getUserInfoRegistTime() {
		return userInfoRegistTime;
	}

	public void setUserInfoRegistTime(Date userInfoRegistTime) {
		this.userInfoRegistTime = userInfoRegistTime;
	}

	public String getUserInfoDescribe() {
		return userInfoDescribe;
	}

	public void setUserInfoDescribe(String userInfoDescribe) {
		this.userInfoDescribe = userInfoDescribe;
	}

	public Integer getUserInfoHonorCount() {
		return userInfoHonorCount;
	}

	public void setUserInfoHonorCount(Integer userInfoHonorCount) {
		this.userInfoHonorCount = userInfoHonorCount;
	}

	@OneToOne
	@PrimaryKeyJoinColumn
	public LoginUser getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	@OneToMany(mappedBy = "userInfo")
	public Set<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(Set<Bug> bugs) {
		this.bugs = bugs;
	}

	@OneToMany(mappedBy = "userInfo")
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@OneToMany(mappedBy = "userInfo")
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(mappedBy = "userInfo")
	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @author tangwenru
	 * @return
	 */
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	public Set<BugLikeRecord> getBugLikeRecords() {
		return bugLikeRecords;
	}

	public void setBugLikeRecords(Set<BugLikeRecord> bugLikeRecords) {
		this.bugLikeRecords = bugLikeRecords;
	}

	@OneToMany(mappedBy = "userInfo",fetch=FetchType.EAGER)
	public Set<R_Tag_UserInfo> getR_tag_userInfo() {
		return r_tag_userInfo;
	}

	public void setR_tag_userInfo(Set<R_Tag_UserInfo> r_tag_userInfo) {
		this.r_tag_userInfo = r_tag_userInfo;
	}
	@OneToOne(mappedBy="userInfo",fetch=FetchType.EAGER)  
	public SignInRecord getSignInRecord() {
		return signInRecord;
	}

	public void setSignInRecord(SignInRecord signInRecord) {
		this.signInRecord = signInRecord;
	}
	@OneToMany(mappedBy = "userInfo",fetch=FetchType.EAGER)
	public Set<SignUpRecord> getSignUpRecords() {
		return signUpRecords;
	}
    
	public void setSignUpRecords(Set<SignUpRecord> signUpRecords) {
		this.signUpRecords = signUpRecords;
	}
	

	

}
