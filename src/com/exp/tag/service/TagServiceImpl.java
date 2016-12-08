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
	 * @zhangzhaolin tag查询
	 */
	@Resource
	private TagDaoImpl tagDaoImpl;

	public List<Tag> findAllTag() {
		return tagDaoImpl.findAllTag();
	}

	/**
	 * 功能： 找到tag通过name
	 * 
	 * @param tagName
	 * @return
	 * @author fengtingxin
	 */
	public Tag getOneTagByName(String tagName) {
		try {
			return this.tagDaoImpl.findOne("from Tag where tagName=?", new Object[] { tagName });
		} catch (Exception e) {
			Tag tag = new Tag();
			tag.setTagName(tagName);
			try {
				this.tagDaoImpl.save(tag);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return tag;
		}
	}
}
