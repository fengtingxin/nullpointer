<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--搜索框-->
<script type="text/javascript" src="${ctx }/js/jquery-1.8.3.min.js"></script>
 <!-- 刷新替换背景 -->
  <script type="text/javascript">
    $(function() {
      var bg = Math.floor(Math.random() * 15+ 1);
      $('#search-area-wrapper').css('background-image', 'url(${ctx }/images/main-bg'+ bg +'.jpg)');
    });
  </script>
<div class="search-area-wrapper" id="search-area-wrapper">
	<div class="search-area container">
		<h3 class="search-header">今天你遇到什么技术难点了吗？</h3>
		<p class="search-tag-line">请在下方搜索您所遇到的问题吧！</p>
		<form id="search-form" class="search-form clearfix" method="get"
			action="#" autocomplete="off" novalidate="novalidate">
			<input class="search-term required" type="text" id="s" name="s"
				placeholder="在这里搜索问题/BUG " title="请在这里搜索您遇到的问题或BUG吧!"
				style="height: 43px;" value="${searchValue}" /> <a><img
				id="clear" alt="清除按钮" src="${ctx}/images/cuohao.jpg"></a>
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
