<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行操作</title>
<%@include file="/page/include/getUserInfo.jsp"%>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />

</head>

<script type="text/javascript">
	var nickName = '<%=nickName%>';
	var userId = '<%=userId%>';
	var userName = '<%=userName%>';
	var email = '<%=email%>';
	var phoneno = '<%=phoneno%>';
</script>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md9 layui-col-xs12">
		<form class="layui-form" action="">
		  <div class="layui-form-item">
		   <div class="layui-inline">
		      <label class="layui-form-label">开始日期</label>
		      <div class="layui-input-inline">
		        <input class="layui-input startTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">结束日期</label>
		      <div class="layui-input-inline">
		        <input class="layui-input endTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">平台ID</label>
		      <div class="layui-input-inline">
		        <input class="layui-input platformIds" lay-verify="platform" autocomplete="off" type="text" placeholder="多平台用逗号分隔">
		      </div>
		    </div>
		    <!-- 
		    <div class="layui-inline">
		      <label class="layui-form-label">收件邮箱</label>
		      <div class="layui-input-inline">
		        <input class="layui-input email" lay-verify="email" autocomplete="off" type="text" placeholder="请输入邮箱">
		      </div>
		    </div>
		     -->
		    <div class="layui-inline">
		      <div class="layui-input-block">
			    <button class="layui-btn execute_btn" lay-submit="" lay-filter="execute_btn">执行</button>
			    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			  </div>
		    </div>
		  </div>
		</form>
		<table id="etlExecuteList" lay-filter="etlExecuteList"></table>
	</div>
	<!--操作-->
	<!-- <script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
	 -->
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>执行操作说明</blockquote>
		<div class="border category" >
			<div class="" >
				<ul class="uullExecute">
					
				</ul>
			</div>
		</div>
	</div>
</div> 

<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="EtlExecute.js"></script>
</body>
</html>