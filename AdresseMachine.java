package tp_quatre;

import java.net.InetAddress;
import java.util.Scanner;
/*
Ecrire une application qui affiche l’adresse internet de la machine sur laquelle vous
travaillez puis lit au clavier un nom et affiche à l’écran l’adresse internet correspondante.
L’arrêt de cette application se fait à la lecture de "stop" au clavier. Pour lire au clavier vous
pouvez utiliser la classe Scanner. 
*/

public class AdresseMachine {
     public static void main(String[] args) {
        try {
            // Machine locale
            InetAddress locale = InetAddress.getLocalHost();
            System.out.println("Nom machine : " + locale.getHostName());
            System.out.println("Adresse IP : " + locale.getHostAddress());
            Scanner sc = new Scanner(System.in);
            String nom;
            do {
                System.out.print("Entrer un nom de machine : ");
                nom = sc.nextLine();
                if (!nom.equals("stop")) {
                    InetAddress machine = InetAddress.getByName(nom);
                    System.out.println("Adresse IP : "+ machine.getHostAddress());
                }
            } while (!nom.equals("stop"));
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
