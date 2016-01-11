package ascenseur.test;

import ascenseur.traitement.Constantes;
import ascenseur.traitement.Controleur;
import org.junit.*;
import static org.junit.Assert.*;


/**
 * Created by Kevin on 11/01/2016.
 */
public class ControleurTest {

    private static Controleur controleur = Controleur.getInstance();

    @BeforeClass
    public static void setUpClass() throws Exception {

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

}
