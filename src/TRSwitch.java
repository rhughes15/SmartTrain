public class TRSwitch extends Switch
{
  public TRSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY, leftComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{false, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}