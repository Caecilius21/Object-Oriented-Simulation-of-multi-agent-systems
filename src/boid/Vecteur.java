package boid;

public class Vecteur {
  private float x;
  private float y;

  /** Array so that this can be temporarily used in an array context */
  private float[] array;

  /**
   * Constructeur pour un vecteur vide: x et y sont mis à 0
   */
  public Vecteur() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Constructeur pour un vecteur 2D
   */
  public Vecteur(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Définir les coordonnées x et y
   *
   */
  public void set(float x, float y) {
    this.x = x;
    this.y = y;
  }


  /**
   * Définir les coordonnées x, y à partir d'un objet Vecteur
   *
   * @param v l'objet Vecteur à copier
   */
  public void set(Vecteur v) {
    this.x = v.x;
    this.y = v.y;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setY(float y) {
    this.y = y;
  }

  /**
   * Obtenir une copie de ce vecteur
   */
  public Vecteur get() {
    return new Vecteur(x, y);
  }

  /**
   * Calculer l'angle entre deux vecteurs, en utilisant le produit scalaire
   * @param v1 un vecteur
   * @param v2 un autre vecteur
   * @return l'angle entre les vecteurs
   */
  public float angleBetween(Vecteur v1, Vecteur v2) {
    double dot = v1.x * v2.x + v1.y * v2.y;
    double v1mag = Math.sqrt(v1.x * v1.x + v1.y * v1.y);
    double v2mag = Math.sqrt(v2.x * v2.x + v2.y * v2.y);
    return (float) Math.acos(dot / (v1mag * v2mag));
  }

  /**
   * Calculer la norme (longueur) du vecteur
   * @return la magnitude du vecteur
   */
  public float mag() {
    return (float) Math.sqrt(x*x + y*y);
  }

  /**
   * Ajouter un vecteur à ce vecteur
   * @param v le vecteur à ajouter
   */
  public void add(Vecteur v) {
    this.x += v.x;
    this.y += v.y;
  }

  /**
   * Soustraire un vecteur d'un autre
   * @param v1 un vecteur
   * @param v2 un autre vecteur
   * @return un nouveau vecteur qui est v1 - v2
   */
  static public Vecteur sub(Vecteur v1, Vecteur v2) {
    return sub(v1, v2, null);
  }


  static public Vecteur sub(Vecteur v1, Vecteur v2, Vecteur target) {
    if (target == null) {
      target = new Vecteur(v1.x - v2.x, v1.y - v2.y);
    } else {
      target.set(v1.x - v2.x, v1.y - v2.y);
    }
    return target;
  }

  static public Vecteur invert(Vecteur v) {
    return new Vecteur(-v.getX(), -v.getY());
  }


  /**
   * Multiplier ce vecteur par un scalaire
   * @param n la valeur à multiplier par
   */
  public void mult(float n) {
    this.x *= n;
    this.y *= n;
  }

  static public Vecteur mult(Vecteur v, float n) {
    return new Vecteur(v.getX() * n, v.getY() * n);
  }

  /**
   * Diviser ce vecteur par un scalaire
   * @param n la valeur à diviser par
   */
  public void div(float n) {
    this.x /= n;
    this.y /= n;
  }

  /**
   * Calculer la distance euclidienne entre deux points (en considérant un point comme un objet vectoriel)
   * @param v1 un vecteur
   * @param v2 un autre vecteur
   * @return la distance euclidienne entre v1 et v2
   */
  static public float dist(Vecteur v1, Vecteur v2) {
    float dx = v1.x - v2.x;
    float dy = v1.y - v2.y;
    return (float) Math.sqrt(dx*dx + dy*dy);
  }

  /**
   * Normaliser le vecteur à la longueur 1 (en faire un vecteur unitaire)
   */
  public void normalize() {
    float m = mag();
    if (m != 0 && m != 1) {
      div(m);
    }
  }


  public Vecteur normalize(Vecteur target) {
      if (target == null) {
        target = new Vecteur();
      }
      float m = mag();
      if (m > 0) {
        target.set(x/m, y/m);
      } else {
        target.set(x, y);
      }
      return target;
    }


  /**
   * Limiter la magnitude de ce vecteur
   * @param max la longueur maximale pour limiter ce vecteur
   */
  public void limit(float max) {
    if (mag() > max) {
      normalize();
      mult(max);
    }
  }

  @Override
  public String toString() {
    return "[ " + x + ", " + y + " ]";
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
}
