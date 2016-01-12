package ascenseur.traitement.requete;

public abstract class Requete {

	protected int etage;
	
	protected Requete(int etage) {
		this.etage = etage;
	} // Constructeur
	
	public int getEtage() {
		return this.etage;
	} // getEtage()

}
