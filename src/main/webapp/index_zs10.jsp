<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="js/bootstrap-table/bootstrap-table.css" />
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/dateformat-min.js"></script>
	<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap-table/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

	<script>

        Date.prototype.format = function(format)
        {
            var o = {
                "M+" : this.getMonth()+1, //month
                "d+" : this.getDate(),    //day
                "h+" : this.getHours(),   //hour
                "m+" : this.getMinutes(), //minute
                "s+" : this.getSeconds(), //second
                "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
                "S" : this.getMilliseconds() //millisecond
            }
            if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
                (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            for(var k in o)if(new RegExp("("+ k +")").test(format))
                format = format.replace(RegExp.$1,
                    RegExp.$1.length==1 ? o[k] :
                        ("00"+ o[k]).substr((""+ o[k]).length));
            return format;
        }

        $(document).ready(function() {
            requestData();
        })


        function requestData(){
            var selectDay = $("#selectDay").val();
            if(selectDay==''){
                selectDay = (new Date()).format('yyyyMMdd');
			}
            getResultData(selectDay);
        }

        function getResultData(selectDay){
            $("#selectDay").text(selectDay);
            $('#bzTable').bootstrapTable('destroy');
            $('#bzTable').bootstrapTable({
                url: "cqssc/getZs10List.sc?selectDay="+selectDay,//数据源
                toolbar: "#modalToolbar",	//启用顶部工具栏
                //search: true,			//启用搜索框
                //showRefresh: true, 		//启用刷新功能
                //showExport: true,		//启用导出功能
                //showToggle: true,		//启用2种表格视图切换
                //showColumns: true,		//启用自定义列功能
                pageNumber: 1,
                pageSize: 120,
                pagination: false,		//启用分页
                pageList: "[8, 25, 50, 100, all]",
                columns: [
                    {
                        title: "期号",
                        field: "day",
                        width: "20px",
                        align: "center"//水平
                    },{
                        title: "开奖号",
                        field: "num",
                        width: "20px",
                        align: "center"//水平
                    },{
                        title: "万位",
                        field: "num_w",
                        width: "20px",
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==0){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center"//水平
                    },{
                        title: "千位",
                        field: "num_q",
                        width: "20px",
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==0){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center"//水平
                    },{
                        title: "百位",
                        field: "num_b",
                        width: "20px",
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==0){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center"//水平
                    },{
                        title: "十位",
                        field: "num_s",
                        width: "20px",
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==0){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center"//水平
                    },{
                        title: "个位",
                        field: "num_g",
                        width: "20px",
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==0){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center"//水平
                    },
                    {
                        title: "前三",
                        field: "isWqb",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
							}else if (value==1){
                                return "组三"
							}else{
                                return "豹子";
							}
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "中三",
                        field: "isQbs",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "后三",
                        field: "isBsg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "万千十",
                        field: "isWqs",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "万千个",
                        field: "isWqg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "万百十",
                        field: "isWbs",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "万百个",
                        field: "isWbg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "万十个",
                        field: "isWsg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "千百个",
                        field: "isQbg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    },
                    {
                        title: "千十个",
                        field: "isQsg",
                        width: "30px",
                        formatter:function formatter(value, row, index, field) {
                            if(value==0){
                                return "组六";
                            }else if (value==1){
                                return "组三"
                            }else{
                                return "豹子";
                            }
                        },
                        cellStyle : function cellStyle(value, row, index) {
                            if(value==1){
                                return {
                                    css : {
                                        "background-color" : "#FFFF00"
                                    }
                                };
                            }else if(value ==2 ){
                                return {
                                    css : {
                                        "background-color" : "#FF0000"
                                    }
                                };
                            }else{
                                return {
                                    css : {
                                        "background-color" : "#00B050"
                                    }
                                };
                            }
                        },
                        align: "center",//水平
                        valign: "middle"//垂直
                    }
                ]
            });
        }

        function showData(dp){
            $("#selectDay").val(dp.cal.getDateStr());
            requestData(dp.cal.getDateStr());
        }

        function beforeDay(){
            var selectDay = $("#selectDay").text();
            var nowDay = new Date(selectDay.substring(0,4),selectDay.substring(4,6)-1,selectDay.substring(6,8));
            var preDate = new Date(nowDay.getTime() - 24*60*60*1000); //前一天
            var preDateStr = preDate.format('yyyyMMdd');
            //alert("1:"+selectDay+";2:"+preDateStr);
            getResultData(preDateStr);
        }

        function afterDay(){
            var selectDay = $("#selectDay").text();
            //alert("1:"+selectDay);
            var nowDay = new Date(selectDay.substring(0,4),selectDay.substring(4,6)-1,selectDay.substring(6,8));
            var nextDate = new Date(nowDay.getTime() + 24*60*60*1000); //后一天
            var nextDateStr = nextDate.format('yyyyMMdd');
            //alert("2:"+nextDateStr);
            getResultData(nextDateStr);
        }

	</script>

	<style>
		body{
			margin-left:100px;
			margin-right:100px;
		}

	</style>
</head>
<body>

<div align="center" id="day" style="font-size:24px;margin-top:20px">
	重庆时时彩10路组三
	<a href="javascript:beforeDay()">⇦</a>
	<font color='red'>
		<span id="selectDay"></span>
	</font>
	<a href="javascript:afterDay()">⇨</a>
	<img onClick="WdatePicker({el:'selectDay',dateFmt:'yyyyMMdd',onpicked:function(dp){showData(dp);}})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer" />

	开奖情况
</div>
<div class="container" style="width:1024px">
	<table id="bzTable"></table>
</div>

</body>
</html>
