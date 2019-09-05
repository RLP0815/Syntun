layui.use(['element','form','layer','table','laytpl'],function(){
	var element = layui.element;
	//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
	var layid = location.hash.replace(/^#test1=/, '');
	element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
	//监听Tab切换，以改变地址hash值
/*	element.on('tab(test1)', function(){
		location.hash = 'test1='+ this.getAttribute('lay-id');
	});*/
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	
	getRemarkstate();
	
	
    /*
     * 特殊促销
     */
    var stateIns = table.render({
        elem: '#productState',
        url :$("#ctx").attr("value") + '/productState/getList.ht',
        cellMinWidth : 70,
        page : true,
        height : "full-68",
        limits : [10,15,20,25],
        limit : 10,
        id : "productStateTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: "checkbox", width:50, fixed:"left"},
            {field: 'stateCode', title: '产品状态码', align:"center", minWidth:100},
            {field: 'productState', title: '产品状态', align:"center", minWidth:100},
            {field: 'errorConnect', title: '下架描述', align:"center", minWidth:150},
            {field: 'platformId', title: '平台ID', align:"center", minWidth:100,templet:function(d){
                if(d.platformId == "0"){
                    return "";
                }else{
                	return d.platformId;
                }
            }},
            {field: 'platformName', title: '平台名称', align:"center", minWidth:100},
            {title: '操作', width:130, templet:'#productStateBar',fixed:"right",align:"center"}
        ]]
    });
    
    //搜索
    $(".search_btn").on("click",function(){
    	table.reload("productStateTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/productState/getList.ht',
            method: 'post',
            where: {
            	errorConnect : $(".searchVal").val()
            }
        })
        
    });
    
    $(".add_btn").click(function(){
    	$(".stId").val("");
	    $(".stateCode").val("");
	    $(".productState").val("");
	    $(".errorConnect").val("");
	    $(".platformId").val("");
	    $(".platformName").val("");
	    form.render('select','selFilter'); //渲染
    	addProduct();
    })
    //添加
    function addProduct(edit){
    	$(".addFiled").show();
    	$(".editFiled").hide();
    	var title = "添加产品状态";
    	if(edit){
    		title = "编辑产品状态";
            $(".stId").val(edit.id);   
        	$(".stateCode").val(edit.stateCode);  
            $(".productState").val(edit.productState); 
            $(".errorConnect").val(edit.errorConnect); 
        	$(".platformId").val(edit.platformId=="0"?"":edit.platformId);  
            $(".platformName").val(edit.platformName);
            form.render('select','selFilter'); //渲染
    		$(".addFiled").hide();
    		$(".editFiled").show();
        }
    	
    	$("#stateTab").dialog({  
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
	            	$(".stId").val("");
	        	    $(".stateCode").val("");
	        	    $(".productState").val("");
	        	    $(".errorConnect").val("");
	        	    $(".platformId").val("");
	        	    $(".platformName").val("");
	        	    form.render('select','selFilter'); //渲染
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    }
    
    //平台选择
    $(".selectPlatform").click(function(){
    	var tablePlad = table.render({
            elem: '#platformList',
            url :$("#ctx").attr("value") + '/platformList/getList.ht',
            cellMinWidth : 70,
            page : true,
            //height : "full-220",
            limits : [10,15,20,25],
            limit : 10,
            id : "platformListTable",
            cols : [[
                {type: 'numbers', width:30, fixed: 'left'},
                {field: 'platformId', title: '平台ID', align:"center", width:130},
                {field: 'platformName', title: '平台名称', align:"center", width:200},
                {title: '操作', width:60, templet:'#platformListBar',fixed:"right",align:"center"}
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
    table.on('tool(platformList)', function(obj){
    	var layEvent = obj.event,
        data = obj.data;
    	$('.platformId').val(data.platformId);
    	$('.platformName').val(data.platformName)
    	$("#platform").dialog("close");  
    	//$("#platform").hide();
    });
    
    //立即添加
    form.on("submit(addFiled)",function(data){
    	$(".productState").val($(".stateCode").find("option:selected").text());
    	if($(".platformId").val() == ""){
    		$(".platformId").val("0");
    	}
    	//弹出loading
    	var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/productState/addFiled.ht',{
    	   stateCode : $(".stateCode").val(), 
    	   productState : $(".productState").val(), 
    	   errorConnect : $(".errorConnect").val(), 
    	   platformId : $(".platformId").val(), 
    	   platformName : $(".platformName").val(),
        },function(res){
        	stateIns.reload();
         	top.layer.msg("添加成功！");
         	$(".stId").val("");
    	    $(".stateCode").val("");
    	    $(".productState").val("");
    	    $(".errorConnect").val("");
    	    $(".platformId").val("");
    	    $(".platformName").val("");
    	    form.render('select','selFilter'); //渲染
         	$("#stateTab").dialog("close"); 
        })
        return false;
    })
    //立即修改
    form.on("submit(editFiled)",function(data){
    	$(".productState").val($(".stateCode").find("option:selected").text());
    	if($(".platformId").val() == ""){
    		$(".platformId").val("0");
    	}
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
       $.post($("#ctx").attr("value") + '/productState/editFiled.ht',{
    	   id : $(".stId").val(),
    	   stateCode : $(".stateCode").val(), 
    	   productState : $(".productState").val(), 
    	   errorConnect : $(".errorConnect").val(), 
    	   platformId : $(".platformId").val(), 
    	   platformName : $(".platformName").val(),
        },function(res){
        	tableIns.reload();
         	top.layer.msg("修改成功！");
         	$(".stId").val("");
    	    $(".stateCode").val("");
    	    $(".productState").val("");
    	    $(".errorConnect").val("");
    	    $(".platformId").val("");
    	    $(".platformName").val("");
    	    form.render('select','selFilter'); //渲染
         	$("#promotionTab").dialog("close"); 
        })
        
        return false;
    })
    
	//批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('productStateTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/productState/delAllFiled.ht",{
                    ids : ids.toString()  //将需要删除的newsId作为参数传入
                },function(data){
                	top.layer.msg("批量删除成功！");
                	stateIns.reload();
                	layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    })

    //列表操作
    table.on('tool(productState)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
        	addProduct(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
            	$.get($("#ctx").attr("value") + "/productState/delFiled.ht",{
                	id : data.id
                },function(data){
                	top.layer.msg("删除成功！");
                	stateIns.reload();
                    layer.close(index);
                })
            });
        }
    });
    
   
    function getRemarkstate(){
      	$.ajax({
      		//url: '../../../json/remark.json',
      		  url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
      		  type: 'post',
      		  data: {"remarkId" : "state"},
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      		    var remarks = '<li>产品状态  - 说明</li>';
      		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullstate').html(remarks);
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
    }
    
})
