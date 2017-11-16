import javafx.scene.canvas.GraphicsContext;

public class BLSwitch extends Switch
{
  private int length = Reference.length;
  private int y = Reference.y;
  public BLSwitch(int id, int trackX, int trackY, Component leftNeighbor)
  {
    super(id, trackX, trackY, leftNeighbor);
    canGoFromLeft = new boolean[]{false, true, false};
    canGoFromRight = new boolean[]{true, false, true};
    canGoFromSwitch = new boolean[]{false, true, false};
  }
  @Override
  public void display(GraphicsContext gc)
  {
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200, length +length*this.getTrackX()+44, y*this.getTrackY() + 200);
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200,  length/2 +length*this.getTrackX()+50, (y)*this.getTrackY() + 200 - y/2);
  }
}
