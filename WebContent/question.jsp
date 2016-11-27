
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


<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main5152.css">
<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">

<style id="themeStyle"></style>

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
					<li><a href="${ctx}/bug/listadmin">BUGS</a></li>
					<li><a href="${ctx}/q_a_list.jsp">技术问答</a></li>
					<li><a href="${ctx}/contact">帮助</a></li>
					<li><a href="login.jsp">登陆/注册</a></li>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown"><img src="${ctx}/images/touxiang.jpg"
							width="20px" height="20px" class="img-circle" /> <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="${ctx}/home.jsp">我的主页</a></li>
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
	<!-- End of Header -->

	<!-- Start of Search Wrapper -->
	<div style="margin-top: 30px;">
		<div class="search-area container">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="exampleInputAccount4" class="col-sm-2">标题</label>
					<div class="col-md-6 col-sm-10">
						<input type="text" class="form-control" id="exampleInputAccount4"
							placeholder="问题标题">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputAccount4" class="col-sm-2">类别选择</label>
					<div class="col-md-6">
						<select data-placeholder="选择问题类型..."
							class="chosen-select form-control" tabindex="-1" multiple="">
							<option value="strawberries">JAVA</option>
							<option value="apple">C++</option>
							<option value="orange">hibernate</option>
							<option value="cherry">C语言</option>
							<option value="banana">html</option>
							<option value="figs">php</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputAccount4" class="col-sm-2">详细内容</label>
					<div class="col-md-8 col-sm-10">
						<textarea id="content" name="content"
							class="form-control kindeditor" style="height: 300px;"></textarea>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-lg btn-success">提交</button>
					</div>
				</div>

				<form>
		</div>
	</div>
	<!-- end of #footer -->

	<!-- Footer Bottom -->
	<div id="footer-bottom-wrapper">
		<div id="footer-bottom" class="container">
			<div class="row">
				<div class="span6">
					<p class="copyright">
						Copyright © 2013. All Rights Reserved by KnowledgeBase.Collect
						from <a href="#" title="EXP小组" target="_blank">EXP小组</a>
					</p>
				</div>
				<div class="span6">
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
	<!-- End of Footer Bottom -->

	</footer>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>

	<!-- script -->
	<script type='text/javascript' src='${ctx}/js/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='${ctx}/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript'
		src='js/prettyphoto/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='${ctx}/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='${ctx}/js/jquery.liveSearch.js'></script>
	<script type='text/javascript' src='${ctx}/js/jquery.form.js'></script>
	<script type='text/javascript' src='${ctx}/js/jquery.validate.min.js'></script>


	<script src="${ctx}/assets/jquery.js"></script>
	<script src="${ctx}/assets/clipboard/clipboard.min.js"></script>
	<script src="${ctx}/assets/less/less.min.js"></script>

	<!-- ZUI Javascript组件 -->
	<script src="${ctx}/docs/js/zui.min.js"></script>
	<script src="${ctx}/docs/js/doc.min.js"></script>
	<!-- 增强文档插件 -->
	<script async src="${ctx}/assets/prettify/prettify.js"></script>
	<script src="${ctx}/assets/marked/marked.min.js"></script>

	<script src="${ctx}/js/kindeditor/kindeditor.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			KindEditor.create('textarea.kindeditor', {
				basePath : 'js/kindeditor/',
				allowFileManager : true,
				bodyClass : 'article-content'
			});
		});
	</script>
	<script type="text/javascript">
		$('select.chosen-select').chosen({
			no_results_text : '没有找到', // 当检索时没有找到匹配项时显示的提示文本
			disable_search_threshold : 10, // 10 个以下的选择项则不显示检索框
			search_contains : true
		// 从任意位置开始检索
		});
	</script>
</body>

</html>