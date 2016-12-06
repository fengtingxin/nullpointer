package com.exp.r_tag_userInfo.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.R_Tag_UserInfo;
import com.framework.BaseDao;

@Repository
public class R_Tag_UserInfoDaoImpl extends BaseDao<R_Tag_UserInfo,String>{
	/**
	 * @function 保存社区属性
	 * @author tangwenru
	 * @param r
	 */
	public void saveR_Tag_UserInfo(R_Tag_UserInfo r){
		try {
			this.save(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @function 更新社区属性
	 * @author tangwenru
	 * @param r
	 */
	public void updateR_Tag_UserInfo(R_Tag_UserInfo r){
		try {
			this.update(r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 根据id查询社区属性
	 * @author tangwenru
	 * @param id
	 * @return
	 */
    public R_Tag_UserInfo getR_Tag_UserInfo(Integer id){
    	return this.getR_Tag_UserInfo(id);
    	
    }
    /**
     * @fuction 根据userInfoId,tagId查询社区属性
     * @author tangwenru
     * @param userInfoId
     * @param tagId
     * @return
     */
    public R_Tag_UserInfo findoneR_Tag_UserInfo(Integer userInfoId,Integer tagId){
    	try {
			return super.findOne("from R_Tag_UserInfo r where r.userInfo.userInfoId=? and r.tag.tagId=?",new Object[] { userInfoId,tagId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }

}
