<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
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
	<%
		if (session.getAttribute("bugHonorList") == null) {
			request.getRequestDispatcher("index").forward(request, response);
		}
	%>
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
						<li><a href="${ctx}/bug/listadmin">BUGS</a></li>
						<li><a href="question/list_new">技术问答</a></li>
						<li><a href="${ctx}/contact">帮助</a></li>
						<li><a href="${ctx}/login.jsp">登陆/注册</a></li>
						<!-- 导航中的下拉菜单 -->
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <c:if test="${loginUser==null}">
									<img src="${ctx}/imgUp/default.jpg" width="20px" height="20px"
										class="img-circle" />
								</c:if> <c:if test="${loginUser!=null}">

									<img
										src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
										width="20px" height="20px" class="img-circle" />
								</c:if><b class="caret"></b></a>
							<ul class="dropdown-menu" role="menu" style="text-align: center;">
								<li><a href="${ctx}/home">我的主页</a></li>
								<li><a href="${ctx}/home-question.jsp">信息管理</a></li>
								<li><a href="${ctx}/accountSetting.jsp">账号设置</a></li>
								<li><a href="${ctx}/contact">建议反馈</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- END .navbar-collapse -->
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
	<div class="container" style="padding-top: 20px; padding-bottom: 25px;">
		<div class="row clearfix">
			<!-- start of page content -->
			<div class="col-md-8 column" style="margin-top: 20px;">
				<!-- Basic Home Page Template -->
				<div class="col-md-6 column">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h5>问答推荐</h5>
						</div>
						<div class="panel-body">
							<ul class="articles">
								<c:set var="questionHonorList"
									value="${sessionScope.questionHonorList}"></c:set>
								<c:forEach var="question" items="${questionHonorList}">
									<li class="article-entry standard">
										<h4>
											<a href="single.html" data-toggle="tooltip"
												title="${question.questionTitle}">
												${fn:substring(question.questionTitle,0,50)} <c:if
													test="${fn:length(question.questionTitle) >50}">...</c:if>
											</a>
										</h4> <span class="article-meta"><fmt:formatDate
												value="${question.questionPublishTime}" pattern="yyyy-MM-dd" />

											<c:set var="tag" value="${question.tags }"></c:set> <c:forEach
												var="tt" items="${tag}">
												<a href="#" title="${tt.tagName}"> ${tt.tagName} &nbsp;
												</a>
											</c:forEach> </span> <span class="like-count">${question.questionLikeNum }</span>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>


				</div>
				<div class="col-md-6 column">
					<div class="panel panel-success">
						<div class="panel-heading">
							<h5>BUG专题推荐</h5>
						</div>
						<div class="panel-body">
							<ul class="articles">
								<c:set var="bugHonorList" value="${sessionScope.bugHonorList}"></c:set>
								<c:forEach items="${bugHonorList}" var="bug">
									<li class="article-entry standard">
										<h4>
											<a href="${ctx }/bug/findone?bugId=${bug.bugId}"
												data-toggle="tooltip" title="${bug.bugTitle}">
												${fn:substring(bug.bugTitle, 0, 50)} <c:if
													test="${fn:length(bug.bugTitle)>50}">...</c:if>
											</a>
										</h4> <span class="article-meta"> <fmt:formatDate
												value="${bug.bugPublishTime}" pattern="yyyy-MM-dd" /> <c:set
												var="tag" value="${bug.tags }"></c:set> <c:forEach var="tt"
												items="${tag }">
												<a href="#" title="${tt.tagName }"> ${tt.tagName }
													&nbsp; </a>
											</c:forEach>
									</span> <span class="like-count">${bug.bugLikeNum}</span>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>


				</div>


			</div>
			<!-- end of page content -->


			<!-- start of sidebar -->
			<aside class="col-md-4 column">

				<section class="widget"></section>

				<section class="widget">
					<div class="quick-links-widget">
						<h3 class="title">挖掘机技术哪家强？</h3>

					</div>
				</section>

				<section class="widget">
					<h3 class="title">Tags</h3>
					<div class="tagcloud">
						<c:set var="tag" value="${sessionScope.tagList}"></c:set>
						<c:forEach var="tt" items="${tag}">
							<a href="${ctx}/bug-list-admin.jsp&tagName = ${tt.tagName}"
								class="btn btn-primary">${tt.tagName}</a>
						</c:forEach>
					</div>
				</section>

			</aside>
			<!-- end of sidebar -->
		</div>
	</div>

	<footer id="footer-wrapper">
		<div id="footer" class="container">
			<div class="row">
				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">我们是什么</h3>
						<div class="textwidget">
							<p>我们的主要目标是为程序员解决各种各样的BUG</p>
							<p>你可以在这里查你所想，想你所问，还可以看到各种各样的BUG推荐和问答推荐</p>
						</div>
					</section>
				</div>

				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">目录</h3>
						<ul>
							<li><a href="#" title="Lorem ipsum dolor sit amet,">主页</a></li>
							<li><a href="#" title="Lorem ipsum dolor sit amet,">BUGS</a></li>
							<li><a href="#" title="Lorem ipsum dolor sit amet,">技术问答</a></li>
							<li><a href="#" title="Lorem ipsum dolor sit amet, ">个人中心</a></li>
							<li><a href="#" title="Lorem ipsum dolor sit amet,">信息反馈</a></li>
							<li><a href="#" title="Lorem ipsum dolor sit amet,">联系我们</a></li>
						</ul>
					</section>
				</div>

				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">最新消息</h3>
						<div id="twitter_update_list">
							<ul>
								<li>呵呵哒</li>
							</ul>
						</div>

					</section>
				</div>

				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">Flickr Photos</h3>
						<div class="flickr-photos" id="basicuse"></div>
					</section>
				</div>

			</div>
		</div>
	</footer>
	<!-- end of #footer -->

	<!-- Footer Bottom -->
	<div id="footer-bottom-wrapper">
		<div id="footer-bottom" class="container">
			<div class="row">
				<div class="col-md-6 column">
					<p class="copyright">
						Copyright © 2013. All Rights Reserved by KnowledgeBase.Collect
						from <a href="#" title="旋风小组" target="_blank">EXP小组</a>
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
<script type="text/javascript">
	$('[data-toggle="tooltip"]').tooltip();
	window.open = "${ctx}/index";
</script>
</html>