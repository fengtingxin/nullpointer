<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<style id="themeStyle"></style>

</head>
<body>
	<!-- Start of Header -->
	<%@ include file="nav.jsp"%>
	<!--导航栏完成-->
	<c:if test="${empty loginUser}">
		<%
			response.sendRedirect("../login.jsp");
		%>
	</c:if>
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
					<p>
					${fn:substring(loginUser.userInfo.userInfoDescribe,0,15)}
									<c:if test="${fn:length(loginUser.userInfo.userInfoDescribe) >15}">...</c:if>
					</p>
				</div>
				<div class="col-md-12">


					<nav class="menu" data-toggle="menu"
						style="width: 200px;margin-top:20px;margin-bottom: 20px;">
					<ul class="nav nav-primary">
						<li class="nav-heading">个人中心</li>
						<li><a href="${ctx}/home"><i class="icon icon-home"></i>
								我的主页</a></li>
						<li><a href="${ctx}/accountSetting.jsp"><i
								class="icon-user"></i> 账号设置</a></li>
						<li><a
							href="${ctx }/question/findQuestionByTime"><i
								class="icon icon-question-sign"></i> 我的问题</a></li>
						<li><a
							href="${ctx }/answer/findAnswerByTime"><i
								class="icon icon-reply"></i> 我的回答</a></li>
						<li><a
							href="${ctx }/comment/findCommentByTime"><i
								class="icon icon-comments"></i> 我的评论</a></li>
						<li class="active"><a
							href="${ctx }/share/shareByTime"><i
								class="icon icon-share"></i> 我的分享</a></li>
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
				<h2 class="header-dividing">我的分享</h2>
				<c:if test="${empty pagesShare.list}">
					<h4>暂时没有数据</h4>
				</c:if>
				<c:if test="${not empty pagesShare.list}">
				<c:forEach items="${pagesShare.list}" var="p">
					<div class="item">
						<div class="item-heading">
							<!-- 如果未通过审核显示未通过审核 -->
							<c:if test=""></c:if>
							<!-- 如果通过审核显示通过审核 -->
							<c:if test=""></c:if>
							<!-- 如果未通过审核显示未审核 -->
							<c:if test=""></c:if>
							<c:set var="tags" value="${p.tags}"></c:set>
							<dd class="pull-right">
								<c:forEach var="tt" items="${tags}" varStatus="i">
									<c:if test="${i.count%3==1 }">
										<span class="label label-success">${tt.tagName}</span>
									</c:if>
									<c:if test="${i.count%3==2 }">
										<span class="label label-warning">${tt.tagName}</span>
									</c:if>
									<c:if test="${i.count%3==0}">
										<span class="label label-info">${tt.tagName}</span>
									</c:if>
								</c:forEach>
							</dd>
							<h4>
								<a href="${ctx }/bug/findone?bugId=${p.bugId}">${p.bugTitle}</a>
							</h4>
						</div>
						<div class="item-content">
							<div class="text">${p.bugDescribe}</div>
						</div>
						<div class="item-footer">
							<a href="${ctx }/bug/findone?bugId=${p.bugId}" class="text-muted"><i
								class="icon-comments"></i> ${fn:length(question.comments)} <!-- 汤文茹将此处bug的点赞数修改为评论数 --></a>
							&nbsp; <span class="text-muted"> <fmt:formatDate
									value="${p.bugPublishTime }" pattern="yyyy-MM-dd HH:mm" /> <!-- 汤文茹规范了bug的发表时间 -->
							</span>
						</div>
					</div>
				</c:forEach>

				<ul class="pager">
					<li class="previous"><a
						href="${ctx}/share/shareByTime?pageNum=${pagesShare.prePageNum}">«</a></li>
					<c:forEach begin="1" end="${pagesShare.totalPageNum }"
						var="pageNum">
						<c:if test="${pageNum ==pagesShare.currentPageNum}">
							<li class="active"><a name="pagen"
								href="${ctx }/share/shareByTime?pageNum=${pageNum }">${pageNum }</a></li>
						</c:if>
						<c:if test="${pageNum !=pagesShare.currentPageNum}">
							<li><a name="pagen"
								href="${ctx }/share/shareByTime?pageNum=${pageNum }">${pageNum }</a></li>
						</c:if>
					</c:forEach>

					<li class="next"><a
						href="${ctx}/share/shareByTime?pageNum=${pagesShare.nextPageNum}">»</a></li>
				</ul>
				</c:if>
			</div>
		</div>
	</div>
	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>

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

</script>

</html>