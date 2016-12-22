package com.exp.bug.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.bug.service.BugServiceImpl;
import com.exp.bug.bugHateRecord.service.BugHateRecordServiceImpl;
import com.exp.bug.bugLikeRecord.service.BugLikeRecordServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.BugHateRecord;
import com.exp.entity.BugLikeRecord;
import com.exp.entity.Comment;
import com.exp.entity.LoginUser;

import com.exp.entity.Question;
import com.exp.entity.Tag;
import com.exp.entity.UserInfo;
import com.exp.question.service.QuestionServiceImpl;

import com.exp.entity.R_Tag_UserInfo;
import com.exp.r_tag_userInfo.service.R_Tag_UserInfoServiceImpl;

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
	@Resource
	private QuestionServiceImpl questionServiceImpl;
	@Resource
	private R_Tag_UserInfoServiceImpl r_Tag_UserInfoServiceImpl;
	/**
	 * @author Ray_1功能：搜索下拉框
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
			// System.out.println("触发controller");
			if (page != null) {
				System.out.println("查询内容不为空");

				List<Bug> bugs = page.getList();
				for (Bug bug : bugs) {
					String bugtitle = bug.getBugTitle();
					System.out.println(bugtitle);
					if (bugtitle.length() > 100) {
						sb.append("<li class='showdetail'><a>" + bugtitle.substring(0, 100) + "</a></li>");
					} else {
						sb.append("<li><a>" + bugtitle + "</a></li>");
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
	 * @author zhang zhao lin 通过标签获取相应标签的bug
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "listadmin", method = RequestMethod.GET)
	public String listAdmin(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "tagName", defaultValue = "") String tagName, HttpServletRequest request,
			HttpSession session) {
		Page<Bug> page = new Page<Bug>();
		Tag tag = null;
		List<Tag> tagList = tagServiceImpl.findAllTag();
		try {
			tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取tag属性 -- ID
		if (!tagName.equals("")) {
			tag = this.tagServiceImpl.getOneTagByName(tagName);

			Set<Bug> hashset = tag.getBugs();
			List<Bug> bugList = new ArrayList<Bug>(0);
			Iterator<Bug> it = hashset.iterator();
			while (it.hasNext()) {
				Bug bug_next = it.next();
				if (bug_next.getUserInfo().getLoginUser().getRole().getRoleId() == 1) {
					bugList.add(bug_next);
				}
			}
			System.out.println(bugList.size());
			page.setCurrentPageNum(pageNum);
			page.setPageSize(8);
			page.setTotalCount(hashset.size());
			page.setList(bugList);
			session.setAttribute("tagName", tagName);
			session.setAttribute("tagList", tagList);
			session.setAttribute("adminBugNum", hashset.size());
			session.setAttribute("page", page);
			return "bug-list-admin";
		}

		page = this.bugServiceImpl.listAdminBug(pageNum, 8, null);
		session.setAttribute("tagName", "");
		session.setAttribute("adminBugNum", this.bugServiceImpl.getAdminBugNum());
		session.setAttribute("page", page);

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
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam,
			@RequestParam(name = "tagName", defaultValue = "") String tagName, HttpServletRequest request,
			HttpSession session) {
		Page<Bug> page = new Page<Bug>();
		Tag tag = null;
		List<Tag> tagList = tagServiceImpl.findAllTag();
		System.out.println("tagName:" + tagName);
		try {
			tagName = new String(tagName.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!tagName.equals("")) {
			tag = this.tagServiceImpl.getOneTagByName(tagName);

			Set<Bug> hashset = tag.getBugs();
			List<Bug> bugList = new ArrayList<Bug>(0);
			Iterator<Bug> it = hashset.iterator();
			while (it.hasNext()) {
				Bug bug_next = it.next();
				if (bug_next.getUserInfo().getLoginUser().getRole().getRoleId() == 2) {
					bugList.add(bug_next);
				}
			}
			page.setCurrentPageNum(pageNum);
			page.setPageSize(8);
			page.setTotalCount(hashset.size());
			page.setList(bugList);
			session.setAttribute("tagName", tagName);
			session.setAttribute("tagList", tagList);
			session.setAttribute("userBugNum", hashset.size());
			session.setAttribute("page", page);
			return "bug-list-user";
		}
		page = this.bugServiceImpl.listUserBug(pageNum, 8, null);
		request.setAttribute("userBugNum", this.bugServiceImpl.getUserBugNum());
		request.setAttribute("page", page);
		session.setAttribute("tagName", "");
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
	public String getBug(@RequestParam(name = "bugId") Integer bugId,HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		if(bug==null){
			return "/404"; //若是没有找到bug，也就是避免用户输入地址显示内容为空，跳转到404页面
		}
		LoginUser loginUser =(LoginUser) request.getSession().getAttribute("loginUser");
		Integer userInfoId;
		if(loginUser !=null){
			userInfoId=loginUser.getLoginUserId();
			if(this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId)!=null){
				request.setAttribute("likeStatus",
						this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus());
			}
			if(this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null){
				request.setAttribute("hateStatus",
						this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus());
			}
		}
		bug.setBugPageviews(bug.getBugPageviews()+1);
		this.bugServiceImpl.updateBug(bug);
		request.setAttribute("bug", bug);
		String bug_detailed_bell =(String) request.getSession().getAttribute("bug_detailed_bell");
		if (bug_detailed_bell != null) {
			request.getSession().removeAttribute("bug_detailed_bell");
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
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if (loginUser == null) {
			// 没有登录
			session.setAttribute("bug_detailed_bell", "请登录");
			return "redirect:findone?bugId=" + bugId;
		}
		if (content == null || content.trim().length() == 0) {
			// 内容为空
			session.setAttribute("bug_detailed_bell", "请输入内容");
			return "redirect:findone?bugId=" + bugId;
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
		//评论荣誉值+1
		loginUser.getUserInfo().setUserInfoHonorCount(loginUser.getUserInfo().getUserInfoHonorCount()+1);
		this.userInfoServiceImpl.updateUserInfo(loginUser.getUserInfo());
		// 增加社区属性
		Set<Tag> tags = bug.getTags();
		Iterator<Tag> iterator = tags.iterator();
		while (iterator.hasNext()) {
			Tag tag = iterator.next();
			if (this.r_Tag_UserInfoServiceImpl.findR_Tag_UserInfo(loginUser.getLoginUserId(), tag.getTagId()) == null) {
				R_Tag_UserInfo r = new R_Tag_UserInfo();
				r.setUserInfo(loginUser.getUserInfo());
				r.setTag(tag);
				r.setTagNumber(1);
				this.r_Tag_UserInfoServiceImpl.saveR_Tag_UserInfo(r);
			} else {
				R_Tag_UserInfo r = this.r_Tag_UserInfoServiceImpl.findR_Tag_UserInfo(loginUser.getLoginUserId(),
						tag.getTagId());
				r.setTagNumber(r.getTagNumber() + 1);
				this.r_Tag_UserInfoServiceImpl.updateR_Tag_UserInfo(r);
			}
		}
		return "redirect:findone?bugId=" + bugId;
	}

	/**
	 * @function 对bug进行点赞、取消赞
	 * @author tangwenru
	 * @author fengtingxin 完善此模块，使用ajax局部刷新
	 * @param userInfoId
	 * @param bugId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "like", method = RequestMethod.POST)
	@ResponseBody
	public String bugLike(@RequestParam(name = "bugId") Integer bugId, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		//获取bug的作者
		UserInfo author=bug.getUserInfo();
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		// 判断用户是否登录
		if (loginUser == null) {
			return "not ok";

		} else {// 用户已登录
			UserInfo userInfo = loginUser.getUserInfo();
			Integer userInfoId = userInfo.getUserInfoId();
			if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) == null) {
				// 未踩
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) == null) {
					// 未赞
					bug.setBugLikeNum(bug.getBugLikeNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugLikeRecord bugLikeRecord = new BugLikeRecord();
					bugLikeRecord.setBug(bug);
					bugLikeRecord.setUserInfo(userInfo);
					bugLikeRecord.setBugLikeStatus(1);
					bugLikeRecord.setBugLikeTime(new Date());
					this.bugLikeRecordServiceImpl.saveBugLikeRecord(bugLikeRecord);
					//bug被点赞，作者荣誉值+2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()+2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "likeOk";
				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 0) {
					// 赞失效
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(1);
					bug.setBugLikeNum(bug.getBugLikeNum() + 2);
					this.bugServiceImpl.updateBug(bug);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					//bug被点赞，作者荣誉值+2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()+2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "likeOk";
				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 1) {
					// 赞有效
					bug.setBugLikeNum(bug.getBugLikeNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(0);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					//bug被取消赞，作者荣誉值-2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()-2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "cancelLike";
				}
			}
			if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
					&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 0) {
				// 踩失效
				request.setAttribute("hateStatus", 0);
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) == null) {
					// 未赞
					bug.setBugLikeNum(bug.getBugLikeNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugLikeRecord bugLikeRecord = new BugLikeRecord();
					bugLikeRecord.setBug(bug);
					bugLikeRecord.setUserInfo(userInfo);
					bugLikeRecord.setBugLikeStatus(1);
					bugLikeRecord.setBugLikeTime(new Date());
					this.bugLikeRecordServiceImpl.saveBugLikeRecord(bugLikeRecord);
					//bug被点赞，作者荣誉值+2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()+2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "likeOk";
				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 0) {
					// 赞失效
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(1);
					bug.setBugLikeNum(bug.getBugLikeNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					//bug被点赞，作者荣誉值+2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()+2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "likeOk";
				}
				if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
						&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 1) {
					// 赞有效
					bug.setBugLikeNum(bug.getBugLikeNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					BugLikeRecord bugLikeRecord = this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId);
					bugLikeRecord.setBugLikeStatus(0);
					this.bugLikeRecordServiceImpl.updateBugLikeRecord(bugLikeRecord);
					//bug被取消赞，作者荣誉值-2
			        author.setUserInfoHonorCount(author.getUserInfoHonorCount()-2);
			        this.userInfoServiceImpl.updateUserInfo(author);
					return "cancelLike";
				}
				return "likeOk";

			} else {
				// 踩有效
				return "onHate";
			}

		}

	}

	/**
	 * @function @function 对bug进行踩、取消踩
	 * @author tangwenru
	 * @author fengtingxin 完善此模块，使用ajax局部刷新
	 * @param userInfoId
	 * @param bugId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "hate", method = RequestMethod.POST)
	@ResponseBody
	public String bugHate(@RequestParam(name = "bugId") Integer bugId, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		// 判断用户是否登录
		if (loginUser == null) {
			return "not ok";

		} else {// 用户已登录
			UserInfo userInfo = loginUser.getUserInfo();
			Integer userInfoId = userInfo.getUserInfoId();
			if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) == null) {
				// 赞的记录为空
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) == null) {
					// 踩的记录为空
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = new BugHateRecord();
					bugHateRecord.setBug(bug);
					bugHateRecord.setUserInfo(userInfo);
					bugHateRecord.setBugHateStatus(1);
					bugHateRecord.setBugHateTime(new Date());
					this.bugHateRecordServiceImpl.saveBugHateRecord(bugHateRecord);
					return "hateOk";
				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 0) {
					// 踩失效
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(1);
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					return "hateOk";
				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 1) {
					// 踩有效
					bug.setBugHateNum(bug.getBugHateNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(0);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					return "cancelHate";
				}
			}
			if (this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId) != null
					&& this.bugLikeRecordServiceImpl.findBugLikeRecord(bugId, userInfoId).getBugLikeStatus() == 0) {
				// 赞已失效
				request.setAttribute("likeStatus", 0);

				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) == null) {
					// 踩的记录为空
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = new BugHateRecord();
					bugHateRecord.setBug(bug);
					bugHateRecord.setUserInfo(userInfo);
					bugHateRecord.setBugHateStatus(1);
					bugHateRecord.setBugHateTime(new Date());
					this.bugHateRecordServiceImpl.saveBugHateRecord(bugHateRecord);
					return "hateOk";
				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 0) {
					// 踩失效
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(1);
					bug.setBugHateNum(bug.getBugHateNum() + 1);
					this.bugServiceImpl.updateBug(bug);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					return "hateOk";
				}
				if (this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId) != null
						&& this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId).getBugHateStatus() == 1) {
					// 踩有效
					bug.setBugHateNum(bug.getBugHateNum() - 1);
					this.bugServiceImpl.updateBug(bug);
					BugHateRecord bugHateRecord = this.bugHateRecordServiceImpl.findBugHateRecord(bugId, userInfoId);
					bugHateRecord.setBugHateStatus(0);
					this.bugHateRecordServiceImpl.updateBugHateRecord(bugHateRecord);
					return "cancelHate";
				}
				return "hateOk";
			} else {
				// 赞有效
				return "onLike";
			}

		}

	}

	/**
	 * 功能： 跳转到用户分享的列表页
	 * 
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bugShareByUser", method = RequestMethod.GET)
	public String toBugUserShare(HttpServletRequest request) {
		List<Tag> tags = this.tagServiceImpl.findAllTag();
		List<Question> questionList = this.questionServiceImpl.findQuestionRecommend().subList(0, 6);
		request.setAttribute("questionList", questionList);
		request.setAttribute("tags", tags);
		return "bug_user_share";
	}

	/**
	 * 功能： 用户分享bug 使用ajax提交，返回字符串 1代表有错误 ok代表成功 not ok表示信息错误
	 * 
	 * @param bugTitle
	 * @param bugReason
	 * @param bugMethod
	 * @param tags
	 * @param bugDescribe
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bugShareByUser", method = RequestMethod.POST)
	@ResponseBody
	public String publishBugByUser(@RequestParam(name = "bugTitle") String bugTitle,
			@RequestParam(name = "bugReason") String bugReason, @RequestParam(name = "bugMethod") String bugMethod,
			@RequestParam(name = "tags") String tags, @RequestParam(name = "bugDescribe") String bugDescribe,
			HttpSession session) {
		if (tags == null || bugTitle == null || bugReason == null || bugMethod == null || bugDescribe == null) {
			return "not ok";
		}

		String[] tagArray = tags.split(","); // 将获取的tag分拆
		Bug bug = new Bug();
		bug.setBugTitle(bugTitle);
		bug.setBugDescribe(bugDescribe);
		bug.setBugHateNum(0);
		bug.setBugLikeNum(0);
		bug.setBugMethod(bugMethod);
		bug.setBugPageviews(0);
		bug.setBugAudited(false);
		bug.setBugPublishTime(new Date());
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		bug.setUserInfo(loginUser.getUserInfo());
		bug.setBugReason(bugReason);
		Set<Tag> bugTags = new HashSet<Tag>();

		try {
			for (int i = 0; i < tagArray.length; i++) {
				bugTags.add(this.tagServiceImpl.getOneTagByName(tagArray[i]));
			}
			bug.setTags(bugTags);
			this.bugServiceImpl.saveOneBug(bug);
		} catch (Exception e) {
			// TODO: handle exception
			return "1";
		}
		// 增加社区属性
		Set<Tag> tagss = bug.getTags();
		Iterator<Tag> iterator = tagss.iterator();
		while (iterator.hasNext()) {
			Tag tag = iterator.next();
			if (this.r_Tag_UserInfoServiceImpl.findR_Tag_UserInfo(loginUser.getLoginUserId(), tag.getTagId()) == null) {
				R_Tag_UserInfo r = new R_Tag_UserInfo();
				r.setUserInfo(loginUser.getUserInfo());
				r.setTag(tag);
				r.setTagNumber(1);
				this.r_Tag_UserInfoServiceImpl.saveR_Tag_UserInfo(r);
			} else {
				R_Tag_UserInfo r = this.r_Tag_UserInfoServiceImpl.findR_Tag_UserInfo(loginUser.getLoginUserId(),
						tag.getTagId());
				r.setTagNumber(r.getTagNumber() + 1);
				this.r_Tag_UserInfoServiceImpl.updateR_Tag_UserInfo(r);
			}
		}
		return "ok";
	}
}
