import javafx.scene.canvas.GraphicsContext;

public class TLSwitch extends Switch
{
  private int length = Reference.length;
  private int y = Reference.y;
  public TLSwitch(int trackX, int trackY, Component leftComponent)
  {
    super(trackX, trackY,leftComponent);
    canGoFromLeft = new boolean[]{false, true, false};
    canGoFromRight = new boolean[]{true, false, true};
    canGoFromSwitch = new boolean[]{false, true, false};
  }

  @Override
  public synchronized void display(GraphicsContext gc)
  {
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200, length +length*this.getTrackX()+44, y*this.getTrackY() + 200);
    gc.strokeLine(length/2 + length*this.getTrackX()+50, y*this.getTrackY() + y/2 + 200, length +length*this.getTrackX()+44, (y)*this.getTrackY() + 200 );
  }
}