package com.exp.r_tag_userInfo.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.R_Tag_UserInfo;
import com.exp.r_tag_userInfo.dao.R_Tag_UserInfoDaoImpl;

@Service
@Transactional(readOnly=true)
public class R_Tag_UserInfoServiceImpl {
    @Resource
    private R_Tag_UserInfoDaoImpl r_Tag_UserInfoDaoImpl;
    /**
     * @function 更新社区属性
     * @author tangwenru
     * @param r
     */
    @Transactional(readOnly=false)
    public void updateR_Tag_UserInfo(R_Tag_UserInfo r){
    	this.r_Tag_UserInfoDaoImpl.updateR_Tag_UserInfo(r);
    }
    /**
     * @function 保存社区属性
     * @author tangwenru
     * @param r
     */
    @Transactional(readOnly=false)
    public void saveR_Tag_UserInfo(R_Tag_UserInfo r){
    	this.r_Tag_UserInfoDaoImpl.saveR_Tag_UserInfo(r);
    }
    /**
     * @function 根据id查询社区属性
     * @author tangwenru
     * @param id
     * @return
     */
    public R_Tag_UserInfo getR_Tag_UserInfo(Integer id){
    	return this.r_Tag_UserInfoDaoImpl.getR_Tag_UserInfo(id);
    }
    /**
     * @function 根据userInfoId,tagId查询社区属性
     * @author tangwenru
     * @param userInfoId
     * @param tagId
     * @return
     */
    public R_Tag_UserInfo findR_Tag_UserInfo(Integer userInfoId,Integer tagId){
    	return this.r_Tag_UserInfoDaoImpl.findoneR_Tag_UserInfo(userInfoId, tagId);
    }
}
