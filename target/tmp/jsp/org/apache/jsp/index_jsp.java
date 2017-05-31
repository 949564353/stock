package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"js/bootstrap/css/bootstrap.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"js/bootstrap-table.css\" />\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery-3.2.1.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap/js/bootstrap.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/bootstrap-table.js\"></script>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\t\n");
      out.write("$(document).ready(function() {\n");
      out.write("\t$.ajax({\n");
      out.write("\t\turl : '/cqssc/getCurrentDay.sc',// è·³è½¬å° action\n");
      out.write("\t\tdata : {},\n");
      out.write("\t\ttype : 'post',\n");
      out.write("\t\tcache : false,\n");
      out.write("\t\tasync: false,\n");
      out.write("\t\tdataType : 'json',\n");
      out.write("\t\tsuccess : function(obj) {\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar initData =[];\n");
      out.write("\t\t\tif(obj!=null && obj.length>0){\n");
      out.write("\t    \t\tfor(var i=0;i<obj.length;i++){\n");
      out.write("\t    \t\t\tvar row = {};\n");
      out.write("\t    \t\t\trow[\"no\"] = obj[i].no;\n");
      out.write("\t    \t\t\trow[\"num\"] = obj[i].num;\n");
      out.write("\t    \t\t\tinitData.push(row);\n");
      out.write("\t    \t\t}\n");
      out.write("\t    \t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\tvar $table = $('#cqssc-data').bootstrapTable({\n");
      out.write("\t\t\t\t//url: 'data1.json',  éè¿urlå¯ä»¥ä»åå°è·åè¡¨æ ¼æ°æ®\n");
      out.write("\t\t\t\ttoolbar: \"#configToolbar\",\t//å¯ç¨é¡¶é¨å·¥å·æ \n");
      out.write("\t\t\t\tsearch: true,\t\t\t//å¯ç¨æç´¢æ¡\n");
      out.write("\t\t\t\tshowRefresh: true, \t\t//å¯ç¨å·æ°åè½\n");
      out.write("\t\t\t\tshowExport: true,\t\t//å¯ç¨å¯¼åºåè½\n");
      out.write("\t\t\t\tshowToggle: true,\t\t//å¯ç¨2ç§è¡¨æ ¼è§å¾åæ¢\n");
      out.write("\t\t\t\tshowColumns: true,\t\t//å¯ç¨èªå®ä¹ååè½\n");
      out.write("\t\t\t\tpagination: true,\t\t//å¯ç¨åé¡µ\n");
      out.write("\t\t\t\tshowHeader: true,\n");
      out.write("\t\t\t\tmultipleSearch: true,\n");
      out.write("\t\t\t\tuniqueId: \"id\",\n");
      out.write("\t\t\t\tpageList: \"[8, 25, 50, 100, all]\",\n");
      out.write("\t\t\t\tcolumns: [\n");
      out.write("\t\t\t        {\n");
      out.write("\t\t\t            field : 'no',\n");
      out.write("\t\t\t            title : 'æå·',\n");
      out.write("\t\t\t            align : 'center',\n");
      out.write("\t\t\t            valign : 'middle',\n");
      out.write("\t\t\t            sortable : true\n");
      out.write("\t\t\t        }, {\n");
      out.write("\t\t\t            field : 'num',\n");
      out.write("\t\t\t            title : 'å¼å¥å·ç ',\n");
      out.write("\t\t\t            align : 'center',\n");
      out.write("\t\t\t            valign : 'middle',\n");
      out.write("\t\t\t            sortable : true\n");
      out.write("\t\t\t        }],\n");
      out.write("\t\t\t\tdata: initData\n");
      out.write("\t\t\t\t}); \n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\n");
      out.write("\t    \t\n");
      out.write("\t\t\t\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\tinitTable();\n");
      out.write("\t\n");
      out.write("})\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("function initTable(){\n");
      out.write("    var url = \"/cqssc/getCurrentDay.sc&random=\"+Math.random();\n");
      out.write("    $('#demo-table').bootstrapTable({\n");
      out.write("        method:'POST',\n");
      out.write("        dataType:'json',\n");
      out.write("        contentType: \"application/x-www-form-urlencoded\",\n");
      out.write("        cache: false,\n");
      out.write("        striped: true,                              //æ¯å¦æ¾ç¤ºè¡é´éè²\n");
      out.write("        sidePagination: \"server\",           //åé¡µæ¹å¼ï¼clientå®¢æ·ç«¯åé¡µï¼serveræå¡ç«¯åé¡µï¼*ï¼\n");
      out.write("        url:url,\n");
      out.write("        height: $(window).height() - 110,\n");
      out.write("        width:$(window).width(),\n");
      out.write("        showColumns:true,\n");
      out.write("        pagination:true,\n");
      out.write("        queryParams : \"\",\n");
      out.write("        minimumCountColumns:2,\n");
      out.write("        pageNumber:1,                       //åå§åå è½½ç¬¬ä¸é¡µï¼é»è®¤ç¬¬ä¸é¡µ\n");
      out.write("               pageSize: 20,                       //æ¯é¡µçè®°å½è¡æ°ï¼*ï¼\n");
      out.write("              pageList: [10, 25, 50, 100],        //å¯ä¾éæ©çæ¯é¡µçè¡æ°ï¼*ï¼\n");
      out.write("              uniqueId: \"id\",                     //æ¯ä¸è¡çå¯ä¸æ è¯ï¼ä¸è¬ä¸ºä¸»é®å\n");
      out.write("        showExport: true,                    \n");
      out.write("        exportDataType: 'all',\n");
      out.write("        responseHandler: responseHandler,\n");
      out.write("        columns: [\n");
      out.write("        {\n");
      out.write("            field : 'no',\n");
      out.write("            title : 'æå·',\n");
      out.write("            align : 'center',\n");
      out.write("            valign : 'middle',\n");
      out.write("            sortable : true\n");
      out.write("        }, {\n");
      out.write("            field : 'num',\n");
      out.write("            title : 'å¼å¥å·ç ',\n");
      out.write("            align : 'center',\n");
      out.write("            valign : 'middle',\n");
      out.write("            sortable : true\n");
      out.write("        }]\n");
      out.write("    });\n");
      out.write("}\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h2>Hello World!</h2>\n");
      out.write("\t<table id=\"cqssc-table\">\n");
      out.write("    </table>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
