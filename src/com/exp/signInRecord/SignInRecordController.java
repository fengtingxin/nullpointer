package com.exp.signInRecord;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exp.entity.LoginUser;
import com.exp.entity.SignInRecord;
import com.exp.entity.UserInfo;
import com.exp.signInRecord.service.SignInRecordServiceImpl;

@Controller
public class SignInRecordController {
	@Resource
	private SignInRecordServiceImpl signInRecordServiceImpl;
	@RequestMapping(value="sign")
	public String sign(HttpServletRequest request){
		LoginUser loginUser=(LoginUser) request.getSession().getAttribute("loginUser");
		UserInfo userInfo=loginUser.getUserInfo();
		if(this.signInRecordServiceImpl.findSignInRecord(loginUser.getLoginUserId())==null){
			SignInRecord signInRecord=new SignInRecord();
			signInRecord.setLastTime(new Date());
			signInRecord.setSignNumber(1);
			signInRecord.setUserInfo(loginUser.getUserInfo());
			this.signInRecordServiceImpl.saveSignInRecord(signInRecord);
			request.setAttribute("signDay", 1);
			return "home";
		}else{
		   Date date=new Date();
		   if(userInfo.getSignInRecord().getSignNumber()!=null){
		   request.setAttribute("signDay", userInfo.getSignInRecord().getSignNumber());
		   }
		   if((userInfo.getSignInRecord().getLastTime().getYear()==date.getYear())&&
				   (userInfo.getSignInRecord().getLastTime().getMonth()==date.getMonth())&&
				   (userInfo.getSignInRecord().getLastTime().getDay()==date.getDay())){
			   System.out.println("明天再来签到吧！");
			   return "home";
		   }else{
			   userInfo.getSignInRecord().setLastTime(new Date());
			   userInfo.getSignInRecord().setSignNumber(userInfo.getSignInRecord().getSignNumber()+1);
			   this.signInRecordServiceImpl.updateSignInRecord(userInfo.getSignInRecord());
			   System.out.println("record:"+userInfo.getSignInRecord().getSignNumber().toString()+"\n"+userInfo.getSignInRecord().getSignNumber().intValue());
			   request.setAttribute("signDay", userInfo.getSignInRecord().getSignNumber().intValue());
			   return "home";
		   }
		}
		
		
	}

}
