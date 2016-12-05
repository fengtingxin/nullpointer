package com.exp.bug.bugLikeRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.bug.bugLikeRecord.dao.BugLikeRecordDaoImpl;
import com.exp.entity.BugLikeRecord;

@Service
@Transactional(readOnly=true)
public class BugLikeRecordServiceImpl {
	@Resource
	private BugLikeRecordDaoImpl bugLikeRecordDaoImpl;
	/**
	 * @function 更新bug点赞记录表
	 * @author tangwenru
	 * @param b
	 */
	@Transactional(readOnly=false)
	public void updateBugLikeRecord(BugLikeRecord b){
		BugLikeRecord record=this.bugLikeRecordDaoImpl.getBugLikeRecord(b.getBugLikeRecordId());
		b.setBugLikeStatus(b.getBugLikeStatus());
		this.bugLikeRecordDaoImpl.updateBugLikeRecord(record);
		
	}
	/**
	 * @function 保存bug点赞记录
	 * @author tangwenru
	 * @param b
	 */
	@Transactional(readOnly=false)
	public void saveBugLikeRecord(BugLikeRecord b){
		this.bugLikeRecordDaoImpl.saveBugLikeRecord(b);
	}
	/**
	 * @function 查询单个bug点赞记录
	 * @author tangwenru
	 * @param bugId
	 * @param userInfoId
	 * @return
	 */
	public BugLikeRecord findBugLikeRecord(Integer bugId,Integer userInfoId){
		return this.bugLikeRecordDaoImpl.findBugLikerecord(bugId, userInfoId);
		
	}
	/**
	 * @function 根据id查询单个bug点赞记录
	 * @author tangwenru
	 * @param bugLikeRecordId
	 * @return
	 */
	public BugLikeRecord getBugLikeRecord(Integer bugLikeRecordId){
		return this.bugLikeRecordDaoImpl.getBugLikeRecord(bugLikeRecordId);
	}
	
	

}
