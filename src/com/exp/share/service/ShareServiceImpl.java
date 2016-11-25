package com.exp.share.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.share.dao.ShareDaoImpl;
import com.framework.Page;

@Service
@Transactional(readOnly = true)
public class ShareServiceImpl {
	@Resource
	private ShareDaoImpl sharedaoimpl;

	/**
	 * @author Ray_1 功能 按时间将用户的分享的bug查询出来
	 * @param pageNum
	 *            页码数
	 * @param pageSize
	 *            一页几条
	 * @param params
	 *            hql参数
	 * @return 用户的问题列表，放到page对象中
	 */
	@Transactional(readOnly = true)
	public Page<Bug> findBugByTime(int pageNum, int pageSize, Object[] params) {
		return this.sharedaoimpl.ShareByTime(pageNum, pageSize, params);
	}
}
