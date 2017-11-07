public abstract class Component implements Runnable
{
  abstract void acceptMessage(String message);
  abstract void lock();
  public Component rightComponent;
  public void setRightComponent(Component component)
  {
    this.rightComponent= component;
  }
}
