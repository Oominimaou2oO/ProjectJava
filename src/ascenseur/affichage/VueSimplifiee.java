package ascenseur.affichage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ascenseur.traitement.*;

public class VueSimplifiee {
	public void getVueCoupe(List<Ascenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages) {
		String affichage = "            ";
		System.out.println("VUE EN COUPE");

		for(int i = 0; i < ascenseurs.size(); ++i) {
			affichage += "Ascenseur " + (i + 1) + "    ";
		}
		
		System.out.println(affichage + "    Boutons activés");
		for(int i = nombreEtages - 1; i >= 0; --i) {
			affichage = "Etage " + i;
			for(int j = 0; j < 10  - Integer.toString(i).length(); ++j) {
				affichage += " ";
			}
			for(Ascenseur ascenseur : ascenseurs) {
				if(ascenseur.getEtage() == i) {
					affichage += ascenseur + "            ";
				}
				else {
					affichage += " '             ";
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
            for(Requete requete : ascenseur.getRequetes()) {
                etagesPressed.add(requete.getEtage());
            }
			
			affichage = "Ascenseur " + (i + 1) + " : ";
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
		
	} //getVueCoupe()
	
	public void getVueInteractif(List<Ascenseur> ascenseurs, int nombreEtages) {
		// Boutons des panneaux de controle (bloquage aussi)
		System.out.println("AJOUTER DES REQUÊTES INTERNES");

		Ascenseur ascenseur;

		String etat;
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < ascenseurs.size(); ++i) {
			ascenseur = ascenseurs.get(i);
			switch (ascenseur.getEtat()) {
				case Constantes.IMMOBILE_OUVERT:
					etat = "Immobile ouvert";
					break;
				case Constantes.MOUVEMENT_VERS_LE_BAS:
					etat = "Mouvement vers le bas";
					break;
				case Constantes.MOUVEMENT_VERS_LE_HAUT:
					etat = "Mouvement vers le haut";
					break;
				default:
					etat = "Immobile fermé";
			}
			System.out.println("Ascenseur " + (i + 1));
			System.out.println("Etat : " + etat);
            int etage;
            for(;;){
                System.out.println("Etage à desservir (Laisser vide pour passer à la suite) :");
                try {
                    line = bufferedReader.readLine();
                    if(line.equals("")) break;
                    etage = Integer.parseInt(line);
                    if(etage >= nombreEtages) {
                        System.out.println("Cet étage n'existe pas. Les étages vont de 0 à " + (nombreEtages - 1) + ".");
                        continue;
                    }
                    ascenseur.creerRequeteInterne(etage);
                    System.out.println("Requete interne ajouté vers l'étage " + etage + ".");
                }
                catch (IOException|NumberFormatException e) {
                    System.out.println("Erreur de saisie !");
                }
            }
            System.out.println("Ajout de requêtes internes terminées !\n");
		}
        System.out.println("AJOUTER DES REQUÊTES EXTERNES");
        for(int i = 0; i < nombreEtages; ++i) {
            System.out.println("Etage " + i + " :");
            for(;;) {
                System.out.println("Direction du déplacement (H[AUT] | B[AS]) (Laisser vide pour passer à la suite) :");
                try {
                    line = bufferedReader.readLine();
                    if(line.equals("")) break;
                    if(line.toLowerCase().equals("haut") || line.toLowerCase().equals("h")) {
                        Controleur.getInstance().creerRequeteExterne(i, Constantes.MOUVEMENT_VERS_LE_HAUT);
                        System.out.println("Requete externe ajouté à l'étage " + i + " vers le haut.");
                    }
                    else if(line.toLowerCase().equals("bas") || line.toLowerCase().equals("b")) {
                        Controleur.getInstance().creerRequeteExterne(i, Constantes.MOUVEMENT_VERS_LE_BAS);
                        System.out.println("Requete externe ajouté à l'étage " + i + " vers le bas.");
                    }
                }
                catch (IOException e) {
                    System.out.println("Erreur de saisie !");
                }
            }
        }
        System.out.println("Ajout de requêtes externes terminées !");
	} // getVueInteractif()

    public void getVueRequetes(List<Ascenseur> ascenseurs, List<RequeteExterne> requetesExternes, int nombreEtages) {
        List<Requete> requetes;
        String affichage = "LISTE DES REQUÊTES A SATISFAIRE";
        Ascenseur ascenseur;

		for(int i = 0; i < nombreEtages; ++i) {
            System.out.println("Etage " + i);
            for (RequeteExterne requetesExterne : requetesExternes) {
                if(requetesExterne.getEtage() == i) {
                    System.out.println("Requete externe en direction du " + requetesExternes + ".");
                }
            }
            for (int indexAscenseur = 0; indexAscenseur < ascenseurs.size(); ++indexAscenseur) {
                ascenseur = ascenseurs.get(indexAscenseur);
                requetes = ascenseur.getRequetes();
                for (Requete requete : requetes) {
                    if(requete.getEtage() == i) {
                        System.out.println("Requete interne de l'ascenseur " + (indexAscenseur + 1) + ".");
                    }
                }
            }
            System.out.println("");
        }
    } // getVueRequetes()
}
