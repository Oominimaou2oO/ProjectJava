package ascenseur.traitement;

public class RequeteExterne extends Requete {
	private int etageDemandeur;
	private int direction;
	
	public RequeteExterne(int etageDemandeur, int direction) {
		this.etageDemandeur = etageDemandeur;
		this.direction = direction;
	}
	
	public int getEtage() {
		return this.etageDemandeur;
	}
}
