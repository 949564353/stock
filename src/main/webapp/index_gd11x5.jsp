<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="utf-8">
	<title>广东11选5-当天</title>
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
            $(":radio").click(function(){
                $("#3").attr("checked",false);
                $("#4").attr("checked",false);
                $("#5").attr("checked",false);
                $("#6").attr("checked",false);
                $("#7").attr("checked",false);
                $("#8").attr("checked",false);
                $("#9").attr("checked",false);
                $(this).attr("checked",'checked'); ;
                requestData();
            });
        })

        function requestData(){
            var selectDay = $("#selectDay").text();
            getResultData(selectDay);
            getMiddleCountData(selectDay);
        }

        function isEmpty(obj){
            if(obj == undefined || typeof obj == "undefined" || obj == null || obj == ""){
                return true;
            }else{
                return false;
            }
        }

        function getMiddleCountData(selectDay){
            $.ajax({
                url : 'gd11x5/getMiddleCount.sc',// 跳转到 action
                type : 'post',
                cache : false,
                async: false,
                data : {
                    selectDay:selectDay
                },
                dataType : 'text',
                success : function(obj) {
                    $("#middle").text(obj);
                }
            });
        }

        function getResultData(selectDay){
            $.ajax({
                url : 'gd11x5/getCurrentDay.sc',// 跳转到 action
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
                            var even = obj[i].even;
                            var odd = obj[i].odd;
                            var typeStyle = "";
                            if(even =="1"){
                                typeStyle = "<font color='red'><b>"+odd+":"+even+"</b></font>";
                            }else if(even=="4" ){
                                typeStyle = "<font color='#0000FF'><b>"+odd+":"+even+"</b></font>";
							}else if(even=="0" || even=="5"){
                                typeStyle = "<font color='#00FF00'><b>"+odd+":"+even+"</b></font>";
                            }else if(!isEmpty(obj[i].num)){
                                typeStyle = odd+":"+even;
                            }

                            // var count = document.getElementById("count");
                            // var value = count.options[count.selectedIndex].value;

                            var value = $("input[name='count'][checked]").val();

                            var middleStyle = obj[i].middle;
                            if(obj[i].middle== value){
                                middleStyle = "<font color='red'><b>"+obj[i].middle+"</b></font>";
							}

							//alert(obj[i].num+"---dd"+typeStyle);
                            // var typeStyle = obj[i].bsg;
                            // if(obj[i].bsg=="1"){
                            //     typeStyle = "<font color='red'>组三</font>";
                            // }else if(obj[i].bsg=="2"){
                            //     typeStyle = "<font color='#0000FF'>豹子</font>";
                            // }else if(obj[i].bsg=='0'){
                            //     typeStyle = "组六";
                            // }
                            //$("#"+obj[i].no).html("&nbsp;&nbsp;"+obj[i].no+"&nbsp;&nbsp;&nbsp;&nbsp;     "+obj[i].num+"&nbsp;&nbsp;&nbsp;&nbsp;     "+typeStyle+"&nbsp;&nbsp;&nbsp;&nbsp;     "+middleStyle);
                            $("#"+obj[i].no).html("&nbsp;&nbsp;"+obj[i].no+"&nbsp;&nbsp;     "+obj[i].num+"&nbsp;&nbsp;     "+typeStyle+"&nbsp;&nbsp;     "+middleStyle);
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
            getMiddleCountData(preDateStr);
        }

        function afterDay(){
            var selectDay = $("#selectDay").text();
            //alert("1:"+selectDay);
            var nowDay = new Date(selectDay.substring(0,4),selectDay.substring(4,6)-1,selectDay.substring(6,8));
            var nextDate = new Date(nowDay.getTime() + 24*60*60*1000); //后一天
            var nextDateStr = nextDate.format('yyyyMMdd');
            //alert("2:"+nextDateStr);
            getResultData(nextDateStr);
            getMiddleCountData(preDateStr);
        }

        // function changeCount() {
        //     requestData();
        // }


	</script>
	<style type="text/css">
		td {
			width: 200px;
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
	广东11选5
	<a href="javascript:beforeDay()">⇦</a>
	<font color='red'>
		<span id="selectDay"></span>
	</font>
	<a href="javascript:afterDay()">⇨</a>
	<img onClick="WdatePicker({el:'selectDay',dateFmt:'yyyyMMdd',onpicked:function(dp){showData(dp);}})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle" style="cursor:pointer" />

	开奖号码
	<br>
	<br>
	<input type="radio" name="count" value="3" id="3">3
	<input type="radio" name="count" value="4" id="4">4
	<input type="radio" name="count" value="5" id="5">5
	<input type="radio" name="count" checked value="6" id="6">6
	<input type="radio" name="count" value="7" id="7">7
	<input type="radio" name="count" value="8" id="8">8
	<input type="radio" name="count" value="9" id="9">9

	<%--<select id="count" onchange="changeCount();" style="width: 50px;">--%>
		<%--<option value="3">3</option>--%>
		<%--<option value="4">4</option>--%>
		<%--<option value="5">5</option>--%>
		<%--<option value="6">6</option>--%>
		<%--<option value="7">7</option>--%>
		<%--<option value="8">8</option>--%>
		<%--<option value="9">9</option>--%>
	<%--</select>--%>
</div>
<table align="center" border="1">
	<tr>
		<td id="01"></td>
		<td id="21"></td>
		<td id="41"></td>
		<td id="61"></td>
		<td id="81"></td>
	</tr>
	<tr>
		<td id="02"></td>
		<td id="22"></td>
		<td id="42"></td>
		<td id="62"></td>
		<td id="82"></td>
	</tr>
	<tr>
		<td id="03"></td>
		<td id="23"></td>
		<td id="43"></td>
		<td id="63"></td>
		<td id="83"></td>
	</tr>
	<tr>
		<td id="04"></td>
		<td id="24"></td>
		<td id="44"></td>
		<td id="64"></td>
		<td id="84"></td>
	</tr>
	<tr>
		<td id="05"></td>
		<td id="25"></td>
		<td id="45"></td>
		<td id="65"></td>
		<td></td>
	</tr>
	<tr>
		<td id="06"></td>
		<td id="26"></td>
		<td id="46"></td>
		<td id="66"></td>
		<td></td>
	</tr>
	<tr>
		<td id="07"></td>
		<td id="27"></td>
		<td id="47"></td>
		<td id="67"></td>
		<td></td>
	</tr>
	<tr>
		<td id="08"></td>
		<td id="28"></td>
		<td id="48"></td>
		<td id="68"></td>
		<td></td>
	</tr>
	<tr>
		<td id="09"></td>
		<td id="29"></td>
		<td id="49"></td>
		<td id="69"></td>
		<td></td>
	</tr>
	<tr>
		<td id="10"></td>
		<td id="30"></td>
		<td id="50"></td>
		<td id="70"></td>
		<td></td>
	</tr>
	<tr>
		<td id="11"></td>
		<td id="31"></td>
		<td id="51"></td>
		<td id="71"></td>
		<td></td>
	</tr>
	<tr>
		<td id="12"></td>
		<td id="32"></td>
		<td id="52"></td>
		<td id="72"></td>
		<td></td>
	</tr>
	<tr>
		<td id="13"></td>
		<td id="33"></td>
		<td id="53"></td>
		<td id="73"></td>
		<td></td>
	</tr>
	<tr>
		<td id="14"></td>
		<td id="34"></td>
		<td id="54"></td>
		<td id="74"></td>
		<td></td>
	</tr>
	<tr>
		<td id="15"></td>
		<td id="35"></td>
		<td id="55"></td>
		<td id="75"></td>
		<td></td>
	</tr>
	<tr>
		<td id="16"></td>
		<td id="36"></td>
		<td id="56"></td>
		<td id="76"></td>
		<td></td>
	</tr>
	<tr>
		<td id="17"></td>
		<td id="37"></td>
		<td id="57"></td>
		<td id="77"></td>
		<td></td>
	</tr>
	<tr>
		<td id="18"></td>
		<td id="38"></td>
		<td id="58"></td>
		<td id="78"></td>
		<td></td>
	</tr>
	<tr>
		<td id="19"></td>
		<td id="39"></td>
		<td id="59"></td>
		<td id="79"></td>
		<td></td>
	</tr>
	<tr>
		<td id="20"></td>
		<td id="40"></td>
		<td id="60"></td>
		<td id="80"></td>
		<td></td>
	</tr>
</table>
<div style="margin-left:200px;">
	<div id="middle">&nbsp;&nbsp;</div>
    <div>
        5:0/65.17；
        4:1/5.19；
        3:2/1.9；
        2:3/2.6；
        1:4/13；
        0:5/392；
    </div>
    <div>
        3:16.14；
        4:7.17；
        5:5.02；
        6:4.52；
        7:5；*
        8:7.16；
        9:16.1
    </div>
</div>
</body>
</html>
