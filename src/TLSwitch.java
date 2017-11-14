public class TLSwitch extends Switch
{
  public TLSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY,leftComponent);
    canGoFromLeft = new boolean[]{false, true, false};
    canGoFromRight = new boolean[]{true, false, true};
    canGoFromSwitch = new boolean[]{false, true, false};
  }
}