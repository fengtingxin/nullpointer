package com.exp.userinfo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.exp.entity.UserInfo;
import com.framework.BaseDao;

@Repository
public class UserInfoDaoImpl extends BaseDao<UserInfo, Integer> {
	@Resource
	private SessionFactory sessionFactory;
	/**
	 * @function 荣誉榜
	 * @author tangwenru
	 * @return
	 */
	public List<UserInfo> order(){
		Query query =this.sessionFactory.getCurrentSession().createQuery("from UserInfo u where"
				+ " u.loginUser.role.roleId=2 and u.loginUser.loginActive = 1 order by u.userInfoHonorCount desc");
		  query.setFirstResult(0); //开始记录 
		  query.setMaxResults(5);  //查询出来的记录数 
		return query.list();
	}
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
