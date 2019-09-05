<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>table_count_list</title>
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
</script>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form" lay-filter="selFilter">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				库名：
				<div class="layui-input-inline" style="width:150px;">
					<select class="searchValSelect" lay-filter="">
						<option value="">请选择库名</option>
					</select>
				</div>
				表名：
				<div class="layui-input-inline" style="width:150px;">
					<select class="searchVal" lay-filter="">
						<option value="">请选择表名</option>
					</select>
				</div>
				采集日期：
		    	<div class="layui-input-inline" style="width:100px;">
		    		<input class="layui-input getDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
		    	</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
				<a class="layui-btn email_btn" data-type="reload">发邮件</a>
				<a class="layui-btn execute_btn" data-type="reload">补执行</a>
			</div>	
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
	<!--操作-->
	<!-- <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> -->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
	
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>表格数据条数说明</blockquote>
		<div class="border category" >
			<div class="" >
				<ul class="uull">
					<!-- <li>字段替换  - 说明</li>
					<li>1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”</li>
					<li>2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因， 造成这个问题，所以将提示信息做了一个延时】</li>
					<li>3、“个人资料”提供修改功能</li>
					<li>4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】</li>
					<li>5、优化“用户列表”无法查询到新添加的用户【竟然是因为我把key值写错了，该死。。。】</li>
					<li>6、将左侧菜单做成json方式调用，而不是js调用，方便开发使用。同时添加了参数配置和非窗口模式打开的判断，【如登录页面】</li>
					<li>7、优化部分页面样式问题</li> -->
				</ul>
			</div>
		</div>
	</div>
</div> 
</form>
<div id="addFiled" style="display:none;">
	<form class="layui-form" style="width:95%;">
		<input type="text" style="display:none" class="filedId">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">数据库</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input database" lay-verify="required" placeholder="请输入数据库名">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">表名</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input tableName" lay-verify="required" placeholder="请输入表名">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">唯一键</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input uniqueKey" lay-verify="required" placeholder="请输入唯一键组合（用逗号“,”隔开）">
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

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="TableCountList.js"></script>
</body>
</html>