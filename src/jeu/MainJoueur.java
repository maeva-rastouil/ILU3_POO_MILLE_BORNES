package jeu;

import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
	private List <Carte> jeudanslamain = new LinkedList<>();
	
	@Override
	public String toString() {
		return jeudanslamain.toString();
	}
	
	@Override
	public Iterator<Carte> iterator(){
		return jeudanslamain.iterator();
	}
	
	public void prendre(Carte carte) {
		jeudanslamain.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert jeudanslamain.contains(carte); //on vérifie si la carte est dans notre jeu dans la main
		jeudanslamain.remove(carte);
	}
}

