<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
<%@include file="/resources/css/ordine.css"%>
<%@include file="/resources/css/ristorante.css"%>
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/script.js"></script>
<title>Nuovo ordine</title>
</head>
<body>
	<img class="begear"
		src="<%=request.getContextPath()%>/resources/img/logo.png">
	<c:if test="${ordine.codOrdine > 0 }">
		<h3 align="center">Codice Ordine: Ord-${ordine.codOrdine}</h3>
	</c:if>

	<form action="newOrder" method="post">
		<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
		<input type="hidden" value="${msg}" name="msg" />
		<table align="center" class="insert">
			<tr>
				<th>CODICE CAMERIERE</th>
				<th>NUMERO TAVOLO</th>
				<th>NUMERO COPERTI</th>
			</tr>
			<tr>
				<td class="transparent noaction" align="center"><input
					type="number" min="1" name="codCameriere" placeholder="ID"
					value="${ordine.cameriere.codCameriere}" class="fields" required /></td>

				<td class="transparent noaction" align="center"><input
					type="number" min="1" name="numTavolo" placeholder="Tavolo"
					value="${ordine.numTavolo}" class="fields" required /></td>

				<td class="transparent noaction" align="center"><input
					type="number" min="1" name="numCoperti" placeholder="Coperti"
					value="${ordine.numCoperti}" value="1" class="fields" required /></td>
			</tr>
			<tr>
				<th colspan="3">Menù</th>
			</tr>
			<c:set var = "tipologia" scope = "session" value = "" />
			<c:forEach items="${menu}" var="menuEntry">
				<tr>
				
					
					<c:if test="${tipologia != menuEntry.key.tipologia}">
					<c:set var = "tipologia" scope = "session" value = "${menuEntry.key.tipologia}" />
					<tr>
						<th>
						${tipologia}
						</th>
					</tr>
					</c:if>	
					
			
				<td id="prodotto" colspan="2">${menuEntry.key.nome}</td>
				<td class="transparent noaction" align="center"><input
					type="button" class="buttonsPlusMinun"
					onclick="decrement('${menuEntry.key.nome}')" value=" - "><input
					type="number" min="0" max="100"
					name="quantity${menuEntry.key.nome}" value="${menuEntry.value}"
					class="quantity" id="myNumber${menuEntry.key.nome}" required /><input
					type="button" class="buttonsPlusMinun"
					onclick="increment('${menuEntry.key.nome}')" value="+"></td>
				</tr>
			</c:forEach>
			<tr style="border: 0px" align="right">
				<td class="transparent noaction" align="center" colspan="3"
					style="border: 0px"><input class="bottone" type="submit"
					value="CONFERMA" /></td>
			</tr>
			</form>
			<tr>
				<td class="transparent noaction" colspan="3" align="center">
					<form action="index" method="get">
						<button class="bottone">HOME</button>
					</form>
				</td>
			</tr>
		</table>
</body>
</html>