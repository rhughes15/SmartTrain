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
  private Train train;
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


  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    this.message = message;
    this.path = new ArrayList<>(path);
    this.sending = sending;
    this.notify();
  }
  public synchronized void messageAccepted(String message, ArrayList<Component> path, boolean sending)
  {

    if (sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      path.add(this);
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for (Component c : path)
      {
        if (c instanceof Track) System.out.print("T ");
        else if (c instanceof Signal) System.out.print("Si ");
        else if (c instanceof Switch) System.out.print("Sw ");
        else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
      }
      System.out.println(message.substring(1) + message.substring(0, 1));
      track.acceptMessage(message.substring(1) + message.substring(0, 1), path, false);

    } else if (sending && path != null)
    {
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for (Component c : path)
      {
        if (c instanceof Track) System.out.print("T ");
        else if (c instanceof Signal) System.out.print("Si ");
        else if (c instanceof Switch) System.out.print("Sw ");
        else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
      }
    }
    if (!sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for (Component c : path)
      {
        if (c instanceof Track) System.out.print("T ");
        else if (c instanceof Signal) System.out.print("Si ");
        else if (c instanceof Switch) System.out.print("Sw ");
        else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
      }


      if (train != null)
      {
        synchronized (train)
        {
          train.notify();
        }
        train.travel(path);
      }
    }
  }
  @Override
  public void run()
  {
    synchronized (this)
    {
      while (true)
      {
        try
        {
          this.wait();
        } catch (Exception InterruptedException)
        {
        }
        messageAccepted(message,path,sending);
      }
    }
  }

  public Component getTrack()
  {
    return track;
  }

  public void setTrain(Train train)
  {
    this.train = train;
  }

  public synchronized String getStationName()
  {
    return stationName;
  }
}
