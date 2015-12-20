package ascenseur.traitement;

public class RequeteInterne extends Requete {
	private int etageDemande;
	
	public RequeteInterne(int etageDemande) {
		this.etageDemande = etageDemande;
	}
	
	public int getEtage() {
		return this.etageDemande;
	}
}
