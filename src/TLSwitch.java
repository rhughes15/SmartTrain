public class TLSwitch extends Switch
{
  public TLSwitch(int id, int trackX, int trackY, int guiX, int guiY, int partnerId, Component leftComponent)
  {
    super(id, trackX, trackY, guiX, guiY, partnerId, leftComponent);
    canGoFromLeft = new boolean[]{true, false};
    canGoFromRight = new boolean[]{true, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}
