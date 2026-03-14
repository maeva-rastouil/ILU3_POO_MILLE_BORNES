package testsFonctionnels;

import cartes.*;

public class TestMethodeEquals {

	public static void main(String[] args) {

        //Deux objets de la carte borne 25km
        Carte borne1 = new Borne(25);
        Carte borne2 = new Borne(25);
        System.out.println("Deux cartes de 25km sont identiques ? " + borne1.equals(borne2));

        //Deux objets de la carte feu rouge
        Carte feuRouge1 = new Attaque(Type.FEU);
        Carte feuRouge2 = new Attaque(Type.FEU);
        System.out.println("Deux cartes de feux rouge sont identiques ? " + feuRouge1.equals(feuRouge2));

        //Un objet de la carte feu rouge et un objet de la carte feu vert
        Carte feuVert = new Parade(Type.FEU);
        System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + feuRouge1.equals(feuVert));
    }
}