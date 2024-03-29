layui.use([ 'form', 'layer', 'jquery' ], function() {
	var form = layui.form, layer = parent.layer === undefined ? layui.layer
			: top.layer
	$ = layui.jquery;
	
	// 登录按钮
	form.on("submit(login)", function(data) {
		$(this).text("登录中...").attr("disabled", "disabled").addClass(
				"layui-disabled");
		
		var userName = $("#userName").val();
		var password = $("#password").val();
		
		$.ajax({
			type : 'POST',
			url : $("#ctx").attr("value") + '/tadmin/getLogin.ht',
			data : {
				"userName" : userName,
				"password" : password
			},
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data.state == 1){
					window.location.href = "/ProData/index.jsp";
				}else{
					alert("账号或密码错误");
					window.location.href = "/ProData/page/login/login.jsp";
					
				}
					
			}
		});
		return false;
	})
	// 表单输入效果
	$(".loginBody .input-item").click(function(e) {
		e.stopPropagation();
		$(this).addClass("layui-input-focus").find(".layui-input").focus();
	})
	$(".loginBody .layui-form-item .layui-input").focus(function() {
		$(this).parent().addClass("layui-input-focus");
	})
	$(".loginBody .layui-form-item .layui-input").blur(function() {
		$(this).parent().removeClass("layui-input-focus");
		if ($(this).val() != '') {
			$(this).parent().addClass("layui-input-active");
		} else {
			$(this).parent().removeClass("layui-input-active");
		}
	})
})
