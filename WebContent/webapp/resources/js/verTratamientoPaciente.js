function CambiarFiltro() {
	document.getElementById("ACCION").value = "CAMBIAR_FILTROS";
	document.getElementById("Form").submit();
}
function ModalInactivar() {
	const elem = document.getElementById("modal");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function Inactivar() {
	document.getElementById("ACCION").value = "INACTIVAR_TRATAMIENTO";
	document.getElementById("Form").submit();
}