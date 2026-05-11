package tp;

//paramètrre de port d'ecoute TCP

import java.io.*;
import java.net.*;


public class ServeurTCP {
    public static void main(String[] args) throws Exception {
        int port = 5000; // port fixe
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur démarré sur localhost:" + port);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connecté : " + client.getInetAddress());
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                String message = in.readLine();
                System.out.println("Reçu : " + message);
                String reponse = message.toUpperCase() + "\n";
                out.writeBytes(reponse);
                client.close();
            }
        } catch (Exception e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }

}
