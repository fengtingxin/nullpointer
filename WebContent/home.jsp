<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>nullpointer</title>
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<!-- Style Sheet-->
<link href="${ctx }/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/zui-theme.css">
<link href="${ctx }/docs/css/doc.min.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="${ctx }/css/main5152.css">
<link rel="stylesheet" type="text/css" href="${ctx }/css/style.css">
<style id="themeStyle"></style>
<style type="text/css">
.profile__mod-inner-heading {
	font-size: 14px;
	color: #999;
}

.mb5, .mb-5 {
	margin-bottom: 5px !important;
}

.profile__heading-edit {
	border: none;
	box-shadow: none;
	margin-left: 8px;
	color: #999;
	font-size: 12px;
	font-weight: 400;
}
</style>
</head>

<body>

	<!-- Start of Header -->
	<c:if test="${loginUser==null }">
		<%
			response.sendRedirect("login.jsp");
		%>
	</c:if>
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
				<img src="${ctx }/images/logo.png" alt="nullpointer" width="200"
					style="margin-top: 3px;">
			</div>
			<!-- 导航项目 -->
			<div class="collapse navbar-collapse navbar-collapse-example">

				<ul class="nav navbar-nav navbar-right">
					<li class="current-menu-item"><a href="${ctx }/index.jsp">主页</a></li>
					<li><a href="${ctx }/bug-list-admin.html">BUGS</a></li>
					<li><a href="${ctx }/q_a_list.html">技术问答</a></li>
					<li><a href="${ctx }/contact.html">帮助</a></li>
					<li><a href="${ctx }/login.html">登陆/注册</a></li>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="${ctx }/your/nice/url"
						class="dropdown-toggle" data-toggle="dropdown"><img
							src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
							width="20px" height="20px" class="img-circle" /><b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="${ctx }/home.html">我的主页</a></li>
							<li><a href="${ctx }/home-question.html">信息管理</a></li>
							<li><a href="${ctx }/accountSetting.html">账号设置</a></li>
							<li><a href="${ctx }/contact.html">建议反馈</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>

	</nav>
	<!--导航栏完成-->
	<!-- End of Header -->
	<div class="container" style="padding-top: 20px;">
		<div class="col-md-3 column">

			<div class="col-md-11 column">
				<div class="example"
					style="text-align: center; background-color: #fafafa;">
					<div class="container">
						<img src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
							width="100px" height="100px" class="img-circle" />
					</div>

					<h3>${loginUser.loginName}</h3>
					<c:if
						test="${ loginUser.userInfo.userInfoDescribe == null || loginUser.userInfo.userInfoDescribe == ''}">
						<p>这家伙很懒 什么都没有留下</p>
					</c:if>
					<p>${loginUser.userInfo.userInfoDescribe}</p>
				</div>

				<div class="col-md-12">
					<nav class="menu" data-toggle="menu"
						style="width: 200px;margin-top:20px;margin-bottom: 20px;">
					<ul class="nav nav-primary">
						<li class="nav-heading">个人中心</li>
						<li class="active"><a href="${ctx }/home.jsp"><i
								class="icon icon-home"></i> 我的主页</a></li>
						<li><a href="${ctx }/accountSetting.jsp"><i
								class="icon-user"></i> 账号设置</a></li>
						<li><a href="${ctx }/question/findQuestionByTime"><i
								class="icon icon-question-sign"></i> 我的问题<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/answer/findAnswerByTime"><i
								class="icon icon-reply"></i> 我的回答<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/home-comment.jsp"><i
								class="icon icon-comments"></i> 我的评论<span
								class="label label-badge label-success">4</span></a></li>
						<li><a href="${ctx }/home-share.jsp"><i
								class="icon icon-share"></i> 我的分享<span
								class="label label-badge label-success">4</span></a></li>
					</ul>
					</nav>
				</div>

			</div>

		</div>
		<div class="col-md-9 column">
			<div class="alert alert-success with-icon alert-dismissable">
				<i class="icon-user"></i>
				<div class="content">
					hi!&nbsp;&nbsp;&nbsp;${loginUser.loginName}&nbsp;&nbsp;&nbsp;你已经在这里度过了${day}天${hour}小时${min}分${second }秒
				</div>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">×</button>
			</div>
			<!--表格-->
			<div class="panel">
				<div class="panel-heading">
					<h4>社区属性</h4>
				</div>
				<div class="panel-body">
					<canvas id="myChart" width="200" height="150"></canvas>
					<canvas id="Honor" width="500" height="200"
						style="margin-left: 40px;padding-top: 15px;"></canvas>
				</div>
			</div>
			<div class="panel">
				<div class="panel-heading">
					<h4>个人档案</h4>
				</div>
				<div class="panel-body">
					<div class="pb15 profile__base">
						<div class="row" style="margin-top: 10px;">
							<div class="col-md-3">
								<div class="container" style="text-align: center;">
									<span class="profile__mod-inner-heading">个人头像</span> <img
										class="img-circle"
										src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
										width="130" height="130" style="margin-top: 5px;">
								</div>

							</div>
							<div class="col-md-4">
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">用户名</span>
									</div>
									<div>
										<span>
										<c:if test="${loginUser.loginName!=null}">
										${loginUser.loginName}
										</c:if>
										<c:if test="${loginUser.loginName==null}">
										暂无
										</c:if>
										</span>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">出生日期</span>
									</div>
									<div>
									<c:if test="${loginUser.userInfo.userInfoBirthday!=null}">
										<fmt:formatDate value="${loginUser.userInfo.userInfoBirthday}"
											pattern="yyyy-MM-dd" />
									</c:if>
									<c:if test="${loginUser.userInfo.userInfoBirthday==null}">
										暂无
									</c:if>
										
										
										<span></span>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">性别</span>
									</div>
									<div>
										<span>
										<c:if test="${loginUser.userInfo.userInfoSex==null}">
										暂无
									    </c:if>
									    <c:if test="${loginUser.userInfo.userInfoSex!=null}">
										${loginUser.userInfo.userInfoSex}
									    </c:if>
						
										</span>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">荣誉值</span>
									</div>
									<div>
										<span>
										 <c:if test="${loginUser.userInfo.userInfoHonorCount !=null}">
										${loginUser.userInfo.userInfoHonorCount }
									    </c:if>
										 <c:if test="${loginUser.userInfo.userInfoHonorCount ==null}">
										暂无
									    </c:if>
										</span>
									</div>
								</div>
							</div>
							<div class="col-md-5">
								<span class="profile__heading-edit pull-right  btn btn-xs"
									data-type="base"><i class="fa fa-pencil"
									aria-hidden="true"></i><a href="${ctx }/accountSetting.jsp">编辑</a></span>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">邮箱</span>
										<div>
											<span>
											 <c:if test="${loginUser.loginEmail !=null}">
										${loginUser.loginEmail }
									    </c:if>
									     <c:if test="${loginUser.loginEmail ==null}">
										暂无
									    </c:if>
									    
											
											</span>
										</div>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">注册时间</span>
									</div>
									<div>
										<span> 
										 <c:if test="${loginUser.userInfo.userInfoRegistTime!=null}">
										<fmt:formatDate
												value="${loginUser.userInfo.userInfoRegistTime}"
												pattern="yyyy-MM-dd" />
									    </c:if>
									     <c:if test="${loginUser.userInfo.userInfoRegistTime==null}">
									    暂无
									    </c:if>
										</span>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">个人描述</span>
									</div>
									<div>
										<span>
										<c:if test="${loginUser.userInfo.userInfoDescribe!=null }">
										${loginUser.userInfo.userInfoDescribe }</c:if>	
											<c:if test="${ loginUser.userInfo.userInfoDescribe == null || loginUser.userInfo.userInfoDescribe == ''}">
						这家伙很懒 什么都没有留下
					</c:if>									
										</span>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>


	<!-- Footer Bottom -->
	<div id="footer-bottom-wrapper">
		<div id="footer-bottom" class="container">
			<div class="row">
				<div class="col-md-6 column">
					<p class="copyright">
						Copyright © 2013. All Rights Reserved by KnowledgeBase.Collect
						from <a href="#" title="EXP" target="_blank">EXP小组</a>
					</p>
				</div>
				<div class="col-md-6 column">
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

