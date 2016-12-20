// 搜索框js
//@author Ray
//修改
$(document).ready(function() {
	$("#bugSearch").click(function() {
		// alert("点击事件");
		var searchValue=$("#s").val();
		if(searchValue!=null||""!=searchValue)
		   window.location = "http://localhost:8080/nullpointer/findBugByPage?s=" + searchValue;
		else
			//alert("不存在");
		   window.location = "http://localhost:8080/nullpointer/bug/listadmin";
	});

	$("#questionSearch").click(function() {
		// alert("点击事件");
		var ss = $("#s").val();
		window.location = "http://localhost:8080/nullpointer/findQuestionByPage?s=" + ss;			
	})
	// 显示搜索内容
	$("#s").keydown(function(e) {
		if ( e.keyCode) {
			//alert("呀");
			var title = $("#s").val();
			// 3.获取到输入的内容之后，就要通过ajax传给后台
			$.post("http://localhost:8080/nullpointer/findBugAndQuestionByValue", {
				"title" : title
			}, function(data) {
				if (title == "") {
					$("#dtitles").hide();
				} else {
					// 显示展示div,把它清空
					$("#dtitles").show().html("");
					if (data == "") {
						$("#dtitles").hide();
					} else {
						$("#dtitles").append(data);
						// 4.鼠标移上去之后，加一个背景
						$("li").hover(function() {
							// alert("鼠标移上去");
							$(this).addClass("li1");
						}, function() {
							$(this).removeClass("li1");
						});
						// 点击后显示在框里
						$("li").click(function() {
							$("#s").val($(this).text());
							$("#dtitles").hide();
							if ($("#s").val() != "" || $("#s").val() != null) {
								$("#clear").show();
							}
						});
						
					}
				}
			});
		}
	}
	);

	// 搜索框js @author Ray
	// 修改 红叉
	// 1.页面加载之后，找到文本框的内容对它触发一个事件
	$("#s").keyup(function() {
		// 判断IE 浏览器 ，如果是IE的话，不显示。
		if (($("#s").val() != "" || $("#s").val() != null)&&(navigator.userAgent.indexOf('MSIE') >= 0) 
			    && (navigator.userAgent.indexOf('Opera') < 0)) {
			$("#clear").show();
		}
		if ($("#s").val() == "" || $("#s").val() == null) {
			$("#clear").hide();
			$("#dtitles").hide();
		}
	});
	$("#clear").click(function() {
		$("#s").val("");
		$("#clear").hide();
		$("#dtitles").hide();
	});
	
});