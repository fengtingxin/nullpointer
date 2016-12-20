<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第一行</title>
</head>
<body>
		<c:if test="${empty loginUser }">
		<%
			response.sendRedirect("/nullpointer/admin/login.jsp");
		%>
		</c:if>
		<%
		session.setMaxInactiveInterval(3600);//单位为秒
		%>
<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<a href="${ctx }/"> <img src="${ctx}/images/logo.png"
					alt="nullpointer" width="200">
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<c:if test="${loginUser==null}">
								<img src="${ctx}/imgUp/default.jpg" width="20px" height="20px"
									class="img-circle" />
							</c:if> <c:if test="${loginUser!=null}">

								<img
									src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
									width="30px" height="30px" class="nav-user-photo" alt="${loginUser.loginName }的头像" />
							</c:if>
								<span class="user-info">
									<small>欢迎,</small>
									${loginUser.loginName }
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="${ctx }/admin/logOut">
										<i class="icon-off"></i>
										退出登录
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
</body>
</html>