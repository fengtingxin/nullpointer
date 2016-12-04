package com.exp.text.dao;

import org.springframework.stereotype.Repository;

import com.exp.entity.Bug;
import com.framework.BaseDao;
import com.framework.Page;

@Repository
public class TextDaoImpl extends BaseDao<Bug, String> {
	/**
	 * @author Ray_1
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	// 按页，搜索bug查询
	
	public Page<Bug> findText(int pageNum, int pageSize, Object[] params) {
		String hql = null;
		if (params != null && params.length > 0) {
			hql = "from Bug p where p.bugTitle like ?";
			params[0] = "%" + params[0] + "%";
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
		return null;
	}
}
