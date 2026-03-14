package testsFonctionnels;

import cartes.*;
import utils.GestionCartes;

import java.util.*;

public class TestGestionCartes {

    //méthode générique pour tester le mélange
    public static <T> boolean testMelange(List<T> l1, List<T> l2) {
        if (l1.size() != l2.size()) return false;

        for (T e : l1) {
            if (Collections.frequency(l1, e)
                    != Collections.frequency(l2, e)) {
                return false;
            }
        }
        return true;
    }

    
    
    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();

        //on garde une copie non détruite
        List<Carte> listeCarteNonMelangee = new LinkedList<>();
        for (Carte carte : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(carte);
        }

        //copie à mélanger
        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
        System.out.println(listeCartes);

        //mélange
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println(listeCartes);
        System.out.println("liste mélangée sans erreur ? " + testMelange(listeCarteNonMelangee, listeCartes));

        //rassemblement
        listeCartes = GestionCartes.rassembler(listeCartes);
        System.out.println(listeCartes);
        System.out.println("liste rassemblée sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));


        //tests
        List<Integer> l1 = Arrays.asList(1, 1, 2, 1, 3);
        List<Integer> l2 = Arrays.asList(1, 4, 3, 2);
        List<Integer> l3 = Arrays.asList(1, 1, 2, 3, 1);

        System.out.println("verif rassemblement l1 : " + GestionCartes.verifierRassemblement(l1));
        System.out.println("verif rassemblement l2 : " + GestionCartes.verifierRassemblement(l2));
        System.out.println("verif rassemblement l3 : " + GestionCartes.verifierRassemblement(l3));
    }
}
