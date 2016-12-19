package com.exp.signUpRecord.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.SignUpRecord;
import com.exp.signUpRecord.dao.SignUpRecordDaoImpl;


@Service
@Transactional(readOnly=true)
public class SignUpRecordServiceImpl {
    @Resource
    private SignUpRecordDaoImpl signUpRecordDaoImpl;
    /** 
     * @function 保存登录记录
     * @author tangwenru
     * @param signUpRecord
     */
    @Transactional(readOnly=false)
	public void saveSignUpRecord(SignUpRecord signUpRecord){
    	System.out.println("haha");
		this.signUpRecordDaoImpl.saveSignUpRecord(signUpRecord);
	}
    /**
     * @function 更新登录记录
     * @author tangwenru
     * @param signUpRecord
     */
    @Transactional(readOnly=false)
    public void updateSignUpRecord(SignUpRecord signUpRecord){
    	this.signUpRecordDaoImpl.updateSignUpRecord(signUpRecord);
    }
    /**
     * @fucntion 根据年份和用户查询登录记录
     * @author tangwenru
     * @param year
     * @return
     */
    public List<SignUpRecord> findByYear(Integer year,Integer userInfoId){
    	return this.signUpRecordDaoImpl.findByYear(year,userInfoId);
    }
    /**
     * @function 根据年份和月份查询登录记录
     * @author tangwenru
     * @param year
     * @param month
     * @return
     */
    public SignUpRecord findByYearAndMonth(Integer year,Integer month,Integer userInfoId){
    	return this.signUpRecordDaoImpl.findByYearAndMonth(year, month,userInfoId);
    }
    
}
