package com.exp.bug.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.exp.entity.Bug;
import com.exp.entity.BugLikeRecord;
import com.framework.BaseDao;
import com.framework.Page;

@Repository
public class BugDaoImpl extends BaseDao<Bug, String> {
	/**
	 * @zhangzhaolin
	 * @return 首页中返回bug推荐 按照赞的数量排序
	 */
	public List<Bug> findBugRecommend() {
		Session session = super.getSession();
		// 按照赞的数量排序
		Query query = session.createQuery("from Bug order by bugPublishTime DESC,bugLikeNum DESC ");
		return query.list();
	}
	
	/**
	 * @功能  获取管理员发布的bug，对其分页排序
	 * @author tangwenru
	 * @param pageNum  分页数量
	 * @param pageSize 分页大小
	 * @param params   参数
	 * @return
	 */
	public Page<Bug> findAdminBug(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Bug b where b.bugTitle like ? and b.userInfo.loginUser.role.roleId=1 order by b.bugPublishTime desc,b.bugLikeNum desc,b.comments.size desc";
			params[0]="%"+params[0]+"%";
			
		}else{
			hql="from Bug b where b.userInfo.loginUser.role.roleId=1 order by bugPublishTime DESC,bugLikeNum DESC,comments.size desc";
		}
		try {
			Page<Bug> page=new Page<Bug>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @function 获取用户发布的bug,分页显示
	 * @author tangwenru
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public Page<Bug> findUserBug(int pageNum, int pageSize,Object[] params){
		String hql;
		if(params!=null && params.length>0){
			hql="from Bug b where b.bugTitle like ? and b.userInfo.loginUser.role.roleId=2 order by b.bugPublishTime desc,b.bugLikeNum desc,b.comments.size desc";
			params[0]="%"+params[0]+"%";
			
		}else{
			hql="from Bug b where b.userInfo.loginUser.role.roleId=2 order by b.bugPublishTime desc,b.bugLikeNum desc,b.comments.size desc";
		}
		try {
			Page<Bug> page=new Page<Bug>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page=this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @function　获取官方发布的bug的数量
	 * @author tangwenru
	 * @return
	 */
	public Integer getAdminBugNum(){
		Session session = super.getSession();
		Query query = session.createQuery("from Bug b where b.userInfo.loginUser.role.roleId=1 ");
		return query.list().size();
	}
	/**
	 * @function　获取用户发布的bug的数量
	 * @author tangwenru
	 * @return
	 */
	public Integer getUserBugNum(){
		Session session = super.getSession();
		Query query = session.createQuery("from Bug b where b.userInfo.loginUser.role.roleId=2");
		return query.list().size();
	}
	/**
	 * @function 根据bug的id查询单个bug
	 * @author tangwenru
	 * @param bugId
	 * @return
	 */
	public Bug getBug(int bugId){
		try {
			Bug bug=this.get(bugId);
			return bug;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @function 更新bug
	 * @author tangwenru
	 * @param bug
	 */
	public void updateBug(Bug bug){
		try {
			super.update(bug);
			System.out.println("gengxinwanbi");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
