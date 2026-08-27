package py.una.server.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import py.una.entidad.Pedido;
import py.una.entidad.PedidoJSON;

public class UDPClient {

    public static void main(String a[]) throws Exception {

        // Datos del servidor (GlobalTrack)
        String ipServidor = "localhost";
        int puertoServidor = 9876;

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ipServidor);

            // 1. Prolens arma su Pedido con el idEnvio que desea consultar
            Pedido pedidoConsulta = new Pedido();
            pedidoConsulta.setIdEnvio(1); // El número de tracking que Prolens guardó al registrar

            // Convertimos la entidad Pedido a JSON
            String datoEnvio = PedidoJSON.objetoString(pedidoConsulta);
            byte[] sendData = datoEnvio.getBytes();

            System.out.println("Óptica Prolens consultando estado: " + datoEnvio);

            // 2. Enviar el paquete a Global Express
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);
            clientSocket.send(sendPacket);

            // 3. Esperar la respuesta del servidor
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // 4. Procesar la respuesta
            String respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Convertir el JSON recibido de vuelta a la entidad Pedido de Prolens
            Pedido pedidoActualizado = PedidoJSON.stringObjeto(respuesta);

            System.out.println("=======================================");
            System.out.println("ACTUALIZACIÓN RECIBIDA EN PROLENS");
            System.out.println("ID Envío (Tracking): " + pedidoActualizado.getIdEnvio());
            System.out.println("Estado actual: " + pedidoActualizado.getEstado());
            System.out.println("=======================================");

            clientSocket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}