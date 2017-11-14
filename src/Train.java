import javafx.scene.image.Image;

import java.util.ArrayList;

public class Train implements Runnable
{
  private Component currentComponent;
  private int x, y;
  private Image image;

  public Train(Component component)
  {
    currentComponent = component;
  }

  public void travel(ArrayList<Component> path)
  {

  }

  @Override
  public void run()
  {
    synchronized (this)
    {
      try
      {
        this.wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
