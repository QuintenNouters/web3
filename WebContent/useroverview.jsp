<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="User overview" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Overview of users" name="title" />
		</jsp:include>
		<main>
		<table>
			<tr>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Delete</th>
				<th>Check password</th>
			</tr>
			<c:forEach var="user" items="${allUsers}">
				<tr>
					<td><c:out value="${user.email}"/></td>
					<td><c:out value="${user.firstName}"/></td>
					<td><c:out value="${user.lastName}"/></td>
					<td><a href="Controller?action=showDeleteUserPage&id=${user.userId}">Delete</a></td>
					<td><a href="Controller?action=showCheckPasswordPage&id=${user.userId}">Check</a></td>
				</tr>
			</c:forEach>

			<caption>Users Overview</caption>
		</table>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showUserOverviewPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>