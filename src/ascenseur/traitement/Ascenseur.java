package ascenseur.traitement;

import java.util.ArrayList;
import java.util.List;

public class Ascenseur {
	private int etat = Constantes.IMMOBILE_FERME;
	private int etage = 0;
	private boolean bloquer = false;
	
	private List<Requete> requetes = new ArrayList<Requete>();	
	
	public void bloquer() {
		this.bloquer = true;
	}
	
	public void debloquer() {
		this.bloquer = false;
	}
	
	public void ajouterRequete(Requete requete) {
		requetes.add(requete);
	}
	
	public void creerRequeteInterne(int etage) {
		requetes.add(new RequeteInterne(etage));
	}
	
	public void action() {
		if(!bloquer) {
			switch(etat) {
			case Constantes.IMMOBILE_FERME:
				if(!requetes.isEmpty()) {
					int etageCible = requetes.get(requetes.size()-1).getEtage();
					if(etageCible > etage) {
						etat = Constantes.MOUVEMENT_VERS_LE_HAUT;
					}
					else if(etageCible < etage) {
						--etage;
						etat = Constantes.MOUVEMENT_VERS_LE_BAS;
					}
					else {
						etat = Constantes.IMMOBILE_OUVERT;
					}								
				}
				break;
			case Constantes.IMMOBILE_OUVERT:
				if(requetes.isEmpty() || requetes.get(requetes.size()-1).getEtage() != etage) {
					etat = Constantes.IMMOBILE_FERME;
				}
				break;
			case Constantes.MOUVEMENT_VERS_LE_BAS:
				--etage;
				if(!requetes.isEmpty()) {
					int etageCible = requetes.get(requetes.size()-1).getEtage();
					if(etageCible == etage) {
						etat = Constantes.IMMOBILE_OUVERT;
					}
				}
				break;
			case Constantes.MOUVEMENT_VERS_LE_HAUT:
				++etage;
				if(!requetes.isEmpty()) {
					int etageCible = requetes.get(requetes.size()-1).getEtage();
					if(etageCible == etage) {
						etat = Constantes.IMMOBILE_OUVERT;
					}
				}
				break;
			}				
		}
		//Statisfaire les requete de l'étage actuel
	}
	
	public String toString() {
		String affichage;
		switch(etat) {
		case Constantes.IMMOBILE_FERME:
			affichage = "[|]";
			break;
		case Constantes.IMMOBILE_OUVERT:
			affichage = "[ ]";
			break;
		case Constantes.MOUVEMENT_VERS_LE_BAS:
			affichage = "[v]";
			break;
		case Constantes.MOUVEMENT_VERS_LE_HAUT:
			affichage = "[^]";
			break;
		default:
			affichage = "[|]";
		}
		return affichage;
	}

	public int getEtage() {
		return this.etage;
	}
	
	public int getEtat() {
		return this.etat;
	}

	public boolean isBloqued() {
		return this.bloquer;
	}
}
