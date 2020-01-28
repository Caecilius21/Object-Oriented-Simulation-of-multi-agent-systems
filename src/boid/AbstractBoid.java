// Fiat Lux
package boid;

import java.util.ArrayList;
import java.awt.*;
import gui.*;

public abstract class AbstractBoid {
  protected Vecteur locationInit;
  protected Vecteur location;
  protected Vecteur velocity;
  protected Vecteur acceleration;
  protected float maxspeed;
  protected float maxforce;
  protected Triangle oval;
  protected float force;
  protected Color couleur;
  protected int champDeVision;
  public static GUISimulator gui;


  public AbstractBoid(float x, float y, Color couleur) {
    this.acceleration = new Vecteur((float)( Math.random() - 0.5), (float) (Math.random() - 0.5));
    this.velocity = new Vecteur((float)( Math.random() - 0.5), (float) (Math.random() - 0.5));
    this.location = new Vecteur(x,y);
    this.locationInit = this.location.get();
    this.maxspeed = 5f;
    this.maxforce = 0.6f;
    this.couleur = couleur;
    this.oval = new Triangle((int) x, (int) y, velocity, this.couleur, 5);
    this.champDeVision = 50;
    this.draw();
 }

  public Vecteur getLocation() {
    return location;
  }

    /**
     * modifie la localisation
     * @param x
     * @param y
     */
  public void setLocation(float x, float y) {
    this.location.set(x, y);
    this.oval.translate((int )x - this.oval.getX(), (int) y - this.oval.getY());
  }

  public Vecteur getVelocity() {
    return this.velocity;
  }

  /**
   * Dessine un boid
   */
  public void draw() {
    gui.addGraphicalElement(this.oval);
  }

  /**
   * Remet le boid à sa position initiale
   */
  public void reInitBoid() {
    this.location.set(this.locationInit);
    this.oval.translate((int) this.location.getX() - this.oval.getX(), (int) this.location.getY() - this.oval.getY());
  }

  /*
   * Mise-à-jour des attributs du boid
   */
  public void update() {
    this.velocity.add(this.acceleration);
    this.velocity.limit(this.maxspeed);
    this.location.add(this.velocity);
    this.acceleration.mult(0);
    this.oval.translate((int) this.location.getX() - this.oval.getX(), (int) this.location.getY() - this.oval.getY());
  }

  public Color getCouleur() {
      return couleur;
  }

  public boolean isFood() {
      return false;
  }

  public boolean isClassic() {
      return false;
  }

  public boolean isPredateur() {
      return false;
  }

  /**
   * Appliquer une force sur l'acceleration de notre boids
   * Thus, les forces s'accumule à chaque fois qu'on en ajoute une.
   /* @param force à appliquer sur le boid
   */
  public void applyForce(Vecteur force) {
    this.acceleration.add(force);
  }

  /**
   * Fonction qui reçoit une cible de vecteur et calcule une force de pilotage vers cette cible
   * @param target le point cible
   * @return un vecteur de force
   */
  public Vecteur seek(Vecteur target) {
    // desired velocity is our target destination minus our current location
    Vecteur desired = Vecteur.sub(target, this.location);
    //we scale according to maximum speed
    desired.normalize();
    desired.mult(this.maxspeed);
    // The steering force would henceforth be our desired velocity minus our current velocity
    Vecteur steer = Vecteur.sub(desired, this.velocity);
    steer.limit(this.maxforce);
    // Appliquer la force à l'accélération de l'objet
    return steer;
  }

