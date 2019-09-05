<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QC管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />

</head>
<body class="childrenBody">
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">

	<div class="layui-row layui-col-space25 panel_box">
		<!-- <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/database.jsp">
				<div class="panel_icon layui-bg-green">
					<span>数据处理</span>
				</div>
				<div class="panel_word">
					<span>数据处理</span>
					<cite class="layui-hide">数据处理</cite>
				</div>
			</a>
		</div> -->
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/TableUniqueKey.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表格唯一键</span>
				</div>
				<div class="panel_word">
					<span>表格唯一键</span>
					<cite class="layui-hide">表格唯一键</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/TableCountList.jsp">
				<div class="panel_icon layui-bg-green">
					<span>采集数据条数</span>
				</div>
				<div class="panel_word">
					<span>采集数据条数</span>
					<cite class="layui-hide">采集数据条数</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/tableDataShow.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表数据展示</span>
				</div>
				<div class="panel_word">
					<span>表数据展示</span>
					<cite class="layui-hide">表数据展示</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/dataCheckQC.jsp">
				<div class="panel_icon layui-bg-green">
					<span>QC</span>
				</div>
				<div class="panel_word">
					<span>数据处理QC</span>
					<cite class="layui-hide">数据处理QC</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/meetingRoom/skuexcel.jsp">
				<div class="panel_icon layui-bg-black">
					<span>可乐周报</span>
				</div>
				<div class="panel_word">
					<span>可乐周报</span>
					<cite class="layui-hide">可乐周报</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/meetingRoom/skuriexcel.jsp">
				<div class="panel_icon layui-bg-green">
					<span>可乐日报</span>
				</div>
				<div class="panel_word">
					<span>可乐日报</span>
					<cite class="layui-hide">可乐日报</cite>
				</div>
			</a>
		</div>
		 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/meetingRoom/dataCheck.jsp">
				<div class="panel_icon layui-bg-black">
					<span>数据核对模板</span>
				</div>
				<div class="panel_word">
					<span>数据核对模板</span>
					<cite class="layui-hide">数据核对模板</cite>
				</div>
			</a>
		</div>
		 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/meetingRoom/productDetail.jsp">
				<div class="panel_icon layui-bg-green">
					<span>产品详情</span>
				</div>
				<div class="panel_word">
					<span>产品详情</span>
					<cite class="layui-hide">产品详情</cite>
				</div>
			</a>
		</div>
	</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="../crawler/CollectTable.js"></script>
<script type="text/javascript">
    $(function() {
		//初始化数据方法
    });
</script>
</body>
</html>