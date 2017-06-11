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
	
	$('#bzTable').bootstrapTable({
        url: "/cqssc/getBzList.sc",//数据源
        dataField: "rows",//服务端返回数据键值 就是说记录放的键值是rows，分页时使用总记录数的键值为total
        pagination: true,
        pageNumber: 1,
        pageSize: 15,
        pageList: [10, 20, 30, 50],
        sidePagination: "client",//服务端分页
        contentType: "application/x-www-form-urlencoded",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
        dataType: "json",//期待返回数据类型
        method: "post",//请求方式
        toolbar: "#toolbar",//指定工具栏
        columns: [
            {
                title: "日期",
                field: "day",
                width: "40px",
                align: "center"//水平
            },
            {
                title: "前三",
                field: "isWqb",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "万千十",
            	field: "isWqs",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "万千个",
            	field: "isWqg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "万百十",
            	field: "isWbs",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "万百个",
            	field: "isWbg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "万十个",
            	field: "isWsg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "中三",
            	field: "isQbs",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "千百个",
            	field: "isQbg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "千十个",
            	field: "isQsg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            },
            {
            	title: "后三",
            	field: "isBsg",
                width: "30px",
                cellStyle : function cellStyle(value, row, index) {
                	if(value>0)
                		return {
                			css : {
                				"background-color" : "#87cefa"
                			}
                		};
                	else
                		return {
                    		css : {
                    		}
                    	};
                			
                },
                align: "center",//水平
                valign: "middle"//垂直
            }
        ]
    });
}



</script>

<style>
	body{
		margin-left:200px;
		margin-right:200px;
	}
	
</style>
</head>
<body >
	
	<div align="center" id="day" style="font-size:24px;margin-top:20px">
		重庆时时彩豹子开奖情况
	</div>
    <table id="bzTable"></table>
	
</body>
</html>
