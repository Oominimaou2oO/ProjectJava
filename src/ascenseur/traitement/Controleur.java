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
		// Pour le moment, fait de mani�re arbitraires
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
			
			System.out.println("Nombre d'�tages � desservir :");
			Controleur.getInstance().etages = inputScanner.nextInt();					
			System.out.println("Nombre d'�tages fix� � " + Controleur.getInstance().etages + ".");
			
			System.out.println("Nombre d'ascenseurs :");
			int nombreAscenseurs = inputScanner.nextInt();
			System.out.println("Cr�ation de " + nombreAscenseurs + " ascenseurs...");
			for(int i = 0; i < nombreAscenseurs; ++i) {
				Controleur.getInstance().ascenseurs.add(new Ascenseur());
			}			
			System.out.println("Cr�ation termin� !");

			System.out.println("Ajout de requ�tes internes");
			System.out.println("Ascenseur X");
			System.out.println("Etat : ETAT");
			System.out.println("Etage � desservir (Laisser vide pour passer � la suite) :");
		
			System.out.println("Ajout de l'�tage X � desservir pour l'ascenseur Y.");
			System.out.println("Ajout de requ�tes internes termin�es !");
			
			System.out.println("Ajout de requ�tes externes");
			System.out.println("Etage X :");
			System.out.println("Direction du d�placement (H[AUT] | B[AS]) (Laisser vide pour passer � la suite) :");
			System.out.println("Appel d'un ascenseur � l'�tage X, direction Y.");
			System.out.println("Ajout de requ�tes externes termin�es !");
			// Affichage de l'�tat
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
