package com.medmanager.bdd.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQuery {
		
	public ExecuteQuery() {
		
	}
	
	public static void SimpleExecute(String Sentence) throws SQLException {
		Connection conexion = ConnectionBDD.GetConexion();

		if (conexion != null) {
			Statement st = conexion.createStatement();
			st.execute(Sentence);

		}
	}

	public static ResultSet ResultedExecute(String Sentence) throws SQLException {
		Connection conexion = ConnectionBDD.GetConexion();

		if (conexion != null) {
			Statement st = conexion.createStatement();
			return st.executeQuery(Sentence);

		} else {
			return null;
		}

	}
}
