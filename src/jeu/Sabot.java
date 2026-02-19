package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
    private Carte[] tableau_sabot;
    private int nbCartes;
    private int compteur;

    
    public Sabot(Carte[] tableau_sabot) { //le constructeur nous fournit un tableau
        this.tableau_sabot = tableau_sabot;
        this.nbCartes = tableau_sabot.length; //initialement le nb de carte dans le tableau_sabot
        this.compteur = 0;
    }

    public int getNbCartes() {
        return nbCartes;
    }

    public Carte[] getTableauSabot() {
        return tableau_sabot;
    }
    
    public boolean estVide() {
        if(nbCartes == 0) {
            return true;
        } else{
            return false;
        }
    }
    
    public void ajouterCarte(Carte carte) throws Exception{
    	if(nbCartes > tableau_sabot.length) {
    		throw new Exception("Le sabot est plein");
    	}
    	
    	tableau_sabot[nbCartes] = carte;
    	nbCartes++;
    }
    
    public Carte piocher() {
        Iterator<Carte> it = iterator(); //on crée un itérateur sur le sabot

        if (!it.hasNext()) { //on vérifie que le sabot est vide
            throw new IllegalStateException("Le sabot est vide");
        }

        Carte carte = it.next(); //on recupère la première carte
        it.remove(); //on supprime la carte du sabot
        return carte; //on retourne la carte piochée
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public Iterator<Carte> iterator() {
        return new SabotIterator();
    }
    
    private class SabotIterator implements Iterator<Carte> {
        private int index = 0;
        private int expectedModCount = compteur;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return index < nbCartes;
        }

        @Override
        public Carte next() {
            if (expectedModCount != compteur) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return tableau_sabot[index++];
        }

        @Override
        public void remove() {
            if (expectedModCount != compteur) {
                throw new ConcurrentModificationException();
            }
            if (!canRemove) {
                throw new IllegalStateException();
            }
            for (int i = index - 1; i < nbCartes - 1; i++) {
                tableau_sabot[i] = tableau_sabot[i + 1];
            }
            nbCartes--;
            index--;
            compteur++;
            expectedModCount++;
            canRemove = false;
        }
    }

}
