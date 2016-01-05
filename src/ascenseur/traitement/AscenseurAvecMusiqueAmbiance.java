package ascenseur.traitement;

public class AscenseurAvecMusiqueAmbiance extends DecorateurAscenseur {

	public AscenseurAvecMusiqueAmbiance(Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	} // Constructor
	
	@Override
	public String getOptionLibelle() {
		return "Musique d'ambiance lors des déplacements de l'ascenseur";
	} // getOptionLibelle()

}
