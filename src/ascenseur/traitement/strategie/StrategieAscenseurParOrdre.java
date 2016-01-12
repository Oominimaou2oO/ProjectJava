package ascenseur.traitement.strategie;

import ascenseur.traitement.Ascenseur;
import ascenseur.traitement.Constantes;

/**
 * Created by Kevin on 11/01/2016.
 */
public class StrategieAscenseurParOrdre implements IStrategieAscenseur {

    @Override
    public void action(Ascenseur asc) {
        switch (asc.getEtat()) {
            case Constantes.IMMOBILE_FERMER:
                if (!asc.getRequetes().isEmpty()) {
                    int etageCible = asc.getRequetes().getFirst().getEtage();
                    if (etageCible > asc.getEtage()) {
                        asc.setEtat(Constantes.MOUVEMENT_VERS_LE_HAUT);
                    } else if (etageCible < asc.getEtage()) {
                        asc.setEtat(Constantes.MOUVEMENT_VERS_LE_BAS);
                    } else {
                        asc.setEtat(Constantes.IMMOBILE_OUVERT);
                    }
                }
                break;
            case Constantes.IMMOBILE_OUVERT:
                for(int i = 1 ; i < asc.getRequetes().size() ; ++i) {
                    if (asc.getRequetes().get(i).getEtage() == asc.getRequetes().getFirst().getEtage())
                        asc.getRequetes().remove(i);
                }
                asc.getRequetes().removeFirst();
                if (asc.getRequetes().isEmpty() || asc.getRequetes().getFirst().getEtage() != asc.getEtage()) {
                    asc.setEtat(Constantes.IMMOBILE_FERMER);
                }
                break;
            case Constantes.MOUVEMENT_VERS_LE_BAS:
                asc.setEtage(asc.getEtage()-1);
                if (asc.getRequetes().getFirst().getEtage() == asc.getEtage()) {
                    asc.setEtat(Constantes.IMMOBILE_OUVERT);
                }
                break;
            case Constantes.MOUVEMENT_VERS_LE_HAUT:
                asc.setEtage(asc.getEtage()+1);
                if (asc.getRequetes().getFirst().getEtage() == asc.getEtage()) {
                    asc.setEtat(Constantes.IMMOBILE_OUVERT);
                }
                break;
        }
    } // action()

}
