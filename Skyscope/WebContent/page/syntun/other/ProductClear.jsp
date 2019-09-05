<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字段替换</title>
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
<form class="layui-form" action="">
<div class="layui-tab layui-tab-brief" lay-filter="filter">
	<ul class="layui-tab-title">
		<li class="layui-this" lay-id="00">匹配结果</li>
		<li lay-id="11">未匹配结果</li>
		<li lay-id="22">匹配规则</li>
 	</ul>
 	<div class="layui-tab-content">
	  	<div class="layui-tab-item layui-show">
			<blockquote class="layui-elem-quote quoteBox">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<label class="layui-form-label">平台</label>
						<div class="layui-input-block  layui-form" lay-filter="selFilter">
							<select name="" class="platformId" lay-filter="">
								<option value="">请选择平台</option>
								<option value="45">唯品会</option>
								<option value="49">网易考拉</option>
							</select>
						</div>
					</div>
					获取日期
					<div class="layui-input-inline">
						<input class="layui-input getDate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
					<a class="layui-btn layui-btn-normal product_btn" data-type="reload">添加数据</a>
					<a class="layui-btn layui-btn-warm add_btn" data-type="reload">执行入库</a>
						
				</div>	
			</blockquote>
			<table id="filedList" lay-filter="filedList"></table>
			<!--操作-->
			<script type="text/html" id="newsListBar">
				<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			</script>
		</div>
	    <div class="layui-tab-item">
	    	<blockquote class="layui-elem-quote quoteBox">
				<div class="layui-inline">
					<div class="layui-input-inline">
						<label class="layui-form-label">平台</label>
						<div class="layui-input-block  layui-form" lay-filter="selFilter">
							<select name="" class="platformIdCopy" lay-filter="selFilter">
								<option value="">请选择平台</option>
								<option value="45">唯品会</option>
								<option value="49">网易考拉</option>
							</select>
						</div>
					</div>
					获取日期：
					<div class="layui-input-inline">
						<input class="layui-input getDateCopy" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" type="text">
					</div>
					<a class="layui-btn search_btn_c" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn_c" data-type="reload">批量删除</a>
				</div>	
			</blockquote>
			<table id="productList" lay-filter="productList"></table>
			<!--操作-->
			<script type="text/html" id="productListBar">
				<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			</script>
	    </div>
	    <div class="layui-tab-item">
	    	<blockquote class="layui-elem-quote quoteBox">
				<div class="layui-inline">
					产品名称：
					<div class="layui-input-inline">
						<input type="text" class="layui-input productNameS" placeholder="请输入产品名称" />
					</div>
					<a class="layui-btn search_btn_f" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn_f" data-type="reload">增加</a>
					<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn_f" data-type="reload">批量删除</a>
				</div>	
			</blockquote>
			<table id="filterList" lay-filter="filterList"></table>
			<!--操作-->
			<script type="text/html" id="filterListBar">
				<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
				<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			</script>
			
			<div id="addFiled" style="display:none;">
				<form class="layui-form" style="width:90%;">
					<input type="text" style="display:none" class="filedId">
					<div class="layui-form-item layui-row layui-col-xs12">
						<label class="layui-form-label">产品ID</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input productId" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入产品ID(只能是数字)">
						</div>
					</div>
					<div class="layui-form-item layui-row layui-col-xs12">
						<label class="layui-form-label">产品名称</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input productName" lay-verify="required" placeholder="请输入产品名称">
						</div>
					</div>
					<div class="layui-form-item layui-row layui-col-xs12">
						<label class="layui-form-label">条件一</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input filter1" placeholder="请输入条件1">
						</div>
					</div>
					<div class="layui-form-item layui-row layui-col-xs12">
						<label class="layui-form-label">条件二</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input filter21" placeholder="请输入条件2">
							或
							<input type="text" class="layui-input filter22" placeholder="请输入条件2">
							或
							<input type="text" class="layui-input filter23" placeholder="请输入条件2">
						</div>
					</div>
					<div class="layui-form-item layui-row layui-col-xs12">
						<label class="layui-form-label">条件三</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input filter3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入条件3(只能是数字)">
						</div>
					</div>
					
					<div class="layui-form-item layui-row layui-col-xs12">
						<div class="layui-input-block">
							<button class="layui-btn layui-btn-sm addFlied" lay-submit="" lay-filter="addFlied">立即添加</button>
							<button class="layui-btn layui-btn-sm editFlied" lay-submit="" lay-filter="editFlied">立即修改</button>
						</div>
					</div>
				</form>
			</div>
			
	    </div>
	</div>
</div> 
</form>


<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="ProductClear.js"></script>
</body>
</html>