package tp_deux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurB {
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1027);
            System.out.println("Serveur en attente.........");
            Socket socket = serveur.accept();
            System.out.println("Client connecté!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) { 
                System.out.println("Message réçu: "+message);
                break;
            }
            socket.close();
            serveur.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
