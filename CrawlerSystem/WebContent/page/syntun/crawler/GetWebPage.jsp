<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>获取网页代码</title>
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
<%-- 	var authorities = '<%=authorities%>'; --%>
</script>

<body>
	<input id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
		<legend>获取网页所需参数</legend>
	</fieldset>
	<form class="layui-form" style="width:99%;">
		<div class="layui-form-item">
		    <label class="layui-form-label">URL</label>
		    <div class="layui-input-block">
				<input type="text" class="layui-input url" lay-verify="required" autocomplete="off" placeholder="请输入URL">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">父URL</label>
		    <div class="layui-input-block">
				<input type="text" class="layui-input urlParent" lay-verify="" autocomplete="off" placeholder="请输入父URL">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">cookie</label>
		    <div class="layui-input-block">
				<input type="text" class="layui-input cookie" lay-verify="" autocomplete="off" placeholder="请输入Cookie">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">userAgent</label>
		    <div class="layui-input-block">
				<input type="text" class="layui-input userAgent" lay-verify="" autocomplete="off" placeholder="请输入UserAgent">
		    </div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md6">
			    <label class="layui-form-label">pattern1</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input patternStr1" lay-verify="required" autocomplete="off" placeholder="请输入patternStr1">
			    </div>
			</div>
			<div class="layui-col-md6">
			    <label class="layui-form-label">group1</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input group1" lay-verify="required" autocomplete="off" placeholder="请输入group1(多个用逗号“,”隔开)">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="magb15 layui-col-md6 layui-col-xs12">
			    <label class="layui-form-label">pattern2</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input patternStr2" lay-verify="required" autocomplete="off" placeholder="请输入patternStr2">
			    </div>
			</div>
			<div class="magb15 layui-col-md6 layui-col-xs12">
			    <label class="layui-form-label">group2</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input group2" lay-verify="required" autocomplete="off" placeholder="请输入group2(多个用逗号“,”隔开)">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md4">
	    		<label class="layui-form-label">提交方式</label>
	   			<div class="layui-input-block" id="IsPurchased">
	     			<input type="radio" name="sex" value="GET" title="GET" checked="">
	     			<input type="radio" name="sex" value="POST" title="POST">
	   			</div>
   			</div>
   			<div class="layui-col-md2">
   				<a class="layui-btn layui-btn-normal selectKeyValue">选择</a>
   			</div>
  		</div>
		<div class="layui-form-item">
			<div class="layui-col-md2">
			    <label class="layui-form-label">key1</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key1" placeholder="请输入key1">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value1</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value1" placeholder="请输入value1">
			    </div>
			</div>
			<div class="layui-col-md2">
			    <label class="layui-form-label">key2</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key2" placeholder="请输入key2">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value2</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value2" placeholder="请输入value2">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md2">
			    <label class="layui-form-label">key3</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key3" placeholder="请输入key3">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value3</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value3" placeholder="请输入value3">
			    </div>
			</div>
			<div class="layui-col-md2">
			    <label class="layui-form-label">key4</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key4" placeholder="请输入key4">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value4</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value4" placeholder="请输入value4">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md2">
			    <label class="layui-form-label">key5</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key5" placeholder="请输入key5">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value5</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value5" placeholder="请输入value5">
			    </div>
			</div>
			<div class="layui-col-md2">
			    <label class="layui-form-label">key6</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key6" placeholder="请输入key6">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value6</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value6" placeholder="请输入value6">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md2">
			    <label class="layui-form-label">key7</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key7" placeholder="请输入key7">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value7</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value7" placeholder="请输入value7">
			    </div>
			</div>
			<div class="layui-col-md2">
			    <label class="layui-form-label">key8</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key8" placeholder="请输入key8">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value8</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value8" placeholder="请输入value8">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-col-md2">
			    <label class="layui-form-label">key9</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key9" placeholder="请输入key9">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value9</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value9" placeholder="请输入value9">
			    </div>
			</div>
			<div class="layui-col-md2">
			    <label class="layui-form-label">key10</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input key10" placeholder="请输入key10">
			    </div>
			</div>
			<div class="layui-col-md4">
			    <label class="layui-form-label">value10</label>
			    <div class="layui-input-block">
					<input type="text" class="layui-input value10" placeholder="请输入value10">
			    </div>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn urlSubmit" lay-submit="" lay-filter="urlSubmit">提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>
	<div id="remarkRecord" style="display:none;">
		<div class="layui-row">
			<div class="magb15 layui-col-md6 layui-col-xs12">
				<label class="layui-form-label">pattern1</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input pattern1" lay-verify="required" placeholder="请选择平台">
				</div>
			</div>
			<div class="magb15 layui-col-md6 layui-col-xs12">
				<label class="layui-form-label">pattern2</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input pattern2" lay-verify="required" placeholder="请选择平台">
				</div>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">采集结果</label>
		    <div class="layui-input-block">
		      <textarea placeholder="请输入内容" style="height:360px;" class="layui-textarea out"></textarea>
		    </div>
		</div>
	</div>
	
	<div id="keyValue" style="display:none;">
		<table id="keyValueList" lay-filter="keyValueList"></table>
		<script type="text/html" id="keyValueListBar">
			<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
		</script>
	</div>
	
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript">
layui.use(['form','layer','table','laytpl'],function(){

	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laytpl = layui.laytpl;
	
	//选择Key-value
    $(".selectKeyValue").click(function(){
    	var tablePlad = table.render({
            elem: '#keyValueList',	
            url :$("#ctx").attr("value") + '/filterUrl/getKeyValueList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-220",
            limits : [10,15,20,25],
            limit : 10,
            id : "keyValueListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'urlGroup', title: 'key', align:"center", width:180},
                {field: 'sortId', title: 'value', align:"center", width:290},
                {title: '操作', width:60, templet:'#keyValueListBar',fixed:"right",align:"center"}
            ]]
        });
    	$("#keyValue").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 500,   //弹出框高度  
            title : "key-value选择",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	           /* '确定':function(){  
	            //调用登录的方法  
	            }, */ 
	            '取消':function(){  
	            	//$('.platformId').val("");
	            	//$('.platformName').val("")
	            	$(this).dialog("close");  
	            }  
            }  
       });  
    })
    //选择操作
    table.on('tool(keyValueList)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	if($(".key1").val() == ""){
    		$(".key1").val(data.urlGroup);
    		$(".value1").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key2").val() == ""){
    		$(".key2").val(data.urlGroup);
    		$(".value2").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key3").val() == ""){
    		$(".key3").val(data.urlGroup);
    		$(".value3").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key4").val() == ""){
    		$(".key4").val(data.urlGroup);
    		$(".value4").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key5").val() == ""){
    		$(".key5").val(data.urlGroup);
    		$(".value5").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key6").val() == ""){
    		$(".key6").val(data.urlGroup);
    		$(".value6").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key7").val() == ""){
    		$(".key7").val(data.urlGroup);
    		$(".value7").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key8").val() == ""){
    		$(".key8").val(data.urlGroup);
    		$(".value8").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key9").val() == ""){
    		$(".key9").val(data.urlGroup);
    		$(".value9").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	} else if($(".key10").val() == ""){
    		$(".key10").val(data.urlGroup);
    		$(".value10").val(data.sortId);
        	$("#keyValue").dialog("close"); 
        	return;
    	}else {
    		top.layer.msg("十个key-value已选择满，请先删除！");
        	$("#keyValue").dialog("close"); 
        	return;
    	}
    });
    
	//立即提交
	form.on("submit(urlSubmit)",function(data){
		var type = $('#IsPurchased input[name="sex"]:checked').val();
		var requestProperty = "";
		if($(".key1").val()!="" && $(".value1").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key1").val() + "@_@" + $(".value1").val();
			}else{
				requestProperty += "#_#" + $(".key1").val() + "@_@" + $(".value1").val();
			}
		}
		if($(".key2").val()!="" && $(".value2").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key2").val() + "@_@" + $(".value2").val();
			}else{
				requestProperty += "#_#" + $(".key2").val() + "@_@" + $(".value2").val();
			}
		}
		if($(".key3").val()!="" && $(".value3").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key3").val() + "@_@" + $(".value3").val();
			}else{
				requestProperty += "#_#" + $(".key3").val() + "@_@" + $(".value3").val();
			}
		}
		if($(".key4").val()!="" && $(".value4").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key4").val() + "@_@" + $(".value4").val();
			}else{
				requestProperty += "#_#" + $(".key4").val() + "@_@" + $(".value4").val();
			}
		}
		if($(".key5").val()!="" && $(".value5").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key5").val() + "@_@" + $(".value5").val();
			}else{
				requestProperty += "#_#" + $(".key5").val() + "@_@" + $(".value5").val();
			}
		}
		if($(".key6").val()!="" && $(".value6").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key6").val() + "@_@" + $(".value6").val();
			}else{
				requestProperty += "#_#" + $(".key6").val() + "@_@" + $(".value6").val();
			}
		}
		if($(".key7").val()!="" && $(".value7").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key7").val() + "@_@" + $(".value7").val();
			}else{
				requestProperty += "#_#" + $(".key7").val() + "@_@" + $(".value7").val();
			}
		}
		if($(".key8").val()!="" && $(".value8").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key8").val() + "@_@" + $(".value8").val();
			}else{
				requestProperty += "#_#" + $(".key8").val() + "@_@" + $(".value8").val();
			}
		}
		if($(".key9").val()!="" && $(".value9").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key9").val() + "@_@" + $(".value9").val();
			}else{
				requestProperty += "#_#" + $(".key9").val() + "@_@" + $(".value9").val();
			}
		}
		if($(".key10").val()!="" && $(".value10").val()!=""){
			if(requestProperty == ""){
				requestProperty = $(".key10").val() + "@_@" + $(".value10").val();
			}else{
				requestProperty += "#_#" + $(".key10").val() + "@_@" + $(".value10").val();
			}
		}
		//弹出loading
		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
		$.post($("#ctx").attr("value") + '/filterUrl/addRecord.ht',{
        	urlGroup : $(".urlGroup").val(),
        	sortId : $(".sortId").val(),
        },function(res){
        	tableIns.reload();
        	top.layer.msg("添加成功！");
    	    $(".urlGroup").val("");
    	    $(".sortId").val("");
        	$("#addFiled").dialog("close"); 
        })
		$.post($("#ctx").attr("value") + '/baseController/getUrl.ht',{
		   url : $(".url").val(),
		   urlParent : $(".urlParent").val(),
		   cookie : $(".cookie").val(),
		   userAgent : $(".userAgent").val(),
		   patternStr1 : $(".patternStr1").val(),
		   group1 : $(".group1").val(),
		   patternStr2 : $(".patternStr2").val(),
		   group2 : $(".group2").val(),
		   type : type,
		   requestProperty : requestProperty,
	    },function(res){
	    	if(JSON.parse(res).code == 0){ 
				top.layer.msg(JSON.parse(res).msg); 
		    	var title = "返回参数";
	            $(".pattern1").val(JSON.parse(res).pattern1);  
	        	$(".pattern2").val(JSON.parse(res).pattern2); 
	        	$(".out").val(JSON.parse(res).out); 
		    	
		    	$("#remarkRecord").dialog({  
		            //autoOpen : false,   // 是否自动弹出窗口  
		            modal : true,    // 设置为模态对话框  
		            resizable : true,  
		            width : 1150,   //弹出框宽度  
		            height : 550,   //弹出框高度  
		            title : title,  //弹出框标题  
		            position: { my: "center top", at: "center top", of: window },
		            closeText : '关闭',
		            buttons:{  
			            '关闭':function(){  
			            	$(".pattern1").val("");  
			 	        	$(".pattern2").val("");   
			 	        	$(".out").val(""); 
			            	$(this).dialog("close");  
			            }  
		            } 
		       });
	        }else{
	        	layer.alert(JSON.parse(res).msg, {
	        		icon: 5,
	        		title: "警告"
	        	});
	        } 
	    })
	    return false;
	});    
})

</script>
</body>
</html>