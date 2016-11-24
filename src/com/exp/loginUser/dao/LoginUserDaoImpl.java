package com.exp.loginUser.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.exp.entity.LoginUser;
import com.framework.BaseDao;

@Repository
public class LoginUserDaoImpl extends BaseDao<LoginUser, String> {

	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 功能：
	 * 注册
	 * @param loginUser
	 * @return
	 * @author fengtingxin
	 */
	public String register(LoginUser loginUser) {
		try {
			super.save(loginUser);
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "1";
		}
	}
	/**
	 * 功能：
	 * 通过loginName得到loginUser 实体
	 * @param loginName
	 * @return
	 * loginUser
	 * @author fengtingxin
	 */
	public LoginUser findByLoginName(String loginName){
		try {
			Query query =this.sessionFactory.getCurrentSession().createQuery("from LoginUser where loginName='"+loginName+"'");
			return (LoginUser) query.uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 功能：
	 * 通过loginEmail得到loginUser 实体
	 * @param email
	 * @return
	 * loginUser 实体
	 * @author fengtingxin
	 */
	public LoginUser findByEmail(String email){
		try {
//			int i = super.excuteBySql("from LoginUser where loginName=?",new Object[]{loginName});
			Query query =this.sessionFactory.getCurrentSession().createQuery("from LoginUser where loginEmail='"+email+"'");
			return (LoginUser) query.uniqueResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 功能：
	 * 更新用户信息
	 * @param loginUser
	 * @author fengtingxin
	 * 
	 */
	public void updateLoginUser(LoginUser loginUser){
		try {
			LoginUser l1=this.findByLoginName(loginUser.getLoginName());
			super.excuteBySql("update loginUser set loginName=?,loginPassword=?,loginEmail=?,loginActive=? where loginUserId=?",
					new Object[]{loginUser.getLoginName(),loginUser.getLoginPassword(),loginUser.getLoginEmail(),loginUser.getLoginActive(),l1.getLoginUserId()});
		} catch (Exception e) {
			e.printStackTrace();
			//对错误的处理
			
		}
	}
}
