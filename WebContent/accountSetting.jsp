<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>nullpointer</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.png" />
<!-- Style Sheet-->
<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main5152.css">
<!-- 头像上传  -->
<link href="${ctx}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/jquery.Jcrop.min.css" rel="stylesheet"
	type="text/css" />

<!-- add scripts -->
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/jquery.Jcrop.min.js"></script>


<style id="themeStyle"></style>
<style type="text/css">
#changeHeadImgText {
	position: absolute;
	z-index: 1;
	left: 6.2em;
	top: 6em;
	color: black;
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
	<%@ include file="nav.jsp"  %>
	<!--导航栏完成-->
	<!-- End of Header -->
	<div class="container" style="padding-top: 20px;">
		<div class="col-md-3 column">

			<div class="col-md-11 column">
				<div class="example"
					style="text-align: center; background-color: #fafafa;">
					<div class="container">

						<img src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
							width="100" height="100" class="img-circle" alt="头像"
							id="personalImage" />

						<div id="changeHeadImgText" style="display: none">
							<a style="color: white; cursor: pointer;" data-type="iframe"
								data-url="${ctx }/modifyAvatar.jsp" data-width="900px"
								data-height="800px" data-toggle="modal">修改头像</a>
						</div>
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
				</div>
				<div class="col-md-12">
					<nav class="menu" data-toggle="menu"
						style="width: 200px;margin-top:20px;margin-bottom: 20px;">
					<ul class="nav nav-primary">
						<li class="nav-heading">个人中心</li>
						<li><a href="${ctx }/home"><i
								class="icon icon-home"></i> 我的主页</a></li>
						<li class="active"><a href="${ctx }/accountSetting.jsp"><i
								class="icon-user"></i> 账号设置</a></li>
						<li><a href="${ctx }/question/findQuestionByTime"><i
								class="icon icon-question-sign"></i> 我的问题</a></li>
						<li><a href="${ctx }/answer/findAnswerByTime"><i
								class="icon icon-reply"></i> 我的回答</a></li>
						<li><a href="${ctx }/comment/findCommentByTime"><i
								class="icon icon-comments"></i> 我的评论</a></li>
						<li><a href="${ctx }/share/shareByTime"><i
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
					hi!&nbsp;&nbsp;&nbsp;${loginUser.loginName}&nbsp;&nbsp;&nbsp;你已经在这里度过了${request.day}天${hour }小时${min }分钟${second }秒
				</div>
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">×</button>
			</div>

			<form class="form-horizontal" action="${ctx }/edit" method="post">
				<div class="form-group">
					<label for="exampleInputAccount4" class="col-sm-2">账号:</label>
					<div class="col-sm-10">
						<p class="form-control-static">${loginUser.getLoginEmail() }</p>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputAccount4" class="col-sm-2">用户名：</label>
					<div class="col-sm-4">
						<input type="text" id="loginName" class="form-control" name="loginName"
							value="${loginUser.getLoginName() }"
							placeholder="${loginUser.getLoginName() } ">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword4" class="col-sm-2">个性签名:</label>
					<div class="col-sm-10">
						<textarea type="text" id="describe" name="describe" class="form-control">${loginUser.userInfo.userInfoDescribe }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword4" class="col-sm-2">性别:</label>
					<c:if test="${not empty loginUser.userInfo.userInfoSex}">
						<c:if test="${loginUser.userInfo.userInfoSex=='男'}">
							<label class="radio-inline-3">
								<input id="sex" type="radio" name="sex" value="男" checked="checked"/> 男
							</label>
							<label class="radio-inline-3">
								<input id="sex" type="radio" name="sex" value="女"/> 女
							</label>
						</c:if>
						<c:if test="${loginUser.userInfo.userInfoSex=='女'}">
							<label class="radio-inline-3">
								<input id="sex" type="radio" name="sex" value="男" /> 男
							</label>
							<label class="radio-inline-3">
								<input id="sex" type="radio" name="sex" value="女" checked="checked"/> 女
							</label>
						</c:if>
					</c:if>
					<c:if test="${empty loginUser.userInfo.userInfoSex}">
						<label class="radio-inline-3"> 
							<input id="sex" type="radio" name="sex" value="男"/> 男
						</label>
						<label class="radio-inline-3">
							<input id="sex" type="radio" name="sex" value="女"/> 女
						</label>
					</c:if>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword4" class="col-sm-2">出生日期:</label>
					<div class="col-sm-10">
						<fmt:formatDate var="birthday"
							value="${loginUser.userInfo.userInfoBirthday}"
							pattern="yyyy-MM-dd" />
						<input id="birthday" name="birthday" onclick="laydate()"
							value="${birthday}"
							style="padding: 7px 10px; border: 1px solid #ccc; margin-right: 10px;"
							class="laydate-icon" />
					</div>
				</div>

				<div class="form-group" style="margin-top: 30px;">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" onclick="updateUserInfo()" class="btn btn-primary">更新信息</button>
					</div>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
function updateUserInfo(){
	var  loginName=$("#loginName").val();
	var  sex=$('input:radio[name="sex"]:checked').val();
	var  describe=$("#describe").val();
	var  birthday=$("#birthday").val();
	
	if(loginName.replace(/(^s*)|(s*$)/g, "").length==0 || null == loginName || "" == loginName){
		new $.zui.Messager('用户名不能为空哦！', {
			icon : 'bell', //定义图标
			fade : 'true',
			type : 'primary', // 定义颜色主题
		}).show();
	}else{
		$.ajax({
			url : "/nullpointer/edit",
			type: "POST",
			method: "post",
			data : {
				loginName : loginName,
				sex:sex,
				describe:describe,
				birthday:birthday,
			},
			success : function(data, status) {
				if(data == "updateOk"){ //成功点赞
					window.location.href = "/nullpointer/home";
				}else if(data=="cancelLike"){
					$('#likeOutSide').removeClass("haveen");
					$("#bugLikeNumber").html(parseInt($("#bugLikeNumber").html())-1);
				}else if(data=="encodeError"){
					new $.zui.Messager('您的输入有误，请刷新重试！', {
						icon : 'bell', //定义图标
						fade : 'true',
						type : 'primary', // 定义颜色主题
					}).show();
				}else if(data == "loginNameUsed"){
					new $.zui.Messager('用户名已经存在了哦，请更换一个！', {
						icon : 'bell', //定义图标
						fade : 'true',
						type : 'primary', // 定义颜色主题
					}).show();
				}else if(data == "dateError"){
					new $.zui.Messager('您的输入有误！', {
						icon : 'bell', //定义图标
						fade : 'true',
						type : 'primary', // 定义颜色主题
					}).show();
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
}
</script>
	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>

</body>
<script src="${ctx}/assets/jquery.js"></script>
<script src="${ctx}/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx}/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx}/docs/js/zui.min.js"></script>
<script src="${ctx}/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx}/assets/prettify/prettify.js"></script>
<script src="${ctx}/assets/marked/marked.min.js"></script>

<script src="${ctx}/js/jquery.Jcrop.min.js"></script>
<script src="${ctx}/js/script.js"></script>

<script src="${ctx}/js/laydate.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//当鼠标移到这里时
		$("#personalImage").mouseover(function() {
			$("#changeHeadImgText").show();
		})
		$("#personalImage").mouseout(function() {
			$("#changeHeadImgText").hide();
		})
		$("#changeHeadImgText").mouseover(function() {
			$("#changeHeadImgText").show();
		})
		$("#changeHeadImgText").mouseout(function() {
			$("#changeHeadImgText").hide();
		})
	});
</script>


</html>