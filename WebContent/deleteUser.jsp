<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Delete User" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Delete User" name="title" />
		</jsp:include>
		<main>
			<h3>Are you sure you want to delete this user?</h3>
			<form method="post" action="Controller?action=doDeleteUser" novalidate="novalidate">
				<input type="hidden" id="userId" name="userId" required <c:out value="${param.id}"/>/>
				<input type="submit" id="Yes" value="Yes"/>
			</form>
			<form method="post" action="Controller?action=showUserOverviewPage" novalidate="novalidate">
				<input type="submit" id="No" value="No"/>
			</form>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showDeleteUserPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>