public abstract class Component implements Runnable
{
  abstract void acceptMessage(String message);
  abstract void lock();
  public Component rightComponent;
  public int trackX, trackY;
  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }
  public int getTrackX()
  {
    return trackX;
  }
  public int getTrackY()
  {
    return trackY;
  }


}
