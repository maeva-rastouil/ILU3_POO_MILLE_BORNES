package jeu;

public class Joueur {
	private String nom;
	private ZoneDeJeu zonedejeu;

	public Joueur(String nom, ZoneDeJeu zonedejeu) {
		this.nom = nom;
		this.zonedejeu = new ZoneDeJeu();
	}
	
	public String getNom() {
		return nom;
	}
	
	public ZoneDeJeu getZonedejeu() {
		return zonedejeu;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if(obj instanceof Joueur joueur) {
	        return nom.equals(joueur.getNom());
	    }
	    return false;
	}
	
	@Override
	public String toString() {
		return nom;
	}

	public void estIdentique(Joueur joueur1, Joueur joueur2) {
		if(joueur1.equals(joueur2)) {
			System.out.println("Les deux joueurs sont identiques \n");
		}
	}


	




}
