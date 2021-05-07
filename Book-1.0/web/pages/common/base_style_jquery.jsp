<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/11/13
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String base = request.getScheme()//协议
            + "://"
            + request.getServerName()//ip地址，域名
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", base);
    //http:localhost:8080/Book/
//    request.getServletPath()
%>
<base href="<%=base%>">
<link rel="stylesheet" href="static/css/style.css" type="text/css">
<script src="static/script/jquery-1.7.2.js" type="text/javascript"></script>
