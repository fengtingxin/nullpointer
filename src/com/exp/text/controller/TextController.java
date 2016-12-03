package com.exp.text.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

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
	/**
	 * @author Ray_1功能：搜索下来框
	 * @param pageNum
	 * @param searchParam
	 * @param request
	 * @param model
	 * @param response
	 */

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void list(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(name = "title", defaultValue = "") String searchParam, HttpServletRequest request,
			Model model, HttpServletResponse response) {
		
			System.out.println("searchParam为" + searchParam);
		try {
			// 这里不设置编码会有乱码
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}

		Page<Bug> page = null;
		searchParam = EncodingTool.encodeStr(searchParam);
		// System.out.println("searchParam"+searchParam);
		if (searchParam == null || "".equals(searchParam)) {
			page = this.textServiceImpl.listTest(pageNum, 4, null);
		} else {
			page = this.textServiceImpl.listTest(pageNum, 4, new Object[] { searchParam });
		}
		request.setAttribute("searchParam", searchParam);
		try {
			StringBuilder sb = new StringBuilder();
			System.out.println("触发controller");
			if (page != null) {
				System.out.println("查询内容不为空");
				sb.append("<ul>");
				List<Bug> bugs = page.getList();
				for (Bug bug : bugs) {
					String bugtitle = bug.getBugTitle();
					System.out.println(bugtitle);
					if (bugtitle.length() > 40)
						sb.append("<li>" + bugtitle.substring(0, 40) + "</li>");
				}

				System.out.println(sb.toString());
				response.getWriter().write(sb.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
