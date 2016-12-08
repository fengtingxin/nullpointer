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
	 * @zhangzhaolin bug查询
	 */
	@Resource
	private BugDaoImpl bugDaoImpl;

	public List<Bug> findHonor() {
		return bugDaoImpl.findBugRecommend();
	}

	/**
	 * @author Ray_1 功能：按搜索框的内容搜索bug
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
	public Integer getAdminBugNum() {
		return this.bugDaoImpl.getAdminBugNum();
	}

	/**
	 * @function 获取用户发布bug的数量
	 * @author tangwenru
	 * @return
	 */
	public Integer getUserBugNum() {
		return this.bugDaoImpl.getUserBugNum();
	}

	/**
	 * @function 根据bug的id查询单个bug
	 * @author tangwenru
	 * @param bugId
	 * @return
	 */
	public Bug getBug(Integer bugId) {
		return this.bugDaoImpl.getBug(bugId);
	}

	/**
	 * 功能：得到所有的bug//无序
	 * 
	 * @return
	 * @author fengtingxin
	 */
	public List<Bug> getAllBug() {
		return this.bugDaoImpl.getAllBug();
	}

	/**
	 * 功能：得到所有未经审核的bug
	 * 
	 * @return
	 * @author fengtingxin
	 */
	public List<Bug> getAllBugNoAudit() {
		return this.bugDaoImpl.getAllBugNoAudit();
	}

	/**
	 * 功能： 根据id删除一个bug
	 * 
	 * @param bugId
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public void deleteOneBug(Integer bugId) {
		try {
			this.bugDaoImpl.delete(bugId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("bug exception about delete one!");
			Bug bug;
			try {
				bug = this.bugDaoImpl.get(bugId);
				this.bugDaoImpl.delete(bug);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * 功能： 根据id找到一个bug
	 * 
	 * @param bugId
	 * @return
	 * @author fengtingxin
	 */
	public Bug getOneBug(Integer bugId) {
		try {
			return this.bugDaoImpl.get(bugId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 功能： 保存bug
	 * 
	 * @param bug
	 * @author fengtingxin
	 */
	@Transactional(readOnly = false)
	public void saveOneBug(Bug bug) {
		try {
			this.bugDaoImpl.save(bug);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("there is a error when save a bug");
		}
	}

	/**
	 * @function 更新bug
	 * @author tangwenru
	 * @param bug
	 */
	@Transactional(readOnly = false)
	public void updateBug(Bug bug) {
		try {
			this.bugDaoImpl.update(bug);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
