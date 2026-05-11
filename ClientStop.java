package tp_deux;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientStop {
    public static void main(String[] args) throws  Exception {
        String host = "127.0.0.1";
        int port = 5001;
        try(
            Socket socket = new Socket(host, port);
            BufferedReader clavier = new  BufferedReader(new  InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        )
        {
            System.out.println("entrer le message: ");
            String message = clavier.readLine();
            out.writeBytes(message+"\n");
            String reponse = in.readLine();
            System.out.println("Reponce du serveur: "+reponse);

        } catch (Exception e) {
            System.err.println("Erreur: "+e.getMessage());
        }
    }
}
