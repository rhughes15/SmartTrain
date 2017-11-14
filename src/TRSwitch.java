public class TRSwitch extends Switch
{
  public TRSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{false, true, true};
    canGoFromRight = new boolean[]{true, false, false};
    canGoFromSwitch = new boolean[]{true, false, false};
  }
}