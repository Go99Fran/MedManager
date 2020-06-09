<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>


<script>
function CambiarRiesgo(){
	document.getElementById("riesgoFinal").value = document.getElementById("riesgo").options[document
		.getElementById("riesgo").selectedIndex].text;
}
</script>

<body class="teal lighten-5">

<jsp:include page="../../commons/head_comm.jsp"/>	
<jsp:include page="../../commons/navbar.jsp"/>
	
<form id="FormAlta" method="POST" action="/MedManager/Resources/Actions/MenuAction.do">
	
<input type="hidden" id="ACCION" name="ACCION" value="GUARDAR_TRATAMIENTO">
<input type="hidden" id="riesgoFinal" name="riesgoFinal" value="<%= ((String)request.getSession().getAttribute("riesgo")) %>">

<div class="section container">
	<div class="card-panel">
		<h5 style="color:grey">El riesgo calculado es: <a style="font-weight: bold; color: black; font-size: 145%">${riesgo }</a></h5>
		<br><br>
		
		<%
			String riesgo = ((String)request.getSession().getAttribute("riesgo"));
			if(riesgo.equalsIgnoreCase("Riesgo Intermedio")){
		%>	
			<a style="font-size:120%;font-weight:bold; color:bold">Por favor elegir el tipo de tratamiento a realizar:</a>
			<div class="col s12 m6 input-field">
				<select onchange="CambiarRiesgo()" id="riesgo" required>
					<option value="1">BCG Intra Vesical</option>
					<option value="2">Quimioterapia Intra Vesical</option>
				</select>
				<br>
			</div>	
		<%
			}
		%>
		
		<div class="row">
			<div class="col s6">
				<a href="/MedManager/webapp/pages/menu/medico/menu.jsp" class="red waves-effect waves-light left btn">Cancelar Tratamiento</a>			
			</div>	
			<div class="col s6">
				<%
					if(riesgo.equals("Riesgo Intermedio") || riesgo.equals("Alto Riesgo") || riesgo.equals("Bajo Riesgo")){
				%>
					<button type="submit" class="green waves-effect waves-light right btn">Guardar Tratamiento</button>
				<%
					}
				%>	
			</div>		
		</div>
	</div>
</div>

</form>

<jsp:include page="../../commons/libraries.jsp"/>
<jsp:include page="../../commons/footer.jsp"/>

</body>
</html>