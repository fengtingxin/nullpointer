package com.exp.question.controller;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.answer.service.AnswerServiceImpl;
import com.exp.entity.Answer;
import com.exp.entity.Bug;
import com.exp.entity.Comment;
import com.exp.entity.LoginUser;
import com.exp.entity.Question;
import com.exp.question.service.QuestionServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;
//删除了不必要引用的包
@Controller
@RequestMapping("question")
public class QuestionController {
	@Resource
	private QuestionServiceImpl questionServiceImpl;//将questionserviceimpl改成questionServiceImpl
	@Resource 
	private AnswerServiceImpl answerServiceImpl;
	// 设置每页有5条数据
	private Integer pageSize = 5;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题 个人主页部分
	 * @param userInfoId 
	 * @author tangwenru 新增参数userInfoId 动态获取此时用户的id
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("findQuestionByTime")
	public String list(@RequestParam(name="userInfoId",required=false) Integer userInfoId,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpSession session) {
		Page<Question> page;
		page = this.questionServiceImpl.findQuestionByTime(pageNum, 4, new Object[] { userInfoId });
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
	/**
	 * @function 根据question的questionId查询单个Question
	 * @author tangwenru
	 * @param questionId
	 * @param request
	 * @return q_a_detailed.jsp页面
	 */
	@RequestMapping(value = "findone", method = RequestMethod.GET)
	public String getQuestion(@RequestParam("questionId") Integer questionId,@RequestParam(name="bug_detailed_bell",required=false) String bug_detailed_bell,
			HttpServletRequest request){
		Question question=this.questionServiceImpl.getQuestion(questionId);
		request.setAttribute("question",question);
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
		return "q_a_detailed";
		
	}
	
	@RequestMapping(value = "{questionId}", method = RequestMethod.POST)
	public String submitAnswer(@PathVariable("questionId") Integer questionId, @RequestParam(name = "content") String content,
			@RequestParam(name = "answerId", required = false) Integer answerId,HttpServletRequest request,HttpSession session) {
		if(content==null||content.trim().length()==0){
			//内容为空
			return "redirect:findone?questionId=" + questionId+"&bug_detailed_bell="+1;
		}
		System.out.println("answer id"+answerId);
		LoginUser loginUser=(LoginUser) session.getAttribute("loginUser");
		if(loginUser==null){
			//没有登录
			return "redirect:findone?questionId=" + questionId+"&bug_detailed_bell="+2;
		}
		//code转换
		content=EncodingTool.encodeStr(content);
		Question question =this.questionServiceImpl.getQuestion(questionId);
		if(answerId==null){
			//添加一条父级commet
			System.out.println("执行了吗 空");
			if(question==null){
				return "redirect:list_new"; //找不到跳转到list
			}
			Answer answer =new Answer();
			answer.setQuestion(question);
			answer.setAnswerContent(content);
			answer.setAnswerPublishTime(new Date());
			answer.setUserInfo(loginUser.getUserInfo());
			question.getAnswers().add(answer);
			this.answerServiceImpl.saveAnswer(answer);
		}else{
			System.out.println("执行了吗 不空");
			Answer answer=new Answer();
			answer.setQuestion(question);
			answer.setAnswerContent(content);
			answer.setAnswerPublishTime(new Date());
			answer.setParentAnswer(this.answerServiceImpl.getAnswer(answerId));
			answer.setUserInfo(loginUser.getUserInfo());
			this.answerServiceImpl.saveAnswer(answer);
		}
		return "redirect:findone?questionId=" + questionId;
	}
}
