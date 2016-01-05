package ascenseur.traitement;

public abstract class Requete {
	protected int etage;
	
	protected Requete(int etage) {
		this.etage = etage;
	} // Constructor
	
	public int getEtage() {
		return this.etage;
	} // getEtage()
}
