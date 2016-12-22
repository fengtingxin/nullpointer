<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page errorPage="error-500.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布bug --nullpointer</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx }/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome.min.css" />
<!-- 网站logo缩略图 -->
<link rel="shortcut icon" href="${ctx }/images/favicon.png" />
<link rel="shortcut icon" href="${ctx }/images/favicon.ico" />
<c:if test="${empty loginUser }">
		<%
			response.sendRedirect("login.jsp");
		%>
</c:if>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="${ctx }/admin/assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/chosen.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/daterangepicker.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/colorpicker.css" />

		<!-- fonts -->



		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx }/admin/assets/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx }/admin/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx }/admin/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx }/admin/assets/js/html5shiv.js"></script>
		<script src="${ctx }/admin/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<%@ include file="nav.jsp"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<ul class="nav nav-list">
						<li>
							<a href="${ctx }/admin/advice.jsp">
								<i class="icon-dashboard"></i>
								<span class="menu-text" id="controllerT"> 控制台 </span>
							</a>
						</li>

						<li>
							<a href="${ctx }/admin/advice">
								<i class="icon-text-width"></i>
								<span class="menu-text"> 用户建议 </span>
							</a>
						</li>
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-edit"></i>
								<span class="menu-text"> bug管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="${ctx }/admin/bug" class="active">
										<i class="icon-double-angle-right"></i>
										查询bug
									</a>
								</li>

								<li>
									<a href="${ctx }/admin/bug_review">
										<i class="icon-double-angle-right"></i>
										bug审核
									</a>
								</li>

								<li class="active">
									<a href="${ctx }/admin/bug_publish.jsp">
										<i class="icon-double-angle-right"></i>
										发布bug
									</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text"> 用户管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="${ctx }/admin/users">
										<i class="icon-double-angle-right"></i>
										用户详情
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${ctx }/admin/advice">Home</a>
							</li>

							<li>
								<a href="${ctx }/admin/bug">bug管理</a>
							</li>
							
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								发布bug
								<small>
									<i class="icon-double-angle-right"></i>
									创建一个新的bug
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> bug标题 </label>
										<div class="col-sm-9">
											<input type="text" id="bugTitle" name="bugTitle" placeholder="bug标题" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-4">bug标签</label>

										<div class="col-sm-9">
											<div>
												<div class="space-2"></div>
													<select name="tags" multiple="" class="width-80 chosen-select" id="form-field-select-4" data-placeholder="选择一个标签">
														<c:forEach	var="tt" items="${tags}">
															<option value="${tt.tagName }">${tt.tagName }</option>
														</c:forEach>
													</select>
												</div>
										</div>
									</div>
									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> bug描述 </label>
										<div class="col-sm-9">
											<textarea id="bugDescribe" name="bugDescribe" class="form-control kindeditor" maxlength="200"></textarea>
											
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> bug原因 </label>
										<div class="col-sm-9">
											<textarea id="bugReason" name="bugReason" class="form-control kindeditor" maxlength="200"></textarea>
										</div>
									</div>

									<div class="space-4"></div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 解决方法 </label>
										<div class="col-sm-9">
											<textarea id="bugMethod" name="bugMethod" class="form-control kindeditor"></textarea>
										</div>
									</div>

									<div class="space-4"></div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" onclick="submitForm()" type="button">
												<i class="icon-ok bigger-110"></i>
												Submit
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="icon-undo bigger-110"></i>
												Reset
											</button>
										</div>
									</div>


								</form>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
<!-- 提交部分 -->
<script type="text/javascript">
function submitForm(){
	var b =document.getElementsByClassName("search-choice");
	var bugList =new Array(b.length);
	for(var i=0;i<b.length;i++){
		bugList[i]=b[i].firstChild.innerHTML;
	}
	$.ajax({
		url : "publishBug",
		type: "POST",
		method: "post",
		data : {
			bugTitle : $("#bugTitle").val(),
			tags : bugList.join(","),
			bugDescribe:$("#bugDescribe").val(),
			bugReason:$("#bugReason").val(),
			bugMethod:$("#bugMethod").val(),
		},
		success : function(data, status) {
			if(data=="ok"){
				alert("bug发布成功！");
				window.location.href = "bug_publish";
			}else {
				if(data=="1"){
				alert("服务器出现问题，请程序员尽快调试！")
				}else{
					alert("您的填写有误！");
				}
			}
			
		},
		error:function(e){
		    alert("错误");   
		} 
	});
}

