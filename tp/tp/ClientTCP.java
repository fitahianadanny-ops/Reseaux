package tp;

//paramètre :(1) nom de la machine Serveur / (2) le port d'ecoute du serveur

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientTCP {
    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1"; // localhost
        int port = 5000;
        try (
            Socket socket = new Socket(host, port);
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        ) {
            System.out.print("Entrez un message : ");
            String message = clavier.readLine();
            out.writeBytes(message + "\n");
            String reponse = in.readLine();
            System.out.println("Réponse serveur : " + reponse);
        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }

}
