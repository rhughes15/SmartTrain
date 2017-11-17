import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class is used to represent a switch in our system. This
// where a lot of the logic for the route finding and route blocking
// occurs. As the route finding message gets sent, it passes the
// message along to all available routes as determined in the boolean
// arrays that are defined by the child classes. After the route has been
// found, as the message is on its way back, the switch sends out a
// message to the Signals to turn red. They are supposed to send out a message
// to the signals to turn green when the train leaves the switch on its
// found route. Switches have a left and right component on the same line,
// the partner component is the other switch on another line.
//***********************************

public abstract class Switch extends Component
{

  protected Component leftComponent;

  public Component getPartnerComponent()
  {
    return partnerComponent;
  }

  protected Component partnerComponent;
  protected boolean[] canGoFromLeft, canGoFromRight, canGoFromSwitch;
  private String message;
  private ArrayList<Component> path;
  private boolean sending;

  public Switch(int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
  }
  public synchronized void setPartner(Component component)
  {
    partnerComponent = component;
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
    if(path != null && sending) path.add(this);
    boolean rightToLeft = message.substring(0, 1).compareTo(message.substring(1)) > 0;
    if (rightToLeft) // message going right to left
    {
        if (canGoFromRight[0]) leftComponent.acceptMessage(message, path, sending);
        if (canGoFromRight[1])partnerComponent.acceptMessage(message, path, sending);
    }
    else
    {
      if (canGoFromLeft[2])rightComponent.acceptMessage(message, path, sending);
      if (canGoFromLeft[1])partnerComponent.acceptMessage(message, path, sending);
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
          messageAccepted(message, path, sending);
      }
    }
  }

  public void display(GraphicsContext gc)
  {
    if(train != null) train.display(gc);
  }
}