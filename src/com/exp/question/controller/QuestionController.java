package com.exp.question.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exp.answer.service.AnswerServiceImpl;
import com.exp.entity.Answer;
import com.exp.entity.Question;
import com.exp.entity.QuestionHateRecord;
import com.exp.entity.QuestionLikeRecord;
import com.exp.entity.Tag;
import com.exp.entity.LoginUser;
import com.exp.entity.UserInfo;
import com.exp.question.questionHateRecord.service.QuestionHateRecordServiceImpl;
import com.exp.question.questionLikeRecord.service.QuestionLikeRecordServiceImpl;
import com.exp.question.service.QuestionServiceImpl;
import com.exp.r_tag_userInfo.service.R_Tag_UserInfoServiceImpl;
import com.exp.tag.service.TagServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

//删除了不必要引用的包
@Controller
@RequestMapping("question")
public class QuestionController {
	@Resource
	private QuestionServiceImpl questionServiceImpl;// 将questionserviceimpl改成questionServiceImpl
	@Resource
	private AnswerServiceImpl answerServiceImpl;
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;
	@Resource
	private QuestionLikeRecordServiceImpl questionLikeRecordServiceImpl;
	@Resource
	private QuestionHateRecordServiceImpl questionHateRecordServiceImpl;
	@Resource
	private TagServiceImpl tagServiceImpl;
	@Resource
	private R_Tag_UserInfoServiceImpl r_Tag_UserInfoServiceImpl;

	// 设置每页有5条数据
	private Integer pageSize = 5;

