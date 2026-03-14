package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return getType().getnomBotte();
	}
	
	@Override
	public boolean equals(Object obj) {

	    if (!super.equals(obj)) return false;

	    Botte autre = (Botte) obj;

	    return this.type == autre.type;
	}
	
	@Override
	public int hashCode() {
	    return 31 * super.hashCode() + type.hashCode();
	}

}
