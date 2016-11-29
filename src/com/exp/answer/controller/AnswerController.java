package com.exp.answer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.answer.service.AnswerServiceImpl;
import com.exp.entity.Answer;
import com.exp.entity.Bug;
import com.exp.entity.Question;
import com.exp.question.service.QuestionServiceImpl;
import com.framework.Page;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	@Resource
	private AnswerServiceImpl answerServiceImpl;//将此处answerserviceimpl改成answerServiceImpl
    @Resource
    private QuestionServiceImpl questionServiceImpl;
	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAnswerByTime")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
		Page<Answer> pages;
		int userInfoId = 1;// userinfoId改为userInfoId
		pages = this.answerServiceImpl.findAnswerByTime(pageNum, 6, new Object[] { userInfoId });
		request.setAttribute("pages", pages);
		return "home-answer";
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
	public String delete(@RequestParam("answerId") int answerId,@RequestParam(name="questionId",required=false) int questionId, HttpServletRequest request) {
		this.answerServiceImpl.deleteAnswer(answerId);
		return "redirect:findone?questionId="+questionId;
	}
	
    /**
     * @function 删除一条评论后调用的方法
     * @author tangwenru
     * @param bugId
     * @param request
     * @return q_a_detailed.jsp页面
     */
	@RequestMapping(value = "findone", method = RequestMethod.GET)
	public String getBug(Integer questionId, HttpServletRequest request) {
		Question question = this.questionServiceImpl.getQuestion(questionId);
		request.setAttribute("question", question);
		return "q_a_detailed";
	}
}
