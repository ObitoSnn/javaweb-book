<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员登录成功页面</title>
    <%--静态包含base、css样式、jQueryjar包--%>
    <%@include file="/pages/common/base_style_jquery.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }
    </style>
</head>
<body>
<div id="header">
    <%--静态包含注册或登录成功页面--%>
    <%@ include file="/pages/common/login_regist_success.jsp" %>
</div>
<div id="main">
    <h1><a href="index.jsp">转到主页</a></h1>
</div>
<%--静态包含版权信息--%>
<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>