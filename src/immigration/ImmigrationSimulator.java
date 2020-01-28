package immigration;

import java.awt.*;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

public class ImmigrationSimulator implements Simulable {
    private GUISimulator gui;
    private int nbEtat;
    private int gridSize;
    private int grilleT_0[][];
    private int grilleT_1[][];

    ImmigrationSimulator(GUISimulator gui, int nbEtat, int gridSize) {
        this.gui = gui;
        this.nbEtat = nbEtat;
        this.gridSize = gridSize;
        this.grilleT_0 = new int[gridSize][gridSize];
        this.grilleT_1 = new int[gridSize][gridSize];

        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                this.grilleT_0[i][j] = (int)(Math.random() * (this.nbEtat));
                this.grilleT_1[i][j] = (int)(Math.random() * (this.nbEtat));
            }
        }
    }

    @Override
    public void next() {
        int voisins[];
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                voisins = this.getVoisins(i, j);
                this.grilleT_1[i][j] = this.evaluer(this.grilleT_0[i][j], voisins);
            }
        }

        // T_1 devient T_0
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                voisins = this.getVoisins(i, j);
                this.grilleT_0[i][j] = this.grilleT_1[i][j];
            }
        }

        this.updateGUI();
    }

    @Override
    public void restart() {

    }

    private int[] getVoisins(int i, int j) {
        /**
         * jm1 == 'j moins 1' == j-1
         * jp1 == 'j plus 1' == j+1
         */
        int im1 = i - 1;
        int ip1 = i + 1;
        int jm1 = j - 1;
        int jp1 = j + 1;

        if (i == 0) {
            im1 = this.gridSize - 1;
        } else if (i == this.gridSize - 1) {
            ip1 = 0;
        }

        if (j == 0) {
            jm1 = this.gridSize - 1;
        } else if (j == this.gridSize - 1) {
            jp1 = 0;
        }

        int[] voisins = {
                this.grilleT_0[im1][jm1],
                this.grilleT_0[im1][j],
                this.grilleT_0[im1][jp1],
                this.grilleT_0[i][jm1],
                this.grilleT_0[i][jp1],
                this.grilleT_0[ip1][jm1],
                this.grilleT_0[ip1][j],
                this.grilleT_0[ip1][jp1]
        };


        return voisins;
    }

    private void updateGUI() {
        this.gui.reset();
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                int layerGray = (this.grilleT_0[i][j] * 255) / this.nbEtat;
                Color couleur = new Color(layerGray, layerGray,layerGray);
                this.gui.addGraphicalElement(new Rectangle(i * 10, j * 10, couleur, couleur, 10, 10));
            }
        }
    }

    private int evaluer(int currentCell, int voisins[]) {
        int nbkp1 = 0;
        int etatSuivant = currentCell + 1 % this.nbEtat;
        for (int voisin: voisins) {
            if (voisin == etatSuivant) {
                nbkp1++;
            }
        }

        if (nbkp1 >= 3) {
            return etatSuivant;
        } else {
            return currentCell;
        }
    }


    @Override
    public String toString() {
        String str = "";

        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                str += this.grilleT_0[i][j] + " ";
            }
            str += "\n";
        }

        return str;
    }
}
