package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
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

	public ZoneDeJeu() {
		this.pileLimite  = new ArrayList<>() ;
		this.pileBataille = new ArrayList<>() ;
		this.pileBornes = new ArrayList<>() ;
	}

	public int donnerLimitationVitesse() {
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
	}


	public boolean peutAvancer(){
	    if(pileBataille.isEmpty()){ 
	        return false;
	    }
	    Carte sommet = pileBataille.get(pileBataille.size() - 1);

	    //si notre carte sommet est un Feu Vert alors on peut avancer donc ça renvoie true
	    return (sommet instanceof Parade parade && parade.getType() == Type.FEU);
	}
	
	private boolean estDepotFeuVertAutorise(){
	    if (pileBataille.isEmpty()) {
	        return true;
	    }
	    Carte sommet = pileBataille.get(pileBataille.size() - 1);

	    //on return false si la carte est un feu vert
	    if(sommet instanceof Parade parade){
	        return parade.getType() != Type.FEU;
	    }
	    return true; //sinon dans les autres cas c'est autorisé
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

	
	private boolean estDepotLimiteAutorise(Limite limite){
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


	private boolean estDepotBatailleAutorise(Bataille bataille) {
	    if(bataille instanceof Attaque){
	        return !estBloque();
	    }

	    if(bataille instanceof Parade parade){
	        if (parade.getType() == Type.FEU) {
	            return estDepotFeuVertAutorise();
	        }

	        if(pileBataille.isEmpty()) {
	            return false;
	        }
	        Carte sommet = pileBataille.get(pileBataille.size() - 1);

	        return (sommet instanceof Attaque att && att.getType() == parade.getType());
	    }
	    return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
	    if(carte instanceof Borne borne){
	        return estDepotBorneAutorise(borne);
	    }
	    if(carte instanceof Limite limite){
	        return estDepotLimiteAutorise(limite);
	    }
	    if(carte instanceof Bataille bataille){
	        return estDepotBatailleAutorise(bataille);
	    }
	    return false;
	}



}
