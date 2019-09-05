<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作日志</title>
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
					按月查询：
					<div class="layui-input-inline">
						<input type="text" class="layui-input searchVal" placeholder="列如：20190301" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input username" placeholder="用户名称" />
					</div>
					<div class="layui-input-inline">
						<input type="text" class="layui-input method" placeholder="方法名称" />
					</div>
					<a class="layui-btn search_btn" data-type="reload">查询</a>
				</div>
			</div>	
		</blockquote>
		<table id="filedList" lay-filter="filedList"></table>
	</div>
</div> 
</form>
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
            url :$("#ctx").attr("value") + '/user/getUserLogList.ht',
            cellMinWidth : 95,
            page : true,
            height : "full-68",
            limits : [10,15,20,25],
            limit : 10,
            id : "filedListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'userid', title: '用户id', align:"center", minWidth:200},
                {field: 'username', title: '用户名称', align:'center',minWidth:200},
                {field: 'method', title: '方法名称', align:'center',minWidth:200},
                {field: 'url', title: '操作接口地址', align:'center',minWidth:200},
                {field: 'requestip', title: '操作ip', align:'center',minWidth:200},
                {field: 'requesttime', title: '操作时间', align:'center',minWidth:200,sort:true},
            ]]
        });

        //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
        $(".search_btn").on("click",function(){
        	table.reload("filedListTable",{
        		page: {
        			curr: 1 //重新从第 1 页开始
                },
                url: $("#ctx").attr("value") + '/user/getUserLogList.ht',
                method: 'post',
                where: {
                	tableName : $(".searchVal").val(),
                	username : $(".username").val(),
                	method : $(".method").val()
                }
             })
             //$(".abstract").val("1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”%OD%OA2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】%OD%OA3、“个人资料”提供修改功能4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】")
        });
    })
</script>
</body>
</html>