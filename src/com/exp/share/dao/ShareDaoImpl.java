package com.exp.share.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.exp.entity.Bug;
import com.exp.entity.Comment;
import com.framework.BaseDao;
import com.framework.Page;

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
	public Page<Bug> ShareByTime(int pageNum, int pageSize, Object[] params) {
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
