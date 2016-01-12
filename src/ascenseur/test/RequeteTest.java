package ascenseur.test;

import ascenseur.traitement.Constantes;
import ascenseur.traitement.Requete;
import ascenseur.traitement.RequeteExterne;
import ascenseur.traitement.RequeteInterne;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by h13002021 on 12/01/16.
 */
public class RequeteTest {

    private static RequeteExterne requeteExterneHaut;
    private static RequeteExterne requeteExterneBas;
    private static RequeteInterne requeteInterne;

    @BeforeClass
    public static void setUpClass() throws Exception {
        requeteExterneHaut = new RequeteExterne(2, Constantes.MOUVEMENT_VERS_LE_HAUT);
        requeteExterneBas = new RequeteExterne(2, Constantes.MOUVEMENT_VERS_LE_BAS);
        requeteInterne = new RequeteInterne(4);
    } // setUpClass()

    @Test
    public void testAffichage() {
        assertEquals("[HAUT]",requeteExterneHaut.toString());
        assertEquals("[BAS]",requeteExterneBas.toString());
    } // testAffichage()

    @Test
    public void testDirection() {
        assertEquals(2,requeteExterneHaut.getDirection());
        assertEquals(3,requeteExterneBas.getDirection());
    } // testDirection()

    @Test
    public void testEtage() {
        assertEquals(4,requeteInterne.getEtage());
        assertEquals(2,requeteExterneHaut.getEtage());
    } // testEtage()

}
