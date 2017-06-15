<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuovo ordine</title>
</head>
<body>
	<form action="newOrder" method="post">
		<input type = "text" name = "codCameriere" placeholder="ID CAMERIERE"/><br />
		<input type = "text" name = "numTavolo" placeholder="TAVOLO"/><br />
		<input type = "text" name = "numCoperti" placeholder="1 COPERTO"/><br />
		<c:forEach items="${menu}" var = "menuEntry">
			 ${menuEntry.nome}<input type="text" name="saràparametrizzato" />
		</c:forEach>
		
		<input type="submit" value="INSERISCI" />
	</form>
</body>
</html>