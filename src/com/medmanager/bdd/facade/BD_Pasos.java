package com.medmanager.bdd.facade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import com.medmanager.bdd.connection.ExecuteQuery;
import com.medmanager.util.CalendarUtil;
import com.medmanager.util.Constantes;

public class BD_Pasos {
	
	public ResultSet getPasosPorIdTratamiento(String id) throws SQLException {
		return ExecuteQuery.ResultedExecute("select pa.nombre, pa.descripcion, pp.observaciones, pp.pasos_paciente_id ,pp.fecha_accion_sugerida, pp.fecha_accion, pp.estado from pasos_paciente pp\r\n" + 
					"left join pasos_tratamiento pt on pp.pasos_tratamiento_id = pt.pasos_tratamiento_id\r\n" + 
					"left join pasos pa on pt.paso_id = pa.paso_id and pa.activo = true\r\n" + 
					"where pp.tratamientos_paciente_id = "+id+ "\r\n"
					+ "order by pp.fecha_accion_sugerida, CASE\r\n" + 
					"    WHEN unidad_tiempo_cod = 'D' THEN q_fecha_cero\r\n" + 
					"    WHEN unidad_tiempo_cod = 'M' THEN q_fecha_cero * 30\r\n" + 
					"    WHEN unidad_tiempo_cod = 'A' THEN q_fecha_cero * 365\r\n" + 
					"    WHEN unidad_tiempo_cod = 'S' THEN q_fecha_cero * 7\r\n" + 
					"END, pa.nombre asc;");
	}

	public void AccionPaso(String idPaso, int opcion) throws SQLException {
		String fecha = CalendarUtil.ParseCalendar(Calendar.getInstance());
		ExecuteQuery.SimpleExecute("update pasos_paciente set estado = "+String.valueOf(opcion)+", fecha_accion = '"+fecha+"' where pasos_paciente_id = " + idPaso);
	}
	
	public void CargarPasosB(String id_pasostratamiento, int id_tratamientos_paciente, Calendar fechaCero) throws NumberFormatException, SQLException {
			ResultSet codigo = ExecuteQuery.ResultedExecute("select MAX(pasos_paciente_id) from pasos_paciente");
			int y = 0;
			
			if(codigo.next()) {
				if(codigo.getString(1) == null)
					y = 0;
				else
					y = Integer.parseInt(codigo.getString(1)) + 1;
			}
			
			ResultSet rs = ExecuteQuery.ResultedExecute("select * from pasos_tratamiento where riesgo = '"+id_pasostratamiento+"'");
			Calendar fechaSugerida = Calendar.getInstance();
			
			while(rs.next()) {
				fechaSugerida.setTime(fechaCero.getTime());

				if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("S"))
					fechaSugerida.add(Calendar.DAY_OF_YEAR, 7 * Integer.parseInt(rs.getString("q_fecha_cero")));
				else if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("M"))
					fechaSugerida.add(Calendar.DAY_OF_YEAR, 30 * Integer.parseInt(rs.getString("q_fecha_cero")));
				else if(rs.getString("unidad_tiempo_cod").equalsIgnoreCase("A"))
					fechaSugerida.add(Calendar.DAY_OF_YEAR, 365 * Integer.parseInt(rs.getString("q_fecha_cero")));
				else
					fechaSugerida.add(Calendar.DAY_OF_YEAR, Integer.parseInt(rs.getString("q_fecha_cero")));
				
				
				ExecuteQuery.SimpleExecute("insert into pasos_paciente (fecha_accion_sugerida, pasos_paciente_id, pasos_tratamiento_id, tratamientos_paciente_id, estado) "
						+ "values ('"+CalendarUtil.ParseCalendar(fechaSugerida)+"', "+y+", "+rs.getString("pasos_tratamiento_id")+", "+id_tratamientos_paciente+", "+Constantes.ESTADOS_PASO_PENDIENTE+")");
				
				y++;
			}
	}
	
	public void CargarPasosA(String id_pasostratamiento, int id_tratamientos_paciente) throws SQLException {
			ResultSet codigo = ExecuteQuery.ResultedExecute("select MAX(pasos_paciente_id) from pasos_paciente");
			int y = 0;
			
			if(codigo.next()) {
				if(codigo.getString(1) == null)
					y = 0;
				else
					y = Integer.parseInt(codigo.getString(1)) + 1;
			}
			
			ResultSet rs = ExecuteQuery.ResultedExecute("select * from pasos_tratamiento where riesgo = '"+id_pasostratamiento+"'");
			
			while(rs.next()) {		
				
				ExecuteQuery.SimpleExecute("insert into pasos_paciente (pasos_paciente_id, pasos_tratamiento_id, tratamientos_paciente_id, estado) "
						+ "values ("+y+", "+rs.getString("pasos_tratamiento_id")+", "+id_tratamientos_paciente+", "+Constantes.ESTADOS_PASO_PENDIENTE+")");
				
				y++;
			}
	}

	public ResultSet isPrimerPaso(String usuario_id) throws SQLException {
		return ExecuteQuery.ResultedExecute("select fecha_cero from tratamientos_paciente where paciente_id = " + usuario_id + " and estado_cod = true");
	}
}