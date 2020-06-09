package com.medmanager.util;

public class TratamientoClasificacion {
	
	/**
	 * 
	 * @param cantTumores Cantidad en int
	 * @param cis Si o No
	 * @param tamañoTumores: TTMA, TTME
	 * @param tipoTumor: TTP, PTA, PT1
	 * @param grado: Alto o Bajo
	 * @param inicio: IPV, IR1, IR2
	 * @param clasificacion TRUE para EAU, FALSE para AUA
	 * @return Alto Riesgo, Bajo Riesgo, Riesgo Intermedio.
	 */
	public static String getClasificacion(String cis, String cantTumores, String tamañoTumores, String tipoTumor, String grado, String inicio, String clasificacion) {
		String response;
		boolean CLASIF = false;
		
		if(tamañoTumores.equalsIgnoreCase("Menos de 3cm"))
			tamañoTumores = "TTME";
		else
			tamañoTumores = "TTMA";
		
		if(tipoTumor.equalsIgnoreCase("PUNLMP"))
			tipoTumor = "TTP";
		else if(tipoTumor.equalsIgnoreCase("Pta"))
			tipoTumor = "PTA";
		else
			tipoTumor = "PT1";
		
		if(inicio.equalsIgnoreCase("Recaída dentro del 1er año"))
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
					if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PTA) && cantTumores.equals("Multiple") && tamañoTumores.equalsIgnoreCase(Constantes.TAMAÑO_TUMORAL_MAYOR)) {
						response = "Alto Riesgo";
					}else {
						if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PUNLMP)) {
							response = "Bajo Riesgo";
						}else {
							if(tamañoTumores.equalsIgnoreCase(Constantes.TAMAÑO_TUMORAL_MAYOR) || cantTumores.equals("Multiple") || inicio.equalsIgnoreCase(Constantes.INICIO_RECAIDA_1)) {
								response = "Riesgo Intermedio";
							}else {
								response = "Bajo Riesgo";
							}
						}
					}
				}
			}else {
				if(grado.equalsIgnoreCase("Alto Grado") && tamañoTumores.equalsIgnoreCase(Constantes.TAMAÑO_TUMORAL_MENOR)) {
					response = "Alto Riesgo";
				}else {
					if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PUNLMP)) {
						response = "Bajo Riesgo";
					}else {
						if(tipoTumor.equalsIgnoreCase(Constantes.TIPO_TUMOR_PTA) && grado.equalsIgnoreCase("Bajo Grado") && tamañoTumores.equals(Constantes.TAMAÑO_TUMORAL_MENOR) && cantTumores.equals("Unico") || inicio.equalsIgnoreCase(Constantes.INICIO_PRIMERA_VEZ) || inicio.equalsIgnoreCase(Constantes.INICIO_RECAIDA_2)) {
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
