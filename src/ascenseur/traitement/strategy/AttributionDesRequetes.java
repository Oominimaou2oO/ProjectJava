package ascenseur.traitement.strategy;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.Requete;

import java.util.Collection;

public interface AttributionDesRequetes {
    void choisirAscenseur(Collection<Ascenseur> ascenseurs, Collection<Requete> requete);
}
