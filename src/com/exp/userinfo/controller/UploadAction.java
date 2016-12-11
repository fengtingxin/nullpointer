package com.exp.userinfo.controller;
/**
 * @author Ray_1
 * 功能：头像上传与头像裁剪的控制类
 * 日期：16/11/19
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.exp.entity.LoginUser;
import com.exp.entity.UserInfo;
import com.exp.userinfo.service.UserInfoServiceImpl;
import com.framework.ImageUtil;

@Controller
@RequestMapping("/userimg")
public class UploadAction {
	/**
	 * @author Ray_1 将裁剪的图片放到指定的文件夹下
	 * @param x1
	 * @param y1
	 * @param w
	 * @param h
	 * @param image_file
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Resource
	private UserInfoServiceImpl userInfoServiceImpl;

	@RequestMapping("/upload")
	public String execute(@RequestParam("x1") String x1, @RequestParam("y1") String y1, @RequestParam("w") String w,
			@RequestParam("h") String h, @RequestParam("image_file") MultipartFile image_file, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
		if(loginUser==null){
			return "redirect:index";
		}
		if (null == session.getAttribute("loginUser") || session.getAttribute("loginUser").equals("null")) {
			request.getRequestDispatcher("loginUser/login").forward(request, response);
		}
		UserInfo userInfo = loginUser.getUserInfo();
		String realpath = System.getProperty("b2cweb.root") + "imgUp";
		System.out.println(realpath);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		if (image_file.getOriginalFilename() != null) {
			Date date = new Date();// 获取当前时间
			String filename = image_file.getOriginalFilename();
			filename = sdf.format(date) + "_" + filename;// 重命名原始图片
			String forname = rename(image_file, filename);
			System.out.println("原图" + filename);
			File savefile = new File(new File(realpath), filename);// 创建图片对象
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			filename = "cuted_" + filename;// 重命名剪裁后的图片
			String imageContentType = filename.substring(filename.indexOf(".") + 1, filename.length());// L图片格式String，如png
			savefile.getPath();
			System.out.println("realPath:"+realpath);
			System.out.println("fileName:"+filename);
			new File(new File(realpath), filename).getPath();
			ImageUtil.parseCutedImage(x1, y1, w, h, imageContentType, savefile.getPath(),
					new File(new File(realpath), filename).getPath());
			// 保存成功信息
			System.out.println("文件上传成功");
			// 保存到数据库
			userInfo.setUserInfoHeadPortrait(filename);
			loginUser.setUserInfo(userInfo);
			session.setAttribute("loginUser", loginUser);
			this.userInfoServiceImpl.updateImgUrl(loginUser.getLoginUserId(), filename);
		}
		return "accountSetting";
	}

	/**
	 * @author Ray_1 对图片进行重命名，转存到指定路径下
	 * @param f
	 * @param name
	 * @return
	 */
	public String rename(MultipartFile f, String name) {
		File f1 = new File(System.getProperty("b2cweb.root") + "imgUp\\" + name);
		try {
			f.transferTo(f1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
}
