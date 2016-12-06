package com.exp.bug.bugHateRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.BugHateRecord;
import com.framework.BaseDao;
@Repository
public class BugHateRecordDaoImpl  extends BaseDao<BugHateRecord, String>{
	/**
	 * @function 保存bug被踩记录表
	 * @author tangwenru
	 * @param b
	 */
	public void saveBugHateRecord(BugHateRecord b){
		try {
			this.save(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 通过bugId,userInfo查询单个bug被踩记录
	 * @author tangwenru
	 * @param bugId
	 * @param userInfoId
	 * @return
	 */
	public BugHateRecord findBugHateRecord(Integer bugId,Integer userInfoId){
		try {
			return super.findOne("from BugHateRecord b where b.bug.bugId=? and b.userInfo.userInfoId=?",new Object[] { bugId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
}
	/**
	 * @function 更新bug被踩记录
	 * @author tangwenru
	 * @param b
	 */
	public void updateBugHateRecord(BugHateRecord b){
		try {
			this.update(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
