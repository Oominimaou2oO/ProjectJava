package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.Collection;

public interface AttributionDesRequetes {

    void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes);

}
