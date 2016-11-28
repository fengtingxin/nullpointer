package com.exp.question.controller;


import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.Question;
import com.exp.question.service.QuestionServiceImpl;
import com.framework.Page;
//删除了不必要引用的包
@Controller
@RequestMapping("question")
public class QuestionController {
	@Resource
	private QuestionServiceImpl questionServiceImpl;//将questionserviceimpl改成questionServiceImpl
	// 设置每页有5条数据
	private Integer pageSize = 5;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题 个人主页部分
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("findQuestionByTime")
	public String list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpSession session) {
		Page<Question> page;
		int userinfoId = 1;
		page = this.questionServiceImpl.findQuestionByTime(pageNum, 4, new Object[] { userinfoId });
		session.setAttribute("page", page);
		return "home-question";
	}

	/**
	 * @author zhang zhao lin
	 * @param currentPageNum
	 *            当前页 默认值为第一页
	 * @return list列表 _最新发布 没有查询功能！！
	 */
	@RequestMapping("list_new")
	public String questionList_theNew(@RequestParam(name = "currentPageNum", defaultValue = "1") Integer currentPageNum,
			HttpSession session) {
		Page<Question> page = new Page<Question>();
        page = questionServiceImpl.findQuestion_theNew(currentPageNum, pageSize);
		session.setAttribute("questionPage", page);
		return "q_a_list_new";
	}

	/**
	 * @author zhang zhao lin
	 * @param currentPageNum
	 *            当前页数 默认为1
	 * @return 查询问题页中数据 按照最多人回答排序 目前没有查询功能！！
	 */
	@RequestMapping("list_answer")
	public String questionList_theMostAnswerCount(
			@RequestParam(name = "currentPageNum", defaultValue = "1") Integer currentPageNum, HttpSession session) {
		Page<Question> page = new Page<Question>();
		page = questionServiceImpl.findQuestion_theMostAnswerCount(currentPageNum, pageSize);
		session.setAttribute("questionPage", page);
		return "q_a_list_answer";
	}

	@RequestMapping("list_noone")
	public String questionList_noOneAnswer(
			@RequestParam(name = "currentPageNum", defaultValue = "1") Integer currentPageNum, HttpSession session) {
		Page<Question> page = new Page<Question>();
		page = questionServiceImpl.findQuestion_noOne(currentPageNum, pageSize);
		session.setAttribute("questionPage", page);
		return "q_a_list_noOne";
	}
}
