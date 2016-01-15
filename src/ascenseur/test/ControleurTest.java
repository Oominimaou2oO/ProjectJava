package ascenseur.test;

import ascenseur.traitement.Controleur;
import ascenseur.traitement.fabrique.FabriqueAscenseurBasique;
import ascenseur.traitement.fabrique.IFabrique;
import ascenseur.traitement.strategie.AttributionRequetes;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by Kevin on 11/01/2016.
 */
public class ControleurTest {

    private static Controleur controleur;

    /**
     * Permet de définir un comportement de base, afin de procéder à des tests concrets.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        controleur = Controleur.getInstance();
    } // setUpClass()

    /**
     * Le but voulu est de vérifier que le controleur est bien unique, et qu'il à uniquement une instance.
     * @see Controleur
     */
    @Test
    public void testUnicité() {
        assertEquals(controleur,Controleur.getInstance());
    } // testUnicité()

    /**
     * Permet de vérifier que l'ajout de requête se passe bien, c'est à dire que le controleur accepte la requête dans sa liste chainée.
     */
    @Test
    public void testAjoutRequeteExterne() {
        controleur.creerRequeteExterne(0, 2);
        assertEquals(1,controleur.getRequetesExternes().size());
    } // testAjoutRequeteExterne()

    /**
     * Permet de vérifier que l'ajout d'ascenseur, c'est à dire que la création et l'ajout de l'ascenseur dans le controleur est bien effectué.
     */
    @Test
    public void testAjoutAscenseur() {
        IFabrique fab = new FabriqueAscenseurBasique();
        controleur.ajouterAscenseur(fab.creer());
        assertEquals(1,controleur.getAscenseurs().size());
    } // testAjoutAscenseur()

    /**
     * Permet de vérifier que le controleur choisisse le bon ascenseur selon sa stratégie.
     * @see AttributionRequetes
     */
    @Test
    public void testChoisirAscenseur() {
        //ICI VU QUE LA STRATEGIE EST StrategieRequeteArbitraire
        //L'ascenseurBasique 0 doit avoir 2 requetes !
        IFabrique fab = new FabriqueAscenseurBasique();
        controleur.ajouterAscenseur(fab.creer());
        controleur.ajouterAscenseur(fab.creer());

        controleur.creerRequeteExterne(2,3);
        controleur.creerRequeteExterne(2,3);
        controleur.creerRequeteExterne(2,3);

        controleur.choisirAscenseur();

        assertEquals(2,controleur.getAscenseurs().get(0).getRequetes().size());
        assertEquals(0,controleur.getRequetesExternes().size());
    } // testChoisirAscenseur()

}
