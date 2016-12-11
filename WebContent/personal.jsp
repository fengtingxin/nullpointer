<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-12">

		<nav class="menu" data-toggle="menu"
			style="width: 200px;margin-top:20px;margin-bottom: 20px;">
		<ul class="nav nav-primary">
			<li class="nav-heading">个人中心</li>
			<li><a href="${ctx }/home.jsp"><i class="icon icon-home"></i>
					我的主页</a></li>
			<li><a href="${ctx }/accountSetting.jsp"><i
					class="icon-user"></i> 账号设置</a></li>
			<li><a
				href="${ctx }/question/findQuestionByTime?userInfoId=${loginUser.loginUserId}"><i
					class="icon icon-question-sign"></i> 我的问题<span
					class="label label-badge label-success">4</span></a></li>
			<li class="active"><a
				href="${ctx }/answer/findAnswerByTime?userInfoId=${loginUser.loginUserId}"><i
					class="icon icon-reply"></i> 我的回答<span
					class="label label-badge label-success">4</span></a></li>
			<li><a
				href="${ctx }/comment/findCommentByTime?userInfoId=${loginUser.loginUserId}"><i
					class="icon icon-comments"></i> 我的评论<span
					class="label label-badge label-success">4</span></a></li>
			<li><a
				href="${ctx }/share/shareByTime?userInfoId=${loginUser.loginUserId}"><i
					class="icon icon-share"></i> 我的分享<span
					class="label label-badge label-success">4</span></a></li>
		</ul>
		</nav>
	</div>
</body>
</html>