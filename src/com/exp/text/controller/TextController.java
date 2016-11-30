package com.exp.text.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.Bug;
import com.exp.text.service.TextServiceImpl;
import com.framework.EncodingTool;
import com.framework.Page;

@Controller
@RequestMapping("/test")
public class TextController {

	@Resource
	private TextServiceImpl textServiceImpl;
	EncodingTool encode = new EncodingTool();
	private Object response;

	@RequestMapping(value="/list",method=RequestMethod.POST)
	public void list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "titleText", defaultValue = "") String searchParam, HttpServletRequest request,
			Model model, HttpServletResponse response) {
		System.out.println("controller");
		Page<Bug> page;
		searchParam = encode.encodeStr(searchParam);
		System.out.println(searchParam);
		if (searchParam == null || "".equals(searchParam)) {
			page = this.textServiceImpl.listTest(pageNum, 8, null);
		} else {
			page = this.textServiceImpl.listTest(pageNum, 8, new Object[] { searchParam });
		}
		request.setAttribute("pageText", page);
		request.setAttribute("searchParam", searchParam);
		try {
			StringBuilder sb = new StringBuilder();
			PrintWriter out = response.getWriter();
			out.println();
			if (page != null) {
				System.out.println("查询内容不为空");
				sb.append("<ul>");
				for (int i = 0; i < 5; i++) {
					sb.append("<li>"+page.getList()+"</li>");
				}
				sb.append("</ul>");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
