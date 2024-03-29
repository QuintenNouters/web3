<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Add Product" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Add Product" name="title" />
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
		
		<form method="post" action="Controller?action=doAddProduct" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<label for="name">Name</label><input type="text" id="name"
					name="name" required <c:out value="${param.name}"/>>
			</p>
			<p>
				<label for="description">Description</label><input type="text"
					id="description" name="description" required <c:out value="${param.description}"/>>
			</p>
			<p>
				<label for="price">Price</label><input type="text"
					id="price" name="price" required <c:out value="${param.price}"/>>
			</p>
			<p>
				<input type="submit" id="add" value="Add">
			</p>

		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showAddProductPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>
