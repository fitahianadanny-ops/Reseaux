package tp_deux;


import java.util.Scanner;

//Ecrire une application qui lit des chaines au clavier et les affiche à l’écran (utiliser la classe Scanner). L’arrêt de cette application se fait à la lecture de "stop" au clavier.
public class ServeurStop {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("entrer le texte(taper 'stop' pour arrêter): ");
        while (!(input = scanner.nextLine()).equalsIgnoreCase("stop")) { 
            System.out.println(input);
        }
    }
}
