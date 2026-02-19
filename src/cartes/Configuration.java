package cartes;

public class Configuration {
    private Carte carte;
    private Integer nbExemplaires;

    public Configuration(Carte carte, Integer nbExemplaires) {
        this.carte = carte;
        this.nbExemplaires = nbExemplaires;
    }

    public Carte getCarte() {
        return carte;
    }

    public Integer getNbExemplaires() {
        return nbExemplaires;
    }
}
