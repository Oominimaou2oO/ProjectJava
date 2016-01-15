package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.Collection;

public interface AttributionDesRequetes {

    /**
     * Permet d'assigner les requêtes externes aux bons ascenseurs.
     * @param ascenseurs La liste de tout les ascenseurs.
     * @param requetesExternes La liste de toute les requêtes externes.
     */
    void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes);

}
