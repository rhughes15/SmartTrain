import javafx.scene.canvas.GraphicsContext;


import java.util.ArrayList;

public class Station extends Component
{
  private Component track, leftComponent;
  private String stationName;
  private Train train;
  private int guiX, guiY;

  public Station(int trackX, int trackY, String stationName)
  {
    this.stationName = stationName;
    this.trackX = trackX;
    this.trackY = trackY;
  }
  public void display(GraphicsContext gc)
  {
    guiX = Reference.length*trackX + 50;
    guiY = Reference.y*trackY+200 - Reference.length;
    gc.setFill(Reference.colors[stationName.charAt(0)% Reference.colors.length]);
    gc.fillRect(guiX, guiY, Reference.length ,Reference.length);

  }

  public void setLeftComponent(Component component)
  {
    leftComponent = component;
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if((rightComponent.getClass().toString()).contains("station")) track = leftComponent;
    else track = rightComponent;

    if(sending)
    {
      track.notify();
      track.acceptMessage(message.substring(1) + message.substring(0, 1), path, false);
    }
    else
    {
      if(train != null)
      {
        train.notify();
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
}
