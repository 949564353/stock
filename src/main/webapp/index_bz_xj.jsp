<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="utf-8">
	<title>新疆-豹子</title>
<link rel="stylesheet" type="text/css" href="js/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="js/bootstrap-table/bootstrap-table.css" />
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-table/bootstrap-table.js"></script> 
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

<script>
	
$(document).ready(function() {
	requestData();
})

function requestData(){
	$('#bzTable').bootstrapTable('destroy');
	$('#bzTable').bootstrapTable({
        url: "xjssc/getBzList.sc",//数据源
        toolbar: "#modalToolbar",	//启用顶部工具栏
		//search: true,			//启用搜索框
		//showRefresh: true, 		//启用刷新功能
		//showExport: true,		//启用导出功能
		//showToggle: true,		//启用2种表格视图切换
		//showColumns: true,		//启用自定义列功能
        pageNumber: 1,
        pageSize: 15,
		pagination: true,		//启用分页
		pageList: "[8, 25, 50, 100, all]",
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
                title: "组选10",
                field: "wxType",
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
                title: "组选5",
                field: "bsgType",
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
                title: "组30",
                field: "bz30",
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
                title: "组20",
                field: "bz20",
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
                title: "组3",
                field: "bz3",
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
<body>
	
	<div align="center" id="day" style="font-size:24px;margin-top:20px">
		新疆时时彩豹子开奖情况
	</div>
	<div class="container" style="width: 960px">
   	 	<table id="bzTable"></table>
	</div>
	
</body>
</html>
