package ascenseur.traitement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.strategie.AttributionDesRequetes;
import ascenseur.traitement.strategie.AttributionRequeteArbitrairement;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<IAscenseur> ascenseurs = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();
    private AttributionDesRequetes strategieRequete;

    private Controleur() {
        strategieRequete = new AttributionRequeteArbitrairement();
    } // Constructeur

    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    public void ajouterAscenseur(IAscenseur ascenseur) {
        ascenseurs.add(ascenseur);
    } // ajouterAscenseur()

    public void choisirAscenseur() {
        strategieRequete.choisirAscenseur(ascenseurs,requetesExternes);
    } // choisirAscenseur()

    public LinkedList<RequeteExterne> getRequetesExternes() {
        return requetesExternes;
    } // getRequetesExternes()

    public List<IAscenseur> getAscenseurs() {
        return ascenseurs;
    } // getAscenseurBasiques()

    public void update() {
        choisirAscenseur();
        for(IAscenseur ascenseur : ascenseurs)
            ascenseur.action();
    } // update()

}
