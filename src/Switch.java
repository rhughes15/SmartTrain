import java.util.ArrayList;

public abstract class Switch extends Component
{

  protected Component leftComponent, partnerComponent;
  private int guiX, guiY, partnerId, id;
  private boolean locked;
  protected boolean[] canGoFromLeft, canGoFromRight, canGoFromSwitch;

  public Switch(int id, int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    locked = false;
  }
  public void setPartner(Component component)
  {
    partnerComponent = component;
  }

  @Override
  public void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(sending)
    {
      path.add(this);
      if (message.substring(0, 1).compareTo(message.substring(1)) < 0) // message going right to left
      {
        if (canGoFromRight[0])
        {
          leftComponent.notify();
          leftComponent.acceptMessage(message, path, sending);
        }
        if (canGoFromRight[2])
        {
          partnerComponent.notify();
          partnerComponent.acceptMessage(message, path, sending);
        }
      }
      else  // message going from left to right
      {
        if(canGoFromLeft[1])
        {
          rightComponent.notify();
          rightComponent.acceptMessage(message, path, sending);
        }
        if(canGoFromLeft[2])
        {
          partnerComponent.notify();
          partnerComponent.acceptMessage(message, path, sending);
        }
      }
    }
    else
    {
      locked = true;
      closeSignals();

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

  private void closeSignals()
  {
    leftComponent.notify();
    leftComponent.acceptMessage("RED", null, true);
    rightComponent.notify();
    rightComponent.acceptMessage("RED", null, true);
  }

  private void openSignals()
  {
    leftComponent.notify();
    leftComponent.acceptMessage("GREEN", null, true);
    rightComponent.notify();
    rightComponent.acceptMessage("GREEN", null, true);
  }
}