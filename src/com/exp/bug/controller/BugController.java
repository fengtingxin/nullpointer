package com.exp.bug.controller;

import java.io.UnsupportedEncodingException;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.bug.service.BugServiceImpl;
import com.exp.entity.Bug;

import com.exp.entity.Tag;


import com.exp.tag.service.TagServiceImpl;
import com.framework.Page;

@Controller
@RequestMapping("bug")
public class BugController {
	@Resource
	private BugServiceImpl bugServiceImpl;
	@Resource
	private TagServiceImpl tagServiceImpl;
	/**
	 * @function 分页查询官方发布的bug,每页8个
	 * @author tangwenru
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="listadmin",method=RequestMethod.GET)
	public String listAdmin(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam, HttpServletRequest request,
			Model model,HttpSession session) {
		Page<Bug> page;
		System.out.println(searchParam);
		String temp = searchParam;
		try {
			temp = new String(temp.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchParam = temp;
		if (searchParam == null || "".equals(searchParam)) {
			page = this.bugServiceImpl.listAdminBug(pageNum, 8, null);
		} else {
			page = this.bugServiceImpl.listAdminBug(pageNum, 8, new Object[] { searchParam });
		}
		request.setAttribute("adminBugNum", this.bugServiceImpl.getAdminBugNum());
		request.setAttribute("page", page);
//		request.setAttribute("searchParam", searchParam);
		List<Tag> tagList = tagServiceImpl.findAllTag();
		session.setAttribute("tagList", tagList);
		return "bug-list-admin";

	}
	/**
	 * @function 分页查询用户发布的bug,每页8个
	 * @author tangwenru
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="listuser",method=RequestMethod.GET)
	public String listUser(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam, HttpServletRequest request,
			Model model,HttpSession session) {
		Page<Bug> page;
		System.out.println(searchParam);
		String temp = searchParam;
		try {
			temp = new String(temp.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchParam = temp;
		if (searchParam == null || "".equals(searchParam)) {
			page = this.bugServiceImpl.listUserBug(pageNum, 8, null);
		} else {
			page = this.bugServiceImpl.listUserBug(pageNum, 8, new Object[] { searchParam });
		}
		request.setAttribute("userBugNum", this.bugServiceImpl.getUserBugNum());
		request.setAttribute("page", page);
		request.setAttribute("searchParam", searchParam);
		List<Tag> tagList = tagServiceImpl.findAllTag();
		session.setAttribute("tagList", tagList);
		return "bug-list-user";

	}
	@RequestMapping(value="findone",method=RequestMethod.GET)
	public String getBug(Integer bugId,HttpServletRequest request){
		Bug bug=this.bugServiceImpl.getBug(bugId);
		request.setAttribute("bug", bug);
		return "bug-detailed";
	}
	
}
