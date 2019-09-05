<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>同步数据</title>
	<%@include file="/page/include/getUserInfo.jsp"%>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../../css/public.css" media="all" />
	<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
</head>
<script type="text/javascript">
	var nickName = '<%=nickName%>';
	var userId = '<%=userId%>';
	var userName = '<%=userName%>';
	var email = '<%=email%>';
	var phoneno = '<%=phoneno%>';
<%-- 	var authorities = '<%=authorities%>'; --%>
</script>

<body>
	<input id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		<legend>参数选择</legend>
	</fieldset>
	<form class="layui-form" style="width:99%;">
		<div class="layui-form-item">
			<div class="layui-col-md6">
			    <label class="layui-form-label">表格</label>
			    <div class="layui-input-block">
					<select class="checkTable" lay-filter="checkTable" id="checkTable">
						<option value=''>请选择</option>
						<option value='all'>全部</option>
						<option value='syntun_v2.BRAND_LIST'>BRAND_LIST</option>
						<option value='syntun_v2.CATEGORY_LIST'>CATEGORY_LIST</option>
						<option value='syntun_v2.PLATFORM_LIST'>PLATFORM_LIST</option>
						<option value='syntun_v2.PRODUCT_LIST'>PRODUCT_LIST</option>
						<option value='syntun_v2.PRODUCT_PLATFORM_LIST'>PRODUCT_PLATFORM_LIST</option>
						<option value='syntun_v2.SHOP_LIST'>SHOP_LIST</option>
						<option value='syntun_v2.SHOP_LIST_YILI'>SHOP_LIST_YILI</option>
					</select>
			    </div>
			</div>
			<div class="layui-col-md6">
			    <div class="layui-input-block">
					 <button class="layui-btn execute_btn" lay-submit="" lay-filter="execute_btn">执行</button>
			    </div>
			</div>
		</div>
	</form>
	
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript">
layui.use(['form','layer','table','laytpl'],function(){

	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laytpl = layui.laytpl;
	
	 //执行按钮
    form.on("submit(execute_btn)",function(data){
    	if($(".checkTable").val() == ""){
    		top.layer.msg("请先选择表格！");
    		return;
    	}
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		$.post($("#ctx").attr("value") + '/editRemark/execute.ht',{
			tableName : $(".checkTable").val(),
			email : email,
		},function(res){
			//console.log(res[10]);
			$(".checkTable").val("");
			form.render();
			top.layer.msg("数据同步，请注意邮件提醒！");
		})
    	return false;
	})  
})

</script>
</body>
</html>