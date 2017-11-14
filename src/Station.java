public class Station extends Component
{
  private Component track, leftComponent;
  private String stationName;
  public Station(int id, int trackX, int trackY, String stationName)
  {
    this.stationName = stationName;
    this.trackX = trackX;
    this.trackY = trackY;
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
