function AgregarDatosTratamiento() {		
		var tamano = document.getElementById("tamanoS").options[document
					.getElementById("tamanoS").selectedIndex].text;
		var clasif = document.getElementById("clasificacionS").options[document
					.getElementById("clasificacionS").selectedIndex].text;
		var inicio = document.getElementById("inicioS").options[document
					.getElementById("inicioS").selectedIndex].text;
		var tumor = document.getElementById("tumorS").options[document
					.getElementById("tumorS").selectedIndex].text;
		var cantidad = document.getElementById("cantidadS").options[document
					.getElementById("cantidadS").selectedIndex].text;
		var grado = "";
				
		if(tumor != "PUNLMP"){
			grado = document.getElementById("gradoS").options[document
				.getElementById("gradoS").selectedIndex].text;
		}else{
			grado = "Bajo Grado";
		}
		
		var fecha = document.getElementById("fechaC").value;
		
		if (tamano != "" && clasif != "" && inicio != "" && grado != ""
				&& tumor != "" && cantidad != "" && fecha != "") {
				
		fecha = document.getElementById("fechaC").value.replace("/","-");
		fecha = document.getElementById("fechaC").value.replace("/","-");
								
		document.getElementById("tamanoTumores").value = tamano;
		document.getElementById("clasif").value = clasif;
		document.getElementById("inicio").value = inicio;
		document.getElementById("grado").value = grado;
		document.getElementById("tumor").value = tumor;
		document.getElementById("cantTumores").value = cantidad;
					
		if (document.getElementById("CIS").checked) {
			document.getElementById("cis").value = 'Si';
		} else {
			document.getElementById("cis").value = "No";
		}
			
		if (document.getElementById("compromiso").checked) {
			document.getElementById("compromisoUretral").value = 'Si';
		} else {
			document.getElementById("compromisoUretral").value = "No";
		}
							
		document.getElementById("FormAlta").submit();
						
		}else{
			const elem = document.getElementById("modalFaltanCampos");
			const instance = M.Modal.init(elem, {
				dismissible : false
			});
			instance.open();
		}	
}
function checkClasif(){
	if(document.getElementById("clasificacionS").options[document
		.getElementById("clasificacionS").selectedIndex].text == "EAU"){
		document.getElementById("div_comp").style.display = "none";
	}else{
		document.getElementById("div_comp").style.display = "block";
	}
}
function checkTumor(){
	if(document.getElementById("tumorS").options[document
		.getElementById("tumorS").selectedIndex].text == "PUNLMP"){
		document.getElementById("grado_div").style.display = "none";
		document.getElementById("grado_div_bajo").style.display = "block";
	}else{
		document.getElementById("grado_div_bajo").style.display = "none";
		document.getElementById("grado_div").style.display = "block";
	}
}
function carga(){
	document.getElementById("grado_div_bajo").style.display = "none";
}
