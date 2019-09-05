<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可乐日报</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<style type="text/css">
	.inputCss{
		    width: 170px;
    		height: 60px;
    		border-radius: 5px;
	}
</style>
</head>
<body class="childrenBody">
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<form class="layui-form" action="${pageContext.request.contextPath}/command/keleexcel">
<div class="layui-tab layui-tab-brief">
	<div class="layui-col-md12 layui-col-xs12">
		<blockquote class="layui-elem-quote quoteBox">
			<div class="layui-inline">
				<div class="demoTable">
				    <div class="layui-inline">
				      <label class="layui-form-label">日期:</label>
				      <div class="layui-input-inline" style="width: 125px">
				        <input type="text" class="layui-input" id="dayId" placeholder="yyyy-MM-dd" name='date'>
				      </div>
				    </div>
				</div>
			</div>	
		</blockquote>
	</div>
</div> 
<div style="text-align: center;">
    <button class="layui-btn">下载</button>
</div>
</form>

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="../crawler/CollectTable.js"></script>
<script type="text/javascript">
    function downLoadExcel(){
    	var index = top.layer.msg('数据获取中，请稍候',{icon: 16,time:false,shade:0.8});
	    var meetingRommId = $("#meetingRommId").val();
	    var dayId = $("#dayId").val();
   	  	$.ajax({
	  		  url: $("#ctx").attr("value") + '/meetingRoom/queryAppointment',
			  type: 'post',
			  data: {
				  "meetingroom":meetingRommId,
				  "meetingroomday":dayId,
			  },
			  dataType: 'json',
			  async:false,
			  success: function (data, status) {
				  if(data.code=='200'){
					  //top.layer.msg("已有人预约！");
					  var meetingInfo = data.meetingInfo;
				      if(meetingInfo.length>0){
				    	  for (var i = 0; i < meetingInfo.length; i++) {
				    		  var dayid = meetingInfo[i].dayid;
				    		  var userName = meetingInfo[i].username;
				    		  var meetingtype = meetingInfo[i].meetingtype;
				    		  var dayidValue = $("#"+dayid).val();
				    		  $("#"+dayid).val(userName+","+dayidValue+","+meetingtype);
				    		  $("#"+dayid).css('background','red')
				    	  }
				      }
				  }else{
					  //top.layer.msg("无人预约！");
				  }
			  },
			  fail: function (err, status) {
				  top.layer.msg("失败");
			  }
		})
    }
	layui.use(['laydate','form','layer','table','laytpl'],function(){
	      var laydate = layui.laydate;
		  	var form = layui.form,
		    layer = parent.layer === undefined ? layui.layer : top.layer,
		    $ = layui.jquery,
		    laytpl = layui.laytpl,
		    table = layui.table;
	      
		  	//下拉框change事件
		  	form.on('select(meetingRommId)', function(data){
	        	//cleanData();
	        });
	      
	      //常规用法
	      laydate.render({
	        elem: '#test1'
	      });
	      //初始赋值和chang事件，
	       laydate.render({
	        elem: '#dayId'
	        ,value: new Date()
	        ,isInitValue: true
	        ,done: function(value, date){
	        	//改变时间处理逻辑
	        	//cleanData();
	        }
	      }); 
	      //国际版
	      laydate.render({
	        elem: '#test1-1'
	        ,lang: 'en'
	      });
	      
	      //年选择器
	      laydate.render({
	        elem: '#test2'
	        ,type: 'year'
	      });
	      
	      //年月选择器
	      laydate.render({
	        elem: '#test3'
	        ,type: 'month'
	      });
	      
	      //时间选择器
	      laydate.render({
	        elem: '#test4'
	        ,type: 'time'
	      });
	      
	      //日期时间选择器
	      laydate.render({
	        elem: '#test5'
	        ,type: 'datetime'
	      });
	      
	      //日期范围
	      laydate.render({
	        elem: '#test6'
	        ,range: true
	      });
	      
	      //年范围
	      laydate.render({
	        elem: '#test7'
	        ,type: 'year'
	        ,range: true
	      });
	      
	      //年月范围
	      laydate.render({
	        elem: '#test8'
	        ,type: 'month'
	        ,range: true
	      });
	      
	      //时间范围
	      laydate.render({
	        elem: '#test9'
	        ,type: 'time'
	        ,range: true
	      });
	      
	      //日期时间范围
	      laydate.render({
	        elem: '#test10'
	        ,type: 'datetime'
	        ,range: true
	      });
	      
	      //自定义格式
	      laydate.render({
	        elem: '#test11'
	        ,format: 'yyyy年MM月dd日'
	      });
	      laydate.render({
	        elem: '#test12'
	        ,format: 'dd/MM/yyyy'
	      });
	      laydate.render({
	        elem: '#test13'
	        ,format: 'yyyyMMdd'
	      });
	      laydate.render({
	        elem: '#test14'
	        ,type: 'time'
	        ,format: 'H点m分'
	      });
	      laydate.render({
	        elem: '#test15'
	        ,type: 'month'
	        ,range: '~'
	        ,format: 'yyyy-MM'
	      });
	      laydate.render({
	        elem: '#test16'
	        ,type: 'datetime'
	        ,range: '到'
	        ,format: 'yyyy年M月d日H时m分s秒'
	      });
	      
	      //开启公历节日
	      laydate.render({
	        elem: '#test17'
	        ,calendar: true
	      });
	      
	      //自定义重要日
	      laydate.render({
	        elem: '#test18'
	        ,mark: {
	          '0-10-14': '生日'
	          ,'0-12-31': '跨年' //每年的日期
	          ,'0-0-10': '工资' //每月某天
	          ,'0-0-15': '月中'
	          ,'2017-8-15': '' //如果为空字符，则默认显示数字+徽章
	          ,'2099-10-14': '呵呵'
	        }
	        ,done: function(value, date){
	          if(date.year === 2017 && date.month === 8 && date.date === 15){ //点击2017年8月15日，弹出提示语
	            layer.msg('这一天是：中国人民抗日战争胜利72周年');
	          }
	        }
	      });
	      
	      //限定可选日期
	      var ins22 = laydate.render({
	        elem: '#test-limit1'
	        ,min: '2016-10-14'
	        ,max: '2080-10-14'
	        ,ready: function(){
	          ins22.hint('日期可选值设定在 <br> 2016-10-14 到 2080-10-14');
	        }
	      });
	      
	      //前后若干天可选，这里以7天为例
	      laydate.render({
	        elem: '#test-limit2'
	        ,min: -7
	        ,max: 7
	      });
	      
	      //限定可选时间
	      laydate.render({
	        elem: '#test-limit3'
	        ,type: 'time'
	        ,min: '09:30:00'
	        ,max: '17:30:00'
	        ,btns: ['clear', 'confirm']
	      });
	      
	      //同时绑定多个
	      lay('.test-item').each(function(){
	        laydate.render({
	          elem: this
	          ,trigger: 'click'
	        });
	      });
	      

	      
	      //选中后的回调
	      laydate.render({
	        elem: '#test20'
	        ,done: function(value, date){
	          layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
	        }
	      });
	      
	      //日期切换的回调
	      laydate.render({
	        elem: '#test21'
	        ,change: function(value, date){
	          layer.msg('你选择的日期是：' + value + '<br><br>获得的对象是' + JSON.stringify(date));
	        }
	      });
	      //不出现底部栏
	      laydate.render({
	        elem: '#test22'
	        ,showBottom: false
	      });
	      
	      //只出现确定按钮
	      laydate.render({
	        elem: '#test23'
	        ,btns: ['confirm']
	      });
	      
	      //自定义事件
	      laydate.render({
	        elem: '#test24'
	        ,trigger: 'mousedown'
	      });
	      
	      //点我触发
	      laydate.render({
	        elem: '#test25'
	        ,eventElem: '#test25-1'
	        ,trigger: 'click'
	      });
	      
	      //双击我触发
	      lay('#test26-1').on('dblclick', function(){
	        laydate.render({
	          elem: '#test26'
	          ,show: true
	          ,closeStop: '#test26-1'
	        });
	      });
	      
	      //日期只读
	      laydate.render({
	        elem: '#test27'
	        ,trigger: 'click'
	      });
	      
	      //非input元素
	      laydate.render({
	        elem: '#test28'
	      });
	      
	      //墨绿主题
	      laydate.render({
	        elem: '#test29'
	        ,theme: 'molv'
	      });
	      
	      //自定义颜色
	      laydate.render({
	        elem: '#test30'
	        ,theme: '#393D49'
	      });
	      
	      //格子主题
	      laydate.render({
	        elem: '#test31'
	        ,theme: 'grid'
	      });
	      
	      
	      //直接嵌套显示
	      laydate.render({
	        elem: '#test-n1'
	        ,position: 'static'
	      });
	      laydate.render({
	        elem: '#test-n2'
	        ,position: 'static'
	        ,lang: 'en'
	      });
	      laydate.render({
	        elem: '#test-n3'
	        ,type: 'month'
	        ,position: 'static'
	      });
	      laydate.render({
	        elem: '#test-n4'
	        ,type: 'time'
	        ,position: 'static'
	      });
	    });
</script>
</body>
</html>