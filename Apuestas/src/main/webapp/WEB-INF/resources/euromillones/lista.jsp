<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.manolete.apuestas.euromillones.Sorteo"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<% 
	String fechaBusqueda = request.getParameter("fechaBusqueda") == null ? "" : request.getParameter("fechaBusqueda");
	List<Sorteo> sorteos = (List<Sorteo>) request.getAttribute("sorteos");
	SimpleDateFormat formatter = new SimpleDateFormat("dd - MM - yyyy");
%>
<html>
	<body>
		<h1>Sorteos Euromillones</h1>
		<form method="post" action="listado">
			<input type="text" id="fechaBusqueda" name="fechaBusqueda" value="<%= fechaBusqueda %>" /> <%= request.getAttribute("error") == null ? "formato 'dd/mm/aaaa'" : (String) request.getAttribute("error") %>
			<input type="submit" value="Buscar" />
		</form>
		<form method="post" action="insertar">
			<table border="1" width="40%">
				<tr>
					<th>Fecha</th>
					<th colspan="5">Números</th>
					<th colspan="2">Estrellas</th>
				</tr>
				<tr align="center">
					<td><input type="text" id="fecha_sorteo" name="fecha_sorteo" value="dd/mm/aaaa" size="10" maxlength="10"/></td>
					<td><input type="text" id="num1" name="nums" size="2" maxlength="2" /></td>
					<td><input type="text" id="num2" name="nums" size="2" maxlength="2" /></td>
					<td><input type="text" id="num3" name="nums" size="2" maxlength="2" /></td>
					<td><input type="text" id="num4" name="nums" size="2" maxlength="2" /></td>
					<td><input type="text" id="num5" name="nums" size="2" maxlength="2" /></td>
					<td><input type="text" id="estrella1" name="stars" size="2" maxlength="2" /></td>
					<td><input type="text" id="estrella2" name="stars" size="2" maxlength="2" /></td>
					<td><input type="submit" value="Añadir" /></td>
				</tr>
			</table>
		</form>
		<table border="1" width="40%">
			<tr>
				<th>Fecha</th>
				<th colspan="5">Números</th>
				<th colspan="2">Estrellas</th>
			</tr>
		<% for (Sorteo sorteo : sorteos) { %>
			<tr align="center">
				<td><%= formatter.format(sorteo.getFecha_sorteo()) %></td>
				<td><%= sorteo.getNum1() %></td>
				<td><%= sorteo.getNum2() %></td>
				<td><%= sorteo.getNum3() %></td>
				<td><%= sorteo.getNum4() %></td>
				<td><%= sorteo.getNum5() %></td>
				<td><%= sorteo.getEstrella1() %></td>
				<td><%= sorteo.getEstrella2() %></td>
				<td><a href="borrar?fecha_sorteo=<%= formatter.format(sorteo.getFecha_sorteo()) %>">Eliminar</a></td>
			</tr>
		<% } %>
	
			
		</table>
	</body>
</html>