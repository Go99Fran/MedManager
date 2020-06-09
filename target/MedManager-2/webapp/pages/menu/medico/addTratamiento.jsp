<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<script src="../../../resources/js/addTratamiento.js"></script>

<body onload="carga()" class="teal lighten-5">

<jsp:include page="../../commons/head_comm.jsp"/>	
<jsp:include page="../../commons/navbar_back.jsp"/>
<br>

<form id="FormAlta" method="POST" action="/MedManager/Resources/Actions/MenuAction.do">
 
<input type="hidden" id="ACCION" name="ACCION" value="ALTA_PACIENTE_2">
<input type="hidden" id="cantTumores" name="cantTumores" value="">
<input type="hidden" id="tamanoTumores" name="tamanoTumores" value=""> 
<input type="hidden" id="tumor" name="tumor" value=""> 
<input type="hidden" id="grado" name="grado" value=""> 
<input type="hidden" id="inicio" name="inicio" value="">
<input type="hidden" id="clasif" name="clasif" value=""> 
<input type="hidden" id="cis" name="cis" value="">
<input type="hidden" id="riesgo" name="riesgo" value="">
<input type="hidden" id="compromisoUretral" name="compromisoUretral" value="">
			
<div class="section container">
	<div class="z-depth-4 card-panel">
		<h4>Datos del Tratamiento</h4>
		<div class="row">
			<div class="col s12">
				<div class="input-field">
					<select onchange="checkClasif()" id="clasificacionS" required>
						<option value="" disabled selected> </option>
						<option value="1">EAU</option>
						<option value="2">AUA</option>
					</select>
					<label>Clasificación a utilizar</label>
				</div>
			</div>
			<div class="col s12 l6">
				<div class="col s12 input-field">
					<select onchange="checkTumor()" id="tumorS" required>
						<option value="" disabled selected> </option>
						<option value="1">PUNLMP</option>
						<option value="2">Pta</option>
						<option value="3">Pt1</option>
					</select>
					<label>Tumor</label>
				</div>
				<div class="col s12 input-field">	
					<select id="cantidadS" required>
						<option value="" disabled selected> </option>
						<option value="1">Unico</option>
						<option value="2">Múltiples</option>
					</select>
					<label>Cantidad de Tumores</label>
				</div>
				<div id="grado_div">
					<div class="col s12 input-field">
						<select id="gradoS" required>
							<option value="" disabled selected> </option>
							<option value="2">Bajo Grado</option>
							<option value="1">Alto Grado</option>
						</select>
						<label>Grado</label>
					</div>
				</div>
				<div id="grado_div_bajo">
					<div class="col s12 input-field">
						<select id="gradoS_bajo" required>
							<option value="1">Bajo Grado</option>
						</select>
						<label>Grado</label>
					</div>
				</div>
				
				<div class="col s12 input-field">
					<label><input type="checkbox" id="CIS"/> 
						<span style="color: black">Carcinoma in situ (CIS)</span>
					</label>
				</div>
				<div id="div_comp" class="col s12 input-field">
					<label>
						<input type="checkbox" id="compromiso"/><span id="span_compromiso" style="color: black">Compromiso Uretral de Alto Grado</span>
					</label>
				</div>
				<div class="col s12"><br><br></div>
			</div>
			<div class="col s12 l6">
				<div class="col s12 input-field">
					<select id="inicioS" required>
						<option value="" disabled selected> </option>
						<option value="1">Primera Vez</option>
						<option value="2">Recaída dentro del 1er año</option>
						<option value="2">Recaída después del 1er año</option>
					</select>
					<label>Inicio</label>
				</div>
				<div class="col s12 input-field">
					<select id="tamanoS" required>
						<option value="" disabled selected> </option>
						<option value="2">Menor a 3cm</option>		
						<option value="1">Mayor a 3cm</option>
					</select>
					<label>Tamaño de Tumor/es</label>
				</div>
					<div class="input-field">
						<div class="row">
							<div style="padding-top: 15px" class="col s5 l6">
								Cirugía RTU-V:
							</div>
							<div class="col s7 l6">
								<input placeholder="Fecha" autocomplete="off" id="fechaC" name="fechaC" type="date" class="validate" required>
							</div>
						</div>
					</div>
				<div class="input-field">
					<textarea autocomplete="off" maxlength="500"  placeholder="Observaciones" id="titulo" name="titulo" style="resize:none; height:100px"></textarea>
				</div>
			</div>			
			<div class="col s12"></div>
			<div class="col m8 s1"></div>
			<div class="col m4 s11">
				<div class="row">
					<div class="col s4">
						<a href="/MedManager/webapp/pages/menu/medico/menu.jsp" class="red waves-effect waves-light right btn-small">Cancelar</a>
					</div>
					<div class="col s8">
						<a onclick="AgregarDatosTratamiento()"
							class="green waves-effect waves-light right btn-small">Crear Tratamiento</a>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>								

	<div id="modalFaltanCampos" class="modal">
		<div class="modal-content">
			<h4>Por favor completar todos los campos!</h4>
			<p></p>
		</div>	
		<div class="modal-footer">
			<a href="#!" class="indigo lighten-4 modal-close waves-effect btn-flat">Ok</a>
		</div>
	</div>		

</form>

<jsp:include page="../../commons/libraries.jsp"/>
<jsp:include page="../../commons/footer.jsp"/>

</body>
</html>