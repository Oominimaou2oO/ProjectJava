package ascenseur;

import ascenseur.affichage.Vue;
import ascenseur.affichage.VueSimplifiee;
import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.Controleur;
import ascenseur.traitement.fabrique.FabriqueAscenseur;
import ascenseur.traitement.fabrique.IFabrique;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Alexandre on 11/01/2016.
 */
public class Application {
    public static void main(String[] args) {

        Controleur controleur = Controleur.getInstance();
        Vue vue = new VueSimplifiee();
        Scanner inputScanner = new Scanner(System.in);
        String inputString;
        int nombreEtages;

        System.out.println("Initialisation");

        System.out.println("Nombre d'étages à desservir :");
        nombreEtages = inputScanner.nextInt();
        System.out.println("Nombre d'étages fixé à " + nombreEtages + ".");

        System.out.println("Nombre d'ascenseurs :");
        int nombreAscenseurs = inputScanner.nextInt();
        System.out.println("Création de " + nombreAscenseurs + " ascenseurs...");
        IFabrique fab = new FabriqueAscenseur();//ASCENSEUR BASIQUE
        for (int i = 0; i < nombreAscenseurs; ++i) {
            Controleur.getInstance().ajouterAscenseur(fab.creer());
        }
        System.out.println("Création terminé !\n");
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vueChoix;
        for(;;) {
            System.out.println("Appuyez sur Entrée pour passer à l'itération suivante.");
            System.out.println("Tapez '0' pour accéder à la vue en coupe.");
            System.out.println("Tapez '1' pour accéder à la vue interactive.");
            System.out.println("Tapez '2' pour accéder à la vue listant les requêtes à satisfaire.");
            System.out.println("Tapez 'exit' pour quitter.");
            try {
                line = bufferedReader.readLine();
                if(line.equals("")) {
                    System.out.println("Traitement des requêtes...\n");
                    controleur.update();
                    continue;
                }
                if(line.equals("exit")) break;
                try {
                    vueChoix = Integer.parseInt(line);
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
                    continue;
                }
            }
            catch (IOException e) {
                System.out.println("Erreur de saisie!");
            }
        }
        //System.out.println("Exit");
    } // main()
}
