package com.medmanager.service;

import com.medmanager.model.Paso;

public interface IPasoService {

	public void accionPaso(String usuario_id, String idPaso, int opcion);
	
	public Paso[] getPasosPorIdTratamiento(String id);

	public Paso[] getPasosPorIdPaciente(String id_usuario);
}
