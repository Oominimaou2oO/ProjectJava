package ascenseur.traitement;

public class RequeteExterne extends Requete {
	private int direction;
	
	public RequeteExterne(int etage, int direction) {
		super(etage);
		this.direction = direction;
	} // Constructor
	
	public int getDirection() {
		return direction;
	} // getDirection()
	
	public String toString() {
		if(direction == Constantes.MOUVEMENT_VERS_LE_BAS)
			return "[BAS]";
		return "[HAUT]";
	} // toString()
}
