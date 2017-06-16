<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuovo ordine</title>
<!-- <script type="text/javascript" src="js/script.js"></script> -->
</head>
<body>
	<form action="newOrder" method="post">
		<input type="text" name="codCameriere" placeholder="ID CAMERIERE" required /><br />
		<input type="text" name="numTavolo" placeholder="TAVOLO" required /><br /> <input
			type="text" name="numCoperti" placeholder="1 COPERTO" required /><br />
		<c:set var="i" value="0" scope="page" />
		<c:forEach items="${menu}" var="menuEntry">
			${menuEntry.nome}<input type="number" min="0" name="quantity${i}" value="0" /><br />
			<c:set var="i" value="${i + 1}" scope="page" />
		</c:forEach>
		<input type="hidden" value="${i}" name="numElements" /><br /> <input type="submit"
			value="INSERISCI" />
	</form>
</body>
</html>