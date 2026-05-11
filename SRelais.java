package tp_quatre;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SRelais {
    public static void main(String[] args) {
        try {
            ServerSocket serveur =
                    new ServerSocket(1026);
            System.out.println(
                    "Serveur relais démarré..."
            );
            while (true) {
                Socket client = serveur.accept();
                new Thread(() -> {
                    try {
                        BufferedReader in =
                                new BufferedReader(
                                        new InputStreamReader(
                                                client.getInputStream()
                                        )
                                );
                        PrintWriter out =
                                new PrintWriter(
                                        client.getOutputStream(),
                                        true
                                );
                        String ip = in.readLine();
                        try {
                            Socket machine =
                                    new Socket(ip, 1027);
                            BufferedReader rep =
                                    new BufferedReader(
                                            new InputStreamReader(
                                                    machine.getInputStream()
                                            )
                                    );
                            String message =
                                    rep.readLine();
                            out.println(message);
                            machine.close();
                        } catch (Exception e) {
                            out.println(
                                    "Machine inaccessible"
                            );
                        }
                        client.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
