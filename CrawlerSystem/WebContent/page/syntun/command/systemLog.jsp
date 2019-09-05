<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>系统日志</title>
<script src="../../../js/jquery/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form">
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
    <div id="log-container" style="width:100%;height: 450px; overflow-y: scroll; background: #0d0d0d; color: #aaa; padding: 10px;">
       <input type="text" id="sendMessageid" placeholder="192.168.0.85,tail -f /home/test/log/log_test.txt" style="width: 30%"/>
       <input type="button" value="提交" onclick="sendMessage()"/>
        <div>
        </div>
    </div>
    <input type="button" value="停止" onclick="sendStop()"/>
</body>
<script>
    
layui.use(['element','form','layer','table','laytpl'],function(){
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    //替换字段列表
    var tableIns = table.render({
        elem: '#filedList',
        url :$("#ctx").attr("value") + '/user/getHistoryInfo?type=1',
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
            url: $("#ctx").attr("value") + '/user/getHistoryInfo?type=1',
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
        	var name = data.name;
        	$("#sendMessageid").val(name);
        }
    });
    
})
    
    var websocket;
    function sendMessage(){
    	//websocket = new WebSocket('ws://localhost:8080/CrawlerSystem/log');
    	 websocket = new WebSocket('ws://192.168.0.132:8080/CrawlerSystem/log');
    	var sendMessageid = $("#sendMessageid").val();
    	if(sendMessageid==''){
    		top.layer.msg("请输入内容！！");
    		return false;
    	}
        // 指定websocket路径
        
        //接受服务器端返回数据
        websocket.onmessage = function(event) {
            // 接收服务端的实时日志并添加到HTML页面中
            $("#log-container div").append(event.data);
            // 滚动条滚动到最低部
            $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
        };
        websocket.onopen = function()
        {
           // Web Socket 已连接上，使用 send() 方法发送数据
           websocket.send(sendMessageid);
        };
        websocket.onclose = function()
        { 
           // 关闭 websocket
        	websocket.close();
        };
        websocket.error = function()
        { 
           // 关闭 error
           alert("连接出错..."); 
        };
    }
    function sendStop(){
    	websocket.close();
    }
</script>
</body>
</html>