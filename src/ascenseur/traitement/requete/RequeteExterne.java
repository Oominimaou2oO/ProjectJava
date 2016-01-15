package ascenseur.traitement.requete;

import ascenseur.traitement.util.Constantes;

public class RequeteExterne extends Requete {

	/**
	 * Désigne la direction, c'est à dire si l'utilisateur souhaite aller vers le HAUT ou vers le BAS
	 * @see Constantes
	 */
	private int direction;

	/**
	 * Constructeur, il appelera le constructeur super.
	 * @param etage Etage de la requête.
	 * @param direction La direction qui désigne soit vers le HAUT, soit vers le BAS.
	 * @see Constantes
	 * @see Requete
	 */
	public RequeteExterne(int etage, int direction) {
		super(etage);
		this.direction = direction;
	} // Constructeur

	/**
	 * Retourne la direction de la requête externe (HAUT|BAS).
	 * @return La direction
	 */
	public int getDirection() {
		return direction;
	} // getDirection()

	/**
	 * Retourne le format textuel de la direction
	 * @return Au format String
	 */
	public String toString() {
		if(direction == Constantes.MOUVEMENT_VERS_LE_BAS)
			return "[BAS]";
		return "[HAUT]";
	} // toString()

}
