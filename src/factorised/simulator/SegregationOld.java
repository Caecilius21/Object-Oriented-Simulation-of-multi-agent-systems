package factorised.simulator;

import gui.GUISimulator;

import java.awt.Point;
import java.awt.Color;
import java.util.LinkedList;

public class SegregationOld extends MultiAgentDiscretSimulator {
    private int seuil;
    private LinkedList<Point> maisonLibres;


    public SegregationOld(int seuil, GUISimulator gui, Color... colors) {
        super(gui, colors);
        this.seuil = seuil;
    }

    @Override
    protected void evaluer(int i, int j) {
        int countDiff = 0;

        for (int voisin: this.getVoisins(i, j)) {
            if (voisin != this.getCurrIJ(i, j) && this.getCurrIJ(i, j) != this.nbEtat) {
                countDiff++;
            }
        }

        if (countDiff > this.seuil) {
            demenage(i, j);
        } else {
            this.setNewIJ(i, j, this.getCurrIJ(i, j));
        }
    }

    @Override
    protected Color getColor(int colorNum) {
        if (colorNum != this.colors.length) {
            return this.colors[colorNum];
        } else {
            return this.gui.getBackground();
        }
    }

    @Override
    protected void initValue(int i, int j) {
        this.maisonLibres = new LinkedList<>();
        this.setCurrIJ(i, j, (int)(Math.random() * (this.colors.length + 1)));
        if (this.getCurrIJ(i, j) == this.colors.length) {
            maisonLibres.addFirst(new Point(i, j));
        }
    }

    /**
     * Déplace une maison dans une autre maison libre
     * La maison nouvellement libre rejoint la liste chainée des maisons libres
     * @param i
     * @param j
     */
    private void demenage(int i, int j) {
        // récupérer une maison libre de manière aléatoire.
        int iRandom = (int) (Math.random() * this.maisonLibres.size());
        if (iRandom != 0){
            Point coord_dest = this.maisonLibres.remove(iRandom);

            // On déplace la famille courante dans la maison en question
            this.setNewIJ((int) coord_dest.getX(), (int) coord_dest.getY(), this.getCurrIJ(i, j));

            // On vide la maison ancienement habitée
            this.setNewIJ(i, j, this.colors.length);
            this.maisonLibres.add(new Point(i, j));
        }
    }
}
