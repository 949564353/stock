<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="utf-8">
	<title>重庆-组5</title>
	<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/dateformat-min.js"></script>
	<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
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

		function changeCount() {
            var count = document.getElementById("count");
            var value = count.options[count.selectedIndex].value;
            getResultData(value);
        }

        function requestData(){
            var count = 5;
            getResultData(count);
        }

        function getResultData(count){
            $.ajax({
                url : 'cqssc/getZ5.sc',// 跳转到 action
                type : 'post',
                cache : false,
                async: false,
                data : {
                    num:count
                },
                dataType : 'json',
                success : function(obj) {
                    var day;
                    if(obj!=null && obj.length>0){
                        day = obj[0].day;
                        for(var i=0;i<obj.length;i++){
                            $("#"+obj[i].no).html("&nbsp;&nbsp;"+obj[i].no+"&nbsp;&nbsp;&nbsp;&nbsp;     <font color='red'>"+obj[i].num+"</font>");
                        }
                    }
                    $("#selectDay").text(day);
                }
            });
		}


        function showData(dp){
            $("#selectDay").val(dp.cal.getDateStr());
            requestData();
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
	<style type="text/css">
		td {
			width: 80px;
		}

		div{
			font-size:24px;
			margin-top:20px;
			margin-bottom:20px;
		}


	</style>
</head>
<body>

<div align="center" id="day" style="font-size:24px">
	重庆时时彩组5个数&nbsp;>
	<select id="count" onchange="changeCount();" style="width: 50px;">
		<option value="4">4</option>
		<option value="5" selected>5</option>
		<option value="6">6</option>
		<option value="7">7</option>
		<option value="8">8</option>
		<option value="9">9</option>
	</select>
</div>
<table align="center" border="1">
	<tr>
		<td id="001"></td>
		<td id="021"></td>
		<td id="041"></td>
		<td id="061"></td>
		<td id="081"></td>
		<td id="101"></td>
	</tr>
	<tr>
		<td id="002"></td>
		<td id="022"></td>
		<td id="042"></td>
		<td id="062"></td>
		<td id="082"></td>
		<td id="102"></td>
	</tr>
	<tr>
		<td id="003"></td>
		<td id="023"></td>
		<td id="043"></td>
		<td id="063"></td>
		<td id="083"></td>
		<td id="103"></td>
	</tr>
	<tr>
		<td id="004"></td>
		<td id="024"></td>
		<td id="044"></td>
		<td id="064"></td>
		<td id="084"></td>
		<td id="104"></td>
	</tr>
	<tr>
		<td id="005"></td>
		<td id="025"></td>
		<td id="045"></td>
		<td id="065"></td>
		<td id="085"></td>
		<td id="105"></td>
	</tr>
	<tr>
		<td id="006"></td>
		<td id="026"></td>
		<td id="046"></td>
		<td id="066"></td>
		<td id="086"></td>
		<td id="106"></td>
	</tr>
	<tr>
		<td id="007"></td>
		<td id="027"></td>
		<td id="047"></td>
		<td id="067"></td>
		<td id="087"></td>
		<td id="107"></td>
	</tr>
	<tr>
		<td id="008"></td>
		<td id="028"></td>
		<td id="048"></td>
		<td id="068"></td>
		<td id="088"></td>
		<td id="108"></td>
	</tr>
	<tr>
		<td id="009"></td>
		<td id="029"></td>
		<td id="049"></td>
		<td id="069"></td>
		<td id="089"></td>
		<td id="109"></td>
	</tr>
	<tr>
		<td id="010"></td>
		<td id="030"></td>
		<td id="050"></td>
		<td id="070"></td>
		<td id="090"></td>
		<td id="110"></td>
	</tr>


	<tr>
		<td id="011"></td>
		<td id="031"></td>
		<td id="051"></td>
		<td id="071"></td>
		<td id="091"></td>
		<td id="111"></td>
	</tr>
	<tr>
		<td id="012"></td>
		<td id="032"></td>
		<td id="052"></td>
		<td id="072"></td>
		<td id="092"></td>
		<td id="112"></td>
	</tr>
	<tr>
		<td id="013"></td>
		<td id="033"></td>
		<td id="053"></td>
		<td id="073"></td>
		<td id="093"></td>
		<td id="113"></td>
	</tr>
	<tr>
		<td id="014"></td>
		<td id="034"></td>
		<td id="054"></td>
		<td id="074"></td>
		<td id="094"></td>
		<td id="114"></td>
	</tr>
	<tr>
		<td id="015"></td>
		<td id="035"></td>
		<td id="055"></td>
		<td id="075"></td>
		<td id="095"></td>
		<td id="115"></td>
	</tr>
	<tr>
		<td id="016"></td>
		<td id="036"></td>
		<td id="056"></td>
		<td id="076"></td>
		<td id="096"></td>
		<td id="116"></td>
	</tr>
	<tr>
		<td id="017"></td>
		<td id="037"></td>
		<td id="057"></td>
		<td id="077"></td>
		<td id="097"></td>
		<td id="117"></td>
	</tr>
	<tr>
		<td id="018"></td>
		<td id="038"></td>
		<td id="058"></td>
		<td id="078"></td>
		<td id="098"></td>
		<td id="118"></td>
	</tr>
	<tr>
		<td id="019"></td>
		<td id="039"></td>
		<td id="059"></td>
		<td id="079"></td>
		<td id="099"></td>
		<td id="119"></td>
	</tr>
	<tr>
		<td id="020"></td>
		<td id="040"></td>
		<td id="060"></td>
		<td id="080"></td>
		<td id="100"></td>
		<td id="120"></td>
	</tr>
</table>
</body>
</html>
