package ascenseur.test;

import ascenseur.traitement.Constantes;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.fabrique.FabriqueAscenseur;
import ascenseur.traitement.fabrique.IFabrique;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by Kevin on 11/01/2016.
 */
public class ControleurTest {

    private static Controleur controleur;

    @BeforeClass
    public static void setUpClass() throws Exception {
        controleur = Controleur.getInstance();
    }

    @Test
    public void testUnicité() {
        assertEquals(controleur,Controleur.getInstance());
    }

    @Test
    public void testAjoutRequeteExterne() {
        controleur.creerRequeteExterne(0, 2);
        assertEquals(1,controleur.getRequetesExternes().size());
    }

    @Test
    public void testAjoutAscenseur() {
        IFabrique fab = new FabriqueAscenseur();
        controleur.ajouterAscenseur(fab.creer());
        assertEquals(1,controleur.getAscenseurs().size());
    }

    @Test
    public void testChoisirAscenseur() {
        IFabrique fab = new FabriqueAscenseur();
        controleur.ajouterAscenseur(fab.creer());
        controleur.ajouterAscenseur(fab.creer());

        controleur.creerRequeteExterne(2,3);
        controleur.creerRequeteExterne(2,3);
        controleur.creerRequeteExterne(2,3);

        controleur.choisirAscenseur();

        assertEquals(2,controleur.getAscenseurs().get(0).getRequetes().size());
        assertEquals(0,controleur.getRequetesExternes().size());
    }


}
