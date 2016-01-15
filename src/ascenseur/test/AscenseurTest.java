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

    /**
     * Permet de définir un comportement de base, afin de procéder à des tests concrets.
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        ascenseurBasique = new AscenseurBasique();
        ascenseurBasique.ajouterRequete(new RequeteInterne(2));
        ascenseurBasique.ajouterRequete(new RequeteInterne(0));
    } // setUpClass()

    /**
     * Permet de vérifier que l'ascenseur subit bien l'action bloqué.
     * @throws Exception
     */
    @Test
    public void testBloquer() throws Exception {
        ascenseurBasique.bloquer();
        assertEquals(true, ascenseurBasique.isBloqued());
    } // testBloquer()

    /**
     * Permet de vérifier que l'ascenseur subit bien l'action débloqué.
     * @throws Exception
     */
    @Test
    public void testDebloquer() throws Exception {
        ascenseurBasique.debloquer();
        assertEquals(false, ascenseurBasique.isBloqued());
    } // testDebloquer()

    /**
     * Macro de assertEquals, afin d'aller plus vite.
     * @param exceptedEtat L'état voulu.
     * @param etat L'état courant.
     * @throws Exception
     */
    public void testEtat(int exceptedEtat, int etat) throws Exception {
        assertEquals(exceptedEtat,etat);
    } // testEtat()

    /**
     * Permet de vérifier que tout ce passe bien lorsqu'on appele la fonction action() sur l'ascenseur.
     * @throws Exception
     */
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