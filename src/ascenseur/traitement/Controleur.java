package ascenseur.traitement;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.strategie.AttributionRequetes;
import ascenseur.traitement.strategie.AttributionRequetesArbitrairement;
import ascenseur.traitement.strategie.AttributionRequetesHasard;
import ascenseur.traitement.strategie.AttributionRequetesIntelligente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<IAscenseur> ascenseurs = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();
    private AttributionRequetes strategieRequete;

    /**
     * Constructeur privé, permettant le singleton.
     */
    private Controleur() {
        strategieRequete = new AttributionRequetesHasard();
    } // Constructeur

    /**
     * Permet de récupérer l'instance du Controleur.
     * @return L'instance unique.
     * @see ascenseur.test.ControleurTest
     */
    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    /**
     * Permet de créer une requête externe.
     * @param etage L'étage ou la requête à était effectuée.
     * @param direction La direction voulu, soit monter, soit descendre.
     */
    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    /**
     * Permet d'ajouter un nouvel ascenseur à la liste des ascenseurs.
     * @param ascenseur Ascenseur de tout type.
     */
    public void ajouterAscenseur(IAscenseur ascenseur) {
        ascenseurs.add(ascenseur);
    } // ajouterAscenseur()

    /**
     * Suivant la stratégie, le controleur va attribué les requêtes externes aux ascenseurs, suivant le comportement écrit.
     * @see AttributionRequetes
     */
    public void choisirAscenseur() {
        strategieRequete.choisirAscenseur(ascenseurs,requetesExternes);
    } // choisirAscenseur()

    /**
     * Permet de récuperer toute les requêtes externes qui n'ont pas été distribuée.
     * @return La liste chainée de requêtes externes.
     */
    public LinkedList<RequeteExterne> getRequetesExternes() {
        return requetesExternes;
    } // getRequetesExternes()

    /**
     * Permet de récuperer tout les ascenseurs qui ont été créer pour déservir les étages.
     * @return Une liste d'ascenseur.
     */
    public List<IAscenseur> getAscenseurs() {
        return ascenseurs;
    } // getAscenseurBasiques()

    /**
     * Permet aux ascenseurs disponibles de faire leur prochaine action.
     */
    public void update() {
        choisirAscenseur();
        for(IAscenseur ascenseur : ascenseurs)
            ascenseur.action();
    } // update()

}
