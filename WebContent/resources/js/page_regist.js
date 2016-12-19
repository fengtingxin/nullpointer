$(document).ready(function(){
	
	//获取JS传递的语言参数
	var utils = new Utils();
	var args = utils.getScriptArgs();	
	
	
	//隐藏Loading/注册失败 DIV
	$(".loading").hide();
	$(".login-error").hide();
	registError = $("<label class='error repeated'></label>");
	
	//加载国际化语言包资源
//	utils.loadProperties(args.lang);
	
	//输入框激活焦点、移除焦点
	jQuery.focusblur = function(focusid) {
		var focusblurid = $(focusid);
		var defval = focusblurid.val();
		focusblurid.focus(function(){
			var thisval = $(this).val();
			if(thisval==defval){
				$(this).val("");
			}
		});
		focusblurid.blur(function(){
			var thisval = $(this).val();
			if(thisval==""){
				$(this).val(defval);
			}
		});
	 
	};
	/*下面是调用方法*/
	$.focusblur("#email");
	$.focusblur("#name");
	$.focusblur("#password");
	//获取表单验证对象[填写验证规则]
	var validate = $("#signupForm").validate({
		rules: {
			name:{
				required :true
			},
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 4,
				maxlength: 16
			},
			passwordAgain: {
				required: true,
				minlength: 4,
				maxlength: 16,
				equalTo: "#password"
			}
		},
		messages: {
			name:{
				required: $.i18n.prop("请输入name"),
			},
			email: {
				required: $.i18n.prop("请输入email"),
				email: $.i18n.prop("请输入正确的email")
			},
			password: {
				required: $.i18n.prop("请输入密码"),
				minlength: jQuery.format($.i18n.prop("您的密码过短")),
				maxlength: jQuery.format($.i18n.prop("您的密码过长"))
			},
			passwordAgain: {
				required: $.i18n.prop("请再次输入密码"),
				minlength: jQuery.format($.i18n.prop("您的密码过短")),
				maxlength: jQuery.format($.i18n.prop("您的密码过长")),
				equalTo: jQuery.format($.i18n.prop("两次的密码不同"))
			}
		}
	});
	
	
	//输入框激活焦点、溢出焦点的渐变特效
	if($("#name").val()){
		$("#name").prev().fadeOut();
	};
	$("#name").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#name").blur(function(){
		if(!$("#name").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#email").val()){
		$("#email").prev().fadeOut();
	};
	$("#email").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#email").blur(function(){
		if(!$("#email").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#password").val()){
		$("#password").prev().fadeOut();
	};
	$("#password").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#password").blur(function(){
		if(!$("#password").val()){
			$(this).prev().fadeIn();
		};		
	});
	if($("#passwordAgain").val()){
		$("#passwordAgain").prev().fadeOut();
	};
	$("#passwordAgain").focus(function(){
		$(this).prev().fadeOut();
	});
	$("#passwordAgain").blur(function(){
		if(!$("#passwordAgain").val()){
			$(this).prev().fadeIn();
		};		
	});

	//ajax提交注册信息
	$("#submit").bind("click", function(){
		regist(validate);
	});
//	
	$("body").each(function(){
		$(this).keydown(function(){
			if(event.keyCode == 13){
				regist(validate);
			}
		});
	});
	
});

function regist(validate){
	console.log("this is regist");
	//校验name,realName,phone,Email, password，校验如果失败的话不提交
	if(validate.form()){
			var md5 = new MD5();
			$.post({
				url : "loginUser/register",
				data: {
					loginName: $("#name").val(),
					password: md5.MD5($("#password").val()),
					email: $("#email").val(),
				},
				beforeSend: function(){
					$('.loading').show();
				},
				success: function(data,status){
//					console.log("data");
					$('.loading').hide();
					if(data){
						if(data == "0"){
							//注册成功
							window.location.href="registerSure.jsp"
						}else if(data == "1"){
							//数据库链接失败
							$(".login-error").html($.i18n.prop("服务器有问题，请刷新注册"));
						}else if(data == "2"){
							//参数传递失败
							$(".login-error").show();
							$(".login-error").html($.i18n.prop("您的网络有问题"));
						}else if(data == "3"){
							//名字已经被注册
							$("#name").addClass("error");
							$("#name").after(registError);
							$("#name").next("label.repeated").text($.i18n.prop("您的用户名已经存在"));
							registError.show();
						}else if(data == "4"){
							//邮箱已经被注册
							$("#email").addClass("error");
							$("#email").after(registError);
							$("#email").next("label.repeated").text($.i18n.prop("邮箱已经存在"));
							registError.show();
						}else if(data == "5"){
							//邮箱不符合格式
							$("#email").addClass("error");
							$("#email").after(registError);
							$("#email").next("label.repeated").text($.i18n.prop("邮箱不符合格式！"));
							registError.show();
						}else{
							//系统错误
							$(".login-error").html($.i18n.prop("系统错误"));
						}
					}
				},
				error: function (){
					$(".loading").hide();
					$(".login-error").hide();
					$(".login-error").html($.i18n.prop("您的网络有问题，请刷新试试"));
				}
			});
	}
}

var Utils = function(){};


Utils.prototype.getScriptArgs = function(){//获取多个参数
    var scripts=document.getElementsByTagName("script"),
    //因为当前dom加载时后面的script标签还未加载，所以最后一个就是当前的script
    script=scripts[scripts.length-1],
    src=script.src,
    reg=/(?:\?|&)(.*?)=(.*?)(?=&|$)/g,
    temp,res={};
    while((temp=reg.exec(src))!=null) res[temp[1]]=decodeURIComponent(temp[2]);
    return res;
};
