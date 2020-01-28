package conway;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import java.awt.Color;

public class ConwaySimulator implements Simulable {
    private GUISimulator gui;
    private int gridSize;
    private Grid grid;

    public ConwaySimulator(GUISimulator gui, int gridSize) {
        this.gui = gui;
        this.gridSize = gridSize;
        this.grid = new Grid(this.gridSize, new RulesConway());

    }

    @Override
    public void next() {
        this.grid.next();
        System.out.println(this.grid);
        this.updateGUI();
    }

    @Override
    public void restart() {

    }

    private void updateGUI() {
        this.gui.reset();
        for (int i = 0 ; i < this.gridSize ; i++) {
            for (int j = 0 ; j < this.gridSize ; j++) {
                if (this.grid.getCell(i, j) == EtatConway.VIVANT) {
                    this.gui.addGraphicalElement(new Rectangle(i * 10, j * 10, Color.WHITE, Color.WHITE, 10, 10));
                }
            }
        }
    }
}
