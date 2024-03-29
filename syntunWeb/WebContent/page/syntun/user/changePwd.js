layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //添加验证规则
    form.verify({
       /* oldPwd : function(value, item){
            if(value != "123456"){
                return "密码错误，请重新输入！";
            }
        },*/
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //立即修改按钮
    form.on("submit(changePwd)",function(data){
    	//alert(111);
    	//弹出loading
       var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
       $.post($("#ctx").attr("value") + '/tadmin/changePwd.ht',{
    	   nickName : nickName,
    	   oldPwd : $(".oldPwd").val(),  //旧密码
    	   newPwd : $(".newPwd").val(),  //新密码
        },function(res){
        	getAllList();
        	
        	
        })
        return false;
    });
    function getAllList(){
    	var data = {
    			nickName : nickName,
    			passWord : $(".newPwd").val() 
    		}
    	$.ajax({
      		  url: $("#ctx").attr("value") + '/tadmin/getAllList.ht',
      		  type: 'post',
      		  data: data,
      		  dataType: 'json',
      		  timeout: 2000,
      		  success: function (data, status) {
      			  if(data.length == 1){
      				top.layer.msg('修改成功！');
      				$(".oldPwd").val("");
      	    	    $(".newPwd").val("");
      	    	    $(".pwd").val("");
      			  }else{
      				top.layer.msg('旧密码错误，修改失败！');
      			  }
      		    
      		  },
      		  fail: function (err, status) {
      			  alert(status);
      		  }
      	})
      	
     }
    //控制表格编辑时文本的位置【跟随渲染时的位置】
    $("body").on("click",".layui-table-body.layui-table-main tbody tr td",function(){
        $(this).find(".layui-table-edit").addClass("layui-"+$(this).attr("align"));
    });

})