<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理</title>
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
<style type="text/css">
	
.role-button { /* 按钮美化 */
	width: 150px; /* 宽度 */
	height: 40px; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	border-radius: 3px; /* 边框半径 */
	background: #1E90FF; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: white; /* 字体颜色 */
	font-size: 17px; /* 字体大小 */
	margin-left: 7px;
}
.role-button:hover { /* 鼠标移入按钮范围时改变颜色 */
	background: #5599FF;
}
</style>

</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					角色名称：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输入角色名称" />
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
		<a class="layui-btn layui-btn-xs" lay-event="query">权限</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</div> 
</form>
<div id="addFiled" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<input type="text" style="display:none" class="filedId">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">角色ID</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input cateId" lay-verify="required" placeholder="请输入角色ID">
		<!-- 不能输入双字节字符设置
	onpaste="return false" ondragenter="return false"  onkeyup="this.value=this.value.replace(/[^\x00-\x80]/gi,'')"
		-->
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">角色描述</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input cateName" lay-verify="required" placeholder="请输入角色描述">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">角色分类</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input account" lay-verify="required" placeholder="请输入角色分类">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm addFlied" lay-submit="" lay-filter="addFlied">立即添加</button>
				<button class="layui-btn layui-btn-sm editFlied" lay-submit="" lay-filter="editFlied">立即修改</button>
				<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
			</div>
		</div>
		
	</form>
</div>


<div style="margin-left: 100px;margin-top: 30px;">
<!--	<input type="button" value="角色id绑定的权限" onclick="test()"/>
 	<input type="button" value="用户id查菜单" onclick="test1()"/>
	<input type="button" value="用户id查按钮" onclick="test2()"/> -->
	<input type="button" class="role-button" value="保存绑定权限" onclick="test3()"/>
	<ul id="treeDemo" class="ztree"></ul>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>

<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="rolePowerManage.js"></script>
</body>
</html>