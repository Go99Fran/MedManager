package com.medmanager.bdd.facade;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.medmanager.bdd.connection.ExecuteQuery;
import com.medmanager.security.Cryptography;

public class BD_Usuarios {

	public ResultSet esUsuarioConfirmado(String mail) throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT confirmado from usuarios where mail = '"+mail+"'");
	}

	public ResultSet existeMail(String mail) throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT usuario_id from usuarios where mail = '"+mail+"'");
	}

	public ResultSet getNextUsuarioId() throws SQLException {
		return ExecuteQuery.ResultedExecute("SELECT MAX(usuario_id) FROM usuarios");
	}	

	public void ValidarUsuario(String mail, String pass) throws Exception {
		ExecuteQuery.SimpleExecute("UPDATE usuarios SET clave = '"+Cryptography.encrypt(pass, Cryptography.getKey())+"', confirmado = true WHERE mail = '"+mail+"'");
	}

	public ResultSet LogeoUsuario(String mail, String password) throws SQLException, Exception{
		return ExecuteQuery.ResultedExecute("SELECT * from usuarios where mail = '"+mail+"' and activo = true");
	}

	public void registrarMedico(String mail, String password, String nombre, String apellido, boolean confirmado,
			boolean activo, String id_usuario) throws SQLException, Exception {
		System.out.println(Cryptography.encrypt(password, Cryptography.getKey()));
		ExecuteQuery.SimpleExecute("insert into usuarios (tipo_usuario_cod, mail, clave, nombre, apellido, confirmado, activo, usuario_id) "
					+ "values(1, '"+mail+"', '"+Cryptography.encrypt(password, Cryptography.getKey())+"', '"+nombre+"', '"+apellido+"', "+confirmado+", "+activo+", "+id_usuario+")");
	}
}