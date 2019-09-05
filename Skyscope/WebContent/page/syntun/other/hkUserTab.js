layui.use(['form','layer','table','laytpl'],function(){
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

	getRemarkUsers();
    /*
	 * 用户列表
	 */
    var tableRep = table.render({
        elem: '#skyUsers',
        url :$("#ctx").attr("value") + '/skyUsers/getHkList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-68",
        limits : [10,15,20,25],
        limit : 10,
        id : "skyUsersTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'userId', title: '用户ID', align:"center", minWidth:150},
            {field: 'userName', title: '用户名', align:"center", minWidth:300},
            {title: '操作', width:80, templet:'#skyUsersBar',fixed:"right",align:"center"}
        ]]
    });
    
    //搜索
    $(".search_btn_rep").on("click",function(){
    	table.reload("skyUsersTable",{
           page: {
              curr: 1 //重新从第 1 页开始
           },
           url: $("#ctx").attr("value") + '/skyUsers/getHkList.ht',
           method: 'post',
           where: {
        	   userName : $(".searchValRep").val()
           }
       })
        
    });
    
    $(".add_btn_rep").click(function(){
    	$(".userIdU").val("");  
	    $(".userId").val("");
	    $(".userName").val("");
	    addUsers();
    })
    //添加
    function addUsers(edit){
    	$(".addRepRecord").show();
    	var title = "添加需要全球购用户";
    	
    	$("#skyUsersTab").dialog({  
            //autoOpen : false,	//是否自动弹出窗口  
            modal : true,		//设置为模态对话框  
            resizable : true,	//是否可以改变dialog的大小
            width : 700,		//弹出框宽度  
            height : 250,		//弹出框高度  
            title : title,		//弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	            '取消':function(){ 
	            	$(".userIdU").val("");  
	        	    $(".userId").val("");
	        	    $(".userName").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
    }
    
    form.on("submit(addRepRecord)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/skyUsers/addHkRecord.ht',{
    	   userId : $(".userId").val(),
    	   userName : $(".userName").val(),
        },function(res){
        	tableRep.reload();
         	top.layer.msg("添加成功！"); 
         	$(".userIdU").val("");  
    	    $(".userId").val("");
    	    $(".userName").val("");
         	$("#skyUsersTab").dialog("close"); 
        })
        
        return false;
    })
   
	//批量删除
    $(".delAll_btn_rep").click(function(){
        var checkStatus = table.checkStatus('skyUsersTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/skyUsers/delAllHkRecord.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	tableRep.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(skyUsers)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/skyUsers/delHkRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableRep.reload();
                    layer.close(index);
                })
            });
        }
    });
   
    $(".selectGroup").click(function(){
    	var tablePro = table.render({
            elem: '#groupsList',
            url :$("#ctx").attr("value") + '/skyUsers/getNoHkList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-200",
            limits : [10,15,20,25],
            limit : 5,
            id : "groupsListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'userId', title: '用户ID', align:"center", width:120},
                {field: 'userName', title: '用户名', align:"center", width:150},
                {title: '操作', width:80, templet:'#groupsListBar',fixed:"right",align:"center"}
            ]]
        });
    	
    	$("#groups").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 460,   //弹出框宽度  
            height : 400,   //弹出框高度  
            title : "用户选择",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	           /* '确定':function(){  
	            //调用登录的方法  
	            }, */ 
	            '取消':function(){  
	            	$('.userId').val("");
	            	$('.userName').val("");
	            	$(this).dialog("close");  
	            }  
            }  
       });  
    })
    
    //选择操作
    table.on('tool(groupsList)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	$('.userId').val(data.userId);
    	$('.userName').val(data.userName);
        
    	$("#groups").dialog("close"); 
    });
   
    function getRemarkUsers(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "hkusers"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>全球购用户维护  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.hkusers').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }

})