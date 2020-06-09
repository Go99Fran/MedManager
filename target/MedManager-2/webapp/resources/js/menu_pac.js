function logOut(){
	document.getElementById("Action").value = "LogOut";
	document.getElementById("FormAC").submit();
}
function PasoCheck(id){
	document.getElementById("PasoID").value = id.split(" ")[1];
	
	const elem = document.getElementById("modalPasoCheck");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function GuardarPasoCheck(){
	document.getElementById("Action").value = "CheckPaso";
	document.getElementById("scrollPosition").value = document.documentElement.scrollTop;
	document.getElementById("FormAC").submit();
}
function PasoNulo(id){	
	document.getElementById("PasoID").value = id.split(" ")[1];
	
	const elem = document.getElementById("modalPasoNulo");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function GuardarPasoNulo(){
	document.getElementById("Action").value = "AnularPaso";
	document.getElementById("scrollPosition").value = document.documentElement.scrollTop;
	document.getElementById("FormAC").submit();
}
function CambiarFiltro(){
	document.getElementById("Action").value = "CambiarFiltros";
	document.getElementById("FormAC").submit();
}
function Deshacer(id){	
	document.getElementById("PasoID").value = id.split(" ")[1];
	const elem = document.getElementById("modalDeshacerPaso");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function GuardarPasoDeshacer(){
	document.getElementById("Action").value = "DeshacerPaso";
	document.getElementById("scrollPosition").value = document.documentElement.scrollTop;
	document.getElementById("FormAC").submit();
}
function carga(){
	document.documentElement.scrollTop = document.getElementById("scrollPosition").value;
}