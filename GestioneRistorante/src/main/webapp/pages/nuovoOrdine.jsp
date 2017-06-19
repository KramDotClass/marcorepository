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
		<input type="hidden" value="${ordine.codOrdine}" />
		<input type="text" name="codCameriere" placeholder="ID CAMERIERE" value="${ordine.cameriere.codCameriere}" required /><br />
		<input type="text" name="numTavolo" placeholder="TAVOLO" value="${ordine.numTavolo}" required /><br /> <input
			type="number" min="0" name="numCoperti" placeholder="NUMERO COPERTI" value="${ordine.numCoperti}" value="1" required /><br />
		<c:set var="i" value="0" scope="page" />
		<c:forEach items="${menu}" var="menuEntry">
			${menuEntry.key.nome}<input type="number" min="0" name="quantity${i}" value="${menuEntry.value}" /><br />
			<c:set var="i" value="${i + 1}" scope="page" />
		</c:forEach>
		<input type="hidden" value="${i}" name="numElements" /><br /> <input type="submit"
			value="INSERISCI" />
	</form>
</body>
</html>