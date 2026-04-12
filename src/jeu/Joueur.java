package jeu;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
	
	public Joueur(String nom) {
	    this.nom = nom;
	    this.zonedejeu = new ZoneDeJeu();
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
    
    public Set<Coup> coupsPossibles(Set<Joueur> participants) {
        Set<Coup> ensemble = new HashSet<>();

        for (Carte carte : main) {
            for (Joueur cible : participants) {
                Coup coup = new Coup(this, carte, cible);
                if (coup.estValide()) {
                    ensemble.add(coup);
                }
            }

            Coup coupSabot = new Coup(this, carte, null);
            if (coupSabot.estValide()) {
                ensemble.add(coupSabot);
            }
        }

        return ensemble;
    }
    
    public Set<Coup> coupsDefausse() {
        Set<Coup> ensemble = new HashSet<>();

        for (Carte carte : this.main) {
            Coup coup = new Coup(this, carte, null);
            ensemble.add(coup);
        }

        return ensemble;
    }

    public void retirerDeLaMain(Carte carte) {
        main.jouer(carte);
    }
    
    private Coup choisirAleatoire(Set<Coup> coups) {
        int index = new Random().nextInt(coups.size());
        int i = 0;

        for (Coup coup : coups) {
            if (i == index) {
                return coup;
            }
            i++;
        }
        return null;
    }

    
    public Coup choisirCoup(Set<Joueur> participants) {
        Set<Coup> coups = coupsPossibles(participants);

        if (!coups.isEmpty()) {
            return choisirAleatoire(coups);
        }

        Set<Coup> defausse = coupsDefausse();
        return choisirAleatoire(defausse);
    }

    public String afficherEtatJoueur() {
        StringBuilder sb = new StringBuilder();

        sb.append("Joueur ").append(nom).append(" :\n");
        sb.append("  Bottes : ").append(zonedejeu.getBottes()).append("\n");
        sb.append("  Limitation de vitesse : ");
        sb.append("  Bataille (sommet) : ").append(zonedejeu.getSommetBataille()).append("\n");
        sb.append("  Main : ").append(main).append("\n");

        return sb.toString();
    }

  



}
