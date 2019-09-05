<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>价格相关</title>
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
	<input type="text" style="display:none" class="zzId">
	<input type="text" style="display:none" class="thId">
	<input type="text" style="display:none" class="jgId">
	<div class="layui-tab layui-tab-brief" lay-filter="filter">
		<ul class="layui-tab-title">
			<li class="layui-this" lay-id="00">满减正则匹配</li>
			<li lay-id="11">满减字符替换</li>
			<li lay-id="22">非满减价格计算</li>
  		</ul>
  		<div class="layui-tab-content">
		  	<div class="layui-tab-item layui-show">
				<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  正则内容：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchVal" placeholder="请输入正则内容" />
								</div>
								<a class="layui-btn search_btn" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn" data-type="reload">批量删除</a>
								</security:authorize>
					 		</div>
				 		</div>
					</blockquote>
					<table id="patternList" lay-filter="patternList"></table>
				</div>
				<script type="text/html" id="patternListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>满减正则说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullpattern">
								
							</ul>
						</div>
					</div>
				</div>
				
				<div id="patternTab" style="display:none;">
					<form class="layui-form" style="width:100%;">
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">优先级</label>
								<div class="layui-input-block">
									<input type="text" class="layui-input priorityPat" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入优先级(只能是数字)">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">正则类型</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="patternType" lay-verify="required">
										<option value="">请选择</option>
										<option value="1">1</option>
										<option value="2">2</option>
									</select>
								</div>
							</div>
						</div>
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">满的group</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="manGroup" lay-verify="required">
										<option value="">请选择</option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
									</select>
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">减的group</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="jieGroup" lay-verify="required">
										<option value="">请选择</option>
										<option value="0">0</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">是否满打折</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="isDazhe" lay-verify="required">
										<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">是否每满</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="isMeiman" lay-verify="required">
										<option value="">请选择</option>
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">正则内容</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input patternStr" lay-verify="required" placeholder="请输入正则内容">
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addPatRecord" lay-submit="" lay-filter="addPatRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editPatRecord" lay-submit="" lay-filter="editPatRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
			</div>
		    <div class="layui-tab-item">
		    	<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  原始字符：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchValRep" placeholder="请输入原始字符" />
								</div>
								<a class="layui-btn search_btn_rep" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn_rep" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn_rep" data-type="reload">批量删除</a>
								</security:authorize>
					 		</div>
				 		</div>
					</blockquote>
					<table id="replaceList" lay-filter="replaceList"></table>
				</div>
				<script type="text/html" id="replaceListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>满减字符替换说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullreplace">
								
							</ul>
						</div>
					</div>
				</div>
				
				<div id="replaceTab" style="display:none;">
					<form class="layui-form" style="width:90%;">
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">优先级</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input priorityRep" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入优先级(只能是数字)">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">原始字符</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input replaceStr" lay-verify="required" placeholder="请输入原始字符">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">替换字符</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input endStr" lay-verify="required" placeholder="请输入替换字符">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addRepRecord" lay-submit="" lay-filter="addRepRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editRepRecord" lay-submit="" lay-filter="editRepRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
		    </div>
		    <div class="layui-tab-item">
		    	<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  促销名称：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchValPri" placeholder="请输入促销名称" />
								</div>
								<a class="layui-btn search_btn_pri" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn_pri" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL">
								<a class="layui-btn layui-btn-danger delAll_btn_pri" data-type="reload">批量删除</a>
								</security:authorize>
					 		</div>
				 		</div>
					</blockquote>
					<table id="priceList" lay-filter="priceList"></table>
				</div>
				<script type="text/html" id="priceListBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>价格计算公式说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullprice">
								
							</ul>
						</div>
					</div>
				</div>
				
				<div id="priceTab" style="display:none;">
					<form class="layui-form" style="width:90%;">
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">平台ID</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input platformId" lay-verify="required" placeholder="请选择平台">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">促销名称</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input promotionTypeName" lay-verify="required" placeholder="请选择促销">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<div class="layui-input-block">
									<a class="layui-btn layui-btn-normal selectPromotion">促销选择</a>
								</div>
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">促销价计算公式</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input promotionPrice" lay-verify="required" placeholder="请输入促销价计算公式">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">标准价计算公式</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input price" lay-verify="required" placeholder="请输入标准价计算公式">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addPriRecord" lay-submit="" lay-filter="addPriRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editPriRecord" lay-submit="" lay-filter="editPriRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
				<div id="promotion" style="display:none;">
					<table id="promotionList" lay-filter="promotionList"></table>
					<script type="text/html" id="promotionListBar">
						<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
					</script>
				</div>
		    </div>
		</div>
	</div> 

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="PriceTab.js"></script>
</body>
</html>