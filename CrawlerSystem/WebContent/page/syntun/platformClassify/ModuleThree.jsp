<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块三find</title>
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
<script type="text/javascript">
	var nickName = '<%=nickName%>';
	var userId = '<%=userId%>';
	var userName = '<%=userName%>';
	var email = '<%=email%>';
	var phoneno = '<%=phoneno%>';
</script>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form" lay-filter="selFilter">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				校对方式
				<div class="layui-input-inline">
					<select class="checkMethod" lay-filter="checkMethod" id="checkMethod">
						<option value=''>请选择</option>
						<option value='qc_btn1'>检查</option>
						<option value='qc_btn2'>匹配</option>
						<option value='qc_btn3'>包含</option>
					</select>
				</div>
			</div>
			<div class="layui-inline num">
				&nbsp;&nbsp;&nbsp;&nbsp;条件数
    			<div class="layui-input-inline" >
      				<input type="radio" lay-filter="num" name="num" value="1" title="1" checked="">
      				<input type="radio" lay-filter="num" name="num" value="2" title="2">
    			</div>
  			</div>
			<a class="layui-btn search_btn" data-type="reload">搜索</a>
			<a class="layui-btn qc_btn" data-type="reload">校对</a>
			<a class="layui-btn layui-btn-normal qcAll_btn" data-type="reload">批量校对</a>
			<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
		</blockquote>
	</div>
	<div class="layui-col-md6 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				查询条件
				<div class="layui-input-inline" style="width:177px;">
					<select class="listVal" lay-filter="listVal" lay-search="" id="listVal">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校对列
				<div class="layui-input-inline" style="width:155px;">
					<select class="tableColumn1" lay-filter="tableColumn1" lay-search="" id="tableColumn1">
					</select>
				</div>
			</div>
			<div class="layui-inline divKey1">
				匹配条件列1
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectKey1" lay-filter="connectKey1" lay-search="" id="connectKey1">
					</select>
				</div>
			</div>
			<div class="layui-inline divVal1">
				匹配条件值1
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectVal1" lay-filter="connectVal1" lay-search="" id="connectVal1">
					</select>
				</div>
			</div>
			<div class="layui-inline divKey2">
				匹配条件列2
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectKey2" lay-filter="connectKey2" lay-search="" id="connectKey2">
					</select>
				</div>
			</div>
			<div class="layui-inline divVal2">
				匹配条件值2
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectVal2" lay-filter="connectVal2" lay-search="" id="connectVal2">
					</select>
				</div>
			</div>
		</blockquote>
	</div>
	<div class="layui-col-md6 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				数据库
				<div class="layui-input-inline" style="width:115px;">
					<select class="databaseName" lay-filter="databaseName" lay-search="" id="databaseName">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				数据表
				<div class="layui-input-inline" style="width:115px;">
					<select class="tableName" lay-filter="tableName" lay-search="" id="tableName">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				被校对列
				<div class="layui-input-inline" style="width:115px;">
					<select class="tableColumnB1" lay-filter="tableColumnB1" lay-search="" id="tableColumnB1">
					</select>
				</div>
			</div>
			<div class="layui-inline divBKey1">
				被匹配条件列1
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectBKey1" lay-filter="connectBKey1" lay-search="" id="connectBKey1">
					</select>
				</div>
			</div>
			<div class="layui-inline divBKey2">
				被匹配条件列2
				<div class="layui-input-inline" style="width:155px;">
					<select class="connectBKey2" lay-filter="connectBKey2" lay-search="" id="connectBKey2">
					</select>
				</div>
			</div>
		<!-- <table id="filedList" lay-filter="filedList"></table> -->
		</blockquote>
	</div>
	<div class="layui-col-md12 layui-col-xs12">
		<table id="filedList" lay-filter="filedList"></table>
	</div>
	<!--操作-->
	<script type="text/html" id="newsListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">校对</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</div> 
