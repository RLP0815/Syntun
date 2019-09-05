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

</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">

<div style="margin-left: 100px;margin-top: 30px;">
	<ul id="tree" class="ztree"></ul>
</div>

<div id="treeaddFiled" style="display:none;">
<form class="layui-form" style="width:90%;">
	<input type="text" style="display:none" class="filedId">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">名称</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="treeName" lay-verify="required" placeholder="请输入名称">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">类型</label>
		<div class="layui-input-inline">
			<select id="treeType" class="searchVal" lay-filter="">
			    <option value="menu1">一级菜单</option>
				<option value="menu2">二级菜单</option>
				<option value="permission">按钮</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">URL</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="treeUrl" lay-verify="required" placeholder="请输入URL">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">图片地址</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="treeImage" lay-verify="required" placeholder="请输入图片地址">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">菜单排序</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="treeSort" lay-verify="required" placeholder="请输入数字">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">所属项目</label>
		<div class="layui-input-inline">
			<select id="treeProject" class="searchVal" lay-filter="">
			    <option value="ProData">ProData</option>
			    <option value="ProData1">ProData1</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" id="treeAdd">保存</button>
		</div>
	</div>
	</form>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<!-- 树依赖js -->
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../../js/jqtree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="powerManage.js"></script>
</body>
</html>