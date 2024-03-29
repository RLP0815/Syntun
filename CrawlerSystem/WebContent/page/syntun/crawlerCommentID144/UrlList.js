layui.use(['upload','element','form','layer','table','laytpl'],function(){
	
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
	    url:$("#ctx").attr("value") + '/crawlerCommentJD144UrlList/upload.ht',	//  /CrawlerSystem
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
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/crawlerCommentJD144UrlList/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-95",
        limits : [10,15,20,25],
        limit : 10,
        id : "filedListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {type: 'checkbox', fixed: 'left'},
            {field: 'urlGroup', title: 'url_group', align:'center',minWidth:100},
            {field: 'urlMd5', title: 'url_md5', align:'center',minWidth:100},
            {field: 'urlStr', title: 'url_str', align:"center", minWidth:180},
            {field: 'fatherUrl', title: 'father_url', align:'center',minWidth:100},
            {field: 'urlData', title: 'url_data', align:'center',minWidth:100},
            {field: 'urlCharset', title: 'url_charset', align:'center',minWidth:120},
            {field: 'sortId', title: 'sort_id', align:'center',minWidth:100},
            {title: '操作', width:120, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	table.reload("filedListTable",{
    		page: {
    			curr: 1 //重新从第 1 页开始
            },
            url: $("#ctx").attr("value") + '/crawlerCommentJD144UrlList/getList.ht',
            method: 'post',
            where: {
            	urlStr : $(".searchVal").val()
            }
         })
         //$(".abstract").val("1、修改刚进入页面无任何操作时按回车键提示“请输入解锁密码！”%OD%OA2、优化关闭弹窗按钮的提示信息位置问题【可能是因为加载速度的原因，造成这个问题，所以将提示信息做了一个延时】%OD%OA3、“个人资料”提供修改功能4、顶部天气信息自动判断位置【忘记之前是怎么想的做成北京的了，可能是我在大首都吧，哈哈。。。】")
    });

    $(".add_btn").click(function(){
	    $(".urlGroup").val("");
	    $(".urlMd5").val("");
    	$(".urlStr").val("");
	    $(".fatherUrl").val("");
	    $(".urlData").val("");
	    $(".urlCharset").val("");
	    $(".sortId").val("");
    	addFiled();
    });
  //添加
    function addFiled(edit){
    	$(".urlStatus").val($('#IsPurchased input[name="urlStatus"]:checked').val());
    	$(".addFlied").show();
    	$(".editFlied").hide();
    	var title = "添加URL配置";
    	if(edit){
    		title = "编辑URL配置";
            
            $(".filedId").val(edit.id);  
            $(".urlGroup").val(edit.urlGroup);
            $(".urlMd5").val(edit.urlMd5);
        	$(".urlStr").val(edit.urlStr);
            $(".fatherUrl").val(edit.fatherUrl);
            $(".urlData").val(edit.urlData);
            $(".urlCharset").val(edit.urlCharset);
            $(".sortId").val(edit.sortId);
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
            	    $(".urlGroup").val("");
            	    $(".urlMd5").val("");
	            	$(".urlStr").val("");
            	    $(".fatherUrl").val("");
            	    $(".urlData").val("");
            	    $(".urlCharset").val("");
            	    $(".sortId").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       });  
    	
    };
    //立即添加按钮
    form.on("submit(addFlied)",function(data){
    	$(".urlStatus").val($('#IsPurchased input[name="urlStatus"]:checked').val());
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/crawlerCommentJD144UrlList/addRecord.ht',{
       		urlGroup : $(".urlGroup").val(),
       		urlMd5 : $(".urlMd5").val(),
    	    urlStr : $(".urlStr").val(),
    	    fatherUrl : $(".fatherUrl").val(),
        	urlData : $(".urlData").val(),
        	urlCharset : $(".urlCharset").val(),
        	sortId : $(".sortId").val(),
        },function(res){
        	tableIns.reload();
        	top.layer.msg("添加成功！");
    	    $(".urlGroup").val("");
    	    $(".urlMd5").val("");
        	$(".urlStr").val("");
    	    $(".fatherUrl").val("");
    	    $(".urlData").val("");
    	    $(".urlCharset").val("");
    	    $(".sortId").val("");
        	$("#addFiled").dialog("close"); 
        })
        
        return false;
    });
    //立即修改按钮
    form.on("submit(editFlied)",function(data){
    	$(".urlStatus").val($('#IsPurchased input[name="urlStatus"]:checked').val());
        //弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/crawlerCommentJD144UrlList/editRecord.ht',{
    	   id : $(".filedId").val(),
    	   urlGroup : $(".urlGroup").val(), 
    	   urlMd5 : $(".urlMd5").val(), 
    	   urlStr : $(".urlStr").val(),
    	   fatherUrl : $(".fatherUrl").val(),
    	   urlData : $(".urlData").val(), 
    	   urlCharset : $(".urlCharset").val(), 
    	   sortId : $(".sortId").val(), 
        },function(res){
        	tableIns.reload();
        	top.layer.msg("修改成功！");
    	    $(".urlGroup").val("");
    	    $(".urlMd5").val("");
        	$(".urlStr").val("");
    	    $(".fatherUrl").val("");
    	    $(".urlData").val("");
    	    $(".urlCharset").val("");
    	    $(".sortId").val("");
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
                $.get($("#ctx").attr("value") + "/crawlerCommentJD144UrlList/delAllRecord.ht",{
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
                $.get($("#ctx").attr("value") + "/crawlerCommentJD144UrlList/delRecord.ht",{
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
  		  data: {"remarkId" : "crawlerCommentJD144UrlList"},
  		  dataType: 'json',
  		  timeout: 2000,
  		  success: function (data, status) {
  		    var remarks = '<li>init_url_list  - 说明</li>';
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
