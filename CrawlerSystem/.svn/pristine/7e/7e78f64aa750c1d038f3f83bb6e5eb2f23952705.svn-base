<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台分类</title>
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
						<input type="text" class="layui-input platformid" placeholder="请输入平台编号" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input platformname" placeholder="请输入平台名称" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input onecategoryname" placeholder="请输入一级分类名称" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input twocategoryname" placeholder="请输入二级分类名称" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input threecategoryname" placeholder="请输入三级分类名称" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">查询</a>
					<a class="layui-btn add_btn" data-type="reload">新增</a>
					<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn" data-type="reload">批量操作</a>
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
		<label class="layui-form-label">类型</label>
		<div class="layui-input-inline">
			<select id="platformFlag" class="" lay-filter="">
			    <option value="1">淘宝</option>
				<option value="2">京东</option>
				<option value="3">国美</option>
				<option value="4">苏宁</option>
			</select>
		</div>
		</div>
<!-- 		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">分类标签</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="classifysTag" lay-verify="required" placeholder="请输入分类标签">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">子分类标签</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="classifyTag" lay-verify="required" placeholder="请输入子分类标签">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">一级分类名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="oneClassifyName" lay-verify="required" placeholder="请输入一级分类名称">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">二级分类标签</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="twoClassifyTag" lay-verify="required" placeholder="请输入二级分类标签">
			</div>
		</div>
		<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">二级分类名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="twoClassifyName" lay-verify="required" placeholder="请输入二级分类名称">
			</div>
		</div>
			<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">三级分类标签</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="threeClassifyTag" lay-verify="required" placeholder="请输入三级分类标签">
			</div>
		</div>
				<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">三级分类名称</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="threeClassifyName" lay-verify="required" placeholder="请输入三级分类名称">
			</div>
		</div>
					<div class="layui-form-item layui-row layui-col-xs12">
			<label class="layui-form-label">三级分类Url</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input oldFiled" id="threeClassifyUrl" lay-verify="required" placeholder="请输入三级分类Url">
			</div>
		</div> -->
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
            url :$("#ctx").attr("value") + '/platformClassify/getPlatformClassifyInfo',
            cellMinWidth : 95,
            page : true,
            height : "full-68",
            limits : [10,15,20,25],
            limit : 10,
            id : "filedListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {type: 'checkbox', fixed: 'left'},
                {field: 'platformid', title: '平台编号', align:"center", minWidth:200},
                {field: 'platformname', title: '平台名称', align:'center',minWidth:200},
                {field: 'onecategoryid', title: '一级分类id', align:'center',minWidth:200},
                {field: 'onecategoryname', title: '一级分类名称', align:'center',minWidth:200},
                {field: 'twocategoryid', title: '二级分类id', align:'center',minWidth:200},
                {field: 'twocategoryname', title: '二级分类名称', align:'center',minWidth:200},
                {field: 'threecategoryid', title: '三级分类id', align:'center',minWidth:200},
                {field: 'threecategoryname', title: '三级分类名称', align:'center',minWidth:200},
                {field: 'threecategoryurl', title: '三级分类URL', align:'center',minWidth:200},
                {field: 'createtime', title: '创建时间', align:'center',minWidth:200,sort:true},
            ]]
        });

        //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	table.reload("filedListTable",{
        		page: {
        			curr: 1 //重新从第 1 页开始
                },
                url: $("#ctx").attr("value") + '/platformClassify/getPlatformClassifyInfo',
                method: 'post',
                where: {
                	platformid : $(".platformid").val(),
                	platformname : $(".platformname").val(),
                	onecategoryname : $(".onecategoryname").val(),
                	twocategoryname : $(".twocategoryname").val(),
                	threecategoryname : $(".threecategoryname").val(),
                }
             })
        });
        //批量操作
        $(".delAll_btn").click(function(){
        	var checkStatus = table.checkStatus('filedListTable'),
                data = checkStatus.data;
        	//console.log(JSON.stringify(data))//选中的数据
            if(data.length > 0) {
           	  	$.ajax({
      	  		  url: $("#ctx").attr("value") + '/platformClassify/addInitUrlList',
      			  type: 'post',
      			  data: {
      				  "platformClassifyInfo":JSON.stringify(data),
      			  },
      			  dataType: 'json',
      			  success: function (data, status) {
      				  if(data.code=='200'){
      					  top.layer.msg("保存成功！");
      				  }else{
      					  top.layer.msg("操作失败！");
      				  }
      			  },
      			  fail: function (err, status) {
      				  alert(status);
      			  }
      			})
            }else{
                layer.msg("请选择需要的记录！");
            }
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
     	    	  "platformFlag" : $("#platformFlag").val()
     		  },
    		  dataType: 'json',
     		  success: function (data, status) {
     			  if(data.code=='200'){
     				  top.layer.msg("添加成功！");
     			  }else{
     				  top.layer.msg("添加失败！");
     			  }
     			 $(".saveClass").dialog("close"); 
     		  },
     		  fail: function (err, status) {
     			 top.layer.msg("添加失败！");
     			  alert(status);
     		  }
         	})
         	return false;
        });
    })
</script>
</body>
</html>