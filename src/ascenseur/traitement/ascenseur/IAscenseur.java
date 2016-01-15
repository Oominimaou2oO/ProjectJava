package ascenseur.traitement.ascenseur;

import ascenseur.traitement.requete.Requete;

import java.util.LinkedList;

/**
 * Created by Kevin on 12/01/16.
 */
public interface IAscenseur {

    /**
     * Permet de bloquer l'ascenseur à son état actuel, ainsi plus aucune requête n'est traitée.
     */
    void bloquer();
    /**
     * Permet de debloquer l'ascenseur, ainsi il est de nouveau possible de traitée les requetês.
     */
    void debloquer();
    /**
     * Ajoute une nouvelle requête(Externe) à la liste des requêtes de l'ascenseur, qui traitera par la suite.
     * @param requete La requête externe que le controleur assigne à cet ascenseur.
     */
    void ajouterRequete(Requete requete);
    /**
     * Créer une requête interne, l'étage passé en paramètre est l'étage demandé.
     * @param etage L'étage à atteindre.
     */
    void creerRequeteInterne(int etage);
    /**
     * L'ascenseur effectue l'action suivante seulement si l'ascenseur n'est pas bloqué.
     */
    void action();
    /**
     * Permet d'accéder à l'étage actuel de l'ascenseur.
     * @return L'étage de l'ascenseur.
     */
    int getEtage();
    /**
     * Permet d'accéder à l'etat actuel de l'ascenseur
     * @return L'état de l'ascenseur
     */
    int getEtat();
    /**
     * Permet de savoir ce qu'est l'ascenseur : Ascenseur Basique
     * @return Le libelle de l'ascenseur
     */
    String getLibelle();
    /**
     * Permet de savoir si l'ascenseur et actuellement bloqué
     * @return Le boolean , TRUE = Bloqué, FALSE = Non-bloqué
     */
    boolean isBloqued();
    /**
     * Permet d'avoir toute les requêtes non-traité lié à l'ascenseur
     * @return Une liste chainé dans l'ordre des requêtes pas encore traitée
     */
    LinkedList<Requete> getRequetes();

}
