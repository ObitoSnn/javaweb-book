<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%--静态包含base、css样式、jQueryjar包--%>
<%@include file="/pages/common/base_style_jquery.jsp"%>
	<script type="text/javascript">
		$(document).ready(function () {
			if (${not empty requestScope.underStockMsg}) {
				alert("${requestScope.underStockMsg}");
			}
			$(".updateCount").change(function () {
				if (confirm("确定将【" + $(this).parent().parent().find("td:eq(0)").text() + "】的数量修改为【" + this.value + "】吗")) {
					location.href = "${pageScope.basePath}cartServlet?action=updateCount&id=" + $(this).attr("bookId") + "&count=" + this.value;
				} else {
					this.value = this.defaultValue;
				}
			});
			$(".deleteItem").click(function () {
				return confirm("确定要删除【" + $(this).parent().parent().find("td:eq(0)").text() + "】吗");
			});
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗");
			});
		});
	</script>
</head>
<body>

	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/login_regist_success.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td><input bookId="${entry.value.id}" class="updateCount" type="text" value="${entry.value.count}" style="width: 80px;"></td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>