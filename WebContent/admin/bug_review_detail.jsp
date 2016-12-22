<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="error-500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
${fn:substring(oneBug.bugTitle , 0, 10)} 
<c:if test="${fn:length(oneBug.bugTitle)>10}">...</c:if>
</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style type="text/css">
*{
font-family: "微软雅黑","宋体";
}
</style>
<!-- 网站logo缩略图 -->
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<!-- 条件 -->
<c:if test="${empty loginUser }">
		<%
			response.sendRedirect("../login.jsp");
		%>
</c:if>
		<!-- basic styles -->

		<link href="${ctx }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		
		<!-- fonts -->
		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx }/admin/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx }/admin/assets/js/html5shiv.js"></script>
		<script src="${ctx }/admin/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<%@ include file="nav.jsp"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>
					<ul class="nav nav-list">
						<li>
							<a href="${ctx }/admin/advice">
								<i class="icon-dashboard"></i>
								<span class="menu-text" id="controllerT"> 控制台 </span>
							</a>
						</li>

						<li>
							<a href="${ctx }/admin/advice">
								<i class="icon-text-width"></i>
								<span class="menu-text"> 用户建议 </span>
							</a>
						</li>

						<li class="active open">
							<a href="" class="dropdown-toggle">
								<i class="icon-edit"></i>
								<span class="menu-text"> bug管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="${ctx }/admin/bug" class="active">
										<i class="icon-double-angle-right"></i>
										查询bug
									</a>
								</li>

								<li class="active">
									<a href="${ctx }/admin/bug_review">
										<i class="icon-double-angle-right"></i>
										bug审核
									</a>
								</li>

								<li>
									<a href="${ctx }/admin/bug_publish">
										<i class="icon-double-angle-right"></i>
										发布bug
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 用户管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="${ctx }/admin/users">
										<i class="icon-double-angle-right"></i>
										用户详情
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">bug管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								bug审核
								<small>
									<i class="icon-double-angle-right"></i>
									${oneBug.bugTitle }
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-12">
										<div class="widget-box">
											<h4>${oneBug.bugTitle }</h4>
												<address>
													<c:if test="${ empty oneBug.userInfo.userInfoHeadPortrait}">
														<img class="nav-user-photo" width="20px" height="20px" src="${ctx}/imgUp/default.jpg" alt="Photo">
													</c:if> 
													<c:if test="${not empty oneBug.userInfo.userInfoHeadPortrait}">
														<img class="nav-user-photo" width="20px" height="20px" src="${ctx}/imgUp/${oneBug.userInfo.userInfoHeadPortrait}" alt="Photo">
													</c:if>
													  <input type="hidden" id="idBug" name="idBug"value="${oneBug.bugId }" />
													作者：<strong>${oneBug.userInfo.loginUser.loginName }</strong>
													发布时间：<strong><fmt:formatDate	value="${oneBug.bugPublishTime}" pattern="yyyy-MM-dd" /></strong>
													
												</address>
											<hr />
											<h2>发现有如下问题：</h2>
											<h2>bug描述：</h2>
											${oneBug.bugDescribe }
											<hr/>
											<h2>bug原因：</h2>
											${oneBug.bugReason }
											<hr/>
											<h2>bug解决方法：</h2>
											${oneBug.bugMethod }
											<hr/>
											<div class="col-sm-2">
												<button class="btn btn-lg btn-danger" id="refuseBugShare" >
													<i class="icon-bolt bigger-110"></i>
														拒绝
													<i class="icon-arrow-left icon-on-left"></i>
												</button>
											</div>
											<div class="col-sm-2">
												<button class="btn btn-lg btn-success" id="agreeBugShare">
												<i class="icon-ok"></i>
												同意
												</button>
											</div>
										</div>
									</div><!-- /span -->
								</div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
<script type="text/javascript">
function refuse(){
	$.ajax({
		url : "denyBugPublish",
		type: "POST",
		method: "post",
		data : {
			bugId : $("#idBug").val(),
		},
		success : function(data, status) {
			alert("该用户分享失败！"); 
		},
		error:function(e){
		    alert("错误");   
		} 
	});
}
function agree(){
	$.ajax({
		url : "agreeBugPublish",
		type: "POST",
		method: "post",
		data : {
			bugId : $("#idBug").val(),
		},
		success : function(data, status) {
			alert("该用户分享成功！"); 
		},
		error:function(e){
		    alert("错误");   
		} 
	});
}
</script>
				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">将本网站的代码改的好一些就好了


									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; 选择皮肤</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl">从右向左  (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								中间位置 <b>.包含</b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="${ctx }/admin/assets/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx }/admin/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx }/admin/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx }/admin/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx }/admin/assets/js/bootstrap.min.js"></script>
		<script src="${ctx }/admin/assets/js/typeahead-bs2.min.js"></script>

<script src="${ctx }/admin/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx }/admin/assets/js/bootbox.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.gritter.min.js"></script>

		<!-- page specific plugin scripts -->
		<!-- ace scripts -->

		<script src="${ctx }/admin/assets/js/ace-elements.min.js"></script>
		<script src="${ctx }/admin/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
			
				window.prettyPrint && prettyPrint();
				$('#id-check-horizontal').removeAttr('checked').on('click', function(){
					$('#dt-list-1').toggleClass('dl-horizontal').prev().html(this.checked ? '&lt;dl class="dl-horizontal"&gt;' : '&lt;dl&gt;');
				});
				$("#refuseBugShare").on(ace.click_event, function() {
					bootbox.confirm("你确定不让这个bug通过审核吗？", function(result) {
						if(result) {
							refuse();
						}
					});
				});
				$("#agreeBugShare").on(ace.click_event, function() {
					bootbox.confirm("你确定通过审核吗？", function(result) {
						if(result) {
							agree();
						}
					});
				});
			})
		</script>
	</body>
</html>
