package factorised.agent;

import factorised.simulator.SegregationCellularSimulator;

import java.util.ArrayList;
import java.util.LinkedList;

public class SegregationCell extends Cell {
    private int seuil;

    public SegregationCell(int i, int j, int currEtat) {
        super(i, j, currEtat);
        this.seuil = 4;
    }

    public void evaluer(ArrayList<Agent> voisins, LinkedList<SegregationCell> maisonsVides) {
        int countDiff = 0;

        for (Agent voisin: voisins) {
            if (((Cell) voisin).getEtat() != this.currEtat && ((Cell) voisin).getEtat() != Cell.nbEtats) {
                countDiff++;
            }
        }

        if (countDiff > this.seuil) {
            this.demenage(maisonsVides);
        } else {
            nextEtat = currEtat;
        }
    }

    private void demenage(LinkedList<SegregationCell> maisonsVides) {
        int iRandom = (int) (Math.random() * maisonsVides.size());
        if (iRandom != 0){
             SegregationCell maisonCible = maisonsVides.remove(iRandom);

            // On déplace la famille courante dans la maison en question
            maisonCible.nextEtat = this.currEtat;

            // On vide la maison ancienement habitée
            this.nextEtat = Cell.nbEtats;
            maisonsVides.addFirst(this);
        }
    }

    @Override
    public void evaluer(ArrayList<Agent> voisins) {

    }
}
