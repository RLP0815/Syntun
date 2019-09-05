<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>配置表格</title>
	<%@include file="/page/include/getUserInfo.jsp"%>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<!-- <link rel="stylesheet" href="../../../css/public.css" media="all" /> -->
</head>
<script type="text/javascript">
	var nickName = '<%=nickName%>';
	var userId = '<%=userId%>';
	var userName = '<%=userName%>';
	var email = '<%=email%>';
	var phoneno = '<%=phoneno%>';
<%-- 	var authorities = '<%=authorities%>'; --%>
</script>
<style>
.ddtable {
    margin-bottom: -1px;
	position: relative;
	width: auto;
	margin: 0;
	background-color: #fff;
	color: #666;
}
.table th {
    position: relative;
    padding: 9px 15px;
    min-height: 20px;
    line-height: 20px;
    font-size: 15px;
    padding: 5px 0;

}
</style>
<body>
	<input id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<form class="layui-form">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				IP地址
				<div class="layui-input-inline" style="width:150px;">
					<select class="dataIP" lay-filter="dataIP" id="dataIP">
						<option value="">请选择</option>
						<option value="192.168.0.132">192.168.0.132</option>
						<option value="192.168.0.142">192.168.0.142</option>
						<option value="192.168.0.143">192.168.0.143</option>
						<option value="192.168.0.144">192.168.0.144</option>
					</select>
				</div>
			</div>
			<div class="layui-inline">
				数据库
				<div class="layui-input-inline" style="width:150px;">
					<select class="databaseName" lay-filter="databaseName" id="databaseName">
					</select>
				</div>
			</div>
			<div class="layui-inline">
				<div class="demoTable">
					表名：
					<div class="layui-input-inline">
						<input type="text" class="layui-input tableName" placeholder="请输入表名" />
					</div>
					<button class="layui-btn addTable" lay-submit="" lay-filter="addTable">创建表格</button>
					<!-- <a class="layui-btn layui-btn-normal add" data-type="reload">增加行</a>
					<a class="layui-btn layui-btn-normal add1" data-type="reload">增加行</a> -->
				</div>
			</div>
		</blockquote>
		<!-- <table class="layui-hide" id="tableColumn" lay-filter="tableColumn"></table> -->
		
		<table class="table" border="1">
			<tr>
				<th></th>
				<th>列名</th>
    			<th>数据类型</th>
    			<th>数据长度</th>
    			<th>默认值</th>
    			<th>主键</th>
    			<th>非空</th>
    			<th>自增</th>
    			<th>注释</th>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">1</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column1">
  				</td>
    			<td>
    				<select class="dataType1" lay-filter="">
	        			<option value="int" selectd="">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" lay-verify="number" class="layui-input length1">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default1">
  				</td>
    			<td align="center" width="50">
    				<input type="checkbox" checked="" lay-filter="" id="primaryKey1" >
    			</td>
  				<td align="center" width="50">
  					<input type="checkbox" checked="" lay-filter="" id="notNull1" >
  				</td>
  				<td align="center" width="50">
  					<input type="checkbox" checked="" lay-filter="" id="autoIncrement1" >
  				</td>
  				<td width="300">
  					<input type="text" placeholder="" class="layui-input comment1">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">2</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column2">
  				</td>
    			<td>
    				<select class="dataType2" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length2">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default2">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey2" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull2" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement2" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment2">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">3</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column3">
  				</td>
    			<td>
    				<select class="dataType3" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length3">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default3">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey3" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull3" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement3" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment3">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">4</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column4">
  				</td>
    			<td>
    				<select class="dataType4" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length4">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default4">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey4" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull4" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement4" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment4">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">5</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column5">
  				</td>
    			<td>
    				<select class="dataType5" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length5">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default5">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey5" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull5" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement5" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment5">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">6</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column6">
  				</td>
    			<td>
    				<select class="dataType6" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length6">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default6">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey6" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull6" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement6" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment6">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">7</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column7">
  				</td>
    			<td>
    				<select class="dataType7" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length7">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default7">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey7" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull7" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement7" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment7">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">8</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column8">
  				</td>
    			<td>
    				<select class="dataType8" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length8">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default8">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey8" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull8" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement8" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment8">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">9</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column9">
  				</td>
    			<td>
    				<select class="dataType9" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length9">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default9">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey9" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull9" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement9" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment9">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">10</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column10">
  				</td>
    			<td>
    				<select class="dataType10" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length10">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default10">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey10" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull10" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement10" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment10">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">11</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column11">
  				</td>
    			<td>
    				<select class="dataType11" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length11">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default11">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey11" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull11" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement11" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment11">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">12</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column12">
  				</td>
    			<td>
    				<select class="dataType12" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length12">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default12">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey12" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull12" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement12" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment12">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">13</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column13">
  				</td>
    			<td>
    				<select class="dataType13" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length13">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default13">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey13" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull13" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement13" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment13">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">14</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column14">
  				</td>
    			<td>
    				<select class="dataType14" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length14">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default14">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey14" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull14" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement14" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment14">
  				</td>
  			</tr>
  			<tr>
  				<td align="center" valign="middle" width="40">15</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input column15">
  				</td>
    			<td>
    				<select class="dataType15" lay-filter="">
	        			<option value=""></option>
	        			<option value="int">int</option>
	        			<option value="bigint">bigint</option>
	        			<option value="varchar">varchar</option>
	        			<option value="date">date</option>
	        			<option value="datetime">datetime</option>
	        			<option value="timestamp">timestamp</option>
	        			<option value="text">text</option>
      				</select>
      			</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input length15">
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input default15">
  				</td>
    			<td align="center">
    				<input type="checkbox" lay-filter="" id="primaryKey15" >
    			</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="notNull15" >
  				</td>
  				<td align="center">
  					<input type="checkbox" lay-filter="" id="autoIncrement15" >
  				</td>
  				<td>
  					<input type="text" placeholder="" class="layui-input comment15">
  				</td>
  			</tr>
		</table>
		
	</form>
	<script type="text/javascript" src="../../../layui/layui.js"></script>
	<script type="text/javascript" src="AddTable.js"></script>
</body>
</html>