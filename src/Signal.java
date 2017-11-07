public class Signal extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int trackX, trackY, guiX, guiY;

  public Signal(int id, int trackX, int trackY, int guiX, int guiY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.guiX = guiX;
    this.guiY = guiY;
    this.leftComponent = leftComponent;
    locked = true;
  }

  @Override
  public void acceptMessage(String message)
  {

  }

  @Override
  public void lock()
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
