package ascenseur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ascenseur.affichage.Vue;
import ascenseur.affichage.VueConsole;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.fabrique.FabriqueAscenseurBasique;
import ascenseur.traitement.fabrique.IFabrique;

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
        IFabrique fabrique = new FabriqueAscenseurBasique();
        Application application = new Application();

        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        int nombreAscenseurs;

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

        System.out.println("Nombre d'étages fixé à " + application.nombreEtages + ".");

        for(;;){
            System.out.println("Nombre d'ascenseurs :");
            try {
                line = bufferedReader.readLine();
                nombreAscenseurs = Integer.parseInt(line);
                break;
            }
            catch (IOException|NumberFormatException e) {
                System.out.println("Erreur de saisie !");
            }
        }

        System.out.println("Création de " + nombreAscenseurs + " ascenseurs...");

        for (int i = 0; i < nombreAscenseurs; ++i) {
            Controleur.getInstance().ajouterAscenseur(fabrique.creer());
        }

        System.out.println("Création terminé !\n");

        while(application.isRunning()) {
            application.update();
        }
        application.stop();

    } // main()
}
