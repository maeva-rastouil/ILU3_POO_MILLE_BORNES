package cartes;

import java.util.*;

public class JeuDeCartes {

    private Map<Carte, Integer> configuration;

    public JeuDeCartes() {
        configuration = new HashMap<>();

        configuration.put(new Borne(25), 10);
        configuration.put(new Borne(50), 10);
        configuration.put(new Borne(75), 10);
        configuration.put(new Borne(100), 12);
        configuration.put(new Borne(200), 4);

        configuration.put(new FinLimite(), 6);
        configuration.put(new DebutLimite(), 4);

        configuration.put(new Attaque(Type.FEU), 5);
        configuration.put(new Attaque(Type.ESSENCE), 3);
        configuration.put(new Attaque(Type.CREVAISON), 3);
        configuration.put(new Attaque(Type.ACCIDENT), 3);

        configuration.put(new Parade(Type.FEU), 14);
        configuration.put(new Parade(Type.ESSENCE), 6);
        configuration.put(new Parade(Type.CREVAISON), 6);
        configuration.put(new Parade(Type.ACCIDENT), 6);

        configuration.put(new Botte(Type.FEU), 1);
        configuration.put(new Botte(Type.ESSENCE), 1);
        configuration.put(new Botte(Type.CREVAISON), 1);
        configuration.put(new Botte(Type.ACCIDENT), 1);
    }

    public StringBuilder affichageJeuDeCartes() {
        StringBuilder resultat = new StringBuilder("JEU :\n");

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            resultat.append(entry.getValue())
                    .append(" ")
                    .append(entry.getKey())
                    .append("\n");
        }

        return resultat;
    }

    public Carte[] donnerCartes() {
        int total = 0;

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            total += entry.getValue();
        }

        Carte[] tableau = new Carte[total];
        int index = 0;

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            Carte carte = entry.getKey();
            int nb = entry.getValue();

            for (int i = 0; i < nb; i++) {
                tableau[index++] = carte;
            }
        }

        return tableau;
    }

    public boolean checkCount() {
        Carte[] cartes = donnerCartes();

        for (Map.Entry<Carte, Integer> entry : configuration.entrySet()) {
            Carte carte = entry.getKey();
            int attendu = entry.getValue();
            int compteur = 0;

            for (Carte c : cartes) {
                if (c.equals(carte)) {
                    compteur++;
                }
            }

            if (compteur != attendu) {
                return false;
            }
        }

        return true;
    }
}
