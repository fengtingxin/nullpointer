package com.exp.answer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.answer.service.AnswerServiceImpl;
import com.exp.entity.Answer;
import com.exp.entity.LoginUser;
import com.exp.entity.Question;
import com.exp.entity.UserInfo;
import com.exp.loginUser.service.LoginUserServiceImpl;
import com.exp.question.questionHateRecord.service.QuestionHateRecordServiceImpl;
import com.exp.question.questionLikeRecord.service.QuestionLikeRecordServiceImpl;
import com.exp.question.service.QuestionServiceImpl;
import com.exp.userinfo.controller.UserInfoController;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	@Resource
	private AnswerServiceImpl answerServiceImpl;// 将此处answerserviceimpl改成answerServiceImpl
	@Resource
	private QuestionServiceImpl questionServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@Resource
	private LoginUserServiceImpl loginUserServiceImpl;
	@Resource
	private QuestionHateRecordServiceImpl questionHateRecordServiceImpl;
	@Resource
	private QuestionLikeRecordServiceImpl questionLikeRecordServiceImpl;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题
	 * @author tangwenru 增加了参数userInfoId，动态获取当前用户
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAnswerByTime")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request,HttpSession session) {
		LoginUser loginUser =(LoginUser) session.getAttribute("loginUser");
		if(loginUser==null){ //若是在loginUser为空的情况下，客户端访问该控制器，应该让控制器返回登录界面
			return "login";
		}
		UserInfo userInfo = loginUser.getUserInfo();

		// 调用求时间差的方法，计算用户注册距离现在的时间差，并将时间差存到request范围
		long array[] = UserInfoController.differ(userInfo);
		request.setAttribute("day", array[0]);
		request.setAttribute("hour", array[1]);
		request.setAttribute("min", array[2]);
		request.setAttribute("second", array[3]);
		Page<Answer> pages;
		pages = this.answerServiceImpl.findAnswerByTime(pageNum, 6, new Object[] { loginUser.getLoginUserId() });
		if(pages==null){
			request.setAttribute("pages", null);
		}else{
			request.setAttribute("pages", pages);
		}
		return "home-answer";
	}
	/**
	 * @function 查找他的答案
	 * @author tangwenru
	 * @param pageNum
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/findAnswerByTimeTwo")
	public String listTwo(@RequestParam(name = "loginName") String loginName,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
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
		Page<Answer> pages;
		pages = this.answerServiceImpl.findAnswerByTime(pageNum, 6, new Object[] { userInfo.getUserInfoId() });
		if(pages==null){
			request.setAttribute("pages", null);
		}else{
			request.setAttribute("pages", pages);
		}
		return "hishome-answer";
	}
	/**
	 * @function 根据answerId删除回答
	 * @author tangwenru
	 * @param answerId
	 * @param questionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("answerId") int answerId,
			@RequestParam(name = "questionId", required = false) int questionId, HttpServletRequest request,HttpSession session) {
		LoginUser loginUser=(LoginUser) session.getAttribute("loginUser");
		this.answerServiceImpl.deleteAnswer(answerId);
		return "redirect:findone?questionId=" + questionId;
	}

	/**
	 * @function 删除一条回答后调用的方法
	 * @author tangwenru
	 * @param questionId
	 * @param request
	 * @return q_a_detailed.jsp页面
	 */
	@RequestMapping(value = "findone", method = RequestMethod.GET)
	public String getQuestion(Integer questionId,Integer userInfoId,HttpServletRequest request) {
		Question question = this.questionServiceImpl.getQuestion(questionId);
		request.setAttribute("question", question);
		if(this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)!=null){
			request.setAttribute("hateStatus", this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId).getQuestionHateStatus());
		}
		if(this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)!=null){
			request.setAttribute("likeStatus", this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId).getQuestionLikeStatus());
		}
		return "q_a_detailed";
	}
    
}
