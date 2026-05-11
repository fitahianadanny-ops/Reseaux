package tp_trois;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            byte[] buffer = new byte[1024];
            System.out.println("Serveur UDP démarré...");
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message reçu : " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
