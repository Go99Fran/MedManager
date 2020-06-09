function VerPaciente(id) {
	document.getElementById("ACCION").value = "VER_TRATAMIENTO";
	document.getElementById("IdTratamiento").value = id.replace("V", "");
	document.getElementById("Form").submit();
}
function BuscarPaciente(opcion) {
	document.getElementById("ACCION").value = "BUSCAR_PACIENTE";
	if (opcion == 0) {
		document.getElementById("buscar").value = document
				.getElementById("busquedaPacienteInput").value;
		document.getElementById("Form").submit();
	} else {
		document.getElementById("buscar").value = '';
		document.getElementById("Form").submit();
	}
}
function BajaPaciente() {
	document.getElementById("ACCION").value = "BAJA_PACIENTE";
	document.getElementById("Form").submit();
}
function AltaTratamiento(id){
	document.getElementById("ACCION").value = "ALTA_VISTA_TRATAMIENTO";
	document.getElementById("IdPaciente").value = id.replace("BA", "");
	document.getElementById("Form").submit();
}
function PutIDBajaPaciente(id){
	document.getElementById("IdPaciente").value = id.replace("B", "");
	
	const elem = document.getElementById("modalBajaPaciente");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function PutIDEditPaciente(id){
	document.getElementById("IdPaciente").value = id.replace("B", "");
	
	const elem = document.getElementById("modalEditPaciente");
	const instance = M.Modal.init(elem, {
		dismissible : false
	});
	instance.open();
}
function logOut(){
	document.getElementById("ACCION").value = "LogOut";
	document.getElementById("Form").submit();
}