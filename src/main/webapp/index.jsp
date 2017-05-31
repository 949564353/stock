<html>
<head>

<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="js/bootstrap-table.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-table.js"></script>

<script>
	
$(document).ready(function() {
	$.ajax({
		url : '/cqssc/getCurrentDay.sc',// 跳转到 action
		data : {},
		type : 'post',
		cache : false,
		async: false,
		dataType : 'json',
		success : function(obj) {
			
			var initData =[];
			if(obj!=null && obj.length>0){
	    		for(var i=0;i<obj.length;i++){
	    			var row = {};
	    			row["no"] = obj[i].no;
	    			row["num"] = obj[i].num;
	    			initData.push(row);
	    		}
	    	}
			
			var $table = $('#cqssc-data').bootstrapTable({
				//url: 'data1.json',  通过url可以从后台获取表格数据
				toolbar: "#configToolbar",	//启用顶部工具栏
				search: true,			//启用搜索框
				showRefresh: true, 		//启用刷新功能
				showExport: true,		//启用导出功能
				showToggle: true,		//启用2种表格视图切换
				showColumns: true,		//启用自定义列功能
				pagination: true,		//启用分页
				showHeader: true,
				multipleSearch: true,
				uniqueId: "id",
				pageList: "[8, 25, 50, 100, all]",
				columns: [
			        {
			            field : 'no',
			            title : '期号',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'num',
			            title : '开奖号码',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }],
				data: initData
				}); 
			
			
	    	
			
		}
	});
	initTable();
	
})



function initTable(){
    var url = "/cqssc/getCurrentDay.sc&random="+Math.random();
    $('#demo-table').bootstrapTable({
        method:'POST',
        dataType:'json',
        contentType: "application/x-www-form-urlencoded",
        cache: false,
        striped: true,                              //是否显示行间隔色
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        url:url,
        height: $(window).height() - 110,
        width:$(window).width(),
        showColumns:true,
        pagination:true,
        queryParams : "",
        minimumCountColumns:2,
        pageNumber:1,                       //初始化加载第一页，默认第一页
               pageSize: 20,                       //每页的记录行数（*）
              pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
              uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showExport: true,                    
        exportDataType: 'all',
        responseHandler: responseHandler,
        columns: [
        {
            field : 'no',
            title : '期号',
            align : 'center',
            valign : 'middle',
            sortable : true
        }, {
            field : 'num',
            title : '开奖号码',
            align : 'center',
            valign : 'middle',
            sortable : true
        }]
    });
}
</script>
</head>
<body>
<h2>Hello World!</h2>
	<table id="cqssc-table">
    </table>
</body>
</html>
