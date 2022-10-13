package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para listar todas personas de la bbdd
 * @author Aitor Perez
 *
 */
public class BuscarPersonaPorId {

public static void main(String[] args) throws SQLException {
		
		System.out.println("Buscar Personas por Id");
		final String SQL = "SELECT id, nombre, nif, edad FROM persona WHERE id = ? ; ";
		
		// recursos autoclosables
		try( Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado?useSSL=false", "root", "root");
			 PreparedStatement pst = con.prepareStatement(SQL);
				Scanner sc = new Scanner(System.in); 
			 	){
			
			
			System.out.println("Intoroduce tu id");
			int id =Integer.parseInt( sc.nextLine());
			
			pst.setInt(1, id);
			
			try(ResultSet rs = pst.executeQuery()){
			
			
			
			
				
			
			
			//bucle para listar
			
			if (rs.next() ) {
			
				 id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String nif = rs.getString("nif");
				int edad = rs.getInt("edad");
				
				System.out.printf("%-5s %-50s %-9s %-2s \n",id, nombre,nif,edad);
				
			}else {
				System.out.println("lo sentimos pero no existe la persona");
			}
			if( id == 0) {
				System.out.println("Pinceh wey introduce una id");
			}
			System.out.printf("---------------------------------------------------------------------------------- \n");		
		
				
			
			}
		}// try
		

	}
}
