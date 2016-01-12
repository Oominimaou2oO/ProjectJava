package ascenseur.traitement.fabrique;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.ascenseur.AscenseurAvecMusique;
import ascenseur.traitement.ascenseur.IAscenseur;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurMusique implements IFabrique {

    @Override
    public IAscenseur creer() {
        return new AscenseurAvecMusique(new AscenseurBasique());
    } // creer()

}
