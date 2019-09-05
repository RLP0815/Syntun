<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户相关</title>
	<%@include file="/page/include/getUserInfo.jsp"%>
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
<script type="text/javascript">
	var nickName = '<%=nickName%>';
	var userId = '<%=userId%>';
	var userName = '<%=userName%>';
	var email = '<%=email%>';
	var phoneno = '<%=phoneno%>';
	var authorities = '<%=authorities%>';
</script>
<body>
	<input  id="ctx" style="display:none" value="${pageContext.request.contextPath}">
	<input type="text" style="display:none" class="groupId">
	<input type="text" style="display:none" class="userIdU">
	<input type="text" style="display:none" class="jgId">
	<div class="layui-tab layui-tab-brief" lay-filter="filter">
		<ul class="layui-tab-title">
			<li class="layui-this" lay-id="00">用户组</li>
			<li lay-id="11">用户</li>
  		</ul>
  		<div class="layui-tab-content">
		  	<div class="layui-tab-item layui-show">
				<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  用户组名称：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchVal" placeholder="请输入用户组" />
								</div>
								<a class="layui-btn search_btn" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL,ROLE_ADMIN">
								<a class="layui-btn layui-btn-danger delAll_btn" data-type="reload">批量删除</a>
								</security:authorize>
					 		</div>
				 		</div>
					</blockquote>
					<table id="skyGroups" lay-filter="skyGroups"></table>
				</div>
				<script type="text/html" id="skyGroupsBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>用户组维护说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullgroups">
								
							</ul>
						</div>
					</div>
				</div>
				
				<div id="skyGroupsTab" style="display:none;">
					<form class="layui-form" style="width:90%;">
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">用户组</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input groupName" lay-verify="required" placeholder="请输入用户组名">
							</div>
						</div>
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">执行1</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="inspect1" lay-verify="required">
										<option value="">请选择</option>
										<option value="非满减">非满减</option>
										<option value="满减">满减</option>
										<option value="满折">满折</option>
										<option value="立减">立减</option>
										<option value="领券">领券</option>
									</select>
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">执行2</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="inspect2" lay-verify="required">
										<option value="">请选择</option>
										<option value="非满减">非满减</option>
										<option value="满减">满减</option>
										<option value="满折">满折</option>
										<option value="立减">立减</option>
										<option value="领券">领券</option>
									</select>
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">执行3</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="inspect3" lay-verify="required">
										<option value="">请选择</option>
										<option value="非满减">非满减</option>
										<option value="满减">满减</option>
										<option value="满折">满折</option>
										<option value="立减">立减</option>
										<option value="领券">领券</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">执行4</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="inspect4" lay-verify="required">
										<option value="">请选择</option>
										<option value="非满减">非满减</option>
										<option value="满减">满减</option>
										<option value="满折">满折</option>
										<option value="立减">立减</option>
										<option value="领券">领券</option>
									</select>
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">执行5</label>
								<div class="layui-input-block  layui-form" lay-filter="selFilter">
									<select class="inspect5" lay-verify="required">
										<option value="">请选择</option>
										<option value="非满减">非满减</option>
										<option value="满减">满减</option>
										<option value="满折">满折</option>
										<option value="立减">立减</option>
										<option value="领券">领券</option>
									</select>
								</div>
							</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addPatRecord" lay-submit="" lay-filter="addPatRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editPatRecord" lay-submit="" lay-filter="editPatRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
			</div>
		    <div class="layui-tab-item">
		    	<div class="layui-col-md9 layui-col-xs12">
					<blockquote class="layui-elem-quote quoteBox">
						<div class="demoTable">
						  用户名称：
							<div class="layui-inline">
								<div class="layui-input-inline">
									<input type="text" class="layui-input searchValRep" placeholder="请输入用户名称" />
								</div>
								<a class="layui-btn search_btn_rep" data-type="reload">搜索</a>
								<a class="layui-btn layui-btn-normal add_btn_rep" data-type="reload">增加</a>
								<security:authorize ifAnyGranted="ROLE_DEL,ROLE_ADMIN">
								<a class="layui-btn layui-btn-danger delAll_btn_rep" data-type="reload">批量删除</a>
								</security:authorize>
								<!-- 
								<a class="layui-btn exe_btn" data-type="reload">全部执行</a>
								 -->
					 		</div>
				 		</div>
					</blockquote>
					<table id="skyUsers" lay-filter="skyUsers"></table>
				</div>
				<script type="text/html" id="skyUsersBar">
					<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
					<a class="layui-btn layui-btn-xs" lay-event="exe">执行</a>
				</script>
	
				<div class="layui-col-md3 layui-col-xs12">
					<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i>用户维护说明</blockquote>
					<div class="border category">
						<div class="">
							<ul class="uullusers">
								
							</ul>
						</div>
					</div>
				</div>
				
				<div id="skyUsersTab" style="display:none;">
					<form class="layui-form" style="width:90%;">
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">用户ID</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input userId" lay-verify="required" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入用户ID(只能是数字)">
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">用户名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input userName" lay-verify="required" placeholder="请输入用户名称">
							</div>
						</div>
						<div class="layui-row">
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">用户组ID</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input groupIdU" lay-verify="required" placeholder="请选择用户组">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<label class="layui-form-label">用户组名</label>
								<div class="layui-input-block">
									<input type="text" disabled="disabled" class="layui-input groupNameU" lay-verify="required" placeholder="请选择用户组">
								</div>
							</div>
							<div class="magb15 layui-col-md4 layui-col-xs12">
								<div class="layui-input-block">
									<a class="layui-btn layui-btn-normal selectGroup">用户组选择</a>
								</div>
							</div>
						</div>
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm addRepRecord" lay-submit="" lay-filter="addRepRecord">立即添加</button>
								<button class="layui-btn layui-btn-sm editRepRecord" lay-submit="" lay-filter="editRepRecord">立即修改</button>
								<!-- <button class="layui-btn layui-btn-sm" lay-filter="cancelFlied">取消</button> -->
							</div>
						</div>
					</form>
				</div>
				<div id="groups" style="display:none;">
					<table id="groupsList" lay-filter="groupsList"></table>
					<script type="text/html" id="groupsListBar">
						<a class="layui-btn layui-btn-xs" lay-event="select">选择</a>
					</script>
				</div>
				
				<div id="exeTab" style="display:none;">
					<form class="layui-form" style="width:90%;">
						
						<input type="text" class=""  style="width:0px;height:0px;" />
							
						<div class="layui-form-item layui-row layui-col-xs12">
							<label class="layui-form-label">执行时间</label>
							<div class="layui-input-block">
					    		<input class="layui-input exeDate" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" type="text">
					    	</div>
						</div>
						
						<div class="layui-form-item layui-row layui-col-xs12">
							<div class="layui-input-block">
								<button class="layui-btn layui-btn-sm exeRecord" lay-submit="" lay-filter="exeRecord">立即执行</button>
								<button class="layui-btn layui-btn-sm exeAllRecord" lay-submit="" lay-filter="exeAllRecord">立即执行</button>
							</div>
						</div>
					</form>
				</div>
		    </div>
		    
		</div>
	</div> 

<script type="text/javascript" src="../../../js/jquery/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../../../js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../../layui/layui.js"></script>
<script type="text/javascript" src="UserTab.js"></script>
</body>
</html>