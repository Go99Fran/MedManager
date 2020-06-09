package com.medmanager.service;

import java.sql.SQLException;

import com.medmanager.model.TratamientoPaciente;

public interface ITratamientoService {

	public TratamientoPaciente[] getTratamientosActivosPorId(int id_medico);

	public int addTratamientoById(
				String riesgo,
				String compromiso,
				String fechaCero,
				String titulo,
				String cantTumores,
				String tamañoTumores,
				String tumor,
				String grado,
				String inicio,
				String clasif,
				String id_medico,
				String cis,
				int id_paciente) throws SQLException;

	public String getClasificacionRiesgo(String cis, String cantTumores, String tamanoTumores, String tumor,
			String grado, String inicio, String clasif, String compromisoUretral);

	public void inactivarTratamientoPorIdTratamiento(String IdTratamiento);
	
}
