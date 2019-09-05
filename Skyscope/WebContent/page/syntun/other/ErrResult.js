layui.use(['element','form','layer','table','laytpl'],function(){
	
	var element = layui.element;

	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	//获取说明
	getRemark();
    //替换字段列表
    var tableIns = table.render({
        elem: '#errResultList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/errResult/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-68",
        limits : [10,15,20,25],
        limit : 10,
        id : "errResultListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
           /* {type: 'checkbox', fixed: 'left'},*/
            {field: 'platformId', title: '平台ID', align:"center", minWidth:80},
            {field: 'operationProductId', title: '网页ID', align:"center", minWidth:80},
            {field: 'getDate', title: '获取日期', align:"center", minWidth:110},
            {field: 'errTypeName', title: '错误类型名称', align:"center", minWidth:150},
            {field: 'errTypeInfo', title: '错误小类型', align:"center", minWidth:100},
            {field: 'errFocusInformation', title: '具体错误信息', align:"center", minWidth:180},
            {field: 'shopId', title: '店铺ID', align:'center',minWidth:80}/*,
            {title: '操作', width:130, templet:'#newsListBar',fixed:"right",align:"center"}*/
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	table.reload("errResultListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/errResult/getList.ht',
            method: 'post',
            where: {
            	errTypeName : $(".searchVal").val()
            }
         })
    });
/*
    $(".add_btn").click(function(){
    	addFiled();
    })
    //添加
    function addFiled(edit){
    	$(".addFlied").show();
    	$(".editFlied").hide();
    	var title = "添加替换字段";
    	if(edit){
    		title = "编辑替换字段";
            $(".filedId").val(edit.id);  
        	$(".oldFiled").val(edit.oldFiled);  //原字段
            $(".unityFiled").val(edit.unityFiled);  //统一字段
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
            	'添加':function(data){  
            		 
	            }, 
	            '取消':function(){  
	            	$(".oldFiled").val("");
            	    $(".unityFiled").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    }
    //立即添加按钮
    form.on("submit(addFlied)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/replaceFiled/addFiled.ht',{
        	oldFiled : $(".oldFiled").val(),  //登录名
        	unityFiled : $(".unityFiled").val(),  //邮箱
        },function(res){
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        	$(".oldFiled").val("");
    	    $(".unityFiled").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    })
    //立即修改按钮
    form.on("submit(editFlied)",function(data){
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/replaceFiled/editFiled.ht',{
    	   id : $(".filedId").val(),
    	   oldFiled : $(".oldFiled").val(),  
    	   unityFiled : $(".unityFiled").val(), 
        },function(res){
        	tableIns.reload();
        	top.layer.msg("修改成功！");
        	$(".oldFiled").val("");
    	    $(".unityFiled").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    })
*/
    
    //批量删除
    $(".delAll_btn").click(function(){
        /*var checkStatus = table.checkStatus('filedListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }*/
            layer.confirm('确定清空表中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/errResult/delAllRecord.ht",{
                	//ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("清空数据成功！");
                	tableIns.reload();
                	layer.close(index);
                })
            })
        /*}else{
            layer.msg("请选择需要删除的记录");
        }*/
    })
/*
    //列表操作
    table.on('tool(filedList)', function(obj){
        var layEvent = obj.event;
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addFiled(data);
        }else if(layEvent === 'del'){ //删除
        	layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get($("#ctx").attr("value") + "/replaceFiled/delFiled.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });
   */ 
  //默认请求参数
    /*var _options = {
      url: null,  // 请求连接
      type: 'GET',  // 请求类型
      data: null,  // post时请求体
      dataType: 'text',  // 返回请求的类型，有text/json两种
      jsonp: 'callback',  // jsonp请求的标志，一般不改动
      jsonpCallback: 'jsonpCallback',  //jsonp请求的函数名
      async: true,   // 是否异步
      cache: true,   // 是否缓存
      timeout:null,  // 设置请求超时
      contentType: 'application/x-www-form-urlencoded',
      success: null,  // 请求成功回调函数
      fail: null   // 请求失败回调
    }*/
    
    //ajax
    function getRemark(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
    		  type: 'post',
    		  data: {remarkId: "err"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    		    var remarks = '<li>抛出错误  - 说明</li>';
    		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uull').html(remarks);
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }

})
