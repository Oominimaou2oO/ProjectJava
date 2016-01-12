package ascenseur.test;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.fabrique.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurBasiqueTest {

    private static IFabrique fabMusique;
    private static IFabrique fabVitesse;
    private static IFabrique fabVitesseMusique;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fabMusique = new FabriqueAscenseurMusique();
        fabVitesse = new FabriqueAscenseurVitesse();
        fabVitesseMusique = new FabriqueAscenseurVitesseMusique();
    } // setUpClass()

    @Test
    public void testCreerAscenseurMusique() throws Exception {
        IAscenseur asc = fabMusique.creer();
        assertEquals("Ascenseur basique avec musique",asc.getLibelle());
    } // testCreerAscenseurMusique()

    @Test
    public void testCreerAscenseurVitesse() throws Exception {
        IAscenseur asc = fabVitesse.creer();
        assertEquals("Ascenseur basique avec vitesse",asc.getLibelle());
    } // testCreerAscenseurVitesse()

    @Test
    public void testCreerAscenseurVitesseMusique() throws Exception {
        IAscenseur asc = fabVitesseMusique.creer();
        assertEquals("Ascenseur basique avec musique avec vitesse",asc.getLibelle());
    } // testCreerAscenseurVitesseMusique()

}