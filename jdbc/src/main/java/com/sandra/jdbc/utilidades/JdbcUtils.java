package com.sandra.jdbc.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sandra.jdbc.entidades.Tabla1;

public class JdbcUtils {
	final static String URI = "jdbc:mysql://localhost:3306/jdbc";
	final static String USER = "root";
	final static String PASSWORD = "";
	
	public static Connection con;
	public static Statement st;
	public static PreparedStatement ps;
	public static ResultSet rs;
	
	/**
	 * Método que conecta con la base de datos.
	 * @return True si conecta correctamente, False si falla la conexión
	 */
	public static boolean conexion() {
    	con = null;
    	try {
    		con = DriverManager.getConnection(URI, USER, PASSWORD);
    		return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
		}
    	return false;
    }
	
	/**
	 * Método que desconecta con la base de datos.
	 * @return True si desconecta correctamente, False si falla la desconexión
	 */
	public static boolean desconexion() {
		try {
			if(!con.isClosed()) {
				con.close();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void selectBasica() {
		String sql = "SELECT * FROM tabla1";
		int registros = 0; // En esta variable iremos llevando la cuenta de los resultados
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				registros++;
				System.out.println("ID: "+rs.getInt("id")+"\nNombre: "+rs.getString("nombre")); // Por nombre de columna
				// System.out.println("ID: "+rs.getInt(1)+"\nNombre: "+rs.getString(2)); // Por orden de las columnas
			}
			System.out.println("El número de resultados es "+registros);
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Tabla1> devolverRegistrosTabla1() {
		List<Tabla1> resultado = new ArrayList<Tabla1>();
		String sql = "SELECT * FROM tabla1";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				resultado.add(new Tabla1(rs.getInt("id"), rs.getString("nombre")));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Dada una sentencia sql devuelve un ResultSet con el contenido de los datos solicitados
	 * @param sql. Sentencia SELECT que solicita información
	 * @return ResultSet con los resultados o NULL si la sql es incorrecta
	 */
	public static ResultSet devolverResultSet(String sql) {
		try {
			st = con.createStatement();
			return st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Dada una sentencia de modificación de datos (Insert, Update o Delete)
	 * la ejecuta sobre la Base de Datos y me devuelve el número de registros afectados
	 * @param sql Sentencia de modificación
	 * @return Número de registros afectados. En caso de error devolveré -1.
	 */
	public static int statementDML(String sql) {
		try {
			st = con.createStatement();  
			return st.executeUpdate(sql);   			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public static void ejemploPreparedStatement() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduzca el nombre de la persona a borrar: ");
		String nombre = sc.nextLine();
		try {
			ps = con.prepareStatement("DELETE FROM tabla1 WHERE nombre LIKE ?");
			ps.setString(1, nombre);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sc.close();
	}
	
	/**
	 * Dada una sql y una lista de Objetos con sus parámetros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados al ejecutar la Select
	 * @param sql String con la SQL con las '?' para que puedan bindeadas (enlazadas)
	 * @param parametros Lista con los parámetros
	 * @return ResultSet de la respuesta de la Select y null en caso de error
	 */
	public static ResultSet devolverResultSetPreparedStatement(String sql, List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?')) return null; // Esto significa que no coinciden los parámetros con el número de parámetros esperado
		try {
			ps = con.prepareStatement(sql);
			for(int i=1; i<=parametros.size(); i++) {
				ps.setObject(i, parametros.get(i-1));
			}
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Dada una sql y una lista de Objetos con sus parámetros a cambiar por las '?'
	 * nos devuelve el ResultSet de los resultados al ejecutar la Select
	 * @param sql String con la SQL con las '?' para que puedan bindeadas (enlazadas)
	 * @param parametros Lista con los parámetros
	 * @return número de registros afectados, -1 en caso de error
	 */
	public static int PreparedStatementDML(String sql, List<Object> parametros) {
		if(parametros.size()!=countMatches(sql, '?')) return -1; // Esto significa que no coinciden los parámetros con el número de parámetros esperado
		try {
			ps = con.prepareStatement(sql);
			for(int i=1; i<=parametros.size(); i++) {
				ps.setObject(i, parametros.get(i-1));
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private static int countMatches(String sql, char caracterBuscado) {
		return (int)sql.chars().filter(e->e==caracterBuscado).count();
	}
}
