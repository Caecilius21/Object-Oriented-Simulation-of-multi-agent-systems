package factorised.agent;

import gui.GraphicalElement;
import gui.Rectangle;

import java.awt.*;
import java.util.ArrayList;

public abstract class Cell implements Agent {
    public static ArrayList<Color> couleurs;
    public static int size;
    public static int nbEtats;

    protected Point position;
    protected int currEtat;
    protected int nextEtat;

    public Cell(int i, int j, int currEtat) {
        this.position = new Point(i, j);
        this.currEtat = currEtat;
    }

    @Override
    public abstract void evaluer(ArrayList<Agent> voisins);

    @Override
    public void update() {
        currEtat = nextEtat;
    }

    @Override
    public GraphicalElement toGraphicalElement() {
        if (this.currEtat != Cell.nbEtats) {
            return new Rectangle(this.position.x * Cell.size, this.position.y * Cell.size,
                    Cell.couleurs.get(this.currEtat), Cell.couleurs.get(this.currEtat), Cell.size, Cell.size);
        } else {
            return new Rectangle(this.position.x * Cell.size, this.position.y * Cell.size,
                    Color.WHITE, Color.WHITE, Cell.size, Cell.size);
        }
    }


    public Point getPosition() {
        return position;
    }

    public int getEtat() {
        return currEtat;
    }

    public void setEtat(int etat) {
        this.currEtat = etat;
    }
}
