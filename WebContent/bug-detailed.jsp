<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en-US"> <![endif]-->
<!--[if IE 7]>    <html class="lt-ie9 lt-ie8" lang="en-US"> <![endif]-->
<!--[if IE 8]>    <html class="lt-ie9" lang="en-US"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en-US">
<!--<![endif]-->
<head>
<!-- META TAGS -->
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>nullpointer</title>
<link rel="shortcut icon" href="${ctx}/images/favicon.png" />
<!-- Style Sheet-->
<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main5152.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/searchKuang.css">
<style id="themeStyle"></style>
<script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("likeDiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","${ctx}/ajax/test1.txt",true);
xmlhttp.send();
}
</script>
<script type="text/javascript"
	src="${ctx }/js/hibernateSearch.js?lang=zh" id="rescourse"
	data="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>"></script>
</head>

<body>
	<%@ include file="nav.jsp"%>
	<!--导航栏完成-->
	<!-- 搜索栏开始 -->
	<%@include file="search-area.jsp"%>
	<!-- 搜索栏完成 -->
	<!--文章开始-->
	<div class="container" style="padding-top: 20px; padding-bottom: 25px;">
		<div style="border: 1px solid #ddd; padding: 20px;">
			<article class="article"> <header>
			<h1 class="text-center">${bug.bugTitle }</h1>
			<dl class="dl-inline">
				<dt>来源：</dt>
				<dd>${bug.userInfo.loginUser.loginName }</dd>
				<dt>
					最后修订：
					<fmt:formatDate value="${bug.bugPublishTime }"
						pattern="yyyy-MM-dd HH:mm" />
				</dt>
				<dd></dd>
				<dt></dt>
				<dd class="pull-right">
					<!-- <span class="label label-success">HTML</span> -->
					<c:set var="tag" value="${bug.tags }"></c:set>
					<c:forEach var="tt" items="${tag }" varStatus="i">
						<c:if test="${i.count%3==1 }">
							<span class="label label-success">${tt.tagName }</span>
						</c:if>
						<c:if test="${i.count%3==2 }">
							<span class="label label-warning">${tt.tagName }</span>
						</c:if>
						<c:if test="${i.count%3==0}">
							<span class="label label-info">${tt.tagName }</span>
						</c:if>

					</c:forEach>
				</dd>
			</dl>
			<section class="abstract">
			<p>
				<strong>摘要：</strong>${bug.bugDescribe }
			</p>
			</section> </header> <section class="content">
			<h2>Bug原因：</h2>
			<p>${bug.bugReason }</p>
			<h2>Bug解决方法</h2>
			<p>${bug.bugMethod }</p>
<!-- 隐藏域，获取bugId,loginUserId -->
<input type="hidden" id="bugId" value="${bug.bugId }" />
			</section> <footer>
			<div class="container" style="margin-top: 25px;">
				<a href="javascript:void(0);" onclick="like()">
					<div class="col-md-6 column thumbs"	style="width: 70px; height: 60px；text-align:center; margin-left:40%; border: 1px solid #ddd;float:left">
						<c:if test="${likeStatus==1 }">
							<div id="likeOutSide" class="haveen">
								<div class="container">
									<i class="icon icon-angle-up icon-3x"></i>
								</div>
								<div class="container"	style="text-align: center; padding-bottom: 10px;">
									<p id="bugLikeNumber" style="margin-bottom: 0px">${bug.bugLikeNum }</p>
									赞
								</div>
							</div>
						</c:if>
						<c:if test="${likeStatus==null }">
							<div id="likeOutSide">
								<div class="container">
									<i class="icon icon-angle-up icon-3x"></i>
								</div>
								<div class="container"
									style="text-align: center; padding-bottom: 10px;">
									<p id="bugLikeNumber" style="margin-bottom: 0px">${bug.bugLikeNum }</p>
									赞
								</div>
							</div>
						</c:if>
						<c:if test="${likeStatus==0}">
							<div id="likeOutSide">
								<div class="container">
									<i class="icon icon-angle-up icon-3x"></i>
								</div>
								<div class="container"
									style="text-align: center; padding-bottom: 10px;">
									<p id="bugLikeNumber" style="margin-bottom: 0px">${bug.bugLikeNum }</p>
									赞
								</div>
							</div>
						</c:if>
					</div>
				</a>
				<a href="javascript:void(0);" onclick="hate()">
					<div class="col-md-6 column thumbs"
						style="width: 70px; height: 60px；text-align:center; border: 1px solid #ddd; margin-left: 1%;float:left">
						<c:if test="${hateStatus==1 }">
							<div id="hateOutSide" class="haveen">
								<div class="container">
									<i class="icon icon-angle-down icon-3x"></i>
								</div>
								<div class="container"
									style="text-align: center; padding-bottom: 10px;">
									<p id="bugHateNumber" style="margin-bottom: 0px">${bug.bugHateNum }</p>
									踩
								</div>
							</div>
						</c:if>

						<c:if test="${hateStatus==null }">
							<div id="hateOutSide">
								<div class="container">
									<i class="icon icon-angle-down icon-3x"></i>
								</div>
								<div class="container"
									style="text-align: center; padding-bottom: 10px;">
									<p id="bugHateNumber" style="margin-bottom: 0px">${bug.bugHateNum }</p>
									踩
								</div>
							</div>
						</c:if>
						<c:if test="${hateStatus==0 }">
							<div id="hateOutSide">
								<div class="container">
									<i class="icon icon-angle-down icon-3x"></i>
								</div>
								<div class="container"
									style="text-align: center; padding-bottom: 10px;">
									<p id="bugHateNumber" style="margin-bottom: 0px">${bug.bugHateNum }</p>
									踩
								</div>
							</div>
						</c:if>
					</div>

				</a>
			</div>
