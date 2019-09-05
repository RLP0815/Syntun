layui.use(['element','form','layer','table','laytpl'],function(){
	var element = layui.element;
	var indexTab;
	element.on('tab(filter)', function(data){
		indexTab = data.index;
		
		if(indexTab == 0){
			tableIns.reload();
		}else if(indexTab == 1){
			tableRep.reload();
		}/*else if(indexTab == 2){
			tablePri.reload();
		}*/
	});
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	getRemarkpattern();
	getRemarkreplace();
	//getRemarkprice();
	/*
	 * 满减正则列表
	 */
    var tableIns = table.render({
        elem: '#patternList',
        url :$("#ctx").attr("value") + '/patternList/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "patternListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'priority', title: '优先级', align:"center", minWidth:60},
            {field: 'patternType', title: '正则类型', align:"center", minWidth:60},
            {field: 'patternStr', title: '正则内容', align:"center", minWidth:200},
            {field: 'manGroup', title: '满的group', align:"center", minWidth:100},
            {field: 'jieGroup', title: '减的group', align:"center", minWidth:100},
            {field: 'isDazhe', title: '是否满打折', align:"center", minWidth:100,templet:function(d){
                if(d.isDazhe == "0"){
                    return "否";
                }else if(d.isDazhe == "1"){
                    return "是";
                }
            }},
            {field: 'isMeiman', title: '是否每满', align:"center", minWidth:60,templet:function(d){
                return d.isMeiman == "0" ? "否" : "是";
            }},
            {title: '操作', width:120, templet:'#patternListBar',fixed:"right",align:"center"}
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
    	table.reload("patternListTable",{
           page: {
              curr: 1 //重新从第 1 页开始
           },
           url: $("#ctx").attr("value") + '/patternList/getList.ht',
           method: 'post',
           where: {
        	   patternStr : $(".searchVal").val()
           }
       })
        
    });
    
    $(".add_btn").click(function(){
    	$(".zzId").val("");  
    	$(".priorityPat").val("");
        $(".patternType").val("");
        $(".patternStr").val(""); 
        $(".manGroup").val(""); 
        $(".jieGroup").val("");  
        $(".isDazhe").val("");  
        $(".isMeiman").val("");  
        form.render('select','selFilter'); //渲染
    	addPattern();
    })
    //添加
    function addPattern(edit){
    	$(".addPatRecord").show();
    	$(".editPatRecord").hide();
    	var title = "添加正则";
    	if(edit){
    		title = "编辑正则";
    		$(".zzId").val(edit.id);  
        	$(".priorityPat").val(edit.priority);
            $(".patternType").val(edit.patternType);
            $(".patternStr").val(edit.patternStr); 
            $(".manGroup").val(edit.manGroup); 
            $(".jieGroup").val(edit.jieGroup);  
            $(".isDazhe").val(edit.isDazhe);  
            $(".isMeiman").val(edit.isMeiman);  
            form.render('select','selFilter'); //渲染
    		$(".addPatRecord").hide();
    		$(".editPatRecord").show();
        }
    	
    	$("#patternTab").dialog({  
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
	                $(".zzId").val("");  
	            	$(".priorityPat").val("");
	                $(".patternType").val("");
	                $(".patternStr").val(""); 
	                $(".manGroup").val(""); 
	                $(".jieGroup").val("");  
	                $(".isDazhe").val("");  
	                $(".isMeiman").val("");  
	                form.render('select','selFilter'); //渲染
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    }
   
    form.on("submit(addPatRecord)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/patternList/addRecord.ht',{
    	   priority : $(".priorityPat").val(), 
    	   patternType : $(".patternType").val(), 
    	   patternStr : $(".patternStr").val(), 
    	   manGroup : $(".manGroup").val(), 
    	   jieGroup : $(".jieGroup").val(), 
    	   isDazhe : $(".isDazhe").val(), 
    	   isMeiman : $(".isMeiman").val(), 
        },function(res){
        	tableIns.reload();
         	top.layer.msg("添加成功！"); 
            $(".zzId").val("");  
        	$(".priorityPat").val("");
            $(".patternType").val("");
            $(".patternStr").val(""); 
            $(".manGroup").val(""); 
            $(".jieGroup").val("");  
            $(".isDazhe").val("");  
            $(".isMeiman").val("");  
            form.render('select','selFilter'); //渲染
         	$("#patternTab").dialog("close"); 
        })
        
        return false;
    })
    form.on("submit(editPatRecord)",function(data){
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/patternList/editRecord.ht',{
    	   id : $(".zzId").val(), 
    	   priority : $(".priorityPat").val(), 
    	   patternType : $(".patternType").val(), 
    	   patternStr : $(".patternStr").val(), 
    	   manGroup : $(".manGroup").val(), 
    	   jieGroup : $(".jieGroup").val(), 
    	   isDazhe : $(".isDazhe").val(), 
    	   isMeiman : $(".isMeiman").val(), 
        },function(res){
        	tableIns.reload();
         	top.layer.msg("编辑成功！"); 
            $(".zzId").val("");  
        	$(".priorityPat").val("");
            $(".patternType").val("");
            $(".patternStr").val(""); 
            $(".manGroup").val(""); 
            $(".jieGroup").val("");  
            $(".isDazhe").val("");  
            $(".isMeiman").val("");  
            form.render('select','selFilter'); //渲染
         	$("#patternTab").dialog("close"); 
        })
        
        return false;
    })
	//批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('patternListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/patternList/delAllRecord.ht",{
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
    table.on('tool(patternList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addPattern(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/patternList/delRecord.ht",{
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
	 * 满减字符替换列表
	 */
    var tableRep = table.render({
        elem: '#replaceList',
        url :$("#ctx").attr("value") + '/promotionReplace/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "replaceListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'priority', title: '优先级', align:"center", width:100},
            {field: 'replaceStr', title: '原始字符', align:"center", width:270},
            {field: 'endStr', title: '替换字符', align:"center", width:270},
            {title: '操作', width:120, templet:'#replaceListBar',fixed:"right",align:"center"}
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
    $(".search_btn_rep").on("click",function(){
    	table.reload("replaceListTable",{
           page: {
              curr: 1 //重新从第 1 页开始
           },
           url: $("#ctx").attr("value") + '/promotionReplace/getList.ht',
           method: 'post',
           where: {
        	   replaceStr : $(".searchValRep").val()
           }
       })
        
    });
    
    $(".add_btn_rep").click(function(){
    	$(".thId").val("");  
	    $(".priorityRep").val("");
	    $(".replaceStr").val("");
	    $(".endStr").val("");
    	addReplace();
    })
    //添加
    function addReplace(edit){
    	$(".addRepRecord").show();
    	$(".editRepRecord").hide();
    	var title = "添加替换字符";
    	if(edit){
    		title = "编辑替换字符";
            $(".thId").val(edit.id);  
    	    $(".priorityRep").val(edit.priority);
    	    $(".replaceStr").val(edit.replaceStr);
    	    $(".endStr").val(edit.endStr);
    		$(".addRepRecord").hide();
    		$(".editRepRecord").show();
        }
    	
    	$("#replaceTab").dialog({  
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
	                $(".thId").val("");  
	        	    $(".priorityRep").val("");
	        	    $(".replaceStr").val("");
	        	    $(".endStr").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
    	/*
    	var page = "ReplaceAdd.jsp";
    	var title = "添加替换字符";
    	if(edit){
            page = "ReplaceEdit.jsp";
            title = "编辑替换字符";
        }
    	
    	var index = layui.layer.open({
            title : title,
            type : 2,
            content : page,
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                	body.find(".id").val(edit.id);  
                	body.find(".priority").val(edit.priority);
                    body.find(".replaceStr").val(edit.replaceStr);
                    body.find(".endStr").val(edit.endStr); 
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回主列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })*/
    }
    form.on("submit(addRepRecord)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promotionReplace/addRecord.ht',{
    	   priority : $(".priorityRep").val(),
    	   replaceStr : $(".replaceStr").val(),
    	   endStr : $(".endStr").val(),
        },function(res){
        	tableRep.reload();
         	top.layer.msg("添加成功！"); 
            $(".thId").val("");  
    	    $(".priorityRep").val("");
    	    $(".replaceStr").val("");
    	    $(".endStr").val("");
         	$("#replaceTab").dialog("close"); 
        })
        
        return false;
    })
    form.on("submit(editRepRecord)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/promotionReplace/editRecord.ht',{
    	   id : $(".id").val(),
    	   priority : $(".priority").val(),  
    	   replaceStr : $(".replaceStr").val(), 
    	   endStr : $(".endStr").val(), 
        },function(res){
        	tableRep.reload();
         	top.layer.msg("修改成功！"); 
            $(".thId").val("");  
    	    $(".priorityRep").val("");
    	    $(".replaceStr").val("");
    	    $(".endStr").val("");
         	$("#replaceTab").dialog("close"); 
        })
        
        return false;
    })
	//批量删除
    $(".delAll_btn_rep").click(function(){
        var checkStatus = table.checkStatus('replaceListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/promotionReplace/delAllRecord.ht",{
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
    table.on('tool(replaceList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addReplace(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/promotionReplace/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableRep.reload();
                    layer.close(index);
                })
            });
        }
    });
    
    /*
	 * 非满减价格计算公式列表
	 */
