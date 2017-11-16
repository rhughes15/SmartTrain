import javafx.scene.canvas.GraphicsContext;

//***********************************
// Ryan Hughes and Jacob Traunero
//
// This class represents a specific switch, one of four.
// The only things that are unique about these switches are their
// constructors and the display method. This one is the switch
// with a partner that is up and to the right of it.
//***********************************

public class BRSwitch extends Switch
{
  private int length = Reference.length;
  private int y = Reference.y;
  public BRSwitch(int trackX, int trackY, Component leftComponent)
  {
    super(trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{false, true, true};
    canGoFromRight = new boolean[]{true, false, false};
    canGoFromSwitch = new boolean[]{true, false, false};

  }
  @Override
  public  synchronized void display(GraphicsContext gc)
  {
    super.display(gc);
    gc.strokeLine(length*this.getTrackX()+55, y*this.getTrackY() + 200, length +length*this.getTrackX()+44, y*this.getTrackY() + 200);
    gc.strokeLine(length/2 + length*this.getTrackX()+50, y*this.getTrackY() - y/2 + 200, length +length*this.getTrackX()+44, (y)*this.getTrackY() + 200 );
  }
}