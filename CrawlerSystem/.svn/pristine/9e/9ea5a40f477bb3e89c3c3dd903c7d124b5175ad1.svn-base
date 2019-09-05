layui.use(['element','form','layer','table','laytpl'], function(){
	
	var element = layui.element;
	
	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

	$(".column1").attr("value","id");
	$(".length1").attr("value","11");
	$(".comment1").attr("value","主键ID");
	
	//数据库dataIP select change
    form.on('select(dataIP)', function(data){
    	$("#databaseName").val("");
    	//var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        var dataIP = data.value;
        if(dataIP == ""){
        	var remarks = '<option value="">请选择</option>';
			$('#databaseName').html('');
			$("#databaseName").append(remarks);
			top.layer.msg("请选择数据库IP！");
			form.render();
        }else{
         	$.ajax({
    	  		url: $("#ctx").attr("value") + '/platformClassify/getDataBaseNameIP',
    			type: 'post',
      			data: {
      				"dataIP":dataIP,
      			},
    			dataType: 'json',
    			success: function (data, status) {
    				if(data.code=='200'){
    					//top.layer.msg("数据库表获取成功！");
    					var remarks = '<option value="">请选择</option>';
    					for (var i = 0; i < data.list.length; i++) {
    			  			remarks = remarks + "<option value='"+data.list[i]+"'>"+data.list[i]+"</option>";
    					}
    					$('#databaseName').html('');
    					$("#databaseName").append(remarks);
    					top.layer.msg("数据库获取成功！");
    					form.render();//表单重新渲染，要不然添加完显示不出来新的
    				}else{
    					top.layer.msg("数据库获取失败！");
    				}
    			},
    			fail: function (err, status) {
    				alert(status);
    			}
    		})
        }
        form.render();
    });
    
	//添加表格
	var tableName = "";
	form.on("submit(addTable)",function(data){
		if($("#dataIP").val() == ""){
			top.layer.msg("请选择数据库IP！");
    		return false;
		}
		if($("#databaseName").val() == ""){
    		top.layer.msg("请选择数据库！");
    		return false;
    	}
		tableName = $(".tableName").val();
		if(tableName == ""){
			top.layer.msg("请输入表名！");
			return false;
		}else if(columns == "[]"){
			top.layer.msg("请输入对应列！");
			return false;
		}else{
			//弹出loading
			var columns = "[";
			if($('.column1').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey1").checked){pk="T";}
				if(document.getElementById("notNull1").checked){nn="T";}
				if(document.getElementById("autoIncrement1").checked){ai="T";}
				columns = columns+'{"column":"'+$('.column1').val()+'","dataType":"'+$('.dataType1').val()+'","length":"'+$('.length1').val()+'","default":"'+$('.default1').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment1').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey2").checked){pk="T";}
				if(document.getElementById("notNull2").checked){nn="T";}
				if(document.getElementById("autoIncrement2").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column2').val()+'","dataType":"'+$('.dataType2').val()+'","length":"'+$('.length2').val()+'","default":"'+$('.default2').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment2').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey3").checked){pk="T";}
				if(document.getElementById("notNull3").checked){nn="T";}
				if(document.getElementById("autoIncrement3").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column3').val()+'","dataType":"'+$('.dataType3').val()+'","length":"'+$('.length3').val()+'","default":"'+$('.default3').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment3').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey4").checked){pk="T";}
				if(document.getElementById("notNull4").checked){nn="T";}
				if(document.getElementById("autoIncrement4").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column4').val()+'","dataType":"'+$('.dataType4').val()+'","length":"'+$('.length4').val()+'","default":"'+$('.default4').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment4').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey5").checked){pk="T";}
				if(document.getElementById("notNull5").checked){nn="T";}
				if(document.getElementById("autoIncrement5").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column5').val()+'","dataType":"'+$('.dataType5').val()+'","length":"'+$('.length5').val()+'","default":"'+$('.default5').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment5').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey6").checked){pk="T";}
				if(document.getElementById("notNull6").checked){nn="T";}
				if(document.getElementById("autoIncrement6").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column6').val()+'","dataType":"'+$('.dataType6').val()+'","length":"'+$('.length6').val()+'","default":"'+$('.default6').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment6').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey7").checked){pk="T";}
				if(document.getElementById("notNull7").checked){nn="T";}
				if(document.getElementById("autoIncrement7").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column7').val()+'","dataType":"'+$('.dataType7').val()+'","length":"'+$('.length7').val()+'","default":"'+$('.default7').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment7').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" ){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey8").checked){pk="T";}
				if(document.getElementById("notNull8").checked){nn="T";}
				if(document.getElementById("autoIncrement8").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column8').val()+'","dataType":"'+$('.dataType8').val()+'","length":"'+$('.length8').val()+'","default":"'+$('.default8').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment8').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey9").checked){pk="T";}
				if(document.getElementById("notNull9").checked){nn="T";}
				if(document.getElementById("autoIncrement9").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column9').val()+'","dataType":"'+$('.dataType9').val()+'","length":"'+$('.length9').val()+'","default":"'+$('.default9').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment9').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey10").checked){pk="T";}
				if(document.getElementById("notNull10").checked){nn="T";}
				if(document.getElementById("autoIncrement10").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column10').val()+'","dataType":"'+$('.dataType10').val()+'","length":"'+$('.length10').val()+'","default":"'+$('.default10').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment10').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""
					&&$('.column11').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey11").checked){pk="T";}
				if(document.getElementById("notNull11").checked){nn="T";}
				if(document.getElementById("autoIncrement11").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column11').val()+'","dataType":"'+$('.dataType11').val()+'","length":"'+$('.length11').val()+'","default":"'+$('.default11').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment11').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""
					&&$('.column11').val()!="" && $('.column12').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey12").checked){pk="T";}
				if(document.getElementById("notNull12").checked){nn="T";}
				if(document.getElementById("autoIncrement12").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column12').val()+'","dataType":"'+$('.dataType12').val()+'","length":"'+$('.length12').val()+'","default":"'+$('.default12').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment12').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""
					&&$('.column11').val()!="" && $('.column12').val()!="" && $('.column13').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey13").checked){pk="T";}
				if(document.getElementById("notNull13").checked){nn="T";}
				if(document.getElementById("autoIncrement13").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column13').val()+'","dataType":"'+$('.dataType13').val()+'","length":"'+$('.length13').val()+'","default":"'+$('.default13').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment13').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""
					&&$('.column11').val()!="" && $('.column12').val()!="" && $('.column13').val()!="" && $('.column14').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey14").checked){pk="T";}
				if(document.getElementById("notNull14").checked){nn="T";}
				if(document.getElementById("autoIncrement14").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column14').val()+'","dataType":"'+$('.dataType14').val()+'","length":"'+$('.length14').val()+'","default":"'+$('.default14').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment14').val()+'"}'
			}
			if($('.column1').val()!="" && $('.column2').val()!="" && $('.column3').val()!="" && $('.column4').val()!="" && $('.column5').val()!=""
				&&$('.column6').val()!="" && $('.column7').val()!="" && $('.column8').val()!="" && $('.column9').val()!="" && $('.column10').val()!=""
					&&$('.column11').val()!="" && $('.column12').val()!="" && $('.column13').val()!="" && $('.column14').val()!="" && $('.column15').val()!=""){
				var pk="F",nn="F",ai="F";
				if(document.getElementById("primaryKey15").checked){pk="T";}
				if(document.getElementById("notNull15").checked){nn="T";}
				if(document.getElementById("autoIncrement15").checked){ai="T";}
				columns = columns+',{"column":"'+$('.column15').val()+'","dataType":"'+$('.dataType15').val()+'","length":"'+$('.length15').val()+'","default":"'+$('.default15').val()+'","primaryKey":"'+pk+'","notNull":"'+nn+'","autoIncrement":"'+ai+'","comment":"'+$('.comment15').val()+'"}'
			}
			columns = columns + "]";
			//alert(columns);
			layer.confirm('确定新建表格？', {icon: 3, title: '提示信息'}, function (index) {
				$.post($("#ctx").attr("value") + '/dataTableFiled/addTable.ht',{
					dataIP : $("#dataIP").val(),
					databaseName : $("#databaseName").val(),
					tableName : tableName,
					columns : columns,
				},function(res){
					layer.close(index);
					if(JSON.parse(res).code == 0){ 
						top.layer.msg(JSON.parse(res).msg); 
			        }else{
			        	layer.alert(JSON.parse(res).msg, {
			        		icon: 5,
			        		title: "警告"
			        	});
			        	//alert(JSON.parse(res).msg); 
			        } 
				})
			});
		}
		return false;
	});
  
});
