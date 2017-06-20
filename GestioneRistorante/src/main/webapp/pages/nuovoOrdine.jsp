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
		<table border="1px" align="center">
			<tr>
				<th>Codice cameriere</th>
				<th>Numero tavolo</th>
				<th>Numero coperti</th>
			</tr>
			<tr>
				<td><input type="text" name="codCameriere"
					placeholder="ID CAMERIERE" value="${ordine.cameriere.codCameriere}"
					required /></td>

				<td><input type="text" name="numTavolo" placeholder="TAVOLO"
					value="${ordine.numTavolo}" required /></td>

				<td><input type="number" min="1" name="numCoperti"
					placeholder="NUMERO COPERTI" value="${ordine.numCoperti}" value="1"
					required /></td>
			</tr>
			<tr>
				<th colspan="3">Menu</th>
			</tr>
			<c:forEach items="${menu}" var="menuEntry">
				<tr>
					<td colspan="2">${menuEntry.key.nome}</td>
					<td><input type="number" min="0"
						name="quantity${menuEntry.key.nome}" value="${menuEntry.value}" /></td>
				</tr>
			</c:forEach>
			<tr style="border:0px" align="right" >
				<td colspan="3" style="border:0px"><input type="submit" value="CONFERMA" /></td>
			</tr>

		</table>
		<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
	</form>

</body>
</html>