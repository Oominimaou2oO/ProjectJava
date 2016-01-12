package ascenseur.traitement.strategy;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.Requete;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;


public class AttributionRequeteHasard implements AttributionDesRequetes {

    @Override
    public void choisirAscenseur(Collection<Ascenseur> ascenseurs, Collection<Requete> requetes) {
        for (Requete r : requetes) {
            int index = new Random().nextInt(ascenseurs.size());
            Iterator<Ascenseur> it = ascenseurs.iterator();
            if (index == 0) {
                it.next().ajouterRequete(r);
            }
            else {
                for (; --index != 0 && it.hasNext(); it.next()) ;
                it.next().ajouterRequete(r);
            }
        }
        requetes.clear();
    } // choisirAscenseur()

}
