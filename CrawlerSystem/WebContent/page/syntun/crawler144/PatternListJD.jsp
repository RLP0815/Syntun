<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>purl_list</title>
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
<form class="layui-form">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md9 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					url_str：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输入url_str" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
					<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
					<%-- <security:authorize ifAnyGranted="ROLE_DEL">   --%>
						<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
					<%-- </security:authorize> --%>
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
	
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>purl_list说明</blockquote>
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
	<form class="layui-form" style="width:90%;">
		<input type="text" style="display:none" class="filedId">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">pattern_group</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input patternGroup" lay-verify="required|number" placeholder="请输入pattern_group(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">url_group</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input urlGroup" lay-verify="required|number" placeholder="请输入url_group(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">pattern_type</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input patternType" lay-verify="required|number" placeholder="请输入pattern_type(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">pattern_content</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input patternContent" lay-verify="required" placeholder="请输入pattern_content">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">pattern_index</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input patternIndex" lay-verify="required" placeholder="请输入pattern_index">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">parent_pattern_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input parentPatternId" lay-verify="required|number" placeholder="请输入parent_pattern_id(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">about_pattern_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input aboutPatternId" lay-verify="required" placeholder="请输入about_pattern_id">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">purl_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input purlId" lay-verify="required" placeholder="请输入purl_id">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">sort_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input sortId" lay-verify="required" placeholder="请输入sort_id">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">generate_sort_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input generateSortId" lay-verify="required" placeholder="请输入generate_sort_id">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">col_name</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input colName" lay-verify="required" placeholder="请输入col_name">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">table_id</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input tableId" lay-verify="required|number" placeholder="请输入table_id(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">is_save_page_data</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input isSavePageData" lay-verify="required|number" placeholder="请输入is_save_page_data(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">is_get_url</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input isGetUrl" lay-verify="required|number" placeholder="请输入is_get_url(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">is_data_parallel</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input isDataParallel" lay-verify="required|number" placeholder="请输入is_data_parallel(只能是数字)">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12 writeTime1">
			<label class="layui-form-label">write_time</label>
			<div class="layui-input-block">
	    		<input class="layui-input writeTime" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
	    	</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">remark</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input remark" lay-verify="required" placeholder="请输入备注">
			</div>
		</div>
		
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm addFlied" lay-submit="" lay-filter="addFlied">立即添加</button>
				<button class="layui-btn layui-btn-sm editFlied" lay-submit="" lay-filter="editFlied">立即修改</button>
				<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
			</div>
		</div>
		<!-- 文件导入 -->
		<div class="layui-upload">
			<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
			  <legend>文件导入</legend>
			</fieldset>
		  <button type="button" class="layui-btn layui-btn-sm layui-btn-normal" id="test8">选择文件</button>
		  <button type="button" class="layui-btn layui-btn-sm" id="test9">开始上传</button>
		</div>
		
	</form>
</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="PatternListJD.js"></script>
</body>
</html>