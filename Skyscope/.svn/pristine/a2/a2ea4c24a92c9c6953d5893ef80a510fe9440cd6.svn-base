layui.use(['laydate','element','form','layer','table','laytpl'],function(){
	
	var laydate = layui.laydate;

	var element = layui.element;
	var indexTab;
	element.on('tab(filter)', function(data){
		indexTab = data.index;
		
		if(indexTab == 0){
			//searchBtu();
			$(".platformId").val("");
        	$(".getDate").val("");
        	form.render('select','selFilter');
			tableIns.reload();
		}else if(indexTab == 1){
			//searchBtnC();
			$(".platformIdCopy").val("");
        	$(".getDateCopy").val("");
        	form.render('select','selFilter');
			tableCop.reload();
		}else if(indexTab == 2){
			$(".productNameS").val("");
			tableFil.reload();
		}
	});
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	laydate.render({
	    elem: '.getDate',
	    max: 0
	  });
	laydate.render({
	    elem: '.getDateCopy',
	    max: 0
	  });
    //匹配结果
    var tableIns = table.render({
        elem: '#filedList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/productClear/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "filedListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'startTime', title: '产品ID', align:"center", minWidth:110},
            {field: 'endTime', title: '产品名称', align:"center", minWidth:150},
            {field: 'platformIds', title: '抓取产品名称', align:"center", minWidth:150},
            {field: 'addTime', title: '网页ID', align:"center", minWidth:120},
            {field: 'startExecuteTime', title: '产品skuID', align:"center", minWidth:110},
            {field: 'endExecuteTime', title: '店铺ID', align:"center", minWidth:110},
            {field: 'userId', title: '平台', align:"center", minWidth:110,templet:function(d){
                if(d.userId == "45"){
                    return "唯品会";
                }else if(d.userId == "49"){
                	return "网易考拉";
                }
            }},
            {field: 'userName', title: '获取日期', align:"center", minWidth:110},
            {field: 'status', title: '状态', align:"center", minWidth:80,templet:function(d){
                if(d.status == "99"){
                    return "在架";
                }
            }},
            {title: '操作', width:80, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索
    $(".search_btn").on("click",function (){
    	table.reload("filedListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productClear/getList.ht',
            method: 'post',
            where: {
            	platformId : $(".platformId").val(),
            	getDate : $(".getDate").val()
            }
         })
    });
    
    function searchBtu(){
    	table.reload("filedListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productClear/getList.ht',
            method: 'post',
            where: {
            	platformId : $(".platformId").val(),
            	getDate : $(".getDate").val()
            }
         })
    }
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
                $.get($("#ctx").attr("value") + "/productClear/delAllFiled.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	//searchBtn();
                	tableIns.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录！");
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
                $.get($("#ctx").attr("value") + "/productClear/delFiled.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	//searchBtn();
                	tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });
  
    //添加数据
    $(".product_btn").click(function(){
    	if($(".platformId").val() == ''){
    		layer.msg('请选择平台！');
    		return;
    	}
    	if($(".getDate").val() == ''){
    		layer.msg('请选择获取日期！');
    		return;
    	}
    	
    	layer.confirm('确定添加数据记录？', {icon: 3, title: '提示信息'}, function (index) {
            $.get($("#ctx").attr("value") + "/productClear/productAdd.ht",{
            	getDate : $(".getDate").val(),
            	platformId : $(".platformId").val()
            },function(data){
            	top.layer.msg("添加数据成功！");
            	//tableIns.reload();
            	searchBtu();
            	layer.close(index);
            })
        })
    });
    
    //执行入库
    $(".add_btn").click(function(){
    	if($(".platformId").val() == ''){
    		layer.msg('请选择平台！');
    		return;
    	}
    	if($(".getDate").val() == ''){
    		layer.msg('请选择获取日期！');
    		return;
    	}
    	layer.confirm('确定入库记录？', {icon: 3, title: '提示信息'}, function (index) {
            $.get($("#ctx").attr("value") + "/productClear/productAddList.ht",{
            	getDate : $(".getDate").val(),
            	platformId : $(".platformId").val()
            },function(data){
            	top.layer.msg("数据入库成功！");
            	layer.close(index);
            })
        })
    });
    
  //非匹配结果
    var tableCop = table.render({
        elem: '#productList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/productClear/getProductList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "productListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'startTime', title: '产品id', align:"center", minWidth:110},
            {field: 'endTime', title: '产品名称', align:"center", minWidth:200},
            //{field: 'platformIds', title: '抓取产品名称', align:"center", minWidth:150},
            //{field: 'addTime', title: '网页ID', align:"center", minWidth:120},
            {field: 'startExecuteTime', title: '产品skuID', align:"center", minWidth:110},
            {field: 'endExecuteTime', title: '店铺ID', align:"center", minWidth:110},
            {field: 'userId', title: '平台', align:"center", minWidth:110,templet:function(d){
                if(d.userId == "45"){
                    return "唯品会";
                }else if(d.userId == "49"){
                	return "网易考拉";
                }
            }},
            {field: 'userName', title: '获取日期', align:"center", minWidth:110},
            {field: 'status', title: '状态', align:"center", minWidth:80,templet:function(d){
                if(d.status == "99"){
                    return "在架";
                }
            }},
            {title: '操作', width:80, templet:'#productListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索
    $(".search_btn_c").on("click",function(){
    	table.reload("productListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productClear/getProductList.ht',
            method: 'post',
            where: {
            	platformId : $(".platformIdCopy").val(),
            	getDate : $(".getDateCopy").val()
            }
         })
    });

    function searchBtnC(){
    	table.reload("productListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productClear/getProductList.ht',
            method: 'post',
            where: {
            	platformId : $(".platformIdCopy").val(),
            	getDate : $(".getDateCopy").val()
            }
         })
    }
    
    //批量删除
    $(".delAll_btn_c").click(function(){
    	var checkStatus = table.checkStatus('productListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/productClear/delAllProduct.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	//searchBtnC();
                	tableCop.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录！");
        }
    })
    
    //列表操作
    table.on('tool(productList)', function(obj){
        var layEvent = obj.event;
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addFiled(data);
        }else if(layEvent === 'del'){ //删除
        	layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get($("#ctx").attr("value") + "/productClear/delProduct.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	//searchBtnC();
                	tableCop.reload();
                    layer.close(index);
                })
            });
        }
    });
  
	//匹配规则
    var tableFil = table.render({
        elem: '#filterList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/productFilter/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-113",
        limits : [10,15,20,25],
        limit : 10,
        id : "filterListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'productId', title: '产品ID', align:"center", minWidth:90},
            {field: 'productName', title: '产品名称', align:'center',minWidth:280},
            {field: 'filter1', title: '匹配条件1', align:'center',minWidth:90},
            {field: 'filter2', title: '匹配条件2', align:'center',minWidth:200},
            {field: 'filter3', title: '匹配条件3', align:'center',minWidth:90,templet:function(d){
                if(d.filter3 == 0){
                    return "";
                }else{
                	return d.filter3;
                }
            }},
            {title: '操作', width:130, templet:'#filterListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn_f").on("click",function(){
    	table.reload("filterListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productFilter/getList.ht',
            method: 'post',
            where: {
            	productName : $(".productNameS").val()
            }
         })
    });

    $(".add_btn_f").click(function(){
    	$(".productId").val("");
	    $(".productName").val("");
	    $(".filter1").val("");
	    $(".filter21").val("");
	    $(".filter22").val("");
	    $(".filter23").val("");
	    $(".filter3").val("");
    	addFiled();
    });
	//添加
    function addFiled(edit){
    	$(".addFlied").show();
    	$(".editFlied").hide();
    	var title = "添加匹配规则";
    	if(edit){
    		title = "编辑匹配规则";
            $(".filedId").val(edit.id);  
        	$(".productId").val(edit.productId);
            $(".productName").val(edit.productName);
        	$(".filter1").val(edit.filter1);
        	
        	if(edit.filter2.search("-----") != -1){
        		if(edit.filter2.split("-----").length == 2){
        			$(".filter21").val(edit.filter2.split("-----")[0]);
                	$(".filter22").val(edit.filter2.split("-----")[1]);
        		}else{
        			$(".filter21").val(edit.filter2.split("-----")[0]);
                	$(".filter22").val(edit.filter2.split("-----")[1]);
                	$(".filter23").val(edit.filter2.split("-----")[2]);
        		}
        	}else{
        		$(".filter21").val(edit.filter2);
            	$(".filter22").val("");
            	$(".filter23").val("");
        	}
        	if(edit.filter3 == 0){
        		$(".filter3").val("");
        	}else{
        		$(".filter3").val(edit.filter3);
        	}
        	
    		$(".addFlied").hide();
    		$(".editFlied").show();
        }
    	
    	$("#addFiled").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 500,   //弹出框宽度  
            height : 500,   //弹出框高度  
            title : title,  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$(".productId").val("");
	        	    $(".productName").val("");
	        	    $(".filter1").val("");
	        	    $(".filter21").val("");
	        	    $(".filter22").val("");
	        	    $(".filter23").val("");
	        	    $(".filter3").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    };
    //立即添加按钮
    form.on("submit(addFlied)",function(data){
    	var filter2;
    	var filter3;
        if($(".filter21").val() != "" && $(".filter22").val() != "" && $(".filter23").val() != ""){
        	filter2 = $(".filter21").val() + "-----" + $(".filter22").val() + "-----" + $(".filter23").val();
        }else if($(".filter21").val() != "" && $(".filter22").val() != "" && $(".filter23").val() == ""){
        	filter2 = $(".filter21").val() + "-----" + $(".filter22").val();
        }else if($(".filter21").val() != "" && $(".filter22").val() == "" && $(".filter23").val() == ""){
        	filter2 = $(".filter21").val()
        }else {
        	filter2 = "";
        }

        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/productFilter/addFiled.ht',{
    	   productId : $(".productId").val(),
       	   productName : $(".productName").val(),
       	   filter1 : $(".filter1").val(),
       	   filter2 : filter2,
    	   filter3 : $(".filter3").val(),
        },function(res){
        	tableFil.reload();
        	top.layer.msg("添加成功！");
        	$(".productId").val("");
    	    $(".productName").val("");
    	    $(".filter1").val("");
    	    $(".filter21").val("");
    	    $(".filter22").val("");
    	    $(".filter23").val("");
    	    $(".filter3").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    });
    //立即修改按钮
    form.on("submit(editFlied)",function(data){
        var filter2;
        if($(".filter21").val() != "" && $(".filter22").val() != "" && $(".filter23").val() != ""){
        	filter2 = $(".filter21").val() + "-----" + $(".filter22").val() + "-----" + $(".filter23").val();
        }else if($(".filter21").val() != "" && $(".filter22").val() != "" && $(".filter23").val() == ""){
        	filter2 = $(".filter21").val() + "-----" + $(".filter22").val();
        }else if($(".filter21").val() != "" && $(".filter22").val() == "" && $(".filter23").val() == ""){
        	filter2 = $(".filter21").val()
        }else {
        	filter2 = "";
        }
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/productFilter/editFiled.ht',{
    	   id : $(".filedId").val(),
    	   productId : $(".productId").val(),
       	   productName : $(".productName").val(),
       	   filter1 : $(".filter1").val(),
       	   filter2 : filter2,
    	   filter3 : $(".filter3").val(), 
        },function(res){
        	tableFil.reload();
        	top.layer.msg("修改成功！");
        	$(".productId").val("");
    	    $(".productName").val("");
    	    $(".filter1").val("");
    	    $(".filter21").val("");
    	    $(".filter22").val("");
    	    $(".filter23").val("");
    	    $(".filter3").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    })

  //批量删除
    $(".delAll_btn_f").click(function(){
    	var checkStatus = table.checkStatus('filterListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/productFilter/delAllFiled.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	tableFil.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(filterList)', function(obj){
        var layEvent = obj.event;
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	$(".productId").val("");
    	    $(".productName").val("");
    	    $(".filter1").val("");
    	    $(".filter21").val("");
    	    $(".filter22").val("");
    	    $(".filter23").val("");
    	    $(".filter3").val("");
        	addFiled(data);
        }else if(layEvent === 'del'){ //删除
        	layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get($("#ctx").attr("value") + "/productFilter/delFiled.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	tableFil.reload();
                    layer.close(index);
                })
            });
        }
    });
    

})

