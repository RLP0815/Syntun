layui.use(['element','form','layer','table','laytpl'],function(){
	
	var element = layui.element;

	var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

	$(".searchVal").val("");
	
	// 点击添加新tab；
    $(".panel a").click(function(){
        parent.addTab($(this));
    })
    form.on('select(searchVal)', function(data){
    	var searchVal = data.value;
    	if (searchVal == "IP132_xitong"){
        	$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP132_xitong").show();
    	}else if(searchVal == "IP144_crawler"){
        	$(".IP132_xitong").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
    		$(".IP144_crawler").show();
    	}else if(searchVal == "IP144_crawlerTamll"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP144_crawlerTmall").show();
    	}else if(searchVal == "IP144_crawlerJD"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP144_crawlerJD").show();
    	}else if(searchVal == "IP144_crawlerCommentJD"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerCommentJD").show();
    	}else{
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	top.layer.msg("请先选择数据库！");
    	}
    });
    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	//$(".searchVal").val();
    	//alert($(".searchVal").val());
    	if ($(".searchVal").val() == "IP132_xitong"){
        	$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP132_xitong").show();
    	}else if($(".searchVal").val() == "IP144_crawler"){
        	$(".IP132_xitong").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
    		$(".IP144_crawler").show();
    	}else if($(".searchVal").val() == "IP144_crawlerTamll"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP144_crawlerTmall").show();
    	}else if($(".searchVal").val() == "IP144_crawlerJD"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	$(".IP144_crawlerJD").show();
    	}else if($(".searchVal").val() == "IP144_crawlerCommentJD"){
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerCommentJD").show();
    	}else{
    		$(".IP132_xitong").hide();
    		$(".IP144_crawler").hide();
        	$(".IP144_crawlerTmall").hide();
        	$(".IP144_crawlerJD").hide();
        	$(".IP144_crawlerCommentJD").hide();
        	top.layer.msg("请先选择数据库！");
    	}
    });
})
