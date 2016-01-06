package ascenseur.traitement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ascenseur {
    private int etat;
    private int etage;
    private boolean bloquer;

    private LinkedList<Requete> requetes = new LinkedList<>();

    public Ascenseur() {
        this.etat = Constantes.IMMOBILE_FERMER;
        this.etage = 0;
        this.bloquer = false;
    } // Constructor

    public boolean bloquer() {
        if (bloquer) return false;
        this.bloquer = true;
        return true;
    } // bloquer()

    public boolean debloquer() {
        if (!bloquer) return false;
        this.bloquer = false;
        return true;
    } // debloquer()

    public void ajouterRequete(Requete requete) {
        requetes.addLast(requete);
    } // ajouterRequete

    public void creerRequeteInterne(int etage) {
        requetes.add(new RequeteInterne(etage));
    } // creerRequeteInterne()

    public void action() {
        if (bloquer) return;

        switch (etat) {
            case Constantes.IMMOBILE_FERMER:
                if (!requetes.isEmpty()) {
                    int etageCible = requetes.getFirst().getEtage();
                    if (etageCible > etage) {
                        etat = Constantes.MOUVEMENT_VERS_LE_HAUT;
                    } else if (etageCible < etage) {
                        --etage;
                        etat = Constantes.MOUVEMENT_VERS_LE_BAS;
                    } else {
                        etat = Constantes.IMMOBILE_OUVERT;
                    }
                }
                break;
            case Constantes.IMMOBILE_OUVERT:
                requetes.removeFirst();
                if (requetes.isEmpty() || requetes.getFirst().getEtage() != etage) {
                    etat = Constantes.IMMOBILE_FERMER;
                }
                break;
            case Constantes.MOUVEMENT_VERS_LE_BAS:
                --etage;
                if (requetes.getFirst().getEtage() == etage) {
                    etat = Constantes.IMMOBILE_OUVERT;
                }
                break;
            case Constantes.MOUVEMENT_VERS_LE_HAUT:
                ++etage;
                if (requetes.getFirst().getEtage() == etage) {
                    etat = Constantes.IMMOBILE_OUVERT;
                }
                break;
        }
        System.out.println("Action : " + etage + " -> " + requetes.getFirst().getEtage());
        // To Do : Satisfaire les requete de l'étage actuel
    } // action()

    public String toString() {
        String affichage;
        switch (etat) {
            case Constantes.IMMOBILE_FERMER:
                affichage = "[|]";
                break;
            case Constantes.IMMOBILE_OUVERT:
                affichage = "[ ]";
                break;
            case Constantes.MOUVEMENT_VERS_LE_BAS:
                affichage = "[v]";
                break;
            case Constantes.MOUVEMENT_VERS_LE_HAUT:
                affichage = "[^]";
                break;
            default:
                affichage = "[|]";
        }
        return affichage;
    } // toString()

    public int getEtage() {
        return this.etage;
    } // getEtage()

    public int getEtat() {
        return this.etat;
    } // getEtat()

    public boolean isBloqued() {
        return this.bloquer;
    } // isBloquer()

    public List<Requete> getRequetes() {
        return requetes;
    }
}
