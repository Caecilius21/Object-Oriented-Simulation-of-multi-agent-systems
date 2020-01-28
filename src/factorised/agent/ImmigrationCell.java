package factorised.agent;

import java.util.ArrayList;

public class ImmigrationCell extends Cell {
    public ImmigrationCell(int i, int j, int currEtat) {
        super(i, j, currEtat);
    }

    @Override
    public void evaluer(ArrayList<Agent> voisins) {
        int voisinDiff = 0;
        int etatSuivant = this.currEtat + 1 % Cell.nbEtats;
        for (Agent voisin: voisins) {
            if (((Cell) voisin).getEtat() == etatSuivant) {
                voisinDiff++;
            }
        }

        if (voisinDiff >= 4) {
            this.nextEtat = etatSuivant;
        } else {
            this.nextEtat = this.currEtat;
        }
    }
}
