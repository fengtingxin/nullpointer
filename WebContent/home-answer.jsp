<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-gb" dir="ltr" class="uk-notouch">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
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
<link rel="stylesheet" type="text/css" href="${ctx}/css/paging.css">
<style id="themeStyle"></style>

</head>
<body>
	<%@ include file="nav.jsp" %>

	<!-- Start of Header -->
	<!--导航栏完成-->
	<!-- End of Header -->
	<div class="container" style="padding-top: 20px;">
		<div class="col-md-3 column">

			<div class="col-md-11 column">
				<div class="example"
					style="text-align: center; background-color: #fafafa;">
					<div class="container">
						<img class="img-circle" width="100" height="100"
							src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
							alt="头像">

					</div>

					<h3>${loginUser.loginName}</h3>
					<c:if
						test="${ loginUser.userInfo.userInfoDescribe == null || loginUser.userInfo.userInfoDescribe == ''}">
						<p>这家伙很懒 什么都没有留下</p>
					</c:if>
					<p>${loginUser.userInfo.userInfoDescribe}</p>
				</div>
				<div class="col-md-12">


					<nav class="menu" data-toggle="menu"
						style="width: 200px;margin-top:20px;margin-bottom: 20px;">
					<ul class="nav nav-primary">
						<li class="nav-heading">个人中心</li>
						<li class="active"><a href="${ctx }/home.jsp"><i
								class="icon icon-home"></i> 我的主页</a></li>
						<li><a href="${ctx }/accountSetting.jsp"><i
								class="icon-user"></i> 账号设置</a></li>
					<li><a href="${ctx }/question/findQuestionByTime?userInfoId=${loginUser.loginUserId}"><i
								class="icon icon-question-sign"></i> 我的问题<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/answer/findAnswerByTime?userInfoId=${loginUser.loginUserId}"><i
								class="icon icon-reply"></i> 我的回答<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/comment/findCommentByTime?userInfoId=${loginUser.loginUserId}"><i
								class="icon icon-comments"></i> 我的评论<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/share/shareByTime?userInfoId=${loginUser.loginUserId}"><i
								class="icon icon-share"></i> 我的分享<span
								class="label label-badge label-success">4</span></a></li>
					</ul>
					</nav>
				</div>
			</div>

		</div>
		<div class="col-md-9 column">
			<div class="alert alert-success with-icon alert-dismissable">
				<i class="icon-user"></i>
				<div class="content">
					hi!&nbsp;&nbsp;&nbsp;${loginUser.loginName}&nbsp;&nbsp;&nbsp;你已经在这里度过了${day}天${hour}小时${min}分${second }秒
				</div>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">×</button>
			</div>

			<div class="items">
				<h2 class="header-dividing">我回答的问题</h2>
				<c:forEach items="${pages.list}" var="p">
					<div class="item">
						<div class="item-heading">
							<c:set var="qa" value="${p.question.tags}"></c:set>

							<c:forEach var="tt" items="${qa}">
								<div class="pull-right label label-success">${tt.tagName }</div>
							</c:forEach>
							<c:set var="qb" value="${p.question.questionTitle}"></c:set>
							<a href="${ctx }/question/findone?questionId=${p.question.questionId}">${qb}</a>
						</div>
						<div class="item-footer">
							<a href="${ctx }/question/findone?questionId=${p.question.questionId}" class="text-muted">
							<p>${p.answerContent }</p>
							<i class="icon-comments"></i>
								${fn:length(p.question.answers)}</a> &nbsp; <span class="text-muted"> <fmt:formatDate
									value="${p.answerPublishTime }" pattern="yyyy-MM-dd HH:mm" />
							</span>
						</div>
					</div>
				</c:forEach>
				<ul class="pager">
					<li class="previous"><a
						href="${ctx}/answer/findAnswerByTime?pageNum=${pages.prePageNum}&userInfoId=${loginUser.loginUserId}">«</a></li>
					<c:forEach begin="1" end="${pages.totalPageNum }" var="pageNum">
					<c:if test="${pageNum ==pages.currentPageNum}">
					<li class="active"><a name="pagen"
							href="${ctx }/answer/findAnswerByTime?pageNum=${pageNum }&userInfoId=${loginUser.loginUserId}">${pageNum }</a></li>
					</c:if>
					<c:if test="${pageNum !=pages.currentPageNum}">
					<li><a name="pagen"
							href="${ctx }/answer/findAnswerByTime?pageNum=${pageNum }&userInfoId=${loginUser.loginUserId}">${pageNum }</a></li>
					</c:if>
						
					</c:forEach>

					<li class="next"><a
						href="${ctx}/answer/findAnswerByTime?pageNum=${pages.nextPageNum}&userInfoId=${loginUser.loginUserId}">»</a></li>
				</ul>
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
<script src="${ctx}/assets/jquery.js"></script>
<script src="${ctx}/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx}/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx}/docs/js/zui.min.js"></script>
<script src="${ctx}/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx}/assets/prettify/prettify.js"></script>
<script src="${ctx}/assets/marked/marked.min.js"></script>

<script type="text/javascript" src="${ctx}/js/query.js"></script>
<script type="text/javascript" src="${ctx}/js/paging.js"></script>
<script>
	$('#pageTool').Paging({
		pagesize : 10,
		count : 100,
		callback : function(page, size, count) {
			console.log(arguments)
		}
	});
	$('#pageToolbar').Paging({
		pagesize : 10,
		count : 85,
		toolbar : true
	});
</script>

</html>