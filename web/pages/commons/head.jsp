<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/8/11
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";

    pageContext.setAttribute("basePath", basePath);
%>

<!--
base标签它可以规定所有相对路径工作时参照的路径
一般在项目中使用的时候,写到工程路径

base标签只影响相对路径 , 对绝对路径无效
-->
<base href="${basePath}">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
