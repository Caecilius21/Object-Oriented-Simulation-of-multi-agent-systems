package factorised.simulator;

import factorised.agent.Agent;
import gui.GUISimulator;
import gui.Simulable;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstractMultiAgent implements Simulable {
    protected ArrayList<Agent> agents;
    protected GUISimulator gui;
    protected int nbAgents;

    public AbstractMultiAgent(GUISimulator gui, int nbAgents) {
        this.gui = gui;
        this.nbAgents = nbAgents;
        this.agents = new ArrayList<>(this.nbAgents);
        this.initAgents();
    }

    public abstract void initAgents();

    @Override
    public void next() {
        for(Agent agent: agents) {
            agent.evaluer(this.getVoisins(agent));
        }
        this.updateAgents();
        this.updateGUI();
    }

    @Override
    public void restart() {
        this.agents.clear();
        this.initAgents();
        this.updateGUI();
    }

    protected void updateAgents() {
        for(Agent agent: agents) {
            agent.update();
        }
    }

    protected void updateGUI() {
        this.gui.reset();

        for(Agent agent: agents) {
            gui.addGraphicalElement(agent.toGraphicalElement());
        }
    }

    /**
     * Tableau des 8 cellules les plus proches de la cellule i, j
     * @param
     * @param
     * @return
     */
    protected abstract ArrayList<Agent> getVoisins(Agent agent);

    protected abstract Agent getAgent(Object... params);
}