//    var tablePri = table.render({
//        elem: '#priceList',
//        url :$("#ctx").attr("value") + '/priceCompute/getList.ht',
//        cellMinWidth : 70,
//        page : true,
//        height : "full-113",
//        limits : [10,15,20,25],
//        limit : 10,
//        id : "priceListTable",
//        cols : [[
//            {type: 'numbers', width:30, fixed: 'left'},
//            {type: 'checkbox', fixed: 'left'},
//            {field: 'platformId', title: '平台ID', align:"center", width:120},
//            {field: 'promotionTypeName', title: '促销名称', align:"center", width:120},
//            {field: 'promotionPrice', title: '促销价计算公式', align:"center", width:200},
//            {field: 'price', title: '实际价计算公式', align:"center", width:200},
//            {title: '操作', width:120, templet:'#priceListBar',fixed:"right",align:"center"}
//        ]]
//    });
//    
//    var $ = layui.$, active = {  
//	    
//	    getCheckData: function(){ //获取选中数据  
//	      var arr=[];  
//	      var checkStatus = table.checkStatus('idTest'),
//	      data = checkStatus.data;  
//	  
//	      for(var i=0;i<data.length;i++){    //循环筛选出id  
//	        arr.push(data[i].id);  
//	      }  
//	      alert(arr)  
//	    },  
//	  
//	  };  
//    
//    //搜索
//    $(".search_btn_pri").on("click",function(){
//    	table.reload("priceListTable",{
//           page: {
//              curr: 1 //重新从第 1 页开始
//           },
//           url: $("#ctx").attr("value") + '/priceCompute/getList.ht',
//           method: 'post',
//           where: {
//        	   promotionTypeName : $(".searchValPri").val()
//           }
//       })
//        
//    });
//    
//    $(".add_btn_pri").click(function(){
//    	$(".jgId").val("");  
//	    $(".platformId").val("");
//	    $(".promotionTypeName").val("");
//	    $(".promotionPrice").val("");
//	    $(".price").val("");
//    	addPrice();
//    })
//    //添加
//    function addPrice(edit){
//    	$(".addPriRecord").show();
//    	$(".editPriRecord").hide();
//    	var title = "添加价格计算公式";
//    	if(edit){
//    		title = "编辑价格计算公式";
//            $(".jgId").val(edit.id); 
//            $(".platformId").val(edit.platformId);
//     	    $(".promotionTypeName").val(edit.promotionTypeName);
//    	    $(".promotionPrice").val(edit.promotionPrice);
//    	    $(".price").val(edit.price);
//    		$(".addPriRecord").hide();
//    		$(".editPriRecord").show();
//        }
//    	
//    	$("#priceTab").dialog({  
//            //autoOpen : false,	//是否自动弹出窗口  
//            modal : true,		//设置为模态对话框  
//            resizable : true,	//是否可以改变dialog的大小
//            width : 720,		//弹出框宽度  
//            height : 400,		//弹出框高度  
//            title : title,		//弹出框标题  
//            position: { my: "center top", at: "center top", of: window },
//            closeText : '关闭',
//            buttons:{  
//            	/*'添加':function(data){  
//            		 
//	            }, */
//	            '取消':function(){ 
//	                $(".jgId").val("");
//	                $(".platformId").val("");
//	         	    $(".promotionTypeName").val("");
//	        	    $(".promotionPrice").val("");
//	        	    $(".price").val("");
//	            	$(this).dialog("close");  
//	            }  
//            } 
//       }); 
//    	
//    }
    $(".selectPromotion").click(function(){
    	var tablePro = table.render({
            elem: '#promotionList',
            url :$("#ctx").attr("value") + '/promoCompare/getList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-200",
            limits : [10,15,20,25],
            limit : 10,
            id : "promotionListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'platformId', title: '平台ID', align:"center", width:80},
                {field: 'platformName', title: '平台名称', align:"center", width:80},
                {field: 'promotionTypeName', title: '促销名称', align:"center", width:100},
                {field: 'promotionStandard', title: '促销标准', align:"center", width:100},
                {field: 'promotionId', title: '促销标准ID', align:'center',width:100},
                {title: '操作', width:80, templet:'#promotionListBar',fixed:"right",align:"center"}
            ]]
        });
    	
    	$("#promotion").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 630,   //弹出框宽度  
            height : 410,   //弹出框高度  
            title : "平台/促销选择",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
	           /* '确定':function(){  
	            //调用登录的方法  
	            }, */ 
	            '取消':function(){  
	            	$('.platformId').val("");
	            	$('.promotionTypeName').val("");
	            	$(this).dialog("close");  
	            }  
            }  
       });  
    })
    //选择操作
    table.on('tool(promotionList)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	$('.platformId').val(data.platformId);
    	$('.promotionTypeName').val(data.promotionTypeName);
        
    	$("#promotion").dialog("close"); 
    });
    
    form.on("submit(addPriRecord)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/priceCompute/addRecord.ht',{
    	   platformId : $(".platformId").val(), 
    	   promotionTypeName : $(".promotionTypeName").val(), 
    	   promotionPrice : $(".promotionPrice").val(), 
    	   price : $(".price").val(), 
        },function(res){
        	tablePri.reload();
         	top.layer.msg("添加成功！"); 
            $(".jgId").val("");  
    	    $(".platformId").val("");
    	    $(".promotionTypeName").val("");
    	    $(".promotionPrice").val("");
    	    $(".price").val("");
         	$("#priceTab").dialog("close"); 
        })
        
        return false;
    })
    
    form.on("submit(editPriRecord)",function(data){
        //弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/priceCompute/editRecord.ht',{
    	   id : $(".jgId").val(), 
    	   platformId : $(".platformId").val(), 
    	   promotionTypeName : $(".promotionTypeName").val(), 
    	   promotionPrice : $(".promotionPrice").val(), 
    	   price : $(".price").val(), 
        },function(res){
        	tablePri.reload();
         	top.layer.msg("修改成功！"); 
            $(".jgId").val("");  
    	    $(".platformId").val("");
    	    $(".promotionTypeName").val("");
    	    $(".promotionPrice").val("");
    	    $(".price").val("");
         	$("#priceTab").dialog("close"); 
        })
        
        return false;
    })
	//批量删除
    $(".delAll_btn_pri").click(function(){
        var checkStatus = table.checkStatus('priceListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/priceCompute/delAllRecord.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	tablePri.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(priceList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
        	addPrice(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/priceCompute/delRecord.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tablePri.reload();
                    layer.close(index);
                })
            });
        }
    });
    
    function getRemarkpattern(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "pattern"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>满减正则  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullpattern').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }
    function getRemarkreplace(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "replace"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>满减字符替换  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullreplace').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      }
//    function getRemarkprice(){
//      	$.ajax({
//      		//url: '../../../json/remark.json',
//      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
//      		  type: 'post',
//      		  data: {"remarkId" : "price"},
//      		  dataType: 'json',
//      		  timeout: 2000,
//      		  success: function (data, status) {
//      		    var remarks = '<li>非满减价格计算  - 说明</li>';
//      		    for(var i=0; i<data.length; i++){
//      		    	var j = i+1;
//      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
//      		    }
//      		    $('.uullprice').html(remarks);
//      		  },
//      		  fail: function (err, status) {
//      			  alert(status);
//      		  }
//      	})
//      }

})