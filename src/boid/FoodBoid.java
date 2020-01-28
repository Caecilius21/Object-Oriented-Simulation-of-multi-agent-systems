package boid;

import java.awt.*;
import java.util.ArrayList;

/**
 * Ces Boids sont sensés être de la nouriiture, les autres boids leur fonce dessus
 */
public class FoodBoid extends AbstractBoid{
    public FoodBoid(float x, float y) {
        super(x, y, Color.GREEN);
    }

    @Override
    public Vecteur separate(ArrayList<AbstractBoid> voisins) {
        /* sum = vecteur de la direction dans laquelle on veut aller à vitesse max */
        Vecteur sum = new Vecteur();
        Vecteur steer = new Vecteur();

        if (voisins.size() > 0) {
            for (AbstractBoid other : voisins) {
                if(other.isFood() || other.isClassic()){
                    Vecteur diff = Vecteur.sub(this.location, other.getLocation());
                    diff.normalize();
                    sum.add(diff);
                } else {
                    Vecteur diff = Vecteur.sub(this.location, other.getLocation());
                    diff.normalize();
                    diff.mult(3);
                    sum.add(diff);
                }
            }
            // s'il y a au moins un vecteur, on divise le vecteur sum par le scalaire count
            sum.div(voisins.size());
            sum.normalize();
            sum.mult(maxspeed);
            // la formule pour calculer la vitesse désirée
            steer = Vecteur.sub(sum, this.velocity);
            steer.limit(maxforce);
            // appliquer la force sur l'accélération de l'agent
        }
        return steer;
    }

    @Override
    public Vecteur cohesion(ArrayList<AbstractBoid> voisins) {
        if (voisins.size() > 0) {
            Vecteur sum = new Vecteur();
            for (AbstractBoid b: voisins) {
                if(b.isFood() || b.isClassic())
                    sum.add(b.getLocation());
               // else if (b.isPredateur())
                    // On repousse le boid dans la direction opposé du prédateur
                 //   Vecteur v = new Vecteur(b.getLocation().x)
                   // sum.add(Vecteur.mult(Vecteur.invert(b.getLocation()), 3f));
            }

            sum.div(voisins.size());
            /** On utilise la fonction seek().
             *  la cible qu'on cherche est la location moyenne des voisins.
             */
            return seek(sum);
        } else {
            return new Vecteur(0,0);
        }
    }

    @Override
    public boolean isFood() {
        return true;
    }
}
