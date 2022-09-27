<%@page import="com.digitalers.controllers.LoginController"%>
<%@page import="com.digitalers.commons.time.DateUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.digitalers.entities.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="static/css/table.css">
<link rel="stylesheet" href="static/css/button.css">
<link rel="stylesheet" href="static/css/main.css">
<title>ABM - Usuarios</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	%>

	<header>
		<h1>Proyecto de Usuarios</h1>
		<%
		if (user == null) {
			response.sendRedirect("index.jsp");
		} else {
		%>
		<h2><%=(user.getEmail() + " - " + session.getId())%></h2>

	</header>
	<nav>
		<a href="usuario.jsp">Agregar Usuario</a> <a style="float: right;"
			href="login?invalidate=true">Cerrar Sesion <%=user.getEmail().substring(0, user.getEmail().indexOf("@"))%>
		</a>
	</nav>
	<%
	}
	%>
	<section class="body">
		<table id="table-1">
			<thead>
				<tr>
					<th>Id</th>
					<th>Correo</th>
					<th>Fecha de Creacion</th>
					<th>Activo</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (User userAux : LoginController.users) {
				%>
				<tr>
					<td><%=userAux.getId()%></td>
					<td><%=userAux.getEmail()%></td>
					<td><%=DateUtils.getString(userAux.getCreationDate(), DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS_12H)%></td>
					<td><%=userAux.isActive() ? "Si" : "No"%></td>
					<td>
						<button onclick="window.location.href='users?id=<%=userAux.getId()%>&action=update'"  class="warning">Editar</button>
						<button onclick="window.confirm('Deseas Eliminar el usuario <%=userAux.getEmail()%>? ')?window.location.href='users?id=<%=userAux.getId()%>&action=delete':'#'"  class="danger">Eliminar</button>
					</td>
				</tr>

				<%
				}
				%>

			</tbody>
		</table>


	</section>

	<footer>
		<p>Todos los derechos reservados Educacion IT - Digitalers Telecom</p>
	</footer>
	
	
</body>
</html>