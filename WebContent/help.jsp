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
<title>帮助--nullpointer</title>
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
				<div class="col-md-12 column">
					<article class="article">
					  <header>
					    <h1 class="text-center">帮助</h1>
					    <dl class="dl-inline">
					      <dt>来源：</dt>
					      <dd>管理员</dd>
					      <dt>最后修订：</dt>
					      <dd>2016年12月12日 (星期五) 12:53</dd>
					      <dt></dt>
					    </dl>
					  </header>
					  <header>
						<section class="abstract">
						<p>
							<strong>简介：</strong> <br /> 项目名称：nullpointer网站
							<img src="/nullpointer/images/black_logo.png" width="400px" height="200px" alt="logo">
						<hr />
						项目的提出方：EXP项目小组
						<hr />
						项目目标：为程序员提供更好的学习和交流的平台
						</p>
						</section> </header>
						<blockquote>
							<section class="abstract">
							<p>
								<strong>项目成员简介：</strong> <br /> PM：冯廷鑫
							<hr />
							产品经理：张肇霖
							<hr />
							用户体验、测试工程师：颜荣恩、汤文茹
							<hr />
							开发工程师：冯廷鑫、张肇霖、颜荣恩、汤文茹
							</p>
							</section>
						</blockquote>
					  <section class="content">
					    <h2>激活</h2>
					    <h3>为什么需要激活码激活帐户?</h3>
					    <p>为了确保ID准确无误、保障ID信息安全。</p>
					    <h3>什么情况需要激活帐户?</h3>
					    <p>您第一次注册帐户时。</p>
					    <h3>邮件收不到激活信息怎么办？</h3>
					    <p>（1）检查您邮箱的垃圾箱，看看是否被误认为垃圾邮件。</p>
					    <p>（2）如果仍无法收到，建议您换一个邮箱重新注册。</p>
					    <h2>密码</h2>
					    <h3>忘记密码如何找回?</h3>
					    <p>为了确保ID准确无误、保障ID信息安全，发邮件至管理员邮箱 webmaster(at)nullpointer.net。 </p>
					    <h2>荣誉值</h2>
					    <h3>荣誉值说明</h3>
					    <p>（1）签到，荣誉值+1</p>
					    <p>（2）发布一条评论，荣誉值+1</p>
					    <p>（3）bug被点赞，bug作者荣誉值+2</p>
					    <p>（4）某用户对取消对bug的点赞，bug作者荣誉值-2</p>
					    <p>（5）用分享bug并成功通过审核时，用户荣誉值+5</p>
					    <p>（6）用户登录时，提交建议，该用户荣誉值+1</p>
					    <p>（7）用户提问一条问题，该用户荣誉值+1</p>
					    <p>（8）问题被点赞，提问问题者荣誉值+1</p>
					    <p>（9）问题被取消赞，提问问题者荣誉值-1</p>
					  </section>
					</article>					
				<!-- end of page content -->
				<!-- start of sidebar -->
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
