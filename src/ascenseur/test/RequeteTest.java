package ascenseur.test;

import ascenseur.traitement.util.Constantes;
import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.requete.RequeteInterne;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 12/01/16.
 */
public class RequeteTest {

    private static RequeteExterne requeteExterneHaut;
    private static RequeteExterne requeteExterneBas;
    private static RequeteInterne requeteInterne;

    /**
     * Permet de définir un comportement de base, afin de procéder à des tests concrets.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        requeteExterneHaut = new RequeteExterne(2, Constantes.MOUVEMENT_VERS_LE_HAUT);
        requeteExterneBas = new RequeteExterne(2, Constantes.MOUVEMENT_VERS_LE_BAS);
        requeteInterne = new RequeteInterne(4);
    } // setUpClass()

    /**
     * Permet de vérifier que l'affichage de la requête est correcte, par rapport à sa direction.
     */
    @Test
    public void testAffichage() {
        assertEquals("[HAUT]",requeteExterneHaut.toString());
        assertEquals("[BAS]",requeteExterneBas.toString());
    } // testAffichage()

    /**
     * Permet de vérifier que la direction voulu auprès de la requête externe est correcte.
     */
    @Test
    public void testDirection() {
        assertEquals(2,requeteExterneHaut.getDirection());
        assertEquals(3,requeteExterneBas.getDirection());
    } // testDirection()

    /**
     * Permet de vérifier que l'étage de la requête est celui demandé.
     */
    @Test
    public void testEtage() {
        assertEquals(4,requeteInterne.getEtage());
        assertEquals(2,requeteExterneHaut.getEtage());
    } // testEtage()

}
