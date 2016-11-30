package com.exp.product.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.TbGoods;
import com.exp.product.service.ProductServiceImpl;

@Controller
@RequestMapping("product")
public class ProductController {
	@Resource
	private ProductServiceImpl productServiceImpl;

	@RequestMapping("aaa")
	public String searchAll(@RequestParam(name = "search", defaultValue = "") String search, HttpSession session) {
		try {
			search = new String(search.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TbGoods> list = productServiceImpl.search(search);
		if (list == null) {
			session.setAttribute("aaa", null);
		} else {
			session.setAttribute("aaa", list);
		}

		// System.out.println("aaa");
		return "searchResultDemo";
	}
}
