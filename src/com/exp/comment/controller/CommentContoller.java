package com.exp.comment.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.bug.service.BugServiceImpl;
import com.exp.comment.service.CommentServiceImpl;
import com.exp.entity.Bug;

@Controller
@RequestMapping("comment")
public class CommentContoller {
	@Resource
	private CommentServiceImpl commentServiceImpl; 
	@Resource
	private BugServiceImpl bugServiceImpl;
	/**
	 * @function 根据commentId删除评论
	 * @author tangwenru
	 * @param commentId
	 * @param bugId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("commentId") int commentId,@RequestParam(name="bugId",required=false) int bugId, HttpServletRequest request) {
		this.commentServiceImpl.deleteComment(commentId);
		return "redirect:findone?bugId="+bugId;
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
