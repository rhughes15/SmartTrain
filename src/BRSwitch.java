public class BRSwitch extends Switch
{
  public BRSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{true, false};
    canGoFromSwitch = new boolean[]{false, true};
  }
}