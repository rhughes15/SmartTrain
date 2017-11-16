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
    if(rightComponent.getClass().toString().contains("Station")) this.track = leftComponent;
    else this.track = rightComponent;
  }


  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    System.out.println(message);
    if(sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
      path.add(this);
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
    if(!sending && stationName.equalsIgnoreCase(message.substring(1)))
    {
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
  public synchronized Component getTrack() { return track; }
}
