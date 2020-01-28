package immigration;

import gui.GUISimulator;

import java.awt.*;

public class TestImmigation {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        ImmigrationSimulator simulator = new ImmigrationSimulator(gui, 5, 20);
        gui.setSimulable(simulator);
    }
}
