package ascenseur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ascenseur.affichage.Vue;
import ascenseur.affichage.VueConsole;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.fabrique.*;

/**
 * Created by Alexandre on 11/01/2016.
 */
public class Application {

    private boolean isRunning;
    private int nombreEtages;

    /**
     * Constructeur
     */
    private Application() {
        isRunning = true;
    }

    /**
     * Permet de mettre à jour les vues.
     * @see Vue
     */
    private void update() {
        Controleur controleur = Controleur.getInstance();

        Vue vue = new VueConsole();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = "";
        int vueChoix;

        System.out.println("Appuyez sur Entrée pour passer à l'itération suivante.");
        System.out.println("Tapez '0' pour accéder à la vue en coupe.");
        System.out.println("Tapez '1' pour accéder à la vue interactive.");
        System.out.println("Tapez '2' pour accéder à la vue listant les requêtes à satisfaire.");
        System.out.println("Tapez 'exit' pour quitter.");

        try {
            inputString = bufferedReader.readLine();
            if(inputString.equals("")) {
                System.out.println("Traitement des requêtes...\n");
                controleur.update();
                return;
            }
            if(inputString.equals("exit")) {
                isRunning = false;
                return;
            }
            try {
                vueChoix = Integer.parseInt(inputString);
                switch (vueChoix) {
                    case 0:
                        vue.getVueCoupe(Controleur.getInstance().getAscenseurs(),
                                Controleur.getInstance().getRequetesExternes(), nombreEtages);
                        break;
                    case 1:
                        vue.getVueInteractif(Controleur.getInstance().getAscenseurs(), nombreEtages);
                        break;
                    case 2:
                        vue.getVueRequetes(Controleur.getInstance().getAscenseurs(),
                                Controleur.getInstance().getRequetesExternes(), nombreEtages);
                        break;
                }
            }
            catch (NumberFormatException e ) {
                return;
            }
        }
        catch (IOException e) {
            System.out.println("Erreur de saisie!");
        }
    }

    /**
     * Permet de savoir si l'application est en cours d'éxécution.
     * @return Si l'application est en cours : TRUE, sinon FALSE.
     */
    private boolean isRunning() {
        return isRunning;
    }

    private void stop() {

    }

    /**
     * Exécution principale du programme.
     */
    public static void main(String[] args) {
        IFabrique fabrique;
        IAscenseur ascenseur;
        Application application = new Application();

        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choixTypeAscenseur;

        System.out.println("Initialisation");

        for(;;){
            System.out.println("Nombre d'étages à desservir :");
            try {
                line = bufferedReader.readLine();
                application.nombreEtages = Integer.parseInt(line);
                break;
            }
            catch (IOException|NumberFormatException e) {
                System.out.println("Erreur de saisie !");
            }
        }

        System.out.println("Nombre d'étages fixé à " + application.nombreEtages + ".\n");

        System.out.println("Création des ascenseurs...");

        for (int i = 0; ;) {
            System.out.println("\nAscenseur " + (i + 1) + " : ");

            System.out.println("Appuyez sur Entrée pour terminer la création des ascenseurs.");
            System.out.println("Tapez '0' pour créer un ascenseur simple.");
            System.out.println("Tapez '1' pour créer un ascenseur avec musique.");
            System.out.println("Tapez '2' pour créer un ascenseur avec vitesse.");
            System.out.println("Tapez '3' pour créer un ascenseur avec musique et vitesse.");
            try {
                line = bufferedReader.readLine();
                if(line.equals("")) break;
                choixTypeAscenseur = Integer.parseInt(line);
                switch(choixTypeAscenseur) {
                    case 0:
                        fabrique = new FabriqueAscenseurBasique();
                        break;
                    case 1:
                        fabrique = new FabriqueAscenseurMusique();
                        break;
                    case 2:
                        fabrique = new FabriqueAscenseurVitesse();
                        break;
                    case 3:
                        fabrique = new FabriqueAscenseurVitesseMusique();
                        break;
                    default:
                        throw new NumberFormatException();

                }
                ascenseur = fabrique.creer();
                Controleur.getInstance().ajouterAscenseur(ascenseur);
                System.out.println("\nAscenseur " + (++i) + " crée : " + ascenseur.getLibelle());
            }
            catch (IOException|NumberFormatException e) {
                System.out.println("\nErreur de saisie !");
            }
        }

        System.out.println("Création terminé !\n");

        while(application.isRunning()) {
            application.update();
        }
        application.stop();

    } // main()
}
