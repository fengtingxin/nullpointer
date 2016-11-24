package com.exp.tag.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exp.entity.Tag;
import com.exp.tag.dao.TagDaoImpl;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl {
    /**
     * @zhangzhaolin
     * tag查询
     */
	@Resource
	private TagDaoImpl tagdaoimpl;

	public List<Tag> findAllTag() {
		return tagdaoimpl.findAllTag();
	}
}
