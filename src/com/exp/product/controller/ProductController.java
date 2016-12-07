package com.exp.product.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.Bug;

import com.exp.product.service.ProductServiceImpl;

@Controller
@RequestMapping("product")
public class ProductController {
	@Resource
	private ProductServiceImpl productServiceImpl;

	/**
	 * 
	 * @author zhang zhao lin
	 * @param search
	 * @param session
	 * @return 使用hibernate search 的方法 进行简单的查询 BUG 并实现高亮
	 */
	@RequestMapping("aaa")
	public String searchAll(@RequestParam(name = "search", defaultValue = "") String search, HttpSession session) {
		try {
			search = new String(search.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Bug> list = productServiceImpl.search(search);
		if (list == null) {
			session.setAttribute("aaa", null);
		} else if (list.size() == 0) {
			session.setAttribute("aaa", null);
		} else {
			System.out.println(list.size());
			session.setAttribute("aaa", list);
		}

		// System.out.println("aaa");
		return "searchResultDemo";
	}
}
