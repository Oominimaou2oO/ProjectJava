package ascenseur.traitement.fabrique;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.ascenseur.IAscenseur;

/**
 * Created by Kevin on 07/01/2016.
 */
public interface IFabrique {
    IAscenseur creer();
}