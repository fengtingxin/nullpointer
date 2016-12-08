package com.exp.share.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exp.entity.Bug;
import com.exp.entity.LoginUser;
import com.exp.share.service.ShareServiceImpl;
import com.framework.Page;
//汤文茹删除了不必要的导入的包
@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareServiceImpl shareServiceImpl;//汤文茹修改了此处参数，由Bugserviceimpl改成shareServiceImpl

	/**
	 * @author Ray_1 按时间顺序分页查询个人所分享的问题
	 * @author tangwenru 增加了参数userInfoId，动态获取当前用户
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("/shareByTime")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request,HttpSession session) {
		LoginUser loginUser =(LoginUser) session.getAttribute("loginUser");
		Page<Bug> pages;
		pages = this.shareServiceImpl.findBugByTime(pageNum, 4, new Object[] {loginUser.getLoginUserId() });
		if(pages==null){
			request.setAttribute("pagesShare", null);
		}else{
			request.setAttribute("pagesShare", pages);
		}
		return "home-share";
	}
}
