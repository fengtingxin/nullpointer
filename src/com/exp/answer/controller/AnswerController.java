package com.exp.answer.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.answer.answerHateRecord.service.AnswerHateRecordServiceImpl;
import com.exp.answer.answerLikeRecord.service.AnswerLikeRecordServiceImpl;
import com.exp.answer.service.AnswerServiceImpl;
import com.exp.entity.Answer;
import com.exp.entity.AnswerHateRecord;
import com.exp.entity.AnswerLikeRecord;
import com.exp.entity.Bug;
import com.exp.entity.BugHateRecord;
import com.exp.entity.BugLikeRecord;
import com.exp.entity.LoginUser;
import com.exp.entity.Question;
import com.exp.entity.UserInfo;
import com.exp.question.service.QuestionServiceImpl;
import com.exp.userinfo.service.UserInfoServiceImpl;
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
	private AnswerHateRecordServiceImpl answerHateRecordServiceImpl;
	@Resource
	private AnswerLikeRecordServiceImpl answerLikeRecordServiceImpl;

	/**
	 * @author Ray_1 按时间顺序分页查询个人所提问题的问题
	 * @author tangwenru 增加了参数userInfoId，动态获取当前用户
	 * @param pageNum
	 *            一页有多少
	 * @param request
	 * @return
	 */
	@RequestMapping("/findAnswerByTime")
	public String list(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, HttpServletRequest request) {
		Page<Answer> pages;
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
	public String delete(@RequestParam("answerId") int answerId,
			@RequestParam(name = "questionId", required = false) int questionId, HttpServletRequest request) {
		this.answerServiceImpl.deleteAnswer(answerId);
		return "redirect:findone?questionId=" + questionId;
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
    /**
     * @function 对回答进行赞、取消赞
     * @author tangwenru
     * @param userInfoId
     * @param answerId
     * @param request
     * @return
     */
	@RequestMapping(value = "like", method = RequestMethod.GET)
	public String bugLike(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "answerId") Integer answerId, HttpServletRequest request) {
		Answer answer = this.answerServiceImpl.getAnswer(answerId);
		Question question=answer.getQuestion();
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("answer", answer);
			request.setAttribute("question", question);
			request.setAttribute("remindMsg", "请登录！");
			return "q_a_detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			request.setAttribute("question", question);
			if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) == null) {
				// 未踩
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) == null) {
					// 未赞
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerLikeRecord answerLikeRecord = new AnswerLikeRecord();
					answerLikeRecord.setAnswer(answer);
					answerLikeRecord.setUserInfo(userInfo);
					answerLikeRecord.setAnswerLikeStatus(1);
					answerLikeRecord.setAnswerLikeTime(new Date());
					this.answerLikeRecordServiceImpl.saveAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
						&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId)
								.getAnswerLikeStatus() == 0) {
					// 赞失效
					AnswerLikeRecord answerLikeRecord = this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId,
							userInfoId);
					answerLikeRecord.setAnswerLikeStatus(1);
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					this.answerLikeRecordServiceImpl.updateAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
						&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId)
								.getAnswerLikeStatus() == 1) {
					// 赞有效
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() - 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerLikeRecord answerLikeRecord = this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId,
							userInfoId);
					answerLikeRecord.setAnswerLikeStatus(0);
					this.answerLikeRecordServiceImpl.updateAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
			}
			if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
					&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId)
							.getAnswerHateStatus() == 0) {
				// 踩失效
				request.setAttribute("answerHateStatus", 0);
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) == null) {
					// 未赞
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerLikeRecord answerLikeRecord = new AnswerLikeRecord();
					answerLikeRecord.setAnswer(answer);
					answerLikeRecord.setUserInfo(userInfo);
					answerLikeRecord.setAnswerLikeStatus(1);
					answerLikeRecord.setAnswerLikeTime(new Date());
					this.answerLikeRecordServiceImpl.saveAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
						&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId)
								.getAnswerLikeStatus() == 0) {
					// 赞失效
					AnswerLikeRecord answerLikeRecord = this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId,
							userInfoId);
					answerLikeRecord.setAnswerLikeStatus(1);
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					this.answerLikeRecordServiceImpl.updateAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
						&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId)
								.getAnswerLikeStatus() == 1) {
					// 赞有效
					answer.setAnswerLikeNum(answer.getAnswerLikeNum() - 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerLikeRecord answerLikeRecord = this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId,
							userInfoId);
					answerLikeRecord.setAnswerLikeStatus(0);
					this.answerLikeRecordServiceImpl.updateAnswerLikeRecord(answerLikeRecord);
					request.setAttribute("answerLikeStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				return "q_a_detailed";

			} else {
				// 踩有效
				if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
						&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId)
								.getAnswerLikeStatus() == 0) {
					request.setAttribute("answerLikeStatus", 0);
				}
				request.setAttribute("answerHateStatus", 1);
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("answer", answer);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消踩后才可以赞哦！");
				return "q_a_detailed";
			}

		}

	}
	
	@RequestMapping(value = "hate", method = RequestMethod.GET)
	public String bugHate(@RequestParam(name = "userInfoId") Integer userInfoId,
			@RequestParam(name = "answerId") Integer answerId, HttpServletRequest request) {
		Answer answer = this.answerServiceImpl.getAnswer(answerId);
		Question question=answer.getQuestion();
		// 判断用户是否登录
		if (userInfoId == null) {
			request.setAttribute("adviceReminder", "ok");
			request.setAttribute("answer", answer);
			request.setAttribute("question", question);
			request.setAttribute("remindMsg", "请登录！");
			return "q_a_detailed";

		} else {// 用户已登录
			UserInfo userInfo = this.userInfoServiceImpl.findById(userInfoId);
			LoginUser loginUser = userInfo.getLoginUser();
			if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) == null) {
				// 赞的记录为空
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) == null) {
					// 踩的记录为空
					answer.setAnswerHateNum(answer.getAnswerHateNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerHateRecord answerHateRecord = new AnswerHateRecord();
					answerHateRecord.setAnswer(answer);
					answerHateRecord.setUserInfo(userInfo);
					answerHateRecord.setAnswerHateStatus(1);
					answerHateRecord.setAnswerHateTime(new Date());
					this.answerHateRecordServiceImpl.saveAnswerHateRecord(answerHateRecord);
					request.setAttribute("answerHateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
						&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId).getAnswerHateStatus() == 0) {
					// 踩失效
					AnswerHateRecord answerHateRecord = this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId);
					answerHateRecord.setAnswerHateStatus(1);
					answer.setAnswerHateNum(answer.getAnswerHateNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					this.answerHateRecordServiceImpl.updateAnswerHateRecord(answerHateRecord);
					request.setAttribute("anserHateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
						&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId).getAnswerHateStatus() == 1) {
					// 踩有效
					answer.setAnswerHateNum(answer.getAnswerHateNum() - 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerHateRecord answerHateRecord = this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId);
					answerHateRecord.setAnswerHateStatus(0);
					this.answerHateRecordServiceImpl.updateAnswerHateRecord(answerHateRecord);
					request.setAttribute("answerHateStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
			}
			if (this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId) != null
					&& this.answerLikeRecordServiceImpl.findAnswerLikeRecord(answerId, userInfoId).getAnswerLikeStatus() == 0) {
				// 赞已失效
				request.setAttribute("answerLikeStatus", 0);

				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) == null) {
					// 踩的记录为空
					answer.setAnswerHateNum(answer.getAnswerHateNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerHateRecord answerHateRecord = new AnswerHateRecord();
					answerHateRecord.setAnswer(answer);
					answerHateRecord.setUserInfo(userInfo);
					answerHateRecord.setAnswerHateStatus(1);
					answerHateRecord.setAnswerHateTime(new Date());
					this.answerHateRecordServiceImpl.saveAnswerHateRecord(answerHateRecord);
					request.setAttribute("answerHateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
						&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId).getAnswerHateStatus() == 0) {
					// 踩失效
					AnswerHateRecord answerHateRecord = this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId);
					answerHateRecord.setAnswerHateStatus(1);
					answer.setAnswerHateNum(answer.getAnswerHateNum() + 1);
					this.answerServiceImpl.updateAnswer(answer);
					this.answerHateRecordServiceImpl.updateAnswerHateRecord(answerHateRecord);
					request.setAttribute("anserHateStatus", 1);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
						&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId).getAnswerHateStatus() == 1) {
					// 踩有效
					answer.setAnswerHateNum(answer.getAnswerHateNum() - 1);
					this.answerServiceImpl.updateAnswer(answer);
					AnswerHateRecord answerHateRecord = this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId);
					answerHateRecord.setAnswerHateStatus(0);
					this.answerHateRecordServiceImpl.updateAnswerHateRecord(answerHateRecord);
					request.setAttribute("answerHateStatus", 0);
					request.setAttribute("loginUser", loginUser);
					request.setAttribute("answer", answer);
					return "q_a_detailed";
				}
				return "q_a_detailed";
			} else {
				//赞有效
				if (this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId) != null
						&& this.answerHateRecordServiceImpl.findAnswerHateRecord(answerId, userInfoId).getAnswerHateStatus() == 0) {
					request.setAttribute("AnswerHateStatus", 0);
				}
				request.setAttribute("AnswerLikeStatus", 1);
				request.setAttribute("loginUser", loginUser);
				request.setAttribute("answer", answer);
				request.setAttribute("adviceReminder", "ok");
				request.setAttribute("remindMsg", "取消赞后才可以踩哦！");
				return "q_a_detailed";
			}

		}

	}
}
