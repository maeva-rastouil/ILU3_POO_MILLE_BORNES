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
	public boolean equals(Object obj) {
	    if (!super.equals(obj)) return false;

	    Borne autre = (Borne) obj;

	    return this.km == autre.km;
	}
	
	@Override
	public int hashCode() {
	    return 31 * super.hashCode() + km;
	}

}
