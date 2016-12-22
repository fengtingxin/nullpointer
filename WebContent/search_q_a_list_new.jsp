<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" type="text/css" href="${ctx}/css/searchKuang.css">
<link href="${ctx}/docs/css/zui.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui.lite.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/zui-theme.css">
<link href="${ctx}/docs/css/doc.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/main5152.css">
<style id="themeStyle"></style>
<style type="text/css">
#top .primary-color, a, #cancel-comment-reply-link, .blog-tags a:hover,
	.relThumb a:hover strong, .flex_column h1, .flex_column h2,
	.flex_column h3, .flex_column h4, .flex_column h5, .flex_column h6,
	#top #wrap_all .tweet-text a, #top #js_sort_items a.active_sort,
	.callout a:hover {
	color: #f0b70c;
}
</style>
<script type="text/javascript"
	src="${ctx }/js/hibernateSearch.js?lang=zh" id="rescourse"
	data="<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"%>"></script>
</head>

<body>
	<%@ include file="nav.jsp"%>

	<!--导航栏完成-->
	<!--搜索框-->
	<%@include file="search-area.jsp"%>
	<!--搜索框完成-->
	<div class="container" style="padding-top: 20px; padding-bottom: 25px;">
		<div class="col-md-8 column"
			style="border: 1px solid #ddd; padding: 20px;">
			<h3>
					<i class="icon-list-ul"></i> 问题查询 <small>
						共${questionpageCount}条</small> 
						<small>&nbsp;所用时间：${experienceTime }秒</small>
				</h3>
				<hr/>
			<div class="tab-content">
				<c:if test="${not empty questionpages.list }">
				<div class="tab-pane fade active in" id="tab2Content1">
					<div class="items items-hover">
						<!--标签1内容开始-->
						<c:set var="questionList_theNew" value="${questionpages.list}"></c:set>
						<c:forEach var="question" items="${ questionList_theNew}">
							<div class="item">
								<div class="item-heading">
									<h2 class="post-title">
										<a
											href="${ctx }/question/findone?questionId=${question.questionId}">${question.questionTitle }</a>
									</h2>
								</div>
								<div class="item-content">
									<div class="text">${question.questionDescribe }</div>
								</div>
								<div class="item-footer">
									<a href="#" class="text-muted"><i class="icon-comments"></i>
										${fn:length(question.answers)}</a> &nbsp; <a href="#"
										class="text-muted"><i class="icon-thumbs-o-up"></i>
										${question.questionLikeNum } </a> &nbsp; <span class="text-muted">
										<fmt:formatDate value="${question.questionPublishTime }"
											pattern="yyyy-MM-dd HH:mm" />
									</span>
								</div>
							</div>
						</c:forEach>
						<!--分页实现-->
						<ul class="pager pager-loose">
							<li class="previous"><a
								href="${ctx}/hibernateSearch/findQuestionByPage?pageQuestionNum=${questionpages.prePageNum}">«</a></li>
							<c:forEach begin="1" end="${questionpages.totalPageNum }"
								var="pageNum">
								<c:if test="${pageNum ==questionpages.currentPageNum}">
									<li class="active"><a name="pagen"
										href="${ctx }/bug/listadmin?pageNum=${pageNum }">${pageNum }</a></li>
								</c:if>
								<c:if test="${pageNum!=questionpages.currentPageNum}">
									<li><a name="pagen"
										href="${ctx }/hibernateSearch/findQuestionByPage?pageQuestionNum=${pageNum}">${pageNum }</a></li>
								</c:if>
							</c:forEach>

							<li class="next"><a
								href="${ctx}/hibernateSearch/findQuestionByPage?pageQuestionNum=${questionpages.nextPageNum}">»</a></li>
						</ul>
					</div>
					<!--标签1内容结束-->
				</div>
				</c:if>
				<c:if test="${empty questionpages.list }">
					暂时不存在该数据
				</c:if>
				
				<div class="tab-pane fade" id="tab2Content2">
					<!--<p>标签2的内容。</p>-->

					<!--标签2内容结束-->
				</div>
				<div class="tab-pane fade" id="tab2Content3">
					<!--<p>这是标签3的内容。</p>-->

					<!--标签3内容结束-->
				</div>
			</div>


		</div>
		<div class="col-md-4 column" style="margin-top: 30px;">
			<!-- 如果用户没有登陆 提示登陆 -->
			<c:if test="${empty loginUser }">
				<div class="col-md-12" style="margin-top: 20px;">
					<h2>
						<i class="icon icon-comments icon-2x"></i> 没有你想要的问题？
					</h2>
					<a data-toggle="tooltip" data-placement="right" id="share"><button
							class="btn btn-success btn-lg" type="button"
							onclick="verificate()">向大哲们提问</button></a>

				</div>
				<!-- 点击分享之后 -->
				<script type="text/javascript">
					function verificate() {
						new $.zui.Messager('您还没有登录哦！', {
							icon : 'heart',
							placement : 'top',// 定义显示位置
							type : 'warning',
						}).show();
					}
				</script>
			</c:if>
			<!-- 如果用户已经登陆 -->
			<c:if test="${not empty loginUser }">
				<div class="col-md-12" style="margin-top: 20px;">
					<h2>
						<i class="icon icon-comments icon-2x"></i> 没有你想要的问题？
					</h2>
					<a href="${ctx }/question.jsp"><button
							class="btn btn-success btn-lg" type="button">向大哲们提问</button></a>

				</div>
			</c:if>

			<div class="col-md-12" style="margin-top: 20px;">
				<h2>
					<i class="icon icon-align-left icon-2x"></i> Tag
				</h2>
				<div class="tagcloud">
					<c:set var="tag" value="${sessionScope.tagList}"></c:set>
					<c:forEach var="tt" items="${tag}">
						<c:if test="${tt.tagName =='C++'}">
							<a href="${ctx}/question/list_answer?tagName=C%2B%2B"
								class="btn btn-primary">${tt.tagName}</a>
						</c:if>
						<c:if test="${tt.tagName !='C++'}">
							<a href="${ctx}/question/list_answer?tagName=${tt.tagName}"
								class="btn btn-primary">${tt.tagName}</a>
						</c:if>

					</c:forEach>
				</div>

			</div>
		</div>

	</div>


	<!-- end of #footer -->

	<!-- Footer Bottom -->
	<%@ include file="footer.jsp"%>

</body>
<!-- script -->
<script src="${ctx}/assets/jquery.js"></script>
<script src="${ctx}/assets/clipboard/clipboard.min.js"></script>
<script src="${ctx}/assets/less/less.min.js"></script>

<!-- ZUI Javascript组件 -->
<script src="${ctx}/docs/js/zui.min.js"></script>
<script src="${ctx}/docs/js/doc.min.js"></script>
<!-- 增强文档插件 -->
<script async src="${ctx}/assets/prettify/prettify.js"></script>
<script src="${ctx}/assets/marked/marked.min.js"></script>
<script src="${ctx}/js/hibernateSearch.js"></script>
</html>