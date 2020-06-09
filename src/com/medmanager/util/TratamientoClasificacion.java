package com.medmanager.util;

public class TratamientoClasificacion {
	
	/**
	 * 
	 * @param cantTumores Cantidad en int
	 * @param cis Si o No
	 * @param tama�oTumores: TTMA, TTME
	 * @param tipoTumor: TTP, PTA, PT1
	 * @param grado: Alto o Bajo
	 * @param inicio: IPV, IR1, IR2
	 * @param clasificacion TRUE para EAU, FALSE para AUA
	 * @return Alto Riesgo, Bajo Riesgo, Riesgo Intermedio.
	 */
	public static String getClasificacion(String cis, String cantTumores, String tama�oTumores, String tipoTumor, String grado, String inicio, String clasificacion) {
		String response;
		boolean CLASIF = false;
		
		if(tama�oTumores.equalsIgnoreCase("Menos de 3cm"))
			tama�oTumores = "TTME";
		else
			tama�oTumores = "TTMA";
		
		if(tipoTumor.equalsIgnoreCase("PUNLMP"))
			tipoTumor = "TTP";
		else if(tipoTumor.equalsIgnoreCase("Pta"))
			tipoTumor = "PTA";
		else
			tipoTumor = "PT1";
		
		if(inicio.equalsIgnoreCase("Reca�da dentro del 1er a�o"))
			inicio = "IR1";
		else if(inicio.equalsIgnoreCase("Primera Vez"))
			inicio = "IPV";
		else
			inicio = "IR2";
		
		if(clasificacion.equalsIgnoreCase("EAU"))
			CLASIF = true;

		if(cis.equals("Si")) {
			response = "Alto Riesgo";
		}else {
			if(CLASIF) {
				//EAU
				if(grado.equalsIgnoreCase("Alto Grado") || tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PT1)) {
					response = "Alto Riesgo";
				}else {
					if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PTA) && cantTumores.equals("Multiple") && tama�oTumores.equalsIgnoreCase(Constantes.TAMA�O_TUMORAL_MAYOR)) {
						response = "Alto Riesgo";
					}else {
						if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PUNLMP)) {
							response = "Bajo Riesgo";
						}else {
							if(tama�oTumores.equalsIgnoreCase(Constantes.TAMA�O_TUMORAL_MAYOR) || cantTumores.equals("Multiple") || inicio.equalsIgnoreCase(Constantes.INICIO_RECAIDA_1)) {
								response = "Riesgo Intermedio";
							}else {
								response = "Bajo Riesgo";
							}
						}
					}
				}
			}else {
				if(grado.equalsIgnoreCase("Alto Grado") && tama�oTumores.equalsIgnoreCase(Constantes.TAMA�O_TUMORAL_MENOR)) {
					response = "Alto Riesgo";
				}else {
					if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PUNLMP)) {
						response = "Bajo Riesgo";
					}else {
						if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PTA) && grado.equalsIgnoreCase("Bajo Grado") && tama�oTumores.equals(Constantes.TAMA�O_TUMORAL_MENOR) && cantTumores.equals("Unico") || inicio.equalsIgnoreCase(Constantes.INICIO_PRIMERA_VEZ) || inicio.equalsIgnoreCase(Constantes.INICIO_RECAIDA_2)) {
							response = "Bajo Riesgo";
						}else
							response = "Riesgo Intermedio";
					}
				}

			}
		}
		
		return response;
	}
}
