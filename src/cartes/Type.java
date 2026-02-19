package cartes;

public enum Type {
	FEU("Feu rouge", "Feu vert", "Prioritaire"),
	ESSENCE("Panne d'essence", "Essence", "Citerne d'essence"),
	CREVAISON("Crevaison", "Roue de secours", "Increvable"),
	ACCIDENT("Accident", "Réparation", "As du volant");
	
	private String nomAttaque;
	private String nomParade;
	private String nomBotte;

	private Type(String nomAttaque, String nomParade, String nomBotte) {
		this.nomAttaque = nomAttaque;
		this.nomParade = nomParade;
		this.nomBotte = nomBotte;
	}
	
	public String getnomAttaque() {
		return nomAttaque;
	}
	
	public String getnomParade() {
		return nomParade;
	}
	
	public String getnomBotte() {
		return nomBotte;
	}

}
