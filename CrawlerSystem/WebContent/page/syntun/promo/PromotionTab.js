layui.use(['element','form','layer','table','laytpl'],function(){
	var element = layui.element;
	//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
	var layid = location.hash.replace(/^#test1=/, '');
	element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
	//监听Tab切换，以改变地址hash值
/*	element.on('tab(test1)', function(){
		location.hash = 'test1='+ this.getAttribute('lay-id');
	});*/
	var indexTab;
	element.on('tab(filter)', function(data){
		//console.log(this); //当前Tab标题所在的原始DOM元素
		//console.log(data.index); //得到当前Tab的所在下标
		//console.log(data.elem); //得到当前的Tab大容器
		indexTab = data.index;
		
		if(indexTab == 0){
			tableIns.reload();
		}else if(indexTab == 1){
			PromotionTable.reload();
		}else if(indexTab == 2){
			PlatformTable.reload();
		}
	});
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	getRemarkcompare();
	getRemarkpromotion();
	getRemarkplatform();
    //促销列表
    var tableIns = table.render({
        elem: '#promotionList',
        url :$("#ctx").attr("value") + '/promoCompare/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "promotionListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: "checkbox", width:50, fixed:"left"},
            {field: 'platformId', title: '平台ID', align:"center", width:100},
            {field: 'platformName', title: '平台名称', align:"center", width:140},
            {field: 'promotionTypeName', title: '促销名称', align:"center", width:140},
            {field: 'promotionStandard', title: '促销标准', align:"center", width:140},
            {field: 'promotionId', title: '促销标准ID', align:'center',width:100},
            {title: '操作', width:130, templet:'#promotionListBar',fixed:"right",align:"center"}
        ]]
    });
    
    var $ = layui.$, active = {  
	    
	    getCheckData: function(){ //获取选中数据  
	      var arr=[];  
	      var checkStatus = table.checkStatus('idTest'),
	      data = checkStatus.data;  
	  
	      for(var i=0;i<data.length;i++){    //循环筛选出id  
	        arr.push(data[i].id);  
	      }  
	      alert(arr)  
	    },  
	  
	  };  
    
    //搜索
    $(".search_btn").on("click",function(){
    	table.reload("promotionListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/promoCompare/getList.ht',
            method: 'post',
            where: {
               promotionTypeName : $(".searchVal").val()
            }
        })
        
    });
    
    $(".add_btn").click(function(){
    	$(".cxId").val("");
	    $(".platformId").val("");
	    $(".platformName").val("");
	    $(".promotionTypeName").val("");
	    $(".promotionStandard").val("");
	    $(".promotionId").val("");
    	addPromotion();
    })
    //添加
    function addPromotion(edit){
    	$(".addFiled").show();
    	$(".editFiled").hide();
    	var title = "添加促销";
    	if(edit){
    		title = "编辑促销";
            $(".cxId").val(edit.id);  
        	$(".platformId").val(edit.platformId);  
            $(".platformName").val(edit.platformName); 
            $(".promotionTypeName").val(edit.promotionTypeName); 
            $(".promotionStandard").val(edit.promotionStandard); 
            $(".promotionId").val(edit.promotionId); 
    		$(".addFiled").hide();
    		$(".editFiled").show();
        }
    	
    	$("#promotionTab").dialog({  
            //autoOpen : false,	//是否自动弹出窗口  
            modal : true,		//设置为模态对话框  
            resizable : true,	//是否可以改变dialog的大小
            width : 720,		//弹出框宽度  
            height : 400,		//弹出框高度  
            title : title,		//弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$(".cxId").val("");
            	    $(".platformId").val("");
            	    $(".platformName").val("");
            	    $(".promotionTypeName").val("");
            	    $(".promotionStandard").val("");
            	    $(".promotionId").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    }
    
    //平台选择
    $(".selectPlatform").click(function(){
    	var tablePlad = table.render({
            elem: '#platformListd',
            url :$("#ctx").attr("value") + '/platformList/getList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-220",
            limits : [10,15,20,25],
            limit : 10,
            id : "platformListdTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'platformId', title: '平台ID', align:"center", width:130},
                {field: 'platformName', title: '平台名称', align:"center", width:200},
                {title: '操作', width:60, templet:'#platformListdBar',fixed:"right",align:"center"}
            ]]
        });
    	$("#platform").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 500,   //弹出框宽度  
            height : 400,   //弹出框高度  
            title : "平台选择",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	           /* '确定':function(){  
	            //调用登录的方法  
	            }, */ 
	            '取消':function(){  
	            	$('.platformId').val("");
	            	$('.platformName').val("")
	            	$(this).dialog("close");  
	            }  
            }  
       });  
    })
    
	//选择操作
    table.on('tool(platformListd)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	$('.platformId').val(data.platformId);
    	$('.platformName').val(data.platformName)
    	$("#platform").dialog("close");  
    	//$("#platform").hide();
    });
    
	//促销标准选择
    $(".selectPromotion").click(function(){
    	var tableProd = table.render({
            elem: '#promotionListd',
            //url : '../../../json/userList.json',
            url :$("#ctx").attr("value") + '/promotionList/getList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-230",
            limits : [10,15,20,25],
            limit : 10,
            id : "promotionListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'promotionId', title: '促销标准ID', align:"center", width:130},
                {field: 'promotionColName', title: '促销标准', align:"center", width:200},
                {title: '操作', width:80, templet:'#promotionListdBar',fixed:"right",align:"center"}
            ]]
        });
    	$("#promotion").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 500,   //弹出框宽度  
            height : 400,   //弹出框高度  
            title : "促销选择",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	           /* '确定':function(){  
	            //调用登录的方法  
	            }, */ 
	            '取消':function(){  
	            	$('.promotionId').val("");
	            	$('.promotionStandard').val("");
	            	$(this).dialog("close");  
	            }  
            }  
       });  
    })
    
	//选择操作
    table.on('tool(promotionListd)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	$('.promotionId').val(data.promotionId);
    	$('.promotionStandard').val(data.promotionColName);
    	$("#promotion").dialog("close"); 
    });
    //立即添加
    form.on("submit(addFiled)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promoCompare/addRecord.ht',{
        	platformId : $(".platformId").val(), 
        	platformName : $(".platformName").val(), 
        	promotionTypeName : $(".promotionTypeName").val(), 
        	promotionStandard : $(".promotionStandard").val(), 
        	promotionId : $(".promotionId").val(), 
        },function(res){
        	tableIns.reload();
         	top.layer.msg("添加成功！");
         	$(".cxId").val("");
     	    $(".platformId").val("");
     	    $(".platformName").val("");
     	    $(".promotionTypeName").val("");
     	    $(".promotionStandard").val("");
     	    $(".promotionId").val("");
         	$("#promotionTab").dialog("close"); 
        })
        return false;
    })
    //立即修改
    form.on("submit(editFiled)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promoCompare/editRecord.ht',{
    	   id : $(".cxId").val(),
    	   platformId : $(".platformId").val(),  
    	   platformName : $(".platformName").val(), 
    	   promotionTypeName : $(".promotionTypeName").val(), 
    	   promotionStandard : $(".promotionStandard").val(), 
    	   promotionId : $(".promotionId").val(), 
        },function(res){
        	tableIns.reload();
         	top.layer.msg("修改成功！");
         	$(".cxId").val("");
     	    $(".platformId").val("");
     	    $(".platformName").val("");
     	    $(".promotionTypeName").val("");
     	    $(".promotionStandard").val("");
     	    $(".promotionId").val("");
         	$("#promotionTab").dialog("close"); 
        })
        
        return false;
    })
    
	//批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('promotionListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/promoCompare/delAllRecord.ht",{
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
    table.on('tool(promotionList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addPromotion(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('会同时删除含有此促销的非满减价格计算记录，确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/promoCompare/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });
    
    /*
     * 促销标准相关操作
     */
    var PromotionTable = table.render({
        elem: '#promotionAllList',
        url :$("#ctx").attr("value") + '/promotionList/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "promotionAllListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: "checkbox", width:50, fixed:"left"},
            {field: 'promotionId', title: '促销标准ID', align:'center',width:240},
            {field: 'promotionColName', title: '促销标准名称', align:"center", width:380},
            {title: '操作', width:130, templet:'#promotionAllListBar',fixed:"right",align:"center"}
        ]]
    });
    
    var $ = layui.$, active = {  
	    
	    getCheckData: function(){ //获取选中数据  
	      var arr=[];  
	      var checkStatus = table.checkStatus('idTest'),
	      data = checkStatus.data;  
	  
	      for(var i=0;i<data.length;i++){    //循环筛选出id  
	        arr.push(data[i].id);  
	      }  
	      alert(arr)  
	    },  
	  
	  };  
    
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn_pro").on("click",function(){
    	table.reload("promotionAllListTable",{
            page: {
                 curr: 1 //重新从第 1 页开始
             },
             url: $("#ctx").attr("value") + '/promotionList/getList.ht',
             method: 'post',
             where: {
            	 promotionColName : $(".searchValPro").val()
             }
         })
    });
    
    $(".add_btn_pro").click(function(){
    	$(".cxbzId").val(""); 
    	$(".promotionAllId").val("");  
        $(".promotionColName").val(""); 
    	addAllPromotion();
    })
    //添加,编辑
    function addAllPromotion(edit){
    	$(".addRecord").show();
    	$(".editRecord").hide();
    	var title = "添加促销标准";
    	if(edit){
    		title = "编辑促销标准";
            $(".cxbzId").val(edit.id);  
        	$(".promotionAllId").val(edit.promotionId);  
            $(".promotionColName").val(edit.promotionColName); 
    		$(".addRecord").hide();
    		$(".editRecord").show();
        }
    	
    	$("#promotionAllTab").dialog({  
            //autoOpen : false,	//是否自动弹出窗口  
            modal : true,		//设置为模态对话框  
            resizable : true,	//是否可以改变dialog的大小
            width : 720,		//弹出框宽度  
            height : 400,		//弹出框高度  
            title : title,		//弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$(".cxbzId").val(""); 
	            	$(".promotionAllId").val("");  
	                $(".promotionColName").val(""); 
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    }
    
    form.on("submit(addRecord)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promotionList/addRecord.ht',{
       		promotionId : $(".promotionAllId").val(), 
        	promotionColName : $(".promotionColName").val(), 
        },function(res){
        	PromotionTable.reload();
         	top.layer.msg("添加成功！"); 
        	$(".cxbzId").val(""); 
        	$(".promotionAllId").val("");  
            $(".promotionColName").val(""); 
        	$("#promotionAllTab").dialog("close");  
       
        })
        
        return false;
    })
    
    form.on("submit(editRecord)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promotionList/editRecord.ht',{
    	   id : $(".cxbzId").val(), 
    	   promotionId : $(".promotionAllId").val(), 
    	   promotionColName : $(".promotionColName").val(),
        },function(res){
        	PromotionTable.reload();
         	top.layer.msg("修改成功！"); 
        	$(".cxbzId").val(""); 
        	$(".promotionAllId").val("");  
            $(".promotionColName").val(""); 
         	$("#promotionAllTab").dialog("close"); 
        })
        
        return false;
    })
	//批量删除
    $(".delAll_btn_pro").click(function(){
        var checkStatus = table.checkStatus('promotionAllListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('会同时删除含有此促销标准的促销记录，确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/promotionList/delAllRecord.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	PromotionTable.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(promotionAllList)', function(obj){
    	var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addAllPromotion(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('会同时删除含有此促销标准的促销记录，确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/promotionList/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	PromotionTable.reload();
                    layer.close(index);
                })
            });
        }
    });
    
    /*
     * 平台相关操作
     */
    var PlatformTable = table.render({
        elem: '#platformList',
        url :$("#ctx").attr("value") + '/platformList/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "platformListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: "checkbox", width:50, fixed:"left"},
            {field: 'platformId', title: '平台ID', align:'center',width:140},
            {field: 'platformName', title: '平台名称', align:"center", width:240},
            {field: 'platformTableName', title: '数据库名称', align:"center", width:240},
            {title: '操作', width:130, templet:'#platformListBar',fixed:"right",align:"center"}
        ]]
    });
    
    var $ = layui.$, active = {  
	    
	    getCheckData: function(){ //获取选中数据  
	      var arr=[];  
	      var checkStatus = table.checkStatus('idTest'),
	      data = checkStatus.data;  
	  
	      for(var i=0;i<data.length;i++){    //循环筛选出id  
	        arr.push(data[i].id);  
	      }  
	      alert(arr)  
	    },  
	  
	  };  
    
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn_pla").on("click",function(){
    	table.reload("platformListTable",{
             page: {
                 curr: 1 //重新从第 1 页开始
             },
             url: $("#ctx").attr("value") + '/platformList/getList.ht',
             method: 'post',
             where: {
            	 platformName : $(".searchValPla").val()
             }
         })
    });
    
    $(".add_btn_pla").click(function(){
    	 $(".cxptId").val("");  
    	 $(".platformIdpt").val("");  
         $(".platformNamept").val(""); 
         $(".platformTableName").val(""); 
    	addPlatform();
    })
    //添加,编辑
    function addPlatform(edit){
    	$(".addPlatRecord").show();
    	$(".editPlatRecord").hide();
    	var title = "添加平台";
    	if(edit){
    		title = "编辑平台";
            $(".cxptId").val(edit.id);  
        	$(".platformIdpt").val(edit.platformId);  
            $(".platformNamept").val(edit.platformName); 
            $(".platformTableName").val(edit.platformTableName); 
    		$(".addPlatRecord").hide();
    		$(".editPlatRecord").show();
        }
    	
    	$("#platformTab").dialog({  
            //autoOpen : false,	//是否自动弹出窗口  
            modal : true,		//设置为模态对话框  
            resizable : true,	//是否可以改变dialog的大小
            width : 720,		//弹出框宽度  
            height : 400,		//弹出框高度  
            title : title,		//弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){ 
	                $(".cxptId").val("");  
	            	$(".platformIdpt").val("");  
	                $(".platformNamept").val(""); 
	                $(".platformTableName").val(""); 
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    }
   
    form.on("submit(addPlatRecord)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/platformList/addRecord.ht',{
    	   platformId : $(".platformIdpt").val(), 
    	   platformName : $(".platformNamept").val(), 
    	   platformTableName : $(".platformTableName").val(), 
        },function(res){
        	PlatformTable.reload();
         	top.layer.msg("修改成功！"); 
        	$(".cxptId").val(""); 
        	$(".platformIdpt").val("");  
            $(".platformNamept").val("");  
            $(".platformTableName").val(""); 
         	$("#platformTab").dialog("close"); 
        })
        
        return false;
    })
    form.on("submit(editPlatRecord)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/platformList/editRecord.ht',{
    	   id : $(".cxptId").val(), 
    	   platformId : $(".platformIdpt").val(), 
    	   platformName : $(".platformNamept").val(),
    	   platformTableName : $(".platformTableName").val(),
        },function(res){
        	//console.log(res);
        	PlatformTable.reload();
         	top.layer.msg("修改成功！"); 
        	$(".cxptId").val(""); 
        	$(".platformIdpt").val("");  
            $(".platformNamept").val("");  
            $(".platformTableName").val(""); 
         	$("#platformTab").dialog("close"); 
        })
        
        return false;
    })
	//批量删除
    $(".delAll_btn_pla").click(function(){
        var checkStatus = table.checkStatus('platformListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('会同时删除含有此平台的促销记录和非满减价格计算记录，确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/platformList/delAllRecord.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	PlatformTable.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(platformList)', function(obj){
    	var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addPlatform(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('会同时删除含有此平台的促销记录和非满减价格计算记录，确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/platformList/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	PlatformTable.reload();
                    layer.close(index);
                })
            });
        }
    });

    function getRemarkcompare(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "compare"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>促销维护  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullcompare').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }
    function getRemarkpromotion(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "promotion"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>促销标准  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullpromotion').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }
    function getRemarkplatform(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "platform"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>平台标准  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullplatform').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }
})
