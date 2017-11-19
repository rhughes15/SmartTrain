import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to represent a segment of track in our
// system. Tracks are the most basic component in our system.
// Tracks can have a Train on them, but they just pass messages
// along when they get them.
//***********************************

public class Track extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int length = Reference.length;
  private int y = Reference.y;
  private String message;
  private ArrayList<Component> path;
  private boolean sending;

  public Track(int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    locked = false;
  }

  public void setRightComponent(Component component)
  {
    this.rightComponent = component;
  }

  //**********************************
  // This is the method that is responsible for
  // displaying each component. It takes in a graphics context
  // and returns nothing.
  //***********************************
  @Override
  public void display(GraphicsContext gc)
  {
    if (train != null)
    {
      train.display(gc);

    }
    guiX = length * this.getTrackX() + 55;
    guiY = y * this.getTrackY() + 200;
    gc.strokeLine(guiX, guiY, length + length * this.getTrackX() + 44, guiY);
  }


  //**********************************
  // This is the method for transferring the pathfinding
  // messages. This acts like a setter for message, path,
  // and sending. Additionally, it calls notify on the
  // runnable component.
  //***********************************
  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    this.message = message;
    this.path = new ArrayList<>(path);
    this.sending = sending;
    this.notify();
  }
  //**********************************
  // This is the method that is run when notify is called
  // on a runnable component. This is where most of the message
  // passing logic exists. It takes in a String, ArrayList, and
  // boolean and returns nothing.
  //***********************************
  @Override
  public void messageAccepted(String message, ArrayList<Component> path, boolean sending)
  {
    if (message.substring(0, 1).compareTo(message.substring(1)) > 0)
    {
      if (sending) path.add(this);
      leftComponent.acceptMessage(message, path, sending);
    }
    else
    {
      if (sending) path.add(this);
      rightComponent.acceptMessage(message, path, sending);
    }
  }

  @Override
  public void run()
  {
    while (true)
    {
      synchronized (this)
      {
        try
        {
          this.wait();
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        }

      }
      messageAccepted(message, path, sending);
    }
  }
}