<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page errorPage="error.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%request.setCharacterEncoding("utf-8"); %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript" src="${ctx }/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/page_regist.js?lang=zh"></script>

<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<link href="${ctx }/resources/style/style.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="${ctx }/resources/js/jquery.i18n.properties-1.0.9.js" ></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/md5.js"></script>


</head>
<body class="loginbody">
<div class="dataEye">
    <div>
         <a href="${ctx }/index"><img src="${ctx }/resources/images/logo_touming.gif" width="240px" height="60px" /></a>
    </div>
    <div class="loginbox registbox">
        <div id="bug-manager">
             <h3> nullpointer网站注册</h3>
        </div>
        <div class="login-content reg-content">
            <div class="loginbox-title">
                
            </div>
            <form id="signupForm">
            <div class="login-error"></div>
            <div class="row">
                <label class="field" for="name">用户名</label>
                <input type="text" value="" class="input-text-user noPic input-click" name="name" id="name">
            </div>
            <div class="row">
                <label class="field" for="email">注册邮箱</label>
                <input type="text" value="" class="input-text-user noPic input-click" name="email" id="email">
            </div>
            <div class="row">
                <label class="field" for="password">密码</label>
                <input type="password" value="" class="input-text-password noPic input-click" name="password" id="password">
            </div>	
            <div class="row">
                <label class="field" for="passwordAgain">确认密码</label>
                <input type="password" value="" class="input-text-password noPic input-click" name="passwordAgain" id="passwordAgain">
            </div>
            <div class="row btnArea">
                <a class="login-btn" id="submit">注册</a>
            </div>
            </form>
        </div>
        <div class="go-regist">
            已有帐号,请<a href="${ctx }/login.jsp" class="link">登录</a>
        </div>
    </div>
    
<div id="footer">
    Copyright © 1998-2016 nullpointer All Rights Reserved nullpointer 版权所有
</div>
</div>
<div class="loading">
    <div class="mask">
        <div class="loading-img">
        <img src="${ctx }/resources/images/loading.gif" width="31" height="31">
        </div>
    </div>
</div>
</body>
</html>