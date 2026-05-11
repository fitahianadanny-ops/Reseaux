package tp_trois;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
     public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1027);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );
            Scanner sc = new Scanner(System.in);
            // Thread pour recevoir les messages
            Thread recevoir = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Serveur : " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recevoir.start();
            // Envoi des messages
            while (true) {
                String texte = sc.nextLine();
                out.println(texte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
