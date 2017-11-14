public class TLSwitch extends Switch
{
  public TLSwitch(int id, int trackX, int trackY, Component leftComponent)
  {
    super(id, trackX, trackY,leftComponent);
    canGoFromLeft = new boolean[]{true, false};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}
