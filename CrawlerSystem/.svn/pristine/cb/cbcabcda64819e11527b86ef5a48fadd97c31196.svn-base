<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>api</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  	<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="../../../css/public.css" media="all" />
	<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />
</head>
<body>
 <input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
  <legend id='apiname'>接口名称</legend>
</fieldset>
 
<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
  <ul class="layui-tab-title">
    <li class="layui-this">产品功能</li>
    <li>API文档</li>
    <li>错误码参照</li>
    <li>示例代码</li>
    <li>联系我们</li>
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show" id='productDiv'>
	    <p>1. 当前实况天气，温度、湿度、风向风力</p><br>
	    <p>2. 生活指数，穿衣指数、洗车指数、晨练指数等</p><br>
	    <p>3. 支持GPS坐标直接查询天气 </p><br>
	    <p>4. 覆盖全国2567市县区</p><br>
	    <p>5. 全国天气预报API为企业提供准确、稳定、丰富的天气数据服务。通过API接口，天气数据可以被轻松整合进手机应用，桌面应用或网站。聚合数据API整合多个数据源，智能融合天气数据，让数据更加准确，服务更加稳定。</p>
    </div>
    
    <div class="layui-tab-item">
		<p id='apiaddress'>接口地址：http://v.juhe.cn/weather/index</p><br>
		<p id='backformat'>返回格式：json/xml<p><br>
		<p id='reqmode'>请求方式：get<p><br>
		<p id='reqdemo'>请求示例：http://v.juhe.cn/weather/index?format=2&cityname=%E8%8B%8F%E5%B7%9E&key=您申请的KEY<p><br>
		<p id='apiremarks'>接口备注：</p>
		<p>请求参数说明：<p>
		<table border="1" style="width:100%;" rules="rows" id="reqParam">
		  <tr>
		    <th>名称</th>
		    <th>必填</th>
		    <th>类型</th>
		    <th>说明</th>
		  </tr>
		  <tr>
		    <td>cityname</td>
		    <td>Y</td>
		    <td>string</td>
		    <td>城市名或城市ID，如："苏州"，需要utf8 urlencode</td>
		  </tr>
		  <tr>
		    <td>sdfsdf</td>
		    <td>N</td>
		    <td>string</td>
		    <td>啊师傅打舒服的萨芬</td>
		  </tr>
		</table><br><br>
		返回参数说明：<br>
		<table border="1" style="width:100%;" rules="rows" id="backParam">
		  <tr>
		    <th>名称</th>
		    <th>数值</th>
		    <th>类型</th>
		    <th>说明</th>
		  </tr>
		  <tr>
		    <td>cityname</td>
		    <td>aadsfafas</td>
		    <td>string</td>
		    <td>城市名或城市ID，如："苏州"，需要utf8 urlencode</td>
		  </tr>
		  <tr>
		    <td>sdfsdf</td>
		    <td>aadsfafas</td>
		    <td>string</td>
		    <td>啊师傅打舒服的萨芬</td>
		  </tr>
		</table><br><br>
		JSON返回示例：<br>
    	<textarea rows="50" cols="100">{
	    "resultcode": "200",
	    "reason": "查询成功!",
	    "result": {
	        "sk": {	/*当前实况天气*/
	            "temp": "21",	/*当前温度*/
	            "wind_direction": "西风",	/*当前风向*/
	            "wind_strength": "2级",	/*当前风力*/	
	            "humidity": "4%",	/*当前湿度*/
	            "time": "14:25"	/*更新时间*/
	        },
	        "today": {
	            "city": "天津",
	            "date_y": "2014年03月21日",
	            "week": "星期五",
	            "temperature": "8℃~20℃",	/*今日温度*/
	            "weather": "晴转霾",	/*今日天气*/
	            "weather_id": {	/*天气唯一标识*/
	                "fa": "00",	/*天气标识00：晴*/
	                "fb": "53"	/*天气标识53：霾 如果fa不等于fb，说明是组合天气*/
	            },
	            "wind": "西南风微风",
	            "dressing_index": "较冷", /*穿衣指数*/
	            "dressing_advice": "建议着大衣、呢外套加毛衣、卫衣等服装。",	/*穿衣建议*/
	            "uv_index": "中等",	/*紫外线强度*/
	            "comfort_index": "",/*舒适度指数*/
	            "wash_index": "较适宜",	/*洗车指数*/
	            "travel_index": "适宜",	/*旅游指数*/
	            "exercise_index": "较适宜",	/*晨练指数*/
	            "drying_index": ""/*干燥指数*/
	        },
	        "future": [	/*未来几天天气*/
	            {
	                "temperature": "28℃~36℃",
	                "weather": "晴转多云",
	                "weather_id": {
	                    "fa": "00",
	                    "fb": "01"
	                },
	                "wind": "南风3-4级",
	                "week": "星期一",
	                "date": "20140804"
	            },
	            {
	                "temperature": "28℃~36℃",
	                "weather": "晴转多云",
	                "weather_id": {
	                    "fa": "00",
	                    "fb": "01"
	                },
	                "wind": "东南风3-4级",
	                "week": "星期二",
	                "date": "20140805"
	            },
	            {
	                "temperature": "27℃~35℃",
	                "weather": "晴转多云",
	                "weather_id": {
	                    "fa": "00",
	                    "fb": "01"
	                },
	                "wind": "东南风3-4级",
	                "week": "星期三",
	                "date": "20140806"
	            },
	            {
	                "temperature": "27℃~34℃",
	                "weather": "多云",
	                "weather_id": {
	                    "fa": "01",
	                    "fb": "01"
	                },
	                "wind": "东南风3-4级",
	                "week": "星期四",
	                "date": "20140807"
	            },
	            {
	                "temperature": "27℃~33℃",
	                "weather": "多云",
	                "weather_id": {
	                    "fa": "01",
	                    "fb": "01"
	                },
	                "wind": "东北风4-5级",
	                "week": "星期五",
	                "date": "20140808"
	            },
	            {
	                "temperature": "26℃~33℃",
	                "weather": "多云",
	                "weather_id": {
	                    "fa": "01",
	                    "fb": "01"
	                },
	                "wind": "北风4-5级",
	                "week": "星期六",
	                "date": "20140809"
	            },
	            {
	                "temperature": "26℃~33℃",
	                "weather": "多云",
	                "weather_id": {
	                    "fa": "01",
	                    "fb": "01"
	                },
	                "wind": "北风4-5级",
	                "week": "星期日",
	                "date": "20140810"
	            }
	        ]
		    },
		    "error_code": 0
		}</textarea>
    </div>
    
    <div class="layui-tab-item">
   	 	服务级错误码参照(error_code)：<br><br>
		<table border="1" style="width:100%" rules="rows">
		  <tr>
		    <th>错误码</th>
		    <th>说明</th>
		  </tr>
		  <tr>
		    <td>203901</td>
		    <td>查询城市不能为空</td>
		  </tr>
		  <tr>
		    <td>203902</td>
		    <td>查询不到该城市的天气</td>
		  </tr>
		</table><br><br>
		
   	 	系统级错误码参照(error_code)：<br><br>
		<table border="1" style="width:100%" rules="rows">
		  <tr>
		    <th>错误码</th>
		    <th>说明</th>
		  </tr>
		  <tr>
		    <td>10001</td>
		    <td>错误的请求KEY</td>
		  </tr>
		  <tr>
		    <td>10002</td>
		    <td>该KEY无请求权限</td>
		  </tr>
		</table><br><br>
	</div>
	
    <div class="layui-tab-item">
    	 完整教学代码示例：<br><br>
		<table border="1" style="width:100%" rules="rows">
		  <tr>
		    <th>语言</th>
		    <th>标题</th>
		    <th>提供者</th>
		    <th>时间</th>
		  </tr>
		  <tr>
		    <td>JAVA</td>
		    <td><a href="http://code.juhe.cn/docs/172" class="blue" target="_blank">全国天气预报接口调用示例</a></td>
		    <td>test</td>
		    <td>2018-09-19 10:41:55</td>
		  </tr>
		</table><br><br>
    </div>
    
    <div class="layui-tab-item">
  	   	 联系我们<br><br>
		<table border="1" style="width:100%" rules="rows">
		  <tr>
		    <th>内容</th>
		    <th>详细</th>
		  </tr>
		  <tr>
		    <td>接口测试:</td>
		    <td>API测试工具</td>
		  </tr>
		  <tr>
		    <td>技术支持:</td>
		    <td>info@juhe.cn</td>
		  </tr>
		  <tr>
		    <td>联系电话:</td>
		    <td>info@juhe.cn</td>
		  </tr>
		  <tr>
		    <td>在线客服QQ:</td>
		    <td>800076065</td>
		  </tr>
		</table><br><br>
    </div>
  </div>
