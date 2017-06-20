<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/script.js"></script>
</head>
<body>
	<table border="1px" align="center">
		<tr>
			<th>Codice ordine</th>
			<th>Codice cameriere</th>
			<th>Numero tavolo</th>
			<th>Numero coperti</th>
			<th>totale</th>
			<th colspan="2">Azione</th>
		</tr>
		<c:forEach items="${lista}" var="ordine">
			<tr align="center">
				<td><a href="javascript:apri('ordine/${ordine.codOrdine}');">Ord-${ordine.codOrdine}</a></td>
				<td>Cam-${ordine.cameriere.codCameriere}</td>
				<td>tav-${ordine.numTavolo}</td>
				<td>${ordine.numCoperti}</td>
				<td>${ordine.totale}&euro;</td>
				<td><form action="updatePage" method="post">
						<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
						<button>Modifica</button>
					</form></td>
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