/**
 * Gestionnaire à événements discrets
 * Ce gestionnaire possède une séquence ordonnée d’événements datés (par un entier par exemple, ou une classe Date)
 * À chaque évènement est associée une action à réaliser
 * Les évènements sont ajoutés dans un ordre quelconque, mais bien traités à la date adéquate
 * Le gestionnaire maintient une « date courante » de simulation
 */

package event;
import gui.Simulable;

import java.util.ArrayList;

/*
 * Gestionnaire à événements discrets.
 * À chaque évènement est associée une action à réaliser
 * Les évènements sont ajoutés dans un ordre quelconque, mais bien traités à la date adéquate
 */
public class EventManager implements Simulable {
  /* date courante de simulation */
  private long currentDate;
  private ArrayList<Event> calendar;


  /**
   * Constructeur de la classe
   */
  public EventManager() {
    this.currentDate = 0;
    this.calendar = new ArrayList<Event>();
    Event.setManager(this);
  }

  /**
   * Ajoute un event à notre calendrier
   */
  public void addEvent(Event e) {
    this.calendar.add(e);
  }

  /**
   * incrémenter la date courante puis
   * exécuter dans l’ordre tous les events non encore exécutés jusqu’à currentDate
   */
  public void next() {
    this.currentDate += 1;
    System.out.println("event manager date"  + this.currentDate);
    if (this.isFinished()) {
      return;
    }

    ArrayList<Event> toRemove = new ArrayList<Event>();
    for (Event e: this.calendar) {
      long date = e.getDate();
      if (date <= this.currentDate) {
        toRemove.add(e);
      }
    }
    /* Supprimer les events qu'on a exécuté */
    for (Event e: toRemove) {
      e.execute();
      calendar.remove(e);
    }
  }


  /**
   * @return true si plus aucun évènement n’est en attente d’exécution
   */
  public boolean isFinished() {
    return calendar.isEmpty();
  }

  public void restart() {
    this.currentDate = 0;
    for (Event e: this.calendar) {
      e.reset();
    }
  }
}
