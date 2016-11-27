package com.exp.advice.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.exp.advice.dao.AdviceDaoImpl;
import com.exp.entity.Advice;

@Service
public class AdviceServiceImpl {

	@Resource
	private AdviceDaoImpl adviceDaoImpl;
	/**
	 * 功能：
	 * 保存用户提交的建议
	 * @param advice
	 * @return
	 * 
	 */
	public void saveAdvice(Advice advice){
		try {
			this.adviceDaoImpl.save(advice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//处理异常！
			saveAdvice(advice);
		}
	}
}
