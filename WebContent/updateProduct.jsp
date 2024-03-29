<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Update Product" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Update Product" name="title" />
		</jsp:include>
		<main> 
		<c:set var="errors" value="${errors}" /> 
		<c:if test="${errors != null }">
			<div class="alert-danger">
				<ul>
				<c:forEach var="error" items="${errors}">
					<li>${error}</li>
				</c:forEach>
				</ul>
			</div>
		</c:if>
		<form method="post" action="Controller?action=doUpdateProduct" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<input type="hidden" id="id" name="id" required <c:out value="${product.productId}"/>>
			</p>
			<p>
				<label for="name">Name</label><input type="text" id="name"
					name="name" required <c:out value="${product.name}"/>>
			</p>
			<p>
				<label for="description">Description</label><input type="text"
					id="description" name="description" required <c:out value="${product.description}"/>>
			</p>
			<p>
				<label for="price">Price</label><input type="text"
					id="price" name="price" required <c:out value="${product.price}"/>>
			</p>
			<p>
				<input type="submit" id="update" value="Update">
			</p>

		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showUpdateProductPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>
