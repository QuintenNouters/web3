<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<h1>
		<span>Web shop</span>
	</h1>
	<nav>
		<ul>
			<li ${param.title == 'Home' ?  "id=\"actual\"" : ""}><a href="Controller">Home</a></li>
			<c:if test="${auth}">
			<li ${param.title == 'User Overview' ?  "id=\"actual\"" : ""}><a href="Controller?action=showUserOverviewPage">Users</a></li>			
			</c:if>
			<c:if test="${auth}">
			<li ${param.title == 'Product Overview' ?  "id=\"actual\"" : ""}><a href="Controller?action=showProductOverviewPage">Products</a></li>
			</c:if>
			<c:if test="${auth}">
			<li ${param.title == 'Add Product' ?  "id=\"actual\"" : ""}><a href="Controller?action=showAddProductPage">Add product</a></li>
			</c:if>
			<%-- <c:if test="${!auth}"> --%>
			<li ${param.title == 'Sign Up' ?  "id=\"actual\"" : ""}><a href="Controller?action=showSignUpPage">Sign up</a></li>
			<%-- </c:if> --%>
			<c:if test="${!auth}">
				<li ${param.title == 'Log In' ?  "id=\"actual\"" : ""}><a href="Controller?action=showLogInPage">Log in</a></li>
			</c:if>
			<c:if test="${auth}">
				<li ${param.title == 'Log Out' ?  "id=\"actual\"" : ""}><a href="Controller?action=doLogOut">Log Out</a></li>
			</c:if>
		</ul>
	</nav>
	<h2>${param.title}</h2>

</header>