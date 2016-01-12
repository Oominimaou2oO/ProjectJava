package ascenseur.traitement.fabrique;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.AscenseurAvecMusiqueAmbiance;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurMusique implements IFabrique {

    @Override
    public Ascenseur creer() {
        return new AscenseurAvecMusiqueAmbiance(new Ascenseur());
    } // creer()

}
