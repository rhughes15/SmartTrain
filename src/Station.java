import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;

public class Station extends Component
{
  private Component track, leftComponent;
  private String stationName;
  private Train train;

  public Station(int trackX, int trackY, String stationName)
  {
    this.stationName = stationName;
    this.trackX = trackX;
    this.trackY = trackY;
    guiX = Reference.length*trackX + 50;
    guiY = Reference.y*trackY+200 - Reference.length;
  }
  public void display(GraphicsContext gc)
  {
    if(train != null) train.display(gc);
    gc.setFill(Reference.COLORS[stationName.charAt(0)% Reference.COLORS.length]);
    gc.fillRect(Reference.length*trackX + 50, Reference.y*trackY+200 - Reference.length, Reference.length ,Reference.length);
  }

  public void setLeftComponent(Component component)
  {
    leftComponent = component;
  }
  public void setRightComponent(Component track)
  {
    rightComponent = track;
    if(track instanceof Track) this.track = rightComponent;
    else this.track = leftComponent;
  }


  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      path.add(this);
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for(Component c : path)
      {
        if (c instanceof Track) System.out.print("T ");
        else if (c instanceof Signal) System.out.print("Si ");
        else if (c instanceof Switch) System.out.print("Sw ");
        else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
      }
      synchronized (track)
      {
        track.notify();
      }
      track.acceptMessage(message.substring(1) + message.substring(0, 1), path, false);
    }
    else if (sending && path != null)
    {
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for(Component c : path)
      {
        if (c instanceof Track) System.out.print("T ");
        else if (c instanceof Signal) System.out.print("Si ");
        else if (c instanceof Switch) System.out.print("Sw ");
        else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
      }
    }
    if(!sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      System.out.println("Message Received at Station " + stationName + ": " + message);
      System.out.print("path: ");
      for(Component c : path)
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

    try {
      this.wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run()
  {
    synchronized (this)
    {
      try
      {
        this.wait();
      } catch (Exception InterruptedException) {}
    }
  }

  public void setTrain(Train train) {this.train = train;}
  public synchronized String getStationName() {return stationName;}
  public synchronized Component getTrack()
  {
    if(rightComponent == null) track = leftComponent;
    return track;
  }
}
