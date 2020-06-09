package com.medmanager.bdd.facade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import com.medmanager.bdd.connection.ExecuteQuery;
import com.medmanager.util.CalendarUtil;
import com.medmanager.util.Constantes;

public class BD_Tratamientos {

	public void BajaTratamiento(String id) throws SQLException {
		ExecuteQuery.SimpleExecute("UPDATE tratamientos_paciente set estado_cod = false where paciente_id = '"+id+"'");
	}
	
	public void AltaTratamentoPaciente(String compromiso,
										String cis,
										String id_paciente, 
										String titulo,
										String fechaCero,
										String cantTumores,
										String tamañoTumores, 
										String tumor, 
										String grado, 
										String inicio, 
										String clasif, 
										String id_medico,
										String riesgo,
										String id_tratamiento,
										Integer id_tratamientopaciente) throws SQLException {
		
		if(riesgo.equalsIgnoreCase(Constantes.TRATAMIENTO_BAJO)) {
			ExecuteQuery.SimpleExecute("INSERT into tratamientos_paciente (compromiso_uretral, cis, fecha_cero, tratamientopaciente_id, tratamiento_id, medico_id, paciente_id, titulo, riesgo_cod, fecha_cirugia_rtuv, estado_cod, cantidad_tumores, tamaño_tumoral_cod, tipo_tumor_cod, grado_tumor, inicio_tumor_cod, clasificacion_tratamiento)"
					+ "  values('"+compromiso+"', '"+cis+"', '"+fechaCero+"', "+String.valueOf(id_tratamientopaciente) + ", " + id_tratamiento + ", " + id_medico + ", " + id_paciente + " , '" + titulo +"', '" + riesgo + "', '" + fechaCero + "', true, '" + cantTumores + "', '" + tamañoTumores + "', '" + tumor + "', '" + grado + "', '" + inicio + "', '" + clasif +"'"+")");
		}else {
			ExecuteQuery.SimpleExecute("INSERT into tratamientos_paciente (compromiso_uretral, cis, tratamientopaciente_id, tratamiento_id, medico_id, paciente_id, titulo, riesgo_cod, fecha_cirugia_rtuv, estado_cod, cantidad_tumores, tamaño_tumoral_cod, tipo_tumor_cod, grado_tumor, inicio_tumor_cod, clasificacion_tratamiento)"
					+ "  values('"+compromiso+"', '"+cis+"', "+String.valueOf(id_tratamientopaciente) + ", " + id_tratamiento + ", " + id_medico + ", " + id_paciente + " , '" + titulo +"', '" + riesgo + "', '" + fechaCero + "', true, '" + cantTumores + "', '" + tamañoTumores + "', '" + tumor + "', '" + grado + "', '" + inicio + "', '" + clasif +"'"+")");
		}
	}

	public ResultSet getTratamientosPacienteById(int id_medico) throws SQLException{
		return ExecuteQuery.ResultedExecute("SELECT * FROM tratamientos_paciente where medico_id = '"+id_medico+"' and estado_cod  = true");
	}

	public void setFechaCero(String id_trat, String id_usuario, Calendar fecha) throws SQLException {
		ExecuteQuery.SimpleExecute("UPDATE tratamientos_paciente set fecha_cero = '"+CalendarUtil.ParseCalendar(fecha)+"' where tratamientopaciente_id = "+id_trat+"");
			
			ResultSet rs = ExecuteQuery.ResultedExecute("SELECT pp.pasos_paciente_id, pa.unidad_tiempo_cod, pa.q_fecha_cero\r\n" + 
					"FROM pasos_paciente pp \r\n" + 
					"LEFT JOIN pasos_tratamiento pa ON pp.pasos_tratamiento_id = pa.pasos_tratamiento_id "
					+ "where pp.tratamientos_paciente_id = " + id_trat);
			
			Calendar fechaSugerida = Calendar.getInstance();
						
			while(rs.next()) {
				if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("S"))
					fechaSugerida.add(Calendar.DAY_OF_YEAR, 7 * Integer.parseInt(rs.getString("q_fecha_cero")));
				else if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("M"))
					fechaSugerida.add(Calendar.MONTH, Integer.parseInt(rs.getString("q_fecha_cero")));
				else if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("A"))
					fechaSugerida.add(Calendar.YEAR, Integer.parseInt(rs.getString("q_fecha_cero")));
				else
					fechaSugerida.add(Calendar.DAY_OF_YEAR, Integer.parseInt(rs.getString("q_fecha_cero")));
				
				ExecuteQuery.SimpleExecute("UPDATE pasos_paciente set fecha_accion_sugerida = '"+CalendarUtil.ParseCalendar(fechaSugerida)+"' where pasos_paciente_id = " + rs.getString("pasos_paciente_id"));
				fechaSugerida.setTime(fecha.getTime());
			}

	}

	public ResultSet getNextIdTratamientoPaciente() throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT MAX(tratamientopaciente_id) FROM tratamientos_paciente");
	}

	public ResultSet getClasficacion(String cis, String cantTumores, String tamanoTumores, String tumor, String grado,
			String inicio, String clasif, String compromisoUretral) throws SQLException {
		if(clasif.equalsIgnoreCase("AUA"))
			return ExecuteQuery.ResultedExecute("SELECT riesgo_cod, riesgo_descripcion FROM evaluacion_riesgo "
				+ "where tamaño_tumoral_cod = '"+tamanoTumores+"' and "
				+ "tipo_tumor_cod = '"+tumor+"' and "
				+ "grado_tumor = '"+grado+"' and "
				+ "inicio_tumor_cod = '"+inicio+"' and "
				+ "cantidad_tumores = '"+cantTumores+"' and "
				+ "clasificacion_tratamiento = '"+clasif+"' and "
				+ "compromiso_uretral = '"+compromisoUretral+"' and "
				+ "cis = '"+cis+"'");
		else
			return ExecuteQuery.ResultedExecute("SELECT riesgo_cod, riesgo_descripcion FROM evaluacion_riesgo "
					+ "where tamaño_tumoral_cod = '"+tamanoTumores+"' and "
					+ "tipo_tumor_cod = '"+tumor+"' and "
					+ "grado_tumor = '"+grado+"' and "
					+ "inicio_tumor_cod = '"+inicio+"' and "
					+ "cantidad_tumores = '"+cantTumores+"' and "
					+ "clasificacion_tratamiento = '"+clasif+"' and "
					+ "cis = '"+cis+"'");
	}
	
	public ResultSet getTratamientoPorIdPaciente(String id_paciente) throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT tratamientopaciente_id FROM tratamientos_paciente where paciente_id = " + id_paciente + " and estado_cod = true");
	}
	
	public void inactivarTratamiento(String idTratamiento) throws SQLException {
		ExecuteQuery.SimpleExecute("UPDATE tratamientos_paciente set estado_cod = false where tratamientopaciente_id = " + idTratamiento);
	}
}