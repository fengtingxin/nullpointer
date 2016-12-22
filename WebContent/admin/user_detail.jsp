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
<title>${fn:substring(oneUser.loginName, 0, 10)} 
<c:if test="${fn:length(oneUser.loginName)>10}">...</c:if></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome.min.css" />
<!-- 网站logo缩略图 -->
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<c:if test="${empty loginUser }">
		<%
			response.sendRedirect("login.jsp");
		%>
</c:if>

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="${ctx }/admin/assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/jquery.gritter.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/select2.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/bootstrap-editable.css" />

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
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-edit"></i>
								<span class="menu-text"> bug管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="${ctx }/admin/bug">
										<i class="icon-double-angle-right"></i>
										查询bug
									</a>
								</li>

								<li>
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
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 用户管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li class="active">
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
								<a href="${ctx }/admin/advice">首页</a>
							</li>

							<li>
								<a href="${ctx }/admin/users">用户管理</a>
							</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								用户详情
								<small>
									<i class="icon-double-angle-right"></i>
									${oneUser.loginName }
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<div>
									<div id="user-profile-1" class="user-profile row">
										<div class="col-xs-12 col-sm-3 center">
											<div>
												<span class="profile-picture">
													<c:if test="${ empty oneUser.userInfo.userInfoHeadPortrait}">
														<img class="nav-user-photo" width="180px" height="200px" src="${ctx}/imgUp/default.jpg" alt="Photo">
													</c:if> 
													<c:if test="${not empty oneUser.userInfo.userInfoHeadPortrait}">
														<img class="nav-user-photo" width="180px" height="200px" src="${ctx}/imgUp/${oneUser.userInfo.userInfoHeadPortrait}" alt="Photo">
													</c:if>
												</span>

												<div class="space-4"></div>

												<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														
														<i class="icon-circle light-green middle"></i>
															&nbsp;
														<span class="white">用户名</span>
														
													</div>
												</div>
											</div>

											<div class="space-6"></div>
											<div class="hr hr16 dotted"></div>
										</div>

										<div class="col-xs-12 col-sm-9">
											<div class="center">
												<span class="btn btn-app btn-sm btn-light no-hover">
													<span class="line-height-1 bigger-170 blue">${fn:length(oneUser.userInfo.comments)} </span>

													<br />
													<span class="line-height-1 smaller-90"> 评论数量 </span>
												</span>

												<span class="btn btn-app btn-sm btn-yellow no-hover">
													<span class="line-height-1 bigger-170">${fn:length(oneUser.userInfo.questions)}  </span>

													<br />
													<span class="line-height-1 smaller-90"> 问题数量 </span>
												</span>

												<span class="btn btn-app btn-sm btn-pink no-hover">
													<span class="line-height-1 bigger-170">${fn:length(oneUser.userInfo.bugs)}</span>

													<br />
													<span class="line-height-1 smaller-90"> 分享数量 </span>
												</span>
											</div>

											<div class="space-12"></div>

											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name"> 用户名 </div>

													<div class="profile-info-value">
														<span>${oneUser.loginName }</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 邮箱 </div>

													<div class="profile-info-value">
														
														<span>${oneUser.loginEmail }</span>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 出生日期 </div>

													<div class="profile-info-value">
														
														<c:if test="${empty oneUser.userInfo.userInfoBirthday }">
															<span>暂无</span>
														</c:if>
														<c:if test="${not empty oneUser.userInfo.userInfoBirthday }">
															<span><fmt:formatDate	value="${oneUser.userInfo.userInfoBirthday}" pattern="yyyy-MM-dd" /></span>
														</c:if>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name"> 加入时间 </div>

													<div class="profile-info-value">
														<span ><fmt:formatDate	value="${oneUser.userInfo.userInfoRegistTime}" pattern="yyyy-MM-dd" /></span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name"> 描述 </div>
													<div class="profile-info-value">
														<span>
															<c:if test="${empty oneUser.userInfo.userInfoDescribe }">
																暂无
															</c:if>
															<c:if test="${not empty oneUser.userInfo.userInfoDescribe }">
																${oneUser.userInfo.userInfoDescribe }
															</c:if>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>								
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>


						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> 右向左 (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								中间位置
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

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${ctx }/admin/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.gritter.min.js"></script>
		<script src="${ctx }/admin/assets/js/bootbox.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.slimscroll.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.hotkeys.min.js"></script>
		<script src="${ctx }/admin/assets/js/bootstrap-wysiwyg.min.js"></script>
		<script src="${ctx }/admin/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx }/admin/assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="${ctx }/admin/assets/js/x-editable/bootstrap-editable.min.js"></script>
		<script src="${ctx }/admin/assets/js/x-editable/ace-editable.min.js"></script>

		<!-- ace scripts -->

		<script src="${ctx }/admin/assets/js/ace-elements.min.js"></script>
		<script src="${ctx }/admin/assets/js/ace.min.js"></script>
	</body>
</html>
