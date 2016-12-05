package com.exp.bug.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.bug.service.BugServiceImpl;
import com.exp.bugHateRecord.service.BugHateRecordServiceImpl;
import com.exp.bugLikeRecord.service.BugLikeRecordServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.BugHateRecord;
import com.exp.entity.BugLikeRecord;
import com.exp.entity.Comment;
import com.exp.entity.LoginUser;
import com.exp.entity.Tag;
import com.exp.entity.UserInfo;
import com.exp.tag.service.TagServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

@Controller
@RequestMapping("bug")
public class BugController {
	@Resource
	private BugServiceImpl bugServiceImpl;
	@Resource
	private TagServiceImpl tagServiceImpl;
	@Resource
	private CommentServiceImpl commentServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@Resource
	private BugLikeRecordServiceImpl bugLikeRecordServiceImpl;
	@Resource
	private BugHateRecordServiceImpl bugHateRecordServiceImpl;

	/**
	 * @author Ray_1功能：搜索下来框
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @param response
	 */

	@RequestMapping(value = "/findBugByValue", method = RequestMethod.POST)
	public void list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "title", defaultValue = "") String searchParam, HttpServletRequest request,
			Model model, HttpServletResponse response) {
		
			System.out.println("searchParam为" + searchParam);
		try {
			// 这里不设置编码会有乱码
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		Page<Bug> page = null;
		searchParam = EncodingTool.encodeStr(searchParam);
		// System.out.println("searchParam"+searchParam);
		if (searchParam == null || "".equals(searchParam)) {
			page = this.bugServiceImpl.findBugByValue(pageNum, 4, null);
		} else {
			page = this.bugServiceImpl.findBugByValue(pageNum, 4, new Object[] { searchParam });
		}
		request.setAttribute("searchParam", searchParam);
		try {
			StringBuilder sb = new StringBuilder();
			//System.out.println("触发controller");
			if (page != null) {
				System.out.println("查询内容不为空");
				
				List<Bug> bugs = page.getList();
				for (Bug bug : bugs) {
					String bugtitle = bug.getBugTitle();
					System.out.println(bugtitle);
					if (bugtitle.length() > 100){
						sb.append("<li>" + bugtitle.substring(0, 100) + "</li>");
						}
					else{
						sb.append("<li>" + bugtitle + "</li>");
					}
				}
				
				System.out.println(sb.toString());
				response.getWriter().write(sb.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @function 分页查询官方发布的bug,每页8个
	 * @author tangwenru
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "listadmin", method = RequestMethod.GET)
	public String listAdmin(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request,
			HttpSession session) {
		Page<Bug> page;
		page = this.bugServiceImpl.listAdminBug(pageNum, 8, null);
		session.setAttribute("adminBugNum", this.bugServiceImpl.getAdminBugNum());
		session.setAttribute("page", page);
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
	@RequestMapping(value = "listuser", method = RequestMethod.GET)
	public String listUser(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam, HttpServletRequest request,
			HttpSession session) {
		Page<Bug> page;
		page = this.bugServiceImpl.listUserBug(pageNum, 8, null);
		request.setAttribute("userBugNum", this.bugServiceImpl.getUserBugNum());
		request.setAttribute("page", page);
		List<Tag> tagList = tagServiceImpl.findAllTag();
		session.setAttribute("tagList", tagList);
		return "bug-list-user";

	}

	/**
	 * @function 根据bugId查询到单个Bug,保存Bug对象
	 * @author tangwenru
	 * @param bugId
	 * @param bug_detailed_bell
	 * @param request
	 * @return bug-detailed.jsp页面
	 */
	@RequestMapping(value = "findone", method = RequestMethod.GET)

	public String getBug(@RequestParam(name = "bugId") Integer bugId,
			@RequestParam(name = "userInfoId", required = false) Integer userInfoId,
			@RequestParam(name = "bug_detailed_bell", required = false) String bug_detailed_bell,
			HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		if (userInfoId != null & this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null) {
			request.setAttribute("likeStatus",
					this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus());
		}
		if (userInfoId != null & this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null) {
			request.setAttribute("hateStatus",
					this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
		}
		request.setAttribute("bug", bug);
		if (bug_detailed_bell != null) {
			if (bug_detailed_bell.substring(bug_detailed_bell.length() - 1).equals("1")) {
				bug_detailed_bell = "请输入内容";
			}
			if (bug_detailed_bell.substring(bug_detailed_bell.length() - 1).equals("2")) {
				bug_detailed_bell = "请登录";
			}
			request.setAttribute("bug_detailed_bell", bug_detailed_bell);
			request.setAttribute("bug_detailed_judge", "ok");
		}

		return "bug-detailed";
	}

	/**
	 * 提交评论 条件： 登录 非空 一级评论 二级评论
	 * 
	 * @param bugId
	 * @param content
	 * @param commentId
	 * @param request
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "{bugId}", method = RequestMethod.POST)
	public String submitComment(@PathVariable("bugId") Integer bugId, @RequestParam(name = "content") String content,
			@RequestParam(name = "commentId", required = false) Integer commentId, HttpServletRequest request,
			HttpSession session) {
		if (content == null || content.trim().length() == 0) {
			// 内容为空
			return "redirect:findone?bugId=" + bugId + "&bug_detailed_bell=" + 1;
		}
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if (loginUser == null) {
			// 没有登录
			return "redirect:findone?bugId=" + bugId + "&bug_detailed_bell=" + 2;
		}
		// code转换
		content = EncodingTool.encodeStr(content);
		Bug bug = this.bugServiceImpl.getBug(bugId);
		if (commentId == null) {
			// 添加一条父级commet
			if (bug == null) {
				return "redirect:listadmin"; // 找不到跳转到list
			}
			Comment comment = new Comment();
			comment.setBug(bug);
			comment.setCommentContent(content);
			comment.setCommentPublishTime(new Date());
			comment.setUserInfo(loginUser.getUserInfo());
			bug.getComments().add(comment);
			this.commentServiceImpl.saveComment(comment);
		} else {
			Comment comment = new Comment();
			comment.setBug(bug);
			comment.setCommentContent(content);
			comment.setCommentPublishTime(new Date());
			comment.setParentComment(this.commentServiceImpl.getCommentById(commentId));
			comment.setUserInfo(loginUser.getUserInfo());
			this.commentServiceImpl.saveComment(comment);
		}
		return "redirect:findone?bugId=" + bugId;
	}
}
