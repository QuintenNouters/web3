<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Check Passowrd" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Check Password" name="title" />
		</jsp:include>

		<main>
			<form method="post" action="Controller?action=doCheckPassword" novalidate="novalidate">
				<input type="hidden" id="id" name="id" required value="<c:out value="${param.id}"/>">
				<p>
				<label for="password">Password</label>
				<input type="password" id="password" name="password">
				</p>
				<p>
					<input type="submit" id="check" value="Check password">
				</p>
			</form>
			
			<caption>Outcome of check password</caption>
			<p>${result}</p>
			
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showCheckPasswordPage" name="page" />
		</jsp:include>
	</div>
</body>
</html>