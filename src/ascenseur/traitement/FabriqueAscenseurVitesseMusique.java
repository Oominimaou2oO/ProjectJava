package ascenseur.traitement;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurVitesseMusique implements IFabrique {
    @Override
    public Ascenseur creer() {
        return new AscenseurAvecAugmentationVitesse(new AscenseurAvecMusiqueAmbiance(new Ascenseur()));
    }
}
