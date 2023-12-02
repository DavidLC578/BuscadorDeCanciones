package default_package;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        System.out.println(" APLICACIÓN DE SERVIDOR ");
        System.out.println("----------------------------------");
        try {
            ServerSocket servidor = new ServerSocket();
            // 192.168.0.19 es la dirección IP del servidor, atenderá las peticiones en el
            // puerto 200
            InetSocketAddress direccion = new InetSocketAddress("localhost", 2000);
            // Vinculamos el servidor con la dirección establecida por el objeto
            // InetSocketAddress
            servidor.bind(direccion);
            System.out.println("Servidor creado y escuchando .... ");
            System.out.println("Dirección IP: " + direccion.getAddress());

            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloEscuchador(enchufeAlCliente);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
