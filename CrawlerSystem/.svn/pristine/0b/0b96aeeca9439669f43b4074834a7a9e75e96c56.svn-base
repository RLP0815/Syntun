<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台用户界面</title>
    <link rel="icon" type="image/x-icon" href="img/icon.ico" />
    <link rel="stylesheet" href="../../../layui/css/layui.css"  media="all">
    <style>
    .site-doc-icon li{display: inline-block;vertical-align: middle;width: 127px;line-height: 25px;padding: 20px 0;margin-right: -1px;margin-bottom: -1px;border: 1px solid #e2e2e2;font-size: 14px;text-align: center;color: #666;transition: all .3s;-webkit-transition: all .3s;}
    
        .site-doc-icon li{width: 222px;}
        .site-doc-icon li .layui-anim{width: 150px; height: 150px; line-height: 150px; margin: 0 auto 10px; text-align: center; background-color: #009688; cursor: pointer; color: #fff; border-radius: 50%;}
    </style>
</head>
<body>
<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>API列表</legend>
</fieldset>
<ul class="site-doc-icon site-doc-anim" id='productListid'>
<!--     <li>
        <div class="layui-anim" data-anim="layui-anim-scale">平滑放大</div>
        <div class="code" onclick="productDeail()">详情</div>
    </li>
    <li>
        <div class="layui-anim" data-anim="layui-anim-scale">平滑放大</div>
        <div class="code">详情</div>
    </li> -->
</ul>
<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../layui/layui.js" charset="utf-8"></script>
 
<!--演示动画-->
<script>
$(function() {
	//初始化数据方法
	getInitData();
});
//根据id查询初始化数据
function getInitData(){
  	$.ajax({
		  url: $("#ctx").attr("value") + '/apiManage/getInitProductData',
		  type: 'post',
		  dataType: 'json',
		  success: function (data, status) {
			  if(data.code=='200'){
				  //top.layer.msg("成功！");
				  var apiNameList = data.apiNameList;
				  var str = "";
				  for(var i=0;i<apiNameList.length;i++){
					  str +="<li>"+
					          "<div class=\"layui-anim\" data-anim=\"layui-anim-scale\" onclick=\"productDeail("+apiNameList[i].id+")\">"+apiNameList[i].apiname+"</div>"+
					          "<div class=\"code\" onclick=\"productDeail("+apiNameList[i].id+")\">详情</div>"+
					        "</li>";
				  }
				  $("#productListid").empty();
				  $("#productListid").append(str);
			  }else{
				  top.layer.msg("操作失败！");
			  }
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
}
function productDeail(id){
	window.location.href = "/CrawlerSystem/page/syntun/api/generate.jsp?id="+id;
}
    layui.use('jquery', function(){
        var $ = layui.$;
 
        //演示动画
        $('.site-doc-icon .layui-anim').on('click', function(){
            var othis = $(this), anim = othis.data('anim');
 
            //停止循环
            if(othis.hasClass('layui-anim-loop')){
                return othis.removeClass(anim);
            }
 
            othis.removeClass(anim);
 
            setTimeout(function(){
                othis.addClass(anim);
            });
            //恢复渐隐
            if(anim === 'layui-anim-fadeout'){
                setTimeout(function(){
                    othis.removeClass(anim);
                }, 1300);
            }
        });
    });
</script>
</body>
</html>