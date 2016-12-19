<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
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
* {
	font-family: '微软雅黑';
}

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
	<%@ include file="nav.jsp"%>
	<!--导航栏完成-->
	<c:if test="${empty loginUser}">
		<%
			response.sendRedirect("login.jsp");
		%>
	</c:if>
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
					<p>
					${fn:substring(loginUser.userInfo.userInfoDescribe,0,15)}
									<c:if test="${fn:length(loginUser.userInfo.userInfoDescribe) >15}">...</c:if>
					</p>
					<button onclick="signIn()" class="btn btn-sm btn-success" type="button"><i class="icon icon-edit"></i>&nbsp;签到
					<c:if test="${empty signDay}">
					：<span id="signDay">0</span>天
					</c:if>
					<c:if test="${not empty signDay}">
					：<span id="signDay">${signDay }</span>天
					</c:if>
					</button>
				</div>
				
<script type="text/javascript">
function signIn(){
	$.ajax({
		url : "sign",
		type: "POST",
		method: "post",
		data : {
		},
		success : function(data, status) {
			if(data == "theFirst"){ //第一次签到
				new $.zui.Messager('第一次签到成功！', {
					icon : 'bell', //定义图标
					fade : 'true',
					type : 'primary', // 定义颜色主题
				}).show();
			$("#signDay").text(1);
			}else if(data=="signed"){
				new $.zui.Messager('您已经签过到了！', {
					icon : 'bell', //定义图标
					fade : 'true',
					type : 'primary', // 定义颜色主题
				}).show();
			}else if(data=="signOk"){
				new $.zui.Messager('签到成功！', {
					icon : 'bell', //定义图标
					fade : 'true',
					type : 'primary', // 定义颜色主题
				}).show();
				$("#signDay").text(parseInt($("#signDay").text())+1);
			}
		},
		error:function(e){
			new $.zui.Messager('服务器出问题了，请刷新试试！', {
				icon : 'bell', //定义图标
				fade : 'true',
				type : 'danger', // 定义颜色主题
			}).show(); 
		} 
	});
}
</script>

				<div class="col-md-12">
					<nav class="menu" data-toggle="menu"
						style="width: 200px;margin-top:20px;margin-bottom: 20px;">
					<ul class="nav nav-primary">
						<li class="nav-heading">个人中心</li>
						<li class="active"><a href="${ctx }/home"><i
								class="icon icon-home"></i> 我的主页</a></li>
						<li><a href="${ctx }/accountSetting.jsp"><i
								class="icon-user"></i> 账号设置</a></li>
						<li><a
							href="${ctx }/question/findQuestionByTime"><i
								class="icon icon-question-sign"></i> 我的问题</a></li>
						<li><a
							href="${ctx }/answer/findAnswerByTime"><i
								class="icon icon-reply"></i> 我的回答</a></li>
						<li><a
							href="${ctx }/comment/findCommentByTime"><i
								class="icon icon-comments"></i> 我的评论</a></li>
						<li><a
							href="${ctx }/share/shareByTime"><i
								class="icon icon-share"></i> 我的分享</a></li>
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
										<span> <c:if test="${loginUser.loginName!=null}">
										${loginUser.loginName}
										</c:if> <c:if test="${loginUser.loginName==null}">
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
											<fmt:formatDate
												value="${loginUser.userInfo.userInfoBirthday}"
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
										<span> <c:if
												test="${loginUser.userInfo.userInfoSex!=null}">
										${loginUser.userInfo.userInfoSex}
									    </c:if> <c:if
												test="${loginUser.userInfo.userInfoSex!='男'&&loginUser.userInfo.userInfoSex!='女'}">
										暂无
									    </c:if>

										</span>
									</div>
								</div>
								<div class="profile__mod-inner-item">
									<div>
										<span class="profile__mod-inner-heading">荣誉值</span>
									</div>
									<div>
										<span> <c:if
												test="${loginUser.userInfo.userInfoHonorCount !=null}">
										${loginUser.userInfo.userInfoHonorCount }
									    </c:if> <c:if
												test="${loginUser.userInfo.userInfoHonorCount ==null}">
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
											<span> <c:if test="${loginUser.loginEmail !=null}">
										${loginUser.loginEmail }
									    </c:if> <c:if test="${loginUser.loginEmail ==null}">
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
										<span> <c:if
												test="${loginUser.userInfo.userInfoRegistTime!=null}">
												<fmt:formatDate
													value="${loginUser.userInfo.userInfoRegistTime}"
													pattern="yyyy-MM-dd" />
											</c:if> <c:if test="${loginUser.userInfo.userInfoRegistTime==null}">
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
										<span> <c:if
												test="${loginUser.userInfo.userInfoDescribe!=null }">
										${loginUser.userInfo.userInfoDescribe }</c:if> <c:if
												test="${ loginUser.userInfo.userInfoDescribe == null || loginUser.userInfo.userInfoDescribe == ''}">
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
	<%@ include file="footer.jsp"%>
	<c:set var="userInfo_tags" value="${sessionScope.userInfo_tags }"></c:set>

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
<!-- JSON -->
<script type="text/javascript" src="${ctx }/js/jquery.json-2.3.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var userInfoTag_data = ${userInfo_tags};
						userInfoTag_data = eval(userInfoTag_data);
						var signUpRecords = ${signUpRecords}
						signUpRecords = eval(signUpRecords);
						console.log(signUpRecords);
						if (userInfoTag_data.length == 0) {
							userInfoTag_data = [ {
								label: "暂无数据，默认为",
								value : 1,
								color : '#E5E5E5'
							} ];
						}
						// 使用jquery方法获取 2d context 对象
						var ctx = $("#myChart").get(0).getContext("2d");
						var honor = $("#Honor").get(0).getContext("2d");
						// 或者使用 document.getElementById 获取 2d context 对象
						// var ctx = document.getElementById("myChart").getContext("2d");

						// 使用$.zui.Chart构造Chart实例
						var myNWwChart = new $.zui.Chart(ctx);
						var myNWwChart2 = new $.zui.Chart(honor);
						var data = userInfoTag_data;
                        var a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,l=0;
                        
                        var data3 = signUpRecords;
                        for (var m=0;m<data3.length;m++){
                        if(data3[m].months==1){
                        	a=data3[m].number;
                        }
                        if(data3[m].months==2){
                        	b=data3[m].number;
                        }
                        if(data3[m].months==3){
                        	c=data3[m].number;
                        }
                        if(data3[m].months==4){
                        	d=data3[m].number;
                        }
                        if(data3[m].months==5){
                        	e=data3[m].number;
                        }
                        if(data3[m].months==6){
                        	f=data3[m].number;
                        }
                        if(data3[m].months==7){
                        	g=data3[m].number;
                        }
                        if(data3[m].months==8){
                        	h=data3[m].number;
                        }
                        if(data3[m].months==9){
                        	i=data3[m].number;
                        }
                        if(data3[m].months==10){
                        	j=data3[m].number;
                        }
                        if(data3[m].months==11){
                        	k=data3[m].number;
                        }
                        if(data3[m].months==12){
                        	l=data3[m].number;
                        }
                        }
                        
						var data2 = {
							labels : [ "一月", "二月", "三月", "四月", "五月", "六月",
									"七月", "八月", "九月", "十月", "十一月", "十二月" ],
							datasets : [ {
								label : "蓝队",
								color : 'primary',
								//data:[11,12,13,14,11,12,12,13,56,45,45,56]
								data : [ a, b, c, d, e, f, g, h, i,
										j, k, l ]
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