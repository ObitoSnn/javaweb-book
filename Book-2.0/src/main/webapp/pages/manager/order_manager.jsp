<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%--静态包含base、css样式、jQueryjar包--%>
<%@include file="/pages/common/base_style_jquery.jsp"%>
</head>
<body>
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">订单管理系统</span>
			<%--静态包含管理菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="order">

				<tr>
					<td>${order.orderId}</td>
					<td>${order.createTime}</td>
					<td>${order.totalPrice}</td>
					<td><a href="manager/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status != 0}">
							<td></td>
						</c:when>
						<c:when test="${order.status == 0}">
							<td><a href="manager/orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>

	</div>
	<%@include file="/pages/common/page_nav.jsp"%>
	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>