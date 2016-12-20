package com.exp.admin.bug.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.bug.service.BugServiceImpl;
import com.exp.entity.Bug;
import com.exp.entity.LoginUser;
import com.exp.entity.Tag;
import com.exp.entity.UserInfo;
import com.exp.tag.service.TagServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;

@Controller
@RequestMapping("admin")
public class AdminBugController {

	@Resource
	private BugServiceImpl bugServiceImpl;
	@Resource
	private TagServiceImpl tagServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	
	/**
	 * 功能：
	 * 查询bug列表，存入session使之显示
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bug",method=RequestMethod.GET)
	public String toBug(HttpSession session){
		List<Bug> bug=this.bugServiceImpl.getAllBug();
		if(bug==null){
			session.setAttribute("allBug", null);
		}
		session.setAttribute("allBug", bug);
		return "admin/bug";
		
	}
	/**
	 * 功能：
	 * 根据id删除bug
	 * 
	 * @param bugId
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "deleteBug", method = RequestMethod.GET)
	public String deleteOneBug(@RequestParam(name="bugId")Integer bugId){
		this.bugServiceImpl.deleteOneBug(bugId);
		return "redirect:/admin/bug";
	}
	
	/**
	 * 功能：
	 * 根据id跳转到bug详情页
	 * @param bugId
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bug/{bugId}",method=RequestMethod.GET)
	public String toOneBug(@PathVariable("bugId") Integer bugId,HttpServletRequest request){
		Bug bug=this.bugServiceImpl.getOneBug(bugId);
		if(bug==null){
			//若是空，则跳转到建议的列表页
			return "redirect:/admin/bug";
		}
		request.setAttribute("oneBug", bug);
		return "admin/bug_detail";
	}
	/**
	 * 功能：
	 * 跳转到发布bug页面
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bug_publish",method=RequestMethod.GET)
	public String toPublishBug(HttpServletRequest request){
		List<Tag> tagList = this.tagServiceImpl.findAllTag();
		request.setAttribute("tags", tagList);
		return "admin/bug_publish";
	}
	
	/**
	 * 功能：
	 * 发布bug
	 * 使用ajax提交，返回字符串
	 * 1代表有错误
	 * ok代表成功
	 * not ok表示信息错误
	 * @param bugTitle
	 * @param bugReason
	 * @param bugMethod
	 * @param tags
	 * @param bugDescribe
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "publishBug",method=RequestMethod.POST)
	@ResponseBody
	public String publishBug(@RequestParam(name="bugTitle") String bugTitle,@RequestParam(name="bugReason") String bugReason,@RequestParam(name="bugMethod") String bugMethod,
			@RequestParam(name="tags") String tags,@RequestParam(name="bugDescribe") String bugDescribe,HttpSession session ){
		if(tags==null||tags.length()==0||bugTitle==null||bugReason==null||bugMethod==null||bugDescribe==null){
			return "not ok";
		}
		
		String [] tagArray= tags.split(",");  //将获取的tag分拆
		Bug bug=new Bug();
		bug.setBugTitle(bugTitle);
		bug.setBugDescribe(bugDescribe);
		bug.setBugHateNum(0);
		bug.setBugLikeNum(0);
		bug.setBugMethod(bugMethod);
		bug.setBugPageviews(0);
		bug.setBugAudited(true);
		bug.setBugAuditPass(true);
		bug.setBugPublishTime(new Date());
		LoginUser loginUser =(LoginUser) session.getAttribute("loginUser");
		bug.setUserInfo(loginUser.getUserInfo());
		bug.setBugReason(bugReason);
		Set<Tag> bugTags=new HashSet<Tag>();
		
		try {
			for(int i=0;i<tagArray.length;i++){
				bugTags.add(this.tagServiceImpl.getOneTagByName(tagArray[i]));
			}
			bug.setTags(bugTags);
			this.bugServiceImpl.saveOneBug(bug);
		} catch (Exception e) {
			// TODO: handle exception
			return "1";
		}
		return "ok";
	}
	/**
	 * 功能：
	 * 得到所有没有审核的bug
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bug_review",method=RequestMethod.GET)
	public String toBugReview(HttpSession session){
		List<Bug> bug=this.bugServiceImpl.getAllBugNoAudit();
		if(bug==null){
			session.setAttribute("allBugNoAudit", null);
		}
		session.setAttribute("allBugNoAudit", bug);
		return "admin/bug_review";
	}
	/**
	 * 功能：
	 * 跳转到未经审核的bug详情页面
	 * @param bugId
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bugReview/{bugId}",method=RequestMethod.GET)
	public String toOneNoAuditedBug(@PathVariable("bugId") Integer bugId,HttpServletRequest request){
		Bug bug=this.bugServiceImpl.getOneBug(bugId);
		if(bug==null){
			//若是空，则跳转到建议的列表页
			return "redirect:/admin/bug_review";
		}
		request.setAttribute("oneBug", bug);
		return "admin/bug_review_detail";
	}
	
	/**
	 * 功能：
	 * 同意用户发布bug,更改状态
	 * @param bugId
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bugReview/agreeBugPublish",method=RequestMethod.POST)
	@ResponseBody
	public String passShareBugByUser(@RequestParam(name="bugId")Integer bugId){
		try {
			Bug bug=this.bugServiceImpl.getOneBug(bugId);
			bug.setBugAudited(true);
			bug.setBugAuditPass(true);
			this.bugServiceImpl.updateBug(bug);
			UserInfo author=bug.getUserInfo();
			//分享bug审核通过，作者荣誉值+5
			author.setUserInfoHonorCount(author.getUserInfoHonorCount()+5);
			this.userInfoServiceImpl.updateUserInfo(author);
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			return "not ok";
		}
		
	}
	/**
	 * 功能：
	 * 拒绝用户分享bug页面,更改状态
	 * @param bugId
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "bugReview/denyBugPublish",method=RequestMethod.POST)
	@ResponseBody
	public String denyShareBugByUser(@RequestParam(name="bugId")Integer bugId){
		try {
			Bug bug=this.bugServiceImpl.getOneBug(bugId);
			bug.setBugAudited(true);
			bug.setBugAuditPass(false);
			this.bugServiceImpl.updateBug(bug);
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			return "not ok";
		}
	}
}
