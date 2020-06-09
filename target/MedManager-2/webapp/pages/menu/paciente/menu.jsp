	<%@ page import="java.util.Calendar"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="com.medmanager.util.CalendarUtil"%>
<%@ page import ="com.medmanager.model.Usuario"%>
<%@ page import ="com.medmanager.model.Paso"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Med Manager</title>
<link href="../../../resources/css/menu_pac.css" rel="stylesheet" type="text/css">
</head>

<script src="../../../resources/js/menu_pac.js"></script>

<body onload="carga()" class="teal lighten-5">
<%
	Usuario usuarioLog = ((Usuario)request.getSession().getAttribute("UsuarioLogeado"));
	List<Paso> pasos = (List<Paso>)request.getSession().getAttribute("Pasos");
	
	int pasosTerminados = 0;
	int y = 0;
	while(y < pasos.size()){
		if(pasos.get(y).getEstado_cod() != 1)
			pasosTerminados++;
		y++;
	}
%>
<jsp:include page="../../commons/head_comm.jsp"/>	

<!-- NAVBAR -->
	<header>
		<a class="modal-trigger" id="modaaal" href="#modal1"></a>
		<nav style="background-image:url('../../../resources/img/fondo2.png');" class="z-depth-3 teal darken-3">
			<div class="nav-wrapper">
				<ul class="right">
			        <li><a class="tooltipped" data-position="left" data-tooltip="Cerrar Sesión" onclick="logOut()" href="#"><i class="material-icons">exit_to_app</i></a></li>
				</ul>
				<a style="font-size: 200%;" href="#"
					class="right">Med Manager</a>
					
				<ul>
					<li>
						<a class="white btn-large btn-floating"
						style="color: #00897b; font-size: 200%"><%=usuarioLog.getNombre().substring(0, 1)
						+ usuarioLog.getApellido().substring(0, 1)%>
						</a>
					</li>
					<li class="hide-on-med-and-down">

						<a><span style="font-size: 110%"
						class="white-text name"><%="   " + usuarioLog.getNombre() + " "
						+ usuarioLog.getApellido()%></span></a>
					</li>
					
				</ul>
			</div>
		</nav >
		<br>
	</header>

<main>





  
<div style="padding-left: 15px">
	 <a href="#" data-target="menuSide" class="btn-flat sidenav-trigger"><i class="material-icons">menu</i></a>
</div>
<form action ="/MedManager/Resources/Actions/MenuPacAction.do" name="FormAC" method="POST" id="FormAC">
	<input type="hidden" id="Action" name="Action" value="">
	<input type="hidden" id="IdPaciente" name="IdPaciente" value="<%= usuarioLog.getUsuario_id()%>">
	<input type="hidden" id="PasoID" name="PasoID" value="">
	<input type="hidden" id="scrollPosition" name="scrollPosition" value = "<%= request.getSession().getAttribute("Scroll")%>">

  
<div id="Tratamientos" class="container"> 
  	
  	<div class="row">
	<div class="col s12">
		<a class="left" style="color:grey; font-size: 140%"><%= "Pasos Realizados: " + pasosTerminados + "/" + pasos.size() %></a>
		<a id="filtro" class="right btn-small btn-floating" onclick="CambiarFiltro()"><i class="material-icons">cached</i></a>
		<a class="right" style="padding:4px; color:black; font-weight: bold; font-size:120%"> <%= ((String)request.getSession().getAttribute("FiltroPasos")) %></a>
	</div>
