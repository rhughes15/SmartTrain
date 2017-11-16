import javafx.scene.canvas.GraphicsContext;

public class TRSwitch extends Switch
{
  private int length = Reference.length;
  private int y = Reference.y;
  public TRSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{false, true, true};
    canGoFromRight = new boolean[]{true, false, false};
    canGoFromSwitch = new boolean[]{true, false, false};
  }

  @Override
  public synchronized void display(GraphicsContext gc)
  {
    gc.strokeLine(length * this.getTrackX() + 55, y * this.getTrackY() + 200, length + length * this.getTrackX() + 44, y * this.getTrackY() + 200);
    gc.strokeLine(length * this.getTrackX() + 55, y * this.getTrackY() + 200, length / 2 + length * this.getTrackX() + 50, (y) * this.getTrackY() + 200 + y / 2);
  }
}