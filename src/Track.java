import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Track extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int length = Reference.length;
  private int y = Reference.y;

  public Track(int id, int length, int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    locked = false;
  }
  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }

  @Override
  public void display(GraphicsContext gc)
  {
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200, length +length*this.getTrackX()+44, y*this.getTrackY() + 200);
    if(train != null) train.display(gc);
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(message.equalsIgnoreCase("red") || message.equalsIgnoreCase("green"))
    {
      if(sending)
      {
        synchronized (rightComponent)
        {
          rightComponent.notify();
        }
        rightComponent.acceptMessage(message, path, sending);
      }
      else
      {
        synchronized (leftComponent)
        {
          leftComponent.notify();
        }
        leftComponent.acceptMessage(message, path, sending);
      }
    }
    else if(message.substring(0, 1).compareTo(message.substring(1)) > 0)
    {
      if(sending) path.add(this);
      else locked = true;
      synchronized (leftComponent)
      {
        leftComponent.notify();
      }
      leftComponent.acceptMessage(message, path, sending);
    }
    else
    {
      if(sending) path.add(this);
      else locked = true;
      synchronized (rightComponent)
      {
        rightComponent.notify();
      }
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