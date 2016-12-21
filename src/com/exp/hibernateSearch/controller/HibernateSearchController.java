package com.exp.hibernateSearch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.Bug;
import com.exp.entity.Question;
import com.exp.hibernateSearch.service.HibernateSearchServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

@Controller
// @RequestMapping("hibernateSearch")
public class HibernateSearchController {
	@Resource
	private HibernateSearchServiceImpl hibernateSearchServiceImpl;

	/**
	 * @author Ray_1
	 * @功能 搜索下拉框，查询bug或者question，显示4条，不分页。
	 * @param search
	 * @param request
	 * @param model
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/findBugAndQuestionByValue", method = RequestMethod.POST)
	public void searchAll(@RequestParam(name = "title", defaultValue = "") String search, HttpServletRequest request,
			Model model, HttpServletResponse response, HttpSession session) {
		// System.out.println("searchParam为" + search);
		if (search == "" || search.length() == 0) {
			return;
		}
		// System.out.println("查询内容" + search);
		try {
			// 这里不设置编码会有乱码
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		List<Object> bugs = null;
		bugs = hibernateSearchServiceImpl.searchBug(search);
		List<Object> questions = null;
		questions = hibernateSearchServiceImpl.searchQuestion(search);
		if (bugs == null && questions == null) {
			return;
		}
		try {
			StringBuilder sb = new StringBuilder();
			// 如果bug和quesion不为空时，分两种情况
			// 1 question+bug数小于4， 都显示出来
			// 2 quesion+bug 数大于4时， 若bug<4, 显示全部bug，再用question补充；
			// 若bug数大于4，直接显示4条bug。

			// 如果 有为空的，则显示不为空的也分两种情况
			// 1 question为空，bug不为空 显示小于等于4条bug
			// 2 bug为空，question不为空，显示小于等于4条question

			if (bugs != null && questions != null) {
				if (bugs.size() + questions.size() <= 4) {
					for (Object bug : bugs) {
						String bugtitle = bug.toString();
						//System.out.println(bugtitle);
						if (bugtitle.length()> 120) {
							sb.append("<li><a>" + bugtitle.substring(0, 120) + "</a></li>");
						} else {
							sb.append("<li><a>" + bugtitle + "</a></li>");
						}
					}
					for (Object question : questions) {
						String questiontitle = question.toString();
						System.out.println(questiontitle);
						if (questiontitle.length() > 120) {
							sb.append("<li><a>" + questiontitle.substring(0, 120) + "</a></li>");
						} else {
							sb.append("<li><a>" + questiontitle + "</a></li>");
						}
					}
				} else {
					if (bugs.size() > 0 && bugs.size() <= 4) {
						for (int i = 0; i < bugs.size(); i++) {
							if (bugs.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + bugs.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + bugs.get(i).toString() + "</a></li>");
							}
						}
						for (int i = 0; i < 4 - bugs.size(); i++) {
							if (questions.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + questions.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + questions.get(i).toString() + "</a></li>");
							}
						}
					}
					if (bugs.size() > 4) {
						for (int i = 0; i < 4; i++) {
							if (bugs.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + bugs.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + bugs.get(i).toString() + "</a></li>");
							}
						}
					}

				}
			} else {
				if (bugs != null && questions == null) {
					if (bugs.size() > 4) {
						for (int i = 0; i < 4; i++) {
							if (bugs.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + bugs.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + bugs.get(i).toString() + "</a></li>");
							}
						}
					} else {
						for (int i = 0; i < bugs.size(); i++) {
							if (bugs.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + bugs.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + bugs.get(i).toString() + "</a></li>");
							}
						}
					}
				}
				if (questions != null && bugs == null) {
					if (questions.size() > 4) {
						for (int i = 0; i < 4; i++) {
							if (questions.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + questions.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + questions.get(i).toString() + "</a></li>");
							}
						}
					} else {
						for (int i = 0; i < questions.size(); i++) {
							if (questions.get(i).toString().length() >= 120) {
								sb.append("<li><a>" + questions.get(i).toString().substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + questions.get(i).toString() + "</a></li>");
							}
						}
					}
				}
			}
			// System.out.println(sb.toString());
			if (sb != null)
				response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Ray_1
	 * @desc 实现hibernate Search 查询分页bug
	 * @param search
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@RequestMapping("/findBugByPage")
	public String listbug(@RequestParam("s") String search,
			@RequestParam(name = "pageBugNum", defaultValue = "1") int pageNum, HttpSession session) {
		Date startTime =new Date();
		Page<Bug> page;
		search = EncodingTool.encodeStr(search);
		// System.out.println("search:" + search );
		// System.out.println("到了controller"+"search"+search);
		page = this.hibernateSearchServiceImpl.findBugByPage(pageNum, 8, search);
		// System.out.println("一共多少条："+page.getTotalCount());
		if (page != null)
			session.setAttribute("bugpageCount", page.getTotalCount());
		Date endTime =new Date();
		session.setAttribute("experienceTime", (endTime.getTime()-startTime.getTime())/1000+"."+(endTime.getTime()-startTime.getTime())%1000/100);
		session.setAttribute("bugpages", page);
		session.setAttribute("searchValue", search);
		return "search_bug_list_admin";
	}

	/**
	 * @author Ray_1
	 * @desc 实现hibernate Search 查询分页question
	 * @param search
	 * @param pageNum
	 * @param session
	 * @return
	 */
	@RequestMapping("/findQuestionByPage")
	public String listquestion(@RequestParam("s") String search,
			@RequestParam(name = "pageBugNum", defaultValue = "1") int pageNum, HttpSession session) {
		Date startTime =new Date();
		Page<Question> page;
		search = EncodingTool.encodeStr(search);
		//System.out.println("到了controller" + "search" + search);
		page = this.hibernateSearchServiceImpl.findQuestionByPage(pageNum, 8, search);
		session.setAttribute("questionpages", page);
		if (page != null)
			session.setAttribute("questionpageCount", page.getTotalCount());
		session.setAttribute("searchValue", search);
		Date endTime =new Date();
		session.setAttribute("experienceTime", (endTime.getTime()-startTime.getTime())/1000+"."+(endTime.getTime()-startTime.getTime())%1000/100);
		return "search_q_a_list_new";
	}
}
