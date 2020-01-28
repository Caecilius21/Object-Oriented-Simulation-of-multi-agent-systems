package factorised.simulator;

import factorised.agent.Agent;
import factorised.agent.ConwayCell;
import factorised.agent.ImmigrationCell;
import factorised.agent.SegregationCell;
import gui.GUISimulator;

import java.awt.*;
import java.util.LinkedList;

public class SegregationCellularSimulator extends CellularSimulator {
    private LinkedList<SegregationCell> maisonsVides;

    public SegregationCellularSimulator(GUISimulator gui, int width, int height, Color... couleurs) {
        super(gui, CellularSimulator.SEGREGATION, width, height, couleurs);
    }

    @Override
    public void initAgents() {
        this.maisonsVides = new LinkedList<>();

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                int etat = (int) (Math.random() * (this.nbEtats + 1));
                SegregationCell cell = new SegregationCell(i, j, etat);
                this.agents.add(cell);
                if (etat == this.nbEtats) {
                    this.maisonsVides.addFirst(cell);
                }

            }
        }
    }

    @Override
    public void next() {
        for(Agent agent: agents) {
           ((SegregationCell) agent).evaluer(this.getVoisins(agent), this.maisonsVides);
        }
        this.updateAgents();
        this.updateGUI();
    }
}
