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
<link rel="stylesheet" type="text/css" href="${ctx}/css/searchKuang.css">
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
<script type="text/javascript"
	src="${ctx }/js/hibernateSearch.js?lang=zh" id="rescourse"
	data="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>"></script>
</head>
<body>
	<%
		if (session.getAttribute("bugHonorList") == null) {
			request.getRequestDispatcher("index").forward(request, response);
		}
	%>
	<%@ include file="nav.jsp"%>

	<!--导航栏完成-->
	<!-- 搜索栏开始 -->
	<%@include file="search-area.jsp"%>
	<!-- 搜索栏完成 -->
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
											<a
												href="${ctx }/question/findone?questionId=${question.questionId}"
												data-toggle="tooltip" title="${question.questionTitle}">
												${fn:substring(question.questionTitle,0,25)} <c:if
													test="${fn:length(question.questionTitle) >25}">...</c:if>
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
												${fn:substring(bug.bugTitle, 0,25)} <c:if
													test="${fn:length(bug.bugTitle)>25}">...</c:if>
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
				<section class="widget">
					<div class="quick-links-widget">
						<h3 class="title">
							<img alt="${ctx }/images/honor.png"
								src="${ctx }/images/honor.png">&nbsp;&nbsp;荣&nbsp;誉&nbsp;榜
						</h3>
						<ul class="list-group" style="margin-top:20px;">
							<c:set var="userList" value="${sessionScope.userList}"></c:set>
							<c:if test="${userList!=null }">
								<c:forEach items="${userList}" var="userInfo" varStatus="status">
									<li><span class="label label-warning">${status.count }</span>&nbsp;&nbsp;
										<a
										href="${ctx }/hishome?loginName=${userInfo.loginUser.loginName}">
											<img alt=""
											src="${ctx}/imgUp/${userInfo.userInfoHeadPortrait}"
											width="30px" height="30px" class="img-circle">
											${userInfo.loginUser.loginName }
									</a> &nbsp;&nbsp;<span class="label label-badge">荣誉值&nbsp;${ userInfo.userInfoHonorCount}</span>
									</li>
								</c:forEach>
							</c:if>

						</ul>
					</div>
				</section>

				<section class="widget">
					<h3 class="title">Tags</h3>
					<div class="tagcloud">
						<c:set var="tag" value="${sessionScope.tagList}"></c:set>
						<c:forEach var="tt" items="${tag}">
							<c:if test="${tt.tagName =='C++'}">
								<a href="${ctx}/bug/listadmin?tagName=C%%2B%2B"
									class="btn btn-primary">${tt.tagName}</a>
							</c:if>
							<c:if test="${tt.tagName !='C++'}">
								<a href="${ctx}/bug/listadmin?tagName=${tt.tagName}"
									class="btn btn-primary">${tt.tagName}</a>
							</c:if>

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
						<h3 class="title">最新消息</h3>
						<div id="twitter_update_list">
							<ul>
								<li>nullpointer_ver1.0更新于2016年12月</li>
							</ul>
						</div>

					</section>
				</div>

				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">项目小组</h3>
						<ul>
							<li>EXP项目小组</li>
						</ul>
					</section>
				</div>
				<div class="col-md-3 column">
					<section class="widget">
						<h3 class="title">联系我们</h3>
						<div class="textwidget">
							<p>你有什么问题建议，欢迎联系我们。</p>
							<p>联系方式：2601219524@qq.com</p>
						</div>
					</section>
				</div>


			</div>
		</div>
	</footer>
	<!-- end of #footer -->

	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>

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
<script src="${ctx}/js/hibernateSearch.js"></script>

</html>