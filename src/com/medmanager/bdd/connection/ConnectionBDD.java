package com.medmanager.bdd.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class ConnectionBDD {
	
	private static String url = "";
	private static String pass = "";
	private static String driver = "";
	private static String user = "";
	private static Connection conexion = null;
	
	public static Connection GetConexion() {	
		try {
			if(url.equals("")) {
				 ResourceBundle myResources =
			              ResourceBundle.getBundle(ConnectionBDD.class.getCanonicalName());
				
				url = (String) myResources.getObject("datasource.url");
				pass = (String) myResources.getObject("datasource.password");
				driver = (String) myResources.getObject("datasource.driver");
				user = (String) myResources.getObject("datasource.user");
			}
			
			
			
			if(conexion == null) {
				Class.forName(driver);
				conexion = DriverManager.getConnection(url, user, pass);
			}
				
			return conexion;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
