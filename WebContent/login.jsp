<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>登录页面</title>
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<link href="${ctx}/resources/style/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="${ctx }/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/md5.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/page_login.js?lang=zh" id="url"
	data="<%= request.getHeader("REFERER")%>"></script>
</head>
<!--[if IE]>
  <script src="resources/js/html5.js"></script>
<![endif]-->
<!--[if lte IE 6]>
	<script src="resources/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
	<script>
	  DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
	</script>
<![endif]-->
<body class="loginbody">
	<div class="dataEye">
		<div>
			<a href="${ctx }/index"><img
				src="${ctx }/resources/images/logo_touming.gif" width="240px"
				height="60px" /></a>
		</div>
		<div class="loginbox">
			<div id="bug-manager">
				<h3>nullpointer网站登录</h3>
			</div>
			<div class="login-content">
				<div class="loginbox-title"></div>
				<form id="signupForm">
					<div class="login-error"></div>
					<div class="row">
						<label class="field">用户名/电子邮箱</label> <input type="text"
							class="input-text-user input-click" name="loginName"
							id="loginName">
					</div>
					<div class="row">
						<label class="field">密码</label> <input type="password"
							class="input-text-password input-click" name="password"
							id="password">
					</div>
					<div class="row">
						<label class="field">验证码</label> <input type="text"
							class="input-text-user input-click" name="codeValue"
							id="codeValue" style="width: 120px; float: left"> <img
							src="${ctx }/MakeCodeServlet" width="150px" height="40px"
							style="float: left" />
					</div>
					<div class="row btnArea">
						<a class="login-btn" id="submit"
							style="text-decoration: none">登录</a>
					</div>
					<div class="row tips">
						<a href="${ctx }/admin/login.jsp" class="link">管理员登录</a>
					</div>
				</form>
			</div>
			<div class="go-regist">
				还没有账号？请 <a href="register.jsp">立即注册</a>
			</div>
		</div>

		<div id="footer">Copyright © 1998-2016 nullpointer All Rights
			Reserved nullpointer 版权所有</div>
	</div>
	<div class="loading">
		<div class="mask">
			<div class="loading-img">
				<img src="${ctx }/resources/images/loading.gif" width="31"
					height="31">
			</div>
		</div>
	</div>
</body>
<!-- 回车登陆 -->
<script type="text/javascript">
	$(document).keyup(function(e) {
		if (e.keyCode == 13) {
			$("#submit").click()
		}
	});
</script>
</html>