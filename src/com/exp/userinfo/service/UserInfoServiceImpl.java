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
    /**
     * @function 根据id查询单个UserInfo
     * @author tangwenru
     * @param id
     * @return
     */
    public UserInfo findById(Integer id){
    	return this.userInfoDaoImpl.findById(id);
    }
    /**
     * @function 修改用户信息
     * @author tangwenru
     * @param u UserInfo对象
     */
    @Transactional(readOnly = false)
	public void editUserInfo(UserInfo u) {
		this.userInfoDaoImpl.editUserInfo(u);
    }
    @Transactional(readOnly = false)
	public void updateImgUrl(int id,String url) {
    	userInfoDaoImpl.updateImgUrl(id,url);
    }
        
}
