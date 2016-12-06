package com.exp.bug.bugHateRecord.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.bug.bugHateRecord.dao.BugHateRecordDaoImpl;
import com.exp.entity.BugHateRecord;

@Service
@Transactional(readOnly=true)
public class BugHateRecordServiceImpl {
	@Resource
	private BugHateRecordDaoImpl bugHateRecordDaoImpl;
	/**
	 * @function 保存bug被踩记录
	 * @author tangwenru
	 * @param b
	 */
	@Transactional(readOnly=false)
	public void saveBugHateRecord(BugHateRecord b){
		this.bugHateRecordDaoImpl.saveBugHateRecord(b);
	}
	/**
	 * @function 查询单个bug被踩记录
	 * @author tangwenru
	 * @param userInfoId
	 * @return
	 */
	public BugHateRecord findBugHateRecord(Integer bugId,Integer userInfoId){
		return this.bugHateRecordDaoImpl.findBugHateRecord(bugId, userInfoId);
		
	}
	/**
	 * @function 更新bug被踩记录
	 * @author tangwenru
	 * @param bugHateRecord
	 */
	@Transactional(readOnly=false)
	public void updateBugHateRecord(BugHateRecord bugHateRecord){
		try {
			this.bugHateRecordDaoImpl.update(bugHateRecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
