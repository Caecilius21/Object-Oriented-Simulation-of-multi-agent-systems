package poo.balls;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {
    Balls balls;
    GUISimulator gui;

    BallsSimulator(GUISimulator gui) {
        super();
        this.gui = gui;
        this.balls = new Balls(12);

    }

    @Override
    public void next() {
        this.balls.translate((int)((Math.random()-0.5) * 20),(int)((Math.random()-0.5) * 10));
        this.updateBalls();
        System.out.println(this.balls);
    }

    @Override
    public void restart() {
        this.balls.reInit();
        this.updateBalls();
        System.out.println(this.balls);
    }

    public void updateBalls() {
        gui.reset();
        for (Point point: this.balls.getCoords()){
            gui.addGraphicalElement(new Oval((int) point.getX(), (int) point.getY(), Color.CYAN, Color.cyan, 50));
        }
    }

    public Balls getBalls() {
        return balls;
    }
}
