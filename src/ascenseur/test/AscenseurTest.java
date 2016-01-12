package ascenseur.test;

import ascenseur.traitement.ascenseur.AscenseurBasique;
import ascenseur.traitement.requete.RequeteInterne;
import ascenseur.traitement.util.Constantes;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Kevin on 06/01/2016.
 */
public class AscenseurTest {

    private static AscenseurBasique ascenseurBasique;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ascenseurBasique = new AscenseurBasique();
        ascenseurBasique.ajouterRequete(new RequeteInterne(2));
        ascenseurBasique.ajouterRequete(new RequeteInterne(0));
    } // setUpClass()

    public void testBloquer() throws Exception {
        ascenseurBasique.bloquer();
        assertEquals(true, ascenseurBasique.isBloqued());
    } // testBloquer()

    @Test
    public void testDebloquer() throws Exception {
        ascenseurBasique.debloquer();
        assertEquals(false, ascenseurBasique.isBloqued()); // Debloque par la suite
    } // testDebloquer()

    public void testEtat(int exceptedEtat, int etat) throws Exception {
        assertEquals(exceptedEtat,etat);
    } // testEtat()

    @Test
    public void testAction() throws Exception {
        // IL MONTE EN HAUT (PREMIER REQUETE)
        testEtat(Constantes.IMMOBILE_FERMER, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        assertEquals(true,!ascenseurBasique.getRequetes().isEmpty());
        ascenseurBasique.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_HAUT, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_HAUT, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.IMMOBILE_OUVERT, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.IMMOBILE_FERMER, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);


        // IL DESCEND EN BAS (SECONDE REQUETE)
        assertEquals(true,!ascenseurBasique.getRequetes().isEmpty());
        ascenseurBasique.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_BAS, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.MOUVEMENT_VERS_LE_BAS, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.IMMOBILE_OUVERT, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
        ascenseurBasique.action();
        testEtat(Constantes.IMMOBILE_FERMER, ascenseurBasique.getEtat());
        System.out.println(ascenseurBasique);
    } // testAction()
}