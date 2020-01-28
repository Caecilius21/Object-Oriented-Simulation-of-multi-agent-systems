package poo.balls;

import java.awt.Point;
import java.util.ArrayList;

public class Balls {
    private ArrayList<Point> coords = new ArrayList<Point>();
    private ArrayList<Point> _coords = new ArrayList<Point>();
    private int nbBall;

    Balls(int nbBall) {
        this.nbBall = nbBall;
        for (int i = 0; i < this.nbBall; i++) {
            this.coords.add(new Point((int)(Math.random()*500),(int)(Math.random()*500)));
            // On créer une copie explicite du point
            this._coords.add(new Point(this.coords.get(i)));
        }
    }

    /**
     * Translate toutes les balles
     */
    public void translate(int dx, int dy) {
        for (Point point: this.coords) {
            point.translate(dx, dy);
        }
    }

    /**
     * Remet toutes les balles à leurs positions initiales
     */
    public void reInit() {
        for (int i = 0; i < this.nbBall; i++) {
            this.coords.get(i).setLocation(this._coords.get(i));
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (Point point: this.coords) {
            str += point.getX() + ", " + point.getY() + "\n";
        }
        return str;
    }

    public ArrayList<Point> getCoords() {
        return coords;
    }

    public int getNbBall() {
        return nbBall;
    }
}
