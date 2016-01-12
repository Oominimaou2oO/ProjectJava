package ascenseur.affichage;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;

import java.util.List;

/**
 * Created by Alexandre on 11/01/2016.
 */
public interface Vue {

    void getVueCoupe(List<IAscenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages);
    void getVueInteractif(List<IAscenseur> ascenseurs, int nombreEtages);
    void getVueRequetes(List<IAscenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages);

}
