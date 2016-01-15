package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.Requete;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.Collection;
import java.util.Iterator;


public class AttributionRequeteArbitrairement implements AttributionDesRequetes {


    @Override
    public void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes) {


        int index = 0;
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
