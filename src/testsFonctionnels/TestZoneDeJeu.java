package testsFonctionnels;

import cartes.Attaque;
import cartes.Botte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.Type;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	public static void main(String[] args) {
		// TP4 Partie 1
		boolean depotOK = false;
		// Feu rouge
		System.out.println("Deposer carte Feu rouge");
		ZoneDeJeu zoneDeJeu = new ZoneDeJeu();

		depotOK = zoneDeJeu.estDepotAutorise(Cartes.FEU_ROUGE);
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(Cartes.FEU_ROUGE);
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// Botte Vehicule prioritaire
		System.out.println("Deposer carte Vehicule prioritaire");
		depotOK = zoneDeJeu.estDepotAutorise(Cartes.PRIORITAIRE);
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(Cartes.PRIORITAIRE);
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// accident
		System.out.println("Deposer carte attaque - accident");
		depotOK = zoneDeJeu.estDepotAutorise(new Attaque(Type.ACCIDENT));
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(new Attaque(Type.ACCIDENT));
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
	
		// botte roue de secours
		System.out.println("Deposer botte - roue de secours");
		depotOK = zoneDeJeu.estDepotAutorise(new Botte(Type.CREVAISON));
		if (depotOK) {
			zoneDeJeu.deposer(new Botte(Type.CREVAISON));
		}
		System.out.println("d�p�t ok ? " + depotOK);
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// botte as du volant
		System.out.println("Deposer carte as du volant");
		depotOK = zoneDeJeu.estDepotAutorise(new Botte(Type.ACCIDENT));
		if (depotOK) {
			zoneDeJeu.deposer(new Botte(Type.ACCIDENT));
		}
		System.out.println("d�p�t ok ? " + depotOK);
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// Feu vert
		System.out.println("Deposer carte Feu vert");
		depotOK = zoneDeJeu.estDepotAutorise(Cartes.FEU_VERT);
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(Cartes.FEU_VERT);
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// D�pot attaque accident
		System.out.println("Deposer carte accident");
		depotOK = zoneDeJeu.estDepotAutorise(new Attaque(Type.ACCIDENT));
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(new Attaque(Type.ACCIDENT));
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		// D�pot limitation de vitesse 50 bornes
		System.out.println("Deposer carte limite - 50");
		depotOK = zoneDeJeu.estDepotAutorise(new DebutLimite());
		System.out.println("d�p�t ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu.deposer(new DebutLimite());
		}
		System.out.println("peut avancer ? " + zoneDeJeu.peutAvancer());
		
		//RESULTAT ATTENDU POUR LE TP4 Partie 1
//		Deposer carte Feu rouge
//		d�p�t ok ? false
//		peut avancer ? false
//		Deposer carte Vehicule prioritaire
//		d�p�t ok ? true
//		peut avancer ? true
//		Deposer carte attaque - accident
//		d�p�t ok ? true
//		peut avancer ? false
//		Deposer botte - roue de secours
//		d�p�t ok ? true
//		peut avancer ? false
//		Deposer carte as du volant
//		d�p�t ok ? true
//		peut avancer ? true
//		Deposer carte Feu vert
//		d�p�t ok ? false
//		peut avancer ? true
//		Deposer carte accident
//		d�p�t ok ? false
//		peut avancer ? true
//		Deposer carte limite - 50
//		d�p�t ok ? false
//		peut avancer ? true
	}



	
}


