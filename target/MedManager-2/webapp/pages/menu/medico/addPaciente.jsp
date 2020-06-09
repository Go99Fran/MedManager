<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
<script src="../../../resources/js/addPaciente.js"></script>

</head>

<style>
.form-carta{
	height:480px;
}
</style>

<body class="teal lighten-5">

<jsp:include page="../../commons/head_comm.jsp"/>	
<jsp:include page="../../commons/navbar_back.jsp"/>

<br><br><br>

	<form id="FormAlta" method="POST" action="/MedManager/Resources/Actions/MenuAction.do">
		<input type="hidden" id="ACCION" name="ACCION" value="ALTA_PACIENTE_1">
		<input type="hidden" id="CrearTratamiento" name="CrearTratamiento" value="No">
	
	<div class="row">
		<div class="col s1"></div>
		<div class="col s12 l6">
			<div class="z-depth-4 card-panel form-carta">
				<h4>Datos del Paciente</h4>
				<div class="row">
					<div class="col s12">
						<label for="nombre">Nombre</label>
						<input onkeyup="movimientoNombre()" autocomplete="off"  type="text" name="nombre" id="nombre" class="validate" required>
					</div>
					<div class="col s12">
						<label>Apellido</label>
						<input onkeyup="movimientoNombre()" autocomplete="off" type="text" name="apellido" id="apellido" class="validate" required> 
					</div>
					<div class="col s12">
						<label>Correo Electrónico</label>
						<input onkeyup="movimientoMail()" autocomplete="off" type="text" name="mail" id="mail" class="validate" required>
					</div>
					<div class="col s12 m6">
					<label for="fecha">Fecha de Nacimiento</label>
						<input onkeyup="movimientoFecha()" autocomplete="off" id="fecha" name="fecha" type="date" class="validate" required>
					</div>
					<div class="col s12">
					<br>
					<%
						if(request.getSession().getAttribute("errorMail").equals("Si")){
					%>
						<a style="font-weight:bold; color:red">El mail ya existe!</a>
					<%
						}
					%>
					</div>
					<div class="row">
						<div class="col s4 m8">
							<a onclick="validar(1)" class="green right btn-small">Guardar Paciente</a>
						</div>
						<div class="col s8 m4">
							<a onclick="validar(2)" class="green right btn-small">Agregar Tratamiento</a>
						</div>
					</div>
				</div>
			</div>	
		</div>
		
		<br><br><br>
		
		<div class="col s12 l4">
				<br>
				<div class="row">
					<div class="col s3 center">
						<img src="../../../resources/img/person.png">
					</div>
					<div class="col s8 left">
						<table>
							<tr>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<td>
									<a><i class="grey-text material-icons">arrow_forward</i></a>
								</td>
								<td>
									<a style="color:black; font-weight:bold; font-size: 110%" id="fig_name"></a>
								</td>
							</tr>
							<tr>
								<td>
									<a><i class="grey-text material-icons">arrow_forward</i></a>
								</td>
								<td>
									<a style="color:black; font-weight:bold; font-size: 110%" id="fig_mail"></a>
								</td>
							</tr>
							<tr>
								<td>
									<a><i class="grey-text material-icons">arrow_forward</i></a>
								</td>
								<td>
									<a style="color:black; font-weight:bold; font-size: 110%" id="fig_fecha"></a>
								</td>
							</tr>
						</table>
					</div>
				</div>
		</div>
		<div class="col s1"></div>
	</div>
	
	</form>
	
	<div id="modalFaltanCampos" class="modal">
		<div class="modal-content">
			<h4>Por favor completar todos los campos!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="indigo lighten-4 modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>	
	
	
	<div id="modalErrorMail" class="modal">
		<div class="modal-content">
			<h4>El Formato del mail no es el correcto</h4>
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="indigo lighten-4 modal-close waves-effect btn-flat">Ok!</a>
		</div>
	</div>	
	
		
	<div id="modalFechaMayor" class="modal">
		<div class="modal-content">
			<h4>La fecha de nacimiento no puede ser mayor a la fecha de hoy!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="indigo lighten-4 modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>	
	
	<div id="modalFechaMenor" class="modal">
		<div class="modal-content">
			<h4>La fecha de nacimiento no puede ser menor al año 1900!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="indigo lighten-4 modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>	
	
	<br>
	<br>
	
<jsp:include page="../../commons/footer.jsp"/>
<jsp:include page="../../commons/libraries.jsp"/>

</body>

<script>
function movimientoNombre(){
	document.getElementById("fig_name").innerHTML = document.getElementById("nombre").value + " " + document.getElementById("apellido").value;
}
function movimientoMail(){
	document.getElementById("fig_mail").innerHTML = document.getElementById("mail").value;
}
function movimientoFecha(){
	document.getElementById("fig_fecha").innerHTML = document.getElementById("fecha").value;
}
</script>



</html>