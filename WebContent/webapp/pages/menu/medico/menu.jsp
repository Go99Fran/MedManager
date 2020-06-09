<%@page import="java.util.Formatter"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.medmanager.util.TratamientoClasificacion"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.Period"%>
<%@page import="com.medmanager.model.Usuario"%>
<%@page import="com.medmanager.model.TratamientoPaciente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Med Manager</title>
<script src="../../../resources/js/menu_med.js"></script>
<link href="../../../resources/css/menu_med.css" rel="stylesheet" type="text/css">
</head>

<body class="teal lighten-5">

<jsp:include page="../../commons/head_comm.jsp"/>	

<%
	List<TratamientoPaciente> tratamientos = ((List<TratamientoPaciente>) request.getSession().getAttribute("Tratamientos"));
	List<Usuario> pacientes = ((List<Usuario>) request.getSession().getAttribute("Pacientes"));
%>

	<header>
		<a class="modal-trigger" id="modaaal" href="#modal1"></a>
		<nav style="background-image:url('../../../resources/img/fondo2.png');" class="z-depth-3 teal darken-3">
			
			<div class="nav-wrapper">
				
				<ul class="right">
			        <li><a class="tooltipped" data-position="left" data-tooltip="Cerrar Sesión" onclick="logOut()" href="#"><i class="material-icons">exit_to_app</i></a></li>
				</ul>
				<a style="font-size: 200%;" class="right" href="#">Med Manager</a>
				<ul>					
				</ul>
				<ul id="nav-mobile">
					<li><a href="#" style="width: 14%"
						id="menuB" data-target="slide-out" class="sidenav-trigger"><i
							class="material-icons">menu</i></a></li>
				</ul>
			</div>
		</nav>
		<br>
	</header>

	<main>

		<div class="row">
			<div class="col s12">
				<a style="font-size:180%; padding-top:10px; text-align:left; font-weight: bold; color:grey">Mis Pacientes</a>
			</div>
		</div>
		<form method="POST"
			action="/MedManager/Resources/Actions/MenuAction.do" id="Form"
			name="Pacientes">

			<!-- ACCION -->
			<input type="hidden" id="ACCION" name="ACCION" value="">

			<!-- INPUT BUSCAR PACIENTE -->
			<input type="hidden" id="Busqueda" value="BUSCAR_PACIENTE"> <input
				type="hidden" id="buscar" name="buscar" value="">

			<!-- INPUT VER TRATAMIENTO -->
			<input type="hidden" id="IdTratamiento" name="IdTratamiento" value="">

			<!-- INPUT BAJA PACIENTE -->
			<input type="hidden" id="IdPaciente" name="IdPaciente" value="">
			<input type="hidden" id="BajaPacienteID" name="BajaPacienteID" value="">
			
			<div id="Tratamientos">				
				<div class="divider"></div><br>
				<div class="row">
					<div class="col s1"></div>
					<div class="col m4 s7">
						<input class="right" type="search" autocomplete="off"
							id="busquedaPacienteInput" name="busquedaPacienteInput"
							placeholder="Paciente">
					</div>
					<div style="padding-top: 10px;" class="col s4">
						<button type="button" onclick="BuscarPaciente(0)"
							class="btn-floating">
							<i class="material-icons">search</i>
						</button>
						<a  onclick="<% request.getSession().setAttribute("errorMail", ""); %>" class="tooltipped green btn-floating" data-position="bottom" data-tooltip="Agregar Paciente" href="/MedManager/webapp/pages/menu/medico/addPaciente.jsp"><i class="material-icons">add</i></a>
					</div>
					<div class="col s4"></div>
					<div class="col s12">
						<%
							if (request.getSession().getAttribute("BusquedaPacienteMenuMed") != null
									&& !request.getSession().getAttribute("BusquedaPacienteMenuMed").equals("")) {
								System.out.println("BUSQUEDA: " + request.getSession().getAttribute("BusquedaPacienteMenuMed"));
						%>
						<div class="row">
							<div class="col s1"></div>
								<div class="col s8"><div class="chip">
									<%=request.getSession().getAttribute("BusquedaPacienteMenuMed")%>
									<i onclick="BuscarPaciente(1)" class="close material-icons">close</i>
								</div>
							</div>
							<div class="col s3"></div>
						</div>
						
						<%
							}
						%>						
					</div>
					<br><br><br><br>
					
					<%
						int years = 0;
						int months = 0;
						int days = 0;
						int x = 0;
						
						String busqueda = "";
						if (request.getSession().getAttribute("BusquedaPacienteMenuMed") != null)
							busqueda = (String) request.getSession().getAttribute("BusquedaPacienteMenuMed");
						
						if(pacientes.size() != 0){
					%>
						<div class="section container">
							<ul class="z-depth-4 collapsible popout" data-collapsible="accordion">
					<%
							for (int i = 0; i < pacientes.size(); i++) {

								if ((pacientes.get(i).getNombre() + " " + pacientes.get(i).getApellido()).toLowerCase()
										.contains(busqueda.toLowerCase())) {
	
									Calendar fechaActual = Calendar.getInstance();
	
									years = fechaActual.get(Calendar.YEAR) - pacientes.get(i).getFechaNac().get(Calendar.YEAR);
									months = fechaActual.get(Calendar.MONTH) - pacientes.get(i).getFechaNac().get(Calendar.MONTH);
									days = fechaActual.get(Calendar.DAY_OF_MONTH)
											- pacientes.get(i).getFechaNac().get(Calendar.DAY_OF_MONTH);
	
									if (months < 0 || (months == 0 && days < 0)) {
										years--;
									}
									Formatter fmt = new Formatter();
					%>

						<li style="font-size: 110%;" class="white">
							<div style="font-weight: bold; font-size: 100%"
								class="collapsible-header">
								<i class="material-icons">person</i><%= pacientes.get(i).getNombre() + " " + pacientes.get(i).getApellido() + " - ID: " + fmt.format("%05d", pacientes.get(i).getUsuario_id()) %></div>
							
							<div class="collapsible-body">
									<a id="B<%=pacientes.get(i).getUsuario_id()%>"
								onclick="PutIDBajaPaciente(this.id)"
								class="tooltipped right red btn-small modal-trigger" data-position="right" data-tooltip="Borrar Paciente">
									<i class="tiny material-icons">delete</i>
								</a>
								<a id="E<%=pacientes.get(i).getUsuario_id()%>"
										onclick="PutIDEditPaciente(this.id)"
										class="tooltipped right yellow btn-small" data-position="left" data-tooltip="Editar Paciente">
								<i class="tiny material-icons">edit</i>
								</a>
								<a style="color: black"><i style="color: blue"
									class="tiny material-icons">chevron_right</i><%=pacientes.get(i).getMail()%></a>
								<br> <a style="color: black"><i style="color: blue"
									class="tiny material-icons">chevron_right</i><%=years + " Años ("
							+ String.valueOf(pacientes.get(i).getFechaNac().get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.valueOf(pacientes.get(i).getFechaNac().get(Calendar.MONTH) + 1) + "/"
							+ String.valueOf(pacientes.get(i).getFechaNac().get(Calendar.YEAR)) + ")"%></a>
									<%
										if(pacientes.get(i).getTratamiento().equalsIgnoreCase(" ")){
									%>
										<br>
										<br>
										<a id="BA<%=pacientes.get(i).getUsuario_id() %>" type="button" onclick="AltaTratamiento(this.id)"
											class="green btn">Agregar Tratamiento</a>
									<%
										}else{
											x= 0;
											while(x < tratamientos.size() && tratamientos.get(x).getPaciente_id() != pacientes.get(i).getUsuario_id())
												x++;
									%>
										<br>
										<p><%= pacientes.get(i).getTratamiento()%> <br> <%=tratamientos.get(x).getRiesgo()%></p>
										
									
									<%
										if(tratamientos.get(x).getTitulo() != null && !tratamientos.get(x).getTitulo().equalsIgnoreCase("")){
									%>
											<label for="textarea2">Observaciones</label>
										<textarea style="height:140px; resize: none" disabled id="textarea2"
									><%=tratamientos.get(x).getTitulo()%></textarea>
									<%
										}
									%>
									<br>
									<button id="V<%=tratamientos.get(x).getTratamientoPaciente_id()%>"
										onclick="VerPaciente(this.id)" class="orange btn">
										Ver Tratamiento<i class="right material-icons">arrow_forward</i>
									</button>
									<%
										}
									%>
							</div>
						</li>

					<%
							}
						}
					%>
						</ul>
						<br>				
					<%
					
						}else{
					%>
						<div class="section container">
							<div class="card-panel">
								<h4 style="font-size:160%" class="center">No existen pacientes actualmente</h4>
							</div>
						</div>
					<%
						}
					%>
					
					</div>
				</div>
			</div>	
						
		</form>
		<p style="visibility: hidden">-</p>
	</main>

<div id="modalBajaPaciente" class="modal">
	<div class="modal-content">
   		<h4>¿Seguro que desea borrar al Paciente?</h4>
   		<p style="visibility:hidden">-</p>
    </div>
    <div class="modal-footer">
    	<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
      	<a href="#!" onclick="BajaPaciente()"  class="modal-close waves-effect waves-green btn-flat">Aceptar</a>    	
    </div>
</div>

<div id="modalEditPaciente" class="modal">
	<div class="modal-content">
   		<h4>Aún no implementado</h4>
   		<p style="visibility:hidden">-</p>
    </div>
    <div class="modal-footer">
    	<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cancelar</a>
      	<a href="#!" class="modal-close waves-effect waves-green btn-flat">Aceptar</a>    	
    </div>
</div>

  	
<ul class="sidenav sidenav-fixed" id="slide-out">
<li><div class="user-view">
    	<div class="teal accent-4 background">
      		<img src="../../../resources/img/fondo2.jpg">
      	</div>
      <a class="white btn-large btn-floating"
			style="color: #00897b; font-size: 200%"><%=((Usuario) request.getSession().getAttribute("UsuarioLogeado")).getNombre().substring(0, 1)
			+ ((Usuario) request.getSession().getAttribute("UsuarioLogeado")).getApellido().substring(0, 1)%>
		</a>
      <a href="#name"><span style="font-size: 130%" class="white-text name"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getNombre() + " " + ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getApellido()%></span></a>
      <a href="#email"><span class="white-text email"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getMail() %></span></a>
    </div></li>
    <li><a href="#!" onclick="MostrarListadoPacientes()" class="sidenav-close">Mis Pacientes<i class="material-icons">contacts</i></a></li>
    <li><div class="divider"></div></li>
    <li><a href="#!" onclick="logOut()" class="sidenav-close">Cerrar Sesión</a></li>
</ul>

<jsp:include page="../../commons/footer.jsp"/>
<jsp:include page="../../commons/libraries.jsp"/>

</body>
</html>