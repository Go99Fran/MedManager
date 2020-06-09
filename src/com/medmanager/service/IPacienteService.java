package com.medmanager.service;

public interface IPacienteService {
	
	public void bajaPaciente(String id);

	public int altaPaciente(String nombre, String mail, String fecha, String apellido, String id_paciente);

	public String getRiesgoPaciente(String t_id);
}
