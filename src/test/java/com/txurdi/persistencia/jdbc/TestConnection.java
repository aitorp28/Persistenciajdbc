package com.txurdi.persistencia.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class TestConnection {

	@Test
	public void test() {
		//fail("Not yet implemented");
		//assertTrue(2==2);
		//assertFalse(2!=3);
		//assertTrue("no funciono", 2==3);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			fail("No hemos cargado el driver, mira le pom.xml y mete la dependencia");
			
		}
		
		
	}

}
