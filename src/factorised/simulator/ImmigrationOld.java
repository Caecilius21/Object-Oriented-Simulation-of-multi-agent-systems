package factorised.simulator;

import gui.GUISimulator;

import java.awt.*;

public class ImmigrationOld extends MultiAgentDiscretSimulator {
    public ImmigrationOld(GUISimulator gui, Color... colors) {
        super(gui, colors);
    }

    @Override
    protected void evaluer(int i, int j) {
        int nbkp1 = 0;
        int etatSuivant = this.getCurrIJ(i, j) + 1 % this.nbEtat;
        for (int voisin: this.getVoisins(i, j)) {
            if (voisin == etatSuivant) {
                nbkp1++;
            }
        }

        if (nbkp1 >= 4) {
            this.setNewIJ(i, j, etatSuivant);
        } else {
            this.setNewIJ(i, j, this.getCurrIJ(i, j));
        }
    }
}
