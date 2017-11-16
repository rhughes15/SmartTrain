import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This abstract class is extended by all the components that
// make up our system of Tracks, Signals, Swtiches, and Stations.
// It holds some of the basic shared functionality of all of
// the components and provides an outline for the generic component's
// functionality. They have a trackX and trackY to describe their
// relative position in our system and a guiX and guiY to describe
// their relative postition on the canvas.
//***********************************

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
