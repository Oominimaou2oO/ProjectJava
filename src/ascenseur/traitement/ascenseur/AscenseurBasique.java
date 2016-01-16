package ascenseur.traitement.ascenseur;

import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.util.Constantes;
import ascenseur.traitement.requete.Requete;
import ascenseur.traitement.requete.RequeteInterne;

import java.util.LinkedList;

public class AscenseurBasique implements IAscenseur {

    private int etat;
    private int etage;
    private boolean bloquer;
    private String libelle = "Ascenseur basique";
    private LinkedList<Requete> requetes = new LinkedList<Requete>();

    /**
     * Constructeur
     */
    public AscenseurBasique() {
        this.etat = Constantes.IMMOBILE_FERMER;
        this.etage = 0;
        this.bloquer = false;
    } // Constructeur

    public void bloquer() {
        this.bloquer = true;
    } // bloquer()

    public void debloquer() {
        this.bloquer = false;
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
                        etat = Constantes.MOUVEMENT_VERS_LE_BAS;
                    } else {
                        etat = Constantes.IMMOBILE_OUVERT;
                    }
                }
                break;
            case Constantes.IMMOBILE_OUVERT:
                if(requetes.getFirst().getEtage() == etage)
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

        for(int i = 1 ; i < requetes.size() ; ++i) {
            if(etage == requetes.get(i).getEtage()) {
                etat = Constantes.IMMOBILE_OUVERT;
                requetes.remove(i);
            }
        }
    } // action()

    /**
     * Permet de modéliser l'état de l'ascenseur de manière textuelle.
     * @return L'affichage symbolisant sont état.
     */
    @Override
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

    public LinkedList<Requete> getRequetes() {
        return requetes;
    } // getRequetes()

    public String getLibelle() {
        return libelle;
    } // getLibelle()

}
