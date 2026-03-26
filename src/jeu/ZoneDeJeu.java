package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class ZoneDeJeu {
	private List <Carte> Pilelimite;
	private List <Carte> Pilebataille;
	private List <Carte> Pilebornes;

	public ZoneDeJeu() {
		this.Pilelimite  = new ArrayList<>() ;
		this.Pilebataille = new ArrayList<>() ;
		this.Pilebornes = new ArrayList<>() ;
	}

}
