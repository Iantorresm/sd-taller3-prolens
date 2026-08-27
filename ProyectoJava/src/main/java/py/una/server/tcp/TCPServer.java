package py.una.server.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import py.una.entidad.Pedido;
import py.una.entidad.PedidoJSON;
import py.una.bd.PedidoDAO;

public class TCPServer {
    public static void main(String[] args) {
        int puerto = 4445;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP Óptica Prolens escuchando en el puerto " + puerto + "...");

            PedidoDAO pedidoDAO = new PedidoDAO();

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String datoRecibido = in.readLine();
                    System.out.println("\n[Prolens] Actualización logística recibida: " + datoRecibido);

                    if (datoRecibido != null) {
                        // 1. Convertir JSON a entidad
                        Pedido pedidoActualizado = PedidoJSON.stringObjeto(datoRecibido);

                        // 2. Actualizar estado en BD
                        pedidoDAO.actualizarEstado(pedidoActualizado.getIdPedido(), pedidoActualizado.getEstado());

                        System.out.println("[Prolens] Estado del pedido " + pedidoActualizado.getIdPedido()
                                + " actualizado a: " + pedidoActualizado.getEstado());

                        // 3. Confirmar recepción al sistema logístico
                        out.println("{\"mensaje\": \"Estado actualizado correctamente en e-vision\"}");
                    }

                } catch (Exception e) {
                    System.err.println("Error procesando actualización: " + e.getMessage());
                }
            }
        } catch (Exception ex) {
            System.err.println("Error crítico en el puerto " + puerto + ": " + ex.getMessage());
        }
    }
}