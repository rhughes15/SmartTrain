import java.util.ArrayList;

public class Signal extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked, red;
  private int guiX, guiY;
  private boolean green = true;

  public Signal(int id, int trackX, int trackY,Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
  }

  @Override
  public void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(message.length() == 2)
    {
      if (sending) path.add(this);
      else
      {
        locked = true;
        red = false;
      }

      if (message.substring(0, 1).compareTo(message.substring(1)) < 0)  // message going right to left
      {
        leftComponent.notify();
        leftComponent.acceptMessage(message, path, sending);
      }
      else // message going left to right
      {
        rightComponent.notify();
        rightComponent.acceptMessage(message, path, sending);
      }
    }
    else if(message.equalsIgnoreCase("red") && !locked)
    {
      red = true;
      locked = true;
    }
    else if(message.equalsIgnoreCase("green") && !locked)
    {
      red = false;
      locked = true;
    }
  }

  @Override
  public void run()
  {
    synchronized (this)
    {
      try {
        this.wait();
      } catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public boolean isGreen()
  {
    return green;
  }
}
