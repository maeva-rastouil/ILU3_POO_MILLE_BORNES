package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {

    private final Joueur joueurCourant;
    private final Carte carteJouee;
    private final Joueur joueurCible;

    public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
        this.joueurCourant = joueurCourant;
        this.carteJouee = carteJouee;
        this.joueurCible = joueurCible;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Carte getCarteJouee() {
        return carteJouee;
    }

    public Joueur getJoueurCible() {
        return joueurCible;
    }
    
    public boolean estValide() {
        if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
            return joueurCible != null && joueurCible != joueurCourant;
        }

        return joueurCible == null || joueurCible == joueurCourant;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coup)) return false;
        Coup other = (Coup) o;
        return joueurCourant.equals(other.joueurCourant) 
        	&& carteJouee.equals(other.carteJouee)
            && ((joueurCible == null && other.joueurCible == null) || (joueurCible != null && joueurCible.equals(other.joueurCible)));
    }

    @Override
    public int hashCode() {
        int result = joueurCourant.hashCode();
        result = 31 * result + carteJouee.hashCode();
        result = 31 * result + (joueurCible == null ? 0 : joueurCible.hashCode());
        return result;
    }


    @Override
    public String toString() {
        if (joueurCible == null) {
            return "défausse la carte " + carteJouee;
        } else {
            return "dépose la carte " + carteJouee +
                   " dans la zone de jeu de " + joueurCible.getNom();
        }
    }



}

