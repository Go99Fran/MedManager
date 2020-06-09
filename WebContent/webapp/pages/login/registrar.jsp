<%@ page import="com.medmanager.security.Cryptography"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<script>
function comprobarClaves() {
	clave1 = document.getElementById("password").value
	clave2 = document.getElementById("password2").value

	if (clave1 == clave2) {
		if (clave1.length > 8) {
			document.getElementById("ContrasIguales").innerHTML = "";
		} else {
			document.getElementById("ContrasIguales").innerHTML = "La contraseña debe tener más de 8 digitos";
		}
	} else {
		document.getElementById("ContrasIguales").innerHTML = "Las contraseñas no coinciden";
	}
}
function Guardar() {
	clave1 = document.getElementById("password").value
	clave2 = document.getElementById("password2").value

	if (clave1 == clave2 && clave1.length > 8) {
		if (clave1.length > 8) {
			document.getElementById("ContrasIguales").innerHTML = "";
		} else {
			document.getElementById("ContrasIguales").innerHTML = "La contraseña debe tener más de 8 digitos";
		}

	} else {
		document.getElementById("ContrasIguales").innerHTML = "Las contraseñas no coinciden";
	}
}
</script>

<body class="teal lighten-5">
<jsp:include page="../commons/head_comm.jsp"/>

<jsp:include page="../commons/navbar.jsp"/>
<br><br>
	
	<form id="Registrar" method="POST" action="/MedManager/Resources/Actions/LogAction.do">
	<input type="hidden" id="ACCION" name="ACCION" value="CARGAR_CONTRAS">
	
	<input type="hidden" id="mailV" name="mailV" value="<%= request.getSession().getAttribute("mail_validar") %>">
		<br>
		<div class="section container">
			<div class="row">
				<div class="row">
					<div class="row card-panel">

					<% 
						try{	
					%>
						<div class="col s12">
							<p style="font-size: 20px;"><%= request.getSession().getAttribute("mail_validar") %></p>
						</div>
					<%
						}catch(Exception e){
							response.sendRedirect("/MedManager/webapp/pages/login/login.jsp");
						}
					%>
						<div class="input-field col s12 m6">
							<input type="password" name="password" id="password"
								class="validate" required> <label for="password">Contraseña:</label>
						</div>

						<div class="input-field col s12 m6">
							<input onkeyup="comprobarClaves()" type="password" name="password2" id="password2"
								class="validate" required> <label for="password2">Repetir
								Contraseña:</label>
						</div>
						
						<button class="green right btn" onclick="Guardar()" type="submit">Registrarse</button>
					<p id="ContrasIguales" style="color: red; font-size: 20px;"></p>
					</div>

					

				</div>
			</div>
		</div>
	</form>
		<p style="visibility: hidden">-</p>
	
	
<jsp:include page="../commons/libraries.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
	
</body>
</html>