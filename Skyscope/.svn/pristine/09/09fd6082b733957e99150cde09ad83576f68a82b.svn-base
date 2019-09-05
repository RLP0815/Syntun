<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据补充</title>
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
<style>
    .layui-form-select dl { max-height:200px; }
</style>
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
<form class="layui-form" action="">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
		    <div class="layui-inline">
				<label class="layui-form-label">运行脚本</label>
				<div class="layui-input-block  layui-form" lay-filter="selFilter">
					<select class="runShell" lay-verify="required" lay-filter="runShell">
						<option value="">请选择</option>
					</select>
				</div>
		    </div>
		</blockquote>
		<table id="executeRunList" lay-filter="executeRunList"></table>
	</div>
	
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>执行操作说明</blockquote>
		<div class="border category" >
			<div class="" >
				<ul class="uullReplenish">
					
				</ul>
			</div>
		</div>
	</div>
</div> 
</form>

<div id="dialog1" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn1" lay-submit="" lay-filter="replenish_btn1">执行</button>
			</div>
		</div>
	</form>
</div>

<div id="dialog2" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="userId2" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">日期1</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date21" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">日期2</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date22" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn2" lay-submit="" lay-filter="replenish_btn2">执行</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<div id="dialog3" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="userId3" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">数据日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date3" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn3" lay-submit="" lay-filter="replenish_btn3">执行</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<div id="dialog4" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="userId4" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">平台</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="platform4" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">数据日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date4" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn4" lay-submit="" lay-filter="replenish_btn4">执行</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<div id="dialog5" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用户</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="userId5" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">平台</label>
			<div class="layui-input-block  layui-form" lay-filter="selFilter">
				<select class="platform5" lay-verify="required">
					<option value="">请选择</option>
					<option value="all">全选</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">补数日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date5" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">用数日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date51" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn4" lay-submit="" lay-filter="replenish_btn5">执行</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<div id="salesVolume" style="display:none;">
	<form class="layui-form" style="width:100%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">数据日期</label>
		    <div class="layui-input-inline">
		    	<input class="layui-input runTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
		    </div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
			    <button class="layui-btn replenish_btn6" lay-submit="" lay-filter="replenish_btn6">执行</button>
			    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<div id="dialog7" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">开始日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date71" lay-verify="date" placeholder="yyyy-MM-dd" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">结束日期</label>
	    	<div class="layui-input-block">
	    		<input class="layui-input date72" lay-verify="date" placeholder="yyyy-MM-dd" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn replenish_btn7" lay-submit="" lay-filter="replenish_btn7">执行</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="ExecuteRun.js"></script>
</body>
</html>