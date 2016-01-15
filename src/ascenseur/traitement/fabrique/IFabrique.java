package ascenseur.traitement.fabrique;

import ascenseur.traitement.ascenseur.IAscenseur;

/**
 * Created by Kevin on 07/01/2016.
 */
public interface IFabrique {

    /**
     * Permet de créer un ascenseur suivant le type de la fabrique choisi.
     * @return Retourne l'ascenseur créer grâce à la fabrique.
     */
    IAscenseur creer();

}