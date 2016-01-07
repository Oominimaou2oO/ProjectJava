package ascenseur.traitement;

public abstract class DecorateurAscenseur extends Ascenseur {
	protected Ascenseur ascenseur;

	public void action() {
		ascenseur.action();
	}

	public String getLibelle() {
		return libelle+ascenseur.getLibelle();
	}
}
