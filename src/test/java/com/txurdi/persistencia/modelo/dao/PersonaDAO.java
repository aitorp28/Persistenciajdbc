package com.txurdi.persistencia.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.txurdi.persistencia.modelo.pojo.Persona;

public class PersonaDAO implements Singleton, Crudable<Persona> {

	private static PersonaDAO INSTANCE = null;
	
	// Sentencias SQL
	private static final String SQL_FIND_ALL = "SELECT id, nombre, nif, edad FROM persona ORDER BY nombre ASC LIMIT 500; ";
	private static final String FIND_BY_ID = "SELECT id, nombre, nif, edad FROM persona WHERE id = ? ;  ";
	private static final String UPDATE = "UPDATE `persona` SET `nombre` = ?, `nif` = ?, `edad` = ? WHERE `id` = ? ; ";
	private static final String INSERT = "INSERT INTO `persona` (`nombre`, `nif`, `edad`) VALUES (?,?,?) ; ";
	private static final String DELETE = "DELETE FROM `persona` WHERE `id` = ? ; ";
	
	

	private PersonaDAO() {
		super();
	}
	
	public static PersonaDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PersonaDAO();
		}
		return INSTANCE;
	}

	@Override
	public List<Persona> findAll() {

		ArrayList<Persona> regitros = new ArrayList<Persona>();		

		try (Connection con = ConnectionManager.open();
			 PreparedStatement pst = con.prepareStatement(SQL_FIND_ALL);
		     ResultSet rs = pst.executeQuery();
		){

			while (rs.next()) {
				
				Persona p = new Persona();
				p.setId( rs.getInt("id"));
				p.setNombre(rs.getNString("nombre"));
				p.setNif(rs.getString("nif"));
				// TODO resto de campos
				
				regitros.add(p);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return regitros;
	}

	@Override
	public Persona findById(int id) {
		
		Persona p = new Persona();
		
		try (Connection con = ConnectionManager.open();
				 PreparedStatement pst = con.prepareStatement(FIND_BY_ID);
			     ResultSet rs = pst.executeQuery();
			){

				while (rs.next()) {
					
				
					p.setId( rs.getInt("id"));
					p.setNombre(rs.getNString("nombre"));
					p.setNif(rs.getString("nif"));
					// TODO resto de campos
					
					
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return p;
		
	}

	@Override
	public void update(Persona pojo) throws Exception {


		try (Connection con = ConnectionManager.open();
				 PreparedStatement pst = con.prepareStatement(UPDATE);
			     ResultSet rs = pst.executeQuery();
				Scanner sc = new Scanner(System.in);
			){

		
		
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

		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void insert(Persona pojo) throws Exception {
		
		
		try (Connection con = ConnectionManager.open();
				 PreparedStatement pst = con.prepareStatement(INSERT);
			    
				Scanner sc = new Scanner(System.in);
			){
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

	@Override
	public boolean delete(int id) throws Exception {
		
		System.out.println("Buscar Personas por Id");
		final String SQL = "DELETE FROM  `persona` WHERE id = ? ; ";
		
		
		try (Connection con = ConnectionManager.open();
				 PreparedStatement pst = con.prepareStatement(DELETE);
			    
				Scanner sc = new Scanner(System.in);
			){
		
		System.out.println("Intoroduce tu id");
		int ident = Integer.parseInt(sc.nextLine());

		pst.setInt(1, ident);

		int affectedRows = pst.executeUpdate();

		String msj = (affectedRows == 1) ? "Persona eliminada" : "No se pudo eliminar!!!";
		System.out.println(msj);

		// bucle para listar

		
	}
		return false;

}
}
