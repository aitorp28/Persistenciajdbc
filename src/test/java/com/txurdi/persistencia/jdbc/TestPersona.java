package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Statement;

public class TestPersona {

	@Test
	public void test() {
		fail("Not yet implemented");
		
		
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado","root","root");
			
			Statement st = (Statement) c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM persona");
					while(rs.next()){
						
					}
		} catch (SQLException e) {
			fail("Parece que no existe la bbdd txurdi, o los url de conexion no es correcta");
			
		}
	}

}
