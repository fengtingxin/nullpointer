package com.exp.bug.controller;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
    /**
     * @function 对bug进行点赞、取消赞
     * @author tangwenru
     * @param userInfoId
     * @param bugId
     * @param request
     * @return
     */
	@RequestMapping(value = "like", method = RequestMethod.GET)
	public String bugLike(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "bugId") Integer bugId, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("bug", bug);
			request.setAttribute("remindMsg", "请登录！");
			return "bug-detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			// bug被踩记录为空，或者bug被踩状态失效
			if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) == null
					|| this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 0) {
				// bug已经被点赞
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 1) {
					bug.setBugLikeNum(bug.getBugLikeNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					System.out.println("196hang controller");
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(0);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					request.setAttribute("likeStatus", 0);
					System.out.println("执行");
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					request.setAttribute("hateStatus", this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
					return "bug-detailed";

				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) == null) {
					// 该用户没有点赞此bug
					bug.setBugLikeNum(bug.getBugLikeNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugLikeRecord bugLikeRecord = new BugLikeRecord();
					bugLikeRecord.setBug(bug);
					bugLikeRecord.setUserInfo(userInfo);
					bugLikeRecord.setBugLikeStatus(1);
					bugLikeRecord.setBuglikeTime(new Date());
					this.bugLikeRecordServiceImpl.saveBugLikeRecord(bugLikeRecord);
					request.setAttribute("likeStatus",
							this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus());
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					request.setAttribute("hateStatus", this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
					return "bug-detailed";

				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 0) {
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(1);
					bug.setBugLikeNum(bug.getBugLikeNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					request.setAttribute("likeStatus",
							this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus());
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					request.setAttribute("hateStatus", this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
					return "bug-detailed";
				}

			} else {
				request.setAttribute("hateStatus", this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("bug", bug);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消踩后才可以点赞哦！");
				return "bug-detailed";

			}

			return "bug-detailed";

		}

	}
	
	@RequestMapping(value = "hate", method = RequestMethod.GET)
	public String bugHate(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "bugId") Integer bugId, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("bug", bug);
			request.setAttribute("remindMsg", "请登录！");
			return "bug-detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			// bug被赞记录为空，或者bug被赞状态失效
			if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) == null
					|| this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 0) {
				// bug已经被踩
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 1) {
					bug.setBugHateNum(bug.getBugHateNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(0);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					request.setAttribute("hateStatus", 0);
					request.setAttribute("likeStatus",this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() );
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					return "bug-detailed";

				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) == null) {
					// 该用户没有踩此bug
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = new BugHateRecord();
					bugHateRecord.setBug(bug);
					bugHateRecord.setUserInfo(userInfo);
					bugHateRecord.setBugHateStatus(1);
					bugHateRecord.setBugHateTime(new Date());
					this.bugHateRecordServiceImpl.saveBugHateRecord(bugHateRecord);
					request.setAttribute("hateStatus",
							this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					request.setAttribute("likeStatus",this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() );
					return "bug-detailed";

				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 0) {
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(1);
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					request.setAttribute("hateStatus",
							this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("bug", bug);
					request.setAttribute("likeStatus",this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() );
					return "bug-detailed";
				}

			} else {
				request.setAttribute("likeStatus",this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() );
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("bug", bug);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消赞后才可以踩哦！");
				return "bug-detailed";

			}

			return "bug-detailed";

		}

	}

}
