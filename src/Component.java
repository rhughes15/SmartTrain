import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public abstract class Component implements Runnable
{
  protected Component rightComponent;
  protected int trackX, trackY, guiX, guiY;
  protected Train train;

  abstract void acceptMessage(String message, ArrayList<Component> path, boolean sending);
  abstract void display(GraphicsContext gc);

  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }
  public void setTrain(Train train) {this.train = train;}
  public int getTrackX()
  {
    return trackX;
  }
  public int getTrackY()
  {
    return trackY;
  }
  public int getGuiX() { return guiX; }
  public int getGuiY() { return guiY; }
}
