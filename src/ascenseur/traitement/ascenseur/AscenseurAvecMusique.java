package ascenseur.traitement.ascenseur;

public class AscenseurAvecMusique extends DecorateurAscenseur {

	public AscenseurAvecMusique(IAscenseur ascenseur) {
		this.ascenseur = ascenseur;
	} // Constructeur

	@Override
	public String getLibelle() {
		return ascenseur.getLibelle() + " avec musique";
	}
}
