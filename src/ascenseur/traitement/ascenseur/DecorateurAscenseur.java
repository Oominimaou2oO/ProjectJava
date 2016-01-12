package ascenseur.traitement.ascenseur;

import ascenseur.traitement.requete.Requete;

import java.util.LinkedList;

public abstract class DecorateurAscenseur implements IAscenseur {

	protected IAscenseur ascenseur;

	@Override
	public void bloquer() {
		ascenseur.bloquer();
	}

	@Override
	public void debloquer() {
		ascenseur.debloquer();
	}

	@Override
	public void ajouterRequete(Requete requete) {
		ascenseur.ajouterRequete(requete);
	}

	@Override
	public void creerRequeteInterne(int etage) {
		ascenseur.creerRequeteInterne(etage);
	}

	@Override
	public void action() {
		ascenseur.action();
	}

	@Override
	public int getEtage() {
		return ascenseur.getEtage();
	}

	@Override
	public int getEtat() {
		return ascenseur.getEtat();
	}

	@Override
	public boolean isBloqued() {
		return ascenseur.isBloqued();
	}

	@Override
	public LinkedList<Requete> getRequetes() {
		return ascenseur.getRequetes();
	}

	@Override
	public String toString() {
		return ascenseur.toString();
	}

	@Override
	public String getLibelle() {
		return ascenseur.getLibelle();
	}
}
