<%@ page import ="com.medmanager.model.Usuario"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Med Manager</title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

</head>

<script>
	function mostrarAltaPaciente() {
		document.getElementById("AltaPaciente").style.display = "block";
		document.getElementById("ListadoPacientes").style.display = "none";

		document.getElementById("mySidebar").style.display = "none";
		document.getElementById("myOverlay").style.display = "none";
	}
	function MostrarListadoPacientes() {
		document.getElementById("ListadoPacientes").style.display = "block";
		document.getElementById("AltaPaciente").style.display = "none";

		document.getElementById("mySidebar").style.display = "none";
		document.getElementById("myOverlay").style.display = "none";
	}
	
	function Carga() {
		document.getElementById("AltaPaciente").style.display = "none";
		document.getElementById("ListadoPacientes").style.display = "block";
	}
	function logOut(){
		document.getElementById("LogOUT").submit()
	}
</script>

<body onload="Carga()" class="teal lighten-5">

<jsp:include page="../../commons/head_comm.jsp"/>	

<%
	if(request.getSession().getAttribute("UsuarioLogeado") == null){
		response.sendRedirect("/pages/login/login.jsp");
	}else if(((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getTipo() != 2){
		response.sendRedirect("/Pages/login/login.jsp");
	}
%>

<form action ="/MedManager/Resources/Actions/LogAction.do" method="POST" id="LogOUT">
	<input type="hidden" id="ACCION" name="ACCION" value="LOGOUT">
</form>

	<nav class="z-depth-4 teal darken-3">
		<div class="nav-wrapper container">
			<a href="#" class="brand-logo right">Med Manager®</a>
		</div>
	</nav>
<div class="divider"></div>
<div class="container section">

	<a href="#" class="sidenav-trigger teal lighten-1 btn-floating pulse" data-target="menu-side">
		<i class ="small material-icons">dehaze</i>
	</a>
</div>	

<ul class="sidenav" id="menu-side">
<li><div class="user-view">
      <div class="teal accent-4 background"></div>
      
      <a href="#name"><span class="white-text name"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getNombre() + " " + ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getApellido()%></span></a>
      <a href="#email"><span class="white-text email"><%= ((Usuario)request.getSession().getAttribute("UsuarioLogeado")).getMail() %></span></a>
    </div></li>
    <li><a href="#!" onclick="MostrarListadoMedicos()" class="sidenav-close">Medicos<i class="material-icons">contacts</i></a></li>
    <li><a href="#!" onclick="MostrarListadoPacientes()" class="sidenav-close">Pacientes<i class="material-icons">contacts</i></a></li>
    <li><div class="divider"></div></li>
    <li><a href="#!" onclick="logOut()" >Cerrar Sesión</a></li>
</ul>

<div class="section container" id="ListadoPacientes">
	
	<%
	try{	
		List<String> pacientes = ((ArrayList<String>)request.getSession().getAttribute("Pacientes"));
		System.out.println(pacientes.toString());	
		for(int i = 0; i < pacientes.size(); i++){
	%>
		<div class="col s12 card-panel">
			<label class="col s9 m10"><%= pacientes.get(i) %></label>
		</div>
	<%
		}
	}catch(Exception e){}
	%>
	
</div>

<div class="section container" id="ListadoMedicos">
	
	<%
	try{	
		List<String> medicos = ((ArrayList<String>)request.getSession().getAttribute("Medicos"));
		System.out.println(medicos.toString());	
		for(int i = 0; i < medicos.size(); i++){
	%>
		<div class="col s12 card-panel">
			<label class="col s9 m10"><%= medicos.get(i) %></label>
		</div>
	<%
		}
	}catch(Exception e){}
	%>
	
</div>
	<div id="AltaPaciente" class="col 12 section container">
		<form id="FORM_ACCION" method="POST" action="/MedManager/Resources/Actions/LogAction.do">
		<input type="hidden" id="ACCION" name="ACCION" value="ALTAPACIENTE">
			<div class="section container">
				<div class="col s12 card-panel">
					<div class="input-field col m6 s12">
						<input type="text" name="nombre" id="nombre"
							class="validate" required> <label for="nombre">Nombre
							y Apellido:</label>
					</div>
					<div class="input-field col m6 s12">
						<input type="text" name="mail" id="mail"
							class="validate" required> <label for="mail">Correo
							Electronico:</label>
					</div>
					<div class="input-field col m6 s12">
						<input type="text" name="fecha" id="fecha"
							class="validate" required> <label for="fecha">Fecha
							de Nacimiento:</label>
					</div>
					<button class="btn" type="submit">Registrar</button>
				</div>
			</div>
		</form>
	</div>

<p style="visibility: hidden">-</p>


 <footer class="page-footer teal lighten-2">
          <div class="container">
            <div class="row">
              <div class="col l6 s12">
                <h5 class="white-text">Med Manager</h5>
                <p class="grey-text text-lighten-4">Web para el seguimiento de tratamientos contra el cancer.</p>
              </div>
              <div class="col l4 offset-l2 s12">
                <h5 class="white-text">Links</h5>
                <ul>
                  <li><a class="grey-text text-lighten-3" href="www.instagram.com/Gonzalez99Fran/">Instagram/Gonzalez99Fran</a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="footer-copyright">
            <div class="container">
            © 2020 Copyright Text
            </div>
          </div>
        </footer>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			var elems = document.querySelectorAll('.sidenav');
			var instances = M.Sidenav.init(elems);
		});
	</script>

</body>


</html>