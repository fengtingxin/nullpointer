package com.exp.signUpRecord.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.exp.entity.SignUpRecord;
import com.framework.BaseDao;

@Repository
public class SignUpRecordDaoImpl extends BaseDao<SignUpRecord,String>{
   /**
    * @function 根据年份用户查找登录记录
    * @author tangwenru
    * @param year
    * @return
    */
   public List<SignUpRecord> findByYear(Integer year,Integer userInfoId){
	   Session session = super.getSession();
	   Query query=session.createQuery("from SignUpRecord s where s.years=? and s.userInfo.userInfoId=?");
	   query.setInteger(0, year);
	   query.setInteger(1, userInfoId);
	   return query.list();
	   
   }
   /**
    * @function 根据年份和月份查找登录记录
    * @author tangwenru
    * @param year
    * @param month
    * @return
    */
   public SignUpRecord findByYearAndMonth(Integer year,Integer month,Integer userInfoId){
	  try {
		return this.findOne("from SignUpRecord s where s.years=? and s.months=? and s.userInfo.userInfoId=?",new Object[] { year,month,userInfoId });
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	  
	   
   }
   /**
    * @function 更新登录记录表
    * @author tangwenru
    * @param signUpRecord
    */
   public void updateSignUpRecord(SignUpRecord signUpRecord){
	   try {
		this.update(signUpRecord);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   /**
    * @function 保存登录记录
    * @author tangwenru
    * @param signUpecord
    */
   public void saveSignUpRecord(SignUpRecord signUpRecord){
	   try {
		this.save(signUpRecord);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
