<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/test001.css">
<title>搜索框</title>

</head>
<body>
	<div id="searchbox">
		<div>
			<input type="text" id="txtTitle" />
		</div>
		<div id="btnSelect">
			<a href="" id="buttongoole">搜索</a>
		</div>
	</div>
	<div id="dtitles"></div>
</body>
<!-- @ray   功能：搜索下来框 -->
<script src="${ctx }/assets/jquery.js"></script>
<script type="text/javascript">
	$(document).ready($("#txtTitle").keydown(function(event) {
		//  按空格建或者enter键 触发查询事件
		if (event.keyCode == 32 ||event.keyCode == 13) {
			//2.获取到文本框的内容,注意去空格
			var title = $.trim($("#txtTitle").val());
			//3.获取到输入的内容之后，就要通过ajax传给后台
			$.post("${ctx}/test/list", {
				"title" : title
			}, function(data) {

				if (title == "") {
					$("#dtitles").hide();
				} else {
					//显示展示div,把它清空
					$("#dtitles").show().html("");
					if (data == "") {
						$("#dtitles").text("没有相关数据!");
					} else {
						$("#dtitles").append(data);
						//4.鼠标移上去之后，加一个背景
						$("li").hover(function() {
							//alert("鼠标移上去");
							$(this).addClass("li1");
						}, function() {
							$(this).removeClass("li1");
						});
					}
				}
			});
		}
	}))
	$(document).ready(
	//1.页面加载之后，找到文本框的内容对它触发一个事件
	$("#txtTitle").keyup(function() {
		//2.获取到文本框的内容,注意去空格
		var title = $.trim($("#txtTitle").val());
		title = encodeURI(encodeURI(title));
		console.log(title);
		//3.获取到输入的内容之后，就要通过ajax传给后台
		$.post("${ctx}/test/list", {
			"title" : title
		}, function(data) {
			if (title == "") {
				$("#dtitles").hide();
			} else {
				//显示展示div,把它清空
				$("#dtitles").show().html("");
				if (data == "") {
					$("#dtitles").text("没有相关数据!");
				} else {
					$("#dtitles").append(data);
					//4.鼠标移上去之后，加一个背景
					$("li").hover(function() {
						//alert("鼠标移上去");

						$(this).addClass("li1");
					}, function() {
						$(this).removeClass("li1");
					});
				}
			}
		});
	}))
</script>
</html>