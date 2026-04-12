package jeu;

import cartes.*;
import utils.GestionCartes;

import java.util.*;

public class Jeu {

    private Sabot sabot;
    private Set<Joueur> joueurs;
    private Iterator<Joueur> iterateurJoueurs;


    public Jeu() {
        this.joueurs = new LinkedHashSet<>();

        // récupérer le tableau du jeu de cartes
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tableauCartes = jeuDeCartes.donnerCartes();

        // transformer en liste
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, tableauCartes);

        // mélanger
        listeCartes = GestionCartes.melanger(listeCartes);

        // reconvertir en tableau
        Carte[] tableauMelange = listeCartes.toArray(new Carte[0]);

        // créer le sabot
        sabot = new Sabot(tableauMelange);

        // l’itérateur sera initialisé après inscription des joueurs
        iterateurJoueurs = joueurs.iterator();
    }


    public Sabot getSabot() {
        return sabot;
    }
    
    public void inscrire(Joueur... joueurs) {
        for (Joueur j : joueurs) {
            this.joueurs.add(j);
        }
    }

    
    private static final int NBCARTES = 6;

    public void distribuerCartes() {
        for (int i = 0; i < NBCARTES; i++) {
            for (Joueur joueur : joueurs) {
                Carte carte = sabot.piocher();
                joueur.donner(carte);
            }
        }
    }

    public String jouerTour(Joueur joueur) {
        StringBuilder sb = new StringBuilder();

        // 1. Le joueur pioche une carte
        Carte piochee = sabot.piocher();
        joueur.donner(piochee);
        sb.append(joueur.getNom()).append(" pioche ").append(piochee).append("\n");

        // 2. Le joueur choisit un coup
        Coup coup = joueur.choisirCoup(joueurs);
        sb.append(joueur.getNom()).append(" ").append(coup).append("\n");

        // 3. On retire la carte de la main
        joueur.retirerDeLaMain(coup.getCarteJouee());

        // 4. Si pas de cible → défausse
        if (coup.getJoueurCible() == null) {
            sabot.deposer(coup.getCarteJouee());
            sb.append("La carte est défaussée dans le sabot.\n");
        }
        // 5. Sinon → dépôt dans la zone du joueur cible
        else {
            coup.getJoueurCible().getZonedejeu().deposer(coup.getCarteJouee());
            sb.append("La carte est déposée dans la zone de ")
              .append(coup.getJoueurCible().getNom()).append(".\n");
        }

        return sb.toString();
    }

    public Joueur donnerJoueurSuivant() {
        //dans le cas ou on arrive a la fin alors on doit recommencer
        if (!iterateurJoueurs.hasNext()) {
            iterateurJoueurs = joueurs.iterator();
        }

        return iterateurJoueurs.next();
    }

    public String lancer() {
        StringBuilder sb = new StringBuilder();

        boolean partieTerminee = false;

        while (!partieTerminee) {

            //on récupère le joueur suivant
            Joueur joueur = donnerJoueurSuivant();
            sb.append("---- Tour de ").append(joueur.getNom()).append(" ----\n");

            //on joue un tour
            sb.append(jouerTour(joueur)).append("\n");

            //on vérifie si le joueur a gagné
            if (joueur.donnerKmParcourus() >= 1000) {
                sb.append(joueur.getNom()).append(" a atteint 1000 km !\n");
                sb.append("La partie est terminée.\n");
                partieTerminee = true;
            }

            //on vérifie si le sabot est vide
            if (sabot.estVide()) {
                sb.append("Le sabot est vide. La partie s'arrête.\n");
                partieTerminee = true;
            }
        }

        return sb.toString();
    }



}
