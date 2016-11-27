package com.exp.share.dao;


import org.springframework.stereotype.Repository;

import com.exp.entity.Bug;

import com.framework.BaseDao;
import com.framework.Page;
//汤文茹删除了不必要引入的包
@Repository
public class ShareDaoImpl extends BaseDao<Bug, Integer> {

	/**
	 * @author Ray_1
	 * @fun 功能 按时间将用户的分享的bug查询出来
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            一页几条
	 * @param params
	 *            hql参数
	 * @return 用户的问题列表，放到page对象中
	 */
	// 按页，搜索条件查询
	//汤文茹修改了方法名称，由ShareByTime改成shareByTime,按驼峰命名法命名
	public Page<Bug> shareByTime(int pageNum, int pageSize, Object[] params) {
		String hql;

		hql = "from Bug where bugAuthorId=? order by bugPublishTime";
		params[0] = params[0];
		try {
			Page<Bug> page = new Page<Bug>();
			page.setCurrentPageNum(pageNum);
			page.setPageSize(pageSize);
			page = this.findByPage(pageNum, pageSize, hql, params);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
