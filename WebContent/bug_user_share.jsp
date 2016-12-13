<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
<!-- META TAGS -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>nullpointer</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.png" />
<!-- Style Sheet-->
<!-- 没有登录就跳出 -->
<c:if test="${empty loginUser}">
		<%
			response.sendRedirect("../login.jsp");
		%>
	</c:if>

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

	<%@ include file="nav.jsp"%>
	<!-- End of Header -->

	<!-- Start of Search Wrapper -->
	<div style="padding-top: 30px; background-color: #f3f3f3; padding-bottom: 30px;">
	
		<div class="search-area container" style="background-color: white; padding: 0px 0px 0px 0px">
		
			<div class="col-md-8 column">
				<h2 style="margin-top:15px;" class="header-dividing">Bug分享</h2>
				<div class="example">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="exampleInputAccount4" class="col-sm-2">标题</label>
							<div class="col-md-6 col-sm-10">
								<input type="text" name="bugTitle" class="form-control" id="bugTitle" placeholder="问题标题">
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputAccount4" class="col-sm-2">类别选择</label>
							<div class="col-md-6">
								<select data-placeholder="选择问题类型..."
									class="chosen-select form-control" tabindex="-1" multiple="">
									<c:forEach	var="tt" items="${tags}">
										<option value="${tt.tagName }">${tt.tagName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputAccount4" class="col-sm-2">bug描述</label>
							<div class="col-md-8 col-sm-10">
								<textarea id="bugDescribe" name="bugDescribe" class="form-control kindeditor" style="height: 200px;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputAccount4" class="col-sm-2">产生原因</label>
							<div class="col-md-8 col-sm-10">
								<textarea id="bugReason" name="bugReason" class="form-control kindeditor" style="height: 200px;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputAccount4" class="col-sm-2">解决方法</label>
							<div class="col-md-8 col-sm-10">
								<textarea id="bugMethod" name="bugMethod"
									class="form-control kindeditor" style="height: 300px;"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="button" onclick="submitForm()" class="btn btn-lg btn-success">提交</button>
							</div>
						</div>
					</form>
				</div>
				</div>
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
		<!-- 问题推荐 -->
		
	</div>
	<!-- end of #footer -->
<!-- 提交部分 -->
<script type="text/javascript">
function submitForm(){
	var b =document.getElementsByClassName("search-choice");
	var bugList =new Array(b.length);
	for(var i=0;i<b.length;i++){
		bugList[i]=b[i].firstChild.innerHTML;
	}
	//bugTItle
	if($("#bugTitle").val()=="" || $("#bugTitle").val()== null || $("#bugTitle").val().length>200){
		new $.zui.Messager('标题不符合格式要求！', {
		    type: 'warning', // 定义颜色主题
		    placement: 'top', // 定义显示位置
		    icon: 'bell',
		}).show();
		return ;
	}
	//bugTags
	if(bugList=="" || bugList== null || bugList.length>4 ){
		new $.zui.Messager('标签不符合格式！', {
		    type: 'warning', // 定义颜色主题
		    placement: 'top', // 定义显示位置
		    icon: 'bell',
		}).show();
		return ;
	}
	//bugDescribe
	if($("#bugDescribe").val()=="" || $("#bugDescribe").val()== null ){
		new $.zui.Messager('bug描述不能为空！', {
		    type: 'warning', // 定义颜色主题
		    placement: 'top', // 定义显示位置
		    icon: 'bell',
		}).show();
		return ;
	}
	//bugReason
	if($("#bugReason").val()=="" || $("#bugReason").val()== null ){
		new $.zui.Messager('bug原因不能为空！', {
		    type: 'warning', // 定义颜色主题
		    placement: 'top', // 定义显示位置
		    icon: 'bell',
		}).show();
		return ;
	}
	//bugMethod
	if($("#bugMethod").val()=="" || $("#bugMethod").val()== null ){
		new $.zui.Messager('bug解决方法不能为空！', {
		    type: 'warning', // 定义颜色主题
		    placement: 'top', // 定义显示位置
		    icon: 'bell',
		}).show();
		return ;
	}
	$.ajax({
		url : "bugShareByUser",
		type: "POST",
		method: "post",
		data : {
			bugTitle : $("#bugTitle").val(),
			tags : bugList.join(","),
			bugDescribe:$("#bugDescribe").val(),
			bugReason:$("#bugReason").val(),
			bugMethod:$("#bugMethod").val(),
		},
		success : function(data, status) {
			if(data=="ok"){
				new $.zui.Messager('分享成功，管理员正在加班加点的审核哦！', {
				    type: 'success', // 定义颜色主题
				    placement: 'top', // 定义显示位置
				    icon: 'bell',
				}).show();
				window.location.href = "listuser";
			}else {
				if(data=="1"){
					new $.zui.Messager('唉啊，网络出问题了!', {
					    type: 'important', // 定义颜色主题
					    placement: 'top', // 定义显示位置
					    icon: 'bell',
					}).show();
				}else{
					new $.zui.Messager('唉啊，出错了!', {
					    type: 'danger', // 定义颜色主题
					    placement: 'top', // 定义显示位置
					    icon: 'bell',
					}).show(); 
				}
			}
			
		},
		error:function(e){
			new $.zui.Messager('系统出现错误，请刷新试试！', {
			    type: 'success', // 定义颜色主题
			    placement: 'top', // 定义显示位置
			    icon: 'bell',
			}).show();
		} 
	});
}
</script>
	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>
	<!-- End of Footer Bottom -->

	</footer>
	<!-- End of Footer -->

	<a href="#top" id="scroll-top"></a>

	<!-- script -->
	<script type='text/javascript' src='${ctx}/js/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='${ctx}/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript'
		src='${ctx}/js/prettyphoto/jquery.prettyPhoto.js'></script>
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
				basePath : '../js/kindeditor/',
				allowFileManager : true,
				bodyClass : 'article-content',
				width:"120%",
				afterBlur : function() {
    				this.sync();
    			}
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