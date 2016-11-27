package com.exp.share.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Bug;
import com.exp.share.dao.ShareDaoImpl;
import com.framework.Page;
//汤文茹删除了不必要引入的包
@Service
@Transactional(readOnly = true)
public class ShareServiceImpl {
	@Resource
	private ShareDaoImpl shareDaoImpl;
	//汤文茹修改了此处参数名称，由sharedaoimpl改为shareDaoImpl

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
		return this.shareDaoImpl.shareByTime(pageNum, pageSize, params);
	}
}
