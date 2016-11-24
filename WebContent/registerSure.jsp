<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 设置默认跳转时间为10秒 -->
<!--  <meta http-equiv="refresh" content="10;url=${ctx }/login.jsp" />-->
<!-- META TAGS -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${registerTitle}</title>
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<!-- Style Sheet-->
<!-- 倒计时 -->
<script type="text/javascript" src="${ctx }/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/time_js.js"></script>

<link type="text/css" rel="stylesheet" href="${ctx }/css/time_css.css" />
<script type="text/javascript">
	countDown();
</script>

<link href="${ctx }/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui-theme.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/main5152.css">
<style id="themeStyle"></style>
<style type="text/css">
#top .primary-color, a, #cancel-comment-reply-link, .blog-tags a:hover,
	.relThumb a:hover strong, .flex_column h1, .flex_column h2,
	.flex_column h3, .flex_column h4, .flex_column h5, .flex_column h6,
	#top #wrap_all .tweet-text a, #top #js_sort_items a.active_sort,
	.callout a:hover {
	color: #f0b70c;
}
</style>
<script type="text/javascript" language="javascript">
	$(function() {
		$().popover('show');
		 window.setTimeout("CloseWebPage()",10000);
	})
	function CloseWebPage(){
       console.log("wuwuwuuw");
       quitBox('quit');
     }
	function quitBox(cmd) 
	{   
	    /*if (cmd=='quit')
	    {
	    	open(' ', '_self').close();
	    }
	    return false;*/
		// ==UserScript==
		// @name        window.close demo
		// @include     http://YOUR_SERVER.COM/YOUR_PATH/*
		// @grant       GM_addStyle
		// ==/UserScript==
		var win = window.open("${ctx}/login.jsp", "_self");
		win.close();
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse" role="navigation"
		style="margin-bottom: 0px;">
	<div class="center-block">
		<div class="container">
			<!-- 导航头部 -->
			<div class="navbar-header">
				<!-- 移动设备上的导航切换按钮 -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse-example">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<!-- 品牌名称或logo -->
				<img src="${ctx }/images/logo.png" alt="nullpointer" width="200"
					style="margin-top: 3px;">
			</div>
			<!-- 导航项目 -->
			<div class="collapse navbar-collapse navbar-collapse-example">
				<ul class="nav navbar-nav navbar-right">
					<li class="current-menu-item"><a href="index.html">主页</a></li>
					<li><a href="bug-list-admin.html">BUGS</a></li>
					<li><a href="q_a_list.html">技术问答</a></li>
					<li><a href="contact.html">帮助</a></li>
					<li><a href="login.html">登陆/注册</a></li>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="your/nice/url"
						class="dropdown-toggle" data-toggle="dropdown"><img
							src="images/touxiang.jpg" width="20px" height="20px"
							class="img-circle" /> <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="home.html">我的主页</a></li>
							<li><a href="home-question.html">信息管理</a></li>
							<li><a href="page.html">账号设置</a></li>
							<li><a href="contact.html">建议反馈</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	</nav>
	<!--导航栏完成-->
	<div class="container">
		<div class="row clearfix">

			<div class="game_time" style="float: left;">
				<div class="hold">
					<div class="pie pie1"></div>
				</div>
				<div class="hold">
					<div class="pie pie2"></div>
				</div>
				<div class="bg"></div>
				<div class="time"></div>

			</div>
			<div style="margin-left: 400px;">
				<h2>
					Welcome to nullpointer <small>You will love here!</small>
				</h2>

				<h3 class="popover-title">
					${regiserWelcome }
				</h3>
				<div class="popover-content">${registerContent }</div>
			</div>

		</div>
		<div class="container">
			<article class="article"> <header>
			<dl class="dl-inline">
				<dt><%=new Date()%></dt>
				<dt>Welcome to nullpointer</dt>
			</dl>
			<section class="abstract">
			<p>
				<strong>简介：</strong> <br /> 项目名称：nullpointer-bug管理系统
				
			<hr />
					项目的提出方：EXP项目小组
				  <hr />
					项目目标：为程序员提供更好的学习和交流的平台
					</p>
			    </section>
		  		</header>
		   <blockquote>
			<section class="abstract">
		      <p>
					<strong>项目成员简介：</strong>
		      <br />
		      	PM：冯廷鑫
		      	
				<hr />
		      	产品经理：张肇霖
		      	<hr />
		      	用户体验、测试工程师：颜荣恩、汤文茹
		      	<hr />
		      	开发工程师：冯廷鑫、张肇霖、颜荣恩、汤文茹
				</p>
		    </section>
		    </blockquote>
		</article>
		</div>
	</div>


	<!--搜索框完成-->

	<!-- Footer Bottom -->
	<div id="footer-bottom-wrapper">
		<div id="footer-bottom" class="container">
			<div class="row">
				<div class="col-md-6 column">
					<p class="copyright">
						Copyright © 2013. All Rights Reserved by KnowledgeBase.Collect
						from <a href="#" title="EXP" target="_blank">EXP小组</a>
					</p>
				</div>
				<div class="col-md-6 column">
					<!-- Social Navigation -->
					<ul class="social-nav clearfix">
						<li class="linkedin"><a target="_blank" href="#"></a></li>
						<li class="stumble"><a target="_blank" href="#"></a></li>
						<li class="google"><a target="_blank" href="#"></a></li>
						<li class="deviantart"><a target="_blank" href="#"></a></li>
						<li class="flickr"><a target="_blank" href="#"></a></li>
						<li class="skype"><a target="_blank" href="skype:#?call"></a></li>
						<li class="rss"><a target="_blank" href="#"></a></li>
						<li class="twitter"><a target="_blank" href="#"></a></li>
						<li class="facebook"><a target="_blank" href="#"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</body>
<!-- script -->
				<script src="${ctx }/js/jquery.min.js"></script>
<script src="${ctx }/assets/jquery.js"></script>
<script src="${ctx }/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx }/docs/js/zui.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx }/assets/prettify/prettify.js"></script>
<script src="${ctx }/assets/marked/marked.min.js"></script>
</html>