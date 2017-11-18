
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class represents a train within our system. It is
// created by the CanvasListener when one of the stations is
// clicked. It is passed the Component that it will start at,
// a station, and after being created it waits for further
// instructions from the travel, move, and update methods
//***********************************

public class Train
{
  private Component currentComponent;
  private int x, y, dx, dy, destx, desty;
  private Image image;

  public Train(Component component)
  {
    currentComponent = component;
    x = 100;
    y = 100;
    dx = dy = 0;
    image = new Image("resources/Train.png");

  }

  public synchronized void travel(ArrayList<Component> path)
  {
    if (path == null) System.out.println("Station Unreachable");
    System.out.println("Train path: ");
    for(Component c : path)
    {
      if (c instanceof Track) System.out.print("T ");
      else if (c instanceof Signal) System.out.print("Si ");
      else if (c instanceof Switch) System.out.print("Sw ");
      else if (c instanceof Station) System.out.println("Station " + ((Station) c).getStationName());
    }

  }

  public void move(int newx, int newy)
  {
    dx = (newx - x)/60;
    dy = (newy - y)/60;
    destx = newx;
    desty = newy;
  }

  public void update()
  {
    if(x != destx) x += dx;
    if(y != desty) y += dy;
  }

  public synchronized void display(GraphicsContext gc)
  {
    gc.drawImage(image,currentComponent.getGuiX(), currentComponent.getGuiY()-20);
  }

}
