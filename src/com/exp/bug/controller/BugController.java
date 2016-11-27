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
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.Comment;
import com.exp.entity.LoginUser;
import com.exp.entity.Tag;

import com.exp.tag.service.TagServiceImpl;
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
			@RequestParam(name = "searchParam", defaultValue = "") String searchParam, HttpServletRequest request,HttpSession session) {
		Page<Bug> page;
		page = this.bugServiceImpl.listUserBug(pageNum, 8, null);
		request.setAttribute("userBugNum", this.bugServiceImpl.getUserBugNum());
		request.setAttribute("page", page);
		List<Tag> tagList = tagServiceImpl.findAllTag();
		session.setAttribute("tagList", tagList);
		return "bug-list-user";

	}

	@RequestMapping(value = "findone", method = RequestMethod.GET)
	public String getBug(Integer bugId,@RequestParam(name="bug_detailed_bell",required=false) String bug_detailed_bell, HttpServletRequest request) {
		Bug bug = this.bugServiceImpl.getBug(bugId);
		request.setAttribute("bug", bug);
		if(bug_detailed_bell!=null){
			if(bug_detailed_bell.substring(bug_detailed_bell.length()-1).equals("1")){
				bug_detailed_bell="请输入内容";
			}
			if(bug_detailed_bell.substring(bug_detailed_bell.length()-1).equals("2")){
				bug_detailed_bell="请登录";
			}
			request.setAttribute("bug_detailed_bell", bug_detailed_bell);
			request.setAttribute("bug_detailed_judge", "ok");
		}
		
		return "bug-detailed";
	}

	@RequestMapping(value = "{bugId}", method = RequestMethod.POST)
	public String submitComment(@PathVariable("bugId") Integer bugId, @RequestParam(name = "content") String content,
			@RequestParam(name = "commentId", required = false) Integer commentId,HttpServletRequest request,HttpSession session) {
		if(content==null||content.trim().length()==0){
			//内容为空
			return "redirect:findone?bugId=" + bugId+"&bug_detailed_bell="+1;
		}
		LoginUser loginUser=(LoginUser) session.getAttribute("loginUser");
		if(loginUser==null){
			//没有登录
			return "redirect:findone?bugId=" + bugId+"&bug_detailed_bell="+2;
		}
		//code转换
		content=EncodingTool.encodeStr(content);
		Bug bug =this.bugServiceImpl.getBug(bugId);
		if(commentId==null){
			//添加一条父级commet
			if(bug==null){
				return "redirect:listadmin"; //找不到跳转到list
			}
			Comment comment =new Comment();
			comment.setBug(bug);
			comment.setCommentContent(content);
			comment.setCommentPublishTime(new Date());
			comment.setUserInfo(loginUser.getUserInfo());
			bug.getComments().add(comment);
			this.commentServiceImpl.saveComment(comment);
		}else{
			Comment comment=new Comment();
			comment.setBug(bug);
			comment.setCommentContent(content);
//			System.out.println(commentId);
			comment.setCommentPublishTime(new Date());
			comment.setParentComment(this.commentServiceImpl.getCommentById(commentId));
//			comment.setParentComment(this.commentServiceImpl.getCommentById(commentId));
//			System.out.println("parent ID :"+this.commentServiceImpl.getCommentById(commentId));
			comment.setUserInfo(loginUser.getUserInfo());
//			System.out.println(comment.getParentComment().getCommentId());
			this.commentServiceImpl.saveComment(comment);
		}
		return "redirect:findone?bugId=" + bugId;
	}
}
