package com.exp.userinfo.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.UserInfo;
import com.framework.BaseDao;

@Repository
public class UserInfoDaoImpl extends BaseDao<UserInfo, Integer> {
	/**
	 * @function 根据id查询单个UserInfo
	 * @author tangwenru
	 * @param id
	 * @return
	 */
	public UserInfo findById(Integer id) {
		try {
			return super.findOne("from UserInfo u where u.userInfoId=?", new Object[] { id });
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    /**
     * @function 更新用户信息
     * @author tangwenru
     * @param u
     */
	public void updateUserInfo(UserInfo u) {
		try {
			this.excuteBySql("update userinfo set userInfoBirthday=?, userInfoDescribe=?, userInfoHeadPortrait=?, userInfoHonorCount=?, userInfoRegistTime=?, userInfoSex=? where userInfoId=?",
					new Object[]{u.getUserInfoBirthday(),u.getUserInfoDescribe(),u.getUserInfoHeadPortrait(),u.getUserInfoHonorCount(),u.getUserInfoRegistTime(),u.getUserInfoSex(),u.getUserInfoId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.excuteBySql("update userinfo set userInfoBirthday=?, userInfoDescribe=?, userInfoHeadPortrait=?, userInfoHonorCount=?, userInfoRegistTime=?, userInfoSex=? where userInfoId=?",
						new Object[]{u.getUserInfoBirthday(),u.getUserInfoDescribe(),u.getUserInfoHeadPortrait(),u.getUserInfoHonorCount(),u.getUserInfoRegistTime(),u.getUserInfoSex(),u.getUserInfoId()});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @author Ray_1 更新用户的个人头像 保存到数据库
	 * @param u
	 */
	public void updateImgUrl(int id, String url) {
		UserInfo u = findById(id);
		System.out.println("url:" + url);
		u.setUserInfoHeadPortrait(url);
		updateUserInfo(u);
	}
}
