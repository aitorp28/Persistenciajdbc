package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Ejemplo básico para crear una nueva persona<br>
 * id es autoincremental, no hace falta solicitarlo <br>
 * 
 * @author Aitor Perez
 *
 */
public class InsertarPersona {

	public static void main(String[] args) throws SQLException {

		final String SQL = "INSERT INTO `persona` (`nombre`, `nif`, `edad`) VALUES ( ?, ? , ?) ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado?useSSL=false", "root",
				"root"); PreparedStatement pst = con.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS); Scanner sc = new Scanner(System.in);) {

			// solicitar datos			
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

			// ejecutar pst y nos retorna un int == affetedRows
			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona insertada" : "No se realizo la insert";
			System.out.println(msj);
			//recuperar id
			if(affectedRows == 1) {
				try(ResultSet rs = pst.getGeneratedKeys()){
					if(rs.next()) {
						int id = rs.getInt(1);
						System.out.println("El id generado de la persona es "+ id);
					}
				}catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println("Nif duplicado !!!");
					// TODO: handle exception
				}catch(Exception e) {
					System.out.println("Exception "+ e.getMessage());
				}
			}
			//TODO gestionar Duplicate entry '1111111H' for key 'persona.nif_UNIQUE'
			
		}catch(Exception e) {
			System.out.println("El nif introducido ya existe cambiate de nif ");
			// 1ºtry
		}
		

	}

}
