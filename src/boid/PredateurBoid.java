package boid;

import java.awt.*;
import java.util.ArrayList;

public class PredateurBoid extends AbstractBoid {
    private float hungry;

    public PredateurBoid(float x, float y) {
        super(x, y, Color.RED);
        this.force = 1f;
        this.hungry = 5f;
    }

    @Override
    public Vecteur cohesion(ArrayList<AbstractBoid> voisins) {
        if (voisins.size() > 0) {
            Vecteur sum = new Vecteur();
            for (AbstractBoid b: voisins) {
                if (b.isPredateur())
                    sum.add(Vecteur.invert(b.getLocation()));
                else if (isFood())
                    sum.add(Vecteur.mult(b.getLocation(), this.hungry));
                else
                    sum.add(b.getLocation());
            }

            sum.div(voisins.size());
            /** On utilise la fonction seek()
             *  la cible qu'on cherche est la location moyenne des voisins
             */
            return seek(sum);
        } else {
            return new Vecteur(0,0);
        }
    }

    @Override
    public boolean isPredateur() {
        return true;
    }
}
