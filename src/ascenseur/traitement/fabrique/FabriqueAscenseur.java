package ascenseur.traitement.fabrique;

import ascenseur.traitement.ascenseur.AscenseurBasique;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseur implements IFabrique {

    @Override
    public AscenseurBasique creer() {
        return new AscenseurBasique();
    } // creer()

}
