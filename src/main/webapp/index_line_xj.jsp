<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="js/bootstrap-table/bootstrap-table.css" />
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="js/bootstrap-table/bootstrap-table.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>

	<script>

        // 图表配置
        var options = {
            chart: {
                type: 'line'                          //指定图表的类型，默认是折线图（line）
            },
            title: {
                text: ''                 // 标题
            },
            xAxis: {
            },
            yAxis: {
                title: {
                    text: '个数'                // y 轴标题
                }
            },
			plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            series: []
        };

        $(document).ready(function() {
            var selectDay = $("#selectDay").val();
            requestData(selectDay);
        })

        function requestData(selectDay){
            $.ajax({
                url : 'xjssc/getCountNum.sc',// 跳转到 action
                type : 'post',
                cache : false,
                async: false,
                data : {
                    selectMonth:selectDay
                },
                dataType : 'json',
                success : function(data) {
                    var categories = [];
                    var series = [];
                    if(data.length!=0){
                        categories = data[data.length-1].data;
                        for(var i=0;i<data.length-1;i++){
                            series.push(data[i]);
                        }
                    }
                    options.series = series;
                    options.xAxis.categories = categories;
                    // 图表初始化函数
                    var chart = Highcharts.chart('container', options);

                }
            });
        }


        function showData(dp){
            $("#selectDay").val(dp.cal.getDateStr());
            requestData(dp.cal.getDateStr());
        }


	</script>

	<style>
		body{
			margin-left:200px;
			margin-right:200px;
		}
	</style>
</head>
<body>

<div align="center" id="day" style="font-size:24px;margin-top:20px">
	新疆时时彩组<span id="selectDay"></span><img onClick="WdatePicker({el:'selectDay',dateFmt:'yyyyMM',onpicked:function(dp){showData(dp);}})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer" />20和组10统计信息
</div>
<!-- 图表容器 DOM -->
<div id="container" ></div>

</body>
</html>
