package tp_trois;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress adresse = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Message : ");
                String message = sc.nextLine();
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, adresse,9876);
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
