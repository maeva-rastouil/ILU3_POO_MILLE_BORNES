package jeu;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import cartes.Carte;

public class MainJoueur {
	private List <Carte> jeudanslamain;

	public MainJoueur() {
		this.jeudanslamain = new ArrayList<>(); 
	}
	
	@Override
	public String toString() {
		return jeudanslamain;
	}
	
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
