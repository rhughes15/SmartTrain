public class TRSwitch extends Switch
{
  public TRSwitch(int id, int trackX, int trackY, int guiX, int guiY, int partnerId, Component leftComponent)
  {
    super(id, trackX, trackY, guiX, guiY, partnerId, leftComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{false, true};
    canGoFromSwitch = new boolean[]{true, false};
  }
}