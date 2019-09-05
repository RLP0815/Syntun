<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
<link rel="stylesheet" href="../../../js/jqtree/css/zTreeStyle/zTreeStyle.css" type="text/css">

</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					用户登录名：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输入用户登录名" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
					<security:authorize ifAnyGranted="ROLE_DEL">  
						<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
					</security:authorize>
				</div>
			</div>	
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</div> 
</form>
<div id="addFiled" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<input type="text" style="display:none" class="filedId">
		<input type="hidden" id="userid" value=''/>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户登录名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input cateId" lay-verify="required" placeholder="请输入用户名">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户密码</label>
			<div class="layui-input-block">
				<input type="password" class="layui-input passwd" lay-verify="required" placeholder="请输入用户密码">
			</div>
		</div>
<!-- 		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input user_id" lay-verify="required" placeholder="请输入用户ID">
			</div>
		</div> -->
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input user_name" lay-verify="required" placeholder="请输入姓名">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input cateName" id='email' lay-verify="required" placeholder="请输入邮箱">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">电话号码</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input account" lay-verify="required" placeholder="请输入电话号码">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">角色</label>
			<div class="layui-input-inline">
				<select class="searchVal" lay-filter="role" id="role">
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm addFlied" lay-submit="" lay-filter="addFlied">立即添加</button>
				<button class="layui-btn layui-btn-sm editFlied" lay-submit="" lay-filter="editFlied">立即修改</button>
				<button class="layui-btn layui-btn-sm resetPassword" lay-submit="" lay-filter="resetPassword">重置密码</button>
			</div>
		</div>
		
	</form>
</div>


<div style="margin-left: 100px;margin-top: 30px;">
<!--	<input type="button" value="角色id绑定的权限" onclick="test()"/>
 	<input type="button" value="用户id查菜单" onclick="test1()"/>
	<input type="button" value="用户id查按钮" onclick="test2()"/> -->
	<ul id="treeDemo" class="ztree"></ul>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>

<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="userManage.js"></script>
</body>
</html>