public class Signal extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int guiX, guiY;
  private boolean green = true;
  public Signal(int id, int trackX, int trackY,Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
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

  public boolean isGreen()
  {
    return green;
  }
}
