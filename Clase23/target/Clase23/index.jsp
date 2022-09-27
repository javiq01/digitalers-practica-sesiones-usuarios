<%@page import="com.digitalers.enums.MessageEnum"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="static/css/form.css">
<link rel="stylesheet" href="static/css/button.css">
<link rel="stylesheet" href="static/css/main.css">
<title>ABM - Usuarios</title>
</head>
<body>

	<header>
		<h1>Proyecto de Usuarios</h1>

	</header>
	<%
	MessageEnum messageEnum = (MessageEnum) request.getAttribute("messageEnum");

	if (messageEnum != null) {
	%>
	<h2><%=messageEnum.getMessage()%></h2>
	<%
	}
	%>
	<section>
		<form id="form-1" method="post" action="login">
			<label for="email">Correo:</label> <input type="email" id="email"
				name="email" required placeholder="tuusuario@dominio.ext"> <label
				for="key">Clave:</label> <input type="password" id="key" name="key"
				required placeholder="tu contraseña...">

			<div>
				<button class="success" type="submit">Enviar</button>
				<button class="warning" type="reset">Limpiar</button>
			</div>
		</form>
	</section>
</body>
</html>