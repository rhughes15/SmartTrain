import javafx.scene.canvas.GraphicsContext;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class represents a specific switch, one of four.
// The only things that are unique about these switches are their
// constructors and the display method. This one is the switch
// with a partner that is below and to the right of it.
//***********************************

public class TRSwitch extends Switch
{
  private int length = Reference.length;
  private int y = Reference.y;
  public TRSwitch(int trackX, int trackY, Component leftComponent)
  {
    super(trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{false, true, true};
    canGoFromRight = new boolean[]{true, false, false};
    canGoFromSwitch = new boolean[]{true, false, false};
  }

  @Override
  public synchronized void display(GraphicsContext gc)
  {
    super.display(gc);
    guiX = length * this.getTrackX() + 55;
    guiY = y * this.getTrackY() + 200;
    gc.strokeLine(guiX, guiY, length + length * this.getTrackX() + 44, guiY);
    gc.strokeLine(guiX, guiY, length / 2 + length * this.getTrackX() + 50, guiY + y / 2);
  }
}