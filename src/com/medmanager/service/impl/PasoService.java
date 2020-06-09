package com.medmanager.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import com.medmanager.bdd.facade.BD_Pasos;
import com.medmanager.bdd.facade.BD_Tratamientos;
import com.medmanager.model.Paso;
import com.medmanager.service.IPasoService;
import com.medmanager.util.CalendarUtil;

@WebService(endpointInterface="com.medmanager.service.IPasoService")
public class PasoService implements IPasoService {

	BD_Pasos bd_pasos;
	BD_Tratamientos bd_tratamientos;
	
	public PasoService() {
		bd_pasos = new BD_Pasos();
		bd_tratamientos = new BD_Tratamientos();
	}
	
	public void accionPaso(String id_usuario, String idPaso, int opcion) {
		boolean isPrimero = false;
		try {		
			ResultSet primerPaso_data = bd_pasos.isPrimerPaso(id_usuario);
						
			if(primerPaso_data.next()) {
				if(primerPaso_data.getString("fecha_cero") == null)
					isPrimero = true;
			}else {
				isPrimero = true;
			}
		
			if(isPrimero) {			
				String id = "";
				try {
					ResultSet rs = bd_tratamientos.getTratamientoPorIdPaciente(id_usuario);
					
					if(rs.next())
						id = rs.getString(1);
					
					bd_tratamientos.setFechaCero(id, id_usuario, Calendar.getInstance());
				}catch(Exception e) {
					System.out.println("PASO_SERVICE: accionPaso");
				}				
			}
				bd_pasos.AccionPaso(idPaso, opcion);
		
		} catch (SQLException e) {
			System.out.println("PASO_SERVICE: accionPaso");
			e.printStackTrace();
		}
	}

	public Paso[] getPasosPorIdTratamiento(String id) {
		List<Paso> pasos = new ArrayList<Paso>();
		
		try {
			ResultSet rs = bd_pasos.getPasosPorIdTratamiento(id);
			
			while(rs.next()) {
				pasos.add(new Paso(rs.getString("nombre"),
						rs.getString("descripcion"), 
						Integer.parseInt(rs.getString("estado")),
						CalendarUtil.ParseString(rs.getString("fecha_accion_sugerida")), 
						CalendarUtil.ParseString(rs.getString("fecha_accion")),
						Integer.parseInt(rs.getString("pasos_paciente_id")))
						);
			}
		
		}catch(Exception e) {
			System.out.println("Error select de pasos");
			e.printStackTrace();
		}
		
		Paso[] pasosResponse = pasos.toArray(new Paso[pasos.size()]);

		return pasosResponse;
	}

	@Override
	public Paso[] getPasosPorIdPaciente(String id_usuario) {
		String id = "";
		try {
			ResultSet rs = bd_tratamientos.getTratamientoPorIdPaciente(id_usuario);
			
			if(rs.next())
				id = rs.getString(1);
			
		}catch(Exception e) {
			System.out.println("PASO_SERVICE: getPasosPorIdPaciente");
		}
		
		return getPasosPorIdTratamiento(id);
	}
}
