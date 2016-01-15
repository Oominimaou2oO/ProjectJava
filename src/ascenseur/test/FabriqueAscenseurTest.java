package ascenseur.test;

import ascenseur.traitement.ascenseur.IAscenseur;
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

    /**
     * Permet de définir un comportement de base, afin de procéder à des tests concrets.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        fabMusique = new FabriqueAscenseurMusique();
        fabVitesse = new FabriqueAscenseurVitesse();
        fabVitesseMusique = new FabriqueAscenseurVitesseMusique();
    } // setUpClass()

    /**
     * Permet de vérifier que la fabrique nous créer le bon ascenseur, ici un ascenseur avec de la musique.
     * @see ascenseur.traitement.ascenseur.DecorateurAscenseur
     * @throws Exception
     */
    @Test
    public void testCreerAscenseurMusique() throws Exception {
        IAscenseur asc = fabMusique.creer();
        assertEquals("Ascenseur basique avec musique",asc.getLibelle());
    } // testCreerAscenseurMusique()

    /**
     * Permet de vérifier que la fabrique nous créer le bon ascenseur, ici un ascenseur avec une vitesse améliorée.
     * @see ascenseur.traitement.ascenseur.DecorateurAscenseur
     * @throws Exception
     */
    @Test
    public void testCreerAscenseurVitesse() throws Exception {
        IAscenseur asc = fabVitesse.creer();
        assertEquals("Ascenseur basique avec vitesse",asc.getLibelle());
    } // testCreerAscenseurVitesse()

    /**
     * Permet de vérifier que la fabrique nous créer le bon ascenseur, ici un ascenseur avec de la musique et une vitesse améliorée.
     * @see ascenseur.traitement.ascenseur.DecorateurAscenseur
     * @throws Exception
     */
    @Test
    public void testCreerAscenseurVitesseMusique() throws Exception {
        IAscenseur asc = fabVitesseMusique.creer();
        assertEquals("Ascenseur basique avec musique avec vitesse",asc.getLibelle());
    } // testCreerAscenseurVitesseMusique()

}