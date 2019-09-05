
layui.use('form', function(){
  var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
  
  //……
  
  //但是，如果你的HTML是动态生成的，自动渲染就会失效
  //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
  form.render();
});
var setting = {
        view: {
            addHoverDom: true,
            removeHoverDom: true,
            selectedMulti: true
        },
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: true
        }
    };
var treeObj;
function test(){
  	$.ajax({
  		  url: $("#ctx").attr("value") + '/user/getPermission.ht',
  		  type: 'post',
  		  data: {},
  		  dataType: 'json',
  		  //timeout: 2000,
  		  success: function (data, status) {
//		    console.log(JSON.stringify(data.userList));
		    var treeObj = $.fn.zTree.init($("#treeDemo"), setting, data.userList);
		  //设置节点展开
			var nodes = treeObj.getNodes();
			for (var i = 0; i < nodes.length; i++) { 
				treeObj.expandNode(nodes[i], true, true, true);
			}
			//设置选中的节点
			var menuIds = test1();
			if(menuIds!=null && menuIds !=''){
				for(var i = 0; i < menuIds.length; i++) {
					var node = treeObj.getNodeByParam("id", menuIds[i].id);
					if(node != null) {
						treeObj.checkNode(node, true)
					}
				}
			}
  		  },
  		  fail: function (err, status) {
  			  alert(status);
  		  }
  	})
}
function test1(){
	var flag = "";
  	$.ajax({
  		  url: $("#ctx").attr("value") + '/user/getPermissionUseridInfo.ht',
  		  type: 'post',
  		  data: {"userId" : "1"},
  		  async:false,
  		  dataType: 'json',
  		  //timeout: 2000,
  		  success: function (data, status) {
  			  flag = data.userList;
//  			  alert(JSON.stringify(flag))
//  			  console.log(JSON.stringify(flag));
  		  },
  		  fail: function (err, status) {
  			  alert(status);
  		  }
  	})
  	return flag
}
function test2(){
  	$.ajax({
  		  url: $("#ctx").attr("value") + '/user/getPermissionUseridUrl.ht',
  		  type: 'post',
  		  data: {"userId" : "1"},
  		  dataType: 'json',
  		  //timeout: 2000,
  		  success: function (data, status) {
/*  			  alert(JSON.stringify(data))
  			  console.log(JSON.stringify(data));*/
  		  },
  		  fail: function (err, status) {
  			  alert(status);
  		  }
  	})
}
//最后获取树的选中id
function test3(){
	var v = new Array();
	var nodes = treeObj.getCheckedNodes();
	for(var i = 0; i < nodes.length; i++) {
		//console.log(nodes[i].id)
		v.add(nodes[i].id)
	}
	if(v.isEmpty()){
		alert("请选择菜单！！");
		return false;
	}
	//保存数据到数据库，先删除原来的，在保存现在的
  	$.ajax({
		  url: $("#ctx").attr("value") + '/user/roidPermissionAdd.ht',
		  type: 'post',
		  data: {
			  "roid":"1",
			  "permissionId" : v
		  },
		  dataType: 'json',
		  //timeout: 2000,
		  success: function (data, status) {
			  if(data.code=='200'){
				  alert("操作成功!");
			  }else{
				  alert("操作失败!");
			  }
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
}
//数组操作
Array.prototype.add = function(item) {
    this.push(item);
}
Array.prototype.isEmpty = function() {
    if (this.length == 0)
        return true;
    else
        return false;
}



//树增删改查
var zTree;
var isAdd;//新增还是修改标识
var setting = {
        view:{
            addHoverDom:addHoverDom,
            removeHoverDom:removeHoverDom,
            selectedMulti:false
        },
        edit: {
            enable: true,
            editNameSelectAll:true,
            removeTitle:'删除',
            renameTitle:'重命名'
        },
        data: {
             keep:{
                parent:true,
                leaf:true
            }, 
            simpleData: {
                enable: true
            }
        },
        callback:{
            beforeRemove:beforeRemove,//点击删除时触发，用来提示用户是否确定删除（可以根据返回值 true|false 确定是否可以删除）
            beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
            beforeRename:beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求(也是根据返回值 true|false 确定是否可以编辑完成)
            onRemove:onRemove,//(beforeRemove返回true之后可以进行onRemove)删除节点后触发，用户后台操作
            onRename:onRename,//编辑后触发，用于操作后台
            beforeDrag:beforeDrag,//用户禁止拖动节点
            onClick:clickNode//点击节点触发的事件
        }
    };
/*var zNodes =[
             { id:1, pId:0, name:"父节点 1", open:true},
             { id:11, pId:1, name:"去百度",url:'http://www.baidu.com',target:'_blank'},
             { id:12, pId:1, name:"叶子节点 1-2"},
             { id:13, pId:1, name:"叶子节点 1-3"},
             { id:2, pId:0, name:"父节点 2", open:true},
             { id:21, pId:2, name:"叶子节点 2-1"},
             { id:22, pId:2, name:"叶子节点 2-2"},
             { id:23, pId:2, name:"叶子节点 2-3"},
             { id:3, pId:0, name:"父节点 3", open:true},
             { id:31, pId:3, name:"叶子节点 3-1"},
             { id:32, pId:3, name:"叶子节点 3-2"},
             { id:33, pId:3, name:"叶子节点 3-3"}
         ];*/
$(document).ready(function(){
	initTree();
});
function initTree(){
  	$.ajax({
		  url: $("#ctx").attr("value") + '/user/getPermission.ht',
		  type: 'post',
		  data: {},
		  async:false,
		  dataType: 'json',
		  //timeout: 2000,
		  success: function (data, status) {
//		    console.log(JSON.stringify(data.userList));
		  treeObj = $.fn.zTree.init($("#tree"), setting, data.userList);
		  //设置节点展开
			var nodes = treeObj.getNodes();
			for (var i = 0; i < nodes.length; i++) { 
				treeObj.expandNode(nodes[i], true, true, true);
			}
/*			//设置选中的节点
			var menuIds = test1();
			if(menuIds!=null && menuIds !=''){
				for(var i = 0; i < menuIds.length; i++) {
					var node = treeObj.getNodeByParam("id", menuIds[i].id);
					if(node != null) {
						treeObj.checkNode(node, true)
					}
				}
			}*/
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
}
function beforeRemove(e,treeId,treeNode){
    return confirm("你确定要删除吗？");
}
//删除树节点方法
function onRemove(e,treeId,treeNode){
    var paramsArray = new Array();
    if(treeNode.isParent){
        var childNodes = treeObj.removeChildNodes(treeNode);
        for(var i = 0; i < childNodes.length; i++){
            paramsArray.push(childNodes[i].id);
        }
        //alert("删除父节点的id为："+treeNode.id+"\r\n他的孩子节点有："+paramsArray.join(","));
      	$.ajax({
  		  url: $("#ctx").attr("value") + '/user/permissionDelByIdArrays.ht',
  		  type: 'post',
  		  data: {
  			  "treeid" : treeNode.id,
  			  "childNodes":paramsArray,
  		  },
  		  dataType: 'json',
  		  //timeout: 2000,
  		  success: function (data, status) {
  			  if(data.code=='200'){
  				  alert("操作成功!");
  				  initTree();
  			  }else{
  				  alert("操作失败!");
  			  }
  		  },
  		  fail: function (err, status) {
  			  alert(status);
  		  }
  	})
    }
    //alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id);
  	$.ajax({
		  url: $("#ctx").attr("value") + '/user/permissionDelById.ht',
		  type: 'post',
		  data: {
			  "treeid" : treeNode.id,
		  },
		  dataType: 'json',
		  //timeout: 2000,
		  success: function (data, status) {
			  if(data.code=='200'){
				  alert("操作成功!");
				  initTree();
			  }else{
				  alert("操作失败!");
			  }
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
}
function beforeEditName(treeId,treeNode){
/*    if(treeNode.isParent){
        alert("不准编辑非叶子节点！");
        return false;
    }*/
    return true;
}
function beforeRename(treeId,treeNode,newName,isCancel){
/*    if(newName.length < 3){
        alert("名称不能少于3个字符！");
        return false;
    }*/
    return true;
}
//修改方法
function onRename(e,treeId,treeNode,isCancel){
    //alert("修改节点的id为："+treeNode.id+"\n修改后的名称为："+treeNode.name);
	treeid = treeNode.id;
	isAdd = "2";
	$.ajax({
	  url: $("#ctx").attr("value") + '/user/permissionFindById.ht',
	  type: 'post',
	  data: {
		  "treeid" : treeid,
	  },
	  dataType: 'json',
	  //timeout: 2000,
	  success: function (data, status) {
		  if(data.code=='200'){
				$("#treeName").val(data.permission.name);
				$("#treeType").val(data.permission.type);
				$("#treeUrl").val(data.permission.url);
				$("#treeImage").val(data.permission.icon);
				$("#treeSort").val(data.permission.sortstring);
				$("#treeProject").val(data.permission.project);
				renderForm();
				$("#treeaddFiled").dialog({  
			        //autoOpen : false,   // 是否自动弹出窗口  
			        modal : true,    // 设置为模态对话框  
			        resizable : true,  
			        width : 500,   //弹出框宽度  
			        height : 500,   //弹出框高度  
			        title : "修改",  //弹出框标题  
			        position: { my: "center top", at: "center top", of: window },
			        closeText : '关闭',
			        buttons:{  
			        	/*'添加':function(data){  
			        		 
			            }, */
			            '取消':function(){  
			            	$(".oldFiled").val("");
			        	    $(".unityFiled").val("");
			            	$(this).dialog("close");  
			            }  
			        } 
			   }); 
		  }else{
			  alert("操作失败!");
		  }
	  },
	  fail: function (err, status) {
		  alert(status);
	  }
	})
}
//单击节点方法
function clickNode(e,treeId,treeNode){
    if(treeNode.id == 11){
        location.href=treeNode.url;
    }else{
        //alert("节点名称："+treeNode.name+"\n节点id："+treeNode.id);
    }
}
function beforeDrag(treeId,treeNodes){
    return false;
}
var newCount = 1;
var treeid;
//添加方法
function addHoverDom(treeId,treeNode){
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='添加子节点' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    if (btn) btn.bind("click", function(){
        //在这里向后台发送请求保存一个新建的叶子节点，父id为treeNode.id,让后将下面的100+newCount换成返回的id
        //zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"新建节点" + (newCount++)});
    	//alert(treeNode.id)
    	treeid = treeNode.id;
    	isAdd = "1";
		$("#treeName").val('');
		$("#treeType").val('menu');
		$("#treeUrl").val('');
		$("#treeImage").val('');
    	$("#treeaddFiled").dialog({  
            //autoOpen : false,   // 是否自动弹出窗口  
            modal : true,    // 设置为模态对话框  
            resizable : true,  
            width : 500,   //弹出框宽度  
            height : 500,   //弹出框高度  
            title : "添加",  //弹出框标题  
            position: { my: "center top", at: "center top", of: window },
            closeText : '关闭',
            buttons:{  
            	/*'添加':function(data){  
            		 
	            }, */
	            '取消':function(){  
	            	$(".oldFiled").val("");
            	    $(".unityFiled").val("");
	            	$(this).dialog("close");  
	            }  
            } 
       }); 
        return false;
    });
}
function removeHoverDom(treeId,treeNode){
    $("#addBtn_"+treeNode.tId).unbind().remove();
}

$("#treeAdd").click(function(){
	
	var treeName = $("#treeName").val();
	if(treeName==''){
		alert("请输入姓名！");
		return false;
	}
	var treeType = $("#treeType").val();//菜单类型
	var treeUrl = $("#treeUrl").val();//url
	var treeImage = $("#treeImage").val();//图片路径
	var treeSort = $("#treeSort").val();//排序
	var treeProject = $("#treeProject").val();//所属项目
	var url = "";
	if(isAdd=="1"){
		url = $("#ctx").attr("value") + '/user/permissionAdd.ht'
	}else if(isAdd=="2"){
		url = $("#ctx").attr("value") + '/user/permissionEdit.ht'
	}
  	$.ajax({
		  url: url,
		  type: 'post',
		  data: {
			  "treeName":treeName,
			  "treeType" : treeType,
			  "treeUrl" : treeUrl,
			  "treeImage" : treeImage,
			  "pid" : treeid,
			  "treeSort" : treeSort,
			  "treeProject" : treeProject,
		  },
		  dataType: 'json',
		  //timeout: 2000,
		  success: function (data, status) {
			  if(data.code=='200'){
				  alert("操作成功!");
				  //关闭弹层方法
				  $("#treeaddFiled").dialog("close"); 
				  initTree();
			  }else{
				  alert("操作失败!");
			  }
		  },
		  fail: function (err, status) {
			  alert(status);
		  }
	})
});

//重新渲染表单函数
function renderForm() {
    layui.use(['form'], function () {
        $ = layui.jquery;
        var form = layui.form;
        form.render(); //刷新select选择框渲染
    });
}


