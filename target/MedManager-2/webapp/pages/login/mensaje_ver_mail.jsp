<%@ page import="com.medmanager.security.Cryptography"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<body class="teal lighten-5">
<jsp:include page="../commons/head_comm.jsp"/>
<jsp:include page="../commons/navbar.jsp"/>
	
<br><br><br><br><br><br>
	
	<div class="section container">
		<div class="card-panel">
			<h4 style="font-weight: bold"class="center">Revisá tu Correo Electrónico!</h4>
			<a href="/MedManager/webapp/pages/login/login.jsp" class="btn-small green btn">Volver al Login</a>			
		</div>
	</div>
	
<br><br><br><br><br><br>
	
	
<jsp:include page="../commons/libraries.jsp"/>
<jsp:include page="../commons/footer.jsp"/>

</body>
</html>