public class BRSwitch extends Switch
{
  public BRSwitch(int id, int trackX, int trackY, int guiX, int guiY, int partnerId, Component leftComponent)
  {
    super(id, trackX, trackY, guiX, guiY, partnerId, leftComponent);
    canGoFromLeft = new boolean[]{true, true};
    canGoFromRight = new boolean[]{true, false};
    canGoFromSwitch = new boolean[]{false, true};
  }
}