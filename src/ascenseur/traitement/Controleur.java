package ascenseur.traitement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import ascenseur.traitement.strategie.IStrategieRequete;
import ascenseur.traitement.strategie.StrategieRequeteArbitraire;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<Ascenseur> ascenseurs = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();
    private IStrategieRequete strategieRequete;

    private Controleur() {
        strategieRequete = new StrategieRequeteArbitraire();
    } // Constructor

    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    public void ajouterAscenseur(Ascenseur ascenseur) {
        ascenseurs.add(ascenseur);
    } // ajouterAscenseur()

    public void choisirAscenseur() {
        strategieRequete.choisirAscenseur();
    } // choisirAscenseur()

    public LinkedList<RequeteExterne> getRequetesExternes() {
        return requetesExternes;
    } // getRequetesExternes()

    public List<Ascenseur> getAscenseurs() {
        return ascenseurs;
    } // getAscenseurs()

    public void update() {
        choisirAscenseur();
        for(Ascenseur ascenseur : ascenseurs)
            ascenseur.action();
    } // update()
}
