<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销操作</title>
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
	<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<input type="text" style="display:none" class="stId">
	
	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="demoTable">
			  下架描述：
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输入下架描述" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
					<security:authorize ifAnyGranted="ROLE_DEL,ROLE_ADMIN">
					<a class="layui-btn layui-btn-danger delAll_btn" data-type="reload">批量删除</a>
					</security:authorize>
		 		</div>
	 		</div>
		</blockquote>
		<table id="productState" lay-filter="productState"></table>
	</div>
	<script type="text/html" id="productStateBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>

	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>产品状态说明</blockquote>
		<div class="border category">
			<div class="">
				<ul class="uullstate">
					
				</ul>
			</div>
		</div>
	</div>
	
	
	<div id="stateTab" style="display:none;">
		<form class="layui-form" style="width:95%;">
			<div class="layui-form-item layui-row layui-col-xs12">
				<input type="text" style="display:none" class="productState">
				<label class="layui-form-label">产品状态</label>
				<div class="layui-input-block  layui-form" lay-filter="selFilter">
					<select class="stateCode" lay-verify="required">
						<option value="">请选择</option>
						<option value="1">下架</option>
						<option value="2">无货</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item layui-row layui-col-xs12">
				<label class="layui-form-label">下架描述</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input errorConnect" lay-verify="required" placeholder="请输入下架描述">
				</div>
			</div>
			<div class="layui-row">
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<label class="layui-form-label">平台ID</label>
					<div class="layui-input-block">
						<input type="text" disabled="disabled" class="layui-input platformId" placeholder="请选择平台">
					</div>
				</div>
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<label class="layui-form-label">平台名称</label>
					<div class="layui-input-block">
						<input type="text" disabled="disabled" class="layui-input platformName" placeholder="请选择平台">
					</div>
				</div>
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<div class="layui-input-block">
						<a class="layui-btn layui-btn-normal selectPlatform">平台选择</a>
					</div>
				</div>
			</div>
			
			<div class="layui-form-item layui-row layui-col-xs12">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-sm addFiled" lay-submit="" lay-filter="addFiled">立即添加</button>
					<button class="layui-btn layui-btn-sm editFiled" lay-submit="" lay-filter="editFiled">立即修改</button>
					<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
				</div>
			</div>
		</form>
	</div>
	<div id="platform" style="display:none;">
		<table id="platformList" lay-filter="platformList"></table>
		<script type="text/html" id="platformListBar">
			<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
		</script>
	</div>
			

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="ProductState.js"></script>
</body>
</html>
