public class Track extends Component
{
  private Component leftComponent, rightComponent;
  private boolean locked;
  private int guiX, guiY, id;

  public Track(int id, int length, int trackX, int trackY, Component leftComponent)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.id = id;
    this.leftComponent = leftComponent;
    locked = false;
  }
  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }
  @Override
  public void acceptMessage(String message) {

  }

  @Override
  public void lock()
  {
    this.locked = true;
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