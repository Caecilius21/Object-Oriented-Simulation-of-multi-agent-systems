package factorised.simulator;

import gui.GUISimulator;

import java.awt.*;

public class ConwayOld extends MultiAgentDiscretSimulator {
    static final private int VIVANT = 1;
    static final private int MORT = 0;

    public ConwayOld(GUISimulator gui) {
        super(gui, Color.WHITE, Color.BLACK);
    }

    @Override
    protected void evaluer(int i, int j) {
        int nbVivant = this.getNbVivant(i, j);
        switch (nbVivant) {
            case 3:
                this.setNewIJ(i, j, VIVANT);
                break;
            case 2:
                this.setNewIJ(i, j,  this.getCurrIJ(i, j));
                break;
            default:
                this.setNewIJ(i, j, MORT);
                break;
        }
    }

    private int getNbVivant(int i, int j) {
        int count = 0;
        for (int voisin: this.getVoisins(i, j)) {
            if (voisin == VIVANT) {
                count++;
            }
        }

        return count;
    }
}
