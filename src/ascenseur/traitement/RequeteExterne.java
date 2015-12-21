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
	
	public int getDirection() {
		return direction;
	}
	
	public void choisirAscenseur(Ascenseur ascenseur) {
		ascenseur.ajouterRequete(this);
	}
	
	public String toString() {
		if(direction == Constantes.MOUVEMENT_VERS_LE_BAS) {
			return "[BAS]";
		}
		return "[HAUT]";
	}
}
