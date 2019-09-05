<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全球购用户相关</title>
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
	var authorities = '<%=authorities%>';
</script>
<body>
	<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<input type="text" style="display:none" class="userIdU">
   	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="demoTable">
			  用户名称：
				<div class="layui-inline">
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchValRep" placeholder="请输入用户名称" />
					</div>
					<a class="layui-btn search_btn_rep" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn_rep" data-type="reload">增加</a>
					<security:authorize ifAnyGranted="ROLE_DEL,ROLE_ADMIN">
					<a class="layui-btn layui-btn-danger delAll_btn_rep" data-type="reload">批量删除</a>
					</security:authorize>
		 		</div>
	 		</div>
		</blockquote>
		<table id="skyUsers" lay-filter="skyUsers"></table>
	</div>
	<script type="text/html" id="skyUsersBar">
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>

	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>用户维护说明</blockquote>
		<div class="border category">
			<div class="">
				<ul class="hkusers">
					
				</ul>
			</div>
		</div>
	</div>
	
	<div id="skyUsersTab" style="display:none;">
		<form class="layui-form" style="width:90%;">
			<div class="layui-form-item layui-row layui-col-xs12">
				
			</div>
			<div class="layui-form-item layui-row layui-col-xs12">
				
			</div>
			<div class="layui-row">
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<label class="layui-form-label">用户ID</label>
					<div class="layui-input-block">
						<input type="text" disabled="disabled" class="layui-input userId" lay-verify="required" placeholder="选择用户">
					</div>
				</div>
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<label class="layui-form-label">用户名称</label>
					<div class="layui-input-block">
						<input type="text" disabled="disabled" class="layui-input userName" lay-verify="required" placeholder="请选择用户">
					</div>
				</div>
				<div class="magb15 layui-col-md4 layui-col-xs12">
					<div class="layui-input-block">
						<a class="layui-btn layui-btn-normal selectGroup">用户选择</a>
					</div>
				</div>
			</div>
			<div class="layui-form-item layui-row layui-col-xs12">
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-sm addRepRecord" lay-submit="" lay-filter="addRepRecord">立即添加</button>
				</div>
			</div>
		</form>
	</div>
	<div id="groups" style="display:none;">
		<table id="groupsList" lay-filter="groupsList"></table>
		<script type="text/html" id="groupsListBar">
			<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
		</script>
	</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="hkUserTab.js"></script>
</body>
</html>