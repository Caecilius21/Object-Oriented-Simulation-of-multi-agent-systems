// Fiat Lux
package boid;
import java.awt.*;
import java.util.ArrayList;

public class ClassicBoid extends AbstractBoid {
  public ClassicBoid(float x, float y) {
    super(x, y, Color.WHITE);
    this.force = 1.5f;
  }

  @Override
  public boolean isClassic() {
    return true;
  }
}
