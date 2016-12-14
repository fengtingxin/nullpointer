package com.exp.userinfo.service;


import java.util.List;

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
		try {
			this.userInfoDaoImpl.updateUserInfo(u);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("editUserInfo error !");
			e.printStackTrace();
		}
    }
    @Transactional(readOnly = false)
	public void updateImgUrl(int id,String url) {
    	userInfoDaoImpl.updateImgUrl(id,url);
    }
    /**
     * @function 修改用户信息
     * @author tangwenru
     * @param userInfo
     */
    @Transactional(readOnly=false)
    public void updateUserInfo(UserInfo userInfo){
    	this.userInfoDaoImpl.updateUserInfo(userInfo);
    }
    /**
     * @function 荣誉榜
     * @author tangwenru
     * @return
     */
    public List<UserInfo> order(){
    	return this.userInfoDaoImpl.order();
    }
        
}
