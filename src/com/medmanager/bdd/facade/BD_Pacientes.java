package com.medmanager.bdd.facade;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.medmanager.bdd.connection.ExecuteQuery;
import com.medmanager.security.Cryptography;
import com.medmanager.util.MailValidation;

public class BD_Pacientes {
	
	public void BajaPaciente(String id) throws SQLException {
		ExecuteQuery.SimpleExecute("update usuarios set activo = false where usuario_id = '"+id+"'");
	}
	
	public ResultSet getPacientesPorIdMedico(int usuario_id) throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT * FROM usuarios where medico_ref_id = "+usuario_id+" and activo = true order by usuario_id");
	}
	
	public void AltaPaciente(int nextId, String mail, String nombre, String fecha, String apellido, String id_medico) throws Exception {
			mail = mail.toLowerCase();
			ExecuteQuery.SimpleExecute("insert into usuarios "
					+ "(clave, usuario_id, mail, confirmado, nombre, apellido, fecha_nacimiento, Tipo_Usuario_COD, activo,"
					+ "fecha_term_cond, matricula, medico_ref_id, term_cond_id)\n" + 
					"values ('"+Cryptography.encrypt("Lobo99", Cryptography.getKey())+"', "+nextId+",'"+mail+"',false,'"+nombre+"', '"+apellido+"','"+fecha+"',0, "
							+ "true, null, null, '"+id_medico+"', null)");
			
		@SuppressWarnings("unused")
		MailValidation mailV = new MailValidation(mail, nombre);
	}

	public ResultSet getRiesgoPaciente(String t_id) throws SQLException {
		return ExecuteQuery.ResultedExecute("select riesgo_cod from tratamientos_paciente where tratamientopaciente_id = " + t_id);
	}

	public String getTratamiento(int usuario_id) throws SQLException {
		ResultSet rs = ExecuteQuery.ResultedExecute("select nombre from tratamientos \r\n" + 
				"where tratamientos.tratamiento_id = \r\n" + 
				"(select tratamiento_id from tratamientos_paciente where paciente_id = "+String.valueOf(usuario_id)+" and estado_cod = true)");
		
		String tratamiento = " ";
		
		while(rs.next())
			tratamiento = rs.getString(1);
		
		return tratamiento;
	}
}