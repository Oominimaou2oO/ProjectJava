package ascenseur.test;

import ascenseur.traitement.*;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 06/01/2016.
 */
public class AscenseurTest {

    private static Ascenseur ascenseur;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ascenseur = new Ascenseur();
        ascenseur.ajouterRequete(new RequeteInterne(2));
        ascenseur.ajouterRequete(new RequeteInterne(0));
    }

    public void testBloquer() throws Exception {
        assertEquals(true,ascenseur.bloquer());
    }

    @Test
    public void testDebloquer() throws Exception {
        testBloquer();
        assertEquals(true,ascenseur.debloquer()); // Debloque par la suite
    }

    public void testEtat(int exceptedEtat, int etat) throws Exception {
        assertEquals(exceptedEtat,etat);
    }

    @Test
    public void testAction() throws Exception {
        // IL MONTE EN HAUT (PREMIER REQUETE)
        testEtat(Constantes.IMMOBILE_FERMER,ascenseur.getEtat());
        assertEquals(true,!ascenseur.getRequetes().isEmpty());
        ascenseur.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_HAUT,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_HAUT,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.IMMOBILE_OUVERT,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.IMMOBILE_FERMER,ascenseur.getEtat());

        // IL DESCEND EN BAS (SECONDE REQUETE)
        assertEquals(true,!ascenseur.getRequetes().isEmpty());
        ascenseur.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_BAS,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_BAS,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.IMMOBILE_OUVERT,ascenseur.getEtat());
        ascenseur.action();
        testEtat(Constantes.IMMOBILE_FERMER,ascenseur.getEtat());
    }
}