package com.medmanager.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.medmanager.bdd.facade.BD_Pasos;
import com.medmanager.bdd.facade.BD_Tratamientos;
import com.medmanager.model.TratamientoPaciente;
import com.medmanager.service.ITratamientoService;
import com.medmanager.util.CalendarUtil;
import com.medmanager.util.Constantes;
import com.medmanager.util.ParametrosUtil;

@WebService(endpointInterface="com.medmanager.service.ITratamientoService")
public class TratamientoService implements ITratamientoService {

	BD_Tratamientos bd_tratamientos;
	BD_Pasos bd_pasos;
	
	public TratamientoService() {
		bd_tratamientos = new BD_Tratamientos();
		bd_pasos = new BD_Pasos();
	}

	public TratamientoPaciente[] getTratamientosActivosPorId(int id_medico) {
		List<TratamientoPaciente> lista = new ArrayList<TratamientoPaciente>();
		System.out.println("SELECT de tratamientos activos");
		
		try {
			ResultSet data = bd_tratamientos.getTratamientosPacienteById(id_medico);
			int index = 0;

			ParametrosUtil parametrosHelp = new ParametrosUtil();
			String riesgo = "";
			String riesgo_cod ="";
					
			while(data.next()) {
				riesgo_cod = data.getString("riesgo_cod");
				
				if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_BAJO)) 
					riesgo = "Bajo Riesgo";
				else if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_MEDIO)) 
					riesgo = "Riesgo Intermedio (BCG Intra Vesical)";
				else if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_MEDIO2))
					riesgo = "Riesgo Intermedio (Quimioterapia Intra Vesical)";
				else
					riesgo = "Alto Riesgo";
				
				lista.add(new TratamientoPaciente(
						Integer.parseInt(data.getString("tratamientopaciente_id")),
						Integer.parseInt(data.getString("tratamiento_id")),
						id_medico,
						Integer.parseInt(data.getString("paciente_id")),
						data.getString("titulo"),
						data.getString("cis"),
						data.getString("cantidad_tumores"),
						parametrosHelp.getTamaño(data.getString("tamaño_tumoral_cod")),
						parametrosHelp.getTipo(data.getString("tipo_tumor_cod")),
						data.getString("grado_tumor"),
						data.getString("clasificacion_tratamiento"),
						riesgo,
						parametrosHelp.getInicio(data.getString("inicio_tumor_cod")),
						CalendarUtil.ParseString(data.getString("fecha_cirugia_rtuv")),
						CalendarUtil.ParseString(data.getString("fecha_cero")),
						Boolean.parseBoolean(data.getString("estado_cod")),
						data.getString("compromiso_uretral")
				));
								
				index++;
			}
			
			System.out.println("Se encontraron " + index + " tratamientos activos");
			TratamientoPaciente[] tratamientos = lista.toArray(new TratamientoPaciente[lista.size()]);
			return tratamientos;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("TRATAMIENTO_SERVICE: getTratamientosActivosPorId");
			return null;
		}
	}

	public int addTratamientoById(String riesgo, String compromiso, String fechaCero, String titulo, String cantTumores,
			String tamañoTumores, String tumor, String grado, String inicio, String clasif, String id_medico,
			String cis, int id_paciente) throws SQLException {

		ParametrosUtil parametrosHelp = new ParametrosUtil();

		tamañoTumores = parametrosHelp.getCodigoTamaño(tamañoTumores);
		tumor = parametrosHelp.getCodigoTipo(tumor);
		inicio = parametrosHelp.getCodigoInicio(inicio);

		String id_pasostratamiento = "";

		if (riesgo.equalsIgnoreCase("Alto Riesgo"))
			id_pasostratamiento = Constantes.TRATAMIENTO_ALTO;
		else if (riesgo.equalsIgnoreCase("Bajo Riesgo"))
			id_pasostratamiento = Constantes.TRATAMIENTO_BAJO;
		else if (riesgo.equalsIgnoreCase("BCG Intra Vesical")) {
			id_pasostratamiento = Constantes.TRATAMIENTO_MEDIO;
			riesgo = "Riesgo Intermedio: " + "BCG Intra Vesical";
		} else {
			id_pasostratamiento = Constantes.TRATAMIENTO_MEDIO2;
			riesgo = "Riesgo Intermedio: " + "Quimioterapia Intra Vesical";
		}	
		
		ResultSet data_id = bd_tratamientos.getNextIdTratamientoPaciente();
		int id_trat = 0;
		try{
			if(data_id.next())
				id_trat = Integer.parseInt(data_id.getString(1)) + 1;
			
		}catch(Exception e) {}
		
		if (id_pasostratamiento.equalsIgnoreCase(Constantes.TRATAMIENTO_BAJO)) {
			bd_pasos.CargarPasosB(id_pasostratamiento, id_trat, CalendarUtil.ParseString(fechaCero));
		} else {
			bd_pasos.CargarPasosA(id_pasostratamiento, id_trat);
		}
		
		bd_tratamientos.AltaTratamentoPaciente(compromiso, cis, String.valueOf(id_paciente), titulo, fechaCero, cantTumores, tamañoTumores, tumor,
				grado, inicio, clasif, id_medico, id_pasostratamiento, Constantes.ID_TRATAMIENTO_VEJIGA,
				id_trat);
	
		return id_trat;
	}
	
	public String getClasificacionRiesgo(String cis, String cantTumores, String tamanoTumores, String tumor,
			String grado, String inicio, String clasif, String compromisoUretral) {
		String riesgo = " ";
		String riesgo_cod ="";		
		try {
			ResultSet rs = bd_tratamientos.getClasficacion(cis, cantTumores, tamanoTumores, tumor, grado, inicio, clasif, compromisoUretral);			
			
			if(rs.next()) 
				riesgo_cod = rs.getString("riesgo_cod");
						
			if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_ALTO))
				riesgo = "Alto Riesgo";
			else if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_BAJO))
				riesgo = "Bajo Riesgo";
			else if(riesgo_cod.equalsIgnoreCase(Constantes.TRATAMIENTO_MEDIO))
				riesgo = "Riesgo Intermedio";
			else if(riesgo_cod.equalsIgnoreCase("0"))
				riesgo = rs.getString("riesgo_descripcion");
			else
				riesgo = "No calculado";
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("TRATAMIENTO_SERVICE: getClasificacionRiesgo");
		}
		
		return riesgo;
	}

	@Override
	public void inactivarTratamientoPorIdTratamiento(String idTratamiento) {
		try {
			bd_tratamientos.inactivarTratamiento(idTratamiento);			
		} catch (Exception e) {
			System.out.println("TRATAMIENTO_SERVICE: inactivarTratamientoPorIdPaciente");
			e.printStackTrace();
		}
	}

	
	
}
