package ascenseur.traitement.strategie;

import ascenseur.traitement.ascenseur.IAscenseur;
import ascenseur.traitement.requete.Requete;
import ascenseur.traitement.requete.RequeteExterne;
import ascenseur.traitement.util.Constantes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class AttributionRequetesIntelligente implements AttributionRequetes {
    class EtageAscenseurComparator implements Comparator<IAscenseur> {
        @Override
        public int compare(IAscenseur iAscenseur, IAscenseur t1) {
            return iAscenseur.getEtage()- t1.getEtage();
        }
    }


    interface Comparaison_Requete_Ascenseur {
        public int compare (IAscenseur iA, Requete i2);
    }

    class Comparaison_Requete_Ascenseur_Montante implements Comparaison_Requete_Ascenseur {

        @Override
        public int compare(IAscenseur iA, Requete i2) {
            return i2.getEtage() - iA.getEtage();
        }
    }

    class Comparaison_Requete_Ascenseur_Descendante implements Comparaison_Requete_Ascenseur {

        @Override
        public int compare(IAscenseur iA, Requete i2) {
            return iA.getEtage() - i2.getEtage();
        }
    }


    @Override
    public void choisirAscenseur(Collection<IAscenseur> ascenseurs, Collection<RequeteExterne> requetesExternes) {


        ArrayList<IAscenseur> monte = new ArrayList<>();
        ArrayList<IAscenseur> descend = new ArrayList<>();

        Comparaison_Requete_Ascenseur comparator = null;//Future Pointeur
        ArrayList<IAscenseur>  actual;//Futur Pointeur

        int pas;//Futur delta

        for (IAscenseur asc : ascenseurs) //repartition des ascensseurs en fonction de leurs mouvements
            switch (asc.getEtat()){
                default://arrêté
                    monte.add(asc);
                case Constantes.MOUVEMENT_VERS_LE_BAS:
                    descend.add(asc);
                    break;
                case Constantes.MOUVEMENT_VERS_LE_HAUT:
                    monte.add(asc);
            }




        Collections.sort(descend,new EtageAscenseurComparator());
        Collections.sort(monte,new EtageAscenseurComparator());



        int i = 0;
        int endVal;



        for (RequeteExterne r : requetesExternes) {
            switch (r.getDirection()) {
                default:
                case Constantes.MOUVEMENT_VERS_LE_HAUT:
                    comparator = new Comparaison_Requete_Ascenseur_Montante();//Fixage du pointeur de comparaison
                    actual = monte;//Fixage du pointeurs de tableau
                    pas = 1;//fixage du pas en fonction de la direction de parcours de tableau
                    endVal = monte.size();
                    break;


                case Constantes.MOUVEMENT_VERS_LE_BAS:
                    comparator = new Comparaison_Requete_Ascenseur_Descendante();
                    actual = descend;//Fixage du pointeurs de tableau

                    pas = -1;//fixage du pas en fonction de la direction de parcours de tableau
                    i = descend.size()-1;//Rêglage du I en fonction du sens de parcours de tableau

                    endVal = 0;
                    break;

            }

            for (;(comparator.compare(actual.get(i),r))<0 && i!=endVal; i+=pas );

            if ((pas == 1 && i == 0) || (pas == -1 && i == descend.size() - 1)) {
                //cas embétant où il n'y a pas d'ascenseur bien placé
                continue;
            } else
                actual.get(i-pas).ajouterRequete(r);
        }

    }


    }
