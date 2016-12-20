package com.exp.userinfo.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.bug.service.BugServiceImpl;
import com.exp.entity.Bug;

import com.exp.entity.LoginUser;
import com.exp.entity.Question;
import com.exp.entity.R_Tag_UserInfo;
import com.exp.entity.SignInRecord;
import com.exp.entity.SignUpRecord;
import com.exp.entity.Tag;
import com.exp.entity.UserInfo;
import com.exp.entity.ZuiData;
import com.exp.entity.ZuiDataTwo;
import com.exp.loginUser.service.LoginUserServiceImpl;
import com.exp.question.service.QuestionServiceImpl;
import com.exp.signInRecord.service.SignInRecordServiceImpl;
import com.exp.signUpRecord.service.SignUpRecordServiceImpl;
import com.exp.tag.service.TagServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.EncodingTool;


import net.sf.json.JSONArray;

@Controller
public class UserInfoController {
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@Resource
	private BugServiceImpl bugServiceImpl;
	@Resource
	private QuestionServiceImpl questionServiceImpl;
	@Resource
	private TagServiceImpl tagServiceImpl;
	@Resource
	private LoginUserServiceImpl loginUserServiceImpl;
	@Resource
	private SignInRecordServiceImpl signInRecordServiceImpl;
	@Resource
	private SignUpRecordServiceImpl signUpRecordServiceImpl;
	

	/**
	 * @zhangzhaolin
	 * @param session
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpSession session) {
		// bug推荐
		List<Bug> bugList = bugServiceImpl.findHonor().subList(0, 6);
		session.setAttribute("bugHonorList", bugList);
		// 查询问答推荐
		List<Question> questionList = questionServiceImpl.findQuestionRecommend().subList(0, 6);
		session.setAttribute("questionHonorList", questionList);
		// 所有标签信息
		List<Tag> tagList = tagServiceImpl.findAllTag();
		List<UserInfo> userInfoList=userInfoServiceImpl.order();
		session.setAttribute("tagList", tagList);
		session.setAttribute("userList", userInfoList);
		return "index";
	}

	/**
	 * @function 返回他的主页
	 * @author tangwenru
	 * @param loginName
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "hishome", method = RequestMethod.GET)
	public String findByIdTwo(@RequestParam(value = "loginName") String loginName, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
			if(loginName==null||loginName.equals("")){
				return "/404";
			}
			loginName =EncodingTool.encodeStr(loginName);
			LoginUser lu=this.loginUserServiceImpl.findByName(loginName);
			if(lu==null){
				return "/404";
			}
			UserInfo userInfo =lu.getUserInfo();
			// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
			long array[] = UserInfoController.differ(userInfo);
			session.setAttribute("day", array[0]);
			session.setAttribute("hour", array[1]);
			session.setAttribute("min", array[2]);
			session.setAttribute("second", array[3]);
			ArrayList<ZuiData> zuiData_List = new ArrayList<ZuiData>();
			Set<R_Tag_UserInfo> userInfo_tags = userInfo.getR_tag_userInfo();
			for (R_Tag_UserInfo it : userInfo_tags) {
				ZuiData zuiData = new ZuiData();
				String tagName = it.getTag().getTagName();
				zuiData.setLabel(tagName);
				Integer tagNumber = it.getTagNumber();
				zuiData.setValue(tagNumber);
				zuiData_List.add(zuiData);
			}
			ArrayList<ZuiDataTwo> zuiDataTwo_List=new ArrayList<ZuiDataTwo>();
			Set<SignUpRecord> signUpRecords=new HashSet<SignUpRecord>(0);
			Calendar c=Calendar.getInstance(); 
			Iterator<SignUpRecord> iterator = userInfo.getSignUpRecords().iterator(); 
			while(iterator.hasNext()){
				SignUpRecord signUpRecord=iterator.next();
				if(signUpRecord.getYears()==c.get(Calendar.YEAR)){
					signUpRecords.add(signUpRecord);
				}
			}
			System.out.println("length"+signUpRecords.size());
			for (SignUpRecord it : signUpRecords) {
				ZuiDataTwo zuiDataTwo = new ZuiDataTwo();
				Integer months = it.getMonths();
				zuiDataTwo.setMonths(months);
				Integer number = it.getSignUpNumber();
				zuiDataTwo.setNumber(number);
				zuiDataTwo_List.add(zuiDataTwo);
			}
			JSONArray jsonObject = JSONArray.fromObject(zuiData_List);
			JSONArray jsonObjectTwo = JSONArray.fromObject(zuiDataTwo_List);
			session.setAttribute("userInfo_tags", jsonObject);
			session.setAttribute("signUpRecords", jsonObjectTwo);
			// 等一下再打印
			System.out.println(jsonObject);
			SignInRecord temp = this.signInRecordServiceImpl.findSignInRecord(userInfo.getUserInfoId());
			if(temp!=null){
			request.setAttribute("signDay", temp.getSignNumber().intValue());
			}
			session.setAttribute("userInfo", userInfo);
			// 获取用户的id信息
			LoginUser loginUser=(LoginUser)session.getAttribute("loginUser");
			if(loginUser!=null&&loginUser.getLoginUserId()==userInfo.getUserInfoId()){
				return "home";
			}
			return "hishome";
		
	}
	/**
	 * @function 根据用户的id查找用户，返回home.jsp页面
	 * @author tangwenru
	 * @param userInfo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String findById(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {
		// 获取用户的id信息
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if (session.getAttribute("loginUser") != null && session.getAttribute("loginUser") != "") {
			UserInfo userInfo = loginUser.getUserInfo();

			// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
			long array[] = UserInfoController.differ(userInfo);
			session.setAttribute("day", array[0]);
			session.setAttribute("hour", array[1]);
			session.setAttribute("min", array[2]);
			session.setAttribute("second", array[3]);
			ArrayList<ZuiData> zuiData_List = new ArrayList<ZuiData>();
			Set<R_Tag_UserInfo> userInfo_tags = userInfo.getR_tag_userInfo();
			for (R_Tag_UserInfo it : userInfo_tags) {
				ZuiData zuiData = new ZuiData();
				String tagName = it.getTag().getTagName();
				zuiData.setLabel(tagName);
				Integer tagNumber = it.getTagNumber();
				zuiData.setValue(tagNumber);
				zuiData_List.add(zuiData);
			}
			ArrayList<ZuiDataTwo> zuiDataTwo_List=new ArrayList<ZuiDataTwo>();
			Set<SignUpRecord> signUpRecords=new HashSet<SignUpRecord>(0);
			Calendar c=Calendar.getInstance(); 
			Iterator<SignUpRecord> iterator = userInfo.getSignUpRecords().iterator(); 
			while(iterator.hasNext()){
				SignUpRecord signUpRecord=iterator.next();
				if(signUpRecord.getYears()==c.get(Calendar.YEAR)){
					signUpRecords.add(signUpRecord);
				}
			}
			System.out.println("length"+signUpRecords.size());
			for (SignUpRecord it : signUpRecords) {
				ZuiDataTwo zuiDataTwo = new ZuiDataTwo();
				Integer months = it.getMonths();
				zuiDataTwo.setMonths(months);
				Integer number = it.getSignUpNumber();
				zuiDataTwo.setNumber(number);
				zuiDataTwo_List.add(zuiDataTwo);
			}
			JSONArray jsonObject = JSONArray.fromObject(zuiData_List);
			JSONArray jsonObjectTwo = JSONArray.fromObject(zuiDataTwo_List);
			session.setAttribute("userInfo_tags", jsonObject);
			session.setAttribute("signUpRecords", jsonObjectTwo);
			// 等一下再打印
			System.out.println(jsonObject);
			System.out.println(jsonObjectTwo);
			SignInRecord temp = this.signInRecordServiceImpl.findSignInRecord(loginUser.getLoginUserId());
			if(temp!=null){
			request.setAttribute("signDay", temp.getSignNumber().intValue());
			}
			return "home";
		} else {
			return "login";
		}

	}

	/**
	 * @function 计算用户注册距离现在的时间差
	 * @author tangwenru
	 * @param u
	 *            UserInfo对象
	 * @return
	 */
	public static long[] differ(UserInfo u) {
		// 获取当前时间
		Date now = new Date();
		// 获取用户注册的时间
		Date date = u.getUserInfoRegistTime();

		System.out.println("time : " + u.getUserInfoRegistTime());
		long[] array = new long[4];
		// 计算时间差
		if (now != null) {
			long l = now.getTime() - date.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			long hour = (l / (60 * 60 * 1000) - day * 24);
			long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			array[0] = day;
			array[1] = hour;
			array[2] = min;
			array[3] = s;
			System.out.println("day:" + day + "hour:" + hour);

		}
		return array;
	}

