package factorised.simulator;

import factorised.agent.*;
import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CellularSimulator extends AbstractMultiAgent {
    public static final int CONWAY = 0;
    public static final int IMMIGRATION = 1;
    public static final int SEGREGATION = 2;

    protected int width;
    protected int height;
    protected int nbEtats;
    private int gameMode;

    public CellularSimulator(GUISimulator gui, int gameMode, int width, int height, Color... couleurs) {
        super(gui, width * height);
        this.height = height;
        this.width = width;
        this.nbEtats = couleurs.length;
        this.gameMode = gameMode;

        Cell.couleurs = new ArrayList<>(Arrays.asList(couleurs));
        Cell.size = 10;
        Cell.nbEtats = this.nbEtats;

        this.initAgents();
    }

    @Override
    public void initAgents() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.gameMode == CONWAY){
                    this.agents.add(new ConwayCell(i, j, (int) (Math.random() * this.nbEtats)));
                } else if (this.gameMode == IMMIGRATION) {
                    this.agents.add(new ImmigrationCell(i, j, (int) (Math.random() * this.nbEtats)));
                }
            }
        }
    }

    @Override
    protected ArrayList<Agent> getVoisins(Agent agent) {
        /**
         * jm1 == 'j moins 1' == j-1
         * jp1 == 'j plus 1' == j+1
         */
        ArrayList<Agent> voisins = new ArrayList<>();
        int i = ((Cell) agent).getPosition().x;
        int j = ((Cell) agent).getPosition().y;
        int im1 = i - 1;
        int ip1 = i + 1;;
        int jm1 = j - 1;;
        int jp1 = j + 1;;

        if (i == 0) {
            im1 = this.width - 1;
        } else if (i == this.width - 1) {
            ip1 = 0;
        }

        if (j == 0) {
            jm1 = this.height - 1;
        } else if (j == this.height - 1) {
            jp1 = 0;
        }

        voisins.add(this.getAgent(im1, jm1));
        voisins.add(this.getAgent(im1, j));
        voisins.add(this.getAgent(im1, jp1));
        voisins.add(this.getAgent(i, jm1));
        voisins.add(this.getAgent(i, jp1));
        voisins.add(this.getAgent(ip1, jm1));
        voisins.add(this.getAgent(ip1, j));
        voisins.add(this.getAgent(ip1, jp1));

        return voisins;
    }

    @Override
    protected Agent getAgent(Object... params) {
        return agents.get((int) params[0] * this.width + (int) params[1]);
    }
}
