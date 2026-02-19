package cartes;

public class JeuDeCartes { 
	private Configuration[] typesDeCartes;
	
	public JeuDeCartes() {
        typesDeCartes = new Configuration[19];

        typesDeCartes[0] = new Configuration(new Borne(25), 10);
        typesDeCartes[1] = new Configuration(new Borne(50), 10);
        typesDeCartes[2] = new Configuration(new Borne(75), 10);
        typesDeCartes[3] = new Configuration(new Borne(100), 12);
        typesDeCartes[4] = new Configuration(new Borne(200), 4);

        typesDeCartes[5] = new Configuration(new FinLimite(), 6);
        typesDeCartes[6] = new Configuration(new DebutLimite(), 4);

        typesDeCartes[7] = new Configuration(new Attaque(Type.FEU), 5);
        typesDeCartes[8] = new Configuration(new Attaque(Type.ESSENCE), 3);
        typesDeCartes[9] = new Configuration(new Attaque(Type.CREVAISON), 3);
        typesDeCartes[10] = new Configuration(new Attaque(Type.ACCIDENT), 3);

        typesDeCartes[11] = new Configuration(new Parade(Type.FEU), 14);
        typesDeCartes[12] = new Configuration(new Parade(Type.ESSENCE), 6);
        typesDeCartes[13] = new Configuration(new Parade(Type.CREVAISON), 6);
        typesDeCartes[14] = new Configuration(new Parade(Type.ACCIDENT), 6);

        typesDeCartes[15] = new Configuration(new Botte(Type.FEU), 1);
        typesDeCartes[16] = new Configuration(new Botte(Type.ESSENCE), 1);
        typesDeCartes[17] = new Configuration(new Botte(Type.CREVAISON), 1);
        typesDeCartes[18] = new Configuration(new Botte(Type.ACCIDENT), 1);

    }
	
	public String affichageJeuDeCartes() {
        String resultat = "JEU :\n";

        for (Configuration config : typesDeCartes) {
            resultat = resultat + config.getNbExemplaires() + " " + config.getCarte().toString() + "\n";
        }

        return resultat;
    }
	
	public Carte[] donnerCartes() {
		//on calcule d'abord le nombre total de cartes dans le tableau
		int total = 0;
		for (Configuration config : typesDeCartes) {
			total = total + config.getNbExemplaires();
		}
		
		//on crée le tableau qu'on va retourner de taille du total
		Carte[] tableau_resultat = new Carte[total];
		
		//on doit remplir le tableau avec toutes les cartes
		int indice_tableau = 0;
		for(Configuration config : typesDeCartes) {
			Carte carte_actuelle = config.getCarte();
			int nombre_carte = config.getNbExemplaires();
			
			for(int i = 0; i<nombre_carte; i++) {
				tableau_resultat[indice_tableau] = carte_actuelle;
				indice_tableau++;
			}
		}
		
		return tableau_resultat;
	}

}
