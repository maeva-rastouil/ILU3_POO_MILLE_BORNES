package testsFonctionnels;

import cartes.*;

public class TestJeuDeCartes {

    public static void main(String[] args) {

        JeuDeCartes jeu = new JeuDeCartes();

        System.out.println(jeu.affichageJeuDeCartes());

        System.out.println("Configuration correcte ? " + jeu.checkCount());
    }
}
