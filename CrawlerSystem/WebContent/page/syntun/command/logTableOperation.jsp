<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志表备份操作</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
<link rel="stylesheet" href="../../../js/jqtree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style> 
.a{height:100px;}
.a div{float:left;width:110px;height:100px;margin-left:30px;}
</style> 

</head>
<body>
<input id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<div style="padding-left: 38px;margin-top: 10px;margin-bottom: 10px;">
<!--     <button class="layui-btn layui-btn-primary">无色</button>
    <button class="layui-btn layui-btn-warm">日志表备份</button>
    <button class="layui-btn layui-btn-danger">警告按钮</button> -->
<!--     <input type="button" value='日志表定时备份' onclick="logTableBackups()" class="layui-btn">
    <input type="button" value='文件操作' onclick="fileOperation()" class="layui-btn">
	<input type="button" value='命令操作' onclick="commandButton()" class="layui-btn"> -->
<!-- 	<input type="button" value='清空数据' onclick="emptyData()" class="layui-btn layui-btn-normal"> -->
</div>

<div id="fileDiv" style="display:none;">
	<input type="hidden" class="filedId">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">ip</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="ip" lay-verify="required" placeholder="请输入ip">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">文件路径</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="filePath" lay-verify="required" placeholder="请输入文件路径">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12" style="display: none" id="fileTextDivId">
		<label class="layui-form-label">文本域</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入内容" style="height: 300px" class="layui-textarea" id="fileTextareaId"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" id="queryFile">查看文件</button>
			<button class="layui-btn layui-btn-sm" id="saveFile" style="display: none">保存文件</button>
		</div>
	</div>
</div>

<div id="logDiv" style="display:none;">
	<input type="hidden" class="filedId">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">文本域</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入内容" style="height: 300px" class="layui-textarea" id="textareaId"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" id="saveLog">保存</button>
		</div>
	</div>
</div>

<div id="commandDiv" style="display:none;">
	<input type="hidden" class="filedId">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">ip</label>
		<div class="layui-input-block">
			<input type="text" class="layui-input oldFiled" id="commandIp" lay-verify="required" placeholder="请输入ip">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">文本域</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入内容" style="height: 300px" class="layui-textarea" id="commandTextareaId"></textarea>
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" id="" onclick="goCommand()">执行</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript">
    $(function() {
    	//点击查看文件
    	$("#queryFile").click(function(){
    		var ip = $("#ip").val();
    		if(ip==''){
    			top.layer.msg("请输入ip！");
    			return false;
    		}
    		var filePath = $("#filePath").val();
    		if(filePath==''){
    			top.layer.msg("请输入文件路径！");
    			return false;
    		}
    		var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
       	  	$.ajax({
    	  		  url: $("#ctx").attr("value") + '/command/readData.ht',
    			  type: 'post',
    			  data: {
    				  "ip":ip,
    				  "path":filePath,
    			  },
    			  dataType: 'json',
    			  success: function (data, status) {
    				  if(data.code=='200'){
    					  top.layer.msg("加载完成！");
    					  $("#fileTextDivId").show();
    					  $("#saveFile").show();
    					  $("#fileTextareaId").val(data.strData);
    				  }else{
    					  top.layer.msg("操作失败!");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
    		})
    	});
    	logTableBackups();
    });
    //点击日志表备份按钮
    function logTableBackups(){
    	$("#logDiv").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 560,   //弹出框高度  
            title : "标题",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$("#ip").val("");
            	    $("#name").val("");
            	    $("#password").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
		var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/getCrontab.ht',
			  type: 'post',
			  data: {
				  "ip":"192.168.0.85",
			  },
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
	//点击日志表备份保存按钮
	$("#saveLog").click(function(){
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
				  "ip":"192.168.0.85",
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
	});
	//点击文件操作保存按钮
	$("#saveFile").click(function(){
		var ip = $("#ip").val();
		if(ip==''){
			top.layer.msg("请输入ip！");
			return false;
		}
		var filePath = $("#filePath").val();
		if(filePath==''){
			top.layer.msg("请输入文件路径！");
			return false;
		}
		var str = $("#fileTextareaId").val();
		if(str==''){
			top.layer.msg("请输入文件内容！");
			return false;
		}
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/writeData.ht',
			  type: 'post',
			  data: {
				  "str":str,
				  "ip":ip,
				  "path":filePath,
			  },
			  contentType:"application/x-www-form-urlencoded; charset=utf-8",
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  top.layer.msg("操作完成！");
					  $("#fileTextareaId").val(data.strData);
				  }else{
					  alert("操作失败!");
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
		})
	});
	//点击文件操作按钮
	function fileOperation(){
    	$("#fileDiv").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 560,   //弹出框高度  
            title : "标题",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$("#ip").val("");
            	    $("#name").val("");
            	    $("#password").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
	}
	function readData(){
		var index = top.layer.msg('数据加载中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/readData.ht',
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
	//点击命令操作按钮
	function commandButton(){
    	$("#commandDiv").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 560,   //弹出框高度  
            title : "标题",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$("#ip").val("");
            	    $("#name").val("");
            	    $("#password").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
	}
	//命令操作保存按钮
	function goCommand(){
		var ip = $("#commandIp").val();
		if(ip==''){
			top.layer.msg("请输入IP！");
			return false;
		}
		var str = $("#commandTextareaId").val();
		if(str==''){
			top.layer.msg("请输入内容！");
			return false;
		}
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/commandOperation.ht',
			  type: 'post',
			  data: {
				  "ip":ip,
				  "str":str,
			  },
			  contentType:"application/x-www-form-urlencoded; charset=utf-8",
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  top.layer.msg("操作完成！");
					  $("#commandTextareaId").val(data.s);
				  }else{
					  alert("操作失败!");
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
		})
	}
	//清空数据
	function emptyData(){
		$("#textareaId").val('');
	}
</script>
</body>
</html>