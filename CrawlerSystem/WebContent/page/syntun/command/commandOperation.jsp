<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>命令操作</title>
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
<form class="layui-form">
<div id="commandDiv">
	<input type="hidden" class="filedId">
    <div class="layui-form-item">
      <label class="layui-form-label">ip</label>
		<div class="layui-input-inline">
	        <select name="modules" lay-verify="required" lay-search="" id="commandIp">
	        </select>
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
			<a class="layui-btn layui-btn-sm" id="" onclick="goCommand()">执行</a>
		</div>
	</div>
</div>

<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
 		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					模糊搜索：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="请输入内容" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">搜索</a>
				</div>
			</div>	
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="add">添加</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</div> 
</form>	
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
    	initSelectIp();
    });
    //初始化获取ip地址
    function initSelectIp(){
     	$.ajax({
	  		  url: $("#ctx").attr("value") + '/command/getServersIp',
			  type: 'post',
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					    var remarks = '';
						for (var i = 0; i < data.Servers.length; i++) {
			  		    	remarks = remarks + "<option value='"+data.Servers[i].ip+"'>"+data.Servers[i].ip+"</option>";
						}
						$('#commandIp').html('');
						$("#commandIp").append(remarks);
		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
				  }else{
					  top.layer.msg("获取失败！");
				  }
			  },
			  fail: function (err, status) {
				  alert(status);
			  }
			})
    }
    function renderForm() {
        layui.use(['form'], function () {
            var form = layui.form;
            form.render(); //刷新select选择框渲染
        });
    }
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
	layui.use(['element','form','layer','table','laytpl'],function(){
		
		var form = layui.form,
	        layer = parent.layer === undefined ? layui.layer : top.layer,
	        $ = layui.jquery,
	        laytpl = layui.laytpl,
	        table = layui.table;
	    //替换字段列表
	    var tableIns = table.render({
	        elem: '#filedList',
	        url :$("#ctx").attr("value") + '/user/getHistoryInfo?type=3',
	        cellMinWidth : 95,
	        page : true,
	        height : "full-68",
	        limits : [10,15,20,25],
	        limit : 10,
	        id : "filedListTable",
	        cols : [[
	            {type: 'numbers', width:30, fixed: 'left'},
	            //{field: 'id', title: 'id', align:"center", minWidth:50},
	            {field: 'name', title: '历史执行', align:'center',minWidth:500},
	            {field: 'createtime', title: '创建时间', align:'center',minWidth:200},
	            {title: '操作', width:150, templet:'#newsListBar',fixed:"right",align:"center"}
	        ]]
	    });

	    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
	    $(".search_btn").on("click",function(){
	    	table.reload("filedListTable",{
	    		page: {
	    			curr: 1 //重新从第 1 页开始
	            },
	            url: $("#ctx").attr("value") + '/user/getHistoryInfo?type=3',
	            method: 'post',
	            where: {
	            	cateName : $(".searchVal").val()
	            }
	         })
	         //$(".abstract").val("1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”%OD%OA2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】%OD%OA3、“个人资料”提供修改功能4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】")
	    });

	    $(".add_btn").click(function(){
	    	$(".cateId").val("");
		    $(".cateName").val("");
	    	$(".account").val("");
	    	addFiled();
	    });
	  //添加
	    function addFiled(edit){
	    	$(".addFlied").show();
	    	$(".editFlied").hide();
	    	var title = "添加角色";
	    	if(edit){
	    		title = "编辑角色";
	            $(".filedId").val(edit.id);  
	        	$(".cateId").val(edit.role);  
	            $(".cateName").val(edit.descpt);  
	            $(".account").val(edit.category);  
	    		$(".addFlied").hide();
	    		$(".editFlied").show();
	        }
	    	
	    	$("#addFiled").dialog({  
	            //autoOpen : false,   // 是否自动弹出窗口  
	            modal : true,    // 设置为模态对话框  
	            resizable : true,  
	            width : 500,   //弹出框宽度  
	            height : 350,   //弹出框高度  
	            title : title,  //弹出框标题  
	            position: { my: "center top", at: "center top", of: window },
	            closeText : '关闭',
	            buttons:{  
	            	/*'添加':function(data){  
	            		 
		            }, */
		            '取消':function(){  
		            	$(".cateId").val("");
		        	    $(".cateName").val("");
		            	$(".account").val("");
		            	$(this).dialog("close");  
		            }  
	            } 
	       });  
	    	
	    };
	    //立即添加按钮
	    form.on("submit(addFlied)",function(data){
	    	//弹出loading
	       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			$.ajax({
				  url: $("#ctx").attr("value") + '/user/getEditRoleInfo.ht',
				  type: 'post',
				  data: {
					  "id" : $(".filedId").val(),//ID  
					  "flag" : "add",
			    	  "role" : $(".cateId").val(),
			    	  "descpt" : $(".cateName").val(),  
			    	  "category" : $(".account").val(),  
				  },
				  async:false,
				  dataType: 'json',
				  timeout: 2000,
				  success: function (data, status) {
					  if(data.code=='200'){
						  top.layer.msg("添加成功！");
					  }else if(data.code=='201'){
						  top.layer.msg(data.msg);
					  }else{
						  top.layer.msg("添加失败！");
					  }
					  closeData();
				  },
				  fail: function (err, status) {
					  alert(status);
				  }
		    	})
		    	tableIns.reload();
	        	$(".cateId").val("");
	    	    $(".cateName").val("");
	        	$(".account").val("");
	        	$("#addFiled").dialog("close"); 
	        return false;
	    });
	    //立即修改按钮
	    form.on("submit(editFlied)",function(data){
	        //弹出loading
	       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
			$.ajax({
				  url: $("#ctx").attr("value") + '/user/getEditRoleInfo.ht',
				  type: 'post',
				  data: {
					  "id" : $(".filedId").val(),
					  "flag" : "edit",
			    	  "role" : $(".cateId").val(),
			    	  "descpt" : $(".cateName").val(),  
			    	  "category" : $(".account").val(),  
				  },
				  async:false,
				  dataType: 'json',
				  timeout: 2000,
				  success: function (data, status) {
					  if(data.code=='200'){
						  top.layer.msg("修改成功！");
					  }else if(data.code=='201'){
						  top.layer.msg(data.msg);
					  }else{
						  top.layer.msg("修改失败！");
					  }
					  closeData();
				  },
				  fail: function (err, status) {
					  top.layer.msg(status);
				  }
		    	})
	        	tableIns.reload();
	        	$(".cateId").val("");
	    	    $(".cateName").val("");
	        	$(".account").val("");
	        	$("#addFiled").dialog("close"); 
	        return false;
	    })

	    //批量删除
	    $(".delAll_btn").click(function(){
	    	var checkStatus = table.checkStatus('filedListTable'),
	            data = checkStatus.data,
	            ids = [];
	        if(data.length > 0) {
	            for (var i in data) {
	            	ids.push(data[i].id);
	            }
	            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
	                $.get($("#ctx").attr("value") + "/cateAccount/delAllRecord.ht",{
	                    ids : ids.toString()  //将需要删除的newsId作为参数传入
	                },function(data){
	                	top.layer.msg("批量删除成功！");
	                	tableIns.reload();
	                	layer.close(index);
	                })
	            })
	        }else{
	            layer.msg("请选择需要删除的记录");
	        }
	    })
	  
	    //列表操作
	    table.on('tool(filedList)', function(obj){
	        var layEvent = obj.event;
	            data = obj.data;
	        if(layEvent === 'edit'){ //编辑
	            addFiled(data);
	        }else if(layEvent === 'del'){ //删除
	        	layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
	                $.get($("#ctx").attr("value") + "/user/delHistoryInfo.ht",{
	                	id : data.id,
	                },function(data){
	                	top.layer.msg("删除成功！");
	                	tableIns.reload();
	                    layer.close(index);
	                })
	            });
	        }else if(layEvent === 'add'){
	        	var name = data.name.split(",");
	        	$("#commandIp").val(name[0]);
	        	$("#commandTextareaId").val(name[1]);
	        	renderForm();
	        }
	    });
	    
	})
</script>
</body>
</html>