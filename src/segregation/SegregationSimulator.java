package segregation;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

public class SegregationSimulator implements Simulable {
    private GUISimulator gui;
    private ArrayList<Color> colors;
    private int gridSize;
    private int seuil;
    private int grilleT_0[][];
    private int grilleT_1[][];
    private LinkedList<Point> maisonLibres;

    public SegregationSimulator(GUISimulator gui, ArrayList<Color> colors, int seuil, int gridSize) throws IllegalArgumentException {
        if (seuil > 8 || seuil < 1 || gridSize < 1) {
            throw new IllegalArgumentException();
        }

        this.gui = gui;
        this.colors = colors;
        this.gridSize = gridSize;
        this.seuil = seuil;
        this.grilleT_0 = new int[this.gridSize][this.gridSize];
        this.grilleT_1 = new int[this.gridSize][this.gridSize];
        this.maisonLibres = new LinkedList<Point>();


        for (int i = 0 ; i < gridSize ; i++) {
            for (int j = 0 ; j < gridSize ; j++) {
                // 20% de chanche de mettre un couleur
                if (Math.random() > 0.5) {
                    this.grilleT_0[i][j] = (int)(Math.random() * (this.colors.size()));
                    this.grilleT_1[i][j] = (int)(Math.random() * (this.colors.size()));
                } else {
                    // Si une cellule == colors.length alors la maison est vide
                    this.maisonLibres.add(new Point(i, j));
                    this.grilleT_0[i][j] = this.colors.size();
                    this.grilleT_1[i][j] = this.colors.size();
                }
            }
        }
    }

    @Override
    public void next() {
        int voisins[];
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                voisins = this.getVoisins(i, j);
                if (this.evaluer(this.grilleT_0[i][j], voisins)) {
                    this.demenage(i, j);
                }
            }
        }

        // T_1 devient T_0
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
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
        Color couleur;
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                if (this.grilleT_0[i][j] != this.colors.size()) {
                    couleur = this.colors.get(this.grilleT_0[i][j]);
                } else {
                    couleur = Color.BLACK;
                }

                this.gui.addGraphicalElement(new Rectangle(i * 10, j * 10, couleur, couleur, 10, 10));
            }
        }
    }

    private Boolean evaluer(int currentCell, int voisins[]) {
        int countDiff = 0;

        for (int voisin: voisins) {
            if (voisin != currentCell && currentCell != this.colors.size()) {
                countDiff++;
            }
        }
        return countDiff > this.seuil;
    }

    /**
     * Fait démanager la maison de coordonnées (i, j) vers une maison libre aléatoire
     */
    private void demenage(int i, int j) {
        // récupérer une maison libre de manière aléatoire.
        int iRandom = (int) (Math.random() * this.maisonLibres.size());
        Point coord_dest = this.maisonLibres.remove(iRandom);
        // On déplace la famille courante dans la maison en question
        this.grilleT_1[(int) coord_dest.getX()][(int) coord_dest.getY()] = this.grilleT_0[i][j];

        // On vide la maison ancienement habitée
        this.grilleT_1[i][j] = this.colors.size();
        this.maisonLibres.add(new Point(i, j));
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
