public class Station extends Component
{
  private Component track, leftComponent;
  private int trackX, trackY, guiX, guiY;
  public Station(int id, int trackX, int trackY, int guiX, int guiY)
  {
    this.trackX = trackX;
    this.trackY = trackY;
    this.guiX = guiX;
    this.guiY= guiY;
  }
  public void setLeftComponent(Component component)
  {
    leftComponent = component;
  }
  @Override
  void acceptMessage(String message) {

  }

  @Override
  void lock() {

  }

  @Override
  public void run() {

  }
}
