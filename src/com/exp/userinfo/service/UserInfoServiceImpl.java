package com.exp.userinfo.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.UserInfo;
import com.exp.userinfo.dao.UserInfoDaoImpl;

/**
 * @author tangwenru
 *
 */
@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl{
    @Resource
	private UserInfoDaoImpl userInfoDaoImpl;
    
    public UserInfo findById(Integer id){
    	return this.userInfoDaoImpl.findById(id);
    }
    
    @Transactional(readOnly = false)
	public void editUserInfo(UserInfo u) {
		this.userInfoDaoImpl.editUserInfo(u);
    }
    @Transactional(readOnly = false)
	public void updateImgUrl(int id,String url) {
    	userInfoDaoImpl.updateImgUrl(id,url);
    }
        
}
