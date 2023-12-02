package default_package;

import java.io.Serializable;

public class Cancion implements Serializable {
    private int id_cancion;
    private String titulo;
    private String artista;
    private String genero;
    private String fecha_lanzamiento;
    private String url_cancion;

    /**
     * Contructor con todos los atributos
     * Utilizado para mostrar X cancion/es
     * 
     * @param id_cancion
     * @param titulo
     * @param artista
     * @param genero
     * @param fecha_lanzamiento
     * @param url_cancion
     */
    public Cancion(int id_cancion, String titulo, String artista, String genero, String fecha_lanzamiento,
            String url_cancion) {
        this.id_cancion = id_cancion;
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.url_cancion = url_cancion;
    }

    /**
     * Constructor sin el atributo id
     * Utilizado para listar todas las canciones
     * 
     * @param titulo
     * @param artista
     * @param genero
     * @param fecha_lanzamiento
     * @param url_cancion
     */
    public Cancion(String titulo, String artista, String genero, String fecha_lanzamiento, String url_cancion) {
        this.titulo = titulo;
        this.artista = artista;
        this.genero = genero;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.url_cancion = url_cancion;
    }

    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n" +
                "| Cancion: " + titulo + "\n" +
                "| Artista: " + artista + "\n" +
                "| Género: " + genero + "\n" +
                "| Fecha de lanzamiento: " + fecha_lanzamiento + "\n" +
                "| Enlace a la canción: " + url_cancion + "\n" +
                "-------------------------------------------------------------------";
    }

}
