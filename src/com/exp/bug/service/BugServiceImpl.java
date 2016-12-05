package com.exp.bug.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.bug.dao.BugDaoImpl;
import com.exp.entity.Bug;
import com.framework.Page;

@Service
@Transactional(readOnly = true)
public class BugServiceImpl {

	/**
	 * @zhangzhaolin
	 * bug查询
	 */
	@Resource
	private BugDaoImpl bugDaoImpl;

	public List<Bug> findHonor() {
		return bugDaoImpl.findBugRecommend();
	}
	/**
	 * @author Ray_1
	 * 功能：按搜索框的内容搜索bug
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<Bug> findBugByValue(int pageNum, int pageSize, Object[] params) {
		return this.bugDaoImpl.findBugByValue(pageNum, pageSize, params);
	}
	
	/**
	 * @function 分页查询官方发布的bug
	 * @author tangwenru
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page<Bug> listAdminBug(int pageNum, int pageSize, Object[] params) {
		return this.bugDaoImpl.findAdminBug(pageNum, pageSize, params);
	}
	
	/**
	 * @function 分页查询用户发布的bug
	 * @author tangwenru
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page<Bug> listUserBug(int pageNum, int pageSize, Object[] params) {
		return this.bugDaoImpl.findUserBug(pageNum, pageSize, params);
	}
	/**
	 * @function 获取官方发布bug的数量
	 * @author tangwenru
	 * @return
	 */
	public Integer getAdminBugNum(){
		return this.bugDaoImpl.getAdminBugNum();
	}
	/**
	 * @function 获取用户发布bug的数量
	 * @author tangwenru
	 * @return
	 */
	public Integer getUserBugNum(){
		return this.bugDaoImpl.getUserBugNum();
	}
	
	/**
	 * @function 根据bug的id查询单个bug
	 * @author tangwenru
	 * @param bugId
	 * @return
	 */
	public Bug getBug(Integer bugId){
		return this.bugDaoImpl.getBug(bugId);
	}
	/**
	 * @function 更新bug
	 * @author tangwenru
	 * @param bug
	 */
	@Transactional(readOnly=false)
	public void updateBug(Bug bug){
		this.bugDaoImpl.updateBug(bug);
	}
}
