package default_package;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.mysql.fabric.xmlrpc.base.Array;

import java.util.ArrayList;

public class Cliente {
    public static void main(String[] args) {
        System.out.println(" APLICACIÓN CLIENTE");
        System.out.println("-----------------------------------");
        Scanner lector = new Scanner(System.in);
        try {
            Socket cliente = new Socket();
            InetSocketAddress direccionServidor = new InetSocketAddress("localhost", 2000);
            System.out.println("Esperando a que el servidor acepte la conexión");
            cliente.connect(direccionServidor);
            System.out.println("Comunicación establecida con el servidor");
            InputStream entrada = cliente.getInputStream();
            OutputStream salida = cliente.getOutputStream();
            ObjectInputStream entradaObjeto = new ObjectInputStream(cliente.getInputStream());
            String opc = "";
            String opcBuscar = "";
            String valorBuscar = "";
            ArrayList<Cancion> listaCanciones = new ArrayList<>();
            boolean salir = false;
            while (salir == false) {
                System.out.println("1: Buscar Cancion\n"
                        + "2: Listar todas las canciones\n"
                        + "3: Salir del programa");
                opc = lector.nextLine();
                salida.write(opc.getBytes());
                if (opc.equals("3")) {
                    salir = true;
                    System.out.println("Has salido del programa");
                    entrada.close();
                    salida.close();
                    cliente.close();
                    System.out.println("Comunicación cerrada");
                    break;
                }
                if (opc.equals("1")) {
                    // Pregunta al cliente el criterio de la búsqueda
                    // Bucle por si la opcion de opcbuscar es inválida o válida
                    while (true) {
                        System.out.println("titulo: Buscar por titulo \n" + "artista: Buscar por artista");
                        opcBuscar = lector.nextLine();
                        if (opcBuscar.equals("titulo") || opcBuscar.equals("artista")) {
                            break;
                        } else {
                            System.out.println("Opción inválida. Por favor, elige 'titulo' o 'artista'.");
                        }
                    }

                    // Pregunta al cliente el valor de la búsqueda
                    System.out.println("Valor de la búsqueda: ");
                    valorBuscar = lector.nextLine();

                    // Da salida a las variabes opcBuscar y valorBuscar
                    salida.write(opcBuscar.getBytes());
                    salida.write(valorBuscar.getBytes());

                    try {
                        // Recibe la lista y la recorre para mostrar cada dato por pantalla
                        listaCanciones = (ArrayList<Cancion>) entradaObjeto.readObject();
                        for (Cancion mensajeFinal : listaCanciones) {
                            System.out.println(mensajeFinal);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                if (opc.equals("2")) {
                    try {
                        // Recibe la lista y la recorre para mostrar cada dato por pantalla
                        listaCanciones = (ArrayList<Cancion>) entradaObjeto.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Cancion mensajeFinal : listaCanciones) {
                        System.out.println(mensajeFinal);
                    }
                }

            }

        } catch (UnknownHostException e) {
            System.out.println("No se puede establecer comunicación con el servidor");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
            System.out.println(e.getMessage());
        }
        lector.close();
    }
}
