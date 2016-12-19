package com.exp.loginUser.service;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.LoginUser;
import com.exp.loginUser.dao.LoginUserDaoImpl;
import com.framework.EmailVo;

@Service
@Transactional(readOnly = true)
public class LoginUserServiceImpl {

	@Resource
	private LoginUserDaoImpl loginUserDaoImpl;

	/**
	 * 功能： 通过参数name,email,password,将数据插入数据库loginUser,UserInfo中 同时荣誉值自动增加到10
	 * 我认为数据库中应该增加一个字段，应该是被激活需要用到的字段，判断是否被激活
	 * 
	 * @param loginUser
	 * @return
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public String register(LoginUser loginUser,String serverNameAndPort) {
		// 处理业务逻辑
		// 1.判断是否存在这个email
		if (this.loginUserDaoImpl.findByEmail(loginUser.getLoginEmail()) != null) {
			// 存在这样的数据
			return "4"; // 邮箱已经存在
		}
		// 2.判断是否存在这个用户名
		if (this.loginUserDaoImpl.findByLoginName(loginUser.getLoginName()) != null) {
			// 存在这样的数据
			return "3"; // 用户名已经存在
		}
		// 3.若是数据库中不存在这样的数据，那么开始注册！
		// 1.增加荣誉值为10
		loginUser.getUserInfo().setUserInfoHonorCount(10);
		String result = this.loginUserDaoImpl.register(loginUser);
		if (result == "0") {
			// 发送邮件
			EmailVo emailVo = new EmailVo();
			emailVo.setReceivers(new String[] { loginUser.getLoginEmail() });
			emailVo.setSender("m15315715815@163.com");
			emailVo.setSubject("欢迎注册nullpointer");
			// 邮件内容!
			String activeURL = "http://"+serverNameAndPort+"/nullpointer/loginUser/activeLoginUser?loginName="
					+ loginUser.getLoginName();
			String emailContent = "<html><head><title>欢迎注册nullpointer</title></head><body>"
					+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>" + "<tr>"
					+ "<td style='padding: 10px 0 30px 0;'>"
					+ "<table align='center' border='0' cellpadding='0' cellspacing='0' width='600' style='border: 1px solid #cccccc; border-collapse: collapse;'>"
					+ "<tr>"
					+ "<td align='center' bgcolor='#70bbd9' style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;'>"
					+ "<img src='http://www.fengtingxin.cn/images/black_logo.png' alt='nullpointer Logo' width='300' height='230' style='display: block;' />"
					+ "</td>" + "</tr>" + "<tr>" + "<td bgcolor='#ffffff' style='padding: 40px 30px 40px 30px;'>"
					+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>" + "<tr>"
					+ "<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'>"
					+ "<b>Welcome to nullpointer!</b>" + "</td>" + "</tr>" + "<tr>"
					+ "<td style='padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;'>"
					+ "请点击以下链接完成注册：<b><br/><a href='" + activeURL
					+ "'>http://localhost:8080/nullpointer/loginUser/activeLoginUser?loginActive=true（右键在新标签页中打开）</a></b>"
					+ "</td></tr></table></td></tr><tr>"
					+ "<td bgcolor='#ee4c50' style='padding: 30px 30px 30px 30px;'>"
					+ "<table border='0' cellpadding='0' cellspacing='0' width='100%'>" + "<tr>"
					+ "<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>"
					+ "&reg; nullpointer MadeBy EXP 项目开发小组<br/>"
					+ "<a href='#' style='color: #ffffff;'><font color='#ffffff'>hei boy!</font></a>&nbsp;Welcome you again!"
					+ "</td></tr></table></td></tr></table></td></tr></table>" + "</body></html>";
			emailVo.setEmailContent(emailContent);
			try {
				emailVo.sendOneEmail(emailVo);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "1";
			}
			// System.out.println("email is success to ");
		}
		return result;
	}

	/**
	 * 功能： 通过name得到loginUser
	 * 
	 * @param loginName
	 * @return LoginUser 实体
	 * @author fengtingxin
	 */
	public LoginUser findByName(String loginName) {
		return this.loginUserDaoImpl.findByLoginName(loginName);
	}

	@Transactional(readOnly = false)
	public void updateLoginUser(LoginUser loginUser) {
		this.loginUserDaoImpl.updateLoginUser(loginUser);
	}

	/**
	 * 功能： 1.判断登录名称是否为email/name 2.判断是否激活
	 * 
	 * @param loginName
	 * @param password
	 * @author fengtingxin
	 * @return 0 表示登录成功 -1 表示验证码错误 1 表示数据连接错误 2 表示参数传递错误 14 表示用户名不存在 16 表示尚未激活
	 *         19 表示密码错误
	 * 
	 */
	public String loginVerify(String loginName, String password) {
		// 判断登录名称是否为email
		LoginUser t1 = this.loginUserDaoImpl.findByEmail(loginName);
		if (t1 == null) {
			t1 = this.loginUserDaoImpl.findByLoginName(loginName);
			// 判断登录名称是否为name
			if (t1 == null) {
				return "14"; // 用户名不存在返回14
			}
		}
		//// 判断密码是否正确
		if (!t1.getLoginPassword().equals(password)) {
			return "15";
		}
		// 判断是否激活
		if (t1.isLoginActive() == false) {
			return "16";
		}
		// 登录成功！
		return "0";

	}

	/**
	 * @author zhang zhao lin
	 * @param loginName
	 * @param password
	 * @return 返回用户信息 Loginuser
	 */
	public LoginUser findLoginUser(String loginName) {

	    LoginUser t1=this.loginUserDaoImpl.findByEmail(loginName);
	    if(t1==null){
	    	t1=this.loginUserDaoImpl.findByLoginName(loginName);
	    }
	    return t1;
	}

	/**
	 * 功能： 得到所有的loginUser
	 * 
	 * @return
	 * @author fengtingxin
	 * 
	 */
	public List<LoginUser> findAllLoginUser() {
		return this.loginUserDaoImpl.findAllUsers();
	}

	public LoginUser fingOneLoginUserById(Integer loginUserId) {
		try {
			return this.loginUserDaoImpl.get(loginUserId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("get a loginUser by Id error");
			return null;
		}

	}
}
