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
<title>bug --nullpointer</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome.min.css" />
<!-- 网站logo缩略图 -->
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<!-- 条件 -->
<c:if test="${empty loginUser }">
		<%
			response.sendRedirect("login.jsp");
		%>
</c:if>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

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

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
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
								<li class="active">
									<a href="${ctx }/admin/bug" class="active">
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
								<a href="${ctx }/admin/advice.jsp">首页</a>
							</li>

							<li>
								<a href="">bug管理</a>
							</li>
							
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">
						<!-- /.page-header -->

								<!--<div class="hr hr-18 dotted hr-double"></div>-->


								<div class="row">
									<div class="col-xs-12">
										<h3 class="header smaller lighter blue">bug管理</h3>
										<div class="table-header">
											结果来自bug表
										</div>

										<div class="table-responsive">
											<table id="sample-table-2" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>标题</th>
														<th>发布时间</th>
														<th class="hidden-480">赞数量</th>

														<th>
															<i class="icon-time bigger-110 hidden-480"></i>
															踩数量
														</th>
														<th class="hidden-480">评论量</th>

														<th></th>
													</tr>
												</thead>

												<tbody>
												<c:if test="${empty allBug }">
													<tr>暂无</tr>
												</c:if>
												<c:if test="${not empty allBug }">
													<c:forEach items="${allBug }" var="oneBug">
															<tr>
																<td class="center">
																	<label>
																		<input type="checkbox" class="ace" />
																		<span class="lbl"></span>
																	</label>
																</td>
		
																<td>
																	<a href="bug/${oneBug.bugId }">${fn:substring(oneBug.bugTitle , 0, 10)} <c:if
													test="${fn:length(oneBug.bugTitle )>15}">...</c:if></a>
																</td>
																<td><fmt:formatDate	value="${oneBug.bugPublishTime}" pattern="yyyy-MM-dd" /></td>
																<td class="hidden-480">${oneBug.bugLikeNum }</td>
																<td>${oneBug.bugHateNum }</td>
																<td class="hidden-480">
																	<span class="label label-sm label-warning">${fn:length(oneBug.comments)}</span>
																</td>
																<td>
																	<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
																		<a class="blue" href="bug/${oneBug.bugId }">
																			<i class="icon-zoom-in bigger-130"></i>
																		</a>
																					
																		<a class="red" href="deleteBug?bugId=${oneBug.bugId }">
																			<i class="icon-trash bigger-130"></i>
																		</a>
																	</div>
		
																	<div class="visible-xs visible-sm hidden-md hidden-lg">
																		<div class="inline position-relative">
																			<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
																				<i class="icon-caret-down icon-only bigger-120"></i>
																			</button>
		
																			<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																				<li>
																					<a href="bug/${oneBug.bugId }" class="tooltip-info" data-rel="tooltip" title="View">
																						<span class="blue">
																							<i class="icon-zoom-in bigger-120"></i>
																						</span>
																					</a>
																				</li>
																							
																				<li>
																					<a href="deleteBug?bugId=${oneBug.bugId }" class="tooltip-error" data-rel="tooltip" title="Delete">
																						<span class="red">
																							<i class="icon-trash bigger-120"></i>
																						</span>
																					</a>
																				</li>
																			</ul>
																		</div>
																	</div>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
											</table>
										</div>
									</div>
								</div>
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
							<span>&nbsp; 选择皮肤</span>
						</div>

						
						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> 右向左 (rtl)</label>
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

		<!-- page specific plugin scripts -->

		<script src="${ctx }/admin/assets/js/jquery.dataTables.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.dataTables.bootstrap.js"></script>

		<!-- ace scripts -->

		<script src="${ctx }/admin/assets/js/ace-elements.min.js"></script>
		<script src="${ctx }/admin/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
				var oTable1 = $('#sample-table-2').dataTable( {
				"aoColumns": [
			      { "bSortable": false },
			      null, null,null, null, null,
				  { "bSortable": false }
				] } );
				
				
				$('table th input:checkbox').on('click' , function(){
					var that = this;
					$(this).closest('table').find('tr > td:first-child input:checkbox')
					.each(function(){
						this.checked = that.checked;
						$(this).closest('tr').toggleClass('selected');
					});
						
				});
			
			
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			})
		</script>
	</body>
</html>
