<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
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
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<!-- Style Sheet-->

<link href="${ctx }/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui-theme.css">
<link href="${ctx }/docs/css/doc.min.css" rel="stylesheet">
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
</head>

<body>
	<%@ include file="nav.jsp" %>
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
	<div class="page-container">
		<div class="container">
			<div class="row">

				<!-- start of page content -->
				<div class="col-md-8 column">
					<article class="type-page hentry clearfix">
					<h1 class="post-title">
						<a href="#">建议反馈</a>
					</h1>
					<hr>
					<p>欢迎提出您在使用过程中遇到的问题或宝贵建议，感谢您对nullponiter的支持！</p>
					</article>

					<div class="example" style="margin-top: 20px;">
						<form class="form-horizontal" action="advice" method="post">
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">账号</label>
								<div class="col-md-6 col-sm-10">
								<c:if test="${not empty loginUser }">
									<input type="text" class="form-control" name="name" id="exampleReadonlyInput" placeholder="${loginUser.loginName }" value="${loginUser.loginName }" readonly/>
								</c:if>
								<c:if test="${empty loginUser }">
								<input type="text" class="form-control"
										id="exampleInputAccount4" name="name" placeholder="用户名">
								</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">您的邮箱</label>
								<div class="col-md-6 col-sm-10">
								<c:if test="${not empty loginUser }">
								
									<input type="text" class="form-control"
										id="exampleInputAccount4" name="email" placeholder="${loginUser.loginEmail}" value="${loginUser.loginEmail}" readonly/>
								</c:if>
								<c:if test="${empty loginUser }">
								<input type="text" class="form-control"
										id="exampleInputAccount4" name="email" placeholder="邮箱地址">
								</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">主题</label>
								<div class="col-md-6 col-sm-10">
									<input type="text" class="form-control"
										id="exampleInputAccount4" name="theme" placeholder="主题">
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">您的意见</label>
								<div class="col-md-6 col-sm-10">
									<textarea type="text" class="form-control" rows="5"
										id="exampleInputAccount4" name="advice" placeholder="意见"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">提交</button>
								</div>
							</div>
						</form>
					</div>

				</div>
				<!-- end of page content -->


				<c:if test="${not empty adviceReminder }">
					<!-- 提示部分！ -->
					<script type="text/javascript">
	window.onload=function(){
		new $.zui.Messager('<%=request.getAttribute("remindMsg")%>', {
										icon : 'bell', //定义图标
										fade : 'true',
										type : 'primary', // 定义颜色主题

							}).show();
						}
					</script>
				</c:if>
				<!-- start of sidebar -->
				<div class="col-md-4 column">



					<section class="widget">
					<h3 class="title">问题推荐</h3>
					<ul class="articles">
						<c:forEach var="question" items="${questionList}">
							<li class="article-entry standard">
								<h4>
									<a href="${ctx }/question/findone?questionId=${question.questionId}" data-toggle="tooltip"
										title="${question.questionTitle}">
										${fn:substring(question.questionTitle,0,50)} <c:if
											test="${fn:length(question.questionTitle) >50}">...</c:if>
									</a>
								</h4> <span class="article-meta"> <fmt:formatDate
										value="${question.questionPublishTime}" pattern="yyyy-MM-dd" />
									<c:set var="tag" value="${question.tags }"></c:set> <c:forEach
										var="tt" items="${tag}">
										<a href="#" title="${tt.tagName}"> ${tt.tagName} &nbsp;</a>
									</c:forEach>
							</span> <span class="like-count">${question.questionLikeNum }</span>
							</li>
						</c:forEach>
					</ul>
					</section>
				</div>
			</div>
		</div>
	</div>

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
<script src="${ctx }/assets/jquery.js"></script>
<script src="${ctx }/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx }/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx }/docs/js/zui.min.js"></script>
<script src="${ctx }/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx }/assets/prettify/prettify.js"></script>
<script src="${ctx }/assets/marked/marked.min.js"></script>
</html>
