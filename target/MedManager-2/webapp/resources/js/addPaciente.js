function validar(opcion){
	if(document.getElementById("nombre").value != "" && 
			document.getElementById("apellido").value != "" &&
			document.getElementById("mail").value != "" &&
			document.getElementById("fecha").value != ""){
				
		var fechaOperacion = new Date(document.getElementById("fecha").value);
		var fechaHoy = new Date();
		var fechaMinNovecientos = new Date("1900-01-01");
		
		if(fechaOperacion.getTime() < fechaHoy.getTime()){
			if(fechaOperacion.getTime() > fechaMinNovecientos.getTime()){
				var mail = document.getElementById("mail").value;
				if(mail.includes("@")){
					document.getElementById("fecha").value = document.getElementById("fecha").value.replace("/","-");
					document.getElementById("fecha").value = document.getElementById("fecha").value.replace("/","-");
					if(opcion == "2"){
						CrearTratamiento();
					}else{
						AddPacienteSolo();
					}
				}else{
					const elem = document.getElementById("modalErrorMail");
					const instance = M.Modal.init(elem, {
						dismissible : false
					});
					instance.open();
				}
			}else{
				const elem = document.getElementById("modalFechaMenor");
				const instance = M.Modal.init(elem, {
					dismissible : false
				});
				instance.open();
			}
			
		}else{
			const elem = document.getElementById("modalFechaMayor");
			const instance = M.Modal.init(elem, {
				dismissible : false
			});
			instance.open();
		}
	}else{
		const elem = document.getElementById("modalFaltanCampos");
		const instance = M.Modal.init(elem, {
			dismissible : false
		});
		instance.open();
	}
}
function CrearTratamiento(){
	document.getElementById("CrearTratamiento").value = "Si";
	document.getElementById("FormAlta").submit();
}

function AddPacienteSolo(){
	document.getElementById("CrearTratamiento").value = "No";
	document.getElementById("FormAlta").submit();
}

function movimientoNombre(){
	document.getElementById("fig_name").innerHTML = document.getElementById("nombre").value + " " + document.getElementById("apellido").value;
}
function movimientoMail(){
	document.getElementById("fig_mail").innerHTML = document.getElementById("mail").value;
}
function movimientoFecha(){
	document.getElementById("fig_fecha").innerHTML = document.getElementById("fecha").value;
}