<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据处理QC</title>
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
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/ModuleOne.jsp">
				<div class="panel_icon layui-bg-green">
					<span>QC一</span>
				</div>
				<div class="panel_word">
					<span>一对多&一致性</span>
					<cite class="layui-hide">QC一</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/ModuleTwo.jsp">
				<div class="panel_icon layui-bg-black">
					<span>QC二</span>
				</div>
				<div class="panel_word">
					<span>大小写&含空格<br/>&非法值</span>
					<cite class="layui-hide">QC二</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/platformClassify/ModuleThree.jsp">
				<div class="panel_icon layui-bg-green">
					<span>QC三</span>
				</div>
				<div class="panel_word">
					<span>检查&匹配<br/>&包含</span>
					<cite class="layui-hide">QC三</cite>
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