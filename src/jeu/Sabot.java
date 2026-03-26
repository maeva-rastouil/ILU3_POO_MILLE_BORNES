package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	
    private Carte[] tableauSabot;
    private int nbCartes;
    private int compteur = 0;

    
    public Sabot(Carte[] tableauSabot) { //le constructeur nous fournit un tableau
        this.tableauSabot = tableauSabot;
        this.nbCartes = tableauSabot.length; //initialement le nb de carte dans le tableau_sabot
    }

    public int getNbCartes() {
        return nbCartes;
    }

    public Carte[] getTableauSabot() {
        return tableauSabot;
    }
    
    public boolean estVide() {
        return nbCartes == 0;
    }
    
    public void ajouterCarte(Carte carte){
    	if(nbCartes > tableauSabot.length) {
    		throw new IllegalStateException("Le sabot est plein");
    	}
    	
    	tableauSabot[nbCartes] = carte;
    	nbCartes++;
    	compteur++;
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
            verifierConcurrence();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            canRemove = true;
            return tableauSabot[index++];
        }

		private void verifierConcurrence() {
			if (expectedModCount != compteur) {
                throw new ConcurrentModificationException();
            }
		}

        @Override
        public void remove() {
            verifierConcurrence();
            if (!canRemove) {
                throw new IllegalStateException();
            }
            for (int i = index - 1; i < nbCartes - 1; i++) {
                tableauSabot[i] = tableauSabot[i + 1];
            }
            nbCartes--;
            index--;
            compteur++;
            expectedModCount++;
            canRemove = false;
        }
    }

}
