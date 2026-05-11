package tp_quatre;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

/* On veut créer une application client/serveur qui permette de savoir quelles sont les
machines qui sont actives. Cette application utilise des sockets TCP.
• Le serveur attend une requête du client sur le port 1027 et envoie un message (par
exemple contenant l’heure locale).
• Le client lit une adresse au clavier et envoie une requête au serveur du site pour savoir
si la machine correspondante est active. Dans l’affirmative, il affiche le message envoyé
par le serveur. */

public class ServeurAct {
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1027);
            System.out.println("Serveur actif...");
            while (true) {
                Socket socket = serveur.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Machine active - Heure : " + LocalTime.now());
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