</form>
	<div id="remarkRecord" style="display: none;">
		<form class="layui-form"
			action="${pageContext.request.contextPath}/baseController/exportTxt">
			<div class="layui-form-item layui-form-text">
				<textarea placeholder="请输入内容" style="height: 400px;"
					class="layui-textarea out"></textarea>
			</div>
			<button class="layui-btn">下载</button>
		</form>
	</div>
	<div id="illegalChar" style="display:none;">
		<div class="layui-form-item layui-form-text">
		    <textarea placeholder="请输入非法字符" class="layui-textarea char"></textarea>
		</div>
	</div>
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['element','form','layer','table','laytpl'],function(){
    	var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laytpl = layui.laytpl,
            table = layui.table;

    	$(".divKey2").hide();
    	$(".divVal2").hide();
    	$(".divBKey2").hide();
    	
    	$("#checkMethod").val("");
    	$("#listVal").val("");
    	$("#connectKey1").val("");
    	$("#connectBKey1").val("");
    	$("#connectVal1").val("");
    	$("#connectKey2").val("");
    	$("#connectBKey2").val("");
    	$("#connectVal2").val("");
    	$("#tableColumn1").val("");
    	$("#tableColumnB1").val("");
    	$("#databaseName").val("");
    	$("#tableName").val("");
    	//初始加载数据库
    	getDatabase();
    	//初始化查询条件
    	//getListVal();
    	//初始化关联条件列
    	//getConnectKey();
    	var datat1 = [[
                   {type: 'numbers', width:30, fixed: 'left'},
                   {type: 'checkbox', fixed: 'left'},
                  // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                   {field: 'listVal', title: '查询条件', align:'center',minWidth:100},
                   {field: 'column1', title: '校对列', align:'center',minWidth:100},
                   {field: 'connectKey1', title: '匹配条件列1', align:'center',minWidth:120},
                   {field: 'connectBKey1', title: '被匹配条件列1', align:'center',minWidth:130},
                   {field: 'connectVal1', title: '匹配条件值1', align:'center',minWidth:120},
                   {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                   {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                   {field: 'columnB1', title: '被校对列', align:'center',minWidth:100},
                   {field: 'method', title: '被校方式', align:'center',minWidth:100},
                   {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
               ]];
		var datat2 = [[
                   {type: 'numbers', width:30, fixed: 'left'},
                   {type: 'checkbox', fixed: 'left'},
                  // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                   {field: 'listVal', title: '查询条件', align:'center',minWidth:100},
                   {field: 'column1', title: '校对列', align:'center',minWidth:100},
                   {field: 'connectKey1', title: '匹配条件列1', align:'center',minWidth:120},
                   {field: 'connectBKey1', title: '被匹配条件列1', align:'center',minWidth:130},
                   {field: 'connectVal1', title: '匹配条件值1', align:'center',minWidth:120},
                   {field: 'connectKey2', title: '匹配条件列2', align:'center',minWidth:120},
                   {field: 'connectBKey2', title: '被匹配条件列2', align:'center',minWidth:130},
                   {field: 'connectVal2', title: '匹配条件值2', align:'center',minWidth:120},
                   {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                   {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                   {field: 'columnB1', title: '被校对列', align:'center',minWidth:100},
                   {field: 'method', title: '被校方式', align:'center',minWidth:100},
                   {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
               ]];
		var datat3 = [[
                   {type: 'numbers', width:30, fixed: 'left'},
                   {type: 'checkbox', fixed: 'left'},
                  // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                   {field: 'listVal', title: '查询条件', align:'center',minWidth:100},
                   {field: 'column1', title: '校对列', align:'center',minWidth:100},
                   {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                   {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                   {field: 'columnB1', title: '被校对列', align:'center',minWidth:100},
                   {field: 'method', title: '被校方式', align:'center',minWidth:100},
                   {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
               ]];
		var datat4 = [[
                   {type: 'numbers', width:30, fixed: 'left'},
                   {type: 'checkbox', fixed: 'left'},
                  // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                   {field: 'listVal', title: '查询条件', align:'center',minWidth:100},
                   {field: 'column1', title: '校对列', align:'center',minWidth:100},
                   {field: 'connectKey1', title: '匹配条件列', align:'center',minWidth:120},
                   {field: 'connectVal1', title: '匹配条件值', align:'center',minWidth:120},
                   {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                   {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                   {field: 'columnB1', title: '被校对列', align:'center',minWidth:100},
                   {field: 'method', title: '被校方式', align:'center',minWidth:100},
                   {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
               ]];
		var datat = datat1;
    	//初始化匹配条件
    	function getListVal() {
			$.ajax({
				url: $("#ctx").attr("value") + '/moduleThree/getListVal',
				type: 'post',
      			data: {
      				"tableName":"行业规则",
      				"column":"品类",
      			},
				dataType: 'json',
				success: function (data, status) {
					if(data.code=='200'){
						//top.layer.msg("查询条件获取成功！");
						var remarks = '<option value="">直接选择或搜索选择</option>';
   						for (var i = 0; i < data.list.length; i++) {
   			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
   						}
   						$('.listVal').html('');
   						$(".listVal").append(remarks);
   						form.render();//表单重新渲染，要不然添加完显示不出来新的
					}else{
						top.layer.msg("查询条件获取失败！");
					}
				},
				fail: function (err, status) {
					alert(status);
				}
			})
            //form.render();
		}
    	//初始化匹配条件
    	function getListVal2() {
			$.ajax({
				url: $("#ctx").attr("value") + '/moduleThree/getListVal',
				type: 'post',
      			data: {
      				"tableName":"参数表列名对应内容",
      				"column":"品类"
      			},
				dataType: 'json',
				success: function (data, status) {
					if(data.code=='200'){
						//top.layer.msg("查询条件获取成功！");
						var remarks = '<option value="">直接选择或搜索选择</option>';
   						for (var i = 0; i < data.list.length; i++) {
   			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
   						}
   						$('.listVal').html('');
   						$(".listVal").append(remarks);
   						form.render();//表单重新渲染，要不然添加完显示不出来新的
					}else{
						top.layer.msg("查询条件获取失败！");
					}
				},
				fail: function (err, status) {
					alert(status);
				}
			})
            //form.render();
		}
    	//数据库databaseName select change
        form.on('select(listVal)', function(data){
        	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
               	$('#listVal').val("");
               	form.render();
        		return;
        	}
        	$("#tableColumn1").val("");
            var listVal = data.value;
            if(listVal == "" && $("#checkMethod").val() == "qc_btn2"){
            	var remarks = '<option value="">直接选择或搜索选择</option>';
				$('#tableColumn1').html('');
				$("#tableColumn1").append(remarks);
				form.render();//表单重新渲染，要不然添加完显示不出来新的
				return;
            }
            $("#connectKey1").val("");
            $("#connectKey2").val("");
            var remarks = '<option value="">请选择</option>';
			$('#connectVal1').html('');
			$("#connectVal1").append(remarks);
			$('#connectVal2').html('');
			$("#connectVal2").append(remarks);
			form.render();
            
            if($("#checkMethod").val() == "qc_btn2"){
            	$.ajax({
    				url: $("#ctx").attr("value") + '/moduleThree/getListVal2',
    				type: 'post',
          			data: {
          				"tableName":"参数表列名对应内容",
          				"column":"列名",
          				"listVal":listVal
          			},
    				dataType: 'json',
    				success: function (data, status) {
    					if(data.code=='200'){
    						//top.layer.msg("匹配条件列获取成功！");
    						var remarks = '<option value="">直接选择或搜索选择</option>';
       						for (var i = 0; i < data.list.length; i++) {
       			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
       						}
       						//$('#connectKey1').html('');
    						//$("#connectKey1").append(remarks);
    						//$('#connectKey2').html('');
    						//$("#connectKey2").append(remarks);
    						$('#tableColumn1').html('');
    						$("#tableColumn1").append(remarks);
       						form.render();//表单重新渲染，要不然添加完显示不出来新的
    					}else{
    						top.layer.msg("匹配条件列获取失败！");
    					}
    				},
    				fail: function (err, status) {
    					alert(status);
    				}
    			})
            }
            //form.render();
        });
    	//初始化关联条件列
    	function getConnectKey() {
         	$.ajax({
    	  		url: $("#ctx").attr("value") + '/platformClassify/getColumns',
    			type: 'post',
      			data: {
      				"tableName":"行业规则",
      				"database":"info",
      			},
    			dataType: 'json',
    			success: function (data, status) {
    				if(data.code=='200'){
    					//top.layer.msg("匹配条件列获取成功！");
    					var remarks = '<option value="">直接选择或搜索选择</option>';
    					for (var i = 0; i < data.list.length; i++) {
    			  			remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    					}
    					$('#connectKey1').html('');
    					$("#connectKey1").append(remarks);
    					$('#connectKey2').html('');
    					$("#connectKey2").append(remarks);
    					$('#tableColumn1').html('');
    					$("#tableColumn1").append(remarks);
    					form.render();//表单重新渲染，要不然添加完显示不出来新的
    				}else{
    					top.layer.msg("匹配条件列获取失败！");
    				}
    			},
    			fail: function (err, status) {
    				alert(status);
    			}
   			})
            //form.render();
        }
    	//初始化关联条件列
    	function getConnectKey2() {
    		$.ajax({
				url: $("#ctx").attr("value") + '/moduleThree/getListVal',
				type: 'post',
      			data: {
      				"tableName":"参数表列名对应内容",
      				"column":"列名"
      			},
				dataType: 'json',
				success: function (data, status) {
					if(data.code=='200'){
						//top.layer.msg("匹配条件列获取成功！");
						var remarks = '<option value="">直接选择或搜索选择</option>';
   						for (var i = 0; i < data.list.length; i++) {
   			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
   						}
   						$('#connectKey1').html('');
						$("#connectKey1").append(remarks);
						$('#connectKey2').html('');
						$("#connectKey2").append(remarks);
						$('#tableColumn1').html('');
						$("#tableColumn1").append(remarks);
   						form.render();//表单重新渲染，要不然添加完显示不出来新的
					}else{
						top.layer.msg("匹配条件列获取失败！");
					}
				},
				fail: function (err, status) {
					alert(status);
				}
			})
            //form.render();
        }
    	// 初始化校对数据库
		function getDatabase() {
			$.ajax({
				url: $("#ctx").attr("value") + '/platformClassify/getDataBaseName',
				type: 'post',
				dataType: 'json',
				success: function (data, status) {
					if(data.code=='200'){
						//top.layer.msg("数据库获取成功！");
						var remarks = '<option value="">直接选择或搜索选择</option>';
   						for (var i = 0; i < data.list.length; i++) {
   			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
   						}
   						$('.databaseName').html('');
   						$(".databaseName").append(remarks);
   						form.render();//表单重新渲染，要不然添加完显示不出来新的
					}else{
						top.layer.msg("数据库获取失败！");
					}
				},
				fail: function (err, status) {
					alert(status);
				}
			})
		}
		//数据库checkMethod select change
        form.on('select(checkMethod)', function(data){
        	var checkMethod = data.value;
        	var method;
        	if(checkMethod == "qc_btn1"){
        		$("#databaseName").val("");
    			$("#tableName").val("");
    			$("#tableColumn1").val("");
    			$("#tableColumnB1").val("");
    			$("#listVal").val("");
    			$("#connectKey1").val("");
    			$("#connectBKey1").val("");
    			$("#connectVal1").val("");
    			$("#connectKey2").val("");
    			$("#connectBKey2").val("");
    			$("#connectVal2").val("");
    			
    			$(".num").show();
    			$(".divKey1").show();
            	$(".divVal1").show();
            	$(".divBKey1").show();
            	
    			getListVal();
            	getConnectKey();
            	datat = datat1;
            	method = "检查";
        	}else if(checkMethod == "qc_btn2"){
        		$("#databaseName").val("");
    			$("#tableName").val("");
    			$("#tableColumn1").val("");
    			$("#tableColumnB1").val("");
    			$("#listVal").val("");
    			$("#connectKey1").val("");
    			$("#connectBKey1").val("");
    			$("#connectVal1").val("");
    			$("#connectKey2").val("");
    			$("#connectBKey2").val("");
    			$("#connectVal2").val("");
    			
    			$(".num").hide();
    			$(".divKey1").hide();
            	$(".divVal1").hide();
            	$(".divBKey1").hide();
    			$(".divKey2").hide();
            	$(".divVal2").hide();
            	$(".divBKey2").hide();
            	getListVal2();
            	//getConnectKey2();
            	var remarks = '<option value="">请选择</option>';
				$('#tableColumn1').html('');
				$("#tableColumn1").append(remarks);
				form.render();
				datat = datat3;
				method = "匹配";
        	}else if(checkMethod == "qc_btn3"){
        		$("#databaseName").val("");
    			$("#tableName").val("");
    			$("#tableColumn1").val("");
    			$("#tableColumnB1").val("");
    			$("#listVal").val("");
    			$("#connectKey1").val("");
    			$("#connectBKey1").val("");
    			$("#connectVal1").val("");
    			$("#connectKey2").val("");
    			$("#connectBKey2").val("");
    			$("#connectVal2").val("");
    			
    			$(".num").hide();
            	$(".divBKey1").hide();
    			$(".divKey2").hide();
            	$(".divVal2").hide();
            	$(".divBKey2").hide();
            	
            	getListVal();
            	getConnectKey();
            	datat = datat4;
            	method = "包含";
        	}
        	/* var tableIns = table.render({
				elem: '#filedList',
                url :$("#ctx").attr("value") + '/moduleThree/getList.ht',
                cellMinWidth : 95,
                page : false,
                height : "full-179",
                limits : [10,15,20,25],
                limit : 10,
                id : "filedListTable",
                cols : datat
           });  */
        	table.reload("filedListTable",{
                url: $("#ctx").attr("value") + '/moduleThree/getList.ht',
                method: 'post',
                where: {
                	method : method
                }
             })
        });
      //条件数radio select change
        form.on('radio(num)', function(data){
        	var num = data.value;
        	if(num == "1"){
            	$(".divKey2").hide();
            	$(".divVal2").hide();
            	$(".divBKey2").hide();
            	datat = datat1;
        	}else if(num == "2"){
        		$(".divKey2").show();
        		$(".divVal2").show();
        		$(".divBKey2").show();
        		datat = datat2;
        	}
        	form.render();
        	/* var tableIns = table.render({
				elem: '#filedList',
                url :$("#ctx").attr("value") + '/moduleThree/getList.ht',
                cellMinWidth : 95,
                page : false,
                height : "full-179",
                limits : [10,15,20,25],
                limit : 10,
                id : "filedListTable",
                cols : datat
           }); 
			var checkMethod = $("#checkMethod").val();
        	if(checkMethod == "qc_btn1"){
        		table.reload("filedListTable",{
                    url: $("#ctx").attr("value") + '/moduleThree/getList.ht',
                    method: 'post',
                    where: {
                    	method : "检查"
                    }
				})
			} */
        });
       	//表 connectKey1 select change
        form.on('select(connectKey1)', function(data){
        	var checkMethod = $("#checkMethod").val();
        	if(checkMethod == ""){
        		top.layer.msg("请选择校对方式！");
               	$('#connectKey1').val("");
               	form.render();
        		return;
        	}
        	if($("#listVal").val() == ""){
        		top.layer.msg("请选择查询条件！");
               	$('#connectKey1').val("");
               	form.render();
        		return;
        	}
           	$('#connectVal1').val("");
            var connectKey1 = data.value;
            if(connectKey1 == ""){
            	var remarks = '<option value="">直接选择或搜索选择</option>';
				$('#connectVal1').html('');
				$("#connectVal1").append(remarks);
				form.render();//表单重新渲染，要不然添加完显示不出来新的
				return;
            }
            
            var url = $("#ctx").attr("value") + '/moduleThree/getListVal2';
            var tableName = "行业规则";
            if(checkMethod == "qc_btn2"){
            	url = $("#ctx").attr("value") + '/moduleThree/getConnectVal';
            	tableName = "参数表列名对应内容";
            }
          	$.ajax({
          		url: url,
				type: 'post',
      			data: {
      				"tableName":tableName,
      				"column":connectKey1,
      				"listVal":$("#listVal").val(),
      			},
     			dataType: 'json',
     			success: function (data, status) {
     				if(data.code=='200'){
     					//top.layer.msg("关联列条件值获取成功！");
     					var remarks = '<option value="">直接选择或搜索选择</option>';
     					for (var i = 0; i < data.list.length; i++) {
     			  			remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
     					}
     					$('#connectVal1').html('');
     					$("#connectVal1").append(remarks);
     					form.render();//表单重新渲染，要不然添加完显示不出来新的
     				}else{
     					top.layer.msg("关联列条件值获取失败！");
     				}
     			},
     			fail: function (err, status) {
     				alert(status);
     			}
    		})
            //form.render();
       });
      	//表 connectKey2 select change
		form.on('select(connectKey2)', function(data){
			var checkMethod = $("#checkMethod").val();
        	if(checkMethod == ""){
        		top.layer.msg("请选择校对方式！");
               	$('#connectKey2').val("");
               	form.render();
        		return;
        	}
        	if($("#listVal").val() == ""){
        		top.layer.msg("请选择查询条件！");
               	$('#connectKey1').val("");
               	form.render();
        		return;
        	}
      		$('#connectVal2').val("");
            var connectKey2 = data.value;
            if(connectKey2 == ""){
            	var remarks = '<option value="">请选择</option>';
				$('#connectVal2').html('');
				$("#connectVal2").append(remarks);
				form.render();//表单重新渲染，要不然添加完显示不出来新的
				return;
            }
            
            var url = $("#ctx").attr("value") + '/moduleThree/getListVal2';
            var tableName = "行业规则";
            if(checkMethod == "qc_btn2"){
            	url = $("#ctx").attr("value") + '/moduleThree/getConnectVal';
            	tableName = "参数表列名对应内容";
            }
          	$.ajax({
          		url: url,
				type: 'post',
      			data: {
      				"tableName":tableName,
      				"column":connectKey2,
      				"listVal":$("#listVal").val(),
      			},
     			dataType: 'json',
     			success: function (data, status) {
     				if(data.code=='200'){
     					//top.layer.msg("关联列条件值获取成功！");
     					var remarks = '<option value="">直接选择或搜索选择</option>';
     					for (var i = 0; i < data.list.length; i++) {
     			  			remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
     					}
     					$('#connectVal2').html('');
     					$("#connectVal2").append(remarks);
     					form.render();//表单重新渲染，要不然添加完显示不出来新的
     				}else{
     					top.layer.msg("关联列条件值获取失败！");
     				}
     			},
     			fail: function (err, status) {
     				alert(status);
     			}
    		})
            //form.render();
       });
		
		//数据库databaseName select change
        form.on('select(databaseName)', function(data){
        	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
               	$('#databaseName').val("");
               	form.render();
        		return;
        	}
        	$("#tableName").val("");
            var database = data.value;
            if(database == ""){
            	var remarks = '<option value="">直接选择或搜索选择</option>';
				$('#tableName').html('');
				$("#tableName").append(remarks);
				form.render();//表单重新渲染，要不然添加完显示不出来新的
				return;
            }
         	$.ajax({
    	  		url: $("#ctx").attr("value") + '/platformClassify/getTableNames',
    			type: 'post',
      			data: {
      				"database":database,
      			},
    			dataType: 'json',
    			success: function (data, status) {
    				if(data.code=='200'){
    					//top.layer.msg("数据库表获取成功！");
						var remarks = '<option value="">直接选择或搜索选择</option>';
	    				for (var i = 0; i < data.list.length; i++) {
	    			  		remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
	    				}
	    				$('#tableName').html('');
	    				$("#tableName").append(remarks);
	    				form.render();//表单重新渲染，要不然添加完显示不出来新的
    				}else{
    					top.layer.msg("数据库表获取失败！");
    				}
    			},
    			fail: function (err, status) {
    				alert(status);
    			}
   			})
            //form.render();
        });
      //表 tableName select change
        form.on('select(tableName)', function(data){
        	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
               	$('#tableName').val("");
               	form.render();
        		return;
        	}
        	$('#connectBKey1').val("");
        	$('#connectBKey2').val("");
        	$('#tableColumnB1').val("");
            var tableName = data.value;
        	var databaseName = $("#databaseName").val();
        	if(tableName == ""){
            	var remarks = '<option value="">直接选择或搜索选择</option>';
            	$('#tableColumnB1').html('');
				$("#tableColumnB1").append(remarks);
				$('#connectBKey1').html('');
				$("#connectBKey1").append(remarks);
				$('#connectBKey2').html('');
				$("#connectBKey2").append(remarks);
				form.render();//表单重新渲染，要不然添加完显示不出来新的
				return;
            }
         	$.ajax({
    	  		  url: $("#ctx").attr("value") + '/platformClassify/getColumns',
    			  type: 'post',
      			  data: {
      				  "tableName":tableName,
      				  "database":databaseName,
      			  },
    			  dataType: 'json',
    			  success: function (data, status) {
    				  if(data.code=='200'){
    					  //top.layer.msg("表字段获取成功！");
    					    var remarks = '<option value="">直接选择或搜索选择</option>';
    						for (var i = 0; i < data.list.length; i++) {
    			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    						}
    						$('#tableColumnB1').html('');
    						$("#tableColumnB1").append(remarks);
    						$('#connectBKey1').html('');
    						$("#connectBKey1").append(remarks);
    						$('#connectBKey2').html('');
    						$("#connectBKey2").append(remarks);
    						form.render();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("表字段获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
            //form.render();
        });
      
        //列表
         var tableIns = table.render({
        	 elem: '#filedList',
             url :$("#ctx").attr("value") + '/moduleThree/getList.ht',
             cellMinWidth : 95,
             page : false,
             height : "full-179",
             limits : [10,15,20,25],
             limit : 10,
             id : "filedListTable",
             cols : [[
                      {type: 'numbers', width:30, fixed: 'left'},
                      {type: 'checkbox', fixed: 'left'},
                     // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                      {field: 'listVal', title: '查询条件', align:'center',minWidth:100},
                      {field: 'column1', title: '校对列', align:'center',minWidth:100},
                      {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                      {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                      {field: 'columnB1', title: '被校对列', align:'center',minWidth:100},
                      {field: 'connectKey1', title: '匹配条件列1', align:'center',minWidth:120},
                      {field: 'connectBKey1', title: '被匹配条件列1', align:'center',minWidth:130},
                      {field: 'connectVal1', title: '匹配条件值1', align:'center',minWidth:120},
                      {field: 'connectKey2', title: '匹配条件列2', align:'center',minWidth:120},
                      {field: 'connectBKey2', title: '被匹配条件列2', align:'center',minWidth:130},
                      {field: 'connectVal2', title: '匹配条件值2', align:'center',minWidth:120},
                      {field: 'method', title: '被校方式', align:'center',minWidth:100},
                      {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
                  ]]
        });
        
		function getCheckResult(edit){
			var checkMethod = $("#checkMethod").val();
			var method;
			var ed = "F";
        	if(checkMethod == "qc_btn1"){
        		method = "检查";
			}else if(checkMethod == "qc_btn2"){
        		method = "匹配";
			}else if(checkMethod == "qc_btn3"){
        		method = "包含";
			}
			var database = $("#databaseName").val();
			var tableName = $("#tableName").val();
			var column1 = $("#tableColumn1").val();
			var columnB1 = $("#tableColumnB1").val();
			var listVal = $("#listVal").val();
			var connectKey1 = $("#connectKey1").val();
			var connectBKey1 = $("#connectBKey1").val();
			var connectVal1 = $("#connectVal1").val();
			var connectKey2 = $("#connectKey2").val();
			var connectBKey2 = $("#connectBKey2").val();
			var connectVal2 = $("#connectVal2").val();
			if(edit){
				database = edit.dataBase;
				tableName = edit.tableName;
				column1 = edit.column1;
				columnB1 = edit.columnB1;
				listVal = edit.listVal;
				connectKey1 = edit.connectKey1;
				connectBKey1 = edit.connectBKey1;
				connectVal1 = edit.connectVal1;
				connectKey2 = edit.connectKey2;
				connectBKey2 = edit.connectBKey2;
				connectVal2 = edit.connectVal2;
				ed = "T";
			}
			$.ajax({
				url: $("#ctx").attr("value") + '/moduleThree/getCheckResult',
				type: 'post',
				data: {	
						checkMethod : checkMethod,
						database : database,
						tableName : tableName,
						column1 : column1,
						columnB1 : columnB1,
						listVal : listVal,
						connectKey1 : connectKey1,
						connectBKey1 : connectBKey1,
						connectVal1 : connectVal1,
						connectKey2 : connectKey2,
						connectBKey2 : connectBKey2,
						connectVal2 : connectVal2,
						method : method,
						ed : ed,
					},
				dataType: 'json',
				success: function (data, status) {
  				  	if(data.code=='200'){
  					  	top.layer.msg("获取成功！");
  						var title = "校对结果";
      		        	$(".out").val(data.out); 
      			    	
      			    	$("#remarkRecord").dialog({  
      			            //autoOpen : false,   // 是否自动弹出窗口  
      			            modal : true,    // 设置为模态对话框  
      			            resizable : true,  
      			            width : 700,   //弹出框宽度  
      			            height : 550,   //弹出框高度  
      			            title : title,  //弹出框标题  
      			            position: { my: "center top", at: "center top", of: window },
      			            closeText : '关闭',
      			            buttons:{  
      				            '关闭':function(){  
      				 	        	$(".out").val(""); 
      				            	$(this).dialog("close");  
      				            }  
      			            } 
      			       });
  				  	}else{
  					  	top.layer.msg("校对数据"+data.msg);
  				  	}
  			  	},
  			  	fail: function (err, status) {
					alert(status);
  			  	}
 			})
 			if(!edit){
 	    		tableIns.reload();
			}
		}
		//搜索【此功能需要后台配合，所以暂时没有动态效果演示】
	    $(".search_btn").on("click",function(){
	    	var checkMethod = $("#checkMethod").val();
			var method;
        	if(checkMethod == "qc_btn1"){
        		method = "检查";
			}else if(checkMethod == "qc_btn2"){
        		method = "匹配";
			}else if(checkMethod == "qc_btn3"){
        		method = "包含";
			}
			var listVal = $("#listVal").val();
			var column1 = $("#tableColumn1").val();
			var database = $("#databaseName").val();
			var tableName = $("#tableName").val();
			var columnB1 = $("#tableColumnB1").val();
			
	    	table.reload("filedListTable",{
	    		page: {
	    			curr: 1 //重新从第 1 页开始
	            },
	            url: $("#ctx").attr("value") + '/moduleThree/getList.ht',
	            method: 'post',
	            where: {
					database : database,
					tableName : tableName,
					columnB1 : columnB1,
					listVal : listVal,
					column1 : column1,
					method : method,
	            }
	         })
	    });
		//校对
        $(".qc_btn").on("click",function(){
        	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
        		return;
        	}else if($("#listVal").val() == ""){
        		top.layer.msg("请选择查询条件！");
        		return;
        	}else if($("#databaseName").val() == ""){
        		top.layer.msg("请选择数据库！");
        		return;
        	}else if($("#tableName").val() == ""){
        		top.layer.msg("请选择数据库表格！");
        		return;
        	}else if($('#tableColumn1').val() == ""){
        		top.layer.msg("请选择校对列！");
        		return;
        	}else if($('#tableColumnB1').val() == ""){
        		top.layer.msg("请选择被校对列！");
        		return;
        	}else if($("#checkMethod").val() == "qc_btn3" && $('#connectKey1').val() == ""){
        		top.layer.msg("请选择匹配条件列1！");
        		return;
        	}else if($("#checkMethod").val() == "qc_btn3" && $('#connectVal1').val() == ""){
        		top.layer.msg("请选择匹配条件值1！");
        		return;
        	}else{
        		layer.confirm('确定提交校对参数吗？',{icon:3, title:'提示信息'},function(index){
            		getCheckResult();
            		layer.close(index);
                });
        	}
        });
		//批量校对
	    $(".qcAll_btn").click(function(){
	    	var checkMethod = $("#checkMethod").val();
	    	if(checkMethod == ""){
        		top.layer.msg("请选择校对方式！");
        		return;
        	}
	    	
	    	var checkStatus = table.checkStatus('filedListTable'),
	            data = checkStatus.data,
	            ids = [];
	    		dataBases = [];
		    	tableNames = [];
		    	column1s = [];
				columnB1s = [];
				listVals = [];
				connectKey1s = [];
				connectBKey1s = [];
				connectVal1s = [];
				connectKey2s = [];
				connectBKey2s = [];
				connectVal2s = [];
	        if(data.length > 0) {
	            for (var i in data) {
	            	ids.push(data[i].id);
	            	dataBases.push(data[i].dataBase);
	            	tableNames.push(data[i].tableName);
	            	column1s.push(data[i].column1);
					columnB1s.push(data[i].columnB1);
					listVals.push(data[i].listVal);
					connectKey1s.push(data[i].connectKey1==""?"O":data[i].connectKey1);
					connectBKey1s.push(data[i].connectBKey1==""?"O":data[i].connectBKey1);
					connectVal1s.push(data[i].connectVal1==""?"O":data[i].connectVal1);
					connectKey2s.push(data[i].connectKey2==""?"O":data[i].connectKey2);
					connectBKey2s.push(data[i].connectBKey2==""?"O":data[i].connectBKey2);
					connectVal2s.push(data[i].connectVal2==""?"O":data[i].connectVal2);
	            }
	            layer.confirm('确定批量校对选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
	            	$.ajax({
	    				url: $("#ctx").attr("value") + '/moduleThree/getCheckResults',
	    				type: 'post',
	    				data: {	
	    						checkMethod : checkMethod,
	    						database : dataBases.toString(),
		                    	tableName : tableNames.toString(),
		                    	column1 : column1s.toString(),
		        				columnB1 : columnB1s.toString(),
			    				listVal : listVals.toString(),
			    				connectKey1 : connectKey1s.toString(),
			    				connectBKey1 : connectBKey1s.toString(),
			    				connectVal1 : connectVal1s.toString(),
			    				connectKey2 : connectKey2s.toString(),
			    				connectBKey2 : connectBKey2s.toString(),
			    				connectVal2 : connectVal2s.toString(),
	    					},
	    				dataType: 'json',
	    				success: function (data, status) {
	      				  	if(data.code=='200'){
	      					  	top.layer.msg("获取成功！");
	      						var title = "校对结果";
	          		        	$(".out").val(data.out); 
	          			    	
	          			    	$("#remarkRecord").dialog({  
	          			            //autoOpen : false,   // 是否自动弹出窗口  
	          			            modal : true,    // 设置为模态对话框  
	          			            resizable : true,  
	          			            width : 700,   //弹出框宽度  
	          			            height : 550,   //弹出框高度  
	          			            title : title,  //弹出框标题  
	          			            position: { my: "center top", at: "center top", of: window },
	          			            closeText : '关闭',
	          			            buttons:{  
	          				            '关闭':function(){  
	          				 	        	$(".out").val(""); 
	          				            	$(this).dialog("close");  
	          				            }  
	          			            } 
	          			       });
	      				  	}else{
	      					  	top.layer.msg("获取失败！");
	      				  	}
	      			  	},
	      			  	fail: function (err, status) {
	    					alert(status);
	      			  	}
	     			})
            		layer.close(index);
	            })
	        }else{
	            layer.msg("请选择需要校对的记录");
	        }
	    })
	   
		//列表操作
		table.on('tool(filedList)', function(obj){
            var layEvent = obj.event;
                data = obj.data;
            if(layEvent === 'edit'){ //编辑
            	if($("#checkMethod").val() == ""){
            		top.layer.msg("请选择校对方式！");
            		return;
            	}
            	getCheckResult(data);
            }else if(layEvent === 'del'){ //删除
            	layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                    $.get($("#ctx").attr("value") + "/moduleThree/delRecord.ht",{
                    	id : data.id
                    },function(data){
                    	top.layer.msg("删除成功！");
                    	tableIns.reload();
                        layer.close(index);
                    })
                });
            }
        });
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
	                $.get($("#ctx").attr("value") + "/moduleThree/delAllRecord.ht",{
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
	    
    })
    
</script>
</body>
</html>