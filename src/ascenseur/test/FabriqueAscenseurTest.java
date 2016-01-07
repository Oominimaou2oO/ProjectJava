package ascenseur.test;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.fabrique.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 07/01/2016.
 */
public class FabriqueAscenseurTest {

    private static IFabrique fabMusique;
    private static IFabrique fabVitesse;
    private static IFabrique fabVitesseMusique;

    @BeforeClass
    public static void setUpClass() throws Exception {
        fabMusique = new FabriqueAscenseurMusique();
        fabVitesse = new FabriqueAscenseurVitesse();
        fabVitesseMusique = new FabriqueAscenseurVitesseMusique();
    }

    @Test
    public void testCreerAscenseurMusique() throws Exception {
        Ascenseur asc = fabMusique.creer();
        assertEquals("Ascenseur avec musique & Ascenseur basique",asc.getLibelle());
    }

    @Test
    public void testCreerAscenseurVitesse() throws Exception {
        Ascenseur asc = fabVitesse.creer();
        assertEquals("Ascenseur avec vitesse & Ascenseur basique",asc.getLibelle());
    }

    @Test
    public void testCreerAscenseurVitesseMusique() throws Exception {
        Ascenseur asc = fabVitesseMusique.creer();
        assertEquals("Ascenseur avec vitesse & Ascenseur avec musique & Ascenseur basique",asc.getLibelle());
    }
}