package ascenseur.traitement.fabrique;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.AscenseurAvecAugmentationVitesse;

public class FabriqueAscenseurVitesse implements IFabrique {

    @Override
    public Ascenseur creer() {
        return new AscenseurAvecAugmentationVitesse(new Ascenseur());
    } // creer()

}
