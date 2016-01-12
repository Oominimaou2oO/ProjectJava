package ascenseur.traitement;

import ascenseur.traitement.strategie.StrategieAscenseurParOrdre;
import ascenseur.traitement.strategie.IStrategieAscenseur;

import java.util.LinkedList;

public class Ascenseur {

    private int etat;
    private int etage;
    private boolean bloquer;
    protected String libelle = "Ascenseur basique";

    private LinkedList<Requete> requetes = new LinkedList<>();
    private IStrategieAscenseur strategie;

    public Ascenseur() {
        this.strategie = new StrategieAscenseurParOrdre();
        this.etat = Constantes.IMMOBILE_FERMER;
        this.etage = 0;
        this.bloquer = false;
    } // Constructeur

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
        strategie.action(this);

        /*
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
        }*/
        // To Do : Satisfaire les requetes de l'étage actuel
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

    public void setStrategie(IStrategieAscenseur strategie) {
        this.strategie = strategie;
    } // setStrategie()

    public void setEtat(int etat) {
        this.etat = etat;
    } // setEtat()

    public void setEtage(int etage) {
        this.etage = etage;
    } //setEtage()

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
