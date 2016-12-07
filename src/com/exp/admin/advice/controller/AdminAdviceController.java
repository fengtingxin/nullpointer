package com.exp.admin.advice.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.advice.service.AdviceServiceImpl;
import com.exp.entity.Advice;

@Controller
@RequestMapping("admin")
public class AdminAdviceController {

	@Resource
	private AdviceServiceImpl adviceServiceImpl;

	/**
	 * 功能：
	 * advice 列表
	 * @param session
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "advice", method = RequestMethod.GET)
	public String toAdvice(HttpSession session) {
		List<Advice> advice = this.adviceServiceImpl.findAllAdvice();
		if (advice == null) {
			session.setAttribute("allAdvice", null);
		}
		session.setAttribute("allAdvice", advice);
		return "admin/advice";
	}
	
	/**
	 * 功能:
	 * 删除建议
	 * @param adviceId
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteOneAdvice(@RequestParam(name="adviceId")Integer adviceId){
		this.adviceServiceImpl.deleteOneAdvice(adviceId);
		return "redirect:advice";
	}
	
	/**
	 * 功能：
	 * 建议详情
	 * @param adviceId
	 * @param request
	 * @return
	 * @author fengtingxin
	 */
	@RequestMapping(value = "advice/{adviceId}",method=RequestMethod.GET)
	public String toOneAdvice(@PathVariable("adviceId") Integer adviceId,HttpServletRequest request){
		Advice advice = this.adviceServiceImpl.fingOneAdvice(adviceId);
		if(advice==null){
			//若是空，则跳转到建议的列表页
			return "redirect:/admin/advice";
		}
		advice.setVisable(true);
		this.adviceServiceImpl.updateAdvice(advice);
		request.setAttribute("oneAdvice", advice);
		return "admin/advice_detail";
	}
}
