package ascenseur.traitement.requete;

public abstract class Requete {

	/**
	 * Correspond à l'étage de la requête.
	 * L'étage de la requête interne est l'étage que l'utilisateur souhaite atteindre,
	 * L'étage de la requête externe est l'étage où l'utilisateur à appelé l'ascenseur.
	 */
	protected int etage;

	/**
	 * Constructeur de base appelé par tout types de requêtes.
	 * @param etage L'étage de la requête
	 */
	protected Requete(int etage) {
		this.etage = etage;
	} // Constructeur

	/**
	 * Permet de savoir l'étage qui compose la requête.
	 * @return L'étage de la requête.
	 */
	public int getEtage() {
		return this.etage;
	} // getEtage()

}
