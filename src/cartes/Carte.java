package cartes;

public abstract class Carte{
	
	@Override
	public boolean equals(Object obj) {
	    return getClass() == obj.getClass();
	}
	
	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}

}
