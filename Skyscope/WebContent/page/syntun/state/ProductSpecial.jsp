<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>特殊处理产品</title>
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
<body>
<input id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					uid：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输用户id" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
					<%-- <security:authorize ifAnyGranted="ROLE_DEL">   --%>
						<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
					<%-- </security:authorize> --%>
				</div>
			</div>	
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<!-- <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> -->
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
	<!-- 
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>data_table_list说明</blockquote>
		<div class="border category" >
			<div class="" >
				<ul class="uull">
				</ul>
			</div>
		</div>
	</div> -->
</div> 
</form>
<div id="addFiled" style="display:none;">
	<form class="layui-form" style="width:100%;">
		<!-- 文件导入 -->
		<div class="layui-upload">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			  <legend>文件导入</legend>
			</fieldset>
		  <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="test8">选择文件</button>
		  <button type="button" class="layui-btn layui-btn-sm" id="test9">开始上传</button>
		</div>
	</form>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="ProductSpecial.js"></script>
</body>
</html>