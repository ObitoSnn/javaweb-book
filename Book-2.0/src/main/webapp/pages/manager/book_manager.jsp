<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%--静态包含base、css样式、jQueryjar包--%>
<%@include file="/pages/common/base_style_jquery.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("td a.deleteClass").click(function () {
				var $trObj = $(this).parent().parent();
				var $tdObj = $trObj.find("td:eq(0)");
				/*
                    confirm(要传入的提示信息)
                    按确定返回true，执行行为
                    按取消返回false，阻止元素默认行为
                 */
				return confirm("你确定要删除[" + $tdObj.text() + "]");
			});
			$("#searchPageNoBtn").click(function () {
				/*
					javascript 语言提供了一个location地址栏对象
					它有一个属性href，它可以获取浏览器地址栏中的地址
					href属性可读可写
				 */
				var pageNo = $("#pn_input").val();
				if (pageNo > 0 && pageNo <= ${requestScope.page.pageTotal}) {
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
				} else {
					alert("输入的页码错误！");
				}
			});
		});
	</script>
</head>
<body>
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">图书管理系统</span>
			<%--静态包含管理菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=deleteBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>
		</table>
		<%--静态包含分页--%>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
	<%--静态包含版权信息--%>
	<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>