</div>      
          
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script>
$(function() {
	//初始化数据方法
	var id = GetQueryString('id');
	getInitData(id);
});
//根据id查询初始化数据
function getInitData(id){
  	$.ajax({
		  url: $("#ctx").attr("value") + '/apiManage/getInitData',
		  type: 'post',
		  data: {
			  "id":id,
		  },
		  dataType: 'json',
		  success: function (data, status) {
			  if(data.code=='200'){
				  //top.layer.msg("成功！");
				  var apiName = data.apiName;
				  var productList = data.productList;
				  var docList = data.docList;
				  $("#apiname").text(apiName.apiname);
				  $("#apiaddress").text("接口地址："+apiName.apiaddress);
				  $("#backformat").text("返回格式："+apiName.backformat);
				  $("#reqmode").text("请求方式："+apiName.reqmode);
				  $("#apiremarks").text("接口备注："+apiName.apiremarks);
				  $("#reqdemo").text("请求示例："+apiName.reqdemo);
				  
				  var str ="";
				  for(var i=0;i<productList.length;i++){
					  str +="<p>"+i+"、"+ productList[i].name+"</p><br>";
				  }
				  $("#productDiv").empty();
				  $("#productDiv").append(str);
				  
				  var str1 ="<tr><th>名称</th><th>数据</th><th>必填</th><th>类型</th><th>说明</th></tr>";
				  var str2 ="<tr><th>名称</th><th>数据</th><th>类型</th><th>说明</th></tr>";
				  for(var i=0;i<docList.length;i++){
					  if(docList[i].flag == "0"){
						  str1 += "<tr>"+
						    "<td>"+docList[i].name+"</td>"+
						    "<td>"+docList[i].datavalue+"</td>"+
						    "<td>"+docList[i].ismust+"</td>"+
						    "<td>"+docList[i].type+"</td>"+
						    "<td>"+docList[i].explains+"</td>"+
						  "</tr>";
					  }else{
						  str2 += "<tr>"+
						    "<td>"+docList[i].name+"</td>"+
						    "<td>"+docList[i].datavalue+"</td>"+
						    "<td>"+docList[i].type+"</td>"+
						    "<td>"+docList[i].explains+"</td>"+
						  "</tr>";
					  }
				  }
				  $("#reqParam").empty();
				  $("#reqParam").append(str1);
				  
				  $("#backParam").empty();
				  $("#backParam").append(str2);
			  }else{
				  top.layer.msg("操作失败！");
			  }
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
}

function GetQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
     if(r!=null)return  unescape(r[2]); return null;
}
layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
  
  //触发事件
  var active = {
    tabAdd: function(){
      //新增一个Tab项
      element.tabAdd('demo', {
        title: '新选项'+ (Math.random()*1000|0) //用于演示
        ,content: '内容'+ (Math.random()*1000|0)
        ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
      })
    }
    ,tabDelete: function(othis){
      //删除指定Tab项
      element.tabDelete('demo', '44'); //删除：“商品管理”
      
      
      othis.addClass('layui-btn-disabled');
    }
    ,tabChange: function(){
      //切换到指定Tab项
      element.tabChange('demo', '22'); //切换到：用户管理
    }
  };
  
  //Hash地址的定位
  var layid = location.hash.replace(/^#test=/, '');
  element.tabChange('test', layid);
  
  element.on('tab(test)', function(elem){
    location.hash = 'test='+ $(this).attr('lay-id');
  });
  
});
</script>

</body>
</html>