</script>
				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; 选择皮肤</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl">从右向左 (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								中间位置
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="${ctx }/admin/assets/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx }/admin/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${ctx }/admin/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx }/admin/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctx }/admin/assets/js/bootstrap.min.js"></script>
		<script src="${ctx }/admin/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${ctx }/admin/assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${ctx }/admin/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx }/admin/assets/js/chosen.jquery.min.js"></script>
		<script src="${ctx }/admin/assets/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="${ctx }/admin/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx }/admin/assets/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="${ctx }/admin/assets/js/date-time/moment.min.js"></script>
		<script src="${ctx }/admin/assets/js/date-time/daterangepicker.min.js"></script>
		<script src="${ctx }/admin/assets/js/bootstrap-colorpicker.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.knob.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.autosize.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="${ctx }/admin/assets/js/jquery.maskedinput.min.js"></script>
		<script src="${ctx }/admin/assets/js/bootstrap-tag.min.js"></script>

		<!-- ace scripts -->

		<script src="${ctx }/admin/assets/js/ace-elements.min.js"></script>
		<script src="${ctx }/admin/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
				$('#id-disable-check').on('click', function() {
					var inp = $('#form-input-readonly').get(0);
					if(inp.hasAttribute('disabled')) {
						inp.setAttribute('readonly' , 'true');
						inp.removeAttribute('disabled');
						inp.value="This text field is readonly!";
					}
					else {
						inp.setAttribute('disabled' , 'disabled');
						inp.removeAttribute('readonly');
						inp.value="This text field is disabled!";
					}
				});
			
			
				$(".chosen-select").chosen(); 
				$('#chosen-multiple-style').on('click', function(e){
					var target = $(e.target).find('input[type=radio]');
					var which = parseInt(target.val());
					if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
					 else $('#form-field-select-4').removeClass('tag-input-style');
				});
			
			
				$('[data-rel=tooltip]').tooltip({container:'body'});
				$('[data-rel=popover]').popover({container:'body'});
				
				$('textarea[class*=autosize]').autosize({append: "\n"});
				$('textarea.limited').inputlimiter({
					remText: '%n character%s remaining...',
					limitText: 'max allowed : %n.'
				});
			
				$.mask.definitions['~']='[+-]';
				$('.input-mask-date').mask('99/99/9999');
				$('.input-mask-phone').mask('(999) 999-9999');
				$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
				$(".input-mask-product").mask("a*-999-a999",{placeholder:" ",completed:function(){alert("You typed the following: "+this.val());}});
			
			
			
				$( "#input-size-slider" ).css('width','200px').slider({
					value:1,
					range: "min",
					min: 1,
					max: 8,
					step: 1,
					slide: function( event, ui ) {
						var sizing = ['', 'input-sm', 'input-lg', 'input-mini', 'input-small', 'input-medium', 'input-large', 'input-xlarge', 'input-xxlarge'];
						var val = parseInt(ui.value);
						$('#form-field-4').attr('class', sizing[val]).val('.'+sizing[val]);
					}
				});
			
				$( "#input-span-slider" ).slider({
					value:1,
					range: "min",
					min: 1,
					max: 12,
					step: 1,
					slide: function( event, ui ) {
						var val = parseInt(ui.value);
						$('#form-field-5').attr('class', 'col-xs-'+val).val('.col-xs-'+val);
					}
				});
				
				
				$( "#slider-range" ).css('height','200px').slider({
					orientation: "vertical",
					range: true,
					min: 0,
					max: 100,
					values: [ 17, 67 ],
					slide: function( event, ui ) {
						var val = ui.values[$(ui.handle).index()-1]+"";
			
						if(! ui.handle.firstChild ) {
							$(ui.handle).append("<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
						}
						$(ui.handle.firstChild).show().children().eq(1).text(val);
					}
				}).find('a').on('blur', function(){
					$(this.firstChild).hide();
				});
				
				$( "#slider-range-max" ).slider({
					range: "max",
					min: 1,
					max: 10,
					value: 2
				});
				
				$( "#eq > span" ).css({width:'90%', 'float':'left', margin:'15px'}).each(function() {
					// read initial values from markup and remove that
					var value = parseInt( $( this ).text(), 10 );
					$( this ).empty().slider({
						value: value,
						range: "min",
						animate: true
						
					});
				});
			
				
				$('#id-input-file-1 , #id-input-file-2').ace_file_input({
					no_file:'No File ...',
					btn_choose:'Choose',
					btn_change:'Change',
					droppable:false,
					onchange:null,
					thumbnail:false //| true | large
					//whitelist:'gif|png|jpg|jpeg'
					//blacklist:'exe|php'
					//onchange:''
					//
				});
				
				$('#id-input-file-3').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'icon-cloud-upload',
					droppable:true,
					thumbnail:'small'//large | fit
					//,icon_remove:null//set null, to hide remove/reset button
					/**,before_change:function(files, dropped) {
						//Check an example below
						//or examples/file-upload.html
						return true;
					}*/
					/**,before_remove : function() {
						return true;
					}*/
					,
					preview_error : function(filename, error_code) {
						//name of the file that failed
						//error_code values
						//1 = 'FILE_LOAD_FAILED',
						//2 = 'IMAGE_LOAD_FAILED',
						//3 = 'THUMBNAIL_FAILED'
						//alert(error_code);
					}
			
				}).on('change', function(){
					//console.log($(this).data('ace_input_files'));
					//console.log($(this).data('ace_input_method'));
				});
				
			
				//dynamically change allowed formats by changing before_change callback function
				$('#id-file-format').removeAttr('checked').on('change', function() {
					var before_change
					var btn_choose
					var no_icon
					if(this.checked) {
						btn_choose = "Drop images here or click to choose";
						no_icon = "icon-picture";
						before_change = function(files, dropped) {
							var allowed_files = [];
							for(var i = 0 ; i < files.length; i++) {
								var file = files[i];
								if(typeof file === "string") {
									//IE8 and browsers that don't support File Object
									if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
								}
								else {
									var type = $.trim(file.type);
									if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
											|| ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
										) continue;//not an image so don't keep this file
								}
								
								allowed_files.push(file);
							}
							if(allowed_files.length == 0) return false;
			
							return allowed_files;
						}
					}
					else {
						btn_choose = "Drop files here or click to choose";
						no_icon = "icon-cloud-upload";
						before_change = function(files, dropped) {
							return files;
						}
					}
					var file_input = $('#id-input-file-3');
					file_input.ace_file_input('update_settings', {'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
					file_input.ace_file_input('reset_input');
				});
			
			
			
			
				$('#spinner1').ace_spinner({value:0,min:0,max:200,step:10, btn_up_class:'btn-info' , btn_down_class:'btn-info'})
				.on('change', function(){
					//alert(this.value)
				});
				$('#spinner2').ace_spinner({value:0,min:0,max:10000,step:100, touch_spinner: true, icon_up:'icon-caret-up', icon_down:'icon-caret-down'});
				$('#spinner3').ace_spinner({value:0,min:-100,max:100,step:10, on_sides: true, icon_up:'icon-plus smaller-75', icon_down:'icon-minus smaller-75', btn_up_class:'btn-success' , btn_down_class:'btn-danger'});
			
			
				
				$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				$('input[name=date-range-picker]').daterangepicker().prev().on(ace.click_event, function(){
					$(this).next().focus();
				});
				
				$('#timepicker1').timepicker({
					minuteStep: 1,
					showSeconds: true,
					showMeridian: false
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
				
				$('#colorpicker1').colorpicker();
				$('#simple-colorpicker-1').ace_colorpicker();
			
				
				$(".knob").knob();
				
				
				//we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
				var tag_input = $('#form-field-tags');
				if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) ) 
				{
					tag_input.tag(
					  {
						placeholder:tag_input.attr('placeholder'),
						//enable typeahead by specifying the source array
						source: ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
					  }
					);
				}
				else {
					//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
					tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
					//$('#form-field-tags').autosize({append: "\n"});
				}
				
				
				
			
				/////////
				$('#modal-form input[type=file]').ace_file_input({
					style:'well',
					btn_choose:'Drop files here or click to choose',
					btn_change:null,
					no_icon:'icon-cloud-upload',
					droppable:true,
					thumbnail:'large'
				})
				
				
			
			});
		</script>
	</body>
	<script src="${ctx }/js/kindeditor/kindeditor.js"></script>
                <script type="text/javascript">
                    $(document).ready(function() {
                        KindEditor.create('textarea.kindeditor', {
                            basePath: '../js/kindeditor/',
                            allowFileManager : false,
                            bodyClass : 'article-content',
                            afterBlur : function() {
                				this.sync();
                			}
                        });
                    }); 
                </script>
</html>
