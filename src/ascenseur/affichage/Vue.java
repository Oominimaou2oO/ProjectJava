package ascenseur.affichage;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.RequeteExterne;

import java.util.List;

/**
 * Created by Alexandre on 11/01/2016.
 */
public interface Vue {

    void getVueCoupe(List<Ascenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages);
    void getVueInteractif(List<Ascenseur> ascenseurs, int nombreEtages);
    void getVueRequetes(List<Ascenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages);

}
