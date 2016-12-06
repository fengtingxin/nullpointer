package com.exp.hibernateSearch.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exp.entity.Bug;
import com.exp.hibernateSearch.service.HibernateSearchServiceImpl;
import com.framework.EncodingTool;

@Controller
@RequestMapping("hibernateSearch")
public class HibernateSearchController {
	@Resource
	private HibernateSearchServiceImpl hibernateSearchServiceImpl;

	@RequestMapping(value = "/findBugByValue", method = RequestMethod.POST)
	public void searchAll(@RequestParam(name = "title", defaultValue = "") String search, HttpServletRequest request,
			Model model, HttpServletResponse response, HttpSession session) {
		System.out.println("searchParam为" + search);
		search = EncodingTool.encodeStr(search);
		if (search == "" || search.length() == 0) {
			return;
		}

		try {
			// 这里不设置编码会有乱码
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		List<Bug> bugs = hibernateSearchServiceImpl.searchBug(search);
		System.out.println("list长度为" + bugs.size());

		try {
			StringBuilder sb = new StringBuilder();
			System.out.println("这是用hibernateSearch实现的");
			if (bugs != null) {

				if (bugs.size() <= 4) {
					for (Bug bug : bugs) {
						String bugtitle = bug.getBugTitle();
						System.out.println(bugtitle);
						if (bugs.size() <= 4) {
							if (bugtitle.length() > 120) {
								sb.append("<li><a>" + bugtitle.substring(0, 120) + "</a></li>");
							} else {
								sb.append("<li><a>" + bugtitle + "</a></li>");
							}
						}
					}
				} else {
					for (int i = 0; i <4; i++) {
						if (bugs.get(i).getBugTitle().length() >= 120) {
							sb.append("<li><a>" + bugs.get(i).getBugTitle().substring(0, 120) + "</a></li>");
						} else {
							sb.append("<li><a>" + bugs.get(i).getBugTitle() + "</a></li>");
						}
					}
				}
			}
			System.out.println(sb.toString());
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
