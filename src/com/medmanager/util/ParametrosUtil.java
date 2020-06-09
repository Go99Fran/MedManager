package com.medmanager.util;

public class ParametrosUtil {

	public String getCodigoTama�o(String t) {
		if(t.equals("Mayor a 3cm"))
			return Constantes.TAMA�O_TUMORAL_MAYOR;
		else
			return Constantes.TAMA�O_TUMORAL_MENOR;
	}
	public String getCodigoTipo(String t) {
		if(t.equals("PUNLMP"))
			return Constantes.TIPO_TUMOR_PUNLMP;
		else if(t.equals("Pta"))
			return Constantes.TIPO_TUMOR_PTA;
		else
			return Constantes.TIPO_TUMOR_PT1;					
	}
	public String getCodigoInicio(String t) {
		if(t.equals("Reca�da dentro del 1er a�o"))
			return Constantes.INICIO_RECAIDA_1;
		else if(t.equals("Reca�da despu�s del 1er a�o"))
			return Constantes.INICIO_RECAIDA_2;
		else
			return Constantes.INICIO_PRIMERA_VEZ;
	}

	public String getTama�o(String t) {
		if(t.equals(Constantes.TAMA�O_TUMORAL_MAYOR))
			return "Mayor a 3cm";
		else
			return "Menor a 3cm";
	}
	
	public String getTipo(String t) {
		if(t.equals(Constantes.TIPO_TUMOR_PUNLMP))
			return "PUNLMP";
		else if(t.equals(Constantes.TIPO_TUMOR_PTA))
			return "Pta";
		else
			return "Pt1";	
	}
	
	public String getInicio(String t) {
		if(t.equals(Constantes.INICIO_PRIMERA_VEZ))
			return "Primera Vez";
		else if(t.equalsIgnoreCase(Constantes.INICIO_RECAIDA_1))
			return "Reca�da dentro del 1er a�o";
		else
			return "Reca�da despu�s del 1er a�o";
	}
}
