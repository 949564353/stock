<html>
<head>

<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script>
	
$(document).ready(function() {
	$.ajax({
		url : '/cqssc/getCurrentDay.sc',// 跳转到 action
		data : {},
		type : 'post',
		cache : false,
		async: false,
		dataType : 'json',
		success : function(result) {
			alert(result.length);
			for(var i=0;i<result.length;i++){
				alert(result[i].num+"---"+result[i].bsg);
			}
		}
	});
	
})
</script>
</head>
<body>
<h2>Hello World!</h2>
</body>
</html>
