<%@ page import="com.medmanager.security.Cryptography"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<title>Med Manager</title>
</head>

<script src="../../resources/js/registrarMedico.js"></script>

<body class="teal lighten-5">
<jsp:include page="../commons/head_comm.jsp"/>

	<jsp:include page="../commons/navbar.jsp"/>
	
	<form id="Registrar" method="POST" action="/MedManager/Resources/Actions/LogAction.do">
	<input type="hidden" id="ACCION" name="ACCION" value="REGISTRO_MEDICO">
	
	<%
		String ErrorMail = "";
		if(request.getSession().getAttribute("errorMail") != null){
			if(request.getSession().getAttribute("errorMail").equals("Si"))
			ErrorMail = "El mail ya existe!";
		}
	%>
	
	<br>
		<div class="container">
			<div class="row">
				<div class="row">
					<div class="card-panel">
						<div class="row">
							<div class="col s12">
								<h4 style="padding-bottom: 5px; color: grey; font-weight: bold">Registro de Médico</h4>
							</div>
							<div class="col s12 l6">
								<label for="mail">Correo Electrónico</label>
								<input autocomplete="off" type="text" name="mail" id="mail"
										class="validate" required>
								<label  for="mail2">Repetir Correo Electrónico</label>
								<input onkeyup="comprobarMails()" autocomplete="off" type="text" name="mail2" id="mail2"
										class="validate" required>
										<label for="fecha">Nombre</label>
								<input autocomplete="off" type="text" id="nombre" name="nombre" class="validate" required>	
							</div>
							<div class="col s12 l6">
								<label for="fecha">Apellido</label>
								<input autocomplete="off" type="text" id="apellido" name="apellido" class="validate" required>
								<label for="password">Contraseña</label>
								<input onkeyup="comprobarClaves()" type="password" name="password" id="password"
										class="validate" required>
								<label for="password2">Repetir Contraseña</label>
								<input onkeyup="comprobarClaves()" type="password" name="password2" id="password2"
										class="validate" required> 
							
								<div style="padding-top: 35px" class="row">
									<div class="col s5 m8">
										<a class="red right btn" href="/MedManager/webapp/pages/login/login.jsp">Cancelar</a>
									</div>
									<div class="col s7 m4">
										<a onclick="Guardar()" class="green right btn">Registrarse</a>
									</div>
								</div>
							</div>
						</div>
			
						<p id="ContrasIguales" style="color: red; font-size: 20px;"></p>
						<p id="mailsIguales" style="color: red; font-size: 20px;"></p>
						<p id="ErrorMail" style="color: red; font-size: 20px;"><%=ErrorMail %></p>
					</div>
				</div>
			</div>
		</div>
	</form>
		<p style="visibility: hidden">-</p>
	
	<input type="hidden" id="ContrasIgualesInput" value="Las contraseñas no coincidenn">
	<input type="hidden" id="CantidadContrasInput" value="La contraseña debe tener al menos 8 caracteres ">
	
	
	<div id="modalFaltanCampos" class="modal">
		<div class="modal-content">
			<h4>Por favor completar todos los campos!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>
	
	<div id="modalErrorMail" class="modal">
		<div class="modal-content">
			<h4>El Formato del mail no es correcto!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>
	
	<div id="modalErrorMail2" class="modal">
		<div class="modal-content">
			<h4>Los mails no coinciden!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>
	
	<div id="modalContraseñas" class="modal">
		<div class="modal-content">
			<h4>Las contraseñas no coinciden!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>
	
	<div id="modalContraseñas2" class="modal">
		<div class="modal-content">
			<h4>La contraseña debe tener al menos 8 digitos!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>
	
	
<jsp:include page="../commons/libraries.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
	
</body>

<script>
function Guardar() {
	clave1 = document.getElementById("password").value
	clave2 = document.getElementById("password2").value
	
	
	var mail = document.getElementById("mail").value;
	var maild = document.getElementById("mail2").value;

	if(mail.includes("@")){
		if(mail == maild){
			if (clave1 == clave2) {
				if (clave1.length > 7) {
					if (document.getElementById("mail").value != ""
							|| document.getElementById("nombre").value != ""
							|| document.getElementById("fecha").value != ""
							|| document.getElementById("apellido").value != "") {
						document.getElementById("Registrar").submit();
					} else {
						const elem = document.getElementById("modalFaltanCampos");
						const instance = M.Modal.init(elem, {
							dismissible : false
						});
						instance.open();
					}
				} else {
					const elem = document.getElementById("modalContraseñas2");
					const instance = M.Modal.init(elem, {
						dismissible : false
					});
					instance.open();
				}
			} else {
				const elem = document.getElementById("modalContraseñas");
				const instance = M.Modal.init(elem, {
					dismissible : false
				});
				instance.open();
			}
		}else{
			const elem = document.getElementById("modalErrorMail2");
			const instance = M.Modal.init(elem, {
				dismissible : false
			});
			instance.open();
		}
	}else{
		const elem = document.getElementById("modalErrorMail");
		const instance = M.Modal.init(elem, {
			dismissible : false
		});
		instance.open();
	}
}

function comprobarClaves() {
	var clave1 = document.getElementById("password").value
	var clave2 = document.getElementById("password2").value

	if (clave1 == clave2) {
		if (clave1.length != 0) {
			if (clave1.length > 7) {
				document.getElementById("ContrasIguales").innerHTML = "";
			} else {
				document.getElementById("ContrasIguales").value = document.getElementById("ContrasIgualesInput").value;
			}
		} else {
			document.getElementById("ContrasIguales").innerHTML = "";
		}
	} else {
		document.getElementById("ContrasIguales").innerHTML = document.getElementById("CantidadContrasInput").value;
	}
}
</script>

</html>