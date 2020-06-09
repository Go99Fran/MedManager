<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<title>Med Manager</title>
</head>

<body class="teal lighten-5">
<jsp:include page="../commons/head_comm.jsp" />
<jsp:include page="../commons/navbar.jsp"/>

	<div class="section container">
		<div class="section container">
			<div class="section container">
				<div class="row">
					<div class="card-panel">
						<div class="row">
							<div class="col s12">
								<h4 style="padding-bottom: 5px; color: grey; font-weight: bold">Recupero
									de Contraseña</h4>
							</div>
							<div class="input-field col s12">
								<i class="material-icons prefix">account_circle</i> <input
									type="text" autocomplete="off" name="mail3" placeholder=" "
									id="mail_olvido" class="validate" required><label
									for="mail">Correo Electrónico</label>
							</div>
							<div class="col s6 m8">
								<a class="green right btn"
									href="/MedManager/webapp/pages/login/login.jsp"
									onclick="VerLogin()">Cancelar</a>
							</div>
							<div class="col s6 m4">
								<a class="green right btn" href="#" onclick="OlvidoContra()">Recuperar</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="../commons/libraries.jsp" />
	<jsp:include page="../commons/footer.jsp" />
</body>

<script>
	function OlvidoContra() {
		if (document.getElementById("mail_olvido").value == "") {
			const elem = document.getElementById("modalVacio");
			const instance = M.Modal.init(elem, {
				dismissible : false
			});
			instance.open();
		} else {
			document.getElementById("ACCION").value = "OLVIDO_CONTRA";
			document.getElementById("MAIL_OLVIDO").value = document
					.getElementById("mail_olvido").value;
			document.getElementById("FORM_ACCION").submit()
		}
	}
</script>
</html>