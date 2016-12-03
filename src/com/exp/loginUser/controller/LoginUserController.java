package com.exp.loginUser.controller;

import java.io.IOException;
import java.util.Date;

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

import com.exp.entity.LoginUser;
import com.exp.entity.Role;
import com.exp.entity.UserInfo;
import com.exp.loginUser.service.LoginUserServiceImpl;
import com.exp.role.service.RoleServiceImpl;
import com.framework.EncodingTool;

@Controller
@RequestMapping("loginUser")
public class LoginUserController {

	@Resource
	private LoginUserServiceImpl userServiceImpl;
	@Resource
	private RoleServiceImpl roleServiceImpl;

	/**
	 * 功能： 实现注册功能 同时实现发送邮件的功能！
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @param session
	 * @return 0表示邮件发送成功 1 代表数据库连接失败，服务器问题 2 代表参数传递错误，网络问题，请刷新重试 3
	 *         代表用户名已经存在，请重新输入 4 代表邮箱已经存在，请重新输入 5 代表邮箱不符合格式
	 * @author fengtingxin
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestParam(name = "loginName") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, HttpSession session) {
		// code转换
		// 判断email是否符合格式,使用java正则表达式
		if (EncodingTool.isEmail(email)) {
			LoginUser loginUser = new LoginUser();
			loginUser.setLoginEmail(email);
			loginUser.setLoginName(name);
			loginUser.setLoginPassword(password);
			UserInfo userInfo = new UserInfo();
			// 获取用户注册时间
			Date time = new Date();// new Date()为获取当前系统时间
			// 给新注册的用户分配角色
			Role role = this.roleServiceImpl.getRole(2);
			loginUser.setRole(role);
			userInfo.setUserInfoRegistTime(time);
			userInfo.setLoginUser(loginUser);
			userInfo.setUserInfoHeadPortrait("default.jpg");
			loginUser.setUserInfo(userInfo);
			String result = this.userServiceImpl.register(loginUser);
			if (result == "0") {
				// 这里是迫不得已才改成的自动跳转，本来想的是自动关闭页面，但是由于google浏览器的限制，没有实现该功能！
				String content = "<h4> <small>本页面将于10秒内自动跳转到登录！<a href='../login.jsp'>立即跳转</a></small></h4>";
				String welcome = "您的注册邮箱为：" + email + ",注册奖励&nbsp;<b>10</b>&nbsp;荣誉值，已经存入您的账户，快去邮箱激活账户吧！";
				session.setAttribute("regiserWelcome", welcome);
				session.setAttribute("registerTitle", "注册成功");
				session.setAttribute("registerEmail", email);
				session.setAttribute("registerContent", content);
				return result;
			}
			return result;
		}
		return "5";
	}

	/**
	 * 功能： 1.激活注册账号 2.使页面跳转到登录页面 3.设置用户头像
	 * 
	 * @param active
	 * @param loginName
	 * @param session
	 * @return 跳转到注册确认界面
	 * @author fengtingxin
	 */
	@RequestMapping(value = "activeLoginUser", method = RequestMethod.GET)
	public String activeLoginUser(@RequestParam(name = "loginName") String loginName, HttpSession session) {
		loginName = EncodingTool.encodeStr(loginName);
		LoginUser loginUser = this.userServiceImpl.findByName(loginName);
		if (loginUser == null) {
			return "error";
		}
		loginUser.setLoginActive(1); // 激活用户
		UserInfo userInfo = loginUser.getUserInfo();
		userInfo.setUserInfoHeadPortrait("default.jpg"); // 设置用户的默认头像
		loginUser.setUserInfo(userInfo);
		this.userServiceImpl.updateLoginUser(loginUser); // 更新
		String content = "<h4> <small>本页面将于10秒内自动跳转到登录！<a href='http://localhost:8080/nullpointer/login.jsp'>立即跳转</a></small></h4>";
		session.setAttribute("regiserWelcome",
				"您的注册邮箱为:" + loginUser.getLoginEmail() + ",恭喜您激活成功，快去体验nullpointer的美好吧！");
		session.setAttribute("registerTitle", "激活成功");
		session.setAttribute("registerEmail", loginUser.getLoginEmail());
		session.setAttribute("registerContent", content);
		return "registerSure";
	}

	/**
	 * 功能: 1.能够使用邮箱登录/也可以使用用户名登录 2.验证code 3.验证用户名/邮箱是否存在 4.验证密码的正确性
	 * 5.跳转到index.jsp //在js中修改
	 * 
	 * @param loginName
	 * @param password
	 * @param codeValue
	 * @param session
	 * @return 返回一个string值 0 表示登录成功 -1 表示验证码错误 1 表示数据连接错误 2 表示参数传递错误 14 表示用户名不存在
	 *         16 表示尚未激活 19 表示密码错误
	 * @author fengtingxin
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam(name = "loginName") String loginName,
			@RequestParam(name = "password") String password, @RequestParam(name = "codeValue") String codeValue,
			HttpSession session) {
		String code = (String) session.getAttribute("post_validate_code");
		if (!code.equalsIgnoreCase(codeValue)) {
			return "-1";
		}
		String result = this.userServiceImpl.loginVerify(loginName, password);
		if (result.equals("0")) {
			// 输入正确
			LoginUser loginUser = this.userServiceImpl.findLoginUser(loginName, password);
			session.setAttribute("loginUser", loginUser);
		}
		return result;
	}
    /**
     * @function 管理员登录
     * @author tangwenru
     * @param loginName
     * @param password
     * @param session
     * @return
     */
	@RequestMapping("loginAdmin")
	public String loginAdmin(@RequestParam(name = "loginName") String loginName,
			@RequestParam(name = "password") String password,
			HttpSession session) {
		
		String result = this.userServiceImpl.loginVerify(loginName, password);
		if (result.equals("0")) {
			// 输入正确
			LoginUser loginUser = this.userServiceImpl.findLoginUser(loginName, password);
			session.setAttribute("loginUser", loginUser);
		}
		return "admin/index";
	}
}
