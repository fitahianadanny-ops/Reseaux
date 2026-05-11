package tp_deux;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientA {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost: ",1027);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner sc = new Scanner(System.in);
            String message;
            do { 
                System.out.println("Entrer votre message: ");
                message = sc.nextLine();
                out.println(message);
            } while (!message.equals("stop"));
            socket.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
