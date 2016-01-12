package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.Collection;

/**
 * Created by h13002021 on 12/01/16.
 */
public class AttributionRequeteArbitrairement implements AttributionDesRequetes {


    @Override
    public void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes) {
        /*for (int i = 0 ; i < requetesExternes.size() ; ++i)
            ascenseurs.get(i % ascenseurs.size()).ajouterRequete(requetesExternes.get(i));
        requetesExternes.clear();
        */
    }

}
