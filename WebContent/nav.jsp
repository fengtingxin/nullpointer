<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	session.setMaxInactiveInterval(3600);//单位为秒
%>
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
				<a href="${ctx }/"> <img src="${ctx}/images/logo.png"
					alt="nullpointer" width="200" style="margin-top: 3px;">
				</a>
			</div>
			<!-- 导航项目 -->
			<div class="collapse navbar-collapse navbar-collapse-example">
				<ul class="nav navbar-nav navbar-right">
					<li class="current-menu-item"><a href="${ctx}/index.jsp">主页</a></li>
					<li><a href="${ctx}/bug/listadmin">BUGS</a></li>
					<li><a href="${ctx}/question/list_new">技术问答</a></li>
					<li><a href="${ctx}/help.jsp">帮助</a></li>
					<c:if test="${not empty loginUser}">
						<c:if test="${loginUser.role.roleId ==1}">
							<li><a href="${ctx}/admin/advice">后台</a></li>
						</c:if>
					</c:if>
					<c:if test="${loginUser==null}">
						<li><a href="${ctx}/login.jsp">登录/注册</a></li>
					</c:if>
					<c:if test="${loginUser!=null}">
						<li><a href="${ctx}/logOut">退出</a></li>
					</c:if>
					<!-- 导航中的下拉菜单 -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <c:if test="${loginUser==null}">
								<img src="${ctx}/imgUp/default.jpg" width="20px" height="20px"
									class="img-circle" />
							</c:if> <c:if test="${loginUser!=null}">

								<img
									src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"
									width="20px" height="20px" class="img-circle" />
							</c:if><b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu" style="text-align: center;">
							<li><a href="${ctx}/home">我的主页</a></li>
							<li><a href="${ctx}/accountSetting.jsp">账号设置</a></li>
							<li><a href="${ctx}/contact">建议反馈</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- END .navbar-collapse -->
		</div>
	</div>
</nav>
