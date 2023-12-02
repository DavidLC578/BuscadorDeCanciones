package default_package;

import java.io.*;

import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;

public class HiloEscuchador implements Runnable {
    private Thread hilo;
    private Socket enchufeAlCliente;

    public HiloEscuchador(Socket cliente) {
        hilo = new Thread(this, "Cliente");
        this.enchufeAlCliente = cliente;

        hilo.start();
    }

    /**
     * 
     */
    @Override
    public void run() {
        Conexion conexion = new Conexion();
        System.out.println("Estableciendo comunicación con " + hilo.getName());
        try {
            InputStream entrada = enchufeAlCliente.getInputStream();
            OutputStream salida = enchufeAlCliente.getOutputStream();
            ObjectOutputStream salidaObjeto = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
            String opc = "";
            String critBusqueda = "";
            String valorBuscar = "";
            boolean salir = false;
            while (salir == false) {
                byte[] opcion = new byte[100];
                entrada.read(opcion);
                opc = new String(opcion).trim();
                switch (opc) {
                    case "1":
                        // Recibe el criterio de búsqueda: titulo o artista
                        byte[] critBusquedabyte = new byte[100];
                        entrada.read(critBusquedabyte);
                        critBusqueda = new String(critBusquedabyte).trim();

                        // Recibe el valor de la búqueda, el título o el artista
                        // Y da salida el ArrayList de la función buscarCancion
                        byte[] valorBuscarbyte = new byte[100];
                        entrada.read(valorBuscarbyte);
                        valorBuscar = new String(valorBuscarbyte).trim();
                        salidaObjeto.writeObject(conexion.buscarCancion(critBusqueda, valorBuscar));

                        break;
                    case "2":
                        // Envía directamente el retorno del resultado de la función de listaCancion
                        salidaObjeto.writeObject(conexion.listaCancion());
                        break;
                    case "3":

                        break;

                }

            }

            entrada.close();
            salida.close();
            enchufeAlCliente.close();
        } catch (

        IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
