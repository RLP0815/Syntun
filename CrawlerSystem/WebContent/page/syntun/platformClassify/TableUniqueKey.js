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
        elem: '#filedList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/tableUniqueKey/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-95",
        limits : [10,15,20,25],
        limit : 10,
        id : "filedListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'dataBase', title: '数据库', align:'center',minWidth:50},
            {field: 'tableName', title: '表名', align:'center',minWidth:50},
            {field: 'uniqueKey', title: '唯一键组合', align:'center',minWidth:100},
            {field: 'ifCount', title: '是否统计', align:"center", minWidth:80,templet:function(d){
                if(d.ifCount == "0"){
                    return "是";
                }else if(d.ifCount == "1"){
                    return "否";
                }
            }},
            {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	table.reload("filedListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/tableUniqueKey/getList.ht',
            method: 'post',
            where: {
            	tableName : $(".searchVal").val()
            }
         })
         //$(".abstract").val("1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”%OD%OA2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】%OD%OA3、“个人资料”提供修改功能4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】")
    });

    $(".add_btn").click(function(){
	    $(".dataBase").val("");
	    $(".tableName").val("");
	    $(".uniqueKey").val("");
    	addFiled();
//    	var numStr = "";
//    	var date = new Date();
//    	var mymonth = date.getMonth()+1;
//        var myweekday = date.getDate();
//        var myhours = date.getHours();
//        var myminutes = date.getMinutes();
//        var myseconds = date.getSeconds();
//        if(mymonth < 10){
//            mymonth = "0" + mymonth;
//        }
//        if(myweekday < 10){
//            myweekday = "0" + myweekday;
//        }
//        if(myhours < 10){
//        	myhours = "0" + myhours;
//        }
//        if(myminutes < 10){
//        	myminutes = "0" + myminutes;
//        }
//        if(myseconds < 10){
//        	myseconds = "0" + myseconds;
//        }
//    	var dateStr = date.getFullYear()+""+mymonth+""+myweekday
//    				+""+myhours+""+myminutes+""+myseconds;
//    	for(var i=0; i<18; i++){
//    		numStr = numStr + "" +Math.floor(Math.random()*9+1);
//    	}
//    	alert(dateStr + "" + numStr);
    	
    });
  //添加
    function addFiled(edit){
    	$(".addFlied").show();
    	$(".editFlied").hide();
    	var title = "添加表格唯一键组合";
    	if(edit){
    		title = "编辑表格唯一键组合";
            
            $(".filedId").val(edit.id);   
            $(".dataBase").val(edit.dataBase);
            $(".tableName").val(edit.tableName);
            $(".uniqueKey").val(edit.uniqueKey);
            $(".ifCount").val(edit.ifCount);
            form.render('select','filter');
    		$(".addFlied").hide();
    		$(".editFlied").show();
        }
    	
    	$("#addFiled").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 400,   //弹出框高度  
            title : title,  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
            	    $(".dataBase").val("");
            	    $(".tableName").val("");
            	    $(".uniqueKey").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    };
    //立即添加按钮
    form.on("submit(addFlied)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/tableUniqueKey/addRecord.ht',{
    	   	dataBase : $(".dataBase").val(),
        	tableName : $(".tableName").val(),
        	uniqueKey : $(".uniqueKey").val(),
        	ifCount : $(".ifCount").val(),
        },function(res){
        	tableIns.reload();
        	top.layer.msg("添加成功！");
    	    $(".dataBase").val("");
    	    $(".tableName").val("");
    	    $(".uniqueKey").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    });
    //立即修改按钮
    form.on("submit(editFlied)",function(data){
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/tableUniqueKey/editRecord.ht',{
    	   id : $(".filedId").val(),
    	   dataBase : $(".dataBase").val(), 
    	   tableName : $(".tableName").val(), 
    	   uniqueKey : $(".uniqueKey").val(), 
    	   ifCount : $(".ifCount").val(), 
        },function(res){
        	tableIns.reload();
        	top.layer.msg("修改成功！");
    	    $(".dataBase").val("");
    	    $(".tableName").val("");
    	    $(".uniqueKey").val("");
        	$("#addFiled").dialog("close"); 
        })
        
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
                $.get($("#ctx").attr("value") + "/tableUniqueKey/delAllRecord.ht",{
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
                $.get($("#ctx").attr("value") + "/tableUniqueKey/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

  //ajax
  function getRemark(){
  	$.ajax({
  		//url: '../../../json/remark.json',
  		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
  		  type: 'post',
  		  data: {"remarkId" : "tableUniqueKey"},
  		  dataType: 'json',
  		  timeout: 2000,
  		  success: function (data, status) {
  		    var remarks = '<li>表格唯一键维护  - 说明</li>';
  		    for(var i=0; i<data.length; i++){
  		    	var j = i+1;
  		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
  		    }
  		    $('.uull').html(remarks);
  		  },
  		  fail: function (err, status) {
  			  alert(status);
  		  }
  	})
  }

})
