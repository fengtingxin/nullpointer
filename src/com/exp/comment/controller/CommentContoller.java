package com.exp.comment.controller;

import java.util.Date;

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
import com.exp.comment.commentHateRecord.service.CommentHateRecordServiceImpl;
import com.exp.comment.commentLikeRecord.service.CommentLikeRecordServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.BugHateRecord;
import com.exp.entity.BugLikeRecord;
import com.exp.entity.Comment;
import com.exp.entity.CommentHateRecord;
import com.exp.entity.CommentLikeRecord;
import com.exp.entity.LoginUser;
import com.exp.entity.UserInfo;
import com.exp.userinfo.service.UserInfoServiceImpl;
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
	private CommentLikeRecordServiceImpl commentLikeRecordServiceImpl;
	@Resource
	private CommentHateRecordServiceImpl commentHateRecordServiceImpl;
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
			@RequestParam(name = "bugId", required = false) int bugId,HttpServletRequest request,HttpSession session) {
		LoginUser loginUser=(LoginUser) session.getAttribute("loginUser");
		this.commentServiceImpl.deleteComment(commentId);
	
		return "redirect:findone?bugId=" + bugId+"&userInfoId="+loginUser.getLoginUserId();
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
	/**
	 * @function 对评论进行赞、取消赞
	 * @author tangwenru
	 * @param userInfoId
	 * @param commentId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "like", method = RequestMethod.GET)
	public String bugLike(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "commentId") Integer commentId, HttpServletRequest request) {
		Comment comment = this.commentServiceImpl.getComment(commentId);
		Bug bug=comment.getBug();
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("comment", comment);
			request.setAttribute("bug", bug);
			request.setAttribute("remindMsg", "请登录！");
			return "bug-detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			request.setAttribute("bug", bug);
			if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) == null) {
				// 未踩
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) == null) {
					// 未赞
					comment.setCommentLikeNum(comment.getCommentLikeNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					CommentLikeRecord commentLikeRecord = new CommentLikeRecord();
					commentLikeRecord.setComment(comment);
					commentLikeRecord.setUserInfo(userInfo);
					commentLikeRecord.setCommentLikeStatus(1);
					commentLikeRecord.setCommentLikeTime(new Date());
					this.commentLikeRecordServiceImpl.saveCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
						&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 0) {
					// 赞失效
					CommentLikeRecord commentLikeRecord = this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId);
					commentLikeRecord.setCommentLikeStatus(1);
					comment.setCommentLikeNum(comment.getCommentLikeNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					this.commentLikeRecordServiceImpl.updateCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
						&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 1) {
					// 赞有效
					comment.setCommentLikeNum(comment.getCommentLikeNum() - 1);
					this.commentServiceImpl.updateComment(comment);
					CommentLikeRecord commentLikeRecord = this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId);
					commentLikeRecord.setCommentLikeStatus(0);
					this.commentLikeRecordServiceImpl.updateCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
			}
			if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
					&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 0) {
				// 踩失效
				request.setAttribute("answerHateStatus", 0);
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) == null) {
					// 未赞
					comment.setCommentLikeNum(comment.getCommentLikeNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					CommentLikeRecord commentLikeRecord = new CommentLikeRecord();
					commentLikeRecord.setComment(comment);
					commentLikeRecord.setUserInfo(userInfo);
					commentLikeRecord.setCommentLikeStatus(1);
					commentLikeRecord.setCommentLikeTime(new Date());
					this.commentLikeRecordServiceImpl.saveCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
						&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 0) {
					// 赞失效
					CommentLikeRecord commentLikeRecord = this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId);
					commentLikeRecord.setCommentLikeStatus(1);
					comment.setCommentLikeNum(comment.getCommentLikeNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					this.commentLikeRecordServiceImpl.updateCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
						&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 1) {
					// 赞有效
					comment.setCommentLikeNum(comment.getCommentLikeNum() - 1);
					this.commentServiceImpl.updateComment(comment);
					CommentLikeRecord commentLikeRecord = this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId);
					commentLikeRecord.setCommentLikeStatus(0);
					this.commentLikeRecordServiceImpl.updateCommentLikeRecord(commentLikeRecord);
					request.setAttribute("answerLikeStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				return "bug-detailed";

			} else {
				// 踩有效
				if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
						&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 0) {
					request.setAttribute("likeStatus", 0);
				}
				request.setAttribute("answerHateStatus", 1);
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("comment", comment);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消踩后才可以赞哦！");
				return "bug-detailed";
			}

		}

	}
	/**
	 * @function 对评论进行踩，取消踩
	 * @author tangwenru
	 * @param userInfoId
	 * @param commentId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "hate", method = RequestMethod.GET)
	public String bugHate(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "commentId") Integer commentId, HttpServletRequest request) {
		Comment comment = this.commentServiceImpl.getComment(commentId);
		Bug bug=comment.getBug();
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("comment", comment);
			request.setAttribute("bug", bug);
			request.setAttribute("remindMsg", "请登录！");
			return "bug-detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			request.setAttribute("bug", bug);
			if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) == null) {
				// 赞的记录为空
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) == null) {
					// 踩的记录为空
					comment.setCommentHateNum(comment.getCommentHateNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					CommentHateRecord commentHateRecord = new CommentHateRecord();
					commentHateRecord.setComment(comment);
					commentHateRecord.setUserInfo(userInfo);
					commentHateRecord.setCommentHateStatus(1);
					commentHateRecord.setCommentHateTime(new Date());
					this.commentHateRecordServiceImpl.saveCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
						&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 0) {
					// 踩失效
					CommentHateRecord commentHateRecord = this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId);
					commentHateRecord.setCommentHateStatus(1);
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					this.commentHateRecordServiceImpl.updateCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
						&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 1) {
					// 踩有效
					comment.setCommentHateNum(comment.getCommentHateNum() - 1);
					this.commentServiceImpl.updateComment(comment);
					CommentHateRecord commentHateRecord = this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId);
					commentHateRecord.setCommentHateStatus(0);
					this.commentHateRecordServiceImpl.updateCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
			}
			if (this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId) != null
					&& this.commentLikeRecordServiceImpl.findCommentLikeRecord(commentId, userInfoId).getCommentLikeStatus() == 0) {
				// 赞已失效
				request.setAttribute("likeStatus", 0);

				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) == null) {
					// 踩的记录为空
					comment.setCommentHateNum(comment.getCommentHateNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					CommentHateRecord commentHateRecord = new CommentHateRecord();
					commentHateRecord.setComment(comment);
					commentHateRecord.setUserInfo(userInfo);
					commentHateRecord.setCommentHateStatus(1);
					commentHateRecord.setCommentHateTime(new Date());
					this.commentHateRecordServiceImpl.saveCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
						&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 0) {
					// 踩失效
					CommentHateRecord commentHateRecord = this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId);
					commentHateRecord.setCommentHateStatus(1);
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.commentServiceImpl.updateComment(comment);
					this.commentHateRecordServiceImpl.updateCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
						&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 1) {
					// 踩有效
					comment.setCommentHateNum(comment.getCommentHateNum() - 1);
					this.commentServiceImpl.updateComment(comment);
					CommentHateRecord commentHateRecord = this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId);
					commentHateRecord.setCommentHateStatus(0);
					this.commentHateRecordServiceImpl.updateCommentHateRecord(commentHateRecord);
					request.setAttribute("hateStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("comment", comment);
					return "bug-detailed";
				}
				return "bug-detailed";
			} else {
				//赞有效
				if (this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId) != null
						&& this.commentHateRecordServiceImpl.findCommentHateRecord(commentId, userInfoId).getCommentHateStatus() == 0) {
					request.setAttribute("hateStatus", 0);
				}
				request.setAttribute("likeStatus", 1);
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("comment", comment);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消赞后才可以踩哦！");
				return "bug-detailed";
			}

		}

	}


}
