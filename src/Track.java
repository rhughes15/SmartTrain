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
    this.rightComponent= component;
  }

  @Override
  public void display(GraphicsContext gc)
  {
    if(train != null) train.display(gc);
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200, length +length*this.getTrackX()+44, y*this.getTrackY() + 200);
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {

    this.message = message;
    this.path = new ArrayList<>(path);
    this.sending = sending;
    this.notify();
  }
  private synchronized void messageAccepted(String message, ArrayList<Component> path, boolean sending)
  {
    if(message.equalsIgnoreCase("red") || message.equalsIgnoreCase("green"))
    {
      if(sending)
      {
        rightComponent.acceptMessage(message, path, sending);
      }
      else
      {
        leftComponent.acceptMessage(message, path, sending);
      }
    }
    else if(message.substring(0, 1).compareTo(message.substring(1)) > 0)
    {
      if(sending) path.add(this);
      else locked = true;
      leftComponent.acceptMessage(message, path, sending);
    }
    else
    {
      if(sending) path.add(this);
      else locked = true;
      rightComponent.acceptMessage(message, path, sending);
    }
  }
  @Override
  public void run()
  {
    synchronized (this)
    {

      while(locked == false)
      {
          try
          {
            this.wait();


          } catch (InterruptedException e)
          {
            e.printStackTrace();
          }
          messageAccepted(message, path, sending);
          message = "";


      }
    }
  }
}