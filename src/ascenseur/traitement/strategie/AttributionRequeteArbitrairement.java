package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.Requete;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.Collection;
import java.util.Iterator;


public class AttributionRequeteArbitrairement implements AttributionDesRequetes {

    private static int shift = 0;

    @Override
    public void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes) {

        shift = shift+1%ascenseurs.size();
        int index = shift;
        for (Requete r : requetesExternes) {
            index = index %ascenseurs.size();
            Iterator<IAscenseur> it = ascenseurs.iterator();

            for (int i=index; i != 0 && it.hasNext(); it.next())
                i-=1;
            it.next().ajouterRequete(r);
            ++index;
        }
        requetesExternes.clear();
    }

}
