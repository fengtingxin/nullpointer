<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en-US">
<!--<![endif]-->
<head>
<!-- META TAGS -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>nullpointer</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.png" />
<!-- Style Sheet-->

<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main5152.css">
<style id="themeStyle"></style>
<link rel="stylesheet" type="text/css" href="${ctx}/responsive5152.css">
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
				<img src="${ctx}/images/logo.png" alt="nullpointer" width="200"
					style="margin-top: 3px;">
			</div>
			<!-- 导航项目 -->
			<div class="collapse navbar-collapse navbar-collapse-example">

				<ul class="nav navbar-nav navbar-right">
					<li class="current-menu-item"><a href="${ctx}/index.jsp">主页</a></li>
					<li><a href="${ctx}/bug-list-admin.jsp">BUGS</a></li>
					<li><a href="${ctx}/q_a_list.jsp">技术问答</a></li>
					<li><a href="${ctx}/contact.jsp">帮助</a></li>
					<li><a href="${ctx}/login.jsp">登陆</a></li>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="your/nice/url"
						class="dropdown-toggle" data-toggle="dropdown"><img
							src="images/touxiang.jpg" width="20px" height="20px"
							class="img-circle" /> <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="${ctx}/home.jsp">我的主页</a></li>
							<li><a href="${ctx}/home-question.jsp">信息管理</a></li>
							<li><a href="${ctx}/page.jsp">账号设置</a></li>
							<li><a href="${ctx}/contact.jsp">建议反馈</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	</nav>
	<!--导航栏完成-->
	<!--搜索框-->
	<div class="search-area-wrapper">
		<div class="search-area container">
			<h3 class="search-header">今天你遇到什么技术难点了吗？</h3>
			<p class="search-tag-line">请在下方搜索您所遇到的问题吧！</p>
			<form id="search-form" class="search-form clearfix" method="get"
				action="#" autocomplete="off" novalidate="novalidate">
				<input class="search-term required" type="text" id="s" name="s"
					placeholder="在这里搜索问题/BUG" title="* Please enter a search term!"
					style="height: 43px;" />
				<button type="button" class="btn btn-primary btn-lg"">BUG搜索</button>
				<button type="button" class="btn btn-primary btn-lg"">问题搜索</button>
				<div id="search-error-container"></div>
			</form>
		</div>
	</div>
	<!--搜索框完成-->
	<!--文章开始-->
	<div class="container" style="padding-top: 20px; padding-bottom: 25px;">
		<div class="col col-md-8"
			style="border: 1px solid #ddd; padding: 20px;">
			<article class="article"> <header>
			<h1 class="text-center">我的C语言出现了XX问题？</h1>
			<dl class="dl-inline">

				<dd class="pull-right">
					<span class="label label-success">HTML</span> <span
						class="label label-warning">网页设计</span> <span
						class="label label-info">W3C</span> <span
						class="label label-danger"><i class="icon-eye-open"></i>
						235</span>
				</dd>
			</dl>

			</header> <section class="content">
			巴啦啦balk放假了咯jfk垃圾卡都放假了啊经费独立空间了,该咋办啊？ </section> <!--评论内容-->
			<div class="comments">
				<header>

				<h3>所有评论</h3>
				</header>
				<section class="comments-list">
				<div class="comment">
					<a href="###" class="avatar"> <i
						class="icon-camera-retro icon-2x"></i>
					</a>
					<div class="content">
						<div class="pull-right text-muted">3 个小时前</div>
						<div>
							<a href="###"><strong>张士超</strong></a>
						</div>
						<div class="text">今天玩的真开心！~~~~~~</div>
						<div class="actions">
							<a href="##">回复</a>
						</div>
					</div>
					<div class="comments-list">
						<div class="comment">
							<a href="###" class="avatar"> <i class="icon-user icon-2x"></i>
							</a>
							<div class="content">
								<div class="pull-right text-muted">2 个小时前</div>
								<div>
									<a href="###"><strong>Catouse</strong></a> <span
										class="text-muted">回复</span> <a href="###">汤汤</a>
								</div>
								<div class="text">你到底把我家钥匙放哪里了...</div>
								<div class="actions">
									<a href="##">回复</a> <a href="##">编辑</a> <a href="##">删除</a>
								</div>
							</div>
							<div class="comments-list">
								<div class="comment">
									<a href="###" class="avatar"> <i
										class="icon-yinyang icon-2x"></i>
									</a>
									<div class="content">
										<div class="pull-right text-muted">1 个小时前</div>
										<div>
											<a href="###"><strong>门口大爷</strong></a> <span
												class="text-muted">回复</span> <a href="###">Catouse</a>
										</div>
										<div class="text">不在我这儿...</div>
										<div class="actions">
											<a href="##">回复</a>
										</div>
									</div>
								</div>
								<div class="comment">
									<a href="###" class="avatar"> <i
										class="icon-cube-alt icon-2x"></i>
									</a>
									<div class="content">
										<div class="pull-right text-muted">1 个小时前</div>
										<div>
											<a href="###"><strong>队长</strong></a> <span
												class="text-muted">回复</span> <a href="###">Catouse</a>
										</div>
										<div class="text">也不在我这儿...</div>
										<div class="actions">
											<a href="##">回复</a>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="comment">
							<a href="###" class="avatar"> <i
								class="icon-heart-empty icon-2x"></i>
							</a>
							<div class="content">
								<div class="pull-right text-muted">30 分钟前</div>
								<div>
									<a href="###"><strong>华师大第一美女</strong></a> <span
										class="text-muted">回复</span> <a href="###">张士超</a>
								</div>
								<div class="text">很开心~~~</div>
								<div class="actions">
									<a href="##">回复</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				</section>
				<footer>
				<div class="reply-form" id="commentReplyForm2">
					<a href="###" class="avatar"><i class="icon-user icon-2x"></i></a>
					<form class="form">
						<div class="form-group">
							<textarea class="form-control new-comment-text" rows="2"
								placeholder="撰写评论..."></textarea>
						</div>
						<div class="form-group comment-user">

							<div class="col-md-2 pull-right">
								<button type="submit" class="btn btn-block btn-primary">提交评论</button>
							</div>
						</div>
				</div>
				</form>
			</div>
			</footer>
		</div>
		<!--评论内容结束-->
		</article>
	</div>
	</div>
	<!--文章完成-->



	<!-- Footer Bottom -->
	<div id="footer-bottom-wrapper">
		<div id="footer-bottom" class="container">
			<div class="row">
				<div class="col-md-6 column">
					<p class="copyright">
						Copyright © 2013. All Rights Reserved by KnowledgeBase.Collect
						from <a href="#" title="EXP小组" target="_blank">EXP小组</a>
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
<script src="${ctx}/assets/jquery.js"></script>
<script src="${ctx}/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx}/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx}/docs/js/zui.min.js"></script>
<script src="${ctx}/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx}/assets/prettify/prettify.js"></script>
<script src="${ctx}/assets/marked/marked.min.js"></script>
</html>