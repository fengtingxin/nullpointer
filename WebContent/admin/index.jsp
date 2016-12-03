<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户建议</title>
	<!--[if lt IE 9]>
		<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<script src="Javascript/Flot/excanvas.js"></script>
	<![endif]-->
	<!-- The Fonts -->
	<!-- The Main CSS File -->
	<link rel="stylesheet" href="CSS/style.css" />
	<!-- jQuery -->
	<script src="Javascript/jQuery/jquery-1.7.2.min.js"></script>
	<!-- Flot -->
	<script src="Javascript/Flot/jquery.flot.js"></script>
	<script src="Javascript/Flot/jquery.flot.resize.js"></script>
	<script src="Javascript/Flot/jquery.flot.pie.js"></script>
	<!-- DataTables -->
	<script src="Javascript/DataTables/jquery.dataTables.min.js"></script>
	<!-- ColResizable -->
	<script src="Javascript/ColResizable/colResizable-1.3.js"></script>
	<!-- jQuryUI -->
	<script src="Javascript/jQueryUI/jquery-ui-1.8.21.min.js"></script>
	<!-- Uniform -->
	<script src="Javascript/Uniform/jquery.uniform.js"></script>
	<!-- Tipsy -->
	<script src="Javascript/Tipsy/jquery.tipsy.js"></script>
	<!-- Elastic -->
	<script src="Javascript/Elastic/jquery.elastic.js"></script>
	<!-- ColorPicker -->
	<script src="Javascript/ColorPicker/colorpicker.js"></script>
	<!-- SuperTextarea -->
	<script src="Javascript/SuperTextarea/jquery.supertextarea.min.js"></script>
	<!-- UISpinner -->
	<script src="Javascript/UISpinner/ui.spinner.js"></script>
	<!-- MaskedInput -->
	<script src="Javascript/MaskedInput/jquery.maskedinput-1.3.js"></script>
	<!-- ClEditor -->
	<script src="Javascript/ClEditor/jquery.cleditor.js"></script>
	<!-- Full Calendar -->
	<script src="Javascript/FullCalendar/fullcalendar.js"></script>
	<!-- Color Box -->
	<script src="Javascript/ColorBox/jquery.colorbox.js"></script>
	<!-- Kanrisha Script -->
	<script src="Javascript/kanrisha.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
	<!-- Top Panel -->
	<div class="top_panel">
		<div class="wrapper">
			<div class="user">
				<img src="Images/user_avatar.png" alt="user_avatar" class="user_avatar" />
				<span class="label">John Alex</span>
				<!-- Top Tooltip -->
				<div class="top_tooltip">
					<div>
						<ul class="user_options">
							<li class="i_16_profile"><a href="#" title="Profile"></a></li>
							<li class="i_16_tasks"><a href="#" title="Tasks"></a></li>
							<li class="i_16_notes"><a href="#" title="Notes"></a></li>
							<li class="i_16_options"><a href="#" title="Options"></a></li>
							<li class="i_16_logout"><a href="#" title="Log-Out"></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<header class="main_header">
		<div class="wrapper">
			<div class="logo">
				<a href="#" title="Kanrisha Home">
					<img src="Images/kanrisha_logo.png" alt="kanrisha_logo" />
				</a>
			</div>
		</div>
	</header>

	<div class="wrapper small_menu">
		<ul class="menu_small_buttons">
			<li><a title="General Info" class="i_22_dashboard smActive" href="advice.html"></a></li>
			<li><a title="Your Messages" class="i_22_inbox" href="bug_review.html"></a></li>
			<li><a title="Visual Data" class="i_22_charts" href="find_Users.html"></a></li>
			<li><a title="Kit elements" class="i_22_ui" href="publish_bug.html"></a></li>
			<li><a title="Some Rows" class="i_22_tables" href="find_bug.html"></a></li>

		</ul>
	</div>

	<div class="wrapper contents_wrapper">
		
		<aside class="sidebar">
			<ul class="tab_nav">
				<li class="active_tab i_32_dashboard">
					<a href="advice.html" title="General Info">
						<span class="tab_label">用户建议</span>
						<span class="tab_info">用户提交的建议</span>
					</a>
				</li>
				<li class="i_32_inbox">
					<a href="bug_review.html" title="Your Messages">
						<span class="tab_label">审核bug</span>
						<span class="tab_info">审核用户分享的bug</span>
					</a>
				</li>
				<li class="i_32_charts">
					<a href="find_Users.html" title="Visual Data">
						<span class="tab_label">查询用户</span>
						<span class="tab_info">查询</span>
					</a>
				</li>
				<li class="i_32_ui">
					<a href="publish_bug.html" title="Kit elements">
						<span class="tab_label">发布bug</span>
						<span class="tab_info">发布bug</span>
					</a>
				</li>
				<li class="i_32_tables">
					<a href="find_bug.html" title="Some Rows">
						<span class="tab_label">查询bug</span>
						<span class="tab_info">查询已经发布bug</span>
					</a>
				</li>
			</ul>
		</aside>

		<div class="contents">
			<div class="grid_wrapper">
				<div class="g_6 contents_header">
					<h3 class="i_16_dashboard tab_label">用户建议</h3>
					<div><span class="label">用户提交的建议</span></div>
				</div>
				<div class="g_12 separator"><span></span></div>

				<!-- Quick Statistics -->
				<div class="g_3 quick_stats">
					<div class="big_stats visitor_stats">
						9827
					</div>
					<h5 class="stats_info">评论数量</h5>
				</div>
				<div class="g_3 quick_stats">
					<div class="big_stats tickets_stats">
						23
					</div>
					<h5 class="stats_info">bug数量</h5>
				</div>
				<div class="g_3 quick_stats">
					<div class="big_stats users_stats">
						982
					</div>
					<h5 class="stats_info">用户人数</h5>
				</div>
				<div class="g_3 quick_stats">
					<div class="big_stats orders_stats">
						2045
					</div>
					<h5 class="stats_info">question数量</h5>
				</div>
				<div class="g_12 separator"><span></span></div>
				<!--advice table-->
				<div class="g_12">
					<div class="widget_header">
						<h4 class="widget_header_title wwIcon i_16_tooltip">用户建议</h4>
					</div>
					<div class="widget_contents noPadding twCheckbox">
						<table class="tables datatable noObOLine">
							<thead>
								<tr>
									<th>
										<input type="checkbox" class="simple_form tMainC">`
									</th>
									<th>用户名</th>
									<th>建议标题</th>
									<th>建议内容</th>
									<th>是否查看</th>
								</tr>
							</thead>
							<tbody>
								<tr class="status_open">
									<td><input type="checkbox" class="simple_form"></td>
									<td><a href="advice_detail.html">123123</a></td>
									<td>I Can't Remember My Password</td>
									<td>High</td>
									<td>Open</td>
								</tr>
								<tr class="status_open">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238750</td>
									<td>My Account Was Hacked</td>
									<td>Low</td>
									<td>Open</td>
								<tr class="status_open">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238751</td>
									<td>Can I Change My UserName</td>
									<td>Medium</td>
									<td>Open</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238752</td>
									<td>I Want To Delete My Account Permanently</td>
									<td>Low</td>
									<td>Closed</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238753</td>
									<td>Is It Possible To Create Two Account</td>
									<td>Medium</td>
									<td>Closed</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238754</td>
									<td>There Is A Bug On Your Website</td>
									<td>High</td>
									<td>Closed</td>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238755</td>
									<td>I Can't Found The RSS</td>
									<td>Low</td>
									<td>Closed</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238756</td>
									<td>What's The Price Of The Pro Account</td>
									<td>High</td>
									<td>Closed</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238757</td>
									<td>Can I Upload More Than 30Mb</td>
									<td>Medium</td>
									<td>Closed</td>
								</tr>
								<tr class="status_closed">
									<td><input type="checkbox" class="simple_form"></td>
									<td>#238758</td>
									<td>The Server Is Slow</td>
									<td>High</td>
									<td>Closed</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>

	<footer>
		<div class="wrapper">
			<span class="copyright">
				&copy; Copyright &copy; 2016.Company name All rights reserved. More Templates <a href="0.0" target="_blank" title="EXP项目小组">EXP项目小组</a> - Collect from <a href="0.0" title="0.0" target="_blank">0.0</a>
			</span>
		</div>
	</footer>
</body>
</html>