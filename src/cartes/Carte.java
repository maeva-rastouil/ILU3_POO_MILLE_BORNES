package cartes;

public abstract class Carte{
	
	@Override
	public boolean equals(Object obj) {

	    if (this == obj) return true;

	    if (obj == null) return false;

	    return getClass() == obj.getClass();
	}
	
	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}

}
