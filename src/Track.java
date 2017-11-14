import java.util.ArrayList;

public class Track extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int guiX, guiY, id;

  public Track(int id, int length, int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.id = id;
    this.leftComponent = leftComponent;
    locked = false;
  }
  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(sending) path.add(this);
    if(message.substring(0, 1).compareTo(message.substring(1)) < 0)
    {
      leftComponent.notify();
      leftComponent.acceptMessage(message, path, sending);
    }
    else
    {
      rightComponent.notify();
      rightComponent.acceptMessage(message, path, sending);
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
      if(locked == false)
      {
        try
        {
          this.wait();
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}