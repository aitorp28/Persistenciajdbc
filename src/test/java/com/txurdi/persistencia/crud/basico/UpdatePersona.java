package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para crear una nueva persona<br>
 * id es autoincremental, no hace falta solicitarlo <br>
 * 
 * @author Aitor Perez
 *
 */
public class UpdatePersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "UPDATE `persona` SET  `Nombre` = ?, `nif` = ?, `Edad` = ?  WHERE `id` = ?;";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado?useSSL=false", "root",
				"root"); PreparedStatement pst = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS); Scanner sc = new Scanner(System.in);) {
			
			// solicitar datos		
			System.out.println("Dime el ID de la persona a buscar:");
			int id = Integer.parseInt(sc.nextLine());
			
			// sustituimmos el 1º ? por la variable id
			
			
			
				
			// TODO harcoded data
			System.out.println("Introduce el nombre");
			String nombre = sc.nextLine();
			System.out.println("Introduce el NIF");
			String nif = sc.nextLine();
			System.out.println("Introduce tu edad");
			int edad = Integer.parseInt(sc.nextLine());

			// sustituimmos intertogantes por valores
			pst.setString(1, nombre);
			pst.setString(2, nif);
			pst.setInt(3, edad);
			pst.setInt(4, id);
			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona actualizada" : "No se realizo la update";
			System.out.println(msj);

			//TODO gestionar Duplicate entry '1111111H' for key 'persona.nif_UNIQUE'
			
		}catch(Exception e) {
			System.out.println( e.getMessage());
			// 1ºtry
		}
		

	}

}
