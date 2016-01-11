package ascenseur.traitement;

import ascenseur.affichage.VueSimplifiee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import ascenseur.traitement.Fabrique.*;
import ascenseur.traitement.Strategie.IStrategieRequete;
import ascenseur.traitement.Strategie.StrategieRequeteArbitraire;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<Ascenseur> ascenseurs = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();
    private IStrategieRequete strategie;

    private int etages;

    private Controleur() {
        strategie = new StrategieRequeteArbitraire();
    } // Constructor

    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    public void choisirAscenseur() {
        strategie.choisirAscenseur();
    } // choisirAscenseur()

    public LinkedList<RequeteExterne> getRequetesExternes() {
        return requetesExternes;
    } // getRequetesExternes()

    public int getEtages() {
        return etages;
    } // getEtages()

    public List<Ascenseur> getAscenseurs() {
        return ascenseurs;
    } //getAscenseurs()

    public static void main(String[] args) {
        String inputString;
        Scanner inputScanner = new Scanner(System.in);
        VueSimplifiee vueSimplifiee = new VueSimplifiee();

        System.out.println("Initialisation");

        System.out.println("Nombre d'étages à desservir :");
        Controleur.getInstance().etages = inputScanner.nextInt();
        System.out.println("Nombre d'étages fixé à " + Controleur.getInstance().etages + ".");

        System.out.println("Nombre d'ascenseurs :");
        int nombreAscenseurs = inputScanner.nextInt();
        System.out.println("Création de " + nombreAscenseurs + " ascenseurs...");
        IFabrique fab = new FabriqueAscenseur();//ASCENSEUR BASIQUE
        for (int i = 0; i < nombreAscenseurs; ++i) {
            Controleur.getInstance().ascenseurs.add(fab.creer());
        }
        System.out.println("Création terminé !\n");
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vue;
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
                    for(Ascenseur ascenseur : Controleur.getInstance().ascenseurs)
                        ascenseur.action();
                    continue;
                }
                if(line.equals("exit")) break;
                try {
                    vue = Integer.parseInt(line);
                    switch (vue) {
                        case 0:
                            vueSimplifiee.getVueCoupe();
                            break;
                        case 1:
                            vueSimplifiee.getVueInteractif();
                            break;
                        case 2:
                            vueSimplifiee.getVueRequetes();
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
