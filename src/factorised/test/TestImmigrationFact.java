package factorised.test;

import factorised.agent.ImmigrationCell;
import factorised.simulator.CellularSimulator;
import gui.GUISimulator;

import java.awt.*;

public class TestImmigrationFact {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(700, 700, Color.WHITE);
        CellularSimulator simulator = new CellularSimulator(gui, CellularSimulator.IMMIGRATION,50, 50, Color.BLACK, Color.YELLOW, Color.RED, Color.GREEN);
        gui.setSimulable(simulator);
    }
}
