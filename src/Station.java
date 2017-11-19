import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to represent a Station in our system.
// The Station has a name and a track which are used to identify it
// and connect it to the system. It currently prints the message
// and path it receives with a few labels for testing. It can have a
// train, in that case when it displays itself, it also displays the train
//***********************************

public class Station extends Component
{
  private Component track, leftComponent;
  private String stationName;
  private ArrayList<Component> path;
  private String message;
  private boolean sending;

  public Station(int trackX, int trackY, String stationName)
  {
    this.stationName = stationName;
    this.trackX = trackX;
    this.trackY = trackY;
    guiX = Reference.length * trackX + 50;
    guiY = Reference.y * trackY + 200 - Reference.length;
  }
  //**********************************
  // This is the method that is responsible for
  // displaying each component. It takes in a graphics context
  // and returns nothing.
  //***********************************
  @Override
  public void display(GraphicsContext gc)
  {
    if (train != null) train.display(gc);
    gc.setFill(Reference.COLORS[stationName.charAt(0) % Reference.COLORS.length]);
    gc.fillRect(Reference.length * trackX + 50, Reference.y * trackY + 200 - Reference.length, Reference.length, Reference.length);
  }

  public void setLeftComponent(Component component)
  {
    leftComponent = component;
  }

  public void setRightComponent(Component track)
  {
    rightComponent = track;
    if (rightComponent != null && track instanceof Track) this.track = rightComponent;
    else this.track = leftComponent;
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
    if (sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      path.add(this);
      track.acceptMessage(message.substring(1) + message.substring(0, 1), path, false);

    }
    else if (!sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      setTrain(new Train(this));
      if(this.stationName.charAt(0)>'S') train.setFlipped(true);
      train.getPath().get(0).acceptTrain(train, 0, false);

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
        } catch (Exception InterruptedException)
        {
        }
      }

      messageAccepted(message, path, sending);
    }

  }
  public ArrayList<Component> getPath()
  {
    return path;
  }
  public Component getTrack()
  {
    return track;
  }
  public synchronized String getStationName()
  {
    return stationName;
  }
}
