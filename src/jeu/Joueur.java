package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zonedejeu;
	private MainJoueur main;

	public Joueur(String nom, ZoneDeJeu zonedejeu) {
		this.nom = nom;
		this.zonedejeu = zonedejeu;
		this.main = new MainJoueur();
	}
	
	public String getNom() {
		return nom;
	}
	
	public ZoneDeJeu getZonedejeu() {
		return zonedejeu;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte carte) {
        main.prendre(carte);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joueur joueur) {
            return nom.equals(joueur.getNom());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Joueur : " + nom + ", Main : " + main;
    }


    public Carte prendreCarte(Sabot sabot) {
    	if (sabot.estVide()) {
            return null;                
        }
    	
    	Carte carte = sabot.piocher();
        main.prendre(carte); //on ajoute la carte qu'on a piocher dans la main du joueur
        return carte;  
    }
    
    public int donnerKmParcourus() {
        return zonedejeu.donnerKmParcourus();
    }

    public boolean estDepotAutorise(Carte carte) {
        return zonedejeu.estDepotAutorise(carte);
    }

}
