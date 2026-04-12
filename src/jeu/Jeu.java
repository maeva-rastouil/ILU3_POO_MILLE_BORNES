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
            Joueur joueur = donnerJoueurSuivant();
            sb.append("---- Tour de ").append(joueur.getNom()).append(" ----\n");

            sb.append(jouerTour(joueur)).append("\n");

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

        //classement final
        sb.append("\n=== Classement final ===\n");
        List<Joueur> classement = classement();

        for (int i = 0; i < classement.size(); i++) {
            Joueur j = classement.get(i);
            sb.append((i + 1)).append(". ")
              .append(j.getNom())
              .append(" - ")
              .append(j.donnerKmParcourus())
              .append(" km\n");
        }

        //si le sabot est vide alors on détermine rle vainqueue
        Joueur vainqueur = classement.get(0);
        sb.append("\nVainqueur : ").append(vainqueur.getNom()).append("\n");

        return sb.toString();
    }

    
    public List<Joueur> classement() {
        //on utilise un comparator pour trier les km en ordre croissant
        Comparator<Joueur> comp = new Comparator<Joueur>() {
        	
            @Override
            public int compare(Joueur j1, Joueur j2) {
                int diff = j2.donnerKmParcourus() - j1.donnerKmParcourus();
                
                // Si égalité de km, on compare les noms pour éviter les doublons dans le TreeSet
                if (diff == 0) {
                    return j1.getNom().compareTo(j2.getNom());
                }
                return diff;
            }
        };

        //Treeset
        Set<Joueur> classement = new TreeSet<>(comp);

        //on ajoute tout les joueurs
        classement.addAll(joueurs);

        return new ArrayList<>(classement);
    }




}
