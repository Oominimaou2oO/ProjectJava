package ascenseur.traitement;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurVitesse implements IFabrique {
    @Override
    public Ascenseur creer() {
        return new AscenseurAvecAugmentationVitesse(new Ascenseur());
    }
}
