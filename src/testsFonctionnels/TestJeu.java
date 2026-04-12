package testsFonctionnels;

import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {

    public static void main(String[] args) {
        Jeu jeu = new Jeu();

        Joueur jack = new Joueur("Jack");
        Joueur bill = new Joueur("Bill");
        Joueur luffy = new Joueur("Luffy");
        jeu.inscrire(jack, bill, luffy);
        jeu.distribuerCartes();

        System.out.println("Main de Jack : " + jack.getMain());
        System.out.println("Main de Bill : " + bill.getMain());
        System.out.println("Main de Luffy : " + luffy.getMain());
        System.out.println();


        String trace = jeu.lancer();;
        System.out.println(trace);
    }
}
