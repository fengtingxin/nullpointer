package com.exp.admin.user.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.LoginUser;
import com.exp.loginUser.service.LoginUserServiceImpl;
import com.framework.EncodingTool;

@Controller
@RequestMapping("admin")
public class AdminUserController {

	@Resource
	private LoginUserServiceImpl userServiceImpl;

	/**
	 * 功能：
	 * 管理员登录
	 * 默认role为1
	 * 通过request.setAttr传递返回值loginError,在login.jsp中显示
	 * @param loginName
	 * @param password
	 * @param request //返回参数
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String toLogin(@RequestParam(name="name") String loginName,@RequestParam(value="password") String password,HttpServletRequest request,
			HttpSession session){
		if(loginName==null||password==null||loginName.length()==0||password.length()==0){
			request.setAttribute("loginError", "登录存在错误");
			return "admin/login";
		}
		loginName =EncodingTool.encodeStr(loginName);
		String result =this.userServiceImpl.loginVerify(loginName, password);
		if(!result.equals("0")){
			request.setAttribute("loginError", "登录存在错误");
			return "admin/login";
		}
		
		LoginUser loginUser =this.userServiceImpl.findLoginUser(loginName);
		if(loginUser.getRole().getRoleId()!=1){
			//管理员roleid为1
			request.setAttribute("loginError", "没有权限");
			return "admin/login";
		}
		session.setAttribute("loginUser", loginUser);  //登录后创建session
		return "redirect:advice";
		
	}
	/**
	 * 功能：
	 * users列表
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "users", method = RequestMethod.GET)
	public String toUsers(HttpSession session) {
		List<LoginUser> users = this.userServiceImpl.findAllLoginUser();
		if (users == null) {
			session.setAttribute("allUsers", null);
		}
		session.setAttribute("allUsers", users);
		return "admin/users";
	}
	/**
	 * 功能：
	 * 用户详情
	 * @param loginUserId
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "user/{loginUserId}",method=RequestMethod.GET)
	public String toOneUser(@PathVariable("loginUserId") Integer loginUserId,HttpServletRequest request){
		LoginUser loginUser = this.userServiceImpl.fingOneLoginUserById(loginUserId);
		if(loginUser==null){
			//若是空，则跳转到建议的列表页
			return "redirect:/admin/users";
		}
		request.setAttribute("oneUser", loginUser);
		return "admin/user_detail";
	}
	
	/**
	 * @author Ray_1 用户退出
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("logOut")
	public String loginOut(HttpServletRequest request)
			throws ServletException, IOException {
		HttpSession session = request.getSession();// 防止创建Session
		if (session == null) {
			return "login";
		}
		session.invalidate();
		return "admin/login";
	}
	
}
