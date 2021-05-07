<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含base、css样式、jQueryjar包--%>
	<%@include file="/pages/common/base_style_jquery.jsp"%>
</head>
<body>
	<jsp:forward page="client/bookServlet?action=page"></jsp:forward>
</body>
</html>