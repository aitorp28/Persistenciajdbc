package com.txurdi.persistencia.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

	private static Connection CONEXION = null;
	
	//parametros de conexion
	private final static String URL = "jdbc:mysql://localhost:3306/supermercado?useSSL=false";
	private final static String USER = "root";
	private final static String PASS = "root";
	
	
	static Connection open() throws SQLException {		
		Connection con = DriverManager.getConnection(URL, USER, PASS);			
		return con;	
	}	
	

}
