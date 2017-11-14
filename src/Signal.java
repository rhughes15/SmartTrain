import java.util.ArrayList;

public class Signal extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int guiX, guiY;

  public Signal(int id, int trackX, int trackY,Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    locked = true;
  }

  @Override
  public void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {

  }

  @Override
  public void run()
  {
    synchronized (this)
    {
      if(locked == false) {
        try {
          this.wait();
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}
