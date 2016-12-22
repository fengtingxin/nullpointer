<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page errorPage="error-500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nullpointer 管理员登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link href="${ctx }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${ctx }/admin/assets/css/font-awesome.min.css" />
<!-- 网站缩略图 -->
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<link rel="stylesheet" href="${ctx }/admin/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctx }/admin/assets/css/ace-rtl.min.css" />
<script type="text/javascript" src="${ctx }/resources/js/md5.js"></script>
</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<a href="${ctx }/"><img src="${ctx }/images/logo.png"></a>
							</h1>
							<h4 class="blue">&copy; nullpointer<br/><span id="btitle">管理员登陆</span></h4>
						</div>
						
						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 请输入您的账号信息
										</h4>
										<div class="space-6"></div>

										<form id="adminLogin" action="login" method="post">
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" id="name" class="form-control" name="name"
														placeholder="用户名" /> <i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" id="password" class="form-control"
														name="password" placeholder="密码" /> <i
														class="icon-lock"></i> <c:if
															test="${not empty loginError }">
															<span id="loginErrorSpan"
																class="help-inline col-xs-12 col-sm-7"> <span
																class="text-warning bigger-110 orange">${loginError }</span>
															</span>
														</c:if>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">


													
													<button type="reset"
														class="width-35 pull-right btn btn-sm btn-primary">
														重置</button>
														<button type="button" onclick="validate()"
														class="width-35 pull-left btn btn-sm btn-primary" id="inputlogin">
														<i class="icon-key"></i> 登录
													</button>

													<!-- 登录用 -->
													<script type="text/javascript">
														function validate() {
															var md5 = new MD5();
															var f = '......' == $(
																	'#password')
																	.val() ? ''
																	: md5
																			.MD5($(
																					'#password')
																					.val());
															$('#password').val(
																	f);
															$('#adminLogin')
																	.submit();
														}
														window.onload = function() {
															$("#name")
																	.focus(
																			function() {
																				$(
																						"#loginErrorSpan")
																						.css(
																								"display",
																								"none");
																			});
															$("#password")
																	.focus(
																			function() {
																				$(
																						"#loginErrorSpan")
																						.css(
																								"display",
																								"none");
																			});
														}
													</script>

												</div>

												<div class="space-4"></div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->
						</div>
						<!-- /position-relative -->
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>
	<script src="${ctx }/admin/assets/js/jquery.min.js"></script>

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${ctx }/admin/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>
	<!-- 回车实现登陆 -->
	<script type="text/javascript">
	$(document).keyup(function(e){
        if(e.keyCode==13){
        	 $("#inputlogin").click() 
        }
    });
	
		if ("ontouchend" in document)
			document
					.write("<script src='${ctx }/admin/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
</body>
</html>
