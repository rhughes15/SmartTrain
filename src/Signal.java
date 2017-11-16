import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Signal extends Component
{
  private Component leftComponent, rightComponent;
  private int length = Reference.length;
  private int y = Reference.y;
  private boolean locked, red;
  private boolean green = true;

  public Signal(int trackX, int trackY,Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
  }

  @Override
  public void display(GraphicsContext gc)
  {
    if(this.isGreen())gc.setFill(Color.GREEN);
    else gc.setFill(Color.RED);
    gc.fillOval(length/2 + length*this.getTrackX()+40, y*this.getTrackY() + 175 , 20, 20);
    gc.strokeLine(length * this.getTrackX() + 55, y * this.getTrackY() + 200, length + length * this.getTrackX() + 44, y * this.getTrackY() + 200);
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
