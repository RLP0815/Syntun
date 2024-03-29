<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>星图数据服务管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="logo.ico">
	<link rel="stylesheet" href="layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/public.css" media="all" />
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:set var="ctx" value="${pageContext.request.contextPath}"/>
</head>
<!-- <body class="loginBody" onload="document.loginForm.j_username.focus();"> -->
<body class="loginBody">
	<form class="layui-form" name="loginForm" action="/CrawlerSystem/tadmin/loginSignin.ht" method="post">
		<div class="login_face"><img src="images/logo.jpg" class="userAvatar"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input name='userName' type="text" placeholder="请输入用户名" autocomplete="off" id="userName" class="layui-input" lay-verify="required">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input name='password' type="password" placeholder="请输入密码" autocomplete="off" id="password" class="layui-input" lay-verify="required">
		</div>
		<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
		
		<div class="layui-form-item">
			<button name="submit" type="button" class="layui-btn layui-block" onclick="loginSignin()" lay-filter="login" lay-submit>登录</button>
		</div>
		<!-- 显示登录失败原因 -->  
	    <c:if test="${not empty param.error}">  
	        <font color="red"> 登录失败<br />  
	        <br />  
	        原因: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>  
	    </c:if> 
	</form>
 
<script type="text/javascript" src="layui/layui.js"></script>
<script type="text/javascript" src="js/cache.js"></script>
<script type="text/javascript" src="login.js"></script>
<script type="text/javascript">
	function loginSignin(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/tadmin/loginSignin.ht',
			  type: 'post',
			  data: {
				  "userName":userName,
				  "password":password
			  },
			  contentType:"application/x-www-form-urlencoded; charset=utf-8",
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  top.layer.msg("登录成功");
					  if(GetQueryString("flag")=='room'){
						  window.location.href = "/CrawlerSystem/page/syntun/meetingRoom/meetingRoom.jsp";
					  }else{
						  window.location.href = "/CrawlerSystem/index.jsp";
					  }
				  }else{
					top.layer.msg("账号或密码错误");
					//window.location.href = "/CrawlerSystem/login.jsp";
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
		})
	}
	function GetQueryString(name){
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
	     if(r!=null)return  unescape(r[2]); return null;
	}
</script>
</body>
</html>