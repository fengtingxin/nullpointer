package com.exp.bug.bugLikeRecord.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.BugLikeRecord;
import com.framework.BaseDao;

@Repository
public class BugLikeRecordDaoImpl extends BaseDao<BugLikeRecord, String> {
    /**
     * @function 更新bug点赞记录表
     * @author tangwenru
     * @param b
     */
	public void updateBugLikeRecord(BugLikeRecord b){
		try {
			this.update(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @function 保存bug点赞记录表
	 * @author tangwenru
	 * @param b
	 */
	public void saveBugLikeRecord(BugLikeRecord b){
		try {
			this.save(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @function 通过bugId,userInfo查询单个bug点赞记录
	 * @author tangwenru
	 * @param bugId
	 * @param userInfoId
	 * @return
	 */
	public BugLikeRecord findBugLikerecord(Integer bugId,Integer userInfoId){
		try {
			return super.findOne("from BugLikeRecord b where b.bug.bugId=? and b.userInfo.userInfoId=?",new Object[] { bugId, userInfoId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	/**
	 * @function 根据id查询单个bug点赞记录
	 * @author tangwenru
	 * @param bugLikeREcordId
	 * @return
	 */
	public BugLikeRecord getBugLikeRecord(int bugLikeRecordId){
		try {
			BugLikeRecord b=this.get(bugLikeRecordId);
			return b;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
	
}
