<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@page isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>500 - 对不起，服务器出现异常! -nullpointer</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.png" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/error_style/main.css">
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Style Sheet-->
<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
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

	<%@ include file="nav.jsp"%>
	<!--导航栏完成-->
	<div class="container" style="margin-bottom: 100px;">
		<div id="main" style="margin-top: 0px; paddgin-top: 0px;">
			<img alt="404_error.png" src="${ctx}/css/error_style/error_500.png" style="margin-top:3.2em;"/>
			<div id="content">
				<h2>服务器出现异常了！</h2>
				<p>当您看到这个页面,表示您的访问出错,这个错误服务器产生的,请确认您的操作的正确性,如果是在本站点击后出现这个页面,请联系nullpointer进行处理,或者请通过下边的搜索重新查找资源,nullpointer感谢您的支持!</p>
				<div class="utilities">
					<form name="formsearch" action="" id="formkeyword">
						<div class="input-container">
							<input type="text" class="left" name="q" size="24"
								value="在这里搜索..."
								onfocus="if(this.value=='在这里搜索...'){this.value='';}"
								onblur="if(this.value==''){this.value='在这里搜索...';}"
								id="inputString" onkeyup="lookup(this.value);" onblur="fill();"
								placeholder="搜索..." />
						</div>
					</form>
					<a class="button right" style="background:#68b3a3;" href="#" onClick="history.go(-1);return true;">返回...</a> 
					<a class="button right" style="background:#68b3a3;" href="${ctx}/contact">提出建议</a>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>
</body>
<!-- script -->
<script src="assets/jquery.js"></script>
<script src="assets/clipboard/clipboard.min.js"></script>
<script src="assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="docs/js/zui.min.js"></script>
<script src="docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="assets/prettify/prettify.js"></script>
<script src="assets/marked/marked.min.js"></script>
</html>