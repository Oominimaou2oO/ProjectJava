package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.Requete;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.*;


public class AttributionRequeteHasard implements AttributionDesRequetes {

    @Override
    public void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes) {
        for (Requete r : requetesExternes) {
            int index = new Random().nextInt(ascenseurs.size());
            Iterator<IAscenseur> it = ascenseurs.iterator();
            if (index == 0) {
                it.next().ajouterRequete(r);
            }
            else {
                for (; --index != 0 && it.hasNext(); it.next()) ;
                it.next().ajouterRequete(r);
            }
        }
        requetesExternes.clear();
    } // choisirAscenseur()

}
