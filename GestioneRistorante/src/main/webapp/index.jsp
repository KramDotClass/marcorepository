<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<head>
</head>
<body>
	<table border="1px" align="center">
		<tr>
			<th>Codice ordine</th>
			<th>Codice camerirere</th>
			<th>Numero tavolo</th>
			<th>Numero coperti</th>
			<th>totale</th>
			<th colspan="2">Azione</th>
		</tr>
		<c:forEach items="${lista}" var="ordine">
			<tr align="center">
				<td>Ord-${ordine.codOrdine}</td>
				<td>Cam-${ordine.cameriere.codCameriere}</td>
				<td>tav-${ordine.numTavolo}</td>
				<td>${ordine.numCoperti}</td>
				<td>${ordine.totale}&euro;</td>
				<td><button>Modifica</button></td>
				<td><form action="delOrdine" method="get">
						<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
						<button>Elimina</button>
					</form></td>
			</tr>
		</c:forEach>
		<tr align="center">
			<td colspan="7">
				<form action="nuovoOrdine">
					<input type="submit" value="INSERISCI NUOVO ORDINE" />
				</form>
			</td>
		</tr>
	</table>


</body>
</html>