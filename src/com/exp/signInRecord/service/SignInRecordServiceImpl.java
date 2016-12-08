package com.exp.signInRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.SignInRecord;
import com.exp.signInRecord.dao.SignInRecordDaoImpl;

@Service
@Transactional(readOnly=true)
public class SignInRecordServiceImpl {
	@Resource
	private SignInRecordDaoImpl signInRecordDaoImpl;
	/**
	 * @function 更新签到记录表
	 * @author tangwenru
	 * @param signInRecord
	 */
	@Transactional(readOnly=false)
	public void updateSignInRecord(SignInRecord signInRecord){
		this.signInRecordDaoImpl.updateSignInRecord(signInRecord);
	}
	/**
	 * @function 保存签到记录
	 * @author tangwenru
	 * @param signInRecord
	 */
	@Transactional(readOnly=false)
	public void saveSignInRecord(SignInRecord signInRecord){
		this.signInRecordDaoImpl.saveSignInRecord(signInRecord);
	}
	/**
	 * @function 根据userInfoId查询签到记录
	 * @author tangwenru
	 * @param userInfoId
	 */
	public SignInRecord findSignInRecord(Integer userInfoId){
		return this.signInRecordDaoImpl.findSignInRecord(userInfoId);
	}
	

}
