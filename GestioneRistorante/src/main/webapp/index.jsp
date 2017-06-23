<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, 
initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/script.js"></script>
	<style type="text/css">
    <%@include file="/resources/css/ristorante.css" %>
    </style>
</head>
<body>
<img class="begear" src="<%=request.getContextPath()%>/resources/img/logo.png">
<h3 align="center"><c:if test="${msg != null}" >${msg}</c:if></h3>
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
				<td>Tav-${ordine.numTavolo}</td>
				<td>${ordine.numCoperti}</td>
				<td>${ordine.totale}&euro;</td>
				<td class="transparent"><form action="updatePage" method="post">
						<div class="image-upload">
							<label for="file-edit${ordine.codOrdine}"> <img src="<%=request.getContextPath()%>/resources/img/edit.png" width="40%" height="40%" />
							</label> <input id="file-edit${ordine.codOrdine}" type="submit" />
						</div>
						<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
			
					</form></td>
				<td class="transparent"><form action="delOrdine" method="post" >
		
					<input type="hidden" value="${ordine.codOrdine}" name="codOrdine" />
					<div class="image-upload">
							<label for="file-delete${ordine.codOrdine}"> <img src="<%=request.getContextPath()%>/resources/img/delete.png"
								width="40%" height="40%" />
							</label> <input id="file-delete${ordine.codOrdine}" type="submit" onclick="return confirm('Sei sicuro?')"  />
						</div>
					</form></td>
					
			</tr>
		</c:forEach>
		<tr align="center" class="transparent noaction">
			<td colspan="7" class="transparent noaction">
				<form action="nuovoOrdine">
					<input type="submit" value="+" class="mod" />
				</form>
			</td>
		</tr>
	</table>

</body>
</html>