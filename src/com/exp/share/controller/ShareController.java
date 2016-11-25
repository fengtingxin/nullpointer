package com.exp.share.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exp.entity.Bug;
import com.exp.share.service.ShareServiceImpl;
import com.framework.Page;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareServiceImpl Bugserviceimpl;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所分享的问题
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("/shareByTime")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
		Page<Bug> pages;
		int userinfoId = 1;
		pages = this.Bugserviceimpl.findBugByTime(pageNum, 4, new Object[] {userinfoId });
		request.setAttribute("pagesShare", pages);
		return "home-share";
	}
}
