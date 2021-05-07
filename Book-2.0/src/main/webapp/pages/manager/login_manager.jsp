<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录页面</title>
    <%--静态包含base、css样式、jQueryjar包--%>
    <%@include file="/pages/common/base_style_jquery.jsp"%>
</head>
<body>
<div id="login_header">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>
    <c:if test="${sessionScope.user.username == 'root'}">
        <jsp:forward page="/manager/bookServlet?action=page"></jsp:forward>
    </c:if>
    <c:if test="${sessionScope.user.username != 'root'}">
    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员</h1>
                    <%--<a href="pages/user/regist.jsp">立即注册</a>--%>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        <%--请输入用户名和密码--%>
                        <%--<%=request.getAttribute("msg") == null ? "请输入用户名和密码" : request.getAttribute("msg")%>--%>
                        ${empty requestScope.msg ? "请输入密码" : requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" value="root"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </c:if>
</div>
<%--静态包含版权信息--%>
<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>