  // /**
  //  * Éviter les collisions avec ses voisins
  //  * @return un vecteur de force séparante
  //  */
  // public Vecteur separate(ArrayList<AbstractBoid> boids) {
  //   float desiredseparation = 100;
  //
  //   // sum = vecteur de la direction dans laquelle on veut aller à vitesse max
  //   Vecteur sum = new Vecteur();
  //   // Combien de boids sont proches
  //   int count = 0;
  //   float mag;
  //   Vecteur steer = new Vecteur(0,0);
  //
  //   for (AbstractBoid other : boids) {
  //     float d = Vecteur.dist(this.location, other.location);
  //     if ((d > 0) && (d < desiredseparation)) {
  //       // Too close? point away
  //       Vecteur diff = Vecteur.sub(this.location, other.location);
  //       // vecteur unité
  //       diff.normalize();
  //       // le vecteur est proche: on l'ajoute dans sum
  //       sum.add(diff);
  //       count++;
  //     }
  //   }
  //   if (count > 0) {
  //     // s'il y a au moins un vecteur, on divise le vecteur sum par le scalaire count
  //     sum.div(count);
  //     mag = sum.mag();
  //     // mettre la vitesse max comme étant la magnitude du vecteur sum
  //     sum.normalize();
  //     sum.mult(maxspeed);
  //     // la formule pour calculer la vitesse désirée
  //     steer = Vecteur.sub(sum, this.velocity);
  //     steer.limit(maxforce);
  //     // appliquer la force sur l'accélération de l'agent
  //   }
  //   return steer;
  // }
  //
  // /**
  //  * Se diriger dans la même direction que ses voisins
  //  * @return vecteur de force
  //  */
  // public Vecteur align(ArrayList<AbstractBoid> boids) {
  //   float neighbordist = 50;
  //   Vecteur sum = new Vecteur(0,0);
  //   int count = 0;
  //   for (AbstractBoid other : boids) {
  //     float d = Vecteur.dist(this.location, other.location);
  //     if ((d > 0) && (d < neighbordist)) {
  //       sum.add(other.velocity);
  //       // Pour calculer la moyenne après, we keep track of combien de boids sans dedans notre voisinage
  //       count++;
  //     }
  //   }
  //   if (count > 0) {
  //     sum.div(count);
  //     // We desire to go in that direction at maximum speed.
  //     sum.normalize();
  //     sum.mult(maxspeed);
  //     Vecteur steer = Vecteur.sub(sum,velocity);
  //     steer.limit(maxforce);
  //     return steer;
  //     // Si on trouve pas de boids proches, la force de steering (directrice) est nulle
  //   } else {
  //     return new Vecteur(0,0);
  //   }
  // }
  //
  // /**
  //  * Se diriger vers le centre de ses voisins (rester avec le groupe)
  //  * @return vecteur de force
  //  */
  // public Vecteur cohesion(ArrayList<AbstractBoid> boids) {
  //   float neighbordist = 80;
  //   Vecteur sum = new Vecteur(0,0);
  //   int count = 0;
  //   for (AbstractBoid other : boids) {
  //     float d = Vecteur.dist(this.location, other.location);
  //     if ((d > 0) && (d < neighbordist)) {
  //       sum.add(other.location);
  //       count++;
  //     }
  //   }
  //   if (count > 0) {
  //     sum.div(count);
  //     // On utilise la fonction seek()
  //     // la cible qu'on cherche est la location moyenne des voisins.
  //     return seek(sum);
  //   } else {
  //     return new Vecteur(0,0);
  //   }
  // }