</div>
	
	<%	
		try{
			boolean filtro = !((String)request.getSession().getAttribute("FiltroPasos")).equalsIgnoreCase("Solo Pendientes");
			String fechaSugerida = "";
			
			long fechaSig = 0;
			
			if(pasos.size() != 0){
				for(int i= 0; i< pasos.size();i++){
					
					if(pasos.get(i).getFecha_accion_sugerida() == null)
						fechaSugerida = "Indefinida";
					else
						fechaSugerida = pasos.get(i).getFecha_accion_sugerida().get(Calendar.DAY_OF_MONTH) + "/" + (pasos.get(i).getFecha_accion_sugerida().get(Calendar.MONTH) +1) + "/" + pasos.get(i).getFecha_accion_sugerida().get(Calendar.YEAR);

					if(pasos.get(i).getEstado_cod() == 1){
	%>					
						<div class="row">
							<div id="Paso <%=pasos.get(i).getIdPaso() %>" class="z-depth-4 row card-panel paso">
							<div class="col s8 m10">
								<a style="font-weight: bold;color:black; font-size: 130%;"><%= pasos.get(i).getNombre() %></a>
							</div>
				
	<%
						if(fechaSig == 0 || pasos.get(i).getFecha_accion_sugerida() != null && fechaSig == pasos.get(i).getFecha_accion_sugerida().getTimeInMillis()){
							System.out.println("Entraaca");
							if(pasos.get(i).getFecha_accion_sugerida() == null)
								fechaSig = 1;
							else
								fechaSig = pasos.get(i).getFecha_accion_sugerida().getTimeInMillis();
	%>
								<div class="col s4 m2">
									<a id="A <%=pasos.get(i).getIdPaso() %>" onclick="PasoCheck(this.id)" class="right btn-floating btn-small green s1"><i class="material-icons">check</i></a>
									<a id="B <%=pasos.get(i).getIdPaso() %>" onclick="PasoNulo(this.id)" class="right btn-floating btn-small red s1"><i class="material-icons">highlight_off</i></a>
								</div>							
								
	<%
	

						}
	%>							
								<div class="col s12">
									<a style="color:black; font-size: 110%"><%= pasos.get(i).getDescripcion() %></a>
								</div>
								<div class="col s12">
									<br>
									<a style="font-weight: normal; color:black">Fecha Sugerida: </a>
									<a style="font-weight: bold; color:black; font-size:120%">
										<%=" " + fechaSugerida %>
									</a>
								</div>
							</div>
						</div>
	<%	
					}else if(pasos.get(i).getEstado_cod() == 2){
						if(filtro || CalendarUtil.sacarHora(pasos.get(i).getFecha_accion()).equals(CalendarUtil.sacarHora(Calendar.getInstance()))){
	%>
						<div class="row">
							<div id="Paso <%=pasos.get(i).getIdPaso() %>" class="green lighten-2 z-depth-4 row card-panel paso">
								<div class="col s9 m11">
									<a style="font-weight: bold; color:black; font-size: 130%;"><%= pasos.get(i).getNombre() %></a><br/>
								</div>
								<div class="col s3 m1">
									<a id="R <%=pasos.get(i).getIdPaso() %>" onclick="Deshacer(this.id)" class="btn-floating btn-small yellow right"><i class="material-icons">undo</i></a>
								</div>
								<div class="col s12">
									<a style="color:black; font-size:110%"><%= pasos.get(i).getDescripcion() %></a>
								</div>
								
								
								
								<div class="col s12">
								<br>
									<a style="font-weight: normal; color:black">Fecha Sugerida: </a>
									<a style="font-weight: bold; color:black; font-size:120%">
										<%=" " + pasos.get(i).getFecha_accion_sugerida().get(Calendar.DAY_OF_MONTH) + "/" + (pasos.get(i).getFecha_accion_sugerida().get(Calendar.MONTH)+1) + "/" + pasos.get(i).getFecha_accion_sugerida().get(Calendar.YEAR) %>
									</a>
								</div>
								<div class="col s12">
									<a style="font-weight: normal; color:black">Realizado el: </a>
									<a style="font-weight: bold; color:black; font-size:120%">
										<%= pasos.get(i).getFecha_accion().get(Calendar.DAY_OF_MONTH) + "/" + (pasos.get(i).getFecha_accion().get(Calendar.MONTH) +1) + "/" + pasos.get(i).getFecha_accion().get(Calendar.YEAR)%>
									</a>
								</div>
							</div>
						</div>
	<%
						}
					}else if(pasos.get(i).getEstado_cod() == 3){
						if(filtro || CalendarUtil.sacarHora(pasos.get(i).getFecha_accion()).equals(CalendarUtil.sacarHora(Calendar.getInstance()))){
	%>
						<div class="row">
							<div id="Paso <%=pasos.get(i).getIdPaso() %>" class="red lighten-2 z-depth-4 row card-panel paso" >
								<div class="col s9 m11">
									<a style="font-weight: bold; color:black; font-size: 130%;"><%= pasos.get(i).getNombre() %></a><br/>
								</div>
								<div class="col s3 m1">
									<a id="R <%=pasos.get(i).getIdPaso() %>" onclick="Deshacer(this.id)" class="btn-floating btn-small yellow right"><i class="material-icons">undo</i></a>
								</div>
								<div class="col s12">
									<a style="color:black; font-size:110%"><%= pasos.get(i).getDescripcion() %></a>
								</div>
								
								
								<div class="col s12">
									<br>
									<a style="font-weight: normal; color:black">Fecha Sugerida: </a>
									<a style="font-weight: bold; color:black; font-size:120%">
										<%=" " + pasos.get(i).getFecha_accion_sugerida().get(Calendar.DAY_OF_MONTH) + "/" + (pasos.get(i).getFecha_accion_sugerida().get(Calendar.MONTH)+1) + "/" + pasos.get(i).getFecha_accion_sugerida().get(Calendar.YEAR) %>
									</a><br>
									
									<a style="font-weight: normal; color:black">Anulado el: </a>
									<a style="font-weight: bold; color:black; font-size:120%">
										<%= pasos.get(i).getFecha_accion().get(Calendar.DAY_OF_MONTH) + "/" + (pasos.get(i).getFecha_accion().get(Calendar.MONTH) +1) + "/" + pasos.get(i).getFecha_accion().get(Calendar.YEAR)%>
									</a>
								</div>
							</div>
						</div>
	<%
						}
					}
				}
			}else{
	%>
				<div class="row">
					<div id="NoHay" class="z-depth-4 row card-panel">
						<label style="font-size: 150%" class="col s12 center">No existe un tratamiento activo</label>
					</div>
				</div>
	<%
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	%>
</div>

<p style="visibility: hidden">-</p>
</form>
</main>		

<!-- ---------------------------------------------------------------------------------------------------------- -->

<ul class="sidenav" id="menuSide">
<li><div class="user-view">
      <div class="teal accent-4 background">
      	<img src="../../../resources/img/fondo2.jpg">
      </div>
      <a class="white btn-large btn-floating"
			style="color: #00897b; font-size: 200%"><%=((Usuario) request.getSession().getAttribute("UsuarioLogeado")).getNombre().substring(0, 1)
			+ ((Usuario) request.getSession().getAttribute("UsuarioLogeado")).getApellido().substring(0, 1)%>
		</a>
      <a href="#name" ><span style="font-size: 150%" class="white-text name"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getNombre() + " " + ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getApellido()%></span></a>
      <a href="#email"><span class="white-text email"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getMail() %></span></a>
    </div></li>
    <li><a href="#!" onclick="MostrarListadoPacientes()" class="sidenav-close">Mi Tratamiento<i class="material-icons">contacts</i></a></li>
    <li><div class="divider"></div></li>
    <li><a href="#!" onclick="logOut()" class="sidenav-close">Cerrar Sesión</a></li>
</ul>

<div id="modalPasoNulo" class="modal">
		<div class="modal-content">
			<h4>¿Confirma anular el paso?</h4>
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">No</a>
			<a href="#!"  onclick="GuardarPasoNulo()" class="modal-close waves-effect btn-flat">Sí</a>
		</div>
</div>
<div id="modalPasoCheck" class="modal">
		<div class="modal-content">
			<h4>¿Confirma la realización del paso?</h4>
			<p></p>
		</div>
		<div class="modal-footer">			
			<a href="#!" class="modal-close waves-effect btn-flat">No</a>
			<a href="#!" onclick="GuardarPasoCheck()" class="modal-close waves-effect btn-flat">Sí</a>
		</div>
</div>
<div id="modalDeshacerPaso" class="modal">
		<div class="modal-content">
			<h4>¿Confirma deshacer el paso?</h4>
			<p></p>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-close waves-effect btn-flat">No</a>
			<a href="#!" onclick="GuardarPasoDeshacer()" class="modal-close waves-effect btn-flat">Sí</a>
		</div>
</div>

<jsp:include page="../../commons/libraries.jsp"/>
<jsp:include page="../../commons/footer.jsp"/>
</body>


</html>