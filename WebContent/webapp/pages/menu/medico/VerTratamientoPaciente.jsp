<%@page import="com.medmanager.util.CalendarUtil"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.medmanager.model.Usuario"%>
<%@page import="com.medmanager.model.TratamientoPaciente"%>
<%@page import="java.util.List"%>
<%@page import="com.medmanager.model.Paso"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<script src="../../../resources/js/verTratamientoPaciente.js"></script>

<body class="teal lighten-5">

	<jsp:include page="../../commons/navbar_back.jsp" />
	<jsp:include page="../../commons/head_comm.jsp" />

	<form action="/MedManager/Resources/Actions/MenuAction.do" name="Form"
		method="POST" id="Form">
		<input type="hidden" id="ACCION" name="ACCION" value="">
	</form>

	<%
		List<Usuario> usuarios = ((List<Usuario>) request.getSession().getAttribute("Pacientes"));
		List<TratamientoPaciente> tratamientos = ((List<TratamientoPaciente>) request.getSession()
				.getAttribute("Tratamientos"));

		int id = Integer.parseInt(((String) request.getSession().getAttribute("IdTratamiento")));

		Usuario paciente = null;
		int y = 0;

		while (tratamientos.size() > y && tratamientos.get(y).getTratamientoPaciente_id() != id)
			y++;

		int x = 0;
		while (usuarios.size() > x && usuarios.get(x).getUsuario_id() != tratamientos.get(y).getPaciente_id())
			x++;

		paciente = ((List<Usuario>) request.getSession().getAttribute("Pacientes")).get(x);
		TratamientoPaciente tratamiento = tratamientos.get(y);

		List<Paso> pasos = (List<Paso>) request.getSession().getAttribute("PasosPaciente");

		int pasosTerminados = 0;
		y = 0;
		while (y < pasos.size()) {
			if (pasos.get(y).getEstado_cod() != 1)
				pasosTerminados++;
			y++;
		}
	%>
	<br>
	<br>

	<form method="POST" id="Form"
		action="/MedManager/Resources/Actions/LogAction.do">
		<input type="hidden" id="ACCION" name="ACCION" value="">
	</form>

	<div class="fixed-action-btn">
		<a class="btn-floating btn-large blue"> <i
			class="large material-icons">mode_edit</i>
		</a>
		<ul>
			<li><a onclick="ModalInactivar()"
				class="tooltipped btn-floating red" data-position="left"
				data-tooltip="Inactivar Tratamiento"><i class="material-icons">delete</i></a></li>
			<li><a id="filtro" class="tooltipped btn-floating teal"
				onclick="CambiarFiltro()" data-position="left"
				data-tooltip="Filtro: <%=((String) request.getSession().getAttribute("FiltroPasos"))%>"><i
					class="material-icons">cached</i></a></li>
		</ul>
	</div>

	<div class="row">
		<div class="col s12">
			<div class="col s12 center">
				<a style="font-size: 170%; color: black;" class="center"><%=paciente.getNombre() + " " + paciente.getApellido() + " - "
					+ request.getSession().getAttribute("Riesgo")%></a>
			</div>
			<div class="col s12">
				<br>
				<ul style="font-weight: bold; background-color: white"
					class="white tabs">
					<li class="tab col s6"><a href="#vistaPasos">Pasos</a></li>
					<li class="tab col s6"><a href="#vistaDatos">Información</a></li>
				</ul>
				<br>
			</div>
		</div>

		<div id="vistaPasos" class="col s12">
			<div class="col s12">
				<a id="filtro" class="btn-floating teal" onclick="CambiarFiltro()"><i
					class="material-icons">cached</i></a> <a
					style="color: black; padding: 6px; font-weight: bold">Filtro: <%=((String) request.getSession().getAttribute("FiltroPasos"))%></a>
				<a class="right" style="color: grey; font-size: 140%"><%="Pasos Realizados: " + pasosTerminados + "/" + pasos.size()%></a>
			</div>
			<div class="col s12">
				<%
					String fechaSugerida = "";
					boolean filtro = !((String) request.getSession().getAttribute("FiltroPasos"))
							.equalsIgnoreCase("Solo Pendientes");

					for (int i = 0; i < pasos.size(); i++) {
						if (pasos.get(i).getFecha_accion_sugerida() != null)
							fechaSugerida = pasos.get(i).getFecha_accion_sugerida().get(Calendar.DAY_OF_MONTH) + "/"
									+ (pasos.get(i).getFecha_accion_sugerida().get(Calendar.MONTH) + 1) + "/"
									+ pasos.get(i).getFecha_accion_sugerida().get(Calendar.YEAR);
						else
							fechaSugerida = "Indefinida";
						if (pasos.get(i).getEstado_cod() == 1) {
				%>
				<div class="z-depth-4 card-panel">
					<a style="font-weight: bold; color: black; font-size: 130%;"><%=pasos.get(i).getNombre()%></a><br />
					<a style="color: black; font-size: 110%"><%=pasos.get(i).getDescripcion()%></a>

					<br> <br> <a style="font-weight: normal; color: black">Fecha
						Sugerida: </a> <a
						style="font-weight: bold; color: black; font-size: 120%"> <%=" " + fechaSugerida%>
					</a>
				</div>
				<%
					} else if (pasos.get(i).getEstado_cod() == 2) {
							if (filtro || CalendarUtil.sacarHora(pasos.get(i).getFecha_accion())
									.equals(CalendarUtil.sacarHora(Calendar.getInstance()))) {
				%>
				<div class="green lighten-2 z-depth-4 card-panel">
					<a style="font-weight: bold; color: black; font-size: 130%;"><%=pasos.get(i).getNombre()%></a><br />
					<a style="color: black; font-size: 110%"><%=pasos.get(i).getDescripcion()%></a>

					<br> <br> <a style="font-weight: normal; color: black">Fecha
						Sugerida: </a> <a
						style="font-weight: bold; color: black; font-size: 120%"> <%=fechaSugerida%>
					</a><br> <a style="font-weight: normal; color: black">Realizado
						el: </a> <a style="font-weight: bold; color: black; font-size: 120%">
						<%=" " + pasos.get(i).getFecha_accion().get(Calendar.DAY_OF_MONTH) + "/"
								+ (pasos.get(i).getFecha_accion().get(Calendar.MONTH) + 1) + "/"
								+ pasos.get(i).getFecha_accion().get(Calendar.YEAR)%>
					</a>
				</div>
				<%
					}
						} else {
							if (filtro || CalendarUtil.sacarHora(pasos.get(i).getFecha_accion())
									.equals(CalendarUtil.sacarHora(Calendar.getInstance()))) {
				%>
				<div class="red lighten-2 z-depth-4 card-panel">
					<a style="font-weight: bold; color: black; font-size: 130%;"><%=pasos.get(i).getNombre()%></a><br />
					<a style="color: black; font-size: 110%"><%=pasos.get(i).getDescripcion()%></a>

					<br> <br> <a style="font-weight: normal; color: black">Fecha
						Sugerida: </a> <a
						style="font-weight: bold; color: black; font-size: 120%"> <%=fechaSugerida%>
					</a><br> <a style="font-weight: normal; color: black">Anulado
						el: </a> <a style="font-weight: bold; color: black; font-size: 120%">
						<%=" " + pasos.get(i).getFecha_accion().get(Calendar.DAY_OF_MONTH) + "/"
								+ (pasos.get(i).getFecha_accion().get(Calendar.MONTH) + 1) + "/"
								+ pasos.get(i).getFecha_accion().get(Calendar.YEAR)%>
					</a>
				</div>
				<%
					}
						}
				%>


				<%
					}
				%>
			</div>

		</div>

		<div id="vistaDatos" class="col s12">
			<div class="card-panel">
				<div class="row">
					<h4 class="center">
						Datos del Tratamiento <i class="material-icons">arrow_forward</i><a
							style="font-size: 85%"> Clasificación: <%=tratamiento.getClasificacionTratamiento()%></a>
					</h4>
					<br>
					<div class="col s12 center">
						<table style="color: black; font-size: 130%" class="center">
							<tr>
								<td><a style="color:black; font-weight: bold">Fecha
										Cero: </a></td>
								<%
									if (tratamiento.getFechaCero() != null) {
								%>
								<td><a style="color:black"><%=CalendarUtil.ParseCalendar(tratamiento.getFechaCero())%></a>
								</td>
								<%
									} else {
								%>
								<td><a style="color:black">Indefinida</a>
								</td>
								<%
									}
								%>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Tumor: </a>
								</td>
								<td><a style="color:black"><%=tratamiento.getTipo_tumor_cod()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Cantidad:
								</a></td>
								<td><a style="color:black"><%=tratamiento.getCant_tumores()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color:black; font-weight: bold">Grado: </a>
								</td>
								<td><a style="color:black"><%=tratamiento.getGrado_tumor()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Carcinoma
										In Situ: </a></td>
								<td><a style="color:black"><%=tratamiento.getCis()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Compromiso
										Uretral: </a></td>
								<td><a style="color:black"><%=tratamiento.getCompromiso_uretral()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Inicio:
								</a></td>
								<td><a style="color:black"><%=tratamiento.getInicio_tumor()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Tamaño:
								</a></td>
								<td><a style="color:black"><%=tratamiento.getTamañoTumoral_cod()%></a>
								</td>
							</tr>
							<tr>
								<td><a style="color: black; font-weight: bold">Fecha
										Cirugía RTU-V: </a></td>
								<td><a style="color:black"><%=CalendarUtil.ParseCalendar(tratamiento.getFechaCirugiaRTUV())%></a><br>
								</td>
							</tr>
						</table>
						<br>
						<label for="textarea2">Observaciones</label>
						<textarea style="height:140px; resize: none" disabled id="textarea2"><%=tratamiento.getTitulo()%></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="modal" class="modal">
		<div class="modal-content">
			<h4>¿Desea inactivar el Tratamiento?</h4>
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">Cancelar</a> <a
				href="#!" onclick="Inactivar()"
				class="modal-close waves-effect btn-flat">Inactivar</a>
		</div>
	</div>

	<jsp:include page="../../commons/libraries.jsp" />
	<jsp:include page="../../commons/footer.jsp" />
</body>
</html>