  /**
     * Éviter les collisions avec ses voisins
     * @return un vecteur de force séparante
     */
    public Vecteur separate(ArrayList<AbstractBoid> boids) {
      float desiredseparation = 80;
      /* sum = vecteur de la direction dans laquelle on veut aller à vitesse max */
      Vecteur sum = new Vecteur();
      /* Combien de boids sont proches */
      int count = 0;
      float mag;
      Vecteur steer = new Vecteur(0,0);

      for (AbstractBoid other : boids) {
        float d = Vecteur.dist(this.location, other.location);
        if ((d > 0) && (d < desiredseparation)) {
          float angleBetween;
          angleBetween = this.velocity.angleBetween(this.velocity, new Vecteur(other.location.getX() - this.location.getX(),
                  other.location.getY() - this.location.getY()));
          if( (angleBetween < (3*Math.PI/4) && angleBetween > (-3*Math.PI/4)) || (Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {
            // Too close? point away
            Vecteur diff = Vecteur.sub(this.location, other.location);
            // vecteur unité
            diff.normalize();
            // le vecteur est proche: on l'ajoute dans sum
            sum.add(diff);
            count++;
          }
        }
      }
      if (count > 0) {
        // s'il y a au moins un vecteur, on divise le vecteur sum par le scalaire count
        sum.div(count);
        mag = sum.mag();
        // mettre la vitesse max comme étant la magnitude du vecteur sum
        sum.normalize();
        sum.mult(maxspeed);
        // la formule pour calculer la vitesse désirée
        steer = Vecteur.sub(sum, this.velocity);
        steer.limit(maxforce);
        // appliquer la force sur l'accélération de l'agent
      }
      return steer;
    }

    /**
     * Se diriger dans la même direction que ses voisins
     * @return vecteur de force
     */
    public Vecteur align(ArrayList<AbstractBoid> boids) {
      float neighbordist = 80;
      Vecteur sum = new Vecteur(0,0);
      int count = 0;
      for (AbstractBoid other : boids) {
        float d = Vecteur.dist(this.location, other.location);
        if ((d > 0) && (d < neighbordist)) {
          float angleBetween;
          angleBetween = this.velocity.angleBetween(this.velocity, new Vecteur(other.location.getX() - this.location.getX(),
                  other.location.getY() - this.location.getY()));
          if( (angleBetween < (3*Math.PI/4) && angleBetween > (-3*Math.PI/4)) || (Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {
            sum.add(other.velocity);
            // Pour calculer la moyenne après, we keep track of combien de boids sans dedans notre voisinage
            count++;
          }
        }
      }
      if (count > 0) {
        sum.div(count);
        // We desire to go in that direction at maximum speed.
        sum.normalize();
        sum.mult(maxspeed);
        Vecteur steer = Vecteur.sub(sum,velocity);
        steer.limit(maxforce);
        return steer;
        /* Si on trouve pas de boids proches, la force de steering (directrice) est nulle */
      } else {
        return new Vecteur(0,0);
      }
    }

    /**
     * Se diriger vers le centre de ses voisins (rester avec le groupe)
     * @return vecteur de force
     */
    public Vecteur cohesion(ArrayList<AbstractBoid> boids) {
      float neighbordist = 80;
      Vecteur sum = new Vecteur(0,0);
      int count = 0;
      for (AbstractBoid other : boids) {
        float d = Vecteur.dist(this.location, other.location);
        if ((d > 0) && (d < neighbordist)) {
          float angleBetween;
          angleBetween = this.velocity.angleBetween(this.velocity, new Vecteur(other.location.getX() - this.location.getX(),
                                                                               other.location.getY() - this.location.getY()));
          if( (angleBetween < (3*Math.PI/4) && angleBetween > (-3*Math.PI/4)) || (Math.round(this.velocity.getX()) == 0 && Math.round(this.velocity.getY()) == 0) ) {
            sum.add(other.location);
            count++;
          }
        }
      }
      if (count > 0) {
        sum.div(count);
      /* On utilise la fonction seek().
       * la cible qu'on cherche est la location moyenne des voisins.
       */
        return seek(sum);
      } else {
        return new Vecteur(0,0);
      }
    }


  /**
   * Rebondir sur les murs
   * @return vecteur de force
   */
  public Vecteur bounceWall() {
    Vecteur bounce = new Vecteur();

    int width = this.gui.getPanelWidth();
    int height = this.gui.getPanelHeight();

    if ((this.location.getX() < 1) || (this.location.getX() < width) || (this.location.getY() < 1) || (this.location.getY() < height)) {
      bounce.set(1, 1);
    }
    return bounce;
  }

  public void crossWall() {
    int width = this.gui.getPanelWidth();
    int height = this.gui.getPanelHeight();

    if (this.location.getX() < 0) {
        this.location.setX(width);
    } else if (this.location.getX() > width) {
        this.location.setX(0);
    }

    if (this.location.getY() < 0) {
        this.location.setY(height);
    } else if (this.location.getY() > height) {
        this.location.setY(0);
    }
  }

  /**
   * Gère tous les comportements du groupe des boids
   */
  public void flock(ArrayList<AbstractBoid> boids) {
    // Les trois règles de floaking
    Vecteur sep = separate(boids);
    Vecteur ali = align(boids);
    Vecteur coh = cohesion(boids);
    Vecteur bce = bounceWall();


    // Poids arbitraires pour ces forces
    sep.mult(1.0f);
    ali.mult(0.7f);
    coh.mult(1.0f);

    // Rebondir ou Traverset le mur?
    crossWall();
    // applyForce(bce);

    // Appliquer toutes les forces
    applyForce(sep);
    applyForce(ali);
    applyForce(coh);
  }
}
