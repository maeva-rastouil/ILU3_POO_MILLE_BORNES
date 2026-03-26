package utils;

import java.util.*;

public class GestionCartes {
	
    private static final Random rand = new Random();

    //extraire version 1
    public static <T> T extraire(List<T> liste) {
        int indice = rand.nextInt(liste.size());
        return liste.remove(indice);
    }

    
    
    //extraire version 2
    public static <T> T extraireIterateur(List<T> liste) {
        int indice = rand.nextInt(liste.size());

        ListIterator<T> it = liste.listIterator();
        T element = null;

        for (int i = 0; i <= indice; i++) {
            element = it.next();
        }
        it.remove();
        return element;
    }

    
    
    //melanger
    public static <T> List<T> melanger(List<T> liste) {
        List<T> resultat = new ArrayList<>();

        while (!liste.isEmpty()){
            resultat.add(extraire(liste));
        }
        return resultat;
    }

    
    
    //verifierMelange
    public static <T> boolean verifierMelange(List<T> l1, List<T> l2) {
        if (l1.size() != l2.size()) return false;

        for (T e : l1) {

            if (Collections.frequency(l1, e)
                    != Collections.frequency(l2, e)) {
                return false;
            }
        }
        return true;
    }

    
    
    //rassembler
    public static <T> List<T> rassembler(List<T> liste) {
        List<T> resultat = new ArrayList<>();

        while (!liste.isEmpty()) {
            T element = liste.remove(0);
            resultat.add(element);

            Iterator<T> it = liste.iterator();

            while (it.hasNext()) {
                T e = it.next();
                if (e.equals(element)) {
                    resultat.add(e);
                    it.remove();
                }
            }
        }
        return resultat;
    }

    
    
    //verifierRassemblement
    public static <T> boolean verifierRassemblement(List<T> liste) {
        ListIterator<T> it1 = liste.listIterator();

        while (it1.hasNext()){
            T courant = it1.next();

            ListIterator<T> it2 = liste.listIterator(it1.nextIndex());

            while (it2.hasNext()) {
                T suivant = it2.next();

                if (suivant.equals(courant)) {
                    return false;
                }
                else {
                	break;
                }

            }
        }
        return true;
    }
}
