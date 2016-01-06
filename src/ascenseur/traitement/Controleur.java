package ascenseur.traitement;

import ascenseur.affichage.VueSimplifiee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Controleur {

    private static Controleur instance = new Controleur();
    private List<Ascenseur> ascenseurs = new ArrayList<>();
    private LinkedList<RequeteExterne> requetesExternes = new LinkedList<>();

    private int etages;

    private Controleur() {
    } // Constructor

    public static Controleur getInstance() {
        return instance;
    } // getInstance()

    public void creerRequeteExterne(int etage, int direction) {
        requetesExternes.add(new RequeteExterne(etage, direction));
    } // creerRequeteExterne()

    public void choisirAscenseur() {
        // Pour le moment, fait de manière arbitraires
        for (int i = 0; i < requetesExternes.size(); ++i) {
            ascenseurs.get(i).ajouterRequete(requetesExternes.getFirst());
            requetesExternes.removeFirst();
        }
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
        for (int i = 0; i < nombreAscenseurs; ++i) {
            Controleur.getInstance().ascenseurs.add(new Ascenseur());
        }
        System.out.println("Création terminé !");
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vue;
        for(;;) {

            System.out.println("Appuyez sur Entrée pour passer à litération suivante., ecrivez 'exit' pour quitter.");
            System.out.println("Tapez '0' pour accéder à la vue en coupe.");
            System.out.println("Tapez '1' pour accéder à la vue interactive.");
            System.out.println("Tapez '2' pour accéder à la vue listant les requêtes à satisfaire.");
            System.out.println("Tapez 'exit' pour quitter.");
            try {
                line = bufferedReader.readLine();
                if(line.equals("")) {
                    for(Ascenseur ascenseur : Controleur.getInstance().ascenseurs)
                        ascenseur.action();
                    continue;
                }
                if(line.equals("exit")) break;
                vue = Integer.parseInt(line);
                switch (vue) {
                    case 0:
                        vueSimplifiee.getVue0();
                        break;
                    case 1:
                        vueSimplifiee.getVue1();
                        break;
                    case 2:
                        vueSimplifiee.getVue2();
                        break;
                }
            }
            catch (IOException e) {
                System.out.println("Erreur de saisie!");
            }

        }
        //System.out.println("Exit");
    } // main()
}
