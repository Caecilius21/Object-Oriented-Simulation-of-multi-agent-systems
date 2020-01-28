package factorised.simulator;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public abstract class MultiAgentDiscretSimulator implements Simulable {
    protected GUISimulator gui;
    protected Color colors[];
    protected int nbEtat;
    private int gridHeight;
    private int gridWidth;
    private int grilles[][][];
    protected int saveGrille;
    private int currentGrille;
    private int cellSize;
    private int gutter;

    MultiAgentDiscretSimulator(GUISimulator gui, Color ...colors) {
        this.gui = gui;
        this.gutter = 10;
        this.cellSize = 10;
        this.gridHeight = (gui.getHeight() / this.cellSize) - 1;
        this.gridWidth = (gui.getWidth() / this.cellSize) - 1;
        this.nbEtat = colors.length;
        this.currentGrille = 0;
        this.saveGrille = 1;
        this.grilles = new int[2][this.gridWidth][this.gridHeight];
        this.colors = colors;

        for (int i = 0 ; i < this.gridWidth ; i++) {
            for (int j = 0 ; j < this.gridHeight ; j++) {
                this.initValue(i, j);
            }
        }
    }

    @Override
    public void next() {
        int voisins[];
        for (int i = 0 ; i < this.gridWidth ; i++) {
            for (int j = 0 ; j < this.gridHeight ; j++) {
                this.evaluer(i, j);
            }
        }

        this.currentGrille = (this.currentGrille + 1) % 2;
        this.saveGrille = (this.saveGrille + 1) % 2;

        this.updateGUI();
    }


    @Override
    public void restart() {
        for (int i = 0 ; i < gridWidth ; i++) {
            for (int j = 0 ; j < gridHeight ; j++) {
                this.initValue(i, j);
            }
        }
        this.updateGUI();
    }

    /**
     * Tableau des 8 cellules les plus proches de la cellule i, j
     * @param i
     * @param j
     * @return
     */
    protected int[] getVoisins(int i, int j) {
        /**
         * jm1 == 'j moins 1' == j-1
         * jp1 == 'j plus 1' == j+1
         */
        int im1 = i - 1;
        int ip1 = i + 1;
        int jm1 = j - 1;
        int jp1 = j + 1;

        if (i == 0) {
            im1 = this.gridWidth - 1;
        } else if (i == this.gridWidth - 1) {
            ip1 = 0;
        }

        if (j == 0) {
            jm1 = this.gridHeight - 1;
        } else if (j == this.gridHeight - 1) {
            jp1 = 0;
        }

        int[] voisins = {
                this.getCurrIJ(im1, jm1),
                this.getCurrIJ(im1, j),
                this.getCurrIJ(im1, jp1),
                this.getCurrIJ(i, jm1),
                this.getCurrIJ(i, jp1),
                this.getCurrIJ(ip1, jm1),
                this.getCurrIJ(ip1, j),
                this.getCurrIJ(ip1, jp1)
        };

        return voisins;
    }

    /**
     * Évalue l'état futur de la cellule i, j en fonction de son état courant
     * @param i
     * @param j
     */
    protected abstract void evaluer(int i, int j);

    /**
     * Renvoie la couleur associée à colorNum
     * @param colorNum
     * @return Couleur afficher dans l'interface
     */
    protected Color getColor(int colorNum) {
        return this.colors[colorNum];
    }

    /**
     * Met à jour l'interface
     */
    private void updateGUI() {
        this.gui.reset();
        Color couleur;
        for (int i = 0 ; i < this.gridWidth ; i++) {
            for (int j = 0 ; j < this.gridHeight ; j++) {
                couleur = this.getColor(this.getCurrIJ(i, j));
                this.gui.addGraphicalElement(new Rectangle((i * this.cellSize) + this.gutter, (j * this.cellSize) +
                        this.gutter, couleur, couleur, this.cellSize, this.cellSize));
            }
        }
    }

    /**
     * Initilise le contenue de la cellule i, j
     * Est appelé seulement au début
     * @param i
     * @param j
     */
    protected void initValue(int i, int j) {
        this.setCurrIJ(i, j, (int) (Math.random() * this.nbEtat));
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0 ; i < this.gridWidth ; i++) {
            for (int j = 0 ; j < this.gridHeight ; j++) {
                str += this.getCurrIJ(i, j) + " ";
            }
            str += "\n";
        }

        return str;
    }

    /**
     * Retourne la valeur de la grille courante évaluée en i, j
     * @param i
     * @param j
     * @return
     */
    protected int getCurrIJ(int i, int j) {
        return this.grilles[this.currentGrille][i][j];
    }

    /**
     * Met à jour la valeur de la grille courante évaluée en i, j en fonction de val
     * @param i
     * @param j
     * @param val
     */
    protected void setCurrIJ(int i, int j, int val) {
        this.grilles[this.currentGrille][i][j] = val;
    }

    /**
     * Met à jour la valeur de la grille future évaluée en i, j en fonction de val
     * @param i
     * @param j
     * @param val
     */
    protected void setNewIJ(int i, int j, int val) {
        this.grilles[this.saveGrille][i][j] = val;
    }
}
