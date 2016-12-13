<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--搜索框-->
	<div class="search-area-wrapper">
		<div class="search-area container">
			<h3 class="search-header">今天你遇到什么技术难点了吗？</h3>
			<p class="search-tag-line">请在下方搜索您所遇到的问题吧！</p>
			<form id="search-form" class="search-form clearfix" method="get"
				action="#" autocomplete="off" novalidate="novalidate">
				<input class="search-term required" type="text" id="s" name="s"
					placeholder="在这里搜索问题/BUG" title="请在这里搜索您遇到的问题或BUG吧!"
					style="height: 43px;" /><a><img id="clear" alt="清除按钮"
					src="${ctx}/images/cuohao.jpg"></a>
				<button type="button" id="bugSearch" class="btn btn-primary btn-lg">BUG搜索</button>
				<button type="button" id="questionSearch"
					class="btn btn-primary btn-lg">问题搜索</button>
				<div>
					<ul id="dtitles">

					</ul>
				</div>
				<div id="search-error-container"></div>
			</form>
		</div>
	</div>
	<!--搜索框完成-->
</body>
</html>