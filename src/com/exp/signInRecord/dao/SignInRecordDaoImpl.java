package com.exp.signInRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.SignInRecord;
import com.framework.BaseDao;

@Repository
public class SignInRecordDaoImpl extends BaseDao<SignInRecord,String>{
	/**
	 * @function 保存签到记录
	 * @author tangwenru
	 * @param signInRecord
	 */
	public void saveSignInRecord(SignInRecord signInRecord){
		try {
			this.save(signInRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 更新签到记录
	 * @author tangwenru
	 * @param signInRecord
	 */
	public void updateSignInRecord(SignInRecord signInRecord){
		try {
			this.excuteBySql("update sign_in_record set lastTime=?,signNumber=? where signId=?", new Object[]{signInRecord.getLastTime(),signInRecord.getSignNumber(),signInRecord.getSignId()});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.excuteBySql("update sign_in_record set lastTime=?,signNumber=? where signId=?", new Object[]{signInRecord.getLastTime(),signInRecord.getSignNumber(),signInRecord.getSignId()});
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("update SignInRecord error !!!!");
			}
		}
	}
	/**
	 * @function 根据userInfoId查询单个签到记录
	 * @param userInfoId
	 * @return
	 */
	public  SignInRecord findSignInRecord(Integer userInfoId){
		try {
			return this.findOne("from SignInRecord s where s.userInfo.userInfoId=?",new Object[] { userInfoId } );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
