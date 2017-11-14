import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Component implements Runnable
{
  abstract void acceptMessage(String message, ArrayList<Component> path, boolean sending);
  abstract void display(GraphicsContext gc);
  protected Component rightComponent;
  protected int trackX, trackY;

  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }
  public int getTrackX()
  {
    return trackX;
  }
  public int getTrackY()
  {
    return trackY;
  }


}
