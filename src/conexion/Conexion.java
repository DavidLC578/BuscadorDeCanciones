package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import default_package.Cancion;

public class Conexion {

	private final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/musica?useSSL=False";
	private final String USUARIO = "";
	private final String CLAVE = "";

	public Connection conectar() {
		Connection conexion = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			// System.out.println("Conexión OK");

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}

	/**
	 * Función que retorna la lista de las canciones guardadas recorriendo la tabla
	 * de la base de datos
	 * 
	 * @param criterioBusqueda
	 * @param valorBusqueda
	 * @return
	 */
	public ArrayList<Cancion> buscarCancion(String criterioBusqueda, String valorBusqueda) {
		String consulta = "SELECT * FROM canciones WHERE " + criterioBusqueda + " LIKE ?";
		ArrayList<Cancion> listaCanciones = new ArrayList<>();

		try {
			PreparedStatement statement = conectar().prepareStatement(consulta);
			statement.setString(1, "%" + valorBusqueda + "%");
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				int id_cancion = resultados.getInt("id_cancion");
				String nombreCancion = resultados.getString("titulo");
				String artista = resultados.getString("artista");
				String genero = resultados.getString("genero");
				String fecha_lanzamiento = resultados.getString("fecha_lanzamiento");
				String url_cancion = resultados.getString("url_cancion");
				Cancion cancion = new Cancion(id_cancion, nombreCancion, artista, genero, fecha_lanzamiento,
						url_cancion);

				listaCanciones.add(cancion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCanciones;

	}

	/**
	 * Función que retorna la lista de canciones que contiene todas las canciones
	 * ordenadas por fecha de lanzamiento gracias a recorrer la consulta a la base
	 * de datos
	 * 
	 * @return
	 */
	public ArrayList<Cancion> listaCancion() {
		String consulta = "select titulo, artista, genero, fecha_lanzamiento, url_cancion from canciones order by fecha_lanzamiento asc";
		Cancion cancion;
		ArrayList<Cancion> listaCanciones = new ArrayList<>();

		try {

			PreparedStatement statement = conectar().prepareStatement(consulta);
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				String nombreCancion = resultados.getString("titulo");
				String artista = resultados.getString("artista");
				String genero = resultados.getString("genero");
				String fecha_lanzamiento = resultados.getString("fecha_lanzamiento");
				String url_cancion = resultados.getString("url_cancion");
				cancion = new Cancion(nombreCancion, artista, genero, fecha_lanzamiento, url_cancion);
				listaCanciones.add(cancion);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaCanciones;
	}

	public void main(String[] args) {
		// System.out.println(buscarCancion("titulo", "love"));
	}
}
