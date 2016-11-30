package com.exp.comment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.bug.service.BugServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.Comment;
import com.framework.Page;

@Controller
@RequestMapping("comment")
public class CommentContoller {
	@Resource
	private CommentServiceImpl commentServiceImpl;
	@Resource
	private BugServiceImpl bugServiceImpl;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题 个人主页部分
	 * @param pageNum
	 *            一页有多少
	 * @author tangwenru 新增参数userInfoId 动态获取此时用户的id
	 * @param userInfoId
	 * @param request
	 * @return
	 */
	@RequestMapping("findCommentByTime")
	public String list(@RequestParam(name="userInfoId",required=false) Integer userInfoId,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpSession session) {
		Page<Comment> page;
		page = this.commentServiceImpl.findCommentByTime(pageNum, 4, new Object[] { userInfoId });
		session.setAttribute("pageComment", page);//pagecom改成pageComment
		return "home-comment";
	}

	/**
	 * @function 根据commentId删除评论
	 * @author tangwenru
	 * @param commentId
	 * @param bugId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("commentId") int commentId,
			@RequestParam(name = "bugId", required = false) int bugId, HttpServletRequest request) {
		this.commentServiceImpl.deleteComment(commentId);
		return "redirect:findone?bugId=" + bugId;
	}

	/**
	 * @function 删除一条评论后调用的方法
	 * @author tangwenru
	 * @param bugId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "findone", method = RequestMethod.GET)
	public String getBug(Integer bugId, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		request.setAttribute("bug", bug);
		return "bug-detailed";
	}

}
