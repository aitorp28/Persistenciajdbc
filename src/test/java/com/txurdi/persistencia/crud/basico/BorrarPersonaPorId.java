package com.txurdi.persistencia.crud.basico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Ejemplo básico para listar todas personas de la bbdd
 * 
 * @author Aitor Perez
 *
 */
public class BorrarPersonaPorId {

	public static void main(String[] args) throws SQLException {

		System.out.println("Buscar Personas por Id");
		final String SQL = "DELETE FROM  `persona` WHERE id = ? ; ";

		// recursos autoclosables
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermercado?useSSL=false",
				"root", "root");
				PreparedStatement pst = con.prepareStatement(SQL);
				Scanner sc = new Scanner(System.in);) {

			System.out.println("Intoroduce tu id");
			int id = Integer.parseInt(sc.nextLine());

			pst.setInt(1, id);

			int affectedRows = pst.executeUpdate();

			String msj = (affectedRows == 1) ? "Persona eliminada" : "No se pudo eliminar!!!";
			System.out.println(msj);

			// bucle para listar

		}
	}
}
