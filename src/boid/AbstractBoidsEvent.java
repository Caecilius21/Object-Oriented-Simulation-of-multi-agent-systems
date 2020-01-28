package boid;
// package event;

import event.Event;
import event.*;

import java.awt.*;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Oval;
import gui.Simulable;
import java.lang.Math;
import java.util.ArrayList;


public class AbstractBoidsEvent extends Event {
  private GUISimulator gui;
  private ArrayList<AbstractBoid> boids = new ArrayList<AbstractBoid>();
  private int nbBoids;
  private long period;
  private boolean PREDATEUR_MODE;

  public AbstractBoidsEvent(long date, GUISimulator gui, long period, int nbBoids, boolean predateurMode) {
    super(date);
    this.gui = gui;
    AbstractBoid.gui = gui;
    this.nbBoids = nbBoids;
    this.period = period;
    this.PREDATEUR_MODE = predateurMode;
    initBoids(this.boids);
  }

 /**
  * Remplir le arrayList boids par des boids de coordonnées (location) aléatoires
  * @param boids le array de boids à remplir
  */
  // public void initBoids(ArrayList<AbstractBoid> boids) {
  //   for (int i = 0; i < this.nbBoids; i++) {
  //     this.boids.add(new PredateurBoid((int) (Math.random()*500),(int) (Math.random()*500)));
  //     System.out.println("Sth");
  //   }
  // }
  public void initBoids(ArrayList<AbstractBoid> boids) {
    AbstractBoid boid;
    int width = this.gui.getPanelWidth();
    int height = this.gui.getPanelHeight();

    for (int i = 0; i < this.nbBoids; i++) {
      float x = (int) (Math.random()*width);
      float y = (int) (Math.random()*height);
      if (this.PREDATEUR_MODE) {
        if (Math.random() > 0.8) {
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

  /**
   * Réinitialise tout les boids dans leurs position initiale
   * @param boids une liste de boids
   */
  public void reInit(ArrayList<AbstractBoid> boids) {
    for (AbstractBoid boid: this.boids) {
      boid.reInitBoid();
    }
  }

  /**
   * Appel aux méthodes des systèmes d’agents
   */
  public void execute() {
    for (AbstractBoid boid: this.boids) {
      boid.flock(boids);
    }
    this.updateBoids();
    this.setDate(this.getDate()+this.period);
    System.out.println(" event date " + this.getDate());
    getManager().addEvent(this);
  }

  /**
   * Reset la simulation
   */
  public void reset() {
    reInit(this.boids);
    this.updateBoids();
    this.setDate(0);
  }


  /**
   * Mettre à jour des attributs de tous les boids
   */
  public void updateBoids() {
    // gui.reset();
    for (AbstractBoid boid: this.boids) {
      boid.update();
      // Color couleur = boid.getCouleur();
      // gui.addGraphicalElement(new Oval((int) boid.getLocation().getX(), (int) boid.getLocation().getY(), Color.CYAN, Color.cyan, 5));
      //gui.addGraphicalElement(new Triangle((int) boid.getLocation().getX(), (int) boid.getLocation().getY(), boid.getVelocity(), Color.blue, 5));
    }
  }
}
