<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块一</title>
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
<form class="layui-form" lay-filter="selFilter">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				校对方式
				<div class="layui-input-inline" style="width:150px;">
					<select class="checkMethod" lay-filter="checkMethod" id="checkMethod">
						<option value=''>请选择</option>
						<option value='qc_btn1'>一对多</option>
						<option value='qc_btn2'>一致性</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				数据库
				<div class="layui-input-inline" style="width:150px;">
					<select class="databaseName" lay-filter="databaseName" lay-search="" id="databaseName">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				数据表
				<div class="layui-input-inline" style="width:150px;">
					<select class="tableName" lay-filter="tableName" lay-search="" id="tableName">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				校对列
				<div class="layui-input-inline" style="width:150px;">
					<select class="tableColumn1" lay-filter="tableColumn1" lay-search="" id="tableColumn1">
					</select>
				</div>
			</div>
			<div class="layui-input-inline">
				被校对列
				<div class="layui-input-inline" style="width:150px;">
					<select class="tableColumn2" lay-filter="tableColumn2" lay-search="" id="tableColumn2">
					</select>
				</div>
			</div>
			<a class="layui-btn search_btn" data-type="reload">搜索</a>
			<a class="layui-btn qc_btn" data-type="reload">校对</a>
			<a class="layui-btn layui-btn-normal qcAll_btn" data-type="reload">批量校对</a>
			<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量删除</a>
		</blockquote>
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
    	
    	//初始加载数据库
    	getDatabase();
        
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
        	var checkMethod = $("#checkMethod").val();
        	var method;
        	if(checkMethod == "qc_btn1"){
        		method = "一对多";
        	}else if(checkMethod == "qc_btn2"){
        		method = "一致性";
        	}
        	table.reload("filedListTable",{
                url: $("#ctx").attr("value") + '/moduleOne/getList.ht',
                method: 'post',
                where: {
                	method : method
                }
             })
        });
		//数据库databaseName select change
        form.on('select(databaseName)', function(data){
        	$("#tableName").val("");
        	$('#tableColumn1').val("");
        	$('#tableColumn2').val("");
        	//var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var database = data.value;
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
            form.render();
        });
		
		//表 tableName select change
        form.on('select(tableName)', function(data){
        	$('#tableColumn1').val("");
        	$('#tableColumn2').val("");
        	//var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var tableName = data.value;
        	var databaseName = $("#databaseName").val();
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
    					$('#tableColumn1').html('');
    					$("#tableColumn1").append(remarks);
    					$('#tableColumn2').html('');
    					$("#tableColumn2").append(remarks);
    					form.render();//表单重新渲染，要不然添加完显示不出来新的
    				}else{
    					top.layer.msg("表字段获取失败！");
    				}
    			},
    			fail: function (err, status) {
    				alert(status);
    			}
   			})
            form.render();
        });
		
        //替换字段列表
         var tableIns = table.render({
        	 elem: '#filedList',
             url :$("#ctx").attr("value") + '/moduleOne/getList.ht',
             cellMinWidth : 95,
             page : false,
             height : "full-95",
             limits : [10,15,20,25],
             limit : 10,
             id : "filedListTable",
             cols : [[
                 {type: 'numbers', width:30, fixed: 'left'},
                 {type: 'checkbox', fixed: 'left'},
                // {field: 'ipPort', title: 'ip_port', align:"center", minWidth:180},
                 {field: 'dataBase', title: '数据库', align:'center',minWidth:100},
                 {field: 'tableName', title: '数据表', align:'center',minWidth:100},
                 {field: 'column1', title: '校对列', align:'center',minWidth:120},
                 {field: 'column2', title: '被校对列', align:'center',minWidth:100},
                 {field: 'method', title: '校对方式', align:'center',minWidth:100},
                 {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
             ]]
        });
		function getCheckResult(edit){
			var checkMethod = $("#checkMethod").val();
			var method;
        	if(checkMethod == "qc_btn1"){
        		method = "一对多";
			}else if(checkMethod == "qc_btn2"){
        		method = "一致性";
			}
			var database = $("#databaseName").val();
			var tableName = $("#tableName").val();
			var column1 = $("#tableColumn1").val();
			var column2 = $("#tableColumn2").val();
			if(edit){
				database = edit.dataBase;
				tableName = edit.tableName;
				column1 = edit.column1;
				column2 = edit.column2;
			}
			$.ajax({
				url: $("#ctx").attr("value") + '/moduleOne/getCheckResult',
				type: 'post',
				data: {	
						checkMethod : checkMethod,	
						database : database,
						tableName : tableName,
						column1 : column1,
						column2 : column2,
						method : method,
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
  					  	top.layer.msg("校对数据失败！");
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
        		method = "一对多";
			}else if(checkMethod == "qc_btn2"){
        		method = "一致性";
			}
			var database = $("#databaseName").val();
			var tableName = $("#tableName").val();
			var column1 = $("#tableColumn1").val();
			var column2 = $("#tableColumn2").val();
			
	    	table.reload("filedListTable",{
	    		page: {
	    			curr: 1 //重新从第 1 页开始
	            },
	            url: $("#ctx").attr("value") + '/moduleOne/getList.ht',
	            method: 'post',
	            where: {
					database : database,
					tableName : tableName,
					column1 : column1,
					column2 : column2,
					method : method,
	            }
	         })
	    });
       //校对
        $(".qc_btn").on("click",function(){
        	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
        		return;
        	}else if($("#databaseName").val() == ""){
        		top.layer.msg("请选择数据库！");
        		return;
        	}else if($("#tableName").val() == ""){
        		top.layer.msg("请选择数据库表！");
        		return;
        	}else if($('#tableColumn1').val() == "" || $('#tableColumn2').val() == ""){
        		top.layer.msg("请选择数据库表校对字段！");
        		return;
        	}else if($('#tableColumn1').val() == $('#tableColumn2').val()){
        		top.layer.msg("校对字段不能一样，请重新选择！");
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
	    	if($("#checkMethod").val() == ""){
        		top.layer.msg("请选择校对方式！");
        		return;
        	}
	    	var checkStatus = table.checkStatus('filedListTable'),
	            data = checkStatus.data,
	            ids = [];
	    		dataBases = [];
		    	tableNames = [];
		    	column1s = [];
		    	column2s = [];
	        if(data.length > 0) {
	            for (var i in data) {
	            	ids.push(data[i].id);
	            	dataBases.push(data[i].dataBase);
	            	tableNames.push(data[i].tableName);
	            	column1s.push(data[i].column1);
	            	column2s.push(data[i].column2);
	            }
	            layer.confirm('确定批量校对选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
	            	$.ajax({
	    				url: $("#ctx").attr("value") + '/moduleOne/getCheckResults',
	    				type: 'post',
	    				data: {	
    							checkMethod : $("#checkMethod").val(),
	    						database : dataBases.toString(),
		                    	tableName : tableNames.toString(),
		                    	column1 : column1s.toString(),
		                    	column2 : column2s.toString(),
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
	      					  	top.layer.msg("批量校对失败！");
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
                    $.get($("#ctx").attr("value") + "/moduleOne/delRecord.ht",{
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
	                $.get($("#ctx").attr("value") + "/moduleOne/delAllRecord.ht",{
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