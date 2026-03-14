package cartes;

public abstract class Probleme extends Carte {
	protected Type type;

	protected Probleme(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {

	    if (!super.equals(obj)) return false;

	    Probleme autre = (Probleme) obj;

	    return this.getType() == autre.getType();
	}
	
	@Override
	public int hashCode() {
	    return 31 * super.hashCode() + type.hashCode();
	}

}
