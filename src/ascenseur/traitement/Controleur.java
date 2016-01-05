package ascenseur.traitement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Controleur {
	private static Controleur instance = new Controleur();
	
	private int etages;
	private List<Ascenseur> ascenseurs = new ArrayList<>();
	private LinkedList<Requete> requetesExternes = new LinkedList<>();
	
	private Controleur() {} // Constructor
	
	public static Controleur getInstance() {
		return instance;
	} // getInstance()
	
	public void creerRequeteExterne(int etage, int direction) {
		requetesExternes.add(new RequeteExterne(etage, direction));
	} // creerRequeteExterne()
	
	public void choisirAscenseur() {
		// Distribuer les requetes aux ascenseurs
		// Pour le moment, fait de manière arbitraires
		for(int i = 0; i< requetesExternes.size(); ++i) {
			ascenseurs.get(i).ajouterRequete(requetesExternes.get(i));
			//requetesExternes.removeFirst();
		}
		requetesExternes.clear();
	} // choisirAscenseur()
	
	public static void main(String[] args) {
		String inputString;
		Scanner inputScanner = new Scanner(System.in);
		try {
			System.out.println("Initialisation");
			
			System.out.println("Nombre d'étages à desservir :");
			Controleur.getInstance().etages = inputScanner.nextInt();					
			System.out.println("Nombre d'étages fixé à " + Controleur.getInstance().etages + ".");
			
			System.out.println("Nombre d'ascenseurs :");
			int nombreAscenseurs = inputScanner.nextInt();
			System.out.println("Création de " + nombreAscenseurs + " ascenseurs...");
			for(int i = 0; i < nombreAscenseurs; ++i) {
				Controleur.getInstance().ascenseurs.add(new Ascenseur());
			}			
			System.out.println("Création terminé !");

			System.out.println("Ajout de requêtes internes");
			System.out.println("Ascenseur X");
			System.out.println("Etat : ETAT");
			System.out.println("Etage à desservir (Laisser vide pour passer à la suite) :");
		
			System.out.println("Ajout de l'étage X à desservir pour l'ascenseur Y.");
			System.out.println("Ajout de requêtes internes terminées !");
			
			System.out.println("Ajout de requêtes externes");
			System.out.println("Etage X :");
			System.out.println("Direction du déplacement (H[AUT] | B[AS]) (Laisser vide pour passer à la suite) :");
			System.out.println("Appel d'un ascenseur à l'étage X, direction Y.");
			System.out.println("Ajout de requêtes externes terminées !");
			// Affichage de l'état
			for(inputString = inputScanner.nextLine(); !inputString.isEmpty(); inputString = inputScanner.nextLine()) {
		        System.out.println(inputString);
		    }
		}
		finally {
			inputScanner.close();
		}
		System.out.println("Exit");
	} // main()
}