</body>
<script src="${ctx }/assets/jquery.js"></script>
<script src="${ctx }/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx }/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx }/docs/js/zui.min.js"></script>
<script src="${ctx }/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx }/assets/prettify/prettify.js"></script>
<script src="${ctx }/assets/marked/marked.min.js"></script>


<script src="${ctx }/js/laydate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						// 使用jquery方法获取 2d context 对象
						var ctx = $("#myChart").get(0).getContext("2d");
						var honor = $("#Honor").get(0).getContext("2d");
						// 或者使用 document.getElementById 获取 2d context 对象
						// var ctx = document.getElementById("myChart").getContext("2d");

						// 使用$.zui.Chart构造Chart实例
						var myNWwChart = new $.zui.Chart(ctx);
						var myNWwChart2 = new $.zui.Chart(honor);
						var data = [ {
							value : 150,
							color : "blue", // 使用颜色名称
							label : "JAVA"
						}, {
							value : 250,
							color : "#F7464A", // 自定义颜色
							// highlight: "#FF5A5E", // 自定义高亮颜色
							label : "C++"
						}, {
							value : 50,
							color : 'green',
							label : "hibernate"
						}, {
							// 不指定color值，使用随机颜色
							// 
							value : 100,
							label : "servlet"
						} ];

						var data2 = {
							labels : [ "一月", "二月", "三月", "四月", "五月", "六月",
									"七月", "八月", "九月", "十月", "十一月", "十二月" ],
							datasets : [ {
								label : "蓝队",
								color : 'primary',
								data : [ 65, 59, 80, 81, 56, 55, 40, 66, 66,
										66, 36, 69 ]
							} ]
						};
						// 图表配置项，可以留空来使用默认的配置
						var options = {
							scaleShowLabels : true, // 展示标签

						};
						var options2 = {
							//Number - 柱状条描边宽度
							barStrokeWidth : 1,
							barValueSpacing : 2,

						}

						// 创建饼图
						var myPieChart = $("#myChart").pieChart(data, options);
						// 创建环形饼图
						//var myDoughnutChart = $("#myDoughnutChart").doughnutChart(data, {segmentShowStroke: false});
						var myBarChart = $('#Honor').barChart(data2, options2);
					});
</script>

</html>