package event;

public abstract class Event {
  private long date;
  private static EventManager manager;

  /**
   * Constructeur de la classe
   * @return
   * @param date
   */
  public Event(long date) {
    this.date = date;
  }

  /**
   * Setter de date
   * @param date
   */
  public void setDate(long date) {
    this.date = date;
  }

  /**
   * Setter de date
   * @param date
   */
  public long getDate() {
    return this.date;
  }

  /**
   * Setter de manager
   * @param date
   */
  public static void setManager(EventManager newmanager) {
    manager = newmanager;
  }

  /**
   * Getter de manager
   * @return date
   */
  public static EventManager getManager() {
    return manager;
  }

  /**
   * Exécuter une action
   */
  public abstract void execute();

  public abstract void reset();

}