<script type="text/javascript">
		function like(){
			$.ajax({
				url : "like",
				type: "POST",
				method: "post",
				data : {
					bugId : $("#bugId").val(),
				},
				success : function(data, status) {
					if(data == "likeOk"){ //成功点赞
						$('#likeOutSide').addClass("haveen");
						$("#bugLikeNumber").html(parseInt($("#bugLikeNumber").html())+1);
					}else if(data=="cancelLike"){
						$('#likeOutSide').removeClass("haveen");
						$("#bugLikeNumber").html(parseInt($("#bugLikeNumber").html())-1);
					}else if(data=="not ok"){
						new $.zui.Messager('还没有登录哦！', {
							icon : 'bell', //定义图标
							fade : 'true',
							type : 'primary', // 定义颜色主题
						}).show();
					}else if(data == "onHate"){
						new $.zui.Messager('取消踩后才可以赞哦！', {
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
		function hate(){
			$.ajax({
				url : "hate",
				type: "POST",
				method: "post",
				data : {
					bugId : $("#bugId").val(),
				},
				success : function(data, status) {
					if(data == "hateOk"){ //成功点赞
						$('#hateOutSide').addClass("haveen");
						$("#bugHateNumber").html(parseInt($("#bugHateNumber").html())+1);
					}else if(data=="cancelHate"){
						$('#hateOutSide').removeClass("haveen");
						$("#bugHateNumber").html(parseInt($("#bugHateNumber").html())-1);
					}else if(data=="not ok"){
						new $.zui.Messager('还没有登录哦！', {
							icon : 'bell', //定义图标
							fade : 'true',
							type : 'primary', // 定义颜色主题
						}).show();
					}else if(data == "onLike"){
						new $.zui.Messager('取消赞后才可以踩哦！', {
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
</script>

			
			
			
			<!--评论内容开始-->
			<div class="comments">
				<header>

				<h3>所有评论</h3>
				</header>
				<section class="comments-list"> <c:set var="comment"
					value="${bug.comments }">
				</c:set> <!-- 父级 --> <c:forEach var="ct" items="${comment}"
					varStatus="status">
					<c:if test="${ct.parentComment == null }">
						<div class="comment">
							<c:set var="parentId" value="${ct.commentId }"></c:set>
							<a href="${ctx }/hishome?loginName=${ct.userInfo.loginUser.loginName}" class="avatar"> 
                                
								<c:if test="${empty ct.userInfo.userInfoHeadPortrait}">
													<img src="${ctx}/imgUp/default.jpg" width="20px" height="20px"
														class="img-circle" />
												</c:if> <c:if test="${not empty ct.userInfo.userInfoHeadPortrait}">
													<img src="${ctx}/imgUp/${ct.userInfo.userInfoHeadPortrait}"
														width="40px" height="40px" class="img-circle" />
												</c:if>

							</a>
							<div class="content">
								<div class="pull-right text-muted">
									<fmt:formatDate value="${ct.commentPublishTime }"
										pattern="yyyy-MM-dd HH:mm" />
								</div>
								<div>
									<a href="${ctx }/hishome?loginName=${ct.userInfo.loginUser.loginName}"><strong>${ct.userInfo.loginUser.loginName }</strong></a>
								</div>
								<div class="text">${ct.commentContent }</div>
								<div class="actions">
									<a href="javascript:focusAndChangeStatus(${ct.commentId })">回复</a>
								</div>
							</div>

							<div class="comments-list">
								<c:set var="comms" value="${bug.comments}">
								</c:set>
								<c:forEach var="cts" items="${comms}">
									<c:if
										test="${cts.parentComment != null && cts.parentComment.commentId == parentId}">
										<div class="comment">
											<a href="${ctx }/hishome?loginName=${cts.userInfo.loginUser.loginName}" class="avatar"> 
												<c:if test="${empty ct.userInfo.userInfoHeadPortrait}">
													<img src="${ctx}/imgUp/default.jpg" width="40px" height="40px"
														class="img-circle" />
												</c:if> <c:if test="${not empty ct.userInfo.userInfoHeadPortrait}">
													<img src="${ctx}/imgUp/${cts.userInfo.userInfoHeadPortrait}"
														width="40px" height="40px" class="img-circle" />
												</c:if>
											</a>
											<div class="content">
												<div class="pull-right text-muted">
													<fmt:formatDate value="${cts.commentPublishTime }"
														pattern="yyyy-MM-dd HH:mm" />
												</div>
												<div>
													<a href="${ctx }/hishome?loginName=${cts.userInfo.loginUser.loginName}"><strong>${cts.userInfo.loginUser.loginName }</strong></a>
													<span class="text-muted">回复</span> <a href="${ctx }/hishome?loginName=${ct.userInfo.loginUser.loginName}">${ct.userInfo.loginUser.loginName }</a>
												</div>
												<div class="text">${cts.commentContent}</div>
												<div class="actions">
													<c:if test="${cts.userInfo.loginUser.loginUserId==loginUser.loginUserId}">
														<a href="${ctx }/comment/delete?commentId=${cts.commentId}&bugId=${bug.bugId}">删除</a>
													</c:if>

												</div>
											</div>
										</div>
									</c:if>

								</c:forEach>
							</div>
						</div>
					</c:if>
				</c:forEach> </section>
				<footer>
				<div class="reply-form" id="commentReplyForm2">
					<a href="###" class="avatar"> 
						<c:if test="${loginUser==null }">
							<img src="${ctx}/imgUp/default.jpg"	width="40px" height="40px" class="img-circle" />
						</c:if>
						<c:if test="${loginUser!=null }">
							<img src="${ctx}/imgUp/${loginUser.userInfo.userInfoHeadPortrait}"	width="40px" height="40px" class="img-circle"/>
						</c:if>
					</a>
					<form id="comment_form_submit" class="form" method="post"
						action="${ctx }/bug/${bug.bugId}">
						<div class="form-group">
							<textarea id="content_submit" name="content"
								class="form-control kindeditor"></textarea>
						</div>
						<input type="hidden" id="commentIdInput" name="commentId" value="" />
						<div class="form-group comment-user">
							<div class="col-md-2 pull-right">
								<button type="submit" class="btn btn-block btn-primary"
									onclick="formValidation()">提交评论</button>
							</div>
						</div>
					</form>
				</div>
				</footer>
			</div>
			<!--评论内容结束--> </footer> </article>
		</div>
	</div>
	<!--文章完成-->
	<script type="text/javascript">
/*
 * 当点击回复时，修改commentId为点击回复的值
 同时滚动到输入框的div
 */
function focusAndChangeStatus(comentId){
	document.getElementById("commentIdInput").value=comentId; //修改ID
    $('html, body').animate({  
        scrollTop: $("#commentReplyForm2").offset().top
    }, 1000);
}
</script>
	<c:if test="${not empty bug_detailed_judge }">
		<!-- 提示部分！ -->
		<script type="text/javascript">
	window.onload=function(){
		new $.zui.Messager('<%=request.getAttribute("bug_detailed_bell")%>', {
							icon : 'bell', //定义图标
							fade : 'true',
							type : 'primary', // 定义颜色主题
						}).show();
			}
		</script>
	</c:if>
	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>

</body>
<!-- script -->
<script src="${ctx }/assets/jquery.js"></script>
<script src="${ctx }/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx }/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx }/docs/js/zui.min.js"></script>
<script src="${ctx }/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script src="${ctx }/assets/prettify/prettify.js"></script>
<link type="text/css" href="${ctx }/assets/prettify/prettify.css"></link>
<script src="${ctx }/assets/marked/marked.min.js"></script>
<script src="${ctx }/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		KindEditor.create('textarea[id="content_submit"]', {
			basePath : '${ctx}/js/kindeditor/',
			allowFileManager : true,
			bodyClass : 'article-content',
			afterBlur : function() {
				this.sync();
			}
		});
	});
</script>
<script src="${ctx}/js/hibernateSearch.js"></script>

</html>