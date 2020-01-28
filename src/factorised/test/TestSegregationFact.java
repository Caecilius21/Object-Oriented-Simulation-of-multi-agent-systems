package factorised.test;

import factorised.simulator.SegregationCellularSimulator;
import factorised.simulator.SegregationOld;
import gui.GUISimulator;

import java.awt.*;

public class TestSegregationFact {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(700, 700, Color.WHITE);
        SegregationOld simulator = new SegregationOld(3, gui, Color.GREEN, Color.RED,
                Color.YELLOW, Color.CYAN, Color.BLUE, Color.PINK, Color.MAGENTA);
        gui.setSimulable(simulator);
    }
}
