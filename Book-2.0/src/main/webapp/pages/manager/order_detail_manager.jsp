<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<%--静态包含base、css样式、jQueryjar包--%>
<%@include file="/pages/common/base_style_jquery.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
	<script type="text/javascript">
		$(function () {
			$("#returnOrder").click(function () {
				$(this).attr("href", "${basePath}manager/orderServlet?action=page");
			});
		});
	</script>
</head>
<body>
	<div id="header">
			<span class="wel_word">订单详情</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
				<a href="orderServlet?action=showOrders">我的订单</a>
				<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
				<a id="returnOrder" href="">返回</a>
			</div>
	</div>
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td>${orderItem.name}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.totalPrice}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>