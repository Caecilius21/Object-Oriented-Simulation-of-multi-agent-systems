package poo.balls;
import gui.GUISimulator;

import java.awt.*;

public class TestBalls {

    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        BallsSimulator simulator = new BallsSimulator(gui);
        gui.setSimulable(simulator);
    }
}
