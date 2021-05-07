<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%--静态包含base、css样式、jQueryjar包--%>
<%@include file="/pages/common/base_style_jquery.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">我的订单</span>
			<%@include file="/pages/common/login_regist_success.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.orders}">
			<table>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="4"><a href="index.jsp" style="text-decoration: none;">当前无订单，快去主页添加商品至购物车吧</a></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty requestScope.orders}">
			<table>
				<tr>
					<td>订单号</td>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
				</tr>
				<c:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.orderId}</td>
					<td>${order.createTime}</td>
					<td>${order.totalPrice}</td>
					<td>
						<c:choose>
							<c:when test="${order.status == 0}">
								未发货
							</c:when>
							<c:when test="${order.status == 1}">
								已发货
							</c:when>
							<c:when test="${order.status == 2}">
								已签收
							</c:when>
						</c:choose>
					</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
	</div>

	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>