package ascenseur.traitement;

public class AscenseurAvecAugmentationVitesse extends DecorateurAscenseur {

    public AscenseurAvecAugmentationVitesse(Ascenseur ascenseur) {
        this.ascenseur = ascenseur;
        this.libelle = "Ascenseur avec vitesse & ";
    } // Constructor
}