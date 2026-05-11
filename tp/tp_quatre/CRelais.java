package tp_quatre;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class CRelais {
     public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1026);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Adresse IP : ");
            String ip = scanner.nextLine();
            out.println(ip);
            String reponse = in.readLine();
            System.out.println(
                    "Réponse : " + reponse
            );
            socket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
