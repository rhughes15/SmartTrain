import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to represent a signal and its adjacent track
// in our system. It has a boolean to represent the signal state
// (green or !green) as well as a left and right component. It will
// generally only pass a message from one side of it to the other, unless
// the message is "RED" or "GREEN". In these cases the color of the light
// will change.
//***********************************

public class Signal extends Component
{
  private Component leftComponent;
  private int length = Reference.length;
  private int y = Reference.y;
  private boolean locked;
  private boolean green = false;
  private boolean sending = true;
  private ArrayList<Component> path;
  private String message;

  public Signal(int trackX, int trackY,Component leftComponent)
  {

    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    guiX = length * this.getTrackX() + 55;
    guiY =  y * this.getTrackY() + 200;
  }

  @Override
  public void display(GraphicsContext gc)
  {
    if(train != null) train.display(gc);
    if(this.isGreen())gc.setFill(Color.GREEN);
    else gc.setFill(Color.RED);
    gc.fillOval(length/2 + length*this.getTrackX()+40, y*this.getTrackY() + 175 , 20, 20);
    gc.strokeLine(guiX, guiY, length + length * this.getTrackX() + 44, y * this.getTrackY() + 200);
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    this.message = message;
    this.path = new ArrayList<>(path);
    this.sending = sending;
    this.notify();

  }
  public void messageAccepted(String message, ArrayList<Component> path, boolean sending)
  {
    //if(!sending)System.out.println(this.getClass());
    if(message.length() == 2)
    {

      if (sending) path.add(this);
      else if(path.contains(this))
      {
        green = true;
      }

      if (message.substring(0, 1).compareTo(message.substring(1)) > 0)  // message going right to left
      {
        leftComponent.acceptMessage(message, path, sending);
      }
      else // message going left to right
      {
        rightComponent.acceptMessage(message, path, sending);
      }
    }
  }
  @Override
  public void run()
  {

    synchronized (this)
    {
      while (true)
      {
        try
        {
          this.wait();
        } catch (InterruptedException e)
        {
          e.printStackTrace();
        }
        messageAccepted(message,path, sending);
        message = "";
      }
    }
  }

  public synchronized boolean isGreen()
  {
    return green;
  }
}
