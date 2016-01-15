package ascenseur.traitement.ascenseur;

public class AscenseurAvecVitesse extends DecorateurAscenseur {

    /**
     * Constructeur
     * @param ascenseur L'ascenseur qui doit être décoré.
     */
    public AscenseurAvecVitesse(IAscenseur ascenseur) {
        this.ascenseur = ascenseur;
    } // Constructeur

    @Override
    public String getLibelle() {
        return ascenseur.getLibelle() + " avec vitesse";
    }
}
