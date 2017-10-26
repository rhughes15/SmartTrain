public abstract class Switch implements Component
{
  private Component leftComponent, rightComponent, switchComponent;
  private int trackX, trackY, guiX, guiY;
  private boolean locked;
  protected boolean[] canGoFromLeft, canGoFromRight, canGoFromSwitch;

  public Switch(int trackX, int trackY, int guiX, int guiY,
                Component leftComponent, Component rightComponent, Component switchComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.guiX = guiX;
    this.guiY = guiY;
    this.leftComponent = leftComponent;
    this.rightComponent = rightComponent;
    this.switchComponent = switchComponent;
    locked = false;
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