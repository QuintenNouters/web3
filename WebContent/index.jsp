<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Home" name="tabTitle" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Home" name="title" />
		</jsp:include>
		
		<c:if test="${error != null }">
			<div class="alert-danger">
				<ul>
					<li>${error}</li>
				</ul>
			</div>
		</c:if>
		
		<main>
			<c:if test ="${auth}">
				<h3>Welcome ${user.firstName}</h3> 
			</c:if>
			<p>
			Sed ut perspiciatis unde omnis iste natus error sit
			voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
			ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
			dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
			aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
			qui ratione voluptatem sequi nesciunt. 
			</p>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param value="showHomePage" name="page" />
		</jsp:include>
	</div>
</body>
</html>