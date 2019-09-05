<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传下载</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="../../../layui/css/layui.css" media="all" />
<link rel="stylesheet" href="../../../css/public.css" media="all" />
<link rel="stylesheet" href="../../../js/jquery/jquery-ui.css" media="all" />

</head>
<body>
    <form name='form1' action="${pageContext.request.contextPath}/replaceFiled/upload.ht"
		method="post" enctype="multipart/form-data">
	   <label>文件上传</label>
        <input type="file" name="file">
        <input class="layui-btn layui-btn-normal" type="submit" onclick="return CheckText()" value="提交/执行">
	</form>
</body>

<script type="text/javascript">
	function CheckText(){
	    if(document.form1.file.value==""){
	        window.alert("文件不能为空，请选择！");
	        return false;
	    }else if(document.form1.file.value.endsWith(".xlsx") || 
	    		ocument.form1.file.value.endsWith(".xls")){
	        window.alert("文件符合要求，确定提交并执行！");
	        return true;
	    }else{
	    	window.alert("文件格式不符合，请重新选择！");
	        return false;
	    }
		/* if(!document.form1.file.value.endsWith(".xlsx") && 
	    		!ocument.form1.file.value.endsWith(".xls")){
	    	window.alert("文件格式不符合，请重新选择！");
	        return false;
	    } */
	}

</script>
</html>