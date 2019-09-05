layui.use(['laydate','element','form','layer','table','laytpl'],function(){
	var laydate = layui.laydate;
	var element = layui.element;
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	//常规用法
	laydate.render({
	    elem: '.runTime',
	    max: +1,
	    //btns: ['clear','confirm']
	 });
	  laydate.render({
		    elem: '.date21',
		    type: 'datetime',
		    max: +1,
		 });
	  laydate.render({
		    elem: '.date22',
		    type: 'datetime',
		    max: +1,
		 });
	  laydate.render({
		    elem: '.date3',
		    type: 'datetime',
		    max: +1,
		 });
	//日期时间选择器
	  laydate.render({
	    elem: '.date4',
	    type: 'datetime',
	    max: +1,
	 });
	  laydate.render({
		elem: '.date5',
		type: 'datetime',
	    max: +1,
	});
	  laydate.render({
		elem: '.date51',
		type: 'datetime',
	    max: +1,
	});
	  laydate.render({
			elem: '.date71',
			type: 'date',
		    max: 0,
		});
	  laydate.render({
			elem: '.date72',
			type: 'date',
		    max: 0,
		});
	//获取说明
	getRemark();
	//获取说明
	getShell();
	//获取用户
	getUsers();
	//获取平台
	getPlatforms();
	//监听select选择
	/*form.on('select(runShell)', function(data){
		  alert(data.elem); //得到select原始DOM对象
		  alert(data.value); //得到被选中的值
		  alert(data.othis); //得到美化后的DOM对象
	});*/
	//获取脚本
    function getShell(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/executeRun/getShell.ht',
    		  type: 'post',
    		  //data: {remarkId: "replenish"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    			for(var i=0; i<data.length; i++){
    		    	$('.runShell').append("<option value='"+data[i].run_shell+","+data[i].param_group+"'>"+data[i].run_shell+"</option>");
      		    }
    			form.render('select','selFilter');
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }
	//获取用户
    function getUsers(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/executeRun/getUsers.ht',
    		  type: 'post',
    		  //data: {remarkId: "replenish"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    			for(var i=0; i<data.length; i++){
    		    	$('.userId2').append("<option value='"+data[i].user_id+"'>"+data[i].user_name+"</option>");
    		    	$('.userId3').append("<option value='"+data[i].user_id+"'>"+data[i].user_name+"</option>");
    		    	$('.userId4').append("<option value='"+data[i].user_id+"'>"+data[i].user_name+"</option>");
    		    	$('.userId5').append("<option value='"+data[i].user_id+"'>"+data[i].user_name+"</option>");
      		    }
    			form.render('select','selFilter');
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }
	//获取平台
    function getPlatforms(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/executeRun/getPlatforms.ht',
    		  type: 'post',
    		  //data: {remarkId: "replenish"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    			for(var i=0; i<data.length; i++){
    		    	$('.platform4').append("<option value='"+data[i].plat_id+"'>"+data[i].plat_name+"</option>");
    		    	$('.platform5').append("<option value='"+data[i].plat_id+"'>"+data[i].plat_name+"</option>");
      		    }
    			form.render('select','selFilter');
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }
    
	var cols = [
	            {type: 'numbers', width:30, fixed: 'left'},
	            /*{field: 'startTime', title: '补充数据日期', align:"center", minWidth:120},*/
	            {field: 'platformIds', title: '运行脚本', align:"center", minWidth:100},
	            {field: 'addTime', title: '入库时间', align:"center", minWidth:170},
	            {field: 'startExecuteTime', title: '执行开始时间', align:"center", minWidth:170},
	            {field: 'endExecuteTime', title: '执行结束时间', align:"center", minWidth:170},
	            {field: 'status', title: '状态', align:"center", minWidth:60,templet:function(d){
	                if(d.status == "0"){
	                    return "入库";
	                }else if(d.status == "1"){
	                    return "提取";
	                }else if(d.status == "2"){
	                    return "结束";
	                }else if(d.status == "3"){
	                    return "已通知";
	                }
	            }},
	            {field: 'userName', title: '执行人', align:"center", minWidth:60}/*,
	            {title: '操作', width:130, templet:'#newsListBar',fixed:"right",align:"center"}*/
	        ];
    //操作说明列表
    var tableIns = table.render({
        elem: '#executeRunList',
        url :$("#ctx").attr("value") + '/executeRun/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-70",
        limits : [10,15,20,25],
        limit : 10,
        id : "executeRunListTable",
        cols : [cols]
    });
    //值小于10时，在前面补0
    function dateFilter(date){
        if(date < 10){return "0"+date;}
        return date;
    }
    //获取当前时间
    function getNowDate(){
    	var dateObj = new Date(); //表示当前系统时间的Date对象
        var year = dateObj.getFullYear(); //当前系统时间的完整年份值
        var month = dateObj.getMonth()+1; //当前系统时间的月份值
        var date = dateObj.getDate(); //当前系统时间的月份中的日
        var day = dateObj.getDay(); //当前系统时间中的星期值
        var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
        var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
        var hour = dateObj.getHours(); //当前系统时间的小时值
        var minute = dateObj.getMinutes(); //当前系统时间的分钟值
        var second = dateObj.getSeconds(); //当前系统时间的秒钟值
        var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    	var newDate = dateFilter(year)+"-"+dateFilter(month)+"-"+dateFilter(date)+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    	
    	return newDate;
    }
    //获取运行脚本
    function getRunShell(){
    	var strs= new Array(); //定义一数组
  		strs = $(".runShell").val().split(","); //字符分割 
  		var runShell = strs[0];
  		
  		return runShell;
    }
    
    //执行按钮
    form.on("submit(replenish_btn1)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"，数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : "",
        },function(res){
        	$("#dialog1").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    //执行按钮
    form.on("submit(replenish_btn2)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数：用户ID："+$(".userId2").val()+"；日期1："+$(".date21").val()+"；日期2："+$(".date22").val()+"；数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".userId2").val()+','+$(".date21").val()+','+$(".date22").val(),
        },function(res){
     	   	$(".runShell").val(""),  
        	$(".date21").val(""),   
        	$(".date22").val(""),  
        	$(".userId2").val(""),     
     	   	//form.render('select','selFilter');
        	$("#dialog2").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    //执行按钮
    form.on("submit(replenish_btn3)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   //runTime : $(".runTime").val(),
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数：用户ID："+$(".userId3").val()+"；数据日期："+$(".date3").val()+"；数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".userId3").val()+','+$(".date3").val(),
        },function(res){
     	   	$(".runShell").val(""),  
        	$(".date3").val(""),  
        	$(".userId3").val(""),   
     	   	form.render('select','selFilter');
        	$("#dialog3").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    //执行按钮
    form.on("submit(replenish_btn4)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   //runTime : $(".runTime").val(),
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数：用户ID："+$(".userId4").val()+"；数据日期："+$(".date4").val()+"；平台："+$(".platform4").val()+"；数据补充运行完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".userId4").val()+','+$(".date4").val()+','+$(".platform4").val(),
        },function(res){
     	   	$(".runShell").val(""),  
        	$(".date4").val(""),  
        	$(".userId4").val(""),   
        	$(".platform4").val(""),   
     	   	form.render('select','selFilter');
        	$("#dialog4").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    
    //执行按钮
    form.on("submit(replenish_btn5)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   //runTime : $(".runTime").val(),
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数：用户ID："+$(".userId5").val()+"；数据日期："+$(".date5").val()+"；平台："+$(".platform5").val()+"；日期："+$(".date51").val()+"；数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".userId5").val()+','+$(".date5").val()+','+$(".platform5").val()+','+$(".date51").val(),
        },function(res){
     	   	$(".runShell").val(""),  
        	$(".date5").val(""),  
        	$(".userId5").val(""),   
        	$(".platform5").val(""),  
        	$(".date51").val(""),    
     	   	form.render('select','selFilter');
        	$("#dialog5").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    //执行按钮
    form.on("submit(replenish_btn6)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   runTime : $(".runTime").val(),
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数为：数据日期："+$(".runTime").val()+"；数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".runTime").val(),
        },function(res){
        	$(".runTime").val(""),  
     	   	$(".runShell").val(""),   
        	$("#salesVolume").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    //执行按钮
    form.on("submit(replenish_btn7)",function(data){
  		var runShell = getRunShell();
  		var newDate = getNowDate();
  		//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/executeRun/addRecord.ht',{
    	   runShell : runShell,   
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   email : email,
    	   emailText : "您补充数据运行脚本为："+runShell+"；执行参数：开始日期："+$(".date71").val()+"；结束日期："+$(".date72").val()+"；数据补充完成，请注意核查！",
    	   status : 0,
    	   replaceField : $(".date71").val()+','+$(".date72").val(),
        },function(res){
     	   	$(".runShell").val(""),  
        	$(".date71").val(""),   
        	$(".date72").val(""), 
        	$("#dialog7").dialog("close"); 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
    
    
    form.on('select(runShell)', function(data){
    	  //console.log(data.elem); //得到select原始DOM对象
    	  //console.log(data.othis); //得到美化后的DOM对象
    	 //console.log(data.value); //得到被选中的值
    	//alert(data.value);
    	  var strs= new Array(); //定义一数组
    	  strs = data.value.split(","); //字符分割 
    	  var runShell = strs[0];
    	  var paramGroup = strs[1];
    	  
    	  if(paramGroup == 1){
    		  $("#dialog1").dialog({  
  	            //autoOpen : false,   // 是否自动弹出窗口  
  	            modal : true,    // 设置为模态对话框  
  	            resizable : true,  
  	            width : 300,   //弹出框宽度  
  	            height : 200,   //弹出框高度  
  	            title : runShell + "/参数设置",  //弹出框标题  
  	            position: { my: "center top", at: "center top", of: window },
  	            closeText : '关闭',
  	            buttons:{  
  	            	/*'添加':function(data){  
  	            		 
  		            }, */
  		            '取消':function(){  
  		            	$(this).dialog("close");  
  		            }  
  	            } 
  	      	});  
    	  }else if(paramGroup == 2){
    		  $("#dialog2").dialog({  
    	            //autoOpen : false,		// 是否自动弹出窗口  
    	            modal : true,    		// 设置为模态对话框  
    	            resizable : true,  
    	            width : 400,			//弹出框宽度  
    	            height : 350,			//弹出框高度  
    	            title : runShell + "/参数设置",  //弹出框标题  
    	            position: { my: "center top", at: "center top", of: window },
    	            closeText : '关闭',
    	            buttons:{  
    	            	/*'添加':function(data){  
    	            		 
    		            }, */
    		            '取消':function(){  
    		            	$(this).dialog("close");  
    		            }  
    	            } 
    	      	});  
      	  }else if(paramGroup == 3){
    		  $("#dialog3").dialog({  
    	            //autoOpen : false,   // 是否自动弹出窗口  
    	            modal : true,    // 设置为模态对话框  
    	            resizable : true,  
    	            width : 400,   //弹出框宽度  
    	            height : 350,   //弹出框高度  
    	            title : runShell + "/参数设置",  //弹出框标题  
    	            position: { my: "center top", at: "center top", of: window },
    	            closeText : '关闭',
    	            buttons:{  
    	            	/*'添加':function(data){  
    	            		 
    		            }, */
    		            '取消':function(){  
    		            	$(this).dialog("close");  
    		            }  
    	            } 
    	      	});  
      	  }else if(paramGroup == 4){
    		  $("#dialog4").dialog({  
  	            //autoOpen : false,   // 是否自动弹出窗口  
  	            modal : true,    // 设置为模态对话框  
  	            resizable : true,  
  	            width : 400,   //弹出框宽度  
  	            height : 400,   //弹出框高度  
  	            title : runShell + "/参数设置",  //弹出框标题  
  	            position: { my: "center top", at: "center top", of: window },
  	            closeText : '关闭',
  	            buttons:{  
  	            	/*'添加':function(data){  
  	            		 
  		            }, */
  		            '取消':function(){  
  		            	$(this).dialog("close");  
  		            }  
  	            } 
  	      	});  
    	  }else if(paramGroup == 5){
    		  $("#dialog5").dialog({  
    	            //autoOpen : false,   // 是否自动弹出窗口  
    	            modal : true,    // 设置为模态对话框  
    	            resizable : true,  
    	            width : 400,   //弹出框宽度  
    	            height : 400,   //弹出框高度  
    	            title : runShell + "/参数设置",  //弹出框标题  
    	            position: { my: "center top", at: "center top", of: window },
    	            closeText : '关闭',
    	            buttons:{  
    	            	/*'添加':function(data){  
    	            		 
    		            }, */
    		            '取消':function(){  
    		            	$(this).dialog("close");  
    		            }  
    	            } 
    	      	});  
      	  }else if(paramGroup == 6){
  	    	$("#salesVolume").dialog({  
	            //autoOpen : false,   // 是否自动弹出窗口  
	            modal : true,    // 设置为模态对话框  
	            resizable : true,  
	            width : 400,   //弹出框宽度  
	            height : 250,   //弹出框高度  
	            title : runShell + "/参数设置",  //弹出框标题  
	            position: { my: "center top", at: "center top", of: window },
	            closeText : '关闭',
	            buttons:{  
	            	/*'添加':function(data){  
	            		 
		            }, */
		            '取消':function(){  
		            	$(this).dialog("close");  
		            }  
	            } 
	       });  
		}else if(paramGroup == 7){
  		  $("#dialog7").dialog({  
	            //autoOpen : false,		// 是否自动弹出窗口  
	            modal : true,    		// 设置为模态对话框  
	            resizable : true,  
	            width : 400,			//弹出框宽度  
	            height : 350,			//弹出框高度  
	            title : runShell + "/参数设置",  //弹出框标题  
	            position: { my: "center top", at: "center top", of: window },
	            closeText : '关闭',
	            buttons:{  
	            	/*'添加':function(data){  
	            		 
		            }, */
		            '取消':function(){  
		            	$(this).dialog("close");  
		            }  
	            } 
  		  	});  
		}
    });      
    
    //ajax获取说明
    function getRemark(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
    		  type: 'post',
    		  data: {remarkId: "replenish"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    		    var remarks = '<li>补充数据  - 说明</li>';
    		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullReplenish').html(remarks);
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }

})
