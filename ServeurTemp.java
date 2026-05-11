package tp_quatre;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;

/*
Modifier le client et le serveur pour qu’à partir de la première requête du client le serveur
envoie régulièrement un message au client. L’arrêt de la communication, si les machines
sont actives se fait à l’initiative du client. Le serveur doit donc être capable de gérer
plusieurs clients simultanément. 
*/

public class ServeurTemp {
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1027);
            System.out.println("Serveur démarré..." );
            while (true) {
                Socket socket = serveur.accept();
                new Thread(() -> {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true );
                        while (true) {
                            out.println("Heure : " + LocalTime.now());
                            Thread.sleep(60000);
                            
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "Client déconnecté"
                        );
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
