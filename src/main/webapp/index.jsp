<html>
<head>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script>
	
$(document).ready(function() {
	alert("111");
	$.ajax({
		url : '/getCurrentDay.sc',// 跳转到 action
		data : {},
		type : 'post',
		cache : false,
		async: false,
		dataType : 'json',
		success : function(result) {
			 var json = eval("(" + result + ")");
			 alert(json.num);
		}
	});
}
</script>
</head>
<body>
<h2>Hello World!</h2>
</body>
</html>
