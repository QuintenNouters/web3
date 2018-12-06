<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Error" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Something went wrong!" name="title" />
		</jsp:include>
		<main>
			<p>You caused a ${pageContext.exception} on the server!</p>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="Error" name="page" />
		</jsp:include>
	</div>
</body>
</html>