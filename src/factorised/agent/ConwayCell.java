package factorised.agent;

import java.util.ArrayList;

public class ConwayCell extends Cell {
    private final static int VIVANT = 1;
    private final static int MORT = 0;

    public ConwayCell(int i, int j, int currEtat) {
        super(i, j, currEtat);
    }


    private int getNbVivant(ArrayList<Agent> agents) {
        int count = 0;
        for(Agent agent: agents) {
            if (((ConwayCell) agent).currEtat == VIVANT) {
                count ++;
            }
        }
        return count;
    }

    @Override
    public void evaluer(ArrayList<Agent> voisins) {
        int nbVivant = this.getNbVivant(voisins);
        switch (nbVivant) {
            case 3:
                this.nextEtat = VIVANT;
                break;
            case 2:
                this.nextEtat = this.currEtat;
                break;
            default:
                this.nextEtat = MORT;
                break;
        }
    }
}