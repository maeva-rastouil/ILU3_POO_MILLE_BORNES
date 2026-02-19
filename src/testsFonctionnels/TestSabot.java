package testsFonctionnels;

import java.util.Iterator;

import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Botte;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {

	public static void main(String[] args) throws Exception {
        JeuDeCartes jeu = new JeuDeCartes();
        Carte[] toutesLesCartes = jeu.donnerCartes();
        Sabot sabot = new Sabot(toutesLesCartes);

        // piocher une carte avant la boucle (évite débordement)
        sabot.piocher();

        Iterator<Carte> it = sabot.iterator();

        while (it.hasNext()) {
            Carte carte = it.next();

            System.out.println("je pioche " + carte);

            // cas 1 : appel à piocher pendant l’itération
            sabot.piocher(); // → doit lever ConcurrentModificationException

            // cas 2 : ajout d’une carte pendant l’itération
            sabot.ajouterCarte(new Botte(Type.ACCIDENT)); // As du volant
        }
    }

}
