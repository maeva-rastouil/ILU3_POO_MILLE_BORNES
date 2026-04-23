package jeu;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Type;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List <Carte> pileLimite= new LinkedList<>();
	private List <Carte> pileBataille = new LinkedList<>();
	private List <Carte> pileBornes = new LinkedList<>();
	private Set <Botte> ensembleBotte = new HashSet<>();
	
	public Set<Botte> getBottes() {
	    return ensembleBotte;
	}
	
	public Carte getSommetBataille() {
	    if (pileBataille.isEmpty()) {
	        return null;
	    }
	    return pileBataille.get(pileBataille.size() - 1);
	}
	
	public boolean estPrioritaire() {
	    return ensembleBotte.contains(Cartes.PRIORITAIRE);
	}

	public int donnerLimitationVitesse() {
		//si le joueur à la carte botte véhicule prioritaire alors il n'y a pas de limitation
		if (estPrioritaire()) {
	        return 200;
	    }
		
		if (pileLimite.isEmpty()){ 
	        return 200;
	    }
		//on initialise le sommet qui est la dernière carte de la pile
	    Carte sommet = pileLimite.get(0);

	    //cas ou le sommet est une carte fin de limitation
	    if (sommet instanceof FinLimite) {
	        return 200;
	    }
	    return 50;
	}
	
	public boolean estLimiteVitesse() {
	    return donnerLimitationVitesse() == 50;
	}
	
	public void deposer(Carte cartetype){
		if(cartetype instanceof Borne){
	        pileBornes.add(0, cartetype);
	    }
	    else if(cartetype instanceof Limite){
	        pileLimite.add(0, cartetype);
	    }
	    else if(cartetype instanceof Bataille){
	        pileBataille.add(0, cartetype);
	    }
	    else if (cartetype instanceof Botte botte) {
	        ensembleBotte.add(botte);
	    }
	}


	public boolean peutAvancer() {
		//le cas ou la pile de bataille est vide
	    if (pileBataille.isEmpty()) {
	        return estPrioritaire();
	    }

	    Carte sommet = pileBataille.get(0);
	    //le cas ou le sommet est un feu vert
	    if (sommet instanceof Parade parade && parade.getType() == Type.FEU) {
	        return true;
	    }

	    //le cas ou le sommet est une parade et il est prioritaire
	    if (sommet instanceof Parade) {
	        return estPrioritaire();
	    }

	    //le cas ou le sommet est un attaque de type feu et il est prioritaire
	    if (sommet instanceof Attaque att && att.getType() == Type.FEU) {
	        return estPrioritaire();
	    }
	    
	    return sommet.equals(Cartes.FEU_VERT);
	}

	
	private boolean estDepotFeuVertAutorise(){
	    Carte sommet = pileBataille.get(0);

	    //le cas ou le sommet est un feu rouge et le cas ou le sommet est une parade dufférebte d'un feu vert
	    return pileBataille.isEmpty() || sommet instanceof Attaque att && att.getType() == Type.FEU || sommet instanceof Parade parade && parade.getType() != Type.FEU;
	}
	
	public int donnerKmParcours() {
		int total = 0;
		for(Carte carte : pileBornes) {
			if(carte instanceof Borne borne) {
				total += borne.getKm();
			}
		}
		return total;
		
	}


	private boolean estDepotBorneAutorise(Borne borne){
	    return (peutAvancer() && (borne.getKm() <= donnerLimitationVitesse()) && (donnerKmParcours() + borne.getKm() <= 1000));
	}

	
	private boolean estDepotLimiteAutorise(Limite limite) {
	    if (estPrioritaire()) {
	        return false;
	    }

	    if (pileLimite.isEmpty()) {
	        return limite instanceof DebutLimite;
	    }
	    Carte sommet = pileLimite.get(pileLimite.size() - 1);

	    if (limite instanceof DebutLimite) {
	        return sommet instanceof FinLimite;
	    }

	    if (limite instanceof FinLimite) {
	        return sommet instanceof DebutLimite;
	    }
	    return false;
	}
	
	

	
	private boolean estDepotBatailleAutorise(Bataille bataille){
		boolean contenirBotte = ensembleBotte.contains(new Botte(bataille.getType()));
		
		if (contenirBotte) {
		    return false;
		}


	    if (bataille instanceof Attaque) {
	        return peutAvancer();
	    }

	    if (bataille instanceof Parade parade) {
	        if (parade.equals(Cartes.FEU_VERT)) {
	            return estDepotFeuVertAutorise();
	        }

	        if (pileBataille.isEmpty()) {
	            return false;
	        }

	        Carte sommet = pileBataille.get(0);

	        return (sommet instanceof Attaque att && att.getType().equals(parade.getType()));
	    }
	    
	    return false;
	}



	public boolean estDepotAutorise(Carte carte) {
	    if (carte instanceof Botte) {
	        return true;
	    }
	    if (carte instanceof Borne borne) {
	        return estDepotBorneAutorise(borne);
	    }
	    if (carte instanceof Limite limite) {
	        return estDepotLimiteAutorise(limite);
	    }
	    if (carte instanceof Bataille bataille) {
	        return estDepotBatailleAutorise(bataille);
	    }
	    return false;
	}



	


}
