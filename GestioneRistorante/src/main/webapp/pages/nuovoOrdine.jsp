<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <%@include file="ordine.css" %>
    </style>
<title>Nuovo ordine</title>
<!-- <script type="text/javascript" src="js/script.js"></script> -->
</head>
<body>
<img class="begear" src="<%=request.getContextPath()%>/resources/img/logo.png">
	<form action="newOrder" method="post">
		<table align="center">
			<tr>
				<th>CODICE CAMERIERE</th>
				<th>NUMERO TAVOLO</th>
				<th>NUMERO COPERTI</th>
			</tr>
			<tr>
				<td><input type="text" name="codCameriere"
					placeholder="Inserisci ID cameriere" value="${ordine.cameriere.codCameriere}"
					required /></td>

				<td><input type="text" name="numTavolo" placeholder="Inserisci numero tavolo"
					value="${ordine.numTavolo}" required /></td>

				<td><input type="number" min="0" name="numCoperti"
					placeholder="Inserisci numero coperti" value="${ordine.numCoperti}" value="1"
					required /></td>
			</tr>
			<tr>
				<th colspan="3">Men�</th>
			</tr>
			<c:forEach items="${menu}" var="menuEntry">
				<tr>
					<td id="prodotto" colspan="2">${menuEntry.key.nome}</td>
					<td ><input type="number" min="0"
						name="quantity${menuEntry.key.nome}" value="${menuEntry.value}" /></td>
				</tr>
			</c:forEach>
			<tr id="conferma" style="border:0px" align="right" >
				<td colspan="3" style="border:0px"><input align="right" id = "bottone" type="submit" value="CONFERMA" /></td>
			</tr>

		</table>
		<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
	</form>
	<form action="index" method="get">
		<button>Home</button>
	</form>
</body>
</html>