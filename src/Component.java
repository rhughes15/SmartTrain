import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;

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

  //**********************************
  // This is the main method for transporting trains.
  // It takes in a train, an accumulator, and a boolean value
  // to tell whether or not the train should let the signals
  // know they should turn off.
  //***********************************
  public void acceptTrain(Train train, int acc, boolean reversed)
  {
    ArrayList<Component> path = train.getPath();
    train.setCurrentComponent(this);
    if(acc == path.size())
    {
      if(this instanceof  Track)
      {
        setTrain(null);
        train.setCurrentComponent(null);
        return;
      }
      reversed= true;
      train.setFlipped(!train.isFlipped());
      acc=0;
      Collections.reverse(path);
      train.setPath(path);
    }
    if(reversed && this instanceof Signal)
    {
      Signal s = (Signal)this;
      s.setGreen(false);
    }
    Component component = train.getPath().get(acc);

    try
    {
      Thread.sleep(100);
    } catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    component.acceptTrain(train, acc + 1,reversed);
  }

  protected Train train;

  //**********************************
  // This is the method for transferring the pathfinding
  // messages. This acts like a setter for message, path,
  // and sending. Additionally, it calls notify on the
  // runnable component.
  //***********************************
  abstract void acceptMessage(String message, ArrayList<Component> path, boolean sending);



  //**********************************
  // This is the method that is run when notify is called
  // on a runnable component. This is where most of the message
  // passing logic exists. It takes in a String, ArrayList, and
  // boolean and returns nothing.
  //***********************************
  abstract void messageAccepted(String message, ArrayList<Component> path, boolean sending);

  //**********************************
  // This is the method that is responsible for
  // displaying each component. It takes in a graphics context
  // and returns nothing.
  //***********************************
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
