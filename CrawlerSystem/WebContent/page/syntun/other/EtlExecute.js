layui.use(['laydate','element','form','layer','table','laytpl'],function(){
	var laydate = layui.laydate;
	var element = layui.element;
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
	//常规用法
	var startTime = laydate.render({
	    elem: '.startTime',
	    //min: '2016-10-14',
	    max: -1,
	    /*ready: function(){
	    	startTime.hint('日期可选值设定在 <br> 当前日期之前');
	    },*/
	    btns: ['clear','confirm']
	  });
	var endTime = laydate.render({
		elem: '.endTime',
		//min: '2016-10-14',
		max: -1,
		/*ready: function(){
			endTime.hint('日期可选值设定在 <br> 当前日期之前');
		},*/
		btns: ['clear','confirm']
	  });
	form.verify({
		platform: [/^[0-9,]+$/,'只能输入数字和逗号组合'] 
	});      
	//获取说明
	getRemark();
    //操作说明列表
    var tableIns = table.render({
        elem: '#etlExecuteList',
        //url : '../../json/userList.json',
        url :$("#ctx").attr("value") + '/etlExecute/getList.ht',
        cellMinWidth : 95,
        page : true,
        height : "full-128",
        limits : [10,15,20,25],
        limit : 10,
        id : "etlExecuteListTable",
        cols : [[
            {type: 'numbers', width:30, fixed: 'left'},
            {field: 'startTime', title: '开始时间', align:"center", minWidth:110},
            {field: 'endTime', title: '结束时间', align:"center", minWidth:110},
            {field: 'platformIds', title: '平台ID', align:"center", minWidth:100},
            {field: 'addTime', title: '入库时间', align:"center", minWidth:170},
            {field: 'startExecuteTime', title: '执行开始时间', align:"center", minWidth:170},
            {field: 'endExecuteTime', title: '执行结束时间', align:"center", minWidth:170},
            {field: 'status', title: '状态', align:"center", minWidth:80,templet:function(d){
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
            {field: 'userName', title: '执行人', align:"center", minWidth:80}/*,
            {title: '操作', width:130, templet:'#newsListBar',fixed:"right",align:"center"}*/
        ]]
    });
    //值小于10时，在前面补0
    function dateFilter(date){
        if(date < 10){return "0"+date;}
        return date;
    }
    //执行按钮
    form.on("submit(execute_btn)",function(data){
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
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/etlExecute/addRecord.ht',{
    	   startTime : $(".startTime").val(),  
    	   endTime : $(".endTime").val(), 
    	   platformIds : $(".platformIds").val(), 
    	   email : email, 
    	   emailText : "您执行数据日期时间段为：" + $(".startTime").val() + "至" + $(".endTime").val() + "，数据执行完成，请注意核查！",
    	   addTime : newDate,
    	   userId : userId,
    	   userName : userName,
    	   status : 0,
        },function(res){
        	$(".startTime").val(""),  
     	   	$(".endTime").val(""), 
     	   	$(".platformIds").val(""), 
     	   //	$(".email").val(""), 
        	tableIns.reload();
        	top.layer.msg("添加成功！");
        })
        
        return false;
    })
 
    //ajax
    function getRemark(){
    	$.ajax({
    		url: $("#ctx").attr("value") + '/editRemark/getAllList.ht',
    		  type: 'post',
    		  data: {remarkId: "execute"},
    		  dataType: 'json',
    		  timeout: 2000,
    		  success: function (data, status) {
    		    var remarks = '<li>执行操作  - 说明</li>';
    		    for(var i=0; i<data.length; i++){
      		    	var j = i+1;
      		    	remarks = remarks + '<li>' + j + '、' + data[i].remark + '</li>';
      		    }
      		    $('.uullExecute').html(remarks);
    		  },
    		  fail: function (err, status) {
    			  top.layer.msg(err);
    		  }
    	})
    }

})
