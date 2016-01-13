package ascenseur.traitement.ascenseur;

public class AscenseurAvecVitesse extends DecorateurAscenseur {

    public AscenseurAvecVitesse(IAscenseur ascenseur) {
        this.ascenseur = ascenseur;
    } // Constructeur

    @Override
    public String getLibelle() {
        return ascenseur.getLibelle() + " avec vitesse";
    }
}
