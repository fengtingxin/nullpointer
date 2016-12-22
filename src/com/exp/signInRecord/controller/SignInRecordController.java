package com.exp.signInRecord.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.entity.LoginUser;
import com.exp.entity.SignInRecord;
import com.exp.entity.UserInfo;
import com.exp.signInRecord.service.SignInRecordServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;

@Controller
public class SignInRecordController {
	@Resource
	private SignInRecordServiceImpl signInRecordServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@RequestMapping(value="sign",method=RequestMethod.POST)
	@ResponseBody
	public String sign(HttpServletRequest request){
		LoginUser loginUser=(LoginUser) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			return "login"; //如果为空，跳转到login
		}
		UserInfo userInfo=loginUser.getUserInfo();
		if(this.signInRecordServiceImpl.findSignInRecord(loginUser.getLoginUserId())==null){
			SignInRecord signInRecord=new SignInRecord();
			signInRecord.setLastTime(new Date());
			signInRecord.setSignNumber(1);
			
			signInRecord.setUserInfo(loginUser.getUserInfo());
			this.signInRecordServiceImpl.saveSignInRecord(signInRecord);
			loginUser.getUserInfo().setUserInfoHonorCount(loginUser.getUserInfo().getUserInfoHonorCount()+1);
			this.userInfoServiceImpl.updateUserInfo(loginUser.getUserInfo());
			return "theFirst";
		}else{
		   Date date=new Date();
		   if(userInfo.getSignInRecord().getSignNumber()!=null){
		   request.setAttribute("signDay", userInfo.getSignInRecord().getSignNumber());
		   }
		   if((userInfo.getSignInRecord().getLastTime().getYear()==date.getYear())&&
				   (userInfo.getSignInRecord().getLastTime().getMonth()==date.getMonth())&&
				   (userInfo.getSignInRecord().getLastTime().getDay()==date.getDay())){
			   return "signed";
		   }else{
			   userInfo.getSignInRecord().setLastTime(new Date());
			   userInfo.getSignInRecord().setSignNumber(userInfo.getSignInRecord().getSignNumber()+1);
			   this.signInRecordServiceImpl.updateSignInRecord(userInfo.getSignInRecord());
			   loginUser.getUserInfo().setUserInfoHonorCount(loginUser.getUserInfo().getUserInfoHonorCount()+1);
			   this.userInfoServiceImpl.updateUserInfo(loginUser.getUserInfo());
			   return "signOk";
		   }
		}
		
		
	}

}
