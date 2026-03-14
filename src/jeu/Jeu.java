package jeu;

import cartes.*;
import utils.GestionCartes;

import java.util.*;

public class Jeu {

    private Sabot sabot;

    public Jeu() {
        //récupérer le tableau du jeu de cartes
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tableauCartes = jeuDeCartes.donnerCartes();

        //transformer en liste
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, tableauCartes);

        //mélanger
        listeCartes = GestionCartes.melanger(listeCartes);

        //reconvertir en tableau
        Carte[] tableauMelange = listeCartes.toArray(new Carte[0]);

        //créer le sabot
        sabot = new Sabot(tableauMelange);
    }

    public Sabot getSabot() {
        return sabot;
    }
}
