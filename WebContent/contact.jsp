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
<link rel="stylesheet" type="text/css" href="${ctx}/css/searchKuang.css">
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
<script type="text/javascript"
	src="${ctx }/js/hibernateSearch.js?lang=zh" id="rescourse"
	data="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>"></script>
</head>

<body>
	<%@ include file="nav.jsp" %>
	<!--导航栏完成-->
	<!--搜索框-->
	<%@include file="search-area.jsp"%>
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
						<form class="form-horizontal">
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">账号</label>
								<div class="col-md-6 col-sm-10">
								<c:if test="${not empty loginUser }">
									<input type="text" class="form-control" name="name" id="name" placeholder="${loginUser.loginName }" value="${loginUser.loginName }" readonly/>
								</c:if>
								<c:if test="${empty loginUser }">
								<input type="text" class="form-control"
										id="name" name="name" placeholder="用户名">
								</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">您的邮箱</label>
								<div class="col-md-6 col-sm-10">
								<c:if test="${not empty loginUser }">
								
									<input type="text" class="form-control"
										id="email" name="email" placeholder="${loginUser.loginEmail}" value="${loginUser.loginEmail}" readonly/>
								</c:if>
								<c:if test="${empty loginUser }">
								<input type="text" class="form-control"
										id="email" name="email" placeholder="邮箱地址">
								</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">主题</label>
								<div class="col-md-6 col-sm-10">
									<input type="text" class="form-control"
										id="theme" name="theme" placeholder="主题">
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputAccount4" class="col-sm-2 required">您的意见</label>
								<div class="col-md-6 col-sm-10">
									<textarea type="text" class="form-control" rows="5"
										id="advice" name="advice" placeholder="意见"></textarea>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="button" onclick="submitAdvice()" class="btn btn-primary">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- end of page content -->
<script type="text/javascript">
function submitAdvice(){
	//userName
	if($("#name").val() ==null || $("#name").val().replace(/(^s*)|(s*$)/g, "").length ==0){
		new $.zui.Messager('用户名为空！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	if($("#name").val().length>200){
		new $.zui.Messager('用户名过长！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	//email
	if($("#email").val() == null){
		new $.zui.Messager('email不能为空！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if($("#email").val().length>200 ){
		new $.zui.Messager('email不符合格式!', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	//theme
	if($("#theme").val() ==null ||$("#theme").val() =="" ){
		new $.zui.Messager('theme不能为空！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	if($("#theme").val().length>400){
		new $.zui.Messager('theme不符合格式!', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	//advice
	if($("#advice").val() ==null || $("#advice").val().replace(/(^s*)|(s*$)/g, "").length ==0){
		new $.zui.Messager('建议内容不能为空！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
	if($("#advice").val().length>400){
		new $.zui.Messager('建议内容过长!', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
		return ;
	}
		$.ajax({
			url : "/nullpointer/advice",
			type: "POST",
			method: "post",
			data : {
				name : $("#name").val(),
				email : $("#email").val(),
				theme : $("#theme").val(),
				advice : $("#advice").val(),
			},
			success : function(data, status) {
				if(data == "adviceOk"){ //成功提建议
					new $.zui.Messager('感谢您对nullpointer的支持！', {
						icon : 'bell', //定义图标
						fade : 'true',
						type : 'primary', // 定义颜色主题
					}).show();
				}else if(data=="not ok"){
					new $.zui.Messager('服务器开小差，您辛苦了！', {
						icon : 'bell', //定义图标
						fade : 'true',
						type : 'primary', // 定义颜色主题
					}).show();
				}
			},
			error:function(e){
				new $.zui.Messager('服务器出问题了，请刷新试试！', {
					icon : 'bell', //定义图标
					fade : 'true',
					type : 'danger', // 定义颜色主题
				}).show();
			} 
		});
}
</script>
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
	<%@ include file="footer.jsp"%>

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
<script src="${ctx}/js/hibernateSearch.js"></script>
</html>
