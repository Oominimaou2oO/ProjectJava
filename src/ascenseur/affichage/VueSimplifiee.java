package ascenseur.affichage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.Constantes;
import ascenseur.traitement.RequeteExterne;
import ascenseur.traitement.RequeteInterne;

public class VueSimplifiee {
	public void getVue1(int nombreEtages, List<Ascenseur> ascenseurs, 
			List<RequeteExterne> requetesExternes, Map<Ascenseur, List<RequeteInterne>> listRequetesInternes) {
				
		String affichage= "VUE EN COUPE";
		List<RequeteInterne> requetesInternes = new ArrayList<>();

		for(int i = 0; i < 4 + 11 * ascenseurs.size(); ++i) {
			affichage += " ";
		}
		
		System.out.println(affichage + "Boutons activés");
		for(int i = nombreEtages; i >= 0; --i) {
			affichage = "Etage " + i;
			for(int j = 0; j < 10  - Integer.toString(i).length(); ++j) {
				affichage += " ";
			}
			for(Ascenseur ascenseur : ascenseurs) {
				if(ascenseur.getEtage() == i) {
					affichage += ascenseur + "        ";
				}
				else {
					affichage += " '         ";
				}
			}
			for(RequeteExterne requeteExterne : requetesExternes) {
				if(requeteExterne.getEtage() == i) {
					affichage += requeteExterne + " ";
				}
			}
			System.out.println(affichage);
		}
		
		System.out.println("\nPanneaux de controle des ascenseurs (Bouton activé) : ");
		List<Integer> etagesPressed = new ArrayList<>();
		Ascenseur ascenseur;
		for(int i = 0; i < ascenseurs.size(); ++ i) {
			etagesPressed.clear();
			ascenseur = ascenseurs.get(i);
			requetesInternes = listRequetesInternes.get(ascenseur);
			
			if(requetesInternes != null) {
				for(RequeteInterne requeteInterne : requetesInternes) {
					etagesPressed.add(requeteInterne.getEtage());			
				}
			}
			
			affichage = "Ascenseur " + i + " : ";
			for(int j = 0; j < nombreEtages; ++j) {
				if(etagesPressed.contains(j))
					affichage += "(" + j + ") ";
				else
					affichage += " " + j + "  ";
			}
			if(ascenseur.isBloqued())
				affichage += "Bloqué !";
			System.out.println(affichage);
		}
		
	} //getVue1() 
	
	public void getVue2() {
		// Boutons des panneaux de controle (bloquage aussi)
		
		// Boutons de chaque palier
	} // getVue2()
	
	public static void main(String[] args) {
		VueSimplifiee vs = new VueSimplifiee();
		List<Ascenseur> ascenseurs = new ArrayList<>();
		List<RequeteExterne> requetesExternes = new ArrayList<>();
		List<RequeteInterne> requetesInternes = new ArrayList<>();
		
		Map<Ascenseur, List<RequeteInterne>> listRequetesInternes = new HashMap<>();
		
		ascenseurs.add(new Ascenseur());
		ascenseurs.add(new Ascenseur());
		ascenseurs.add(new Ascenseur());
		ascenseurs.add(new Ascenseur());
		
		requetesExternes.add(new RequeteExterne(2, Constantes.MOUVEMENT_VERS_LE_BAS));
		
		requetesInternes.add(new RequeteInterne(2));
		requetesInternes.add(new RequeteInterne(4));
		listRequetesInternes.put(ascenseurs.get(0), requetesInternes);
		
		requetesInternes = new ArrayList<>();
		
		requetesInternes.add(new RequeteInterne(1));
		requetesInternes.add(new RequeteInterne(3));
		
		listRequetesInternes.put(ascenseurs.get(2), requetesInternes);		

		vs.getVue1(5, ascenseurs, requetesExternes, listRequetesInternes);
	} // main()
}