	/**
	 * @author zhang zhao lin
	 * @param questionTitle
	 *            标题
	 * @param questionTag
	 *            类别选择
	 * @param questionDescribe
	 *            问题描述
	 * @param questionDetailed
	 *            详细内容
	 * @param httpSession
	 * @return 没有想要的问题 向大哲提问 提交数据
	 */
	@RequestMapping(value = "questionRelease", method = RequestMethod.POST)
	public String questionRelease(@RequestParam(name = "questionTitle", defaultValue = "") String questionTitle,
			@RequestParam(name = "questionTag", defaultValue = "") String questionTag,
			@RequestParam(name = "questionDescribe", defaultValue = "") String questionDescribe,
			@RequestParam(name = "questionDetailed", defaultValue = "") String questionDetailed,
			HttpServletRequest request, HttpSession session) {

		// 首先进行判断 用户输入是否合法
		if (questionTitle.equals("")) {
			request.setAttribute("warning", "请输入标题");
			return "question";
		}

		if (questionTag.equals("")) {
			request.setAttribute("warning", "请选择相应的标签");
			return "question";
		}

		if (questionDescribe.equals("")) {
			request.setAttribute("warning", "请填写问题描述");
			return "question";
		}

		if (questionDetailed.equals("")) {
			request.setAttribute("warning", "请填写详细内容");
			return "question";
		}

		String[] questionTags = null;

		try {
			questionTitle = new String(questionTitle.getBytes("iso-8859-1"), "utf-8");
			questionTag = new String(questionTag.getBytes("iso-8859-1"), "utf-8");
			questionDescribe = new String(questionDescribe.getBytes("iso-8859-1"), "utf-8");
			questionDetailed = new String(questionDetailed.getBytes("iso-8859-1"), "utf-8");
			questionTags = questionTag.split(",");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("questionTag:" + questionTag);
		// 装填Tag信息
		Set<Tag> tags = new HashSet<Tag>();
		for (int i = 0; i < questionTags.length; i++) {
			tags.add(this.tagServiceImpl.getOneTagByName(questionTags[i]));
		}

		// 获取系统当前时间
		Date questionPublishTime = new Date();
		Question question = new Question();
		// 获取用户信息
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		// 如果没有用户信息，需要进行登陆
		if (loginUser == null) {
			request.setAttribute("warning", "请先登录");
			return "question";
		}

		UserInfo userInfo = loginUser.getUserInfo();
		question.setUserInfo(userInfo);
		question.setTags(tags);
		question.setQuestionDescribe(questionDescribe);
		question.setQuestionTitle(questionTitle);
		question.setQuestionDetailed(questionDetailed);
		question.setQuestionPublishTime(questionPublishTime);
		this.questionServiceImpl.saveQuestion(question);
		request.setAttribute("warning", "No");
		return "question";
	}

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
	public String list(@RequestParam(name = "userInfoId", required = false) Integer userInfoId,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpSession session,
			HttpServletResponse response) {
		// 获取用户信息
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		// 如果没有用户信息，需要进行登陆
		if (loginUser == null) {
			try {
				response.sendRedirect("login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
	public String getQuestion(@RequestParam("questionId") Integer questionId,
			@RequestParam(name = "question_detailed_bell", required = false) String question_detailed_bell,
			HttpServletRequest request) {
		Question question = this.questionServiceImpl.getQuestion(questionId);
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		Integer userInfoId;
		if (loginUser == null) {
			userInfoId = null;
		} else {
			userInfoId = loginUser.getLoginUserId();
		}
		if (userInfoId != null
				& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null) {
			request.setAttribute("likeStatus", this.questionLikeRecordServiceImpl
					.findQuestionLikeRecord(questionId, userInfoId).getQuestionLikeStatus());
		}
		if (userInfoId != null
				& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null) {
			request.setAttribute("hateStatus", this.questionHateRecordServiceImpl
					.findQuestionHateRecord(questionId, userInfoId).getQuestionHateStatus());
		}
		request.setAttribute("question", question);
		if (question_detailed_bell != null) {
			if (question_detailed_bell.substring(question_detailed_bell.length() - 1).equals("1")) {
				question_detailed_bell = "请输入内容";
			}
			if (question_detailed_bell.substring(question_detailed_bell.length() - 1).equals("2")) {
				question_detailed_bell = "请登录";
			}
			request.setAttribute("question_detailed_bell", question_detailed_bell);
			request.setAttribute("question_detailed_judge", "ok");
		}
		return "q_a_detailed";

	}

	/**
	 * 功能： 提交问题的评论
	 * 
	 * @param questionId
	 * @param content
	 * @param answerId
	 * @param request
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "{questionId}", method = RequestMethod.POST)
	public String submitAnswer(@PathVariable("questionId") Integer questionId,
			@RequestParam(name = "content") String content,
			@RequestParam(name = "answerId", required = false) Integer answerId, HttpServletRequest request,
			HttpSession session) {
		if (content == null || content.trim().length() == 0) {
			// 内容为空
			return "redirect:findone?questionId=" + questionId + "&question_detailed_bell=" + 1;
		}
		System.out.println("answer id" + answerId);
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if (loginUser == null) {
			// 没有登录
			return "redirect:findone?questionId=" + questionId + "&question_detailed_bell=" + 2;
		}
		// code转换
		content = EncodingTool.encodeStr(content);
		Question question = this.questionServiceImpl.getQuestion(questionId);
		if (answerId == null) {
			// 添加一条父级commet
			if (question == null) {
				return "redirect:list_new"; // 找不到跳转到list
			}
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setAnswerContent(content);
			answer.setAnswerPublishTime(new Date());
			answer.setUserInfo(loginUser.getUserInfo());
			question.getAnswers().add(answer);
			this.answerServiceImpl.saveAnswer(answer);
		} else {
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setAnswerContent(content);
			answer.setAnswerPublishTime(new Date());
			answer.setParentAnswer(this.answerServiceImpl.getAnswer(answerId));
			answer.setUserInfo(loginUser.getUserInfo());
			this.answerServiceImpl.saveAnswer(answer);
		}
		return "redirect:findone?questionId=" + questionId;
	}

	/**
	 * @function 对问题进行赞、取消赞
	 * @author tangwenru
	 * @param userInfoId
	 * @param questionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "like",method=RequestMethod.POST)
	@ResponseBody
	public String questionLike(@RequestParam(name = "questionId") Integer questionId, HttpServletRequest request) {
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		Question question = this.questionServiceImpl.getQuestion(questionId);
		// 判断用户是否登录
		if (loginUser == null) {
			return "not ok";
		} else {// 用户已登录
			UserInfo userInfo = loginUser.getUserInfo();
			Integer userInfoId = userInfo.getUserInfoId();
			if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) == null) {
				// 未踩
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) == null) {
					// 未赞
					question.setQuestionLikeNum(question.getQuestionLikeNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionLikeRecord questionLikeRecord = new QuestionLikeRecord();
					questionLikeRecord.setQuestion(question);
					questionLikeRecord.setUserInfo(userInfo);
					questionLikeRecord.setQuestionLikeStatus(1);
					questionLikeRecord.setQuestionLikeTime(new Date());
					this.questionLikeRecordServiceImpl.saveQuestionLikeRecord(questionLikeRecord);
					return "likeOk";
				}
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null
						&& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)
								.getQuestionLikeStatus() == 0) {
					// 赞失效
					QuestionLikeRecord questionLikeRecord = this.questionLikeRecordServiceImpl
							.findQuestionLikeRecord(questionId, userInfoId);
					questionLikeRecord.setQuestionLikeStatus(1);
					question.setQuestionLikeNum(question.getQuestionLikeNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					this.questionLikeRecordServiceImpl.updateQuestionLikeRecord(questionLikeRecord);
					return "likeOk";
				}
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null
						&& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)
								.getQuestionLikeStatus() == 1) {
					// 赞有效
					question.setQuestionLikeNum(question.getQuestionLikeNum() - 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionLikeRecord questionLikeRecord = this.questionLikeRecordServiceImpl
							.findQuestionLikeRecord(questionId, userInfoId);
					questionLikeRecord.setQuestionLikeStatus(0);
					this.questionLikeRecordServiceImpl.updateQuestionLikeRecord(questionLikeRecord);
					return "cancelLike";
				}
			}
			if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null
					&& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)
							.getQuestionHateStatus() == 0) {
				// 踩失效
				request.setAttribute("hateStatus", 0);
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) == null) {
					// 未赞
					question.setQuestionLikeNum(question.getQuestionLikeNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionLikeRecord questionLikeRecord = new QuestionLikeRecord();
					questionLikeRecord.setQuestion(question);
					questionLikeRecord.setUserInfo(userInfo);
					questionLikeRecord.setQuestionLikeStatus(1);
					questionLikeRecord.setQuestionLikeTime(new Date());
					this.questionLikeRecordServiceImpl.saveQuestionLikeRecord(questionLikeRecord);
					return "likeOk";
				}
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null
						&& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)
								.getQuestionLikeStatus() == 0) {
					// 赞失效
					QuestionLikeRecord questionLikeRecord = this.questionLikeRecordServiceImpl
							.findQuestionLikeRecord(questionId, userInfoId);
					questionLikeRecord.setQuestionLikeStatus(1);
					question.setQuestionLikeNum(question.getQuestionLikeNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					this.questionLikeRecordServiceImpl.updateQuestionLikeRecord(questionLikeRecord);
					return "likeOk";
				}
				if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null
						&& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)
								.getQuestionLikeStatus() == 1) {
					// 赞有效
					question.setQuestionLikeNum(question.getQuestionLikeNum() - 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionLikeRecord questionLikeRecord = this.questionLikeRecordServiceImpl
							.findQuestionLikeRecord(questionId, userInfoId);
					questionLikeRecord.setQuestionLikeStatus(0);
					this.questionLikeRecordServiceImpl.updateQuestionLikeRecord(questionLikeRecord);
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
	 * @function 对问题进行踩，取消踩
	 * @author tangwenru
	 * @param userInfoId
	 * @param questionId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "hate", method = RequestMethod.POST)
	@ResponseBody
	public String questionHate(@RequestParam(name = "questionId") Integer questionId, HttpServletRequest request) {
		Question question = this.questionServiceImpl.getQuestion(questionId);
		LoginUser loginUser = (LoginUser) request.getSession().getAttribute("loginUser");
		// 判断用户是否登录
		if (loginUser == null) {
			return "not ok";
		} else {// 用户已登录
			UserInfo userInfo = loginUser.getUserInfo();
			Integer userInfoId = userInfo.getUserInfoId();
			if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) == null) {
				// 赞的记录为空
				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) == null) {
					// 踩的记录为空
					question.setQuestionHateNum(question.getQuestionHateNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionHateRecord questionHateRecord = new QuestionHateRecord();
					questionHateRecord.setQuestion(question);
					questionHateRecord.setUserInfo(userInfo);
					questionHateRecord.setQuestionHateStatus(1);
					questionHateRecord.setQuestionHateTime(new Date());
					this.questionHateRecordServiceImpl.saveQuestionHateRecord(questionHateRecord);
					return "hateOk";
				}
				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null
						&& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)
								.getQuestionHateStatus() == 0) {
					// 踩失效
					QuestionHateRecord questionHateRecord = this.questionHateRecordServiceImpl
							.findQuestionHateRecord(questionId, userInfoId);
					questionHateRecord.setQuestionHateStatus(1);
					question.setQuestionHateNum(question.getQuestionHateNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					this.questionHateRecordServiceImpl.updateQuestionHateRecord(questionHateRecord);
					return "hateOk";
				}
				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null
						&& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)
								.getQuestionHateStatus() == 1) {
					// 踩有效
					question.setQuestionHateNum(question.getQuestionHateNum() - 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionHateRecord questionHateRecord = this.questionHateRecordServiceImpl
							.findQuestionHateRecord(questionId, userInfoId);
					questionHateRecord.setQuestionHateStatus(0);
					this.questionHateRecordServiceImpl.updateQuestionHateRecord(questionHateRecord);
					return "cancelHate";
				}
			}
			if (this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId) != null
					&& this.questionLikeRecordServiceImpl.findQuestionLikeRecord(questionId, userInfoId)
							.getQuestionLikeStatus() == 0) {
				// 赞已失效
				request.setAttribute("likeStatus", 0);

				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) == null) {
					// 踩的记录为空
					question.setQuestionHateNum(question.getQuestionHateNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionHateRecord questionHateRecord = new QuestionHateRecord();
					questionHateRecord.setQuestion(question);
					questionHateRecord.setUserInfo(userInfo);
					questionHateRecord.setQuestionHateStatus(1);
					questionHateRecord.setQuestionHateTime(new Date());
					this.questionHateRecordServiceImpl.saveQuestionHateRecord(questionHateRecord);
					return "hateOk";
				}
				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null
						&& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)
								.getQuestionHateStatus() == 0) {
					// 踩失效
					QuestionHateRecord questionHateRecord = this.questionHateRecordServiceImpl
							.findQuestionHateRecord(questionId, userInfoId);
					questionHateRecord.setQuestionHateStatus(1);
					question.setQuestionHateNum(question.getQuestionHateNum() + 1);
					this.questionServiceImpl.updateQuestion(question);
					this.questionHateRecordServiceImpl.updateQuestionHateRecord(questionHateRecord);
					return "hateOk";
				}
				if (this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId) != null
						&& this.questionHateRecordServiceImpl.findQuestionHateRecord(questionId, userInfoId)
								.getQuestionHateStatus() == 1) {
					// 踩有效
					question.setQuestionHateNum(question.getQuestionHateNum() - 1);
					this.questionServiceImpl.updateQuestion(question);
					QuestionHateRecord questionHateRecord = this.questionHateRecordServiceImpl
							.findQuestionHateRecord(questionId, userInfoId);
					questionHateRecord.setQuestionHateStatus(0);
					this.questionHateRecordServiceImpl.updateQuestionHateRecord(questionHateRecord);
					return "cancelHate";
				}
				return "hateOk";
			} else {
				// 赞有效
				return "onLike";
			}

		}

	}

}
