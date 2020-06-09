package com.medmanager.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebService;

import com.medmanager.bdd.facade.BD_Pacientes;
import com.medmanager.bdd.facade.BD_Tratamientos;
import com.medmanager.bdd.facade.BD_Usuarios;
import com.medmanager.service.IPacienteService;
import com.medmanager.util.Constantes;

@WebService(endpointInterface="com.medmanager.service.IPacienteService")
public class PacienteService implements IPacienteService {

	BD_Pacientes bd_pacientes;
	BD_Usuarios bd_usuarios;
	BD_Tratamientos bd_tratamientos;

	public PacienteService() {
		bd_pacientes = new BD_Pacientes();
		bd_usuarios = new BD_Usuarios();
		bd_tratamientos = new BD_Tratamientos();
	}

	public void bajaPaciente(String idPaciente) {
		
		try {
			bd_pacientes.BajaPaciente(idPaciente);
			bd_tratamientos.BajaTratamiento(idPaciente);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("PACIENTE_SERVICE: bajaPaciente");
		}
		
	}

	public int altaPaciente(String nombre, String mail, String fecha, String apellido, String id_medico) {
		int id = 0;
		try{
			ResultSet Data_ID = bd_usuarios.getNextUsuarioId();
		
			if(Data_ID.next())
				id = Integer.parseInt(Data_ID.getString(1)) + 1;
		
		}catch(Exception e) {}
		
		try {
			bd_pacientes.AltaPaciente(id, mail, nombre, fecha, apellido, id_medico);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PACIENTE_SERVICE: altaPaciente");
		}
		
		return id;
	}

	public String getRiesgoPaciente(String t_id) {
		String rsp = "";
		
		try {
			ResultSet rs = bd_pacientes.getRiesgoPaciente(t_id);
			
			if(rs.next())
				rsp = rs.getString("riesgo_cod");
			
			if(rsp.equalsIgnoreCase(Constantes.TRATAMIENTO_BAJO)) 
				return "Bajo Riesgo";
			else if(rsp.equalsIgnoreCase(Constantes.TRATAMIENTO_MEDIO)) 
				return "Riesgo Intermedio (BCG Intra Vesical)";
			else if(rsp.equalsIgnoreCase(Constantes.TRATAMIENTO_MEDIO2))
				return "Riesgo Intermedio (Quimioterapia Intra Vesical)";
			else
				return "Alto Riesgo";
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("PACIENTE_SERVICE: getRiesgoPaciente");
		}
		
		return rsp;
	}

}
