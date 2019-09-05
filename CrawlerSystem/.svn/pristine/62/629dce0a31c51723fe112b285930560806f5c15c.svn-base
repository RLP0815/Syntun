<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件管理</title>
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

	<div class="layui-row layui-col-space25 panel_box">
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/command/fileOperation.jsp">
				<div class="panel_icon layui-bg-green">
					<span>文件操作</span>
				</div>
				<div class="panel_word">
					<span>文件操作</span>
					<cite class="layui-hide">文件操作</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/command/commandOperation.jsp">
				<div class="panel_icon layui-bg-black">
					<span>命令操作</span>
				</div>
				<div class="panel_word">
					<span>命令操作</span>
					<cite class="layui-hide">命令操作</cite>
				</div>
			</a>
		</div>
 		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/command/logTableOperation.jsp">
				<div class="panel_icon layui-bg-green">
					<span>日志表备份</span>
				</div>
				<div class="panel_word">
					<span>日志表备份</span>
					<cite class="layui-hide">日志表备份</cite>
				</div>
			</a>
		</div>
<!--		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/InitUrlList.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表4</span>
				</div>
				<div class="panel_word">
					<span>init_url_list</span>
					<cite class="layui-hide">init_url_list</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/ProductUrl.jsp">
				<div class="panel_icon layui-bg-green">
					<span>表5</span>
				</div>
				<div class="panel_word">
					<span>product_url</span>
					<cite class="layui-hide">product_url</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/PurlList.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表6</span>
				</div>
				<div class="panel_word">
					<span>purl_list</span>
					<cite class="layui-hide">purl_list</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/ReplaceStr.jsp">
				<div class="panel_icon layui-bg-green">
					<span>表7</span>
				</div>
				<div class="panel_word">
					<span>replace_str</span>
					<cite class="layui-hide">replace_str</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/UrlCookie.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表8</span>
				</div>
				<div class="panel_word">
					<span>url_cookie</span>
					<cite class="layui-hide">url_cookie</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/UrlCookieList.jsp">
				<div class="panel_icon layui-bg-green">
					<span>表9</span>
				</div>
				<div class="panel_word">
					<span>url_cookie_list</span>
					<cite class="layui-hide">url_cookie_list</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/UrlLimitList.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表10</span>
				</div>
				<div class="panel_word">
					<span>url_limit_list</span>
					<cite class="layui-hide">url_limit_list</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/Url302List.jsp">
				<div class="panel_icon layui-bg-green">
					<span>表11</span>
				</div>
				<div class="panel_word">
					<span>url302list</span>
					<cite class="layui-hide">url302list</cite>
				</div>
			</a>
		</div>
		<div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
			<a href="javascript:;" data-url="page/syntun/crawler/WebError.jsp">
				<div class="panel_icon layui-bg-black">
					<span>表12</span>
				</div>
				<div class="panel_word">
					<span>web_error</span>
					<cite class="layui-hide">web_error</cite>
				</div>
			</a>
		</div> -->
	</div>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="../crawler/CollectTable.js"></script>
<script type="text/javascript">
    $(function() {
		//初始化数据方法
    });
	function readData(){
		var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/getCrontab.ht',
			  type: 'post',
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  top.layer.msg("加载完成！");
					  $("#textareaId").val(data.strData);
				  }else{
					  alert("操作失败!");
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
		})
	}
	function saveFile(){
		var str = $("#textareaId").val();
		if(str==''){
			top.layer.msg("请输入内容！");
			return false;
		}
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/saveCrontab.ht',
			  type: 'post',
			  data: {
				  "str":str,
			  },
			  contentType:"application/x-www-form-urlencoded; charset=utf-8",
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  top.layer.msg("操作完成！");
				  }else{
					  alert("操作失败!");
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
		})
	}
</script>
</body>
</html>