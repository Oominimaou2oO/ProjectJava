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
	public void getVueCoupe() {
		List<Ascenseur> ascenseurs = Controleur.getInstance().getAscenseurs();
		List<RequeteExterne> requetesExternes = Controleur.getInstance().getRequetesExternes();
		int nombreEtages = Controleur.getInstance().getEtages();

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
        List<Requete> requetesInternes = new ArrayList<>();
        Ascenseur ascenseur;
		for(int i = 0; i < ascenseurs.size(); ++ i) {
			etagesPressed.clear();
			ascenseur = ascenseurs.get(i);
            requetesInternes = ascenseur.getRequetes();
			
			if(requetesInternes != null) {
				for(Requete requete : requetesInternes) {
					etagesPressed.add(requete.getEtage());
				}
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
	
	public void getVueInteractif() {
		// Boutons des panneaux de controle (bloquage aussi)
		System.out.println("AJOUTER DES REQUÊTES INTERNES");
		List<Ascenseur> ascenseurs = Controleur.getInstance().getAscenseurs();
		Ascenseur ascenseur;
        int etages = Controleur.getInstance().getEtages();
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
                    if(etage >= etages) {
                        System.out.println("Cet étage n'existe pas. Les étages vont de 0 à " + (etages - 1) + ".");
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
        for(int i = 0; i < etages; ++i) {
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
                    else {
                        continue;
                    }
                }
                catch (IOException e) {
                    System.out.println("Erreur de saisie !");
                }
            }
        }
        System.out.println("Ajout de requêtes externes terminées !");
	} // getVueInteractif()

    public void getVueRequetes() {
		List<Ascenseur> ascenseurs = Controleur.getInstance().getAscenseurs();
		List<RequeteExterne> requetesExternes = Controleur.getInstance().getRequetesExternes();
        List<Requete> requetes;
        String affichage = "LISTE DES REQUÊTES A SATISFAIRE";
        Requete requete;
        Ascenseur ascenseur;

		int nombreEtages = Controleur.getInstance().getEtages();

		for(int i = 0; i < nombreEtages; ++i) {
            System.out.println("Etage " + i);
            for (int indexRequete = 0; indexRequete < requetesExternes.size(); ++indexRequete) {
                requete = requetesExternes.get(indexRequete);
                if(requete.getEtage() == i) {
                    System.out.println("Requete externe en direction du " + requete + ".");
                }
            }
            for (int indexAscenseur = 0; indexAscenseur < ascenseurs.size(); ++indexAscenseur) {
                ascenseur = ascenseurs.get(indexAscenseur);
                requetes = ascenseur.getRequetes();
                for (int indexRequete = 0; indexRequete < requetes.size(); ++indexRequete) {
                    requete = requetes.get(indexRequete);
                    if(requete.getEtage() == i) {
                        System.out.println("Requete interne de l'ascenseur " + (indexAscenseur + 1) + ".");
                    }
                }
            }
            System.out.println("");
        }
    } // getVueRequetes()
}
