<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.manolete.apuestas.euromillones.Sorteo"%>
<% 
	String frecuencia = request.getParameter("frecuencia") == null ? "true" :  request.getParameter("frecuencia");
	Sorteo prediccion = (Sorteo) request.getAttribute("prediccion");
	SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
%>
<html>
	<body>
		<h1>Prediccion Euromillones</h1>
		<form method="post" action="prediccion">
			<select id="frecuencia" name="frecuencia">
				<option value="true" <%= "true".equals(frecuencia) ? "selected" : "" %>>Más frecuentes</option>
				<option value="false" <%= "false".equals(frecuencia) ? "selected" : "" %>>Menos frecuentes</option>
			</select>
			<input type="submit" value="Buscar" />
		</form>
		<% if (prediccion == null) { %>
		<h3>No hay datos suficientes para generar la predicción</h3>
		<% } else { %>
		<table border="1">
			<tr>
				<th>Fecha</th>
				<th colspan="5">Números</th>
				<th colspan="2">Estrellas</th>
			</tr>
			<tr>
				<td><%= formatter.format(prediccion.getFecha_sorteo()) %></td>
				<td><%= prediccion.getNum1() %></td>
				<td><%= prediccion.getNum2() %></td>
				<td><%= prediccion.getNum3() %></td>
				<td><%= prediccion.getNum4() %></td>
				<td><%= prediccion.getNum5() %></td>
				<td><%= prediccion.getEstrella1() %></td>
				<td><%= prediccion.getEstrella2() %></td>
			</tr>
		</table>
		<% } %>
	</body>
</html>