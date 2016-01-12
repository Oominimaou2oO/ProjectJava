package ascenseur.traitement.ascenseur;

import ascenseur.traitement.requete.Requete;

import java.util.LinkedList;

/**
 * Created by h13002021 on 12/01/16.
 */
public interface IAscenseur {

    void bloquer();
    void debloquer();
    void ajouterRequete(Requete requete);
    void creerRequeteInterne(int etage);
    void action();
    int getEtage();
    int getEtat();
    String getLibelle();
    boolean isBloqued();
    LinkedList<Requete> getRequetes();

}
