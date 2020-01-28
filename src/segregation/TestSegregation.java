package segregation;

import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestSegregation {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.PINK);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);

        SegregationSimulator simulator = new SegregationSimulator(gui, colors, 6, 50);
        gui.setSimulable(simulator);
    }
}
