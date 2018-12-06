<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Log In" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Log In" name="title" />
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
		
		<form method="post" action="Controller?action=doLogIn" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<label for="userid">User id</label><input type="text" id="userid"
					name="userid" required <c:out value="${param.userid}"/>>
			</p>
			<p>
				<label for="password">Password</label><input type="password"
					id="password" name="password" required>
			</p>
			<p>
				<input type="submit" id="signUp" value="Sign Up">
			</p>

		</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showLogInPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>
