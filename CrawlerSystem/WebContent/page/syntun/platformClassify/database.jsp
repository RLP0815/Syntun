<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据库操作</title>
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
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
					<div class="layui-input-inline">
							<label class="layui-form-label">数据库1</label>
							<div class="layui-input-inline">
								<select class="databaseName" lay-filter="databaseName1" id="databaseName1">
								</select>
							</div>
					</div>
					<div class="layui-input-inline">
							<label class="layui-form-label">数据库2</label>
							<div class="layui-input-inline">
								<select class="databaseName" lay-filter="databaseName2" id="databaseName2">
								</select>
							</div>
					</div>
					<a class="layui-btn search_btn" data-type="reload">查询</a>
				</div>
			</div>	
			<div class="layui-inline">
				<div class="demoTable">
					<div class="layui-input-inline">
							<label class="layui-form-label">数据库表1</label>
							<div class="layui-input-inline">
								<select class="tableName" lay-filter="tableName" id="tableName">
								</select>
							</div>
					</div>
					<div class="layui-input-inline">
							<label class="layui-form-label">数据库表2</label>
							<div class="layui-input-inline">
								<select class="tableName2" lay-filter="tableName2" id="tableName2">
								</select>
							</div>
					</div>
				</div>
			</div>
			<div class="layui-inline" style="width: 100%">
				<div class="demoTable">
					<div class="layui-input-inline" style="width: 100%">
							<label class="layui-form-label">表字段1</label>
							<div class="layui-input-inline" style="width: 90%">
								<input type="text" class="layui-input tableColumn" id="tableColumn" placeholder="" />
							</div>
					</div>
				</div>
			</div>
			<div class="layui-inline" style="width: 100%">
				<div class="demoTable">
					<div class="layui-input-inline" style="width: 100%">
							<label class="layui-form-label">表字段2</label>
							<div class="layui-input-inline" style="width: 90%">
								<input type="text" class="layui-input tableColumn2" id="tableColumn2" placeholder="" />
							</div>
					</div>
				</div>
			</div>
			<div class="layui-inline" style="width: 100%">
				<div class="demoTable">
					<div class="layui-input-inline">
							<label class="layui-form-label">关联条件</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input relationid" id="relationid" placeholder="请输入关联条件" />
							</div>
					</div>
					<div class="layui-input-inline" style="width: 65%">
							<label class="layui-form-label">where条件</label>
							<div class="layui-input-inline" style="width: 83%">
								<input type="text" class="layui-input whereid" id="whereid" placeholder="请输入where条件" />
							</div>
					</div>
					<a class="layui-btn gosql" id="gosql" data-type="reload">生成sql</a>
				</div>
			</div>
			<div class="layui-inline" style="width: 100%">
				<div class="demoTable">
					<div class="layui-input-inline" style="width: 100%">
							<label class="layui-form-label">SQL</label>
							<div class="layui-input-inline" style="width: 90%">
								<textarea class="layui-textarea" id="textareaSql"></textarea>
							</div>
					</div>
				</div>
			</div>
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
</div> 
</form>
<div id="fileDiv" style="display:none;">
	<form class="layui-form" style="width:90%;">
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">url</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="url" lay-verify="required" placeholder="请输入url">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">分类标签</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="classifysTag" lay-verify="required" placeholder="请输入分类标签">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<div class="layui-input-block">
				<button class="layui-btn layui-btn-sm saveClass" lay-submit="" lay-filter="saveClass">保存</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript">
    $(function() {
		//初始化数据方法
    });
    layui.use(['element','form','layer','table','laytpl'],function(){
    	
    	var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer,
            $ = layui.jquery,
            laytpl = layui.laytpl,
            table = layui.table;
        //替换字段列表
         var tableIns = table.render({
            elem: '#filedList',
            url :$("#ctx").attr("value") + '/platformClassify/getDataProcessingSqlInfo',
            cellMinWidth : 95,
            page : true,
            height : "full-68",
            limits : [10,15,20,25],
            limit : 10,
            id : "filedListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'sqlname', title: '历史生成的SQL语句', align:"center", minWidth:200},
/*                 {field: 'databasename1', title: '库名1', align:'center',minWidth:200},
                {field: 'databasename2', title: '库名2', align:'center',minWidth:200},
                {field: 'tablename1', title: '表名1', align:'center',minWidth:200},
                {field: 'tablename2', title: '表名2', align:'center',minWidth:200},
                {field: 'tablefield1', title: '表字段1', align:'center',minWidth:200},
                {field: 'tablefield2', title: '表字段2', align:'center',minWidth:200},
                {field: 'relationcondition', title: '关联条件', align:'center',minWidth:200},
                {field: 'wherecondition', title: 'where条件', align:'center',minWidth:200},
                {field: 'createtime', title: '创建时间', align:'center',minWidth:200,sort:true}, */
            ]]
        });

        //查询按钮事件获取库名列表
        $(".search_btn").on("click",function(){
        	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       	  	$.ajax({
    	  		  url: $("#ctx").attr("value") + '/platformClassify/getDataBaseName',
    			  type: 'post',
    			  dataType: 'json',
    			  success: function (data, status) {
    				  if(data.code=='200'){
    					  top.layer.msg("获取成功！");
    					    var remarks = '<option value="">请选择</option>';
    						for (var i = 0; i < data.list.length; i++) {
    			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    						}
    						$('.databaseName').html('');
    						$(".databaseName").append(remarks);
    		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
        });
        function renderForm() {
            layui.use(['form'], function () {
                $ = layui.jquery;
                var form = layui.form;
                form.render(); //刷新select选择框渲染
            });
        }
        //databaseName1 select change
        form.on('select(databaseName1)', function(data){
        	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
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
    					  top.layer.msg("获取成功！");
    					    var remarks = '<option value="">请选择</option>';
    						for (var i = 0; i < data.list.length; i++) {
    			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    						}
    						$('#tableName').html('');
    						$("#tableName").append(remarks);
    		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
            form.render();
        });
        //databaseName2 select change
        form.on('select(databaseName2)', function(data){
        	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
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
    					  top.layer.msg("获取成功！");
    					    var remarks = '<option value="">请选择</option>';
    						for (var i = 0; i < data.list.length; i++) {
    			  		    	remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    						}
    						$('#tableName2').html('');
    						$("#tableName2").append(remarks);
    		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
            form.render();
        });
        //tableName1 select change
        form.on('select(tableName)', function(data){
        	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var tableName = data.value;
        	var databaseName1 = $("#databaseName1").val();
         	$.ajax({
    	  		  url: $("#ctx").attr("value") + '/platformClassify/getColumnNames',
    			  type: 'post',
      			  data: {
      				  "tableName":tableName,
      				  "database":databaseName1,
      				  "flag":"table1",
      			  },
    			  dataType: 'json',
    			  success: function (data, status) {
    				  if(data.code=='200'){
    					  top.layer.msg("获取成功！");
    						$('#tableColumn').val(data.list);
    		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
            form.render();
        });
        //tableName2 select change
        form.on('select(tableName2)', function(data){
        	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var tableName = data.value;
        	var databaseName2 = $("#databaseName2").val();
         	$.ajax({
    	  		  url: $("#ctx").attr("value") + '/platformClassify/getColumnNames',
    			  type: 'post',
      			  data: {
      				  "tableName":tableName,
      				  "database":databaseName2,
      			  },
    			  dataType: 'json',
    			  success: function (data, status) {
    				  if(data.code=='200'){
    					  top.layer.msg("获取成功！");
    					  $('#tableColumn2').val(data.list);
    		                renderForm();//表单重新渲染，要不然添加完显示不出来新的
    				  }else{
    					  top.layer.msg("获取失败！");
    				  }
    			  },
    			  fail: function (err, status) {
    				  alert(status);
    			  }
   			})
            form.render();
        });
        //生成sql
        $(".gosql").click(function(){
        	var databaseName1 = $("#databaseName1").val();
        	var databaseName2 = $("#databaseName2").val();
        	var tableName = $("#tableName").val();
        	if(tableName==null || tableName==''){
        		 top.layer.msg("表名不能为空！");
        		 return false;
        	}
        	var tableName2 = $("#tableName2").val();
        	if(tableName2==null || tableName2==''){
       		 	top.layer.msg("表名不能为空！");
       			return false;
       		}
        	var tableColumn = $("#tableColumn").val();
        	if(tableColumn==null || tableColumn==''){
          		 top.layer.msg("字段不能为空！");
          		 return false;
        	}
        	var tableColumn2 = $("#tableColumn2").val();
        	if(tableColumn2==null || tableColumn2==''){
         		 top.layer.msg("字段不能为空！");
         		 return false;
       		}
        	var relationid = $("#relationid").val();
        	if(relationid==null || relationid==''){
        		 top.layer.msg("关联条件不能为空！");
        		 return false;
      		}
        	var whereid = $("#whereid").val();//where条件
         	$.ajax({
   	  		  url: $("#ctx").attr("value") + '/platformClassify/generateSql',
   			  type: 'post',
   			  data: {
   				  "databaseName1":databaseName1,
   				  "databaseName2":databaseName2,
   				  "tableName":tableName,
   				  "tableName2":tableName2,
   				  "tableColumn":tableColumn,
   				  "tableColumn2":tableColumn2,
   				  "relationid":relationid,
   				  "whereid":whereid,
   			  },
   			  dataType: 'json',
   			  success: function (data, status) {
   				  if(data.code=='200'){
   					  top.layer.msg("保存成功！");
   					  $("#textareaSql").val(data.sql);
   				  }else{
   					  top.layer.msg("操作失败！");
   				  }
   			  },
   			  fail: function (err, status) {
   				  alert(status);
   			  }
   			})
        })
        $(".add_btn").click(function(){
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
        })
        //保存
        form.on("submit(saveClass)",function(){
        	//弹出loading
            var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
      		$.ajax({
     		  url: $("#ctx").attr("value") + '/platformClassify/platformClassifyAdd',
     		  type: 'post',
     		  data: {
     			  "url" : $("#url").val(), 
     	    	  "classifysTag" : $("#classifysTag").val(),
     	    	  "classifyTag" : $("#classifyTag").val(),
     	    	  "oneClassifyName" : $("#oneClassifyName").val(),
     	    	  "twoClassifyTag" : $("#twoClassifyTag").val(),  
     	    	  "twoClassifyName" : $("#twoClassifyName").val(),  
     	    	  "threeClassifyTag" : $("#threeClassifyTag").val(),
     	    	  "threeClassifyName" : $("#threeClassifyName").val(),  
     	    	  "threeClassifyUrl" : $("#threeClassifyUrl").val(),  
     		  },
    		  async:false,
    		  dataType: 'json',
    		  timeout: 2000,
     		  success: function (data, status) {
     			  if(data.code=='200'){
     				  top.layer.msg("添加成功！");
     			  }else{
     				  top.layer.msg("添加失败！");
     			  }
     		  },
     		  fail: function (err, status) {
     			  alert(status);
     		  }
         	})
         	return false;
        });
    })
</script>
</body>
</html>