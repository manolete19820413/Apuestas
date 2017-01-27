<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.manolete.apuestas.euromillones.Sorteo"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<% 
	List<Sorteo> sorteos = (List<Sorteo>) request.getAttribute("sorteos");
	SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
%>
<html>
	<head>
		<title>Sorteos :: Listado JPA</title>
	</head>
<body>
	<h1>Sorteos - Listado JPA</h1>
	<table>
		<tr>
			<th>Fecha</th>
			<th>Número 1</th>
			<th>Número 2</th>
			<th>Número 3</th>
			<th>Número 4</th>
			<th>Número 5</th>
			<th>Estrella 1</th>
			<th>Estrella 2</th>
		</tr>
	<% for (Sorteo sorteo : sorteos) { %>
		<tr>
			<td><%= formatter.format(sorteo.getFecha_sorteo()) %></td>
			<td><%= sorteo.getNum1() %></td>
			<td><%= sorteo.getNum2() %></td>
			<td><%= sorteo.getNum3() %></td>
			<td><%= sorteo.getNum4() %></td>
			<td><%= sorteo.getNum5() %></td>
			<td><%= sorteo.getEstrella1() %></td>
			<td><%= sorteo.getEstrella2() %></td>
		</tr>
	<% } %>

		
	</table>
</body>
</html>