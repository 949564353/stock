<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="js/bootstrap-table.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-table.js"></script> 
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

<script>
	
$(document).ready(function() {
	requestData();
})

function requestData(){
	var selectDay = $("#selectDay").val();
	$.ajax({
		url : '/cqssc/getCurrentDay.sc',// 跳转到 action
		data : {},
		type : 'post',
		cache : false,
		async: false,
		data : {
			selectDay:selectDay
		},
		dataType : 'json',
		success : function(obj) {
			var day;
			if(obj!=null && obj.length>0){
				day = obj[0].day;
	    		for(var i=0;i<obj.length;i++){
	    			var typeStyle = obj[i].bsg;
	    			if(obj[i].bsg=="组三"){
	    				typeStyle = "<font color='red'>"+obj[i].bsg+"</font>";
	    			}else if(obj[i].bsg=="豹子"){
	    				typeStyle = "<font color='#0000FF'>"+obj[i].bsg+"</font>";
	    			}
	    			$("#"+obj[i].no).html("&nbsp;&nbsp;"+obj[i].no+"&nbsp;&nbsp;&nbsp;&nbsp;     "+obj[i].num+"&nbsp;&nbsp;&nbsp;&nbsp;     "+ typeStyle);
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


</script>
<style type="text/css">
td {
	width: 180px;
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
		重庆时时彩
		<font color='red'>
			<span id="selectDay"></span> 
		</font>
		<img onClick="WdatePicker({el:'selectDay',dateFmt:'yyyyMMdd',onpicked:function(dp){showData(dp);}})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer" />
			
		开奖号码
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
