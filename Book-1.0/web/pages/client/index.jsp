<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%--静态包含base、css样式、jQueryjar包--%>
	<%@include file="/pages/common/base_style_jquery.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#searchPageNoBtn").click(function () {
				var pageNo = $("#pn_input").val();
				if (pageNo < 1 || pageNo > ${requestScope.page.pageTotal}) {
					alert("输入页码有误");
				} else {
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + $("#pn_input").val();
				}
			});

			$("button#addItem").click(function () {
				$.getJSON("${basePath}cartServlet", "action=ajaxAddItem&id=" + $(this).attr("bookId"), function (data) {
					console.log(data);
					<%--您的购物车中有${sessionScope.cart.totalCount}件商品--%>
					$("span.totalCount").text("您的购物车中有" + data.totalCount + "件商品");
					<%--${sessionScope.lastItemName}--%>
					$("span.lastItemName").text(data.lastItemName);
				})
			});

			$(".notLoginAddItem").click(function () {
				alert("亲爱的用户，您还没有登录无法将此商品添加至购物车，请点击右上角登录按钮进入登录页面。");
			});
		});

	</script>
</head>
<body>
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">网上书城</span>

			<c:if test="${empty sessionScope.user}">
				<div>
					<a href="pages/user/login.jsp">登录</a> |
					<a href="pages/user/regist.jsp">注册</a>
					<a href="pages/manager/manager.jsp">后台管理</a>
				</div>
			</c:if>
			<c:if test="${not empty sessionScope.user && sessionScope.user.username != 'root'}">
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
					<a href="userServlet?action=logout">注销</a>
					<a href="orderServlet?action=showOrders">我的订单</a>
					<a href="pages/cart/cart.jsp">购物车</a>
				</div>
			</c:if>
			<c:if test="${not empty sessionScope.user && sessionScope.user.username == 'root'}">
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>
					<a href="userServlet?action=logout">注销</a>
					<a href="orderServlet?action=showOrders">我的订单</a>
					<a href="pages/cart/cart.jsp">购物车</a>
					<a href="pages/manager/manager.jsp">后台管理</a>
				</div>
			</c:if>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice"/>
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${not empty sessionScope.cart.items}">
					<span class="totalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
					<div>
						您刚刚将<span class="lastItemName" style="color: red">${sessionScope.lastItemName}</span>加入到了购物车中
					</div>
				</c:if>
				<c:if test="${empty sessionScope.cart.items}">
					<span class="totalCount"> </span>
					<div>
						<span class="lastItemName" style="color: red">当前购物车为空</span>
					</div>
				</c:if>
			</div>
			<c:forEach items="${requestScope.page.items}" var="book">
				<c:if test="${book.stock > 0}">
					<div class="b_list">
						<div class="img_div">
							<img class="book_img" src="${book.imgPath}" />
						</div>
						<div class="book_info">
							<div class="book_name">
								<span class="sp1">书名:</span>
								<span class="sp2">${book.name}</span>
							</div>
							<div class="book_author">
								<span class="sp1">作者:</span>
								<span class="sp2">${book.author}</span>
							</div>
							<div class="book_price">
								<span class="sp1">价格:</span>
								<span class="sp2">￥${book.price}</span>
							</div>
							<div class="book_sales">
								<span class="sp1">销量:</span>
								<span class="sp2">${book.sales}</span>
							</div>
							<div class="book_amount">
								<span class="sp1">库存:</span>
								<span class="sp2">${book.stock}</span>
							</div>
							<c:if test="${not empty sessionScope.user}">
								<div class="book_add">
									<button bookId="${book.id}" id="addItem">加入购物车</button>
								</div>
							</c:if>
							<c:if test="${empty sessionScope.user}">
								<div class="book_add">
									<button class="notLoginAddItem">加入购物车</button>
								</div>
							</c:if>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
		<%--静态包含分页--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>

</body>
</html>