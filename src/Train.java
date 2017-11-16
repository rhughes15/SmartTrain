
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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

  public synchronized void travel(ArrayList<Component> path)
  {
    for(Component c : path)
    {
      if (c instanceof Track) System.out.println("T");
      else if (c instanceof Switch) System.out.println("Sw");
      else if (c instanceof Signal) System.out.println("Si");
    }
    for(int i = 0; i < path.size(); i++)
    {
      currentComponent.acceptMessage("LTRAIN", null, true);
      currentComponent = path.get(i);
      currentComponent.acceptMessage("ATRAIN", null, true);
      try {
        this.wait(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }

  public void display(GraphicsContext gc)
  {
    gc.setFill(Color.BLACK);
    gc.fillRect(x, y, 100, 50);
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