package ascenseur.traitement.strategy;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.requete.Requete;

import java.util.Collection;

public interface AttributionDesRequetes {

    void choisirAscenseur(Collection<AscenseurBasique> ascenseurBasiques, Collection<Requete> requete);

}
