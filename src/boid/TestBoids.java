package boid;

import event.EventManager;
import boid.*;
import gui.GUISimulator;
import java.awt.*;

public class TestBoids {
  public static void main(String[] args) {
    GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
    AbstractBoidsEvent simulator = new AbstractBoidsEvent(0, gui, 2, 50, true);
    // AbstractBoidsEvent simulator2 = new AbstractBoidsEvent(1, gui, 3, 50, false);

    EventManager manager = new EventManager();
    manager.addEvent(simulator);
    // manager.addEvent(simulator2);

    gui.setSimulable(manager);
  }
}
