package boid;

import java.awt.*;
import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;
import java.lang.Math;
import java.util.ArrayList;


public class BoidsSimulator implements Simulable {
    private ArrayList<AbstractBoid> boids = new ArrayList<>();
    private int nbBoids;
    private GUISimulator gui;
    private boolean PREDATEUR_MODE;

    public BoidsSimulator(GUISimulator gui, int nbBoids, boolean predateurMode) {
        super();
        this.gui = gui;
        this.nbBoids = nbBoids;
        this.PREDATEUR_MODE = predateurMode;
        initBoids();
    }


   /**
    * Remplir le arrayList boids par des boids de coordonnées (location) aléatoires
    */
    public void initBoids() {
        AbstractBoid boid;
        for (int i = 0; i < this.nbBoids; i++) {
            float x = (int) (Math.random()*this.gui.getPanelWidth());
            float y = (int) (Math.random()*this.gui.getPanelHeight());
            if (this.PREDATEUR_MODE) {
                if (Math.random() > 0.99) {
                    boid = new PredateurBoid(x, y);
                } else {
                    boid = new FoodBoid(x, y);
                }
            } else {
                boid = new ClassicBoid(x, y);
            }
            this.boids.add(boid);
        }
    }

    public void reInit(ArrayList<AbstractBoid> boids) {
        for (AbstractBoid boid: this.boids) {
          boid.reInitBoid();
        }
    }

    @Override
    public void next() {
        ArrayList<AbstractBoid> voisins;
        for (AbstractBoid boid: this.boids) {
            //voisins = this.getVoisins(boid);
            boid.flock(boids);
            //voisins.clear();
        }

        this.updateBoids();
    }

    private ArrayList<AbstractBoid> getVoisins(AbstractBoid boid) {
        ArrayList<AbstractBoid> res = new ArrayList<>();
        for (AbstractBoid other : this.boids) {
            float distance = Vecteur.dist(boid.getLocation(), other.getLocation());
            if (distance > 0 && distance < other.champDeVision) {
                res.add(other);
            }
        }

        res.remove(boid);
        return res;
    }

    @Override
    public void restart() {
        reInit(this.boids);
        this.updateBoids();
    }

    public void updateBoids() {
      gui.reset();
      for (AbstractBoid boid: this.boids) {
          boid.update();
          Color couleur = boid.getCouleur();
          gui.addGraphicalElement(new Oval((int) boid.getLocation().getX(), (int) boid.getLocation().getY(), couleur,
                  couleur, 5));
      }
    }
}
