import java.util.ArrayList;

public abstract class Switch extends Component
{

  protected Component leftComponent, partnerComponent;
  private boolean locked;
  protected boolean[] canGoFromLeft, canGoFromRight, canGoFromSwitch;

  public Switch(int id, int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.leftComponent = leftComponent;
    locked = false;
  }
  public synchronized void setPartner(Component component)
  {
    partnerComponent = component;
  }

  @Override
  public synchronized void acceptMessage(String message, ArrayList<Component> path, boolean sending)
  {
    if(sending && !locked)
    {
      path.add(this);
      if (message.substring(0, 1).compareTo(message.substring(1)) > 0) // message going right to left
      {
        if (canGoFromRight[0])
        {
          synchronized (leftComponent)
          {
            leftComponent.notify();
          }
          leftComponent.acceptMessage(message, path, sending);
        }
        if (canGoFromRight[2])
        {
          synchronized (partnerComponent)
          {
            partnerComponent.notify();
          }
          partnerComponent.acceptMessage(message, path, sending);
          System.out.println("PARTNER MESSAGE");
        }
      }
      else if (!locked)  // message going from left to right
      {
        if(canGoFromLeft[1])
        {
          synchronized (rightComponent)
          {
            rightComponent.notify();
          }
          rightComponent.acceptMessage(message, path, sending);
        }
        if(canGoFromLeft[2])
        {
          synchronized (partnerComponent)
          {
            partnerComponent.notify();
          }
          partnerComponent.acceptMessage(message, path, sending);
          System.out.println("PARTNER MESSAGE");
        }
      }
    }
    else if(!locked)
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
    leftComponent.acceptMessage("RED", null, false);
    rightComponent.notify();
    rightComponent.acceptMessage("RED", null, true);
  }

  private void openSignals()
  {
    leftComponent.notify();
    leftComponent.acceptMessage("GREEN", null, false);
    rightComponent.notify();
    rightComponent.acceptMessage("GREEN", null, true);
  }
}