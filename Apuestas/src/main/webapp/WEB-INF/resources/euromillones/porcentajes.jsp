<%@page import="java.util.List"%>
<% 
	List<Byte> numeros = (List<Byte>) request.getAttribute("numeros");
	List<Byte> estrellas = (List<Byte>) request.getAttribute("estrellas");
%>
<html>
	<body>
		<h1>Porcentajes Euromillones</h1>
		<table border="1" width="40%" height="40%">
			<tr>
				<th colspan="10"><h3>Números</h3></th>
			</tr>
			<% for (int i=0;i<10;i++) { %>
			<tr align="center">
			<%
					for (int j=0;j<5;j++) {
						int posicion = (j * 10) + i;
			%>
					
			
				<td><b><%= (posicion + 1) %></b></td>
				<td><%= numeros.get(posicion) %>%</td>
			
			
			<% 		} %>
			</tr>
			<%	} %>
		</table>
		<br />
		<br />
		<table border="1" width="20%" height="20%">
			<tr>
				<th colspan="10"><h3>Estrellas</h3></th>
			</tr>
			<% for (int i=0;i<12;i++) { %>
			<tr align="center">
				<td><b><%= (i + 1) %></b></td>
				<td><%= estrellas.get(i) %>%</td>
			</tr>
			<%	} %>
		</table>
	</body>
</html>