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
  }

  public void setLeftComponent(Component component)
  {
    leftComponent = component;
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(sending)
    {
      track.notify();
      track.acceptMessage(message, path, false);
    }
    else
    {
      if(train != null)
      {
        train.notify();
        train.travel(path);
      }
    } // LEFT OFF HERE: implementing message passing
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
}
