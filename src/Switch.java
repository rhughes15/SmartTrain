public abstract class Switch extends Component
{
  private Component leftComponent, partnerComponent;
  private int trackX, trackY, guiX, guiY, partnerId, id;
  private boolean locked;
  protected boolean[] canGoFromLeft, canGoFromRight, canGoFromSwitch;

  public Switch(int id, int trackX, int trackY, int guiX, int guiY, int partnerId, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.guiX = guiX;
    this.guiY = guiY;
    this.leftComponent = leftComponent;
    this.partnerId = partnerId;
    locked = false;
  }
  public void setPartner(Component component)
  {
    partnerComponent = component;
  }
  @Override
  public void acceptMessage(String message)
  {

  }

  @Override
  public synchronized void lock()
  {
    this.lock();
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
        } catch (Exception InterruptedException) {}
      }
    }
  }
}