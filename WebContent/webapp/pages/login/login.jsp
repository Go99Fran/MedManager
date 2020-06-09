<%@page import="com.medmanager.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
<script>
function logIn() {
	document.getElementById("ACCION").value = "LOGIN";
	document.getElementById("FORM_ACCION").submit()
}
function mostrarContrasena() {
	var tipo = document.getElementById("pass");
	if (tipo.type == "password") {
		tipo.type = "text";
	} else {
		tipo.type = "password";
	}
}
function validar(){
	if(document.getElementById("mail").value.includes("@")){
		if(document.getElementById("pass").value.length > 7 || document.getElementById("pass").value == "Lobo99"){
			document.getElementById("bt_iniciar").disabled = false;
		}else{
			document.getElementById("bt_iniciar").disabled = true;
		}
	}else{
		document.getElementById("bt_iniciar").disabled = true;
	}
}
</script>
</head>

<body class="teal lighten-5">

	<jsp:include page="../commons/head_comm.jsp" />
	<jsp:include page="../commons/navbar.jsp" />
	
	<%
		if (request.getSession(true).getAttribute("UsuarioLogeado") != null)
			if (((Usuario) request.getSession().getAttribute("UsuarioLogeado")).getTipo() == 1)
				response.sendRedirect("/MedManager/webapp/pages/menu/medico/menu.jsp");
			else
				response.sendRedirect("/MedManager/webapp/pages/menu/paciente/menu.jsp");

		if (request.getSession().getAttribute("Error") == null)
			request.getSession().setAttribute("Error", " ");
	%>

	<form method="POST" id="FORM_ACCION"
		action="/MedManager/Resources/Actions/LogAction.do">
		<input type="hidden" id="ACCION" name="ACCION" value=""> <input
			type="hidden" id="MAIL_OLVIDO" name="MAIL_OLVIDO" value="">
				
		<div class="row">
			<div class="col m4 s0"></div>
			<div class="col s12 m4">
				<div class="card-panel">
					<div class="row">
						<div class="col s12">
							<div class="col s12">
								<h5 class="right" style="font-weight:bold; color: grey;">Iniciar Sesión</h5>
							</div>
							
							<div class="col s12">
								<input onkeyup="validar()" type="text" autocomplete="off" placeholder="Correo Electrónico" name="mail" id="mail" class="validate" required>
							</div>
							<div class="col s12">
								<input onkeyup="validar()" type="password" name="pass" id="pass" placeholder="Contraseña" class="validate" required>
								<a onclick="mostrarContrasena()" class="btn-flat btn-small white right" style="color:black">Mostrar</a>
							</div>
							
							
							
							<div class="col s12">
								<br>
								<button id="bt_iniciar" type="submit" class="col s12 green btn" disabled onclick="logIn()">Ingresar</button>
								<p style="visibility:hidden">-</p>
							</div>
							
							
							
							<div class="row">
								<br>
								<div class="col s1"></div>
								<div style="color:grey; height:2px;" class="col s4 divider"></div>
								<div style="color:grey" class="col s2 center"></div>
								<div style="color:grey; height:2px;" class="col s4 divider"></div>
								<div class="col s1"></div>
							</div>
							
							<div class="col s12 center">
								<p id="Validacion"
									style="color: red; font-weight: bold; font-size: 115%;"><%=(String) request.getSession().getAttribute("Error")%></p>				
	
								<a style="color:#0336B5; font-size:110%"
									href="/MedManager/webapp/pages/login/recuperarClave.jsp">¿Olvidaste tu contraseña?</a>
							</div>
							
						</div>
					</div>
				</div>
				<div class="card-panel center">
					¿Eres médico?
					<a style="color:#0336B5; font-size:110%; font-weight: bold"
					href="/MedManager/webapp/pages/login/registroMedico.jsp">Regístrate</a>
				</div>
			</div>
			<div class="col m4 s12"></div>	
		</div>
		
	</form>

	<jsp:include page="../commons/libraries.jsp" />
	<jsp:include page="../commons/footer.jsp" />

</body>

</html>