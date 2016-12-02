package com.exp.bugLikeRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.bugLikeRecord.dao.BugLikeRecordDaoImpl;
import com.exp.entity.BugLikeRecord;

@Service
@Transactional(readOnly=true)
public class BugLikeRecordServiceImpl {
	@Resource
	private BugLikeRecordDaoImpl bugLikeRecordImpl;
	/**
	 * @function 更新bug点赞记录表
	 * @author tangwenru
	 * @param b
	 */
	@Transactional(readOnly=false)
	public void updateBugLikeRecord(BugLikeRecord b){
		this.bugLikeRecordImpl.updateBugLikeRecord(b);
		
	}
	/**
	 * @function 保存bug点赞记录
	 * @author tangwenru
	 * @param b
	 */
	@Transactional(readOnly=false)
	public void saveBugLikeRecord(BugLikeRecord b){
		this.bugLikeRecordImpl.saveBugLikeRecord(b);
	}
	public BugLikeRecord findBugLikeRecord(Integer bugId,Integer userInfoId){
		return this.bugLikeRecordImpl.findBugLikerecord(bugId, userInfoId);
		
	}
	

}
