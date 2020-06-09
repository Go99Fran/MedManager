<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Med Manager</title>
</head>

<script>
function Login(){
	<%
		request.getSession().setAttribute("Error", " ");
	%>
}
</script>

<body class="teal lighten-5">

<jsp:include page="../commons/head_comm.jsp"/>
<jsp:include page="../commons/navbar.jsp"/>
	
 
<br><br><br><br>

<form id="Volver" action="/MedManager/webapp/pages/login/login.jsp">
<div class="center-align section container">
	<div class="center-align card-panel">
		<h3>Oops! Algo salió mal :(</h3>
		<p style="visibility: hidden">-</p>
		<button class="btn" type="submit" onclick="Login()">Volver al Login</button>
	</div>

</div>	
</form>

<br><br><br>

	
	
<jsp:include page="../commons/libraries.jsp"/>
<jsp:include page="../commons/footer.jsp"/>
	
</body>
</html>