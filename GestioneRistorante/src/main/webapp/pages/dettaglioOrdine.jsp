<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettaglio ordine</title>
</head>
<body>
	<table border="1px" align="center">

		<tr>
			<td colspan="3">Codice ordine: <c:out
					value="${ordine.codOrdine}" /></td>
		</tr>
		<tr>
			<th>Piatto</th>
			<th>Quantità</th>
			<th>Prezzo <br />(Unitario)
			</th>
		</tr>
		<c:forEach var="entry" items="${lista}">
			<tr align="right">
				<td><c:out value="${entry.key.nome}" /></td>
				<td><c:out value="${entry.value}" /></td>
				<td><c:out value="${entry.key.prezzo}" /> &euro;</td>
			</tr>
		</c:forEach>
		<tr>
			<th colspan="3">Totale</th>
		</tr>
		<tr align="right">
			<td colspan="3"><c:out value="${ordine.totale}" /> &euro;</td>
		</tr>
	</table>
</body>
</html>