package ascenseur.traitement;

public class AscenseurAvecAugmentationVitesse extends DecorateurAscenseur {

	public AscenseurAvecAugmentationVitesse(Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	} // Constructor
	
	@Override
	public String getOptionLibelle() {
		return "Vitesse de déplacement augmentée";
	} // getOptionLibelle()

}
