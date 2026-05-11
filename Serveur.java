package tp_trois;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {
    
    // Liste des clients connectés
    static ArrayList<PrintWriter> clients = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1027);
            System.out.println("Serveur démarré sur le port 1027...");
            while (true) {
                Socket socket = serveur.accept();
                System.out.println("Nouveau client connecté");
                ClientHandler client = new ClientHandler(socket);
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Classe Thread pour gérer chaque client
    static class ClientHandler extends Thread {
        Socket socket;
        BufferedReader in;
        PrintWriter out;
        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                out = new PrintWriter(
                        socket.getOutputStream(),
                        true
                );
                synchronized (clients) {
                    clients.add(out);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message reçu : " + message);
                    synchronized (clients) {
                        for (PrintWriter client : clients) {
                            client.println(message);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Client déconnecté");
            } finally {
                try {
                    synchronized (clients) {
                        clients.remove(out);
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
