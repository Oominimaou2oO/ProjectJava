package ascenseur.traitement.fabrique;

import ascenseur.traitement.Ascenseur;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseur implements IFabrique {

    @Override
    public Ascenseur creer() {
        return new Ascenseur();
    }

}
