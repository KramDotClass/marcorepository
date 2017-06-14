<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ page isELIgnored="false"%>
</head>
<body>
	<c:forEach items="${lista}" var="ordine">

           ${ordine.codOrdine} <br />
           ${ordine.totale}<br />
           ${ordine.numCoperti}<br />
	</c:forEach>
</body>
</html>