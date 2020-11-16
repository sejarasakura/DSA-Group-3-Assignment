/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.59
 * Generated at: 2020-11-16 13:05:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import adt.ArrList;
import com.google.gson.Gson;
import pages.AdminCreateGraph;
import cilent.Graph_allocation;
import org.apache.commons.text.StringEscapeUtils;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.google.gson.Gson");
    _jspx_imports_classes.add("adt.ArrList");
    _jspx_imports_classes.add("org.apache.commons.text.StringEscapeUtils");
    _jspx_imports_classes.add("pages.AdminCreateGraph");
    _jspx_imports_classes.add("cilent.Graph_allocation");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    AdminCreateGraph acg = new AdminCreateGraph();
    String dataPoints = acg.getSampleData();
    String dataPoints2 = acg.getSampleData2();

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            window.onload = function () {\r\n");
      out.write("\r\n");
      out.write("                var chart = new CanvasJS.Chart(\"chartContainer\", {\r\n");
      out.write("                    theme: \"light1\", // \"light2\", \"dark1\", \"dark2\"\r\n");
      out.write("                    animationEnabled: true,\r\n");
      out.write("                    zoomEnabled: true,\r\n");
      out.write("                    title: {\r\n");
      out.write("                        text: \"User trafic\"\r\n");
      out.write("                    },\r\n");
      out.write("                    data: [{\r\n");
      out.write("                            type: \"line\",\r\n");
      out.write("                            dataPoints: ");
out.print(dataPoints);
      out.write("\r\n");
      out.write("                        }]\r\n");
      out.write("                });\r\n");
      out.write("                chart.render();\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                var chart2 = new CanvasJS.Chart(\"chartContainer2\", {\r\n");
      out.write("                    animationEnabled: true,\r\n");
      out.write("                    exportEnabled: true,\r\n");
      out.write("                    title: {\r\n");
      out.write("                        text: \"Sales\"\r\n");
      out.write("                    },\r\n");
      out.write("                    axisY: {\r\n");
      out.write("                        includeZero: true\r\n");
      out.write("                    },\r\n");
      out.write("                    data: [{\r\n");
      out.write("                            type: \"column\", //change type to bar, line, area, pie, etc\r\n");
      out.write("                            //indexLabel: \"{y}\", //Shows y value on all Data Points\r\n");
      out.write("                            indexLabelFontColor: \"#5A5757\",\r\n");
      out.write("                            indexLabelPlacement: \"outside\",\r\n");
      out.write("                            dataPoints: ");
out.print(dataPoints2);
      out.write("\r\n");
      out.write("                        }]\r\n");
      out.write("                });\r\n");
      out.write("                chart2.render();\r\n");
      out.write("            }\r\n");
      out.write("        </script>\r\n");
      out.write("        <!DOCTYPE html>\r\n");
      out.write("    <html>\r\n");
      out.write("        <head>\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  main.WebConfig.META_URL + (( main.WebConfig.META_URL).indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("title", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Admin", request.getCharacterEncoding()), out, false);
      out.write("    \r\n");
      out.write("        </head>\r\n");
      out.write("        <body>\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  main.WebConfig.ADMIN_HEADER_URL + (( main.WebConfig.ADMIN_HEADER_URL).indexOf('?')>0? '&': '?') + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("menu_bar", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("Overview", request.getCharacterEncoding()), out, false);
      out.write("    \r\n");
      out.write("\r\n");
      out.write("            <div class=\"jumbotron text-center\">\r\n");
      out.write("                <h1>Rent car dashboard</h1>\r\n");
      out.write("                <p>Welcome back admin pages !! </p>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"col-sm-6\">\r\n");
      out.write("                        <div id=\"chartContainer\" style=\"height: 370px; width: 100%;\"></div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col-sm-6\">\r\n");
      out.write("                        <div id=\"chartContainer2\" style=\"height: 370px; width: 100%;\"></div>\r\n");
      out.write("                        <script src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response,  main.WebConfig.ADMIN_FOOTER_URL, out, false);
      out.write("\r\n");
      out.write("        </body>\r\n");
      out.write("    </html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
