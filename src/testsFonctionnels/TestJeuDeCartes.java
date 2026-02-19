package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {

	public static void main(String[] args) {

        JeuDeCartes jeu = new JeuDeCartes();

        String affichage = jeu.affichageJeuDeCartes(); //on appelle la fonction qui retourne le tableau des cartes

        System.out.println(affichage);
    }

}
