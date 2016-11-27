<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
					<li><a href="${ctx}/contact">帮助</a></li>
					<li><a href="${ctx}/login.jsp">登陆/注册</a></li>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="your/nice/url"
						class="dropdown-toggle" data-toggle="dropdown">
						<c:if test="${loginUser==null}">
									<img src="${ctx}/imgUp/default.jpg" width="20px" height="20px"
										class="img-circle" />
								</c:if> <c:if test="${loginUser!=null}">

									<img
										src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
										width="20px" height="20px" class="img-circle" />
								</c:if>
						 <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="${ctx}/home.jsp">我的主页</a></li>
							<li><a href="${ctx}/home-question.jsp">信息管理</a></li>
							<li><a href="${ctx}/page.jsp">账号设置</a></li>
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
				<button type="button" class="btn btn-primary btn-lg" >BUG搜索</button>
				<button type="button" class="btn btn-primary btn-lg">问题搜索</button>
				<div id="search-error-container"></div>
			</form>
		</div>
	</div>
	<!--搜索框完成-->

	<div class="container" style="padding-top: 20px; padding-bottom: 25px;">

		<div class="col-md-8 column">
			<div class="example">
				<header>
				<h3>
					<i class="icon-list-ul"></i> 官方BUG查询 <small> 共${adminBugNum }条</small>
				</h3>
				</header>
				<div class="items items-hover">
				 <c:forEach items="${page.list }" var="bug">
				 <div class="item">
						<div class="item-heading">
							<h2 class="post-title">
								<a href="${ctx }/bug/findone?bugId=${bug.bugId}">${fn:substring(bug.bugTitle,0,45)} <c:if
													test="${fn:length(bug.bugTitle) >45}">...</c:if></a>
							</h2>
						</div>
						<div class="item-content">
							<div class="text">${fn:substring(bug.bugDescribe,0,100)} <c:if
													test="${fn:length(bug.bugDescribe) >100}">...</c:if></div>
						</div>
						<div class="item-footer">
							<a href="#" class="text-muted"><i class="icon-comments"></i>
								${fn:length(bug.comments)}</a> &nbsp; <a href="#" class="text-muted"><i
								class="icon-thumbs-o-up"></i> ${bug.bugLikeNum }</a> &nbsp; <span class="text-muted">
								<fmt:formatDate value="${bug.bugPublishTime }" pattern="yyyy-MM-dd" />
							</div>
					</div>
				
				</c:forEach>
				</div>
				<!--分页实现-->
				<ul class="pager pager-loose">
				<li class="previous"><a href="#">«</a></li>
				<c:forEach begin="1" end="${page.totalPageNum }" var="pageNum">
						<li><a name="pagen" href="${ctx }/bug/listadmin?pageNum=${pageNum }">${pageNum }</a></li>
					</c:forEach>
					
				<li class="next"><a href="#">»</a></li>
				</ul>
			</div>


		</div>
		<div class="col-md-4 column" style="margin-top: 30px;">

			<div class="col-md-8">
				<h2>
					<i class="icon icon-align-left"></i> 分类管理
				</h2>
				<ul class="nav nav-stacked nav-primary" style="margin-top: 20px;">
					<li class="active"><a href="${ctx}/bug/lista">官方BUG查询</a></li>
					<li><a href="${ctx}/bug/listuser">用户BUG查询</a></li>
				</ul>
			</div>
			<div class="col-md-12" style="margin-top: 20px;">
				<h2>
					<i class="icon icon-align-left"></i> Tag
				</h2>
				<div class="tagcloud">
						<c:set var="tag" value="${sessionScope.tagList}"></c:set>
						<c:forEach var="tt" items="${tag}">
							<a href="${ctx}/listadmin?tagName = ${tt.tagName}"
								class="btn btn-primary">${tt.tagName}</a>
						</c:forEach>
					</div>

			</div>
		</div>

	</div>


	<!-- end of #footer -->

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