layui.use(['upload','laydate','element','form','layer','table','laytpl'],function(){
	
	var element = layui.element;
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        upload = layui.upload;
	
	//选完文件后不自动上传
	upload.render({
	    elem: '#test8',
	    accept: 'file',
	    exts:'xlsx|xls',
	    url:$("#ctx").attr("value") + '/crawler144DataTableFiledList/upload.ht',	//  /CrawlerSystem
	    method: 'post',
	    auto: false,
	    bindAction: '#test9',
	    done: function(res){
	    	if(res.code > 0){ 
	    		alert("上传失败，"+res.msg); 
	    		return layer.msg('上传失败！'); 
          } 
          //上传成功 
          alert("上传成功，"+res.msg); 
          tableIns.reload();
          $("#addFiled").dialog("close"); 
          return layer.msg('上传成功'); 
	    },
	    error: function(index, upload){
	    	top.layer.msg("上传失败！error！");
	      }
	});
	
	//获取说明
	getRemark();
    //替换字段列表
    var tableIns = table.render({
        elem: '#filedList',
        url :$("#ctx").attr("value") + '/crawler144DataTableFiledList/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-95",
        limits : [10,15,20,25],
        limit : 10,
        id : "filedListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'tableId', title: 'table_id', align:"center", minWidth:180},
            {field: 'filedName', title: 'filed_name', align:'center',minWidth:100},
            {field: 'filedDataType', title: 'filed_data_type', align:'center',minWidth:100},
            {field: 'defaultValue', title: 'default_value', align:'center',minWidth:120},
            {field: 'filedDataFun', title: 'filed_data_fun', align:'center',minWidth:100},
            {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	table.reload("filedListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/crawler144DataTableFiledList/getList.ht',
            method: 'post',
            where: {
            	filedName : $(".searchVal").val()
            }
         })
    });

    $(".add_btn").click(function(){
    	$(".tableId").val("");
	    $(".filedName").val("");
	    $(".filedDataType").val("");
	    $(".defaultValue").val("");
	    $(".filedDataFun").val("");
    	addFiled();
    });
  //添加
    function addFiled(edit){
    	$(".filedDataType").val($('#IsPurchased input[name="filedDataType"]:checked').val());
    	$(".addFlied").show();
    	$(".editFlied").hide();
    	var title = "添加data_table_filed_list";
    	if(edit){
    		title = "编辑data_table_filed_list";
    		$("input[name=filedDataType][value=1]").attr("checked", edit.filedDataType == 1 ? true : false);
    		$("input[name=filedDataType][value=2]").attr("checked", edit.filedDataType == 2 ? true : false);
            form.render(); //更新全部
            
            $(".filedId").val(edit.filedId);  
        	$(".tableId").val(edit.tableId);
            $(".filedName").val(edit.filedName);
            $(".filedDataType").val(edit.filedDataType);
            $(".defaultValue").val(edit.defaultValue);
            $(".filedDataFun").val(edit.filedDataFun);
    		$(".addFlied").hide();
    		$(".editFlied").show();
        }
    	
    	$("#addFiled").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 600,   //弹出框宽度  
            height : 500,   //弹出框高度  
            title : title,  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$(".tableId").val("");
            	    $(".filedName").val("");
            	    $(".filedDataType").val("");
            	    $(".defaultValue").val("");
            	    $(".filedDataFun").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    };
    //立即添加按钮
    form.on("submit(addFlied)",function(data){
    	$(".filedDataType").val($('#IsPurchased input[name="filedDataType"]:checked').val());
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/crawler144DataTableFiledList/addRecord.ht',{
    	    tableId : $(".tableId").val(),
        	filedName : $(".filedName").val(),
        	filedDataType : $(".filedDataType").val(),
        	defaultValue : $(".defaultValue").val(),
        	filedDataFun : $(".filedDataFun").val(),
        },function(res){
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        	$(".tableId").val("");
    	    $(".filedName").val("");
    	    $(".filedDataType").val("");
    	    $(".defaultValue").val("");
    	    $(".filedDataFun").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    });
    //立即修改按钮
    form.on("submit(editFlied)",function(data){
    	$(".filedDataType").val($('#IsPurchased input[name="filedDataType"]:checked').val());
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/crawler144DataTableFiledList/editRecord.ht',{
    	   filedId : $(".filedId").val(),
    	   tableId : $(".tableId").val(),
    	   filedName : $(".filedName").val(), 
    	   filedDataType : $(".filedDataType").val(), 
    	   defaultValue : $(".defaultValue").val(), 
    	   filedDataFun : $(".filedDataFun").val(), 
        },function(res){
        	tableIns.reload();
        	top.layer.msg("修改成功！");
        	$(".tableId").val("");
    	    $(".filedName").val("");
    	    $(".filedDataType").val("");
    	    $(".defaultValue").val("");
    	    $(".filedDataFun").val("");
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
            	ids.push(data[i].filedId);
            }
            layer.confirm('确定删除选中的记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get($("#ctx").attr("value") + "/crawler144DataTableFiledList/delAllRecord.ht",{
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
                $.get($("#ctx").attr("value") + "/crawler144DataTableFiledList/delRecord.ht",{
                	id : data.filedId
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
  		  data: {"remarkId" : "crawler144DataTableFiledList"},
  		  dataType: 'json',
  		  timeout: 2000,
  		  success: function (data, status) {
  		    var remarks = '<li>data_table_filed_list  - 说明</li>';
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