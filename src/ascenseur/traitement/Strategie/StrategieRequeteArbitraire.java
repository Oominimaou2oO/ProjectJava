package ascenseur.traitement.strategie;

import ascenseur.traitement.Controleur;

/**
 * Created by Kevin on 11/01/2016.
 */
public class StrategieRequeteArbitraire implements IStrategieRequete {

    @Override
    public void choisirAscenseur() {
        Controleur controleur = Controleur.getInstance();
        for (int i = 0 ; i < controleur.getRequetesExternes().size() ; ++i) {
            controleur.getAscenseurs().get(i % controleur.getAscenseurs().size()).ajouterRequete(controleur.getRequetesExternes().get(i));
        }
        controleur.getRequetesExternes().clear();
    } // choisirAscenseur(
}
