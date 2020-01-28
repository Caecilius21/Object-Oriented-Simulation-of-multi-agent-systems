package conway;

import gui.GUISimulator;
import java.awt.Color;

public class TestConway {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        ConwaySimulator simulator = new ConwaySimulator(gui, 20);
        gui.setSimulable(simulator);
    }
}
