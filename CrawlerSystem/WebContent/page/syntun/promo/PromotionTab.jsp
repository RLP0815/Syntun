<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销操作</title>
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
	<input type="text" style="display:none" class="cxId">
	<input type="text" style="display:none" class="cxbzId">
	<input type="text" style="display:none" class="cxptId">
	<div class="layui-tab layui-tab-brief" lay-filter="filter">
		<ul class="layui-tab-title">
			<li class="layui-this">促销维护</li>
			<li>促销标准</li>
			<li>平台标准</li>
  		</ul>
  		<div class="layui-tab-content">
		  	<div class="layui-tab-item layui-show">
				<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  促销名称：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchVal" placeholder="请输入促销名称" />
								</div>
								<a class="layui-btn search_btn" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn" data-type="reload">批量删除</a>
								</security:authorize>
					 		</div>
				 		</div>
					</blockquote>
					<table id="promotionList" lay-filter="promotionList"></table>
				</div>
				<script type="text/html" id="promotionListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>促销维护说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullcompare">
								<!-- <li># v1.0.1（优化） - 2017-06-25</li>
								<li>1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”</li>
								<li>2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】</li>
								<li>3、“个人资料”提供修改功能</li>
								<li>4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】</li>
								<li>5、优化“用户列表”无法查询到新添加的用户【竟然是因为我把key值写错了，该死。。。】</li>
								<li>6、将左侧菜单做成json方式调用，而不是js调用，方便开发使用。同时添加了参数配置和非窗口模式打开的判断，【如登录页面】</li>
								<li>7、优化部分页面样式问题</li> -->
							</ul>
						</div>
					</div>
				</div>
				
				
				<div id="promotionTab" style="display:none;">
					<form class="layui-form" style="width:100%;">
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">平台ID</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input platformId" lay-verify="required" placeholder="请选择平台">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">平台名称</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input platformName" lay-verify="required" placeholder="请选择平台">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<div class="layui-input-block">
									<a class="layui-btn layui-btn-normal selectPlatform">平台选择</a>
								</div>
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">促销名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input promotionTypeName" lay-verify="required" placeholder="请输入促销名称">
							</div>
						</div>
						
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">促销标准ID</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input promotionId" lay-verify="required" placeholder="请选择促销标准">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">促销标准</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input promotionStandard" lay-verify="required" placeholder="请选择促销标准">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<div class="layui-input-block">
									<!-- 
									<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addFlied">促销标准选择</button>
									 -->
									<a class="layui-btn layui-btn-normal selectPromotion">促销标准选择</a>
								</div>
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addFiled" lay-submit="" lay-filter="addFiled">立即添加</button>
								<button class="layui-btn layui-btn-sm editFiled" lay-submit="" lay-filter="editFiled">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
				
				<div id="promotion" style="display:none;">
					<table id="promotionListd" lay-filter="promotionListd"></table>
					<script type="text/html" id="promotionListdBar">
						<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
					</script>
				</div>
				<div id="platform" style="display:none;">
					<table id="platformListd" lay-filter="platformListd"></table>
					<script type="text/html" id="platformListdBar">
						<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
					</script>
				</div>
				
			</div>
		    <div class="layui-tab-item">
		    	<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="layui-inline">
							<div class="demoTable">
						  		促销标准：
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchValPro" placeholder="请输入促销标准名称" />
								</div>
								<a class="layui-btn search_btn_pro" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn_pro" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn_pro" data-type="reload">批量删除</a>
								</security:authorize>
							</div>
				 		</div>
					</blockquote>
					<table id="promotionAllList" lay-filter="promotionAllList"></table>
				</div>
				<script type="text/html" id="promotionAllListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>促销标准说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullpromotion">
								<!-- <li># v1.0.1（优化） - 2017-06-25</li>
								<li>1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”</li>
								<li>2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】</li>
								<li>3、“个人资料”提供修改功能</li>
								<li>4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】</li>
								<li>5、优化“用户列表”无法查询到新添加的用户【竟然是因为我把key值写错了，该死。。。】</li>
								<li>6、将左侧菜单做成json方式调用，而不是js调用，方便开发使用。同时添加了参数配置和非窗口模式打开的判断，【如登录页面】</li>
								<li>7、优化部分页面样式问题</li> -->
							</ul>
						</div>
					</div>
				</div>
		    
		    	<div id="promotionAllTab" style="display:none;">
		    		<form class="layui-form" style="width:90%;">
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">促销标准ID</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input promotionAllId" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入促销标准ID(只能添加数字)">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">促销名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input promotionColName" lay-verify="required" placeholder="请输入促销标准名称">
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addRecord" lay-submit="" lay-filter="addRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editRecord" lay-submit="" lay-filter="editRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
		    	</div>
		    </div>
		    <div class="layui-tab-item">
		    	<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="layui-inline">
							<div class="demoTable">
						  		平台名称：
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchValPla" placeholder="请输入平台名称" />
								</div>
								<a class="layui-btn search_btn_pla" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn_pla" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn_pla" data-type="reload">批量删除</a>
								</security:authorize>
							</div>
				 		</div>
					</blockquote>
					<table id="platformList" lay-filter="platformList"></table>
				</div>
				<script type="text/html" id="platformListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>平台维护说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullplatform">
								<!-- <li># v1.0.1（优化） - 2017-06-25</li>
								<li>1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”</li>
								<li>2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】</li>
								<li>3、“个人资料”提供修改功能</li>
								<li>4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】</li>
								<li>5、优化“用户列表”无法查询到新添加的用户【竟然是因为我把key值写错了，该死。。。】</li>
								<li>6、将左侧菜单做成json方式调用，而不是js调用，方便开发使用。同时添加了参数配置和非窗口模式打开的判断，【如登录页面】</li>
								<li>7、优化部分页面样式问题</li> -->
							</ul>
						</div>
					</div>
				</div>
		    	
		    	<div id="platformTab" style="display:none;">
		    		<form class="layui-form" style="width:90%;">
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">平台ID</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input platformIdpt" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入平台ID(只能添加数字)">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">平台名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input platformNamept" lay-verify="required" placeholder="请输入平台名称">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">数据库名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input platformTableName" lay-verify="required" placeholder="请输入数据库名称">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addPlatRecord" lay-submit="" lay-filter="addPlatRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editPlatRecord" lay-submit="" lay-filter="editPlatRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
		    	</div>
		    </div>
		</div>
		
	</div> 

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="PromotionTab.js"></script>
</body>
</html>
