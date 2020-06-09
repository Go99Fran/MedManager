<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<body class="teal lighten-5">
<jsp:include page="../commons/head_comm.jsp"/>

<header>
	<jsp:include page="../commons/navbar.jsp"/>
</header>

<br><br><br><br><br>
<div class="section container">
	<div class="center card-panel">
		<div class="container">
			<h5>La contraseña del usuario ya fue generada!</h5><br><br>
			<a class="green btn" href="/MedManager/webapp/pages/login/login.jsp">Volver al Login</a>
		</div>
	</div>
</div>
<br><br><br>

<jsp:include page="../commons/libraries.jsp"/>
<jsp:include page="../commons/footer.jsp"/>

</body>
</html>