package ascenseur.traitement.strategie;

import ascenseur.traitement.Controleur;

/**
 * Created by Kevin on 11/01/2016.
 */
public class StrategieRequeteArbitraire implements IStrategieRequete {

    private Controleur controleur = Controleur.getInstance();

    @Override
    public void choisirAscenseur() {
        for (int i = 0 ; i < controleur.getRequetesExternes().size() ; ++i) {
            controleur.getAscenseurs().get(i % controleur.getAscenseurs().size()).ajouterRequete(controleur.getRequetesExternes().getFirst());
            controleur.getRequetesExternes().removeFirst();
        }
    } // choisirAscenseur(
}
