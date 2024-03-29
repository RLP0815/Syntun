<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>采集配置系统-登录</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="../../logo.ico">
	<link rel="stylesheet" href="../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../css/public.css" media="all" />
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
</head>
<body class="loginBody">
	<form class="layui-form"  >
		<div class="login_face"><img src="../../images/logo.jpg" class="userAvatar"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" id="userName" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" id="password" class="layui-input" lay-verify="required">
		</div>
		<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
		
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
		</div>
	</form>

<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="../../js/cache.js"></script>
<script type="text/javascript" src="login.js"></script>
</body>
</html>