package ascenseur.traitement.Fabrique;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.AscenseurAvecAugmentationVitesse;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurVitesse implements IFabrique {
    @Override
    public Ascenseur creer() {
        return new AscenseurAvecAugmentationVitesse(new Ascenseur());
    }
}
