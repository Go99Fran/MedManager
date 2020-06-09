function logIn() {
	document.getElementById("ACCION").value = "LOGIN";
	document.getElementById("FORM_ACCION").submit()
}
function mostrarContrasena() {
	var tipo = document.getElementById("pass");
	if (tipo.type == "password") {
		tipo.type = "text";
	} else {
		tipo.type = "password";
	}
}
function validar(){
	if(document.getElementById("mail").value.includes("@")){
		if(document.getElementById("pass").value.length > 7){
			document.getElementById("bt_iniciar").disabled = false;
		}else{
			document.getElementById("bt_iniciar").disabled = true;
		}
	}else{
		document.getElementById("bt_iniciar").disabled = true;
	}
}