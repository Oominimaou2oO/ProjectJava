package ascenseur.traitement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.strategie.IStrategieRequete;
import ascenseur.traitement.strategie.StrategieRequeteArbitraire;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<IAscenseur> ascenseurBasiques = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();
    private IStrategieRequete strategieRequete;

    private Controleur() {
        strategieRequete = new StrategieRequeteArbitraire();
    } // Constructeur

    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    public void ajouterAscenseur(IAscenseur ascenseurBasique) {
        ascenseurBasiques.add(ascenseurBasique);
    } // ajouterAscenseur()

    public void choisirAscenseur() {
        strategieRequete.choisirAscenseur();
    } // choisirAscenseur()

    public LinkedList<RequeteExterne> getRequetesExternes() {
        return requetesExternes;
    } // getRequetesExternes()

    public List<IAscenseur> getAscenseurs() {
        return ascenseurBasiques;
    } // getAscenseurBasiques()

    public void update() {
        choisirAscenseur();
        for(IAscenseur ascenseurBasique : ascenseurBasiques)
            ascenseurBasique.action();
    } // update()

}
