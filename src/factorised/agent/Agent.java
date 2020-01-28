package factorised.agent;

import gui.GraphicalElement;

import java.util.ArrayList;

public interface Agent {
    void update();

    GraphicalElement toGraphicalElement();

    void evaluer(ArrayList<Agent> voisins);
}