	/**
	 * @function 点击编辑，调用的方法
	 * @author tangwenru
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String toEdit(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request) {
		UserInfo userinfo = this.userInfoServiceImpl.findById(id);
		request.setAttribute("userinfo", userinfo);
		Date userInfoBirthday = userinfo.getUserInfoBirthday();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthday = sdf.format(userInfoBirthday);
		// 将用户信息以及格式化后的出生日期和注册日期存到request范围
		request.setAttribute("birthday", birthday);
		// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
		long array[] = UserInfoController.differ(userinfo);
		request.setAttribute("day", array[0]);
		request.setAttribute("hour", array[1]);
		request.setAttribute("min", array[2]);
		request.setAttribute("second", array[3]);
		request.setAttribute("birthday", birthday);
		request.setAttribute("action", "edit");
		return "accountSetting";
	}

	/**
	 * @function 点击更新信息，将编辑后的信息更新
	 * @author tangwenru
	 * @param id
	 * @param loginName
	 * @param birthday
	 * @param sex
	 * @param describe
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "loginName", required = false, defaultValue = "") String loginName,
			@RequestParam(value = "birthday", required = false, defaultValue = "") String birthday,
			@RequestParam(value = "sex", required = false, defaultValue = "") String sex,
			@RequestParam(value = "describe", required = false, defaultValue = "") String describe,HttpSession session) {
		// 防止中文乱码
		try {
			sex = new String(sex.getBytes(), "utf-8");
			describe = new String(describe.getBytes(), "utf-8");
			loginName = new String(loginName.getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "encodeError";
		}
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		LoginUser lu =this.loginUserServiceImpl.findByName(loginName);
		if(lu!=null && lu.getLoginUserId()!= loginUser.getLoginUserId()){
			return "loginNameUsed";
		}
		UserInfo u = loginUser.getUserInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (!birthday.equals("")) {
			try {
				date = sdf.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
				return "dateError";
			}
		}
		u.getLoginUser().setLoginName(loginName);
		u.setUserInfoBirthday(date);
		u.setUserInfoSex(sex);
		u.setUserInfoDescribe(describe);
		loginUser.setUserInfo(u);
		this.userInfoServiceImpl.editUserInfo(u);
		return "updateOk";
	}

	/**
	 * @author Ray_1 用户退出
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/logOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();// 防止创建Session
		if (session == null) {
			return "login";
		}
		session.invalidate();
		// session.removeAttribute("loginUser");
		return "login";
	}
	
}
