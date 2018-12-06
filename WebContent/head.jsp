<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>${param.tabTitle}</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<c:if test="${color == 'red'}">
	<link rel="stylesheet" type="text/css" href="css/red.css">
</c:if>
<c:if test="${color == 'yellow'}">
	<link rel="stylesheet" type="text/css" href="css/yellow.css">
</c:if>
</head>