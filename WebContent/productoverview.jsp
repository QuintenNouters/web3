<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Product Overview" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Overview of products" name="title" />
		</jsp:include>
		<main>
		<table>
			<tr>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
			</tr>
			<c:forEach var="product" items="${allProducts}">
				<tr>
					<td><a href="Controller?action=showUpdateProductPage&id=${product.productId}"><c:out value="${product.name}"/></a></td>
					<td><c:out value="${product.description}"/></td>
					<td><c:out value="${product.price}"/></td>
					<td><a href="Controller?action=showDeleteProductPage&id=${product.productId}">Delete</a></td>
				</tr>
			</c:forEach>

			<caption>Product Overview</caption>
		</table>
		
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showProductOverviewPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>