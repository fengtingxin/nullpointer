package com.exp.comment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.exp.bug.bugHateRecord.service.BugHateRecordServiceImpl;
import com.exp.bug.bugLikeRecord.service.BugLikeRecordServiceImpl;
import com.exp.bug.service.BugServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.Comment;
import com.exp.entity.LoginUser;
import com.exp.entity.UserInfo;
import com.exp.loginUser.service.LoginUserServiceImpl;
import com.exp.userinfo.controller.UserInfoController;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

@Controller
@RequestMapping("comment")
public class CommentContoller {
	@Resource
	private CommentServiceImpl commentServiceImpl;
	@Resource
	private BugServiceImpl bugServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@Resource
	private LoginUserServiceImpl loginUserServiceImpl;
	@Resource
	private BugLikeRecordServiceImpl bugLikeServiceRecordServiceImpl;
	@Resource
	private BugHateRecordServiceImpl bugHateServiceImpl;

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
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,HttpServletRequest request,HttpSession session) {
		LoginUser loginUser =(LoginUser) session.getAttribute("loginUser");
		if(loginUser==null){
			return "login";
		}
		UserInfo userInfo = loginUser.getUserInfo();

		// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
		long array[] = UserInfoController.differ(userInfo);
		request.setAttribute("day", array[0]);
		request.setAttribute("hour", array[1]);
		request.setAttribute("min", array[2]);
		request.setAttribute("second", array[3]);
		Page<Comment> page;
		page = this.commentServiceImpl.findCommentByTime(pageNum, 4, new Object[] { loginUser.getLoginUserId() });
		if(page==null){
			request.setAttribute("pageComment", null);
		}else{
			request.setAttribute("pageComment", page);
		}//pagecom改成pageComment
		return "home-comment";
	}
	/**
	 * @function 查找他的评论
	 * @author tangwenru
	 * @param pageNum
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("findCommentByTimeTwo")
	public String listTwo(@RequestParam(name = "loginName") String loginName,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,HttpServletRequest request) {
		if(loginName==null||loginName.equals("")){
			return "/404";
		}
		loginName =EncodingTool.encodeStr(loginName);
		LoginUser loginUser =this.loginUserServiceImpl.findByName(loginName);
		if(loginUser == null){
			return "/404";
		}
		UserInfo userInfo =loginUser.getUserInfo();
		

		// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
		long array[] = UserInfoController.differ(userInfo);
		request.setAttribute("day", array[0]);
		request.setAttribute("hour", array[1]);
		request.setAttribute("min", array[2]);
		request.setAttribute("second", array[3]);
		Page<Comment> page;
		page = this.commentServiceImpl.findCommentByTime(pageNum, 4, new Object[] { userInfo.getUserInfoId() });
		if(page==null){
			request.setAttribute("pageComment", null);
		}else{
			request.setAttribute("pageComment", page);
		}//pagecom改成pageComment
		return "hishome-comment";
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
			@RequestParam(name = "bugId", required = false) int bugId,HttpServletRequest request,HttpSession session) {
		LoginUser loginUser=(LoginUser) session.getAttribute("loginUser");
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
	public String getBug(Integer bugId, Integer userInfoId,HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		if(this.bugHateServiceImpl.findBugHateRecord(bugId, userInfoId)!=null){
			request.setAttribute("hateStatus", this.bugHateServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
		}
		if(this.bugLikeServiceRecordServiceImpl.findBugLikeRecord(bugId, userInfoId)!=null){
			request.setAttribute("likeStatus", this.bugLikeServiceRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus());
		}
		request.setAttribute("bug", bug);
		return "bug-detailed";
	}

}
