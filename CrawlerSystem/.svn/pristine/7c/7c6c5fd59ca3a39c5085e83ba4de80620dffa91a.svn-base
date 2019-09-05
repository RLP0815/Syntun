<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>api</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../../css/public.css" media="all" />
	<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
   <div class="layui-form-item" style="margin-top: 3%;">
    <label class="layui-form-label">接口名称:</label>
    <div class="layui-input-block">
      <input type="text" id="apiName" lay-verify="title" autocomplete="off" placeholder="请输入接口名称" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">接口地址:</label>
    <div class="layui-input-block">
      <input type="text" id="apiAddress" lay-verify="title" autocomplete="off" placeholder="请输入接口地址" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">返回格式:</label>
    <div class="layui-input-block">
      <input type="text" id="backFormat" lay-verify="title" autocomplete="off" value="json" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">请求方式:</label>
    <div class="layui-input-block">
      <input type="text" id="reqMode" lay-verify="title" autocomplete="off" value="post" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">接口备注:</label>
    <div class="layui-input-block">
      <input type="text" id="apiRemarks" lay-verify="title" autocomplete="off" placeholder="请输入接口备注" class="layui-input">
    </div>
  </div>
  
  <table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>产品功能</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody id="productid">
    <tr name="productName">
      <td><input type="text" name='productname' class="layui-input"></td>
      <td><input type="button" value='删除' class="layui-btn layui-btn-danger del"></td>
    </tr>
  </tbody>
</table>
<div style="text-align: center;">
    <button class="layui-btn" onclick="addProduct()">产品添加</button>
</div> 
  
<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>名称</th>
      <th>数据</th>
      <th>必填</th>
      <th>类型</th>
      <th>说明</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody id="requestParam">
    <tr name="paramName">
      <td><input type="text" name='name' class="layui-input" value="id"></td>
      <td><input type="text" name='dataValue' class="layui-input" value="1"></td>
      <td><input type="text" name='ismust' class="layui-input" value="Y"></td>
      <td><input type="text" name='type' class="layui-input" value="int"></td>
      <td><input type="text" name='explain' class="layui-input" value="产品 id"></td>
      <td><input type="button" value='删除' class="layui-btn layui-btn-danger del"></td>
    </tr>
  </tbody>
</table>
<div style="text-align: center;">
    <button class="layui-btn" onclick="addRequestParam()">请求参数添加</button>
</div>

<table class="layui-table">
  <colgroup>
    <col width="150">
    <col width="200">
    <col>
  </colgroup>
  <thead>
    <tr>
      <th>名称</th>
      <th>数据</th>
      <th>类型</th>
      <th>说明</th>
      <th>操作</th>
    </tr> 
  </thead>
  <tbody id="backParam">
    <tr name="backName">
      <td><input type="text" name='name1' class="layui-input" value="id"></td>
      <td><input type="text" name='dataValue1' class="layui-input" value="1"></td>
      <td><input type="text" name='type1' class="layui-input" value="int"></td>
      <td><input type="text" name='explain1' class="layui-input" value="产品 id"></td>
      <td><input type="button" value='删除' class="layui-btn layui-btn-danger del"></td>
    </tr>
  </tbody>
</table>
<div style="text-align: center;">
    <button class="layui-btn" onclick="addBackParam()">返回参数添加</button>
    <button class="layui-btn layui-btn-normal" onclick="generateApi()">生成api页面</button>
</div>     
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script>
	//产品添加
	function addProduct(){
		var str = "<tr name='productName'><td><input type='text' name='productname' class='layui-input'></td><td><input type='button' value='删除' class='layui-btn layui-btn-danger del'></td></tr>";
		$("#productid").append(str);
	}
	//请求参数添加
	function addRequestParam(){
		var str = "<tr name='paramName'><td><input type='text' name='name' class='layui-input'></td><td><input type='text' name='dataValue' class='layui-input'></td><td><input type='text' name='ismust' class='layui-input'></td><td><input type='text' name='type' class='layui-input'></td><td><input type='text' name='explain' class='layui-input'></td><td><input type='button' value='删除' class='layui-btn layui-btn-danger del'></td></tr>";
		$("#requestParam").append(str);
	}
	//返回参数添加
	function addBackParam(){
		var str = "<tr name='backName'><td><input type='text' name='name1' class='layui-input'></td><td><input type='text' name='dataValue1' class='layui-input'></td><td><input type='text' name='type1' class='layui-input'></td><td><input type='text' name='explain1' class='layui-input'></td><td><input type='button' value='删除' class='layui-btn layui-btn-danger del'></td></tr>";
		$("#backParam").append(str);
	}
	//删除按钮事件
    $('body').on("click",'.del',function () {
        $(this).parents("tr").remove();
    });
	function generateApi(){
		
		var apiName = $("#apiName").val();//接口名称
		var apiAddress = $("#apiAddress").val();//接口地址
		var backFormat = $("#backFormat").val();//返回格式
		var reqMode = $("#reqMode").val();//请求方式
		var apiRemarks = $("#apiRemarks").val(); //接口备注
		
		//产品集合
	    var productArray=[];
	    $("tr[name='productName']").each(function (index,Element) {
	        var product = {};
	        product.name = $(this).find("input[name='productname']").val();
	        productArray.push(product);
	    });
	    //console.log(productArray)
	    
	    //请求参数集合
   	    var paramArray=[];
	    $("tr[name='paramName']").each(function (index,Element) {
	        var param = {};
	        param.name = $(this).find("input[name='name']").val();
	        param.datavalue = $(this).find("input[name='dataValue']").val();
	        param.ismust = $(this).find("input[name='ismust']").val();
	        param.type = $(this).find("input[name='type']").val();
	        param.explains = $(this).find("input[name='explain']").val();
	        paramArray.push(param);
	    });
	    //console.log(paramArray)
	    
	    //返回参数集合
   	    var backParamArray=[];
	    $("tr[name='backName']").each(function (index,Element) {
	        var param = {};
	        param.name = $(this).find("input[name='name1']").val();
	        param.datavalue = $(this).find("input[name='dataValue1']").val();
	        param.type = $(this).find("input[name='type1']").val();
	        param.explains = $(this).find("input[name='explain1']").val();
	        backParamArray.push(param);
	    });
      	$.ajax({
	  		  url: $("#ctx").attr("value") + '/apiManage/apiGenerate',
			  type: 'post',
			  data: {
				  "apiName":apiName,
				  "apiAddress":apiAddress,
				  "backFormat":backFormat,
				  "reqMode":reqMode,
				  "apiRemarks":apiRemarks,
				  "productArray":JSON.stringify(productArray),
				  "paramArray":JSON.stringify(paramArray),
				  "backParamArray":JSON.stringify(backParamArray),
			  },
			  dataType: 'json',
			  success: function (data, status) {
				  if(data.code=='200'){
					  //top.layer.msg("成功！");
					  window.location.href = "/CrawlerSystem/page/syntun/api/generate.jsp?id="+data.id;
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