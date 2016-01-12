package ascenseur.traitement.fabrique;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.ascenseur.AscenseurAvecVitesse;
import ascenseur.traitement.ascenseur.IAscenseur;

public class FabriqueAscenseurVitesse implements IFabrique {

    @Override
    public IAscenseur creer() {
        return new AscenseurAvecVitesse(new AscenseurBasique());
    } // creer()

}
