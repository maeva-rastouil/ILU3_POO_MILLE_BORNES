package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}
	
	@Override
	public String toString() {
		return km + "km";
	}
	
	@Override
	public boolean equals(Object objet) {
	    return super.equals(objet) && this.km == ((Borne)objet).getKm();
	}
	
	@Override
	public int hashCode() {
	    return 31 * super.hashCode() + km;
	}

	public int getKm() {
		return km;
	}

}
