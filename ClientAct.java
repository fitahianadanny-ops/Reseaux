package tp_quatre;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientAct {
    /*
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Adresse IP : ");
            String ip = sc.nextLine();
            Socket socket = new Socket(ip, 1027);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            System.out.println(message);
            socket.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Machine inactive");
        }
    }*/

    /*Modifier le client pour qu’il puisse traiter soit une machine soit un ensemble de
machines (l’utilisateur donnera une adresse et 25 machines depuis cette adresse
seront examinées). */

    
     public static void main(String[] args) {
        String base = "192.168.1.";
        for (int i = 1; i <= 25; i++) {
            String ip = base + i;
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, 1027), 200);
                System.out.println(ip + " active");
                socket.close();
            } catch (Exception e) {
                System.out.println(ip + " inactive");
            }
        }
    }
}
