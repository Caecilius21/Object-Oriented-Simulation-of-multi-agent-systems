package factorised.test;

import factorised.agent.ConwayCell;
import factorised.simulator.CellularSimulator;
import factorised.simulator.ConwayOld;
import gui.GUISimulator;

import java.awt.*;

public class TestConwayFact {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
        CellularSimulator simulator = new CellularSimulator(gui, CellularSimulator.CONWAY, 50, 50, Color.WHITE, Color.BLACK);
        gui.setSimulable(simulator);
    }
}
