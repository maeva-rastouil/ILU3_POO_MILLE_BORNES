package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Type;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List <Carte> pileLimite;
	private List <Carte> pileBataille;
	private List <Carte> pileBornes;
	private Set <Botte> ensembleBotte;

	public ZoneDeJeu() {
		this.pileLimite  = new ArrayList<>() ;
		this.pileBataille = new ArrayList<>() ;
		this.pileBornes = new ArrayList<>() ;
		this.ensembleBotte = new HashSet<>();
	}
	
	public Set<Botte> getBottes() {
	    return ensembleBotte;
	}
	
	public Carte getSommetBataille() {
	    if (pileBataille.isEmpty()) {
	        return null;
	    }
	    return pileBataille.get(pileBataille.size() - 1);
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
	    Carte sommet = pileLimite.get(pileLimite.size() - 1);

	    //cas ou le sommet est une carte fin de limitation
	    if (sommet instanceof FinLimite) {
	        return 200;
	    }
	    return 50;
	}
	
	public boolean estLimiteVitesse() {
	    return donnerLimitationVitesse() == 50;
	}

	
	public int donnerKmParcourus(){
	    int total = 0;

	    for(Carte carte : pileBornes) {
	        if(carte instanceof Borne borne){
	            total += borne.getKm(); 
	        }
	    }
	    return total;
	}
	
	public void deposer(Carte cartetype){
		if(cartetype instanceof Borne){
	        pileBornes.add(cartetype);
	    }
	    else if(cartetype instanceof Limite){
	        pileLimite.add(cartetype);
	    }
	    else if(cartetype instanceof Bataille){
	        pileBataille.add(cartetype);
	    }
	    else if (cartetype instanceof Botte botte) {
	        ensembleBotte.add(botte);
	    }
	}


	public boolean peutAvancer() {
	    if (pileBataille.isEmpty()) {
	        return estPrioritaire();
	    }

	    Carte sommet = pileBataille.get(pileBataille.size() - 1);
	    //le cas ou le sommet est un feu vert
	    if (sommet instanceof Parade parade && parade.getType() == Type.FEU) {
	        return true;
	    }

	    //le cas ou le sommet est une parade
	    if (sommet instanceof Parade) {
	        return estPrioritaire();
	    }

	    //le cas ou le sommet est un attaque de type feu
	    if (sommet instanceof Attaque att && att.getType() == Type.FEU) {
	        return estPrioritaire();
	    }

	    //autre cas
	    if (sommet instanceof Attaque att2) {
	        boolean aLaBotteCorrespondante = ensembleBotte.stream().anyMatch(b -> b.getType() == att2.getType());

	        return aLaBotteCorrespondante; 
	    }
	    return false;
	}

	
	private boolean estDepotFeuVertAutorise(){
	    //le cas ou le joueur est prioritaire
	    if (estPrioritaire()){
	        return false;
	    }

	    //le cas ou la pile est vide
	    if (pileBataille.isEmpty()){
	        return true;
	    }

	    Carte sommet = pileBataille.get(pileBataille.size() - 1);

	    //le cas ou le sommet est un feu rouge
	    if (sommet instanceof Attaque att && att.getType() == Type.FEU){
	        return true;
	    }

	    //le cas ou le sommet est une parade dufférebte d'un feu vert
	    if (sommet instanceof Parade parade && parade.getType() != Type.FEU){
	        return true;
	    }

	    //le cas ou le sommet est un autre type de cas
	    if (sommet instanceof Attaque att2){
	        boolean aLaBotteCorrespondante =
	            ensembleBotte.stream().anyMatch(b -> b.getType() == att2.getType());

	        if (aLaBotteCorrespondante) {
	            return true;
	        }
	    }
	    return false;
	}

	
	private boolean estBloque(){
	    if (pileBataille.isEmpty()) {
	        return true;
	    }
	    Carte sommet = pileBataille.get(pileBataille.size() - 1);

	    // si le sommet est une attaque alors le joueur est bloqué
	    return sommet instanceof Attaque;
	}

	private boolean estDepotBorneAutorise(Borne borne){
	    return !estBloque()
	        && borne.getKm() <= donnerLimitationVitesse()
	        && donnerKmParcourus() + borne.getKm() <= 1000;
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



	private boolean aBotteCorrespondante(Bataille bataille) {
	    return ensembleBotte.stream().anyMatch(b -> b.getType() == bataille.getType());
	}

	
	private boolean estDepotBatailleAutorise(Bataille bataille){
		if (bataille instanceof Attaque && aBotteCorrespondante(bataille)) {
		    return false;
		}


	    if (bataille instanceof Attaque) {
	        return !estBloque();
	    }

	    if (bataille instanceof Parade parade) {
	        if (parade.getType() == Type.FEU) {
	            return estDepotFeuVertAutorise();
	        }

	        if (pileBataille.isEmpty()) {
	            return false;
	        }

	        Carte sommet = pileBataille.get(pileBataille.size() - 1);

	        return (sommet instanceof Attaque att && att.getType() == parade.getType());
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



	public boolean estPrioritaire() {
	    for (Botte b : ensembleBotte) {
	        if (b.getType() == Type.FEU){ 
	            return true;
	        }
	    }
	    return false;
	